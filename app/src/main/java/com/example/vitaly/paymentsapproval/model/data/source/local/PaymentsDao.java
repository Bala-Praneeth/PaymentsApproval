package com.example.vitaly.paymentsapproval.model.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vitaly.paymentsapproval.model.data.Payment;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PaymentsDao {

    @Query("SELECT * FROM payments_table")
    List<Payment> getPayments();

    @Query("SELECT * FROM payments_table WHERE uid = :uid")
    Payment getPaymentByUid(String uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPayment(Payment payment);

}
