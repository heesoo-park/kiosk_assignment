// 주문 메뉴들을 모아 하나의 영수증 형태로 만드는 클래스
// 하위 클래스에 Order와 Delivery가 있음
// 상속을 위해 open 키워드 붙임
open class Receipt {
    // 주문 메뉴들이 모여있는 영수증
    var receipt: MutableList<Food> = mutableListOf<Food>()
    // 주문 메뉴들의 총 금액
    var sum: Int = 0

    // 메뉴 추가
    fun addMenu(food: Food) {
        receipt.add(food)
        sum += food.price
    }

    // 장바구니 비우기
    fun clearBasket() {
        receipt.clear()
        sum = 0
    }

    // 현재까지 장바구니(영수증)에 들어가있는 모든 메뉴들을 보여줌
    // 마지막에는 총 금액까지 출력
    // 이 함수는 OrderReceipt 클래스에서만 그대로 쓰고 DeliveryReceipt 클래스에서는 쓰지 않기 때문에 open 키워드를 붙이지 않음
    fun printOrders() {
        println("[ 장바구니 ]")
        println("=====================================================================================================")
        receipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.name}\t | ${it.price} | ${it.desc}")
        }
        println("=====================================================================================================")

        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }

    // 현재까지 주문(or 배달)이 들어가 진행되고 있는 모든 메뉴들을 보여줌
    // 이 함수는 DeliveryReceipt 클래스에서 추가할 내용이 있어 open 키워드를 붙임
    open fun printProceedingOrders(num: Int) {
        println("[ 주문 ${num} ]")
        receipt.forEachIndexed { index, it ->
            println("${index + 1}. ${it.name}\t | ${it.price} | ${it.desc}")
        }
        println()
    }
}