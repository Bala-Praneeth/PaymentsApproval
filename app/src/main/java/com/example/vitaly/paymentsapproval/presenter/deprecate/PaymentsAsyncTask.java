package com.example.vitaly.paymentsapproval.presenter.deprecate;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vitaly.paymentsapproval.model.api.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by vitaliy on 07/03/2018.
 */

public class PaymentsAsyncTask extends AsyncTask<Void, Void, String> {
    private final String LOG_TAG = PaymentsAsyncTask.class.getSimpleName();
    private LinkedList<String> mWordList;
    private RecyclerView mRecyclerView;

    public PaymentsAsyncTask(LinkedList<String> mWordList, RecyclerView mRecyclerView) {
        this.mWordList = mWordList;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(LOG_TAG, "Start async task!");
    }

    @Override
    protected String doInBackground(Void... param) {
        Log.d(LOG_TAG, "Running async task!");
        return NetworkUtils.getPaymentInfo();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Log.d(LOG_TAG, result);
        try {
            JSONArray jsonArray = new JSONArray(s);
            int i = 0;
            String pNumber = null;
            while (i < jsonArray.length()) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                pNumber = jsonObject.getString("Номер");
                mWordList.addLast(pNumber);
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mRecyclerView.getAdapter().notifyItemInserted(mWordList.size());
        Log.d(LOG_TAG, "End async task!");
    }
}
