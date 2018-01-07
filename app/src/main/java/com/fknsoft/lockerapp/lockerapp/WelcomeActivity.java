package com.fknsoft.lockerapp.lockerapp;

import android.content.Intent;
import android.graphics.Color;
import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import com.fknsoft.lockerapp.lockerapp.com.common.Keys;
import com.fknsoft.lockerapp.lockerapp.com.database.DBHelper;
import com.fknsoft.lockerapp.lockerapp.com.database.LoginInfo;
import com.fknsoft.lockerapp.lockerapp.com.database.UserTB;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Log;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    Keys key = new Keys();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        try
        {
            DBHelper db = OpenHelperManager.getHelper(this, DBHelper.class);
            Dao<LoginInfo, Long> daoLoginInfo = db.getLoginInfoDAO();

            if(daoLoginInfo.queryForAll().size() > 0)
            {
                List<LoginInfo> listLoginInf =
                        daoLoginInfo.queryForEq("IsFirstInstall", true);
                LoginInfo loginInfo = listLoginInf.get(0);
                if(loginInfo.isFirstInstall())
                {
                    Intent mainIntent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                    startActivity(mainIntent);
                }
                else
                {
                    Intent mainIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                }
            }
            else
            {
                LoginInfo loginInfo = GetInitialLoginInfo();
                Long ID = daoLoginInfo.extractId(loginInfo);
                if(ID == null || !daoLoginInfo.idExists(ID))
                {
                    daoLoginInfo.create(loginInfo);
                    Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    daoLoginInfo.update(loginInfo);
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                }
                Intent mainIntent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                startActivity(mainIntent);
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, R.string.AppErrorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private LoginInfo GetInitialLoginInfo()
    {
        LoginInfo li = new LoginInfo();
        li.setFirstInstall(true);
        li.setLoggedIn(false);
        li.setUID(key.LOGIN_INFO_ID);
        li.setUserId("");
        return  li;
    }
}
