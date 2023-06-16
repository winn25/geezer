package com.khoders.geez.dto;

public class FileDirectoryDto extends BaseDto{
    public String directoryName;
    public String directoryPath;
    public String parentId;
    public boolean childDirectory;

    public String getDirectoryName() {
        return directoryName;
    }
    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public boolean isChildDirectory() {
        return childDirectory;
    }
    public void setChildDirectory(boolean childDirectory) {
        this.childDirectory = childDirectory;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
