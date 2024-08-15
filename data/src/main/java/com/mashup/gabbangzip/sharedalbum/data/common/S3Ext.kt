package com.mashup.gabbangzip.sharedalbum.data.common

fun String.toS3Url() = Constants.S3_BUCKET_DOMAIN_URl + this
