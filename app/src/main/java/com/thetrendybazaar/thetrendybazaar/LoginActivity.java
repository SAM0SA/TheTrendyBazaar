package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        loginPassword.setText("password");
        loginEmail.setText("0@g.com");


        loginBtn.setOnClickListener(e -> {
            String username = loginEmail.getText().toString();
            int id;
            try{
                id = Integer.parseInt(username);
                Employee employee = DatabaseManager.employees.select(id);
                if(employee == null){
                    Toast.makeText(this, "No such employee account exisits",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Employee.currentEmployeeId = employee.employeeId;
                    Intent dashboardIntent = new Intent(this, DashboardActivity.class);
                    startActivity(dashboardIntent);
                    finish();
                }
            }catch (NumberFormatException ex){
                int customerId = DatabaseManager.customers.validLogin(loginEmail.getText().toString(), loginPassword.getText().toString());
                if(customerId > 0){
                    Customer.currentCustomerId = customerId;
                    Intent storeIntent = new Intent(this, StoreActivity.class);
                    storeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(storeIntent);
                }else Toast.makeText(this, "No such account exists", Toast.LENGTH_LONG).show();
            }

        });
    }


}
