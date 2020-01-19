package com.example.fixingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String location,password,phoneNumber,name,type,UID,imgID;


    public User(String location, String imgID,String password, String phoneNumber, String name, String type, String UID) {
        this.location = location;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.type = type;
        this.imgID=imgID;
        this.UID = UID;
    }
public User(){

}

    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
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

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    protected User(Parcel in) {
        location = in.readString();
        password = in.readString();
        phoneNumber = in.readString();
        name = in.readString();
        type = in.readString();
        UID = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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
        dest.writeString(type);
        dest.writeString(UID);
    }
}
