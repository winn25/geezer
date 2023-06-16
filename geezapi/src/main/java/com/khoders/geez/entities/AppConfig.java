package com.khoders.geez.entities;
import com.khoders.resource.enums.Status;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_config")
public class AppConfig extends UserAccountRecord implements Serializable {
    @Column(name = "config_name")
    private String configName;
    @Column(name = "config_value")
    private String configValue;
    @Column(name = "config_status")
    @Enumerated(EnumType.STRING)
    private Status configStatus;

    public String getConfigName() {
        return configName;
    }
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    public String getConfigValue() {
        return configValue;
    }
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
    public Status getConfigStatus() {
        return configStatus;
    }
    public void setConfigStatus(Status configStatus) {
        this.configStatus = configStatus;
    }
}
