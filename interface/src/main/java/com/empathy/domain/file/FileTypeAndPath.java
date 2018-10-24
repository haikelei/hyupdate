package com.empathy.domain.file;

/**
 * Created by dly on 2016/8/12.
 */
public enum FileTypeAndPath {
    IMAGE_TYPE_PATH("image", "/image"),
    VIDEO_TYPE_PATH("video", "/video"),
    VOICE_TYPE_PATH("voice", "/voice"),;


    FileTypeAndPath(String type, String path) {
        this.type = type;
        this.path = path;
    }

    String type;
    String path;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static String getPathByType(String type) {
        for (FileTypeAndPath fileTypeAndPath : FileTypeAndPath.values()) {
            if (fileTypeAndPath.getType().equals(type)) {
                return fileTypeAndPath.getPath();
            }
        }
        return null;
    }
}
