package com.example.fixingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Worker implements Parcelable {
    private String imageID,location,password,phoneNumber,name,profession,type,UID;


    public Worker(String imageID,String location, String password, String phoneNumber, String name, String profession, String type, String UID) {
        this.location = location;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.profession = profession;
        this.type = type;
        this.UID = UID;
        this.imageID=imageID;
    }

    public Worker(){}


    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public static Creator<Worker> getCREATOR() {
        return CREATOR;
    }

    protected Worker(Parcel in) {
        location = in.readString();
        password = in.readString();
        phoneNumber = in.readString();
        name = in.readString();
        profession = in.readString();
        type = in.readString();
        UID = in.readString();
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(password);
        dest.writeString(phoneNumber);
        dest.writeString(name);
        dest.writeString(profession);
        dest.writeString(type);
        dest.writeString(UID);
    }
}
