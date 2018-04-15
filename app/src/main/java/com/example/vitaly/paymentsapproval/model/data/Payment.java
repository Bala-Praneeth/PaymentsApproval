package com.example.vitaly.paymentsapproval.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vitaliy on 19/03/2018.
 */

public class Payment implements Serializable {

    @SerializedName("GUID")
    @Expose
    private String UID;
    @SerializedName("Дата")
    @Expose
    private Date date;
    @SerializedName("Номер")
    @Expose
    private String number;

    public String getGUID() {
        return UID;
    }

    public void setGUID(String gUID) {
        this.UID = gUID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateAsString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static class Builder {
        private final String guid;

        private Date date;
        private String number;

        public Builder(String guid) {
            this.guid = guid;
        }

        public Payment build() {
            return new Payment(this);
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }
    }

    public Payment(Builder builder) {
        UID = builder.guid;
        date = builder.date;
        number = builder.number;
    }
}
