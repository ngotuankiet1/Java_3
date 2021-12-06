package com.example.java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.java.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SignUp extends AppCompatActivity {

    EditText TaiKhoan,Email,MatKhau;
    Button DangKibtn, btnBack;
    TextView status;

    Connection con;

    Statement stmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TaiKhoan = (EditText) findViewById(R.id.Taikhoan);
        Email = (EditText) findViewById(R.id.Email);
        MatKhau = (EditText) findViewById(R.id.Matkhau);
        DangKibtn = (Button) findViewById(R.id.Dangky);
        btnBack = (Button) findViewById(R.id.Back);
        status = (TextView)findViewById(R.id.status);

        DangKibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignUp.registeruser().execute("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public class registeruser extends AsyncTask<String, String, String>{
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("sending Data to database");
        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Registration Successful");
            TaiKhoan.setText("");
            Email.setText("");
            MatKhau.setText("");
        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                con = connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
                if(con == null){
                    z = "Kiểm tra kết nối internet";
                }else{
                    String sql= "INSERT INTO register (name,email,password) VALUES('"+TaiKhoan.getText()+"','"+Email.getText()+"','"+MatKhau.getText()+"')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }
            }catch (Exception e){
                isSuccess = false;
                z = e.getMessage();
            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionClass(String user,String password, String database,String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+server+"/"+database+";user="+user+";password="+password+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch (Exception ex){
            Log.e("SQL Connection Error:", ex.getMessage());
        }
        return connection;
    }

}