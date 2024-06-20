package com.mashup.gabbangzip.sharedalbum.presentation.auth

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.Profile

/**
 * 카카오 로그인 유틸
 * https://developers.kakao.com/docs/latest/ko/kakaologin/android
 */
object KakaoUserSdkUtil {
    private const val TAG = "카카오 로그인 테스트"

    fun loginWithKakao(
        context: Context,
        onSuccess: (idToken: String, profile: Profile) -> Unit,
        onFailure: (Throwable?) -> Unit,
    ) = with(UserApiClient.instance) {
        val callback = createKakaoAuthCallback(onSuccess, onFailure)

        if (isKakaoTalkLoginAvailable(context)) {
            loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.idToken}")
                    callback(token, null)
                }
            }
        } else {
            loginWithKakaoAccount(context, callback = callback)
        }
    }

    private fun createKakaoAuthCallback(
        onSuccess: (idToken: String, profile: Profile) -> Unit,
        onFailure: (Throwable?) -> Unit = {},
    ): (OAuthToken?, Throwable?) -> Unit {
        val kakaoOAuthCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
                onFailure(error)
            } else if (token?.idToken != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.idToken}")
                getKakaoUserProfile(
                    onSuccess = { profile ->
                        onSuccess(token.idToken!!, profile)
                    },
                    onFailure = onFailure,
                )
            } else {
                onFailure(IllegalStateException("아이디 토큰을 찾을 수 없음"))
            }
        }
        return kakaoOAuthCallback
    }

    private fun getKakaoUserProfile(
        onSuccess: (Profile) -> Unit,
        onFailure: (Throwable?) -> Unit = {},
    ) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
                onFailure(error)
            } else if (user != null) {
                Log.i(
                    TAG,
                    "사용자 정보 요청 성공\n닉네임: ${user.kakaoAccount?.profile?.nickname}\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}",
                )
                val profile = user.kakaoAccount?.profile
                if (profile != null) {
                    onSuccess(profile)
                } else {
                    onFailure(IllegalStateException("사용자 프로필 정보를 찾을 수 없음"))
                }
            }
        }
    }

    /**
     * 로그아웃
     */
    fun logout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }

    /**
     * 회원탈퇴 시, 카카오계정 연결 끊기
     */
    fun withdrawal(
        onSuccess: () -> Unit = {},
        onFailure: (Throwable?) -> Unit = {},
    ) {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(TAG, "연결 끊기 실패", error)
                onFailure(error)
            } else {
                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                onSuccess()
            }
        }
    }
}
