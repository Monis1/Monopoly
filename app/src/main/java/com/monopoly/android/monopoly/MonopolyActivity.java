package com.monopoly.android.monopoly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Monis on 12/20/2016.
 */

public class MonopolyActivity extends AppCompatActivity {

    TextView cname,cloc,coname,caction,number,number1;
    Button move,movebuy;
    boolean rollAgain=false,ispurchased=false;
    int cindex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monopoly);
        Bundle b = this.getIntent().getExtras();         //receiving the bundle
        final Monopoly monopoly = b.getParcelable("M");        //extracting the monopoly object using its key
        final String player=b.getString("player");             // extracting the player string using its key

        cname=(TextView)findViewById(R.id.cellnametv);  //
        cloc=(TextView)findViewById(R.id.celloctv);     //    setting
        coname=(TextView)findViewById(R.id.ownernametv); //            up
        caction=(TextView)findViewById(R.id.actiontv);  //       layout
        number=(TextView)findViewById(R.id.numbertv);  //               elements
        move=(Button)findViewById(R.id.movebutton);   //
        movebuy=(Button)findViewById(R.id.movebuybutton);   //
        number1=(TextView)findViewById(R.id.numbertv1); //
        Random rand = new Random();      // random number generator class
        int  n = rand.nextInt(6) + 1;      // generating number in the range 1-
        int  n1 = rand.nextInt(6) + 1;      // generating number in the range 1-6

        number.setText(String.valueOf(n));
        number1.setText(String.valueOf(n1));
        //cindex it will hold the index of next cell
        if(player.compareTo("1")==0)  // checking if player 1 is accessing current activity
        {
            cindex=n+n1+monopoly.getP1().getNlocation();                              //calculating index of next cell
            if(cindex>=32){
                cindex=cindex-monopoly.getB().getBoard().length;
                monopoly.getP1().setBalance(monopoly.getP1().getBalance()+200);
            }
            cname.setText(monopoly.getB().getBoard()[cindex].getName());
            // setting name of cell
            cloc.setText(monopoly.getB().getBoard()[cindex].getLocation());      //  setting location of cell
            if(monopoly.getB().getBoard()[cindex].isAccquired())                // checking if the cell is owned by someone or not
            {
                if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==1)  //checking if its accquired by player 1
                {
                    coname.setText(monopoly.getP1().getName());  //setting owner name to player 1
                }
                else // its accquired by player 1
                {
                    coname.setText(monopoly.getP2().getName());   //setting owner name to player 2
                }
            }
            else   // cell is owned by none
            {
                coname.setText("none");
            }
        }
        else  // player 2 is accessing current activity
        {
            //same as of player 1
            cindex=n+n1+monopoly.getP2().getNlocation();
            if(cindex>=32){
                cindex=cindex-monopoly.getB().getBoard().length;
                monopoly.getP2().setBalance(monopoly.getP2().getBalance()+200);
            }
            cname.setText(monopoly.getB().getBoard()[cindex].getName());
            cloc.setText(monopoly.getB().getBoard()[cindex].getLocation());
            if(monopoly.getB().getBoard()[cindex].isAccquired())
            {
                if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==1)
                {
                    coname.setText(monopoly.getP1().getName());
                }
                else
                {
                    coname.setText(monopoly.getP2().getName());
                }
            }
            else
            {
                coname.setText("none");
            }
            // end here
        }
        // changing colors for moving player 1
        if(player.compareTo("1")==0)
        {
            if(monopoly.getB().getBoard()[monopoly.getP1().getNlocation()].getBackcolor().compareTo("Gradient")==0)// checking if the cell is alredy gradient
            {
                monopoly.getB().getBoard()[monopoly.getP1().getNlocation()].setBackcolor("#0000FF"); // setting color to blue
            }
            else
            {
                monopoly.getB().getBoard()[monopoly.getP1().getNlocation()].setBackcolor("#FFFFFF"); // setting color to white
            }
            if(monopoly.getB().getBoard()[cindex].getBackcolor().compareTo("#FFFFFF")==0) // check if the background is white
                monopoly.getB().getBoard()[cindex].setBackcolor("#FF0000"); // setting color to red
            else
                monopoly.getB().getBoard()[cindex].setBackcolor("Gradient"); // setting color to gradient(red and blue)
            monopoly.getP1().setLocation(monopoly.getB().getBoard()[cindex].getName()); // set current location of player
            monopoly.getP1().setNlocation(cindex); // set location index of player
            monopoly.getP1().setMoves(monopoly.getP1().getMoves()+1); // add a single move
        }
        // changing colors for moving player 2
        else // same as of player 1
        {
            if(monopoly.getB().getBoard()[monopoly.getP2().getNlocation()].getBackcolor().compareTo("Gradient")==0)
            {
                monopoly.getB().getBoard()[monopoly.getP2().getNlocation()].setBackcolor("#FF0000");
            }
            else
            {
                monopoly.getB().getBoard()[monopoly.getP2().getNlocation()].setBackcolor("#FFFFFF");
            }
            if(monopoly.getB().getBoard()[cindex].getBackcolor().compareTo("#FFFFFF")==0)
                monopoly.getB().getBoard()[cindex].setBackcolor("#0000FF");
            else
                monopoly.getB().getBoard()[cindex].setBackcolor("Gradient");
            monopoly.getP2().setLocation(monopoly.getB().getBoard()[cindex].getName());
            monopoly.getP2().setNlocation(cindex);
            monopoly.getP2().setMoves(monopoly.getP1().getMoves()+1);
        }
//end changing colors


        if(monopoly.getB().getBoard()[cindex].getName().compareTo("Utility")==0||monopoly.getB().getBoard()[cindex].getName().compareTo("RailRoads")==0) // cell is utility or railroad
        {
            if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==0) // if its accquired by no one
            {
                if(player.compareTo("1")==0)  // if player 1
                {
                    if (monopoly.getP1().getBalance() - monopoly.getB().getBoard()[cindex].getPrice() >= 0) // checking whther player can buy utility or railroad
                    {
                        caction.setText("You can buy this " + monopoly.getB().getBoard()[cindex].getName() + " in $" + monopoly.getB().getBoard()[cindex].getPrice());
                        monopoly.getP1().setBalance(monopoly.getP1().getBalance()-monopoly.getB().getBoard()[cindex].getPrice()); // subtracting price of utility or railroad
                        monopoly.getB().getBoard()[cindex].setAccquired(true); // cell is accquired
                        monopoly.getB().getBoard()[cindex].setAccquiredBy(1);  // cell is accquired by player 1
                        monopoly.getB().getBoard()[cindex].setBorderColor("#FF0000"); // seeting border color of cell to player's color
                    ispurchased=true;
                    }
                    else // not enough money to buy
                    {
                        caction.setText("You don't have enough money to buy this " + monopoly.getB().getBoard()[cindex].getName() + ", just move forward");
                    }
                }
                else // same as for player 2
                {
                    if (monopoly.getP2().getBalance() - monopoly.getB().getBoard()[cindex].getPrice() >= 0)
                    {
                        caction.setText("You can buy this " + monopoly.getB().getBoard()[cindex].getName() + " in $" + monopoly.getB().getBoard()[cindex].getPrice());
                        monopoly.getP2().setBalance(monopoly.getP2().getBalance()-monopoly.getB().getBoard()[cindex].getPrice());
                        monopoly.getB().getBoard()[cindex].setAccquired(true);
                        monopoly.getB().getBoard()[cindex].setAccquiredBy(2);
                        monopoly.getB().getBoard()[cindex].setBorderColor("#0000FF");
                        ispurchased=true;
                    }
                    else
                    {
                        caction.setText("You don't have enough money to buy this " + monopoly.getB().getBoard()[cindex].getName() + ", just move forward");
                    }
                }
//end
            }
            else if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==1) // if cell is railroad or utility is accquired by player 1
            {
                if(player.compareTo("1")==0)
                {
                    caction.setText("This " +monopoly.getB().getBoard()[cindex].getName()+" is owned by you, just move forward");
                }
                else
                {
                    caction.setText("This " + monopoly.getB().getBoard()[cindex].getName() + " is owned by " + monopoly.getP1().getName() + ", you have to give $" + monopoly.getB().getBoard()[cindex].getRent()+" as rent");
                    monopoly.getP2().setBalance(monopoly.getP2().getBalance()-monopoly.getB().getBoard()[cindex].getRent()); //subtracting rent from player 2
                    monopoly.getP1().setBalance(monopoly.getP1().getBalance()+monopoly.getB().getBoard()[cindex].getRent()); //adding rent to player 1
                }
            }
            else if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==2) // same as of player 1
            {
                if(player.compareTo("2")==0)
                {
                    caction.setText("This " +monopoly.getB().getBoard()[cindex].getName()+" is owned by you, just move forward");
                }
                else
                {
                    caction.setText("This " + monopoly.getB().getBoard()[cindex].getName() + " is owned by " + monopoly.getP2().getName() + ", you have to give $" + monopoly.getB().getBoard()[cindex].getRent()+" as rent");
                    monopoly.getP1().setBalance(monopoly.getP1().getBalance()-monopoly.getB().getBoard()[cindex].getRent());
                    monopoly.getP2().setBalance(monopoly.getP2().getBalance()+monopoly.getB().getBoard()[cindex].getRent());
                }
                //end
            }
        }

        else if(monopoly.getB().getBoard()[cindex].getName().compareTo("Luxury Tax")==0||monopoly.getB().getBoard()[cindex].getName().compareTo("Income Tax")==0) // if cell is tax(income or luxury)
        {
            caction.setText("You have to give $"+monopoly.getB().getBoard()[cindex].getRent()+" as "+monopoly.getB().getBoard()[cindex].getName());
            if(player.compareTo("1")==0)
                monopoly.getP1().setBalance(monopoly.getP1().getBalance()-monopoly.getB().getBoard()[cindex].getRent()); //subtracting tax from player 1
            else
                monopoly.getP2().setBalance(monopoly.getP2().getBalance()-monopoly.getB().getBoard()[cindex].getRent()); //subtracting tax from player 2
        }

        else if(monopoly.getB().getBoard()[cindex].getName().compareTo("Nothing")==0||monopoly.getB().getBoard()[cindex].getName().compareTo("Free Parking")==0||monopoly.getB().getBoard()[cindex].getName().compareTo("In Jail")==0) //if cell is nothing or free parking
        {
            caction.setText("You are on "+monopoly.getB().getBoard()[cindex].getName()+", just move forward"); // no action on nothing or free parking or in jail
        }

        else if(monopoly.getB().getBoard()[cindex].getName().compareTo("Roll Again")==0) // if cell is roll again
        {
            caction.setText("You have another turn, Roll Again");
            rollAgain=true; // inform the program that the player has to roll again
        }

        else if(monopoly.getB().getBoard()[cindex].getName().contains("Park")||monopoly.getB().getBoard()[cindex].getName().contains("Garden")) //if cell is park or garden
        {
            caction.setText("You have to give $"+monopoly.getB().getBoard()[cindex].getRent()+" for renting "+monopoly.getB().getBoard()[cindex].getName());
            if(player.compareTo("1")==0)
                monopoly.getP1().setBalance(monopoly.getP1().getBalance()-monopoly.getB().getBoard()[cindex].getRent()); // subtracting rent from player 1
            else
                monopoly.getP2().setBalance(monopoly.getP2().getBalance()-monopoly.getB().getBoard()[cindex].getRent()); // subtracting rent from player 2
        }

        else if(monopoly.getB().getBoard()[cindex].getName().compareTo("Go To Jail")==0)
        {
            caction.setText("You are going to Jail");
            // changing colors for moving player 1
            if(player.compareTo("1")==0)
            {
                if(monopoly.getB().getBoard()[monopoly.getP1().getNlocation()].getBackcolor().compareTo("Gradient")==0)
                {
                    monopoly.getB().getBoard()[monopoly.getP1().getNlocation()].setBackcolor("#0000FF");
                }
                else
                {
                    monopoly.getB().getBoard()[monopoly.getP1().getNlocation()].setBackcolor("#FFFFFF");
                }
                if(monopoly.getB().getBoard()[25].getBackcolor().compareTo("#FFFFFF")==0)
                    monopoly.getB().getBoard()[25].setBackcolor("#FF0000");
                else
                    monopoly.getB().getBoard()[25].setBackcolor("Gradient");
                monopoly.getP1().setLocation(monopoly.getB().getBoard()[25].getName());
                monopoly.getP1().setNlocation(25);
                monopoly.getP1().setMoves(monopoly.getP1().getMoves()+16);
            }
            // changing colors for moving player 2
            else
            {
                if(monopoly.getB().getBoard()[monopoly.getP2().getNlocation()].getBackcolor().compareTo("Gradient")==0)
                {
                    monopoly.getB().getBoard()[monopoly.getP2().getNlocation()].setBackcolor("#FF0000");
                }
                else
                {
                    monopoly.getB().getBoard()[monopoly.getP2().getNlocation()].setBackcolor("#FFFFFF");
                }
                if(monopoly.getB().getBoard()[25].getBackcolor().compareTo("#FFFFFF")==0)
                    monopoly.getB().getBoard()[25].setBackcolor("#0000FF");
                else
                    monopoly.getB().getBoard()[25].setBackcolor("Gradient");
                monopoly.getP2().setLocation(monopoly.getB().getBoard()[25].getName());
                monopoly.getP2().setNlocation(25);
                monopoly.getP2().setMoves(monopoly.getP1().getMoves()+16);
            }
//end changing colors
        }

        else  // if the current cell is an property
        {
            if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==0)   // if accquired by no one
            {
                if(player.compareTo("1")==0) {
                    if (monopoly.getP1().getBalance() - monopoly.getB().getBoard()[cindex].getPrice() >= 0) // can player buy this or not
                    {
                        caction.setText("You can buy " + monopoly.getB().getBoard()[cindex].getName() + " in $" + monopoly.getB().getBoard()[cindex].getPrice());
                        monopoly.getP1().setBalance(monopoly.getP1().getBalance()-monopoly.getB().getBoard()[cindex].getPrice()); // subtracting property price from player's account
                        monopoly.getB().getBoard()[cindex].setAccquired(true); // cell is accquired now
                        monopoly.getB().getBoard()[cindex].setAccquiredBy(1);   // cell is accquired by player 1
                        monopoly.getB().getBoard()[cindex].setBorderColor("#FF0000");   // set cell border color to player's color
                        ispurchased=true;
                    }
                    else // cant buy property
                    {
                        caction.setText("You don't have enough money to buy " + monopoly.getB().getBoard()[cindex].getName() + ", just move forward");
                    }
                }
                else // same for player 2
                {
                    if (monopoly.getP2().getBalance() - monopoly.getB().getBoard()[cindex].getPrice() >= 0)
                    {
                        caction.setText("You can buy " + monopoly.getB().getBoard()[cindex].getName() + " in $" + monopoly.getB().getBoard()[cindex].getPrice());
                        monopoly.getP2().setBalance(monopoly.getP2().getBalance()-monopoly.getB().getBoard()[cindex].getPrice());
                        monopoly.getB().getBoard()[cindex].setAccquired(true);
                        monopoly.getB().getBoard()[cindex].setAccquiredBy(2);
                        monopoly.getB().getBoard()[cindex].setBorderColor("#0000FF");
                        ispurchased=true;
                    }
                    else
                    {
                        caction.setText("You don't have enough money to buy " + monopoly.getB().getBoard()[cindex].getName() + ", just move forward");
                    }
                }

            }
            else if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==1) // if accquired by player 1
            {
                if(player.compareTo("1")==0)
                {
                    caction.setText(monopoly.getB().getBoard()[cindex].getName()+" is owned by you, just move forward");
                }
                else
                {
                    caction.setText(monopoly.getB().getBoard()[cindex].getName() + " is owned by " + monopoly.getP1().getName() + ", you have to give $" + monopoly.getB().getBoard()[cindex].getRent()+" as rent");
                    monopoly.getP2().setBalance(monopoly.getP2().getBalance()-monopoly.getB().getBoard()[cindex].getRent()); // subtracting rent cost from player 2
                    monopoly.getP1().setBalance(monopoly.getP1().getBalance()+monopoly.getB().getBoard()[cindex].getRent());  // adding renting cost in player 1
                }
            }
            else if(monopoly.getB().getBoard()[cindex].getAccquiredBy()==2) //same as for player 2
            {
                if(player.compareTo("2")==0)
                {
                    caction.setText(monopoly.getB().getBoard()[cindex].getName()+" is owned by you, just move forward");
                }
                else
                {
                    caction.setText(monopoly.getB().getBoard()[cindex].getName() + " is owned by " + monopoly.getP2().getName() + ", you have to give $" + monopoly.getB().getBoard()[cindex].getRent()+" as rent");
                    monopoly.getP1().setBalance(monopoly.getP1().getBalance()-monopoly.getB().getBoard()[cindex].getRent());
                    monopoly.getP2().setBalance(monopoly.getP2().getBalance()+monopoly.getB().getBoard()[cindex].getRent());
                }
            }
        }


        move.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //if purchasing
if(ispurchased)
{
    if(player.compareTo("1")==0)
    {
        monopoly.getP1().setBalance(monopoly.getP1().getBalance()+monopoly.getB().getBoard()[cindex].getPrice());
    }
    else
    {
        monopoly.getP2().setBalance(monopoly.getP2().getBalance()+monopoly.getB().getBoard()[cindex].getPrice());
    }
    monopoly.getB().getBoard()[cindex].setAccquired(false);
    monopoly.getB().getBoard()[cindex].setAccquiredBy(0);
    monopoly.getB().getBoard()[cindex].setBorderColor("#000000");
}
                if(rollAgain)
                {
                    if(player.compareTo("1")==0)
                    {
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","1");  // player 1 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                    else{
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","2");  // player 2 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                }
                else
                {
                    if(player.compareTo("1")==0)
                    {
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","2");  // player 2 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                    else{
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","1");  // player 1 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                }
            }
        });

        movebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rollAgain)
                {
                    if(player.compareTo("1")==0)
                    {
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","1");  // player 1 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                    else{
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","2");  // player 2 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                }
                else
                {
                    if(player.compareTo("1")==0)
                    {
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","2");  // player 2 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                    else{
                        Intent i=new Intent(MonopolyActivity.this,PlayerActivity.class);
                        Bundle b1=new Bundle();           //making bundle for passing monopoly object
                        b1.putParcelable("M",monopoly); //loading up parcel
                        i.putExtras(b1);          // passing monopoly object to player activity
                        i.putExtra("player","1");  // player 1 calling
                        startActivity(i);        //  calling the board activity
                        finish();
                    }
                }
            }
        });
    }
}