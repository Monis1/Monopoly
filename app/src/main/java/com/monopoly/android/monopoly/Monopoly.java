package com.monopoly.android.monopoly;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Monis on 12/18/2016.
 */

public class Monopoly implements Parcelable {   // The main class of monopoly


    protected Monopoly(Parcel in) {
        P1 = in.readParcelable(Player.class.getClassLoader());
        P2 = in.readParcelable(Player.class.getClassLoader());
        B = in.readParcelable(MonopolyBoard.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(P1, flags);
        dest.writeParcelable(P2, flags);
        dest.writeParcelable(B, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Monopoly> CREATOR = new Creator<Monopoly>() {
        @Override
        public Monopoly createFromParcel(Parcel in) {
            return new Monopoly(in);
        }

        @Override
        public Monopoly[] newArray(int size) {
            return new Monopoly[size];
        }
    };

    public Player getP1() {
        return P1;
    }

    public void setP1(Player p1) {
        P1 = p1;
    }

    public Player getP2() {
        return P2;
    }

    public void setP2(Player p2) {
        P2 = p2;
    }

    public MonopolyBoard getB() {
        return B;
    }

    public void setB(MonopolyBoard b) {
        B = b;
    }

    private Player P1,P2;
    private MonopolyBoard B;

    public Monopoly(Player p1,Player p2,MonopolyBoard b)
    {
        P1=p1;
        P2=p2;
        B=b;
    }



}
