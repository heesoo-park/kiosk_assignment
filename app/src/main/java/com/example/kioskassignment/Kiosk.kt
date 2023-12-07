package com.example.kioskassignment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.round
import kotlin.random.Random

class Kiosk {
    var itemListList = ArrayList<ItemList>()
    var isTerminate = false
    var basket = ArrayList<Item>()
    var basket_discount = 1.0  // TODO: 장바구니 클래스를 만들어야 하지만 일단 생략
    var basket_forDiscount = 0x70000000
    var orderList = ArrayList<ArrayList<Item>>()
    var deliveryList = ArrayList<DeliveryOrder>()
    var jobCurrentOrderCount: Job
    var jobRandomDelivery: Job
    val t1 = Mytime.parseTime("23:30:00")
    val t2 = Mytime.parseTime("23:50:00")

    fun isImpossibleTime(): Boolean = Mytime.nowTime() in t1..t2


    init {
        initItemListList()
        basket_init()
        jobCurrentOrderCount = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                runBlocking {
                    delay(10000L)  // 5초는 너무 자주 뜬다... 10초로.
                }
                println("\t\t\t(현재 주문 대기수: ${orderList.size})")
            }
        }
        // TODO: 배달 주문 요청은 외부에서 들어오는게 맞는데 그냥 작성.
        jobRandomDelivery = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                runBlocking {
                    delay(8500L + Random.nextLong(0, 1501L))
                }
                var flag = false
                for (i in 1..Random.nextInt(3)) {  // n초마다 배달 주문 0~2개 랜덤으로 들어옴
                    flag = true
                    // 대한민국 위도: 33~43, 경도: 124~132
                    val altitude = round(Random.nextDouble(33.0, 43.0) * 100) / 100
                    val longitude = round(Random.nextDouble(124.0, 132.0) * 100) / 100
                    val menus = ArrayList<Item>().apply {
                        for (i in 0..Random.nextInt(6)) add(  // 메뉴 1~n개 랜덤
                            with(itemListList[Random.nextInt(itemListList.size)]) {
                                this[Random.nextInt(this.size())]
                            }
                        )
                    }
                    menus.sortBy { it.name }
                    deliveryList.add(DeliveryOrder(altitude, longitude, menus))
                }
                if (flag) println("\t\t\t(배달 접수되었습니다. 현재 배달 대기수: ${deliveryList.size})")
                else println("\t\t\t(현재 배달 대기수: ${deliveryList.size})")
            }
        }
    }

    fun initItemListList() {
        itemListList.add(ItemList("버거류", "버거 맛집입니다.", createBurgers()))
        itemListList.add(ItemList("치킨류", "치킨도 팝니다.", createChickens()))
        itemListList.add(ItemList("음료류", "시원한 음료 있습니다.", createDrinks()))
        itemListList.add(ItemList("사이드류", "사이드 없으면 섭합니다.", createSides()))
    }

    fun run() {
        while (!isTerminate) page_first()
        jobCurrentOrderCount.cancel()
        jobRandomDelivery.cancel()
        println("프로그램을 종료합니다.")
    }

    fun page_first() {
        println("=================================")
        println("맘스터치 키오스크입니다.")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        println("[ 맘스터치 메뉴 ]")
        itemListList.forEachIndexed { index, itemList ->
            println("${index + 1}. ${itemList.name}\t| ${itemList.detail}")
        }
        if (basket.isNotEmpty()) {
            println("[ 장바구니 메뉴 ]")
            println("${itemListList.size + 1}. 주문\t\t| 장바구니를 확인 후 주문합니다.")
            println("${itemListList.size + 2}. 쿠폰 적용\t\t| 쿠폰 바코드 또는 큐알코드를 스캔합니다.")
            println("${itemListList.size + 3}. 취소\t\t| 진행중인 주문을 취소합니다.")
        }
        println("[ 직원용 메뉴 ]")
        println("8. 잔액 조회\t| 카드 잔액을 확인합니다.")
        println("9. 배달 요청 목록\t| 들어온 배달 주문의 목록을 확인합니다.")
        println("0. 종료\t\t| 프로그램 종료")

        while (true) {
            val s = readln()
            when (val n = s.toIntOrNull() ?: -1) {
                0 -> {
                    isTerminate = true
                    break
                }

                in 1..itemListList.size -> {
                    page_itemSelect(itemListList[n - 1])
                    break
                }

                itemListList.size + 1 -> {
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        page_basket()
                        break
                    }
                }

                itemListList.size + 2 -> {
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        println("쿠폰 바코드 또는 큐알코드를 스캔해주세요.")
                        println("(스캔 중)")
                        wait(1)
                        basket_discount = 0.1
                        basket_forDiscount = 30000
                        println("3만원 이상 결제 시 90% 할인 쿠폰이 적용되었습니다.")
                        wait(3)
                        break
                    }
                }

                itemListList.size + 3 -> {
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        basket_init()
                        println("주문을 취소합니다.")
                        wait(1)
                        break
                    }
                }

                8 -> println("카드 잔액: ${Cashcard.money}")

                9 -> {
                    page_deliveryCheck()
                    break
                }

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    fun page_itemSelect(itemList: ItemList) {
        println("[ ${itemList.name} 메뉴 ]")
        itemList.lst.forEachIndexed { index, item ->
            println("${index + 1}. ${item.info()}")
        }
        println("0. 뒤로가기")

        while (true) {
            val s = readln()
            when (val n = s.toIntOrNull() ?: -1) {
                0 -> break
                in 1..itemList.size() -> {
                    page_AddBasket(itemList[n - 1])
                    break
                }

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    fun page_AddBasket(item: Item) {
        println("\"${item.info()}\"")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("현재 합계: ${basket.sumOf { it.price }}")
        println("1. 확인    2. 취소")

        while (true) {
            val s = readln()
            when (s.toIntOrNull() ?: -1) {
                1 -> {
                    basket.add(item)
                    println("${item.name} 가 장바구니에 추가되었습니다.")
                    println("현재 합계: ${basket.sumOf { it.price }}")
                    wait(1)
                    break
                }

                2 -> {
                    println("취소되었습니다.")
                    wait(1)
                    break
                }

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    fun page_basket() {
        val total = basket.fold(0) { acc, item -> acc + item.price }
        val total_discounted = (total * basket_discount).toInt()

        println("아래와 같이 주문 하시겠습니까?")
        println("[ 장바구니 ]")
        basket.sortBy { it.name }
        basket.forEachIndexed { index, item ->
            println("${index + 1}. ${item.info()}")
        }
        println("합계: $total 원")
        if (basket_forDiscount <= total) {
            println("주문 금액이 ${basket_forDiscount}원 이상이므로 할인 쿠폰 적용되어 ${total_discounted}원이 결제됩니다.")
        }
        println()
        println("1. 주문    2 또는 0. 뒤로가기")

        while (true) {
            val s = readln()
            when (s.toIntOrNull() ?: -1) {
                1 -> {
                    if (isImpossibleTime()) {
                        println("은행 점검 시간은 ${Mytime.timeHHmm(t1)} ~ ${Mytime.timeHHmm(t2)} 이므로 결제할 수 없습니다.")
                        wait(3)
                        break
                    }

                    if (Cashcard.money < total) {
                        println("현재 잔액은 ${Cashcard.money}원으로 ${total - Cashcard.money}원이 부족해서 주문할 수 없습니다.")
                        // TODO: 개별 주문 취소
                        println("처음부터 다시 시도해주십시오.")
                        basket_init()
                        wait(3)
                        break
                    }

                    Cashcard.money -= (total * basket_discount).toInt()
                    println("주문되었습니다. 현재 잔액은 ${Cashcard.money}원입니다.")
                    println("(주문 시각: ${Mytime.nowDateTimeFormatted()})")
                    // TODO: 주문 넘겨주는거 괜찮은가?
                    orderList.add(basket)
                    basket_init()

                    wait(3)
                    break
                }

                2, 0 -> break

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    fun page_deliveryCheck() {
        println("[ 배달 주문 목록 ]")
        deliveryList.forEachIndexed { index, items ->
            println("[$index] 총 금액: ${items.total()}, 위도: ${items.latitude}, 경도: ${items.longitude}")
            items.lst.forEach { println(it.info()) }
        }
        // TODO: 번호 선택해서 배달 처리한 건 제거 기능
        println("(엔터를 누루면 뒤로 갑니다)")

        while (true) {
            val s = readln()
            break
//            when (s.toIntOrNull() ?: -1) {
//                1 -> {
//                    println("배달 주문 선택 제거 기능")
//                    break
//                }
//
//                else -> println("잘못된 입력입니다: $s")
//            }
        }
    }

    fun wait(sec: Int) {
        runBlocking { delay(999L) }
        if (sec <= 1) return
        print(" ... ")
        (sec - 1 downTo 1).forEach {
            print("$it ")
//            Thread.sleep(999L)
            runBlocking { delay(999L) }
        }
        println()
    }

    fun basket_init() {
        basket = ArrayList<Item>()  // 가비지 컬렉터 알아서 돌아갈 거라고 믿는다
        basket_discount = 1.0
        basket_forDiscount = 0x70000000
    }
}
