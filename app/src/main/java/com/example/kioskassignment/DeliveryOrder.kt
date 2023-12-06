package com.example.kioskassignment

/** 배달 주문 1건 클래스 */
class DeliveryOrder {
    /** -90 ~ 90 도, 대한민국 33~43 */
    var latitude = .0

    /** 0 ~ 180 도, 대한민국 124~132 */
    var longitude = .0

    /** 배달 주문에 포함된 아이템 목록 */
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

    /** 주문 총 가격 */
    fun total(): Int = lst.sumOf { it.price }
}
