package com.freshtawi.tawi.domain.usecases

import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.database.ILocalConfigurationGateway
import com.freshtawi.tawi.domain.repo.IUserGateway
import com.freshtawi.tawi.domain.usecases.validation.IValidationUseCase
import kotlinx.coroutines.flow.Flow

interface IManageAuthenticationUseCase {
    suspend fun createUser(account: Account): Boolean

    suspend fun loginUser(username: String, password: String, keepLoggedIn: Boolean): Boolean
    suspend fun logout()
    suspend fun getAccessToken(): Flow<String>
}

class ManageAuthenticationUseCase(
    private val remoteGateway: IUserGateway,
    private val localGateway: ILocalConfigurationGateway,
    private val validation: IValidationUseCase,
) : IManageAuthenticationUseCase {

    override suspend fun createUser(account: Account): Boolean {
        with(account) {
            with(validation) {
                validateFullName(fullName); validateUsername(username); validatePassword(password)
                validateEmail(email); validatePhone(phone); validateAddress(address)
            }
        }
        return remoteGateway.createUser(account).fullName.isNotEmpty()
    }

    override suspend fun loginUser(
        username: String, password: String, keepLoggedIn: Boolean
    ): Boolean {
        validation.validateUsername(username); validation.validatePassword(password)
        val session = remoteGateway.loginUser(username, password)
        localGateway.saveAccessToken(session.accessToken)
        localGateway.saveRefreshToken(session.refreshToken)
        localGateway.saveKeepMeLoggedInFlag(keepLoggedIn)
        return true
    }

    override suspend fun logout() {
        localGateway.removeAccessToken()
        localGateway.removeRefreshToken()
//        localRestaurantGateway.clearFavoriteRestaurants()
    }

    override suspend fun getAccessToken(): Flow<String> {
        return localGateway.getAccessTokenStream()
    }

}
