package com.example.car_agency;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class sign_up extends AppCompatActivity {

    private static final String DB_Url = "jdbc:mysql://192.168.1.18/logo_me";
    private static final String USER = "adel2020";
    private static final String Pass = "adel2020";
    EditText name, email, password, phone;
    Button sign_up_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);

        sign_up_btn = findViewById(R.id.signUp);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate the fields and call sign method to implement the api
                if (validate(name) && validateEmail() && validate(password)) {
                    signUp();
                }
            }
        });
    }

    private boolean validateEmail() {
        String mail = email.getText().toString().trim();

        if (mail.isEmpty() || !isValidEmail(mail)) {
            email.setError("Email is not valid.");
            email.requestFocus();
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    public  void signUp() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(sign_up.this);
        //progressDialog.setCancelable(false); // set cancelable to false
        access();
        progressDialog.setTitle("Registration");
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);
        Toast toast = Toast.makeText(this, "Account Created", Toast.LENGTH_LONG);
        toast.show();

    }


    public String access() {
        String msg = "";
        String uname = name.getText().toString();
        String uemail = email.getText().toString();
        String upass = password.getText().toString();
        String uphone = phone.getText().toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_Url, USER, Pass);
            if (connection == null) {
                msg = "Wrong Connection";
            } else {
                String query = "INSERT INTO users (Name,Email,Password,Phone) VALUES('" + uname + "," + uemail + "," + upass + "," + uphone + "')";
                Statement stm = connection.createStatement();
                stm.executeUpdate(query);
                msg = "Inserting Successfully";
            }
            connection.close();
        } catch (Exception e) {
            msg = "Wong Connection";
            e.printStackTrace();
        }
        return msg;
    }


    //connect mysql DB with app

}




