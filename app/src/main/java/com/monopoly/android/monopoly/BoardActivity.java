package com.monopoly.android.monopoly;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Monis on 12/11/2016.
 */

public class BoardActivity extends AppCompatActivity {

    TextView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30,c31,c32;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Bundle b = this.getIntent().getExtras();         //receiving the bundle
        MonopolyBoard board = b.getParcelable("B");        //extracting the board object using its key
        // begin initializing cell textViews
       c1=(TextView)findViewById(R.id.textView1);
        printcell(c1,board.getBoard()[0]);

        c2=(TextView)findViewById(R.id.textView2);
        printcell(c2,board.getBoard()[1]);

        c3=(TextView)findViewById(R.id.textView3);
        printcell(c3,board.getBoard()[2]);

        c4=(TextView)findViewById(R.id.textView4);
        printcell(c4,board.getBoard()[3]);

        c5=(TextView)findViewById(R.id.textView5);
        printcell(c5,board.getBoard()[4]);

        c6=(TextView)findViewById(R.id.textView6);
        printcell(c6,board.getBoard()[5]);

        c7=(TextView)findViewById(R.id.textView7);
        printcell(c7,board.getBoard()[6]);

        c8=(TextView)findViewById(R.id.textView8);
        printcell(c8,board.getBoard()[7]);

        c9=(TextView)findViewById(R.id.textView9);
        printcell(c9,board.getBoard()[8]);

        c10=(TextView)findViewById(R.id.textView10);
        printcell(c10,board.getBoard()[9]);

        c12=(TextView)findViewById(R.id.textView12);
        printcell(c12,board.getBoard()[10]);

        c14=(TextView)findViewById(R.id.textView14);
        printcell(c14,board.getBoard()[11]);

        c16=(TextView)findViewById(R.id.textView16);
        printcell(c16,board.getBoard()[12]);

        c18=(TextView)findViewById(R.id.textView18);
        printcell(c18,board.getBoard()[13]);

        c20=(TextView)findViewById(R.id.textView20);
        printcell(c20,board.getBoard()[14]);

        c22=(TextView)findViewById(R.id.textView22);
        printcell(c22,board.getBoard()[15]);

        c32=(TextView)findViewById(R.id.textView32);
        printcell(c32,board.getBoard()[16]);

        c31=(TextView)findViewById(R.id.textView31);
        printcell(c31,board.getBoard()[17]);

        c30=(TextView)findViewById(R.id.textView30);
        printcell(c30,board.getBoard()[18]);

        c29=(TextView)findViewById(R.id.textView29);
        printcell(c29,board.getBoard()[19]);

        c28=(TextView)findViewById(R.id.textView28);
        printcell(c28,board.getBoard()[20]);

        c27=(TextView)findViewById(R.id.textView27);
        printcell(c27,board.getBoard()[21]);

        c26=(TextView)findViewById(R.id.textView26);
        printcell(c26,board.getBoard()[22]);

        c25=(TextView)findViewById(R.id.textView25);
        printcell(c25,board.getBoard()[23]);

        c24=(TextView)findViewById(R.id.textView24);
        printcell(c24,board.getBoard()[24]);

        c23=(TextView)findViewById(R.id.textView23);
        printcell(c23,board.getBoard()[25]);

        c21=(TextView)findViewById(R.id.textView21);
        printcell(c21,board.getBoard()[26]);

        c19=(TextView)findViewById(R.id.textView19);
        printcell(c19,board.getBoard()[27]);

        c17=(TextView)findViewById(R.id.textView17);
        printcell(c17,board.getBoard()[28]);

        c15=(TextView)findViewById(R.id.textView15);
        printcell(c15,board.getBoard()[29]);

        c13=(TextView)findViewById(R.id.textView13);
        printcell(c13,board.getBoard()[30]);

        c11=(TextView)findViewById(R.id.textView11);
        printcell(c11,board.getBoard()[31]);
        // end initializing cell textViews
    }

    public void printcell(TextView T,Cell C)      //This function specifies colors and border of the cell
    {
     if(C.getBackcolor().compareTo("Gradient")==0)
     {
         GradientDrawable gd = new GradientDrawable(
                 GradientDrawable.Orientation.TOP_BOTTOM,
                 new int[] {Color.BLUE, Color.RED});
         gd.setStroke(2, Color.parseColor(C.getBorderColor()));
         T.setBackground(gd);
     }
        else {
         GradientDrawable gbg = (GradientDrawable) T.getBackground();
         gbg.setColor(Color.parseColor(C.getBackcolor()));
         gbg.setStroke(2, Color.parseColor(C.getBorderColor()));
     }
        T.setTextColor(Color.parseColor(C.getTextcolor()));
    }
}
