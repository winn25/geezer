package com.khoders.geez.entities;


import com.khoders.geez.entities.auth.UserAccount;
import com.khoders.springapi.spring.SpringBaseModel;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UserAccountRecord extends SpringBaseModel {
    public static final String _userAccount = "userAccount";
    public static final String _userAccountId = _userAccount + "." + UserAccount._id;
    @JoinColumn(name = "user_account")
    @ManyToOne
    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
