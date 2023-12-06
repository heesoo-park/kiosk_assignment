data class MyOrder(
    val name: String,
    val price: Int,
    val description: String,
    var myMoney: Int,
    var priceSum:Int
)

class Order(){
    public val myOrders: MutableList<MyOrder> = mutableListOf()

    //주문 추가 총가격은 굳이 미리 계산하지 않고, 장바구니를 확인할때 계산.
    fun addOrder(name: String, price: Int, description: String, priceSum:Int? = null) {
        var myMoney = 20000
        var priceSum = 0
        val myOrder = MyOrder(name, price, description, myMoney, priceSum)
        myOrders.add(myOrder)
    }

    //5번 장바구니 확인하는 부분
    fun showOrder() {
        println("[ Orders ]")
        var sum = 0
        for ((index, myOrder) in myOrders.withIndex()) {
            println("${index + 1} : ${myOrder.name}   | W${myOrder.price}  ${myOrder.description}")
            myOrder.priceSum += myOrder.price
            sum +=  myOrder.priceSum
        }
        println("[ Total ]")
        println("W $sum")
        println()
        println("1.주문    2. 메뉴판")
    }

    //5번 장바구니 확인후 주문할때 내 돈이 총가격 이상 있는지 확인, 있으면 주문 없으면 주문불가
    fun isMyMoneyOk(){
        for ((index, myOrder) in myOrders.withIndex()) {
            println("${index + 1} : ${myOrder.name}   | W${myOrder.price}  ${myOrder.description}")

            if(myOrder.myMoney > myOrder.priceSum){
                println("주문이 완료되었습니다.")
                myOrder.myMoney -= myOrder.priceSum
                println("남은잔고 : ${myOrder.myMoney}")
//                myOrders.clear()
                //문제1. 초기화.
                //문제2. 초기화후 addOrder에서 mymoney가 20000원으로 또 주어짐.
            }else{
                var needMoney = myOrder.priceSum - myOrder.myMoney
                println("현재 잔액은 ${myOrder.myMoney} 으로 ${needMoney}이 부족해서 주문할 수 없습니다.")
            }
        }
    }

    fun cancleOrder(){
    }

}
