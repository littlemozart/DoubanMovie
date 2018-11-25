package com.lee.doubanmovie.data

data class Movie(var subjects: List<Subject>) {
    data class Subject(
        var rate: String,
        var cover_x: Int,
        var title: String,
        var url: String,
        var playable: Boolean,
        var cover: String,
        var id: Int,
        var cover_y: Int,
        var is_new: Boolean
    )
}