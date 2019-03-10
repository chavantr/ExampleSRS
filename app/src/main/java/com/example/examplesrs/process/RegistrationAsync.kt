package com.example.examplesrs.process

import android.os.AsyncTask
import org.json.JSONObject

class RegistrationAsync : AsyncTask<JSONObject, Void, String?>() {

    private lateinit var onRegistrationListener: OnRegistrationListener

    override fun doInBackground(vararg params: JSONObject?): String? {
        return HttpConnectionUtil().requestPost(Constants.URL + Constants.REGISTRATION, params[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onRegistrationListener.onRegistrationComplete(result)
    }

    fun setOnRegistrationListener(onRegistrationListener: OnRegistrationListener, request: JSONObject) {
        this.onRegistrationListener = onRegistrationListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }

}