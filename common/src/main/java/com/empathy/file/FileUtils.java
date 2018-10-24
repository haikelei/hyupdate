package com.empathy.file;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtils {
	
	public static String getUrlByUri(String uri){
		return "/"+uri;
	}
	
	public static File getDynamicFilePath(HttpServletRequest request){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String realPath = request.getSession().getServletContext().getRealPath("/pic/image/project/"+format.format(new Date()));
		File f = new File(realPath);
		if(!f.exists()){
			f.mkdirs();
		}
		return f;
	}

	public static String getDynamicUrlPath() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
		String fileURL = "/image/project/"+format.format(new Date());
		return fileURL;
	}
	
	public static String saveFile(HttpServletRequest request,MultipartFile file){
		if(file==null||file.isEmpty()){
			return null;
		}
		String saveName = UUID.randomUUID().toString()+getSuffix(file.getOriginalFilename());
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(getDynamicFilePath(request),saveName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getDynamicUrlPath()+saveName;
	}
	
	// 保存图片并降质
	/*public static String saveImage(HttpServletRequest request,MultipartFile file){
		if(file==null||file.isEmpty()){
			return null;
		}
		String saveName = UUID.randomUUID().toString()+getSuffix(file.getOriginalFilename());
		if(!saveName.endsWith(".png")&&!saveName.endsWith(".jpg")){
			throw new ServiceException("不接受该图片类型!");
		}
		
		saveName = saveName.replace(".png", ".jpg");
//		104857600
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(getDynamicFilePath(request),saveName)));
			
			// 如果文件大于150k
			if(file.getSize()>=150000){
				float fQuality = 100000*1.0f/file.getSize();
				Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(fQuality).toFile(new File(getDynamicFilePath(request),saveName));//按比例缩小
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getDynamicUrlPath()+saveName;
	}*/
	
	
	public static String getSuffix(String fileName){
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		return suffix;
	}
}
