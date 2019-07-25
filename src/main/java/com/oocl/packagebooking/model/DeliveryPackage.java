package com.oocl.packagebooking.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DeliveryPackage {

    @Id
    @GeneratedValue
    private long id;

    private long waybillNumber;
    private String customerName;
    private String phoneNumber;
    private int status;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date bookTime;

    public DeliveryPackage() {
    }

    public DeliveryPackage(long waybillNumber, String customerName, String phoneNumber) {
        this.waybillNumber = waybillNumber;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.status = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(long waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
