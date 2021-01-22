package com.example.ehr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Register extends AppCompatActivity {

    EditText name,address,contact,email,age,bloodg,password,cpassword,aadhar,pretreat;
    Button register;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.editTextUserName);
        address=(EditText)findViewById(R.id.editTextAddress);
        contact=(EditText)findViewById(R.id.editTextPhone);
        email =(EditText)findViewById(R.id.editTextEmail);
        age=(EditText)findViewById(R.id.editTextAge);
        bloodg=(EditText)findViewById(R.id.editTextBloodg);
        password=(EditText)findViewById(R.id.editTextPassword);
        cpassword=(EditText)findViewById(R.id.editTextcPassword);
        aadhar=(EditText)findViewById(R.id.editTextaadhar);
        pretreat=(EditText)findViewById(R.id.editTextPretreat);
        register=(Button)findViewById(R.id.buttonRegister);
        login=(TextView)findViewById(R.id.buttonLogin);

                    //connection
        Firebase.setAndroidContext(this);
        final Firebase fb=new Firebase(Mconfig.fburl+"/Details");

        register.setOnClickListener(new View.OnClickListener() {
            Boolean validationError = false;

            @Override
            public void onClick(View view) {


                StringBuilder validationErrorMessage = new StringBuilder("Please ");

                String nm[]=name.getText().toString().split(" ");

                validationError = false;

                if (isEmpty(name)) {
                    validationError = true;
                    validationErrorMessage.append("Enter First Name");

                }else if (isEmpty(name)) {
                    validationError = true;
                    validationErrorMessage.append("Enter Last Name");

                } else if (nm.length<1)
                {
                    validationError=true;
                    validationErrorMessage.append("Enter First and Last Name ");
                }
                else if(isEmpty(address))
                {
                    validationError = true;
                    validationErrorMessage.append("Enter Address");

                }else if (isEmpty(contact)) {
                    validationError = true;
                    validationErrorMessage.append("Enter Mobile No ");
                }else  if (contact.length()!=10)
                {
                    validationError = true;
                    validationErrorMessage.append("Enter Valid Mobile No");
                }

                else if(isEmpty(email))
                {
                    validationError = true;
                    validationErrorMessage.append("Enter Email");

                }else if(email.getText().toString().contains("@")==false || email.getText().toString().contains(".")==false )
                {
                    validationError=true;
                    validationErrorMessage.append("Enter Valid Email Id");
                }
                else if (isEmpty(password))
                {
                    validationError = true;
                    validationErrorMessage.append("Enter Password");
                }
                else if (password.length()<5)
                {
                    validationError=true;
                    validationErrorMessage.append("Password length should be minimum 5");
                }
                else if (isEmpty(cpassword))
                {
                    validationError=true;
                    validationErrorMessage.append("Enter Confirm Password");

                }
                else if(!isMatching(password, cpassword))
                {
                    validationError = true;

                    validationErrorMessage.append("Enter Same Passwords!");

                }



                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(Register.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }





                String Name=name.getText().toString();
                String Address=address.getText().toString();
                String Contact=contact.getText().toString();
                String Email=email.getText().toString();
                String Age=age.getText().toString();
                String Bloodg=bloodg.getText().toString();
                String Password=password.getText().toString();
                String Aadhar=aadhar.getText().toString();
                String Pret=pretreat.getText().toString();

            RegisterDetail rd=new RegisterDetail();

            rd.setAddress(Address);
            rd.setAge(Age);
            rd.setBloodg(Bloodg);
            rd.setAadhar(Aadhar);
            rd.setPassword(Password);
            rd.setName(Name);
            rd.setContact(Contact);
            rd.setEmail(Email);
            rd.setPretreat(Pret);
            fb.push().setValue(rd);
            Intent i=new Intent(Register.this,Login.class);
            startActivity(i);
            }
        });

    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }


    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }

    }

}
