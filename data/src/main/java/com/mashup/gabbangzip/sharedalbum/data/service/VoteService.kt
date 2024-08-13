package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.vote.VoteResultRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.vote.VoteResultResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface VoteService {
    @POST("api/v1/votes")
    suspend fun requestVoteResult(
        @Body request: VoteResultRequest,
    ): PicResponse<VoteResultResponse>
}
