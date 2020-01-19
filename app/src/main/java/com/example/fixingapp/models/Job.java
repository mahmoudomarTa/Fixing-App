package com.example.fixingapp.models;

import java.util.ArrayList;

public class Job {
    private String jobId , imgCustomer,customerName, jobDescription;
    private double price;
    private  int negotiationCount,likesCount;
    private long time;
    private ArrayList<String> categories;


    public Job(){}
    public Job(String jobId, String imgCustomer, String customerName, String jobDescription, double price, int negotiationCount, int likesCount, long time, ArrayList<String> categories) {
        this.jobId = jobId;
        this.imgCustomer = imgCustomer;
        this.customerName = customerName;
        this.jobDescription = jobDescription;
        this.price = price;
        this.negotiationCount = negotiationCount;
        this.likesCount = likesCount;
        this.time = time;
        this.categories = categories;
    }



    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
//    private ArrayList<Worker> workerLike;
//    private ArrayList<Worker> workerNegotiate;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getImgCustomer() {
        return imgCustomer;
    }

    public void setImgCustomer(String imgCustomer) {
        this.imgCustomer = imgCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNegotiationCount() {
        return negotiationCount;
    }

    public void setNegotiationCount(int negotiationCount) {
        this.negotiationCount = negotiationCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}
