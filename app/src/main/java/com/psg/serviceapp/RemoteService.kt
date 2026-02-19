package com.psg.serviceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.psg.serviceapp.common.CryptoUtil


class RemoteService : Service() {

    private val binder = object : IMessageService.Stub() {

        override fun sendMessage(encryptedMessage: String?): String {

            if (encryptedMessage.isNullOrEmpty()) {
                return CryptoUtil.encrypt("ERROR: Empty message")
            }

            val decrypted = try {
                CryptoUtil.decrypt(encryptedMessage)
            } catch (e: Exception) {
                return CryptoUtil.encrypt("ERROR: Decryption failed")
            }

            val processed = decrypted
                .reversed()
                .uppercase() +
                    " | " + System.currentTimeMillis()

            return CryptoUtil.encrypt(processed)
        }
    }

    override fun onBind(intent: Intent?): IBinder = binder
}
