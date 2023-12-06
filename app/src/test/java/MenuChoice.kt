
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun main(){
    val order = Order()
    val menuUI = MenuUI()
    //코루틴을 사용해서 비동기적으로 메서드 실행
    GlobalScope.launch {
        order.waitingOrder()
    }
    while(true){
        println("메뉴판을 불러오는중....")
        println()
        Thread.sleep(3000)
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        println()
        //메인 메뉴판 불러오기
        menuUI.mainMenu()
        if(order.myOrders.isNotEmpty()){
            menuUI.subMenu()
        }

        val mainChoice = choiceNumber("mainChoice").toString().toInt()
        when(mainChoice){
            1 -> {
                menuUI.burgerMenu()
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    in 1..MenuInfo.burgerInfo.size-> {
                        MenuInfo.burgerInfo[otherChoice-1].displayInfo()
                        intoOrder()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder(MenuInfo.burgerInfo[otherChoice -1])
                                println("주문목록에 ${MenuInfo.burgerInfo[otherChoice-1].name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    else -> {println("뒤로가기")}
                }
            }
            2 -> {
                menuUI.chickenMenu()
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    in 1..MenuInfo.chickenInfo.size-> {
                        MenuInfo.chickenInfo[otherChoice-1].displayInfo()
                        intoOrder()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder(MenuInfo.chickenInfo[otherChoice-1])
                                println("주문목록에 ${MenuInfo.chickenInfo[otherChoice-1].name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    else -> {println("뒤로가기")}
                }
            }
            3 -> {
                menuUI.drinkMenu()
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    in 1.. MenuInfo.drinkInfo.size-> {
                        MenuInfo.drinkInfo[otherChoice-1].displayInfo()
                        intoOrder()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder(MenuInfo.drinkInfo[otherChoice-1])
                                println("주문목록에 ${MenuInfo.drinkInfo[otherChoice-1].name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    else -> {println("뒤로가기")}
                }
            }
            4 -> {
                menuUI.sideMenu()
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    in 1 .. MenuInfo.sideInfo.size-> {
                        MenuInfo.sideInfo[otherChoice-1].displayInfo()
                        intoOrder()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder(MenuInfo.sideInfo[otherChoice-1])
                                println("주문목록에 ${MenuInfo.sideInfo[otherChoice-1].name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    else -> {println("뒤로가기")}
                }
            }
            5 -> {
                println(" 아래와 같이 주문 하시겟습니까?")
                println()
                order.showOrder()
                val realOrder = choiceNumber("realOrder").toString().toInt()
                when(realOrder){
                    1 -> {
                        //특정시간을 비교해서 특정시간대에는 결제 불가능하게
                        val currentTime = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("HHmm")
                        val formattedcurrentTime = currentTime.format(formatter).toInt()

                        val formatter1 = DateTimeFormatter.ofPattern("HH시mm분")
                        val formattedcurrentTime1 = currentTime.format(formatter1)
                        if(formattedcurrentTime > 1730 && formattedcurrentTime < 1750){
                            println("현재 시각은 $formattedcurrentTime1 입니다.")
                            println("은행 점검 시간은 17시30분 ~ 17시 50분 이므로 결제할 수 없습니다.")
                        }else{
                            order.isMyMoneyOk()
                        }
                    }
                    else -> {}
                }
            }
            6 -> {
                order.cancleOrder()
            }
            7 -> {
                println("W15000 이상 구매시 사용가능한 1000원 할인쿠폰을 발급해드렸습니다.")
                order.coupon = 1
            }
            else -> {
                println("프로그램을 종료합니다.")
                break
            }
        }
    }
}
fun choiceNumber(type:String): Int{
    when(type){
        "mainChoice" -> {
            while (true){
                try {
                    val mainChoice:String? = readLine()
                    val result = mainChoice?.toInt() ?: -1
                    if(result > 7 || result < 0){
                        println("범위를 확인하고 다시 골라주세요")
                    }else{
                        return result
                    }
                }catch (e: Exception){
                    println("숫자로 입력해주세요")
                }
            }
        }
        "otherChoice" -> {
            while (true){
                try {
                    val otherChoice:String? = readLine()
                    val result = otherChoice?.toInt() ?: -1
                    if(result > 3 || result < 0){
                        println("범위를 확인하고 다시 골라주세요")
                    }else{
                        return result
                    }
                }catch (e: Exception){
                    println("숫자로 입력해주세요")
                }
            }
        }
        "orderOrCancle" -> {
            while (true){
                try {
                    val orderOrCancle:String? = readLine()
                    val result = orderOrCancle?.toInt() ?: -1
                    if(result > 2 || result < 0){
                        println("범위를 확인하고 다시 골라주세요")
                    }else{
                        return result
                    }
                }catch (e: Exception){
                    println("숫자로 입력해주세요")
                }
            }
        }
        "realOrder" -> {
            while (true){
                try {
                    val realOrder:String? = readLine()
                    val result = realOrder?.toInt() ?: -1
                    if(result > 2 || result < 0){
                        println("범위를 확인하고 다시 골라주세요")
                    }else{
                        return result
                    }
                }catch (e: Exception){
                    println("숫자로 입력해주세요")
                }
            }
        }
        else -> {return 0}
    }
}
fun intoOrder(){
    println("위 메뉴를 장바구니에 추가하시겠습니까?")
    println("1. 확인       2. 취소")
}