package com.example.examplesrs.process

import android.os.AsyncTask
import org.json.JSONObject

class PersonalDetailsAsync : AsyncTask<JSONObject, Void, String?>() {

    private lateinit var onPersonalDetailsListener: OnPersonalDetailsListener

    override fun doInBackground(vararg params: JSONObject?): String? {
        return HttpConnectionUtil().requestPost(Constants.URL + Constants.PERSONAL_DETAILS_IN, params[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onPersonalDetailsListener.onPersonalDetailsSuccess(result)
    }

    fun setOnPersonalDetailsListener(onPersonalDetailsListener: OnPersonalDetailsListener, request: JSONObject) {
        this.onPersonalDetailsListener = onPersonalDetailsListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }


}