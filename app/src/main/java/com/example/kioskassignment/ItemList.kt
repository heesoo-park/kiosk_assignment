package com.example.kioskassignment

open class ItemList {
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
    operator fun get(i: Int): Item = lst[i]
}
