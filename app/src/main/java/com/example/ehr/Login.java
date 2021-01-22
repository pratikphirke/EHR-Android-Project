package com.example.ehr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText Contact,Password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Contact = (EditText) findViewById(R.id.editTextContact);
        Password = (EditText) findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.buttonLogin);

        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#D81B60'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        Firebase.setAndroidContext(this);
        final Firebase fb = new Firebase(Mconfig.fburl+ "/Details");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String contact = Contact.getText().toString();
             /*   if (!isValidEmail(contact)) {
                    //Set error message for email field
                    contact.setError("Invalid Contact Number");
                }*/

                final String pass = Password.getText().toString();
                if (!isValidPassword(pass)) {
                    //Set error message for password field
                    Password.setError("Password must be 4 characters..");
                }


                fb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cnt = (int) dataSnapshot.getChildrenCount();
                        int flg = 0;
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            RegisterDetail rd = data.getValue(RegisterDetail.class);
                            if (rd.getContact().equals(Contact.getText().toString()) && rd.getPassword().equals(Password.getText().toString())) {
                                flg = 1;
                                Upload.contact=rd.getContact();
                                DisplayDocActivity.contact=rd.getAadhar();
                                Document.contact=rd.getAadhar();
                                ShowList.contact=rd.getAadhar();
                                creatCat.contact=rd.getAadhar();

                                Ptreatment.code=rd.getAadhar();
                                Main2Activity_nav.uname=rd.getName();
                                Main2Activity_nav.email=rd.getEmail();

                               // Doctor.con=rd.getContact();
                                //FavouriteActivity.phone=rd.getContact();
                                //BookingHistory.phn=rd.getContact();
                                //GetAppointment.Contact=rd.getContact();
                               // GetAppointment.uname=rd.getName();
                               // GetAppointment.uaddress=rd.getAddress();
                               // CustomDynamicd.phonenumber=rd.getContact();
                               // GetAppointment.fname=rd.getName();

                                break;
                            }

                        }if (flg == 1) {
                            Toast.makeText(Login.this, "succcessssfulll", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, Main2Activity_nav.class);

                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Unsuccessfull", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                     }
        });

            }

            // @SuppressWarnings("deprecation")
            public static Spanned fromHtml(String html) {
                Spanned result;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
                } else {
                    result = Html.fromHtml(html);
                }
                return result;
            }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

}