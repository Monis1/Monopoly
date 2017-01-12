package com.monopoly.android.monopoly;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Monis on 12/18/2016.
 */


public class MonopolyBoard implements Parcelable {   // Class for the monopoly board

    int places;
    private Cell[] board;


    protected MonopolyBoard(Parcel in) {
        places = in.readInt();
        board = in.createTypedArray(Cell.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(places);
        dest.writeTypedArray(board, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MonopolyBoard> CREATOR = new Creator<MonopolyBoard>() {
        @Override
        public MonopolyBoard createFromParcel(Parcel in) {
            return new MonopolyBoard(in);
        }

        @Override
        public MonopolyBoard[] newArray(int size) {
            return new MonopolyBoard[size];
        }
    };

    public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell[] board) {
        board = board;
    }


    public MonopolyBoard(int Places)
    {
        places=Places;
    board=new Cell[places];
        //Initialization of each cell of the board:
        // 0 means not accquired yet
        // -1 means cant be accquired
        // 1 means accquired by player 1
        // 2 means accquire by player  2
        board[0]=new Cell("Go","#000000","Gradient","(0,0)","#000000",false,0,200,0);                        //
        board[1]=new Cell("White House","#000000","#FFFFFF","(0,1)","#000000",false,0,300,200);               //
        board[2]=new Cell("Chicago Avenue","#000000","#FFFFFF","(0,2)","#000000",false,0,300,200);          //
        board[3]=new Cell("Texas Avenue","#000000","#FFFFFF","(0,3)","#000000",false,0,300,200);           //
        board[4]=new Cell("Utility","#000000","#FFFFFF","(0,4)","#000000",false,0,100,50);                //  Initilaizations
        board[5]=new Cell("College Avenue","#000000","#FFFFFF","(0,5)","#000000",false,0,300,200);          //
        board[6]=new Cell("Burger King","#000000","#FFFFFF","(0,6)","#000000",false,0,300,200);         //
        board[7]=new Cell("Nothing","#000000","#FFFFFF","(0,7)","#000000",false,-1,0,0);                //
        board[8]=new Cell("Holiday Inn Hotel","#000000","#FFFFFF","(0,8)","#000000",false,0,300,200);    //
        board[9]=new Cell("Go To Jail","#000000","#FFFFFF","(0,9)","#000000",false,0,300,200);          //
        board[10]=new Cell("Roll Again","#000000","#FFFFFF","(1,9)","#000000",false,-1,0,0);           //
        board[11]=new Cell("Blue Mosque","#000000","#FFFFFF","(2,9)","#000000",false,0,300,200);       //
        board[12]=new Cell("RailRoads","#000000","#FFFFFF","(3,9)","#000000",false,0,100,50);          //
        board[13]=new Cell("Luxury Tax","#000000","#FFFFFF","(4,9)","#000000",false,-1,0,200);        //
        board[14]=new Cell("City Park","#000000","#FFFFFF","(5,9)","#000000",false,-1,0,20);           //
        board[15]=new Cell("Nothing","#000000","#FFFFFF","(6,9)","#000000",false,-1,0,0);             //
        board[16]=new Cell("Free Parking","#000000","#FFFFFF","(7,9)","#000000",false,-1,0,0);        //
        board[17]=new Cell("New York Avenue","#000000","#FFFFFF","(7,8)","#000000",false,0,300,200);  //
        board[18]=new Cell("Colorado Avenue","#000000","#FFFFFF","(7,7)","#000000",false,0,300,200); //
        board[19]=new Cell("Income Tax","#000000","#FFFFFF","(7,6)","#000000",false,-1,0,100);        //
        board[20]=new Cell("Marvin Garden","#000000","#FFFFFF","(7,5)","#000000",false,-1,0,20);      //
        board[21]=new Cell("Nothing","#000000","#FFFFFF","(7,4)","#000000",false,-1,0,0);             //
        board[22]=new Cell("Red House","#000000","#FFFFFF","(7,3)","#000000",false,0,300,200);        //
        board[23]=new Cell("Blue House","#000000","#FFFFFF","(7,2)","#000000",false,0,300,200);       //
        board[24]=new Cell("Hilton Hotel","#000000","#FFFFFF","(7,1)","#000000",false,0,300,200);      //
        board[25]=new Cell("In Jail","#000000","#FFFFFF","(7,0)","#000000",false,-1,0,0);              //
        board[26]=new Cell("Nothing","#000000","#FFFFFF","(6,0)","#000000",false,-1,0,0);             //
        board[27]=new Cell("Sheraton Hotel","#000000","#FFFFFF","(5,0)","#000000",false,0,300,200);     //
        board[28]=new Cell("Yellow House","#000000","#FFFFFF","(4,0)","#000000",false,0,300,200);       //
        board[29]=new Cell("Washington Avenue","#000000","#FFFFFF","(3,0)","#000000",false,0,300,200);  //
        board[30]=new Cell("Roll Again","#000000","#FFFFFF","(2,0)","#000000",false,-1,0,0);           //
        board[31]=new Cell("Mall of Arabia","#000000","#FFFFFF","(1,0)","#000000",false,0,300,200);     //
    //end initializations
    }
}