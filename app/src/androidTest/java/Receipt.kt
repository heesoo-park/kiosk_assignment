open class Receipt {
    var receipt: MutableList<Order> = mutableListOf<Order>()
    var sum: Int = 0

    fun addMenu(detail: Item) {
        receipt.add(Order(detail))
        sum += detail.price
    }

    fun printOrders() {
        println("[ 장바구니 ]")
        receipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }

        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }

    open fun printProceedingOrders(num: Int) {
        println("[ 주문 ${num} ]")
        receipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.detail.name}\t | ${it.detail.price} | ${it.detail.desc}")
        }
        println()
    }
}