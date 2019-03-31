package com.example.examplesrs.process

import android.os.AsyncTask
import org.json.JSONObject

class RateAsync : AsyncTask<JSONObject, Void, String?>() {

    private lateinit var onRateListener: OnRateListener

    override fun doInBackground(vararg param: JSONObject?): String? {
        return HttpConnectionUtil().requestPost(Constants.URL + Constants.SRS_Rate, param[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onRateListener.onRateSuccess(result)
    }

    fun setOnRateListener(onRateListener: OnRateListener, request: JSONObject) {
        this.onRateListener = onRateListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }



}