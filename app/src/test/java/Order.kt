import android.content.ClipData.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class MyOrder(
    val name: String,
    val price: Int,
    val description: String,
    var myMoney: Int,
    var priceSum:Int
)

class Order(){
    public val myOrders: MutableList<MyOrder> = mutableListOf()
    private var myMoney = 20000
    private var priceSum = 0
    private var orderCount = 0
    public var coupon = 0

    //주문 추가 총가격은 굳이 미리 계산하지 않고, 장바구니를 확인할때 계산.
    fun addOrder(selectedMenu : Menu) {
        val myOrder = MyOrder(selectedMenu.name, selectedMenu.price, selectedMenu.description, myMoney, priceSum)
        myOrders.add(myOrder)
    }

    //5번 장바구니 확인하는 부분
    fun showOrder() {
        println("[ Orders ]")
        var sum = 0
        for ((index, myOrder) in myOrders.withIndex()) {
            println("${index + 1} : ${myOrder.name}   | W${myOrder.price}  ${myOrder.description}")
            sum +=  myOrder.price
        }
        println("[ Total ]")
        println("W $sum")
        println()
        println("1.주문    2. 메뉴판")
    }

    //5번 장바구니 확인후 주문할때 내 돈이 총가격 이상 있는지 확인, 있으면 주문 없으면 주문불가
    fun isMyMoneyOk(){
        var sum = 0
        for (myOrder in myOrders) {
            sum += myOrder.price
        }
        if(myMoney >= sum){
            //쿠폰 발급상황을 확인하고, 발급한 상태라면 사용할건지 물어봄.
            if(coupon == 1 && sum >= 15000){
                println("쿠폰 사용이 가능합니다. 사용하시겠습니까?")
                println("1.네   2. 아니오")
                var isCoupon = readLine()!!.toInt()
                when(isCoupon){
                    1 -> {
                        println("원래 금액 : $sum")
                        sum -= 1000
                        println("할인된 금액 : $sum")
                    }
                    2 -> {
                        sum = sum
                    }
                    else -> {println("바른 번호선택이 아닙니다.")}
                }
            }
            println("주문이 완료되었습니다.")
            myMoney -= sum
            println("남은잔고 : ${myMoney}")
            myOrders.clear()
            orderCount++
        }else{
            var needMoney = sum - myMoney
            println("현재 잔액은 ${myMoney} 으로 ${needMoney}이 부족해서 주문할 수 없습니다.")
        }
    }

    fun cancleOrder(){
        if(myOrders.isEmpty()){
            println("장바구니가 이미 비어있습니다.")
        }else{
            for (myOrder in myOrders) {
                myMoney = myOrder.myMoney
            }
            myOrders.clear()
            println("장바구니에 있는 물품들을 비웠습니다.")
        }
    }

    //5초마다 총 주문자수를 출력하는 메소드
    suspend fun waitingOrder(){
        runBlocking {
            launch {
                while (true){
                    println("현재 주문 대기수 : $orderCount")
                    delay(5000)
                }
            }
        }
    }

    //15000원이상 구매시 할인가능한 쿠폰 발급.

}
