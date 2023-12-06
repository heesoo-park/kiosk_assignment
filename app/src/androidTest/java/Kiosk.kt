package com.example.kioskcli

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Kiosk {
    var itemListList = ArrayList<ItemList>()
    var isTerminate = false
    var basket = ArrayList<Item>()
    var orderList = ArrayList<ArrayList<Item>>()
    var job: Job
    val t1 = Mytime.parseTime("00:10:00")
    val t2 = Mytime.parseTime("23:50:00")

    fun isImpossibleTime(): Boolean = Mytime.nowTime() in t1..t2


    init {
        initItemListList()
        job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                runBlocking {
                    delay(5000)
                }
                println("\t\t\t(현재 주문 대기수: ${orderList.size})")
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
        while (!isTerminate) page_1()
        job.cancel()
        println("프로그램을 종료합니다.")
    }

    fun page_1() {
        println("=================================")
        println("SHAKESHACK 키오스크입니다.")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        println("[ 맘스터치 메뉴 ]")
        itemListList.forEachIndexed { index, itemList ->
            println("${index + 1}. ${itemList.name}\t| ${itemList.detail}")
        }
        if (basket.isNotEmpty()) {
            println("[ 주문 메뉴 ]")
            println("${itemListList.size + 1}. 주문\t\t| 장바구니를 확인 후 주문합니다.")
            println("${itemListList.size + 2}. 취소\t\t| 진행중인 주문을 취소합니다.")
        }
        println("0. 종료\t\t| 프로그램 종료")

        while (true) {
            val s = readln()
            when (val n = s.toIntOrNull() ?: -1) {
                0 -> {
                    isTerminate = true
                    break
                }

                in 1..itemListList.size -> {
                    page_2(itemListList[n - 1])
                    break
                }

                itemListList.size + 1 -> {
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        page_order()
                        break
                    }
                }

                itemListList.size + 2 -> {
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        basket.clear()
                        println("주문을 취소합니다.")
                        wait(1)
                        break
                    }
                }

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    fun page_2(itemList: ItemList) {
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

    fun page_order() {
        val total = basket.fold(0) { acc, item -> acc + item.price }

        println("아래와 같이 주문 하시겠습니까?")
        println("[ 주문 목록 ]")
        basket.sortBy { it.name }
        basket.forEachIndexed { index, item ->
            println("${index + 1}. ${item.info()}")
        }
        println("합계: $total 원")
        println()
        println("1. 주문    2 또는 0. 뒤로가기")

        while (true) {
            val s = readln()
            when (s.toIntOrNull() ?: -1) {
                1 -> {
                    if (Cashcard.money < total) {
                        println("현재 잔액은 ${Cashcard.money}원으로 ${total - Cashcard.money}원이 부족해서 주문할 수 없습니다.")
                        // TODO: 주문 취소
                        println("처음부터 다시 시도해주십시오.")
                        basket.clear()
                    } else {
                        if (isImpossibleTime()) {
                            println("은행 점검 시간은 ${Mytime.timeHHmm(t1)} ~ ${Mytime.timeHHmm(t2)} 이므로 결제할 수 없습니다.")
                        } else {
                            Cashcard.money -= total
                            println("주문되었습니다. 현재 잔액은 ${Cashcard.money}원입니다.")
                            println("(주문 시각: ${Mytime.nowDateTimeFormatted()})")
                            // TODO: 주문 넘겨주는거 괜찮은가?
                            orderList.add(basket)
                            basket = ArrayList<Item>()
                        }
                    }
                    wait(3)
                    break
                }

                2, 0 -> break

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    fun wait(sec: Int) {
        print("... ")
        (sec downTo 1).forEach {
            print("$it ")
//            Thread.sleep(999)
            runBlocking {
                delay(999)
            }
        }
        println()
    }
}
