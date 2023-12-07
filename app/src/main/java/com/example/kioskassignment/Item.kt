package com.example.kioskassignment

/** 각 메뉴 아이템 클래스. 부모클래스이지만 자식 분화 필요 없으면 이것만으로 사용해도 좋다. */
open class Item(inName: String, inPrice: Int, inDetail: String) {
    var name = inName
    var price = inPrice
    var detail = inDetail

    /** 이름, 가격, 설명을 String으로 반환 */
    fun info(): String = "%-12s\t| %7s\t| %s".format(name, "${price}원", detail)
}
