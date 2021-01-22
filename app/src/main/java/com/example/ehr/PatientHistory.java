package com.example.ehr;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.*;
import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class PatientHistory  extends ArrayAdapter<String> {
    String []symptom;
    String []disease;
    ArrayList<String[]>medlist;

    ArrayList<String[]>qtylist;

    private Activity context;

   int count=0;
    public PatientHistory(Activity context, String[] symptom, String[] disease, ArrayList<String[]> medicine, ArrayList<String[]>  qtylist) {
        super(context,R.layout.patient_hist,symptom);
        this.context =context;
        this.symptom=symptom;
        this.disease = disease;
        this.medlist = medicine;
        this.qtylist=qtylist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.patient_hist,null,true);

        TextView txtsymtom=(TextView)listViewItem.findViewById(R.id.txtsymtom);
        TextView txtdis=(TextView)listViewItem.findViewById(R.id.txtdis);


        txtsymtom.setText(symptom[position]);

        txtdis.setText(disease[position]);

        LinearLayout ll = (LinearLayout)listViewItem.findViewById(R.id.linear1);

       String []med= medlist.get(position);
        String []qty=qtylist.get(position);
       for(int i=0;i<med.length;i++) {
           LinearLayout lh = new LinearLayout(getContext());
           lh.setOrientation(LinearLayout.HORIZONTAL);
           LinearLayout.LayoutParams linearLayoutSubParentParams =
                   new LinearLayout.LayoutParams(
                           LinearLayout.LayoutParams.MATCH_PARENT,
                           LinearLayout.LayoutParams.WRAP_CONTENT, 100f);
           TextView et = new TextView(getContext());

           LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           // p.gravity= Gravity.CENTER_HORIZONTAL;
           //p.gravity=TEXT_ALIGNMENT_CENTER;

           et.setLayoutParams(p);


           et.setText(med[i]);
           et.setTextSize(15f);
           et.setEms(10);
           et.setTypeface(null, Typeface.BOLD);
           et.setPadding(10,0,0,0);

           et.setId(count);


           TextView et2 = new TextView(getContext());
           et2.setLayoutParams(p);
           et2.setText(qty[i]);
           et2.setTextSize(15f);
           et2.setEms(20);
           et2.setPadding(30,0,0,0);
           et2.setTypeface(null, Typeface.BOLD);
           et2.setGravity(Gravity.CENTER_HORIZONTAL);
          // et2.setLayoutParams(p);
           et2.setId(count + 1);

           ll.addView(lh);
           lh.addView(et);
           lh.addView(et2);
       }



        return listViewItem;
        //  return super.getView(position, convertView, parent);
    }

}
