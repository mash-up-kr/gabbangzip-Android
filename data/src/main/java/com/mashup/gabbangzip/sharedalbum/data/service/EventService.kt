package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateEventRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.CreateEventResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface EventService {
    @POST("api/v1/events")
    suspend fun createEvent(
        @Body createEventRequest: CreateEventRequest,
    ): PicResponse<CreateEventResponse>
}
