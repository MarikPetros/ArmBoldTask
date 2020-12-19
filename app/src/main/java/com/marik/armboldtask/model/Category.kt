package com.marik.armboldtask.model

class Category(
    val name: String,
    val categoryImage: Int,
    val backgroundShape: Int,
    var shapeShadowColor: Int,
    var productList: List<Product>
)