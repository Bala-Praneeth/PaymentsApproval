package com.example.vitaly.paymentsapproval.model.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.vitaly.paymentsapproval.model.data.Payment;

@Database(entities = {Payment.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class PaymentsDatabase extends RoomDatabase {

    private static PaymentsDatabase INSTANCE;

    public abstract PaymentsDao paymentsDao();

    public static PaymentsDatabase getInstance (Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PaymentsDatabase.class, "Payments.db")
                    .build();
        }
        return INSTANCE;
    }
}
