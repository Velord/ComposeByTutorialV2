package com.example.composebytutorialv2.data.model.section3

enum class PostType(val type: Int) {
    TEXT(0),
    IMAGE(1);

    companion object {
        fun fromType(type: Int): PostType = if (type == TEXT.type) TEXT else IMAGE
    }
}