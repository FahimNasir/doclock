package com.fknsoft.lockerapp.lockerapp.com.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by fahim on 1/6/2018.
 */

@DatabaseTable(tableName = "LOGIN_INFO")
public class LoginInfo
{
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public boolean isLoggedIn() {
        return IsLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        IsLoggedIn = loggedIn;
    }

    public boolean isFirstInstall() {
        return IsFirstInstall;
    }

    public void setFirstInstall(boolean firstInstall) {
        IsFirstInstall = firstInstall;
    }


    public Long getInfoId() {
        return InfoId;
    }

    public void setInfoId(Long infoId) {
        InfoId = infoId;
    }

    @DatabaseField(generatedId = true, unique = true)
    private Long InfoId;

    @DatabaseField(unique = true)
    private String UID;

    @DatabaseField
    private String UserId;

    @DatabaseField
    private boolean IsLoggedIn;

    @DatabaseField
    private boolean IsFirstInstall;

}
