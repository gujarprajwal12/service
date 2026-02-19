package com.psg.serviceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.psg.serviceapp.common.CryptoUtil


class RemoteService : Service() {

    private val binder = object : IMessageService.Stub() {
        override fun sendMessage(encryptedMessage: String?): String {
            val decrypted = CryptoUtil.decrypt(encryptedMessage ?: "")
            val processed = decrypted.reversed() + " | " + System.currentTimeMillis()
            return CryptoUtil.encrypt(processed)
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
