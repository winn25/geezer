package com.khoders.geez.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "file_directory")
public class FileDirectory extends UserAccountRecord{
    public static final String _directoryName = "directoryName";
    @Column(name = "directory_name")
    private String directoryName;
    @Column(name = "directory_path")
    private String directoryPath;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "child_directory")
    private boolean childDirectory;
    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
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
    public boolean isChildDirectory() {
        return childDirectory;
    }
    public void setChildDirectory(boolean childDirectory) {
        this.childDirectory = childDirectory;
    }
}
