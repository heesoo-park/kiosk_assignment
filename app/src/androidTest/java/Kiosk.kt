import MenuInfo.totalInfo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.round
import kotlin.random.Random

@OptIn(DelicateCoroutinesApi::class)
class Kiosk {
    // 메뉴 UI 객체
    val menuUI = MenuUI()
    // 주문 영수증 객체
    var orderReceipt = OrderReceipt()
    // 진행중인(준비중인) 주문 리스트 객체
    val proceedingOrderList = mutableListOf<OrderReceipt>()
    // 배달 영수증 객체
    var deliveryReceipt = DeliveryReceipt()
    // 진행중인(준비중인) 배달 리스트 객체
    val proceedingDeliveryList = mutableListOf<DeliveryReceipt>()

    // 사용자의 현재 잔액
    var currentMoney = (30000..50000).random() / 100 * 100
    // 사용자의 쿠폰 발급 여부
    var coupon = false

    var isTerminate = false

    // 프로그램이 시작할 때부터
    init {
        // 프로그램이 종료할 때까지 5초마다 현재 주문 대기수와 배달 대기수를 보여주는 코루틴 함수
        GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                delay(5000)
                printOrderDelivery()
            }
        }

        // 프로그램이 종료할 때까지 10초마다 랜덤한 배달 주문을 추가하는 코루틴 함수
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                delay(10000)
                checkDelivery()
            }
        }
    }

    /**
     * 코루틴이 3초 delay를 main 함수가 강제로 기다리게 만드는 함수
     */
    fun waitTime() {
        print(" ... ")
        for (i in 1..3) {
            print("$i ")
            runBlocking { delay(999L) }
        }
        println()
    }

    // 메인 함수에서 실행하는 함수
    fun run() {
        menuUI.printTitle()
        // 프로그램의 시작 부분
        while (!isTerminate) page_first()
        println("\n************프로그램을 종료합니다.**************")
    }

    // 메인 페이지
    fun page_first() {
        println("\n***********안녕하세요 맘스터치입니다.************")
        println("***아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.***\n")
        // 기본적으로 메인 메뉴를 출력
        menuUI.mainMenu()
        // 조건이 맞을 때 주문 메뉴와 배달 메뉴를 출력
        if (orderReceipt.receipt.isNotEmpty() || proceedingOrderList.isNotEmpty()) {
            menuUI.orderMenu()
        }
        if (deliveryReceipt.receipt.isNotEmpty() || proceedingDeliveryList.isNotEmpty()) {
            menuUI.deliveryMenu()
        }
        print("-> ")

        while (true) {
            // 메인 메뉴 선택
            val mainChoice = choiceNumber("mainChoice").toString().toInt()
            // toIntOrNull 함수로 예외처리
            when (mainChoice) {
                // 프로그램 종료
                0 -> {
                    isTerminate = true
                    break
                }
                in 1..totalInfo.size -> {
                    page_selectFood(totalInfo[mainChoice - 1])
                    break
                }
                // 주문하기
                5 -> {
                    // 주문 영수증이 비어있는 경우
                    if (orderReceipt.receipt.isEmpty()) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                        break
                    } else {
                        page_orderBasket()
                        break
                    }
                }
                // 주문 취소
                6 -> {
                    // 진행중인 주문 리스트가 비어있는 경우
                    if (proceedingOrderList.isEmpty()) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                        break
                    } else {
                        page_cancelOrder()
                        break
                    }
                }
                // 쿠폰 발급
                7 -> {
                    // 쿠폰이 있을 때
                    if (coupon) {
                        println("이미 쿠폰을 발급받았습니다.")
                    } else { // 쿠폰이 없을 때
                        println("쿠폰이 발급되었습니다.")
                        coupon = true
                    }
                    break
                }
                // 배달 주문 목록
                8 -> {
                    // 진행중인 배달 리스트가 비어있는 경우
                    if (proceedingDeliveryList.isEmpty()) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                        break
                    } else {
                        page_deliveryList()
                        break
                    }
                }
            }
        }

        waitTime()
    }

    // 음식을 선택하는 페이지
    fun page_selectFood(menuList: Pair2) {
        menuUI.printFoodMenu(menuList)
        print("-> ")

        while (true) {
            // 음식 선택
            val foodChoice = choiceNumber("foodChoice").toString().toInt()
            when (foodChoice) {
                // 돌아가기
                0 -> {
                    println("{ 처음화면으로 돌아갑니다. }\n")
                    break
                }
                // 음식 선택 시
                in 1..menuList.info.size -> {
                    page_addBasket(menuList.info[foodChoice - 1])
                    break
                }
                else -> System.err.println("잘못된 입력입니다 다시 입력해주세요.")
            }
        }
    }

    // 음식들을 장바구니에 추가하는 페이지
    fun page_addBasket(food: Food) {
        // 선택된 버거의 정보를 따로 출력
        println("\n=====================================================================================================")
        print("   ")
        food.displayInfo()
        println("=====================================================================================================")
        println("\n******위 메뉴를 장바구니에 추가하시겠습니까?*******")
        println("  1. 확인\t 2. 취소")
        print("-> ")

        while (true) {
            // 추가할 지 선택
            val oneOrTwo = choiceNumber("oneOrTwo").toString().toInt()
            when (oneOrTwo) {
                // 확인을 누른 경우
                1 -> {
                    // 해당 버거를 주문 영수증에 추가
                    orderReceipt.addMenu(food)
                    println("${food.name}가 장바구니에 추가되었습니다.\n")
                    break
                }
                // 취소를 누른 경우
                2 -> {
                    println("{ 처음화면으로 돌아갑니다. }\n")
                    break
                }
            }
        }
    }

    // 장바구니에 넣은 음식을 주문하는 페이지
    fun page_orderBasket() {
        println("***아래와 같이 주문 하시겠습니까?***")
        // 주문 영수증에 들어있는 메뉴들 전체 출력
        orderReceipt.printOrders()
        println("  1. 주문\t 2. 메뉴판")
        print("-> ")

        while (true) {
            // 주문할지 선택
            val oneOrTwo = choiceNumber("oneOrTwo").toString().toInt()
            when (oneOrTwo) {
                // 주문하는 걸 선택한 경우
                1 -> {
                    // 은행 점검시간인지 확인
                    if (checkInspectionTime()) {
                        // 처음화면으로 돌아감
                        println("###은행 점검 시간은 오후11시 10분 ~ 오후 11시 20분이므로 결제할 수 없습니다.\n")
                        println("{ 처음화면으로 돌아갑니다. }\n")
                        break
                    } else {
                        // 쿠폰이 있고 쿠폰 적용 조건에 맞는지 확인
                        if (coupon && orderReceipt.sum >= 20000) {
                            println("\n쿠폰을 사용하시겠습니까?")
                            println("사용하기를 원한다면 1을 눌러주세요.")
                            // 쿠폰 사용 여부 선택
                            val oneOrTwo2 = choiceNumber("oneOrTwo").toString().toInt()
                            when (oneOrTwo2) {
                                // 사용 선택 시
                                1 -> {
                                    // 20퍼센트 할인
                                    orderReceipt.sum = (orderReceipt.sum * 0.8).toInt()
                                    // 쿠폰 제거
                                    coupon = false
                                }
                            }
                        }

                        // 현재 잔액이 주문금액이 크다면
                        if (currentMoney >= orderReceipt.sum) {
                            currentMoney -= orderReceipt.sum
                            println("주문이 완료되었습니다.")
                            println("남은 잔액은 ${currentMoney}원입니다.")
                            // 진행중인 주문 리스트에 추가
                            proceedingOrderList.add(orderReceipt)
                            orderReceipt = OrderReceipt()
                        } else { // 현재 잔액이 주문금액이 작다면
                            println("현재 잔액은 ${currentMoney}원으로 ${orderReceipt.sum - currentMoney}원이 부족해서 주문할 수 없습니다.")
                            println("장바구니를 유지합니다.")
                        }
                        break
                    }
                }
                // 메뉴판 선택 시
                2 -> {
                    println("{ 처음화면으로 돌아갑니다. }\n")
                    break
                }
            }
        }
    }
    // 주문을 취소할 수 있는 페이지
    fun page_cancelOrder() {
        println("***취소할 주문을 선택해주세요.***")
        // 현재 진행중인 주문 리스트에 있는 영수증을 다 보여줌
        proceedingOrderList.forEachIndexed { index, it ->
            it.printProceedingOrders(index + 1)
        }
        println("  0. 뒤로가기")
        print("-> ")

        while (true) {
            // 취소 주문 선택
            val selectedOption = readln()
            when (selectedOption.toIntOrNull() ?: -1) {
                // 뒤로가기
                0 -> break
                // 취소 주문 선택 시
                in 1..proceedingOrderList.size -> {
                    while (true) {
                        println("***정말로 [ 주문 ${selectedOption.toInt()} ]을 취소하시겠습니까?***")
                        println("  1. 확인\t 2. 취소")

                        // 취소 결정
                        val oneOrTwo = choiceNumber("oneOrTwo").toString().toInt()
                        when (oneOrTwo) {
                            // 확인 선택 시
                            1 -> {
                                println("[ 주문 ${selectedOption.toInt()} ]이 취소되었습니다.\n")
                                println("${proceedingOrderList[selectedOption.toInt() - 1].sum}원이 환불되었습니다.\n")
                                // 환불 진행
                                currentMoney += proceedingOrderList[selectedOption.toInt() - 1].sum
                                // 진행중인 주문 리스트에서 제거
                                proceedingOrderList.removeAt(selectedOption.toInt() - 1)
                                println("현재 잔액 : ${currentMoney}원\n")
                                break
                            }
                            // 취소 선택 시
                            2 -> break
                        }
                    }
                }
                else -> {
                    System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                    continue
                }
            }
            println("{ 처음화면으로 돌아갑니다. }\n")
            break
        }
    }
    // 배달 리스트를 출력하는 페이지
    fun page_deliveryList() {
        // 진행중인 배달 리스트에 들어있는 모든 배달 영수증을 출력
        proceedingDeliveryList.forEachIndexed { index, it ->
            it.printProceedingOrders(index + 1)
        }
        println("  0. 뒤로가기")
        print("-> ")

        while (true) {
            // 뒤로가기 선택 여부
            val zeroOrNot = choiceNumber("zeroOrNot").toString().toInt()
            when(zeroOrNot) {
                // 뒤로가기 선택 시
                0 -> {
                    println("{ 처음화면으로 돌아갑니다. }\n")
                    break
                }
            }
        }
    }

    // 현재 주문/배달 대기수 출력
    fun printOrderDelivery() {
        // 현재 주문 대기수
        if (proceedingOrderList.isNotEmpty()) println("\n(현재 주문 대기수 : ${proceedingOrderList.size})")
        else println("\n(현재 주문 대기수 : 0)")
        // 현재 배달 대기수
        if (proceedingDeliveryList.isNotEmpty()) println("(현재 배달 대기수 : ${proceedingDeliveryList.size})\n")
        else println("(현재 배달 대기수 : 0)\n")
    }

    // 랜덤 배달 주문
    fun checkDelivery() {
        // 몇 개의 메뉴를 선택할 건지
        val menus = (1..10).random()
        for (i in 1..menus) {
            // 어떤 메뉴를 선택할 건지
            val type1 = (1..totalInfo.size).random()
            val type2 = (1..totalInfo[type1 - 1].info.size).random()
            // 해당 메뉴를 배달 영수증에 추가
            deliveryReceipt.addMenu(totalInfo[type1 - 1].info[type2 - 1])
        }

        // 위도
        val altitude = round(Random.nextDouble(33.0, 43.0) * 100) / 100
        // 경도
        val longitude = round(Random.nextDouble(124.0, 132.0) * 100) / 100
        // 위도, 경도를 배달 영수증에 추가
        deliveryReceipt.addPosition(altitude, longitude)

        // 배달 영수증을 배달리스트에 추가
        proceedingDeliveryList.add(deliveryReceipt)
        deliveryReceipt = DeliveryReceipt()
        println("\n<<<배달 주문이 들어왔습니다.>>>")
    }

    // 은행 점검시간 확인하는 함수
    fun checkInspectionTime(): Boolean {
        // 현재 시간
        val curTime = LocalTime.now()
        // 은행 점검 시작 시간
        val startInspectionTime = LocalTime.parse("11:10:00")
        // 은행 점검 끝나는 시간
        val endInspectionTime = LocalTime.parse("11:20:00")

        // 현재 시간을 HH시 mm분 형태로 변환해 출력
        println("###현재 시각은 ${curTime.format(DateTimeFormatter.ofPattern("HH시 mm분"))}입니다.###")
        // 현재시간이 은행 점검시간에 포함되는지를 비교해서 반환
        return curTime.isAfter(startInspectionTime) && curTime.isBefore(endInspectionTime)
    }

    // 타입에 맞는 입력값인지 판별하는 함수
    fun choiceNumber(type: String): Int {
        when (type) {
            // 메인 메뉴
            "mainChoice" -> {
                while (true) {
                    try {
                        val mainChoice = readln()
                        val result = mainChoice.toInt()
                        if (result > 9 || result < 0) {
                            System.err.println("범위를 확인하고 다시 입력해주세요")
                        } else {
                            return result
                        }
                    } catch (e: Exception) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                    }
                }
            }
            // 음식 리스트
            "foodChoice" -> {
                while (true) {
                    try {
                        val foodChoice = readln()
                        val result = foodChoice.toInt()
                        if (result > 4 || result < 0) {
                            System.err.println("범위를 확인하고 다시 입력해주세요")
                        } else {
                            return result
                        }
                    } catch (e: Exception) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                    }
                }
            }
            // 1 또는 2 선택
            "oneOrTwo" -> {
                while (true) {
                    try {
                        val oneOrTwo = readln()
                        val result = oneOrTwo.toInt()
                        if (result > 3 || result < 0) {
                            System.err.println("범위를 확인하고 다시 입력해주세요")
                        } else {
                            return result
                        }
                    } catch (e: Exception) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                    }
                }
            }
            // 0 하나만 선택 가능
            "zeroOrNot" -> {
                while (true) {
                    try {
                        val orderOrCancle = readln()
                        val result = orderOrCancle.toInt()
                        if (result > 1 || result < 0) {
                            System.err.println("범위를 확인하고 다시 입력해주세요")
                        } else {
                            return result
                        }
                    } catch (e: Exception) {
                        System.err.println("잘못된 입력입니다 다시 입력해주세요.")
                    }
                }
            }

            else -> {
                return 0
            }
        }
    }

}