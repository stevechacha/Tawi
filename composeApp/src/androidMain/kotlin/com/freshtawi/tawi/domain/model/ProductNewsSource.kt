package com.freshtawi.tawi.domain.model

import kotlinx.datetime.Instant

data class ProductNewsSource(
    val id: Int,
    val name: String,
    val url: String,
    val description: String,
    val category: String,
    val date: String,
    val title: String,
    val content: String,
    val headerImageUrl: String?,
    val publishDate: Instant,
    val type: String
)