package com.mashup.gabbangzip.sharedalbum.domain.base

sealed class PicException : Throwable() {
    data object LoginRequiredException : PicException()
    data object UnknownException : PicException()
}
