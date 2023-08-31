package com.example.medilabmemberapp.helpers

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.medilabmemberapp.R


class DialogHelper {

    companion object{
        fun showDialog(context: Context){
            val dialogView: View =LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null)
            val title = dialogView.findViewById<TextView>(R.id.dialog_title)
            title.text = "Check Network"

            val message = dialogView.findViewById<TextView>(R.id.dialog_message)
            message.text = "Please check your intenret connection"

            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)

            val alertDialog = builder.create()
            alertDialog.show()

            val button = dialogView.findViewById<Button>(R.id.dialog_button)
            button.setOnClickListener {
                alertDialog.dismiss()
            }


        }
    }
}
