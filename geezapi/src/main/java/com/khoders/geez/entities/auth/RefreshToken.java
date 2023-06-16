package com.khoders.geez.entities.auth;

import com.khoders.springapi.spring.SpringBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "refresh_token")
public class RefreshToken extends SpringBaseModel {
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false, name = "issued_at")
    private Date issuedAt;

    @Column(nullable = false, name = "expiry_date")
    private Date expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
