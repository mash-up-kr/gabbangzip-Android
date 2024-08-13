package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.vote.VoteResultRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.vote.VotePhotoResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.vote.VoteResultResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VoteService {
    @POST("api/v1/votes")
    suspend fun requestVoteResult(
        @Body request: VoteResultRequest,
    ): PicResponse<VoteResultResponse>

    @GET("/api/v1/votes/{eventId}/options")
    suspend fun getVotePhotoList(
        @Path(value = "eventId") eventId: Long,
    ): PicResponse<VotePhotoResponse>
}
