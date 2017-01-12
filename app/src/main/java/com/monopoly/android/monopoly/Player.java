package com.monopoly.android.monopoly;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Monis on 12/12/2016.
 */

public class Player implements Parcelable{

    private String name,location; //
    private int nlocation;        // Information about player
    private double balance;        //

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public static Creator<Player> getCREATOR() {
        return CREATOR;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    private int moves;
    private String Color;             //

    public Player(String name,String color)
    {
        this.name=name;               //  Setting
        location="Go";              //           Default
        nlocation=0;                //                  Parameters
        balance=1500;                 //
        Color=color;                  //
        moves=0;
    }

    protected Player(Parcel in) {
        name = in.readString();
        location = in.readString();
        nlocation = in.readInt();
        balance = in.readDouble();
        Color=in.readString();
        moves=in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public double getBalance() {                         // Getter and setters
        return balance;                                  // below
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNlocation() {
        return nlocation;
    }

    public void setNlocation(int nlocation) {
        this.nlocation = nlocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeInt(nlocation);
        parcel.writeDouble(balance);
        parcel.writeString(Color);
        parcel.writeInt(moves);
    }
}
