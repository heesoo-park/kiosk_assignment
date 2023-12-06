package com.example.kioskassignment

/** 아이템 목록을 담는 아이템리스트 클래스.
 * 버거류, 음료류 등에 해당 */
open class ItemList {
    /** ~~류 */
    var name = ""
    var detail = ""
    lateinit var lst: ArrayList<Item>

    constructor(inName: String) {
        name = inName
        lst = ArrayList<Item>()
    }

    constructor(inName: String, inDetail: String) {
        name = inName
        detail = inDetail
        lst = ArrayList<Item>()
    }

    constructor(inName: String, inDetail: String, list: ArrayList<Item>) {
        name = inName
        detail = inDetail
        lst = list
    }

    fun clearItem() {
        lst.clear()
    }

    fun addItem(item: Item) {
        lst.add(item)
    }

    fun removeItem(index: Int) {
        lst.removeAt(index)
    }

    fun size() = lst.size

    /** 내부 lst [] 인덱스 접근 오퍼레이터 */
    operator fun get(i: Int): Item = lst[i]
}
