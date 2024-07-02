package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.mashup.gabbangzip.sharedalbum.data.dto.model.ImageWithFrame
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class GroupDetailResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "keyword")
    val keyword: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "status_description")
    val statusDescription: String,
    @Json(name = "updated_images")
    val updatedImages: Boolean,
    @Json(name = "piced")
    val piced: Boolean,
    @Json(name = "recent_event_date")
    val recentEventDate: LocalDateTime,
    @Json(name = "card_front_image_url")
    val cardFrontImageUrl: String,
    @Json(name = "card_back_images")
    val cardBackImages: List<ImageWithFrame>,
)
