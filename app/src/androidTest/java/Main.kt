import MenuInfo.burgerInfo
import MenuInfo.chickenInfo
import MenuInfo.drinkInfo
import MenuInfo.sideInfo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun isInteger(s: String): Boolean {
    return try {
        s.toInt()
        true
    } catch (e: Exception) {
        false
    }
}


fun isInOption(s: String, start: Int, end: Int): Boolean {
    return s.toInt() in start..end
}

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
    val orderReceipt = OrderReceipt()
    val proceedingOrderList = mutableListOf<OrderReceipt>()
    var currentMoney = (30000..50000).random()

    GlobalScope.launch {
        while (true) {
            delay(5000)
            if (proceedingOrderList.isNotEmpty()) println("\n(현재 주문 대기수 : ${proceedingOrderList.size})")
            else println("\n(현재 주문 대기수 : 0)")
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
        if (!isInteger(menu)) {
            System.err.println("메뉴 입력은 숫자만 가능합니다.")
            continue
        }

        if (!isInOption(menu, 0, 4) && orderReceipt.orderReceipt.isEmpty() && proceedingOrderList.isEmpty()) {
            System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
            continue
        } else if (!isInOption(menu, 0, 6)) {
            if (proceedingOrderList.isEmpty()) {
                System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                continue
            }
        }
// toIntOrNull 함수로 교체 + 엘비스 연산자 (-1)
        when (menu.toInt()) {
            0 -> break
            1 -> {
                while (true) {
                    menuUI.bergerMenu()

                    val selectedBurger = readln()
                    if (!isInteger(selectedBurger)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedBurger.toInt()) {
                        0 -> break
                        in 1..burgerInfo.size -> {
                            burgerInfo[selectedBurger.toInt() - 1].displayInfo()
                            println("위 메뉴를 장바구니에 추가하시겠습니까?")
                            println("1. 확인\t 2. 취소")
                            val check = readln()

                            if (!isInteger(check)) {
                                System.err.println("입력은 숫자만 가능합니다.")
                            } else if (!isInOption(check, 1, 2)) {
                                System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                            }

                            when (check.toInt()) {
                                1 -> {
                                    orderReceipt.addMenu(burgerInfo[selectedBurger.toInt() - 1])
                                    println("${burgerInfo[selectedBurger.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")

                                    waitTime()
                                    break
                                }
                                2 -> {
                                    println("처음화면으로 돌아갑니다.\n")
                                    break
                                }
                            }
                        }
                    }
                }
            }
            2 -> {
                while (true) {
                    menuUI.chickenMenu()

                    val selectedChicken = readln()
                    if (!isInteger(selectedChicken)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedChicken.toInt()) {
                        0 -> break
                        in 1..chickenInfo.size -> {
                            chickenInfo[selectedChicken.toInt() - 1].displayInfo()
                            println("위 메뉴를 장바구니에 추가하시겠습니까?")
                            println("1. 확인\t 2. 취소")
                            val check = readln()

                            if (!isInteger(check)) {
                                System.err.println("입력은 숫자만 가능합니다.")
                            } else if (!isInOption(check, 1, 2)) {
                                System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                            }

                            when (check.toInt()) {
                                1 -> {
                                    orderReceipt.addMenu(chickenInfo[selectedChicken.toInt() - 1])
                                    println("${chickenInfo[selectedChicken.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")

                                    waitTime()
                                    break
                                }
                                2 -> {
                                    println("처음화면으로 돌아갑니다.\n")
                                    break
                                }
                            }
                        }
                    }
                }
            }
            3 -> {
                while (true) {
                    menuUI.drinkMenu()

                    val selectedDrink = readln()
                    if (!isInteger(selectedDrink)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedDrink.toInt()) {
                        0 -> break
                        in 1..drinkInfo.size -> {
                            drinkInfo[selectedDrink.toInt() - 1].displayInfo()
                            println("위 메뉴를 장바구니에 추가하시겠습니까?")
                            println("1. 확인\t 2. 취소")
                            val check = readln()

                            if (!isInteger(check)) {
                                System.err.println("입력은 숫자만 가능합니다.")
                            } else if (!isInOption(check, 1, 2)) {
                                System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                            }

                            when (check.toInt()) {
                                1 -> {
                                    orderReceipt.addMenu(drinkInfo[selectedDrink.toInt() - 1])
                                    println("${drinkInfo[selectedDrink.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")

                                    waitTime()
                                    break
                                }
                                2 -> {
                                    println("처음화면으로 돌아갑니다.\n")
                                    break
                                }
                            }
                        }
                    }
                }
            }
            4 -> {
                while (true) {
                    menuUI.sideMenu()

                    val selectedSide = readln()
                    if (!isInteger(selectedSide)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedSide.toInt()) {
                        0 -> break
                        in 1..sideInfo.size -> {
                            sideInfo[selectedSide.toInt() - 1].displayInfo()
                            println("위 메뉴를 장바구니에 추가하시겠습니까?")
                            println("1. 확인\t 2. 취소")
                            val check = readln()

                            if (!isInteger(check)) {
                                System.err.println("입력은 숫자만 가능합니다.")
                            } else if (!isInOption(check, 1, 2)) {
                                System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                            }

                            when (check.toInt()) {
                                1 -> {
                                    orderReceipt.addMenu(sideInfo[selectedSide.toInt() - 1])
                                    println("${sideInfo[selectedSide.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")

                                    waitTime()
                                    break
                                }
                                2 -> {
                                    println("처음화면으로 돌아갑니다.\n")
                                    break
                                }
                            }
                        }
                    }
                }
            }
            5 -> {
                while (true) {
                    println("아래와 같이 주문 하시겠습니까?")
                    orderReceipt.printOrders()
                    println("1. 주문\t 2. 메뉴판")

                    val selectedOption = readln()
                    if (!isInteger(selectedOption)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    if (!isInOption(selectedOption, 1, 2)) {
                        System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                        continue
                    }

                    when (selectedOption.toInt()) {
                        1 -> {
                            if (currentMoney >= orderReceipt.sum) {
                                currentMoney -= orderReceipt.sum
                                println("주문이 완료되었습니다.")
                                println("남은 잔액은 ${currentMoney}원입니다.")
                                val receipt = mutableListOf<Order>()
                                orderReceipt.orderReceipt.forEach {
                                    receipt.add(it)
                                }
                                val totalSum = orderReceipt.sum
                                proceedingOrderList.add(OrderReceipt(receipt, totalSum))
                                orderReceipt.clearOrders()
                            } else {
                                println("현재 잔액은 ${currentMoney}원으로 ${orderReceipt.sum - currentMoney}원이 부족해서 주문할 수 없습니다.")
                                println("장바구니를 유지합니다.")
                            }
                        }
                        2 -> {
                            println("장바구니를 유지합니다.")
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
                        break
                    }
                    println("취소할 주문을 선택해주세요.")
                    proceedingOrderList.forEachIndexed { index, it ->
                        it.printProceedingOrders(index + 1)
                    }
                    println("0. 뒤로가기")

                    val selectedOption = readln()
                    if (!isInteger(selectedOption)) {
                        System.err.println("입력은 숫자만 가능합니다.")
                        continue
                    }

                    if (!isInOption(selectedOption, 0, proceedingOrderList.size)) {
                        System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                        continue
                    }

                    when (selectedOption.toInt()) {
                        0 -> break
                        in 1..proceedingOrderList.size -> {
                            while (true) {
                                println("정말로 [ 주문 ${selectedOption.toInt()} ]을 취소하시겠습니까?")
                                println("1. 확인\t 2. 취소")

                                val check = readln()

                                if (!isInteger(check)) {
                                    System.err.println("입력은 숫자만 가능합니다.")
                                    continue
                                } else if (!isInOption(check, 1, 2)) {
                                    System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                                    continue
                                }

                                when (check.toInt()) {
                                    1 -> {
                                        println("[ 주문 ${selectedOption.toInt()} ]이 취소되었습니다.\n")
                                        println("${proceedingOrderList[selectedOption.toInt() - 1].sum}원이 환불되었습니다.\n")
                                        currentMoney += proceedingOrderList[selectedOption.toInt() - 1].sum
                                        proceedingOrderList.removeAt(selectedOption.toInt() - 1)
                                        println("현재 잔액 : ${currentMoney}원\n")

                                        waitTime()
                                        break
                                    }
                                    2 -> {
                                        break
                                    }
                                }
                            }
                        }
                    }
                    println("처음화면으로 돌아갑니다.\n")

                    waitTime()
                    break
                }
            }
        }
    }
    println("프로그램을 종료합니다.")
}