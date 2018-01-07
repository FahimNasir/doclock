package com.fknsoft.lockerapp.lockerapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.fknsoft.lockerapp.lockerapp.com.common.Keys;
import com.fknsoft.lockerapp.lockerapp.com.database.DBHelper;
import com.fknsoft.lockerapp.lockerapp.com.database.LoginInfo;
import com.fknsoft.lockerapp.lockerapp.com.database.UserTB;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class RegistrationActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    CheckBox cbAuthType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etUsername = (EditText)findViewById(R.id.ETUserName);
        etPassword = (EditText)findViewById(R.id.ETPassword);
        cbAuthType = (CheckBox)findViewById(R.id.cboxAuthType);

        try
        {
            Button btnSave = (Button)findViewById(R.id.btnSaveUser);
            btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUser();
            }
            });

            cbAuthType.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(cbAuthType.isChecked())
                    {
                        etPassword.setHint(R.string.hint_AR_ET_PinCode);
                        etPassword.setInputType(2); //number
                    }
                    else
                    {
                        etPassword.setHint(R.string.hint_AR_ET_Password);
                        etPassword.setInputType(1); //text
                    }
                }
            });
        }
        catch (Exception ex)
        {
            Toast.makeText(this, R.string.AppErrorMessage, Toast.LENGTH_SHORT).show();
        }
    }


    private void SaveUser()
    {
        try
        {
            DBHelper db = OpenHelperManager.getHelper(this, DBHelper.class);
            Dao<UserTB, Long> daoUser = db.getUserDAO();

            UserTB user = new UserTB();
            user.setIsActive(1);
            user.setPassword(etPassword.getText().toString().trim());
            user.setUserName(etUsername.getText().toString().trim());
            user.setAuthType((cbAuthType.isChecked()) ? 1 : 0);
            daoUser.create(user);

            Dao<LoginInfo, Long> daoLoginInfo = db.getLoginInfoDAO();
            LoginInfo loginInfo = daoLoginInfo.queryForEq("UID", new Keys().LOGIN_INFO_ID).get(0);

            loginInfo.setUserId(etUsername.getText().toString().trim());
            loginInfo.setFirstInstall(false);
            loginInfo.setLoggedIn(true);

            daoLoginInfo.update(loginInfo);

            Intent mainIntent = new Intent(RegistrationActivity.this, TagListingActivity.class);
            startActivity(mainIntent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
