package com.example.medilabmemberapp.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.medilabmemberapp.R


class DialogHelper {

    companion object{
        fun showDialog(context: Context, title: String,message: String){
            val dialogView: View =LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null)
            var dialog_title = R.id.dialog_title
            var dialog_message = R.id.dialog_message
            var dialog_button = R.id.dialog_button
            dialog_title.toString()


        }
    }
}
