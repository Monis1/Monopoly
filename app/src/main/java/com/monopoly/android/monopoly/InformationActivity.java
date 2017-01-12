package com.monopoly.android.monopoly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Monis on 12/12/2016.
 */

public class InformationActivity extends AppCompatActivity {

    Button start;
    EditText pn1et,pn2et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        start=(Button)findViewById(R.id.startbutton);    //setting up start button
        pn1et=(EditText)findViewById(R.id.pn1editText);    //setting up edit text
        pn2et=(EditText)findViewById(R.id.pn2editText);     //setting up edit text
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(pn1et.getText().toString().compareTo("")==0||pn2et.getText().toString().compareTo("")==0||pn1et.getText().toString().length()>8||pn2et.getText().toString().length()>8){
                 Toast.makeText(InformationActivity.this,"Complete all fields first with at most 8 characters...",Toast.LENGTH_SHORT).show(); //Checking if the name field are empty or not and length is less than 8
             return;
             }
                Player P1,P2;
                 P1=new Player(pn1et.getText().toString(),"#FF0000");        //making p1 object with its name and color in hex
                 P2=new Player(pn2et.getText().toString(),"#0000FF");         //making p2 object with its name and color in hex
                MonopolyBoard board=new MonopolyBoard(32);        // making monopoly's board object
                Monopoly monopoly=new Monopoly(P1,P2,board);            // making monopoly object
                Intent i=new Intent(InformationActivity.this,MenuActivity.class);
                Bundle b1=new  Bundle();      //  making a bundle for passing monopoly object
                b1.putParcelable("M",monopoly);  //      loading up the parcel
                i.putExtras(b1);              //     Passing Monopoly Object to next activity
                startActivity(i);  // Calling menu activity
                finish();
            }
        });
    }
}
