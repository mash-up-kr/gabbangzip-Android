package com.mashup.gabbangzip.sharedalbum.domain.datasource

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfoDomainModel

interface LocalDataSource {
    fun removeAll()
    fun saveToken(accessToken: String, refreshToken: String)
    fun removeToken()
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun saveUserInfo(userInfo: UserInfoDomainModel)
    fun loadUserInfo(): UserInfoDomainModel
    fun removeUserInfo()
    fun saveVoteFirstVisit(isFirstVisit: Boolean)
    fun getVoteFirstVisit(): Boolean
    fun saveIsFirstOpen(isFirstOpen: Boolean)
    fun getIsFirstOpen(): Boolean
    fun saveHomeAlignState(alignState: String)
    fun getHomeAlignState(): String
}
