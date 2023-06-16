package com.khoders.geez.dto;

public class FileMetadataDto extends BaseDto{
    public String directory;
    public String directoryId;
    public String fileName;
    private String fileExt;
    private String fileExtId;
    private String filePath;
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileExt() {
        return fileExt;
    }
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }
    public String getFileExtId() {
        return fileExtId;
    }
    public void setFileExtId(String fileExtId) {
        this.fileExtId = fileExtId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
