package com.example.ehr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Ptreatment extends AppCompatActivity {
public static String code="";
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptreatment);
        listView=findViewById(R.id.listview);
        Firebase.setAndroidContext(this);
        Firebase fb=new Firebase(Mconfig.fburl+"/Treatment/"+code);

        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int cnt = (int) dataSnapshot.getChildrenCount();
                int flg = 0;
                if(cnt<1){
                    Toast.makeText(Ptreatment.this, "Data Not Available", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(Ptreatment.this,"Data Not Available",Toast.LENGTH_LONG).show();
return;
                }
                String []symptom=new String[cnt];
                String []disease=new String[cnt];
                ArrayList<String[]> medlist=new ArrayList<>();

                ArrayList<String[]>qty=new ArrayList<>();
                     int i=0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Treatmentclass p = data.getValue(Treatmentclass.class);

                    symptom[i]=p.getSymptom().toString();
                    disease[i]=p.getDisease().toString();
                    medlist.add(p.getMedicine());
                    qty.add(p.getQty());
                     i++;

                    }

                PatientHistory customList = new PatientHistory(Ptreatment.this,symptom,disease,medlist,qty);
                listView.setAdapter(customList);


            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
