package com.psg.serviceapp.common

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object CryptoUtil {

    private const val SECRET_KEY = "1234567890123456"
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/ECB/PKCS5Padding"

    fun encrypt(input: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(), ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        val encrypted = cipher.doFinal(input.toByteArray())
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    fun decrypt(encrypted: String): String {
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(), ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, keySpec)
            val decoded = Base64.decode(encrypted, Base64.DEFAULT)
            String(cipher.doFinal(decoded))
        } catch (e: Exception) {
            "Decryption Failed"
        }
    }
}