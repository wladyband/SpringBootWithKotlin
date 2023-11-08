package com.wladimir.bakery.BakeryOurDream.resource

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT

import com.wladimir.bakery.BakeryOurDream.model.UserInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserResource {

    data class TokenRequest(val tokenId: String)



    @PostMapping("/extract-aud")
    fun extractAudFromToken(@RequestBody tokenRequest: TokenRequest): ResponseEntity<UserInfo> {
        val tokenId = tokenRequest.tokenId

        try {
            val decodedJWT: DecodedJWT = JWT.decode(tokenId)


            val tokenData = UserInfo(
                iss = decodedJWT.issuer,
                azp = decodedJWT.getClaim("azp").asString(),
                aud = decodedJWT.getClaim("aud").asString(),
                sub = decodedJWT.subject,
                email = decodedJWT.getClaim("email").asString(),
                email_verified = decodedJWT.getClaim("email_verified").asBoolean(),
                name = decodedJWT.getClaim("name").asString(),
                picture = decodedJWT.getClaim("picture").asString(),
                given_name = decodedJWT.getClaim("given_name").asString(),
                family_name = decodedJWT.getClaim("family_name").asString(),
                locale = decodedJWT.getClaim("locale").asString(),
                iat = decodedJWT.issuedAt.time / 1000, // Converte em segundos
                exp = decodedJWT.expiresAt.time / 1000
            )



            return ResponseEntity.ok(tokenData)
        } catch (e: Exception) {
            // Handle parsing errors or invalid tokens here
            return ResponseEntity.badRequest().build()
        }
    }
}
