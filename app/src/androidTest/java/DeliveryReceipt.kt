class DeliveryReceipt: Receipt() {
    var altitude: Double = 0.0
    var longitude: Double = 0.0

    fun addPosition(x: Double, y: Double) {
        altitude = x
        longitude = y
    }

    override fun printProceedingOrders(num: Int) {
        println("[ 주문 ${num} ]")
        println("[ 위도 : ${altitude}, 경도 : ${longitude} ]")
        receipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }
        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }
}