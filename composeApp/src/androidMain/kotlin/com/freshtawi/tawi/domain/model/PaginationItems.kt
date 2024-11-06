package com.freshtawi.tawi.domain.model

data class PaginationItems<T>(
    val items: List<T>,
    val page: Int,
    val total: Long
)
