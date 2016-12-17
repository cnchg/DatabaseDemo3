package com.tricloudcommunications.ce.databasedemo3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLiteDatabase usersDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
        usersDB.execSQL("CREATE TABLE IF NOT EXISTS users (userName VARCHAR, userAge INT(3))");
        usersDB.execSQL("INSERT INTO users (userName, userAge) VALUES('Chandler', 37)");
        usersDB.execSQL("INSERT INTO users (userName, userAge) VALUES('Triphose', 35)");

        Cursor c = usersDB.rawQuery("SELECT * FROM users", null);

        int userNameIndex = c.getColumnIndex("userName");
        int userAgeIndex = c.getColumnIndex("userAge");

        c.moveToFirst();

        while (c != null){

            Log.i("Database Results", "User Name- " + c.getString(userNameIndex) + " User Age- " + c.getString(userAgeIndex));

            c.moveToNext();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
