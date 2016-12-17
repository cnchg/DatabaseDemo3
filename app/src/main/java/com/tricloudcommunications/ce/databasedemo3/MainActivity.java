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


        try {
            SQLiteDatabase usersDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            //usersDB.execSQL("CREATE TABLE IF NOT EXISTS users (userName VARCHAR, userAge INT(3))");
            //usersDB.execSQL("INSERT INTO users (userName, userAge) VALUES('Chandler', 37)");
            //usersDB.execSQL("INSERT INTO users (userName, userAge) VALUES('Triphose', 35)");
            //usersDB.execSQL("INSERT INTO users (userName, userAge) VALUES('Milan', 10)");
            //usersDB.execSQL("INSERT INTO users (userName, userAge) VALUES('Laila', 8)");

            //Cursor c = usersDB.rawQuery("SELECT * FROM users", null); //Gets all record in that users Table
            //Cursor c = usersDB.rawQuery("SELECT * FROM users WHERE userAge < 18", null);//Searched for all records where the userAge field has a value of less than 18
            //Cursor c = usersDB.rawQuery("SELECT * FROM users WHERE userName = 'Milan' AND userAge < 20", null);//Searched for all records where the userName='Milan' and the userAge<20
            //Cursor c = usersDB.rawQuery("SELECT * FROM users WHERE userName = 'Milan' AND userAge = 20", null);//Searches for all records where the userName='Milan' and the userAge = 20
            //Cursor c = usersDB.rawQuery("SELECT * FROM users WHERE userName LIKE 'L%'", null);//Searched for all records where the userName is LIKE it starts with a capital letter 'L' 'L%'
            //Cursor c = usersDB.rawQuery("SELECT * FROM users WHERE userName LIKE '%n'", null);//Searched for all records where the userName is LIKE it ends with a lower case letter 'n' '%n'
            Cursor c = usersDB.rawQuery("SELECT * FROM users WHERE userName LIKE '%o%'", null);//Searched for all records where the userName is LIKE it has a lower case letter 'o' inside of the string '%o%'

            int userNameIndex = c.getColumnIndex("userName");
            int userAgeIndex = c.getColumnIndex("userAge");

            c.moveToFirst();

            while (c != null) {

                Log.i("Database Results", "User Name: " + c.getString(userNameIndex) + " User Age: " + c.getString(userAgeIndex));

                c.moveToNext();
            }
        }catch (Exception e){

            e.printStackTrace();
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
