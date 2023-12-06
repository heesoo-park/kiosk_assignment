class OrderReceipt {
    var orderReceipt: MutableList<Order>
    var sum: Int

    constructor() {
        orderReceipt = mutableListOf()
        sum = 0
    }
    constructor(orderReceipt: MutableList<Order>, totalSum: Int) {
        this.orderReceipt = orderReceipt
        this.sum = totalSum
    }

    fun addMenu(detail: Item) {
        orderReceipt.add(Order(detail))
        sum += detail.price
    }

    fun cancelMenu(idx: Int) {
        sum -= orderReceipt[idx].detail.price
        orderReceipt.removeAt(idx)
    }

    fun printOrders() {
        println("[ 장바구니 ]")
        orderReceipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }

        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }

    fun printProceedingOrders(num: Int) {
        println("[ 주문 ${num} ]")
        orderReceipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }
        println()
    }

    fun clearOrders() {
        orderReceipt.clear()
        sum = 0
    }
}