package com.freshtawi.tawi.domain.repo

import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.domain.model.Address
import com.freshtawi.tawi.domain.model.Session
import com.freshtawi.tawi.domain.model.User

interface IUserGateway {
    suspend fun createUser(account: Account): User
    suspend fun loginUser(username: String, password: String): Session
    suspend fun refreshAccessToken(refreshToken: String): Pair<String, String>
    suspend fun getUserProfile(): User
    suspend fun getUserAddresses(): List<Address>
    suspend fun updateProfile(fullName: String?, phone: String?): User
}
