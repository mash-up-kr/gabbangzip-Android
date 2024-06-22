package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

data class Photo(
    val id: Int,
    val imageUrl: Int,
    val date: String,
) {
    companion object {
        fun getTempList(): List<Photo> {
            return listOf(
                Photo(1, 1, "2021-09-01"),
                Photo(2, 2, "2021-09-02"),
                Photo(3, 3, "2021-09-03"),
                Photo(4, 4, "2021-09-04"),
                Photo(5, 5, "2021-09-05"),
                Photo(6, 6, "2021-09-06"),
                Photo(7, 7, "2021-09-07"),
                Photo(8, 8, "2021-09-08"),
                Photo(9, 9, "2021-09-09"),
                Photo(10, 10, "2021-09-10"),
            )
        }
    }
}
