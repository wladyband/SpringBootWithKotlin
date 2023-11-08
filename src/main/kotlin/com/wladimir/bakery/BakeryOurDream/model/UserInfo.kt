package com.wladimir.bakery.BakeryOurDream.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "user_info")
data class UserInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val iss: String,
    val azp: String,
    val aud: String,
    val sub: String,
    val email: String,
    val email_verified: Boolean,
    val name: String,
    val picture: String,
    val given_name: String,
    val family_name: String,
    val locale: String,
    val iat: Long,
    val exp: Long
)
