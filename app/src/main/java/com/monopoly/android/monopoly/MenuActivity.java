package com.monopoly.android.monopoly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Monis on 12/12/2016.
 */

public class MenuActivity extends AppCompatActivity {

    Button selectPlayer1,selectPlayer2,endGame,printBoard;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       Bundle b = this.getIntent().getExtras();         //receiving the bundle
       final Monopoly monopoly = b.getParcelable("M");        //extracting the monopoly object using its key
        selectPlayer1=(Button)findViewById(R.id.selectplayer1);
        selectPlayer2=(Button)findViewById(R.id.selectplayer2);
        selectPlayer1.setText("Select Player 1: "+ monopoly.getP1().getName()+" to play");  // set the name
        selectPlayer2.setText("Select Player 2: "+monopoly.getP2().getName()+" to play");   //            of players in button text
        endGame=(Button)findViewById(R.id.endgame);
        printBoard=(Button)findViewById(R.id.printboard);
        printBoard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(MenuActivity.this,BoardActivity.class);
               Bundle b1=new Bundle();           //making bundle for passing board object
               b1.putParcelable("B",monopoly.getB()); //loading up parcel
               i.putExtras(b1);          // passing board object to board activity
               startActivity(i);         // calling the board activity
           }
       });

        selectPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuActivity.this,PlayerActivity.class);
                Bundle b1=new Bundle();           //making bundle for passing mobopoly object
                b1.putParcelable("M",monopoly); //loading up parcel
                i.putExtras(b1);          // passing monopoly object to player activity
                i.putExtra("player","1");  // player 1 calling
                startActivity(i);        //  calling the board activity
                finish();
            }
        });

        selectPlayer2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent i=new Intent(MenuActivity.this,PlayerActivity.class);
                Bundle b1=new Bundle();           //making bundle for passing monopoly object
                b1.putParcelable("M",monopoly); //loading up parcel
                i.putExtras(b1);          // passing monopoly object to player activity
                i.putExtra("player","2");  // player 2 calling
                startActivity(i);        //  calling the board activity
                finish();
            }
        });


        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuActivity.this,InformationActivity.class); // ending game
                startActivity(i); // going back to information activity
                finish();
            }
        });

    }
}
