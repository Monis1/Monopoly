package com.monopoly.android.monopoly;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Monis on 12/19/2016.
 */

public class PlayerActivity extends AppCompatActivity {

    TextView pname,ploc,pmoney,pcolor;
    Button pprintboard,pendgame,prolldice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Bundle b = this.getIntent().getExtras();         //receiving the bundle
        final Monopoly monopoly = b.getParcelable("M");        //extracting the monopoly object using its key
        final String player=b.getString("player");             // extracting the player string using its key
        pname=(TextView)findViewById(R.id.nametv);                //
        ploc=(TextView)findViewById(R.id.loctv);                 //     Setting
        pmoney=(TextView)findViewById(R.id.moneytv);             //            up
        pcolor=(TextView)findViewById(R.id.colortv);             //        layout
        pprintboard=(Button)findViewById(R.id.printboardbuttonp); //             elements
        pendgame=(Button)findViewById(R.id.endGamebuttonp);       //                     variables
        prolldice=(Button)findViewById(R.id.rolldicebuttonp);     //
        // begining setting layout values
        if(player.compareTo("1")==0)        // if player 1 is accessing
        {
            pname.setText(monopoly.getP1().getName());
            pcolor.setTextColor(Color.parseColor(monopoly.getP1().getColor()));
            pcolor.setBackgroundColor(Color.parseColor(monopoly.getP1().getColor()));
            ploc.setText(monopoly.getP1().getLocation());
            pmoney.setText(String.valueOf(monopoly.getP1().getBalance()));
        }
        else             //  player 2 is accessing
        {
            pname.setText(monopoly.getP2().getName());
            pcolor.setTextColor(Color.parseColor(monopoly.getP2().getColor()));
            pcolor.setBackgroundColor(Color.parseColor(monopoly.getP2().getColor()));
            ploc.setText(monopoly.getP2().getLocation());
            pmoney.setText(String.valueOf(monopoly.getP2().getBalance()));
        }
        // ending setting layout values
      pendgame.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
           Intent i=new Intent(PlayerActivity.this,InformationActivity.class);
              startActivity(i);             // ending game, Calling up the info activity
              finish();
          }
      });
        pprintboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PlayerActivity.this,BoardActivity.class);
                Bundle b1=new Bundle();           //making bundle for passing board object
                b1.putParcelable("B",monopoly.getB()); //loading up parcel
                i.putExtras(b1);          // passing board object to board activity
                startActivity(i);         // calling the board activity
            }
        });

        prolldice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PlayerActivity.this,MonopolyActivity.class);
                Bundle b1=new Bundle();           //making bundle for passing monopoly object
                b1.putParcelable("M",monopoly); //loading up parcel
                i.putExtras(b1);          // passing monopoly object to player activity
                i.putExtra("player",player);  // player 1 calling
                startActivity(i);        //  calling the board activity
                finish();
            }
        });
    }
}