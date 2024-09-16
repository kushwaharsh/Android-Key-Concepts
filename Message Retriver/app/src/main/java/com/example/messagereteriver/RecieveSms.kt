package com.example.messagereteriver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class ReceiveSms : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras
            if (bundle != null) {
                try {
                    val pdus = bundle.get("pdus") as Array<*>
                    val msgs = arrayOfNulls<SmsMessage>(pdus.size)
                    for (i in msgs.indices) {
                        msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                        val msgBody = msgs[i]?.messageBody

                        if (msgBody != null && containsExactKeyword(msgBody, "Lenon")) {
                            // Extract OTP
                            val otp = extractOtp(msgBody)
                            Toast.makeText(context, otp, Toast.LENGTH_SHORT).show()
                            // Send a local broadcast with the OTP
                            val broadcastIntent = Intent("OTP_RECEIVED")
                            broadcastIntent.putExtra("otp", otp)
                            LocalBroadcastManager.getInstance(context)
                                .sendBroadcast(broadcastIntent)
                        }


                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    // Method to extract OTP from the message body
    private fun extractOtp(message: String): String {
        val regex = "\\d+".toRegex()
        val matchResults = regex.findAll(message)
        return matchResults.firstOrNull()?.value ?: "No OTP found"
    }

    // Method to check if the message contains the exact keyword
    private fun containsExactKeyword(message: String, keyword: String): Boolean {
        // Regex to match the exact keyword, ignoring case
        val regex = "\\b$keyword\\b".toRegex(RegexOption.IGNORE_CASE)
        return regex.containsMatchIn(message)
    }
}
