package com.empathy.dao;

import com.empathy.domain.bidding.File;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.domain.file.bo.FilesFindBo;

import java.util.List;

/**
 * Created by MI on 2017/12/21.
 */
public interface FileDao {
    void insert(File file);

    void updateByPrimaryKey(File file);

    void deleteByPrimaryKey(Long id);

    File selectByPrimaryKey(Long id);

    File queryPurposeId(String phone);


    File findFileByPurposeIdAndType(FileCarBo fileCarBo);

    List<File> findFileByPurposeIdAndTypeList(FileCarBo fileCarBo);
    List<String> findFileByPurposeIdAndTypeList1(FileCarBo fileCarBo);


    List<String> findFileByPurposeType(FilesFindBo bo);
}
