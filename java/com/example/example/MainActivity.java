package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sname, fname, phone, email, pass;
    Button save, view;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new Database(this);

        sname = (EditText) findViewById(R.id.sname);
        fname = (EditText) findViewById(R.id.fname);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        save = (Button) findViewById(R.id.save);
        view = (Button) findViewById(R.id.view);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    db = openHelper.getWritableDatabase();

                    String sn = sname.getText().toString();
                    String fn = fname.getText().toString();
                    String ph = phone.getText().toString();
                    String em = email.getText().toString();
                    String ps = pass.getText().toString();

                    insertdata(sn, fn, ph, em, ps);
                    Toast.makeText(getApplicationContext(), "Registered Successffully", Toast.LENGTH_LONG).show();

                }
            }

            public boolean validate() {
                if (sname.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Surname Name", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (fname.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (phone.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (email.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (pass.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Choose password", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = getAllData();
                if (res.getCount() == 0) {
                    //show message if nothing is found
                           showMessage("Error", "No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Surname :" + res.getString(1) + "\n");
                    buffer.append("LastName :" + res.getString(2) + "\n");
                    buffer.append("Phone :" + res.getString(3) + "\n");
                    buffer.append("Email :" + res.getString(4) + "\n");
                    buffer.append("Password :" + res.getString(5) + "\n\n");
                }
               // show all data found
                 showMessage("Data", buffer.toString());
            }
        });

    }
        public void insertdata(String sn, String fn, String ph, String em, String ps){
            ContentValues contentValues = new ContentValues();

            contentValues.put(Database.Col2, sn);
            contentValues.put(Database.Col4, fn);
            contentValues.put(Database.Col5, ph);
            contentValues.put(Database.Col6, em);
            contentValues.put(Database.Col3, ps);
            long id = db.insert(Database.Table_Name, null, contentValues);

        }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public Cursor getAllData()
    {
        db = openHelper.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Database.Table_Name, null);
        return res;
    }

            }
