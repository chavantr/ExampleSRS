package com.example.examplesrs.process

import android.os.AsyncTask
import com.example.examplesrs.model.User
import org.json.JSONObject

class LoginAsync : AsyncTask<JSONObject, Void, User?>() {

    private lateinit var onLoginListener: OnLoginListener

    override fun doInBackground(vararg params: JSONObject?): User? {
        val response = HttpConnectionUtil().requestPost(Constants.URL + Constants.LOGIN, params[0])
        if (response.isNotEmpty()) {
            val jNode = JSONObject(response)
            var user = User()
            user.id = jNode.getInt("Id")
            user.name = jNode.getString("Name")
            user.password = jNode.getString("Password")
            user.email = jNode.getString("Email")
            user.number = jNode.getString("Number")
            return user

        } else {
            return null
        }
    }

    override fun onPostExecute(result: User?) {
        super.onPostExecute(result)
        onLoginListener.onLoginSuccess(result)
    }

    fun setOnLoginListener(onLoginListener: OnLoginListener, request: JSONObject) {
        this.onLoginListener = onLoginListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }


}