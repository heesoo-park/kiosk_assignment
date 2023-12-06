package com.example.kioskassignment

class DeliveryOrder {
    var latitude = .0
    var longitude = .0
    var lst = ArrayList<Item>()

    constructor(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }

    constructor(latitude: Double, longitude: Double, menus: ArrayList<Item>) {
        this.latitude = latitude
        this.longitude = longitude
        lst = menus
    }

    fun total(): Int = lst.sumOf { it.price }
}
