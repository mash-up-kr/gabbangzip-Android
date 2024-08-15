package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.CreateEventRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.UploadMyPicRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.CreateEventResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.UploadMyPicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.CreateEventRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.EventVisitRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.CreateEventResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.EventVisitResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface EventService {
    @POST("api/v1/events")
    suspend fun createEvent(
        @Body createEventRequest: CreateEventRequest,
    ): PicResponse<CreateEventResponse>

    @POST("api/v1/events/images")
    suspend fun uploadMyPic(
        @Body uploadMyPicRequest: UploadMyPicRequest,
    ): PicResponse<UploadMyPicResponse>

    @PUT("api/v1/events/visit")
    suspend fun checkVisitEvent(
        @Body eventVisitRequest: EventVisitRequest,
    ): PicResponse<EventVisitResponse>
}
