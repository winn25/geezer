package com.khoders.geez.entities;

import com.khoders.springapi.spring.SpringBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "file_ext")
public class FileExt extends SpringBaseModel {
    @Column(name = "ext_name")
    private String extName;
    public String getExtName() {
        return extName;
    }
    public void setExtName(String extName) {
        this.extName = extName;
    }
}
