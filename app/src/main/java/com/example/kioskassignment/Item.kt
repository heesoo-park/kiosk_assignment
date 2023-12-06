package com.example.kioskassignment

/** 각 메뉴 아이템 클래스. 부모클래스이지만 자식 분화 필요 없으면 이것만으로 사용해도 좋다. */
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

    /** 이름, 가격, 설명을 출력(println) */
    fun displayInfo() {
        println("$name\t| $price 원\t| $detail")
    }

    /** 이름, 가격, 설명을 String으로 반환 */
    fun info(): String =
        "$name\t| $price 원\t| $detail"
}
