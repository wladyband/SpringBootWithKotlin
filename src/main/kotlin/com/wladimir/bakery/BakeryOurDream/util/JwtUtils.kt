package com.wladimir.bakery.BakeryOurDream.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts

import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

object JwtUtils {

    // Chave pública correspondente à chave privada usada para assinar os tokens RS256
    private val publicKey: PublicKey = loadPublicKey()

    fun parseJwt(token: String): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(publicKey)
            .build()
            .parseClaimsJws(token)
    }

    private fun loadPublicKey(): PublicKey {
        // Substitua publicKeyString pela sua chave pública em formato DER
        val publicKeyString = "6f7254101f56e41cf35c9926de84a2d552b4c6f1" // Exemplo

        val keyBytes = hexStringToByteArray(publicKeyString)
        val keySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }

    private fun hexStringToByteArray(hexString: String): ByteArray {
        val len = hexString.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(hexString[i], 16) shl 4) + Character.digit(hexString[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }
}
