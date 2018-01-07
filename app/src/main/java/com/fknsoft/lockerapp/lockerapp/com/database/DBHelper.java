package com.fknsoft.lockerapp.lockerapp.com.database;

import  com.fknsoft.lockerapp.lockerapp.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Fahim on 11/01/2016.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "doclockappdb";
    private static final int DATABASE_VERSION = 1;

    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
    private Dao<UserTB, Long> userDAO;
    private Dao<LoginInfo, Long>  loginInfoDAO;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                /**
                 * R.raw.ormlite_config is a reference to the ormlite_config.txt file in the
                 * /res/raw/ directory of this project
                 * */
                R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates the  database tables
             */
            TableUtils.createTable(connectionSource, UserTB.class);
            TableUtils.createTable(connectionSource, LoginInfo.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            TableUtils.dropTable(connectionSource, UserTB.class, false);
            TableUtils.dropTable(connectionSource, LoginInfo.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an instance of the data access object
     * @return
     * @throws SQLException
     */
    public Dao<UserTB, Long> getUserDAO() throws SQLException {
        if(userDAO == null) {
            userDAO = getDao(UserTB.class);
        }
        return userDAO;
    }

    public Dao<LoginInfo, Long> getLoginInfoDAO() throws SQLException {
        if(loginInfoDAO == null) {
            loginInfoDAO = getDao(LoginInfo.class);
        }
        return loginInfoDAO;
    }


}
