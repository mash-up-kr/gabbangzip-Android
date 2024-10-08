package com.mashup.gabbangzip.sharedalbum.domain.base

sealed class PicException : Throwable() {
    data object LoginRequiredException : PicException()
    data object UnknownException : PicException()
    data object GroupOverflowException : PicException()
    data object InvalidGroupCodeException : PicException()
    data object NoWithdrawGroupException : PicException()
}
