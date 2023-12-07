class DeliveryReceipt {
    var deliveryReceipt: MutableList<Order> = mutableListOf()
    var sum: Int = 0
    var altitude: Double = 0.0
    var longitude: Double = 0.0

    fun addMenu(detail: Item) {
        deliveryReceipt.add(Order(detail))
        sum += detail.price
    }

    fun addPosition(x: Double, y: Double) {
        altitude = x
        longitude = y
    }

    fun cancelMenu(idx: Int) {
        sum -= deliveryReceipt[idx].detail.price
        deliveryReceipt.removeAt(idx)
    }

    fun printOrders() {
        println("[ 장바구니 ]")
        deliveryReceipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }

        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }

    fun printProceedingDelivery(num: Int) {
        println("[ 주문 ${num} ]")
        println("[ 위도 : ${altitude}, 경도 : ${longitude} ]")
        deliveryReceipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }
        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }

    fun clearOrders() {
        deliveryReceipt.clear()
        sum = 0
    }
}