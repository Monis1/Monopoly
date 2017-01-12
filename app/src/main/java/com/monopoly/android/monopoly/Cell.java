package com.monopoly.android.monopoly;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Monis on 12/18/2016.
 */

public class Cell implements Parcelable{  // Class for a single cell of the board

    protected Cell(Parcel in) {
        name = in.readString();
        textcolor = in.readString();
        backcolor = in.readString();
        location = in.readString();
        borderColor = in.readString();
        isAccquired = in.readByte() != 0;
        accquiredBy = in.readInt();
        price = in.readDouble();
        rent = in.readDouble();
    }

    public static final Creator<Cell> CREATOR = new Creator<Cell>() {
        @Override
        public Cell createFromParcel(Parcel in) {
            return new Cell(in);
        }

        @Override
        public Cell[] newArray(int size) {
            return new Cell[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name,textcolor,backcolor,location,borderColor;
    private boolean isAccquired;
    private int accquiredBy;
    private double price;

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    private double rent;

    public Cell(String Name,String tc,String bc,String loc,String borc,boolean isAq,int aqBy,double Price,double Rent)
    {
        name=Name;
        textcolor=tc;
        backcolor=bc;
        location=loc;
        borderColor=borc;
        isAccquired=isAq;
        accquiredBy=aqBy;
        price=Price;
        rent=Rent;
    }

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }

    public String getBackcolor() {
        return backcolor;
    }

    public void setBackcolor(String backcolor) {
        this.backcolor = backcolor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isAccquired() {
        return isAccquired;
    }

    public void setAccquired(boolean accquired) {
        isAccquired = accquired;
    }

    public int getAccquiredBy() {
        return accquiredBy;
    }

    public void setAccquiredBy(int accquiredBy) {
        this.accquiredBy = accquiredBy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(textcolor);
        parcel.writeString(backcolor);
        parcel.writeString(location);
        parcel.writeString(borderColor);
        parcel.writeByte((byte) (isAccquired ? 1 : 0));
        parcel.writeInt(accquiredBy);
        parcel.writeDouble(price);
        parcel.writeDouble(rent);
    }
}