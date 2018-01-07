package com.fknsoft.lockerapp.lockerapp.com.database;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;
/**
 * Created by fahim on 11/25/2017.
 */

@DatabaseTable(tableName = "APP_USER")
public class UserTB {

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAuthType() {
        return AuthType;
    }

    public void setAuthType(int authType) {
        AuthType = authType;
    }

    @DatabaseField(generatedId = true, unique = true)
    private int UserId;

    @DatabaseField
    private String UserName;

    @DatabaseField
    private int IsActive;

    @DatabaseField
    private String Password;

    @DatabaseField
    private int AuthType;

}
