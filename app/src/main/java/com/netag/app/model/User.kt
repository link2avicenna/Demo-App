package com.netag.app.model

import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val name: String,
    val email: String,
    val designation: String,
    val companyname: String,
    val phone: String,
    val profileUrl: String
)

data class Contacts(val id: String, val name: String, val imageview: String)

data class Users1(val id: String, val directMode: Boolean, val leadMode: Boolean, val profileOn: Int)

data class UserSelectedLink1(val id: String, val linkId: String, var selectedForDirect: Boolean)

data class UserSelectedLink(
    val id: Int,
    val name: String,
    @DrawableRes val socialIcon: Int, // Resource ID for the social media icon
    val value: String, // Link or identifier for the social media account
    val baseUrl: String? = null // Optional base URL for the link
)