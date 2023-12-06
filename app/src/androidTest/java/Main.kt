import MenuInfo.burgerInfo
import MenuInfo.chickenInfo
import MenuInfo.drinkInfo
import MenuInfo.sideInfo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// 쓰지 않게된 함수들
//fun isInteger(s: String): Boolean {
//    return try {
//        s.toInt()
//        true
//    } catch (e: Exception) {
//        false
//    }
//}
//
//fun isInOption(s: String, start: Int, end: Int): Boolean {
//    return s.toInt() in start..end
//}

@OptIn(DelicateCoroutinesApi::class)
fun waitTime() {
    runBlocking {
        GlobalScope.launch {
            delay(3000)
        }.join()
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val menuUI = MenuUI()
    var orderReceipt = OrderReceipt()
    val proceedingOrderList = mutableListOf<OrderReceipt>()
    var currentMoney = (30000..50000).random()
    var coupon = false

    GlobalScope.launch {
        while (true) {
            delay(5000)
            if (proceedingOrderList.isNotEmpty()) println("(현재 주문 대기수 : ${proceedingOrderList.size})")
            else println("(현재 주문 대기수 : 0)")
        }
    }

    while (true) {
        println("안녕하세요 맘스터치입니다.")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n")
        menuUI.mainMenu()
        if (orderReceipt.orderReceipt.isNotEmpty() || proceedingOrderList.isNotEmpty()) {
            menuUI.orderMenu()
        }

        val menu = readln()
        when (menu.toIntOrNull() ?: -1) {
            0 -> break
            1 -> {
                while (true) {
                    menuUI.bergerMenu()

                    val selectedBurger = readln()
                    when (selectedBurger.toIntOrNull() ?: -1) {
                        0 -> break
                        in 1..burgerInfo.size -> {
                            while (true) {
                                burgerInfo[selectedBurger.toInt() - 1].displayInfo()
                                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                                println("1. 확인\t 2. 취소")

                                val check = readln()
                                when (check.toIntOrNull() ?: -1) {
                                    1 -> {
                                        orderReceipt.addMenu(burgerInfo[selectedBurger.toInt() - 1])
                                        println("${burgerInfo[selectedBurger.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")
                                        break
                                    }
                                    2 -> {
                                        println("처음화면으로 돌아갑니다.\n")
                                        break
                                    }
                                    else -> {
                                        println("잘못된 입력입니다 다시 입력해주세요.")
                                    }
                                }
                            }

                            waitTime()
                            break
                        }
                        else -> {
                            println("잘못된 입력입니다 다시 입력해주세요.")
                        }
                    }
                }
            }
            2 -> {
                while (true) {
                    menuUI.chickenMenu()

                    val selectedChicken = readln()
                    when (selectedChicken.toIntOrNull() ?: -1) {
                        0 -> break
                        in 1..chickenInfo.size -> {
                            while (true) {
                                chickenInfo[selectedChicken.toInt() - 1].displayInfo()
                                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                                println("1. 확인\t 2. 취소")

                                val check = readln()
                                when (check.toIntOrNull() ?: -1) {
                                    1 -> {
                                        orderReceipt.addMenu(chickenInfo[selectedChicken.toInt() - 1])
                                        println("${chickenInfo[selectedChicken.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")
                                        break
                                    }
                                    2 -> {
                                        println("처음화면으로 돌아갑니다.\n")
                                        break
                                    }
                                    else -> {
                                        println("잘못된 입력입니다 다시 입력해주세요.")
                                    }
                                }
                            }

                            waitTime()
                            break
                        }
                        else -> {
                            println("잘못된 입력입니다 다시 입력해주세요.")
                        }
                    }
                }
            }
            3 -> {
                while (true) {
                    menuUI.drinkMenu()

                    val selectedDrink = readln()
                    when (selectedDrink.toIntOrNull() ?: -1) {
                        0 -> break
                        in 1..drinkInfo.size -> {
                            while (true) {
                                drinkInfo[selectedDrink.toInt() - 1].displayInfo()
                                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                                println("1. 확인\t 2. 취소")

                                val check = readln()
                                when (check.toIntOrNull() ?: -1) {
                                    1 -> {
                                        orderReceipt.addMenu(drinkInfo[selectedDrink.toInt() - 1])
                                        println("${drinkInfo[selectedDrink.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")
                                        break
                                    }
                                    2 -> {
                                        println("처음화면으로 돌아갑니다.\n")
                                        break
                                    }
                                    else -> {
                                        println("잘못된 입력입니다 다시 입력해주세요.")
                                    }
                                }
                            }

                            waitTime()
                            break
                        }
                        else -> {
                            println("잘못된 입력입니다 다시 입력해주세요.")
                        }
                    }
                }
            }
            4 -> {
                while (true) {
                    menuUI.sideMenu()

                    val selectedSide = readln()
                    when (selectedSide.toIntOrNull() ?: -1) {
                        0 -> break
                        in 1..sideInfo.size -> {
                            while (true) {
                                sideInfo[selectedSide.toInt() - 1].displayInfo()
                                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                                println("1. 확인\t 2. 취소")

                                val check = readln()
                                when (check.toIntOrNull() ?: -1) {
                                    1 -> {
                                        orderReceipt.addMenu(sideInfo[selectedSide.toInt() - 1])
                                        println("${sideInfo[selectedSide.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")
                                        break
                                    }
                                    2 -> {
                                        println("처음화면으로 돌아갑니다.\n")
                                        break
                                    }
                                    else -> {
                                        println("잘못된 입력입니다 다시 입력해주세요.")
                                    }
                                }
                            }

                            waitTime()
                            break
                        }
                        else -> {
                            println("잘못된 입력입니다 다시 입력해주세요.")
                        }
                    }
                }
            }
            5 -> {
                while (true) {
                    if (orderReceipt.orderReceipt.isEmpty()) {
                        println("주문 내역이 없습니다.")

                        waitTime()
                        break
                    }

                    println("아래와 같이 주문 하시겠습니까?")
                    orderReceipt.printOrders()
                    println("1. 주문\t 2. 메뉴판")

                    val selectedOption = readln()
                    when (selectedOption.toIntOrNull() ?: -1) {
                        1 -> {
                            if (checkInspectionTime()) {
                                println("은행 점검 시간은 오후11시 10분 ~ 오후 11시 20분이므로 결제할 수 없습니다.\n")
                            } else {
                                if (coupon && orderReceipt.sum >= 20000) {
                                    println("\n쿠폰을 사용하시겠습니까?")
                                    println("사용하기를 원한다면 1을 눌러주세요.")
                                    val selectedUse = readln()
                                    when (selectedUse.toIntOrNull() ?: -1) {
                                        1 -> {
                                            orderReceipt.sum = (orderReceipt.sum * 0.8).toInt()
                                            coupon = false
                                        }
                                    }
                                }

                                if (currentMoney >= orderReceipt.sum) {
                                    currentMoney -= orderReceipt.sum
                                    println("주문이 완료되었습니다.")
                                    println("남은 잔액은 ${currentMoney}원입니다.")
                                    proceedingOrderList.add(orderReceipt)
                                    orderReceipt = OrderReceipt()
                                } else {
                                    println("현재 잔액은 ${currentMoney}원으로 ${orderReceipt.sum - currentMoney}원이 부족해서 주문할 수 없습니다.")
                                    println("장바구니를 유지합니다.")
                                }
                            }
                        }
                        2 -> {
                            println("장바구니를 유지합니다.")
                        }
                        else -> {
                            println("잘못된 입력입니다 다시 입력해주세요.")
                            continue
                        }
                    }
                    println("처음화면으로 돌아갑니다.\n")

                    waitTime()
                    break
                }
            }
            6 -> {
                while (true) {
                    if (proceedingOrderList.isEmpty()) {
                        println("취소할 주문이 없습니다.\n")

                        waitTime()
                        break
                    }
                    println("취소할 주문을 선택해주세요.")
                    proceedingOrderList.forEachIndexed { index, it ->
                        it.printProceedingOrders(index + 1)
                    }
                    println("0. 뒤로가기")

                    val selectedOption = readln()
                    when (selectedOption.toIntOrNull() ?: -1) {
                        0 -> break
                        in 1..proceedingOrderList.size -> {
                            while (true) {
                                println("정말로 [ 주문 ${selectedOption.toInt()} ]을 취소하시겠습니까?")
                                println("1. 확인\t 2. 취소")

                                val check = readln()
                                when (check.toIntOrNull() ?: -1) {
                                    1 -> {
                                        println("[ 주문 ${selectedOption.toInt()} ]이 취소되었습니다.\n")
                                        println("${proceedingOrderList[selectedOption.toInt() - 1].sum}원이 환불되었습니다.\n")
                                        currentMoney += proceedingOrderList[selectedOption.toInt() - 1].sum
                                        proceedingOrderList.removeAt(selectedOption.toInt() - 1)
                                        println("현재 잔액 : ${currentMoney}원\n")
                                        break
                                    }
                                    2 -> {
                                        break
                                    }
                                    else -> {
                                        println("잘못된 입력입니다 다시 입력해주세요.")
                                    }
                                }
                            }
                        }
                        else -> {
                            println("잘못된 입력입니다 다시 입력해주세요.")
                            continue
                        }
                    }
                    println("처음화면으로 돌아갑니다.\n")

                    waitTime()
                    break
                }
            }
            7 -> {
                if (coupon) {
                    println("이미 쿠폰을 발급받았습니다.")
                } else {
                    println("쿠폰이 발급되었습니다.")
                    coupon = true
                }

                waitTime()
            }
            else -> {
                println("잘못된 입력입니다 다시 입력해주세요.")
            }
        }
    }
    println("프로그램을 종료합니다.")
}

fun checkInspectionTime(): Boolean {
    val curTime = LocalTime.now()
    val startInspectionTime = LocalTime.parse("11:10:00")
    val endInspectionTime = LocalTime.parse("11:20:00")

    println("현재 시각은 ${curTime.format(DateTimeFormatter.ofPattern("HH시 mm분"))}입니다. ")
    return curTime.isAfter(startInspectionTime) && curTime.isBefore(endInspectionTime)
}
