package com.example.kioskcli

open class Item {
    var name = ""
    var price = 0
    var detail = ""

    constructor(inName: String) {
        name = inName
    }

    constructor(inName: String, inPrice: Int) {
        name = inName
        price = inPrice
    }

    constructor(inName: String, inPrice: Int, inDetail: String) {
        name = inName
        price = inPrice
        detail = inDetail
    }

    fun displayInfo() {
        println("$name | W $price | $detail")
    }

    fun info(): String =
        "$name\t| $price 원\t| $detail"
}
