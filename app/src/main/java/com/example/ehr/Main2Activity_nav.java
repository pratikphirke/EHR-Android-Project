package com.example.ehr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Main2Activity_nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
public static  String uname="";
    public static  String email="";
    ViewFlipper viewFlipper;
    CardView history;
    CardView carddoc,cardtreatment,cardbill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int images[]={R.drawable.gynec,R.drawable.ort,R.drawable.cardioo};
        viewFlipper = (ViewFlipper) findViewById(R.id.v_flipper);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       ImageView iv = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        TextView Name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        TextView uemail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email);
        Name.setText(uname);
        uemail.setText(email);


        for (int image : images) {
            flipperImages(image);
        }

        carddoc = (CardView) findViewById(R.id.carddocument);
        cardtreatment = (CardView) findViewById(R.id.cardtreatment);
        cardbill = (CardView) findViewById(R.id.cardbills);

        history = (CardView) findViewById(R.id.cardview1);

        carddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity_nav.this, DisplayDocActivity.class);
                startActivity(i);
            }
        });
        cardtreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Main2Activity_nav.this, Ptreatment.class);
                startActivity(i1);
            }
        });
        cardbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Main2Activity_nav.this, ShowList.class);
                startActivity(i1);
            }
        });


    }


    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2_activity_nav, menu);
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
                    Intent i=new Intent();
                    startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

     /*   if (id == R.id.nav_app) {

        Intent i=new Intent(Main2Activity_nav.this,Appointment.class);
        startActivity(i);
        } else*/ if (id == R.id.nav_doc) {
            Intent i=new Intent(Main2Activity_nav.this,DisplayDocActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_ptreat) {
            Intent i=new Intent(Main2Activity_nav.this,Ptreatment.class);
            startActivity(i);


        } /*else if (id == R.id.nav_bookh) {
            Intent i=new Intent(Main2Activity_nav.this,BookingHistory.class);
            startActivity(i);

        }*/else if (id == R.id.nav_bill) {

            Intent i=new Intent(Main2Activity_nav.this,ShowList.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
