package com.example.kioskassignment

fun main() {
    /** 키오스크 기능은 키오스크 클래스에서 이루어진다 */
    Kiosk().run()
}

/**
 * 고객이 현금 카드(체크 카드)를 결제 수단으로 사용하는 것으로 상정.
 * 혹은 신용 카드의 한도까지의 잔액이라고 봐도 된다
 */
object Cashcard {
    var money = 60000
}
