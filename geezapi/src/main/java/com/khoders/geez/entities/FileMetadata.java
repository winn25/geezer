package com.khoders.geez.entities;
import javax.persistence.*;

@Entity
@Table(name = "file_metadata")
public class FileMetadata extends UserAccountRecord {
    public static final String _fileDirectory = "fileDirectory";
    public static final String _fileDirectoryId = FileDirectory._id;
    @JoinColumn(name = "file_directory", referencedColumnName = "id")
    @ManyToOne
    private FileDirectory fileDirectory;

    public static final String _fileName = "fileName";
    @Column(name = "file_name")
    private String fileName;
    public static final String _filePath = "filePath";
    @Column(name = "file_path")
    private String filePath;

    public static final String _fileExt = "fileExt";
    @JoinColumn(name = "file_ext", referencedColumnName = "id")
    @ManyToOne
    private FileExt fileExt;

    public FileDirectory getFileDirectory() {
        return fileDirectory;
    }
    public void setFileDirectory(FileDirectory fileDirectory) {
        this.fileDirectory = fileDirectory;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public FileExt getFileExt() {
        return fileExt;
    }
    public void setFileExt(FileExt fileExt) {
        this.fileExt = fileExt;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
