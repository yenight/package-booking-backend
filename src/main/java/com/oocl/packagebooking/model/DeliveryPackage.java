package com.oocl.packagebooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DeliveryPackage {

    @Id
    @GeneratedValue
    private long id;

    private String CustomerName;
    private String phoneNumber;
    private int status;
    private Date bookTime;

    public DeliveryPackage() {
    }

    public DeliveryPackage(String customerName, String phoneNumber, int status, Date bookTime) {
        CustomerName = customerName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.bookTime = bookTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }
}
