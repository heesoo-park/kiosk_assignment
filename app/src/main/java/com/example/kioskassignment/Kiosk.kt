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
    var isTerminate = false

    /** 버거류 등의 ItemList 클래스를 모은 아이템리스트리스트 */
    var itemListList = ArrayList<ItemList>()

    /** 장바구니. 단순히 ArrayList<Item> */
    var basket = ArrayList<Item>()
    var basket_discount = 1.0  // TODO: 장바구니 클래스를 만들어야 하지만 일단 생략
    var basket_forDiscount = 0x70000000

    /** 홀 주문 목록. ArrayList<ArrayList<Item>> */
    var orderList = ArrayList<ArrayList<Item>>()

    /** 배달 주문 목록. ArrayList<DeliveryOrder> */
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
                /** 배달이 새로 들어왔는지 여부 */
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
                    menus.sortBy { it.name }  // 이름 기준 정렬
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

    /** 키오스크의 main같은 함수 */
    fun run() {
        while (!isTerminate) page_first()  // 무한으로 첫 페이지를 띄운다.
        jobCurrentOrderCount.cancel()
        jobRandomDelivery.cancel()
        println("프로그램을 종료합니다.")
    }

    fun page_first() {
//        println("             _____                   __      ____                   ______    _____       __  __      ____       __  __     \n" +
//                " /'\\_/`\\    /\\  __`\\     /'\\_/`\\    /\\ \\    /\\  _`\\                /\\__  _\\  /\\  __`\\    /\\ \\/\\ \\    /\\  _`\\    /\\ \\/\\ \\    \n" +
//                "/\\      \\   \\ \\ \\/\\ \\   /\\      \\   \\ \\/    \\ \\,\\L\\_\\              \\/_/\\ \\/  \\ \\ \\/\\ \\   \\ \\ \\ \\ \\   \\ \\ \\/\\_\\  \\ \\ \\_\\ \\   \n" +
//                "\\ \\ \\__\\ \\   \\ \\ \\ \\ \\  \\ \\ \\__\\ \\   \\/      \\/_\\__ \\                 \\ \\ \\   \\ \\ \\ \\ \\   \\ \\ \\ \\ \\   \\ \\ \\/_/_  \\ \\  _  \\  \n" +
//                " \\ \\ \\_/\\ \\   \\ \\ \\_\\ \\  \\ \\ \\_/\\ \\            /\\ \\L\\ \\                \\ \\ \\   \\ \\ \\_\\ \\   \\ \\ \\_\\ \\   \\ \\ \\L\\ \\  \\ \\ \\ \\ \\ \n" +
//                "  \\ \\_\\\\ \\_\\   \\ \\_____\\  \\ \\_\\\\ \\_\\           \\ `\\____\\                \\ \\_\\   \\ \\_____\\   \\ \\_____\\   \\ \\____/   \\ \\_\\ \\_\\\n" +
//                "   \\/_/ \\/_/    \\/_____/   \\/_/ \\/_/            \\/_____/                 \\/_/    \\/_____/    \\/_____/    \\/___/     \\/_/\\/_/\n" +
//                "                                                                                                                            \n")
//        println(" ,ggg, ,ggg,_,ggg,     _,gggggg,_      ,ggg, ,ggg,_,ggg,        ,gg,          ,ggggggggggggggg   _,gggggg,_      ,ggg,         gg      ,gggg,   ,ggg,        gg \n" +
//                "dP\"\"Y8dP\"\"Y88P\"\"Y8b  ,d8P\"\"d8P\"Y8b,   dP\"\"Y8dP\"\"Y88P\"\"Y8b      i8\"\"8i        dP\"\"\"\"\"\"88\"\"\"\"\"\"\" ,d8P\"\"d8P\"Y8b,   dP\"\"Y8a        88    ,88\"\"\"Y8b,dP\"\"Y8b       88 \n" +
//                "Yb, `88'  `88'  `88 ,d8'   Y8   \"8b,dPYb, `88'  `88'  `88      `8,,8'        Yb,_    88       ,d8'   Y8   \"8b,dPYb, `88        88   d8\"     `Y8Yb, `88       88 \n" +
//                " `\"  88    88    88 d8'    `Ybaaad88P' `\"  88    88    88       `88'          `\"\"    88       d8'    `Ybaaad88P' `\"  88        88  d8'   8b  d8 `\"  88       88 \n" +
//                "     88    88    88 8P       `\"\"\"\"Y8       88    88    88       dP\"8,                88       8P       `\"\"\"\"Y8       88        88 ,8I    \"Y88P'     88aaaaaaa88 \n" +
//                "     88    88    88 8b            d8       88    88    88      dP' `8a               88       8b            d8       88        88 I8'               88\"\"\"\"\"\"\"88 \n" +
//                "     88    88    88 Y8,          ,8P       88    88    88     dP'   `Yb              88       Y8,          ,8P       88        88 d8                88       88 \n" +
//                "     88    88    88 `Y8,        ,8P'       88    88    88 _ ,dP'     I8        gg,   88       `Y8,        ,8P'       88        88 Y8,               88       88 \n" +
//                "     88    88    Y8, `Y8b,,__,,d8P'        88    88    Y8,\"888,,____,dP         \"Yb,,8P        `Y8b,,__,,d8P'        Y8b,____,d88,`Yba,,_____,      88       Y8,\n" +
//                "     88    88    `Y8   `\"Y8888P\"'          88    88    `Y8a8P\"Y88888P\"            \"Y8P'          `\"Y8888P\"'           \"Y888888P\"Y8  `\"Y8888888      88       `Y8\n" +
//                "                                                                                                                                                                \n")
//        println("    ___       ___       ___       ___            ___       ___       ___       ___       ___   \n" +
//                "   /\\__\\     /\\  \\     /\\__\\     /\\  \\          /\\  \\     /\\  \\     /\\__\\     /\\  \\     /\\__\\  \n" +
//                "  /::L_L_   /::\\  \\   /::L_L_   /::\\  \\         \\:\\  \\   /::\\  \\   /:/ _/_   /::\\  \\   /:/__/_ \n" +
//                " /:/L:\\__\\ /:/\\:\\__\\ /:/L:\\__\\ /\\:\\:\\__\\        /::\\__\\ /:/\\:\\__\\ /:/_/\\__\\ /:/\\:\\__\\ /::\\/\\__\\\n" +
//                " \\/_/:/  / \\:\\/:/  / \\/_/:/  / \\:\\:\\/__/       /:/\\/__/ \\:\\/:/  / \\:\\/:/  / \\:\\ \\/__/ \\/\\::/  /\n" +
//                "   /:/  /   \\::/  /    /:/  /   \\::/  /        \\/__/     \\::/  /   \\::/  /   \\:\\__\\     /:/  / \n" +
//                "   \\/__/     \\/__/     \\/__/     \\/__/                    \\/__/     \\/__/     \\/__/     \\/__/  \n")

//        println("_________________________________________________________________________________________________\n" +
//                "    _   _       __      _   _          __        ______      __      _     _      __      _     _\n" +
//                "    /  /|     /    )    /  /|  /     /    )        /       /    )    /    /     /    )    /    / \n" +
//                "---/| /-|----/----/----/| /-|--------\\------------/-------/----/----/----/-----/---------/___ /--\n" +
//                "  / |/  |   /    /    / |/  |         \\          /       /    /    /    /     /         /    /   \n" +
//                "_/__/___|__(____/____/__/___|_____(____/________/_______(____/____(____/_____(____/____/____/____\n" +
//                "                                                                                                 \n")

//        // eftifont
//        println(" _   _   _   _   _  _ __   ___   _   _ _   __  _ _ \n" +
//                "| \\_/ | / \\ | \\_/ |/// _| |_ _| / \\ | | | / _|| U |\n" +
//                "| \\_/ |( o )| \\_/ |  \\_ \\  | | ( o )| U |( (_ |   |\n" +
//                "|_| |_| \\_/ |_| |_|  |__/  |_|  \\_/ |___| \\__||_n_|\n" +
//                "                                                   \n")
//        // nancyj-fancy
//        println("M\"\"\"\"\"`'\"\"\"`YM MMP\"\"\"\"\"YMM M\"\"\"\"\"`'\"\"\"`YM d8 MP\"\"\"\"\"\"`MM    M\"\"\"\"\"\"\"\"M MMP\"\"\"\"\"YMM M\"\"MMMMM\"\"M MM'\"\"\"\"'YMM M\"\"MMMMM\"\"MM \n" +
//                "M  mm.  mm.  M M' .mmm. `M M  mm.  mm.  M 88 M  mmmmm..M    Mmmm  mmmM M' .mmm. `M M  MMMMM  M M' .mmm. `M M  MMMMM  MM \n" +
//                "M  MMM  MMM  M M  MMMMM  M M  MMM  MMM  M .P M.      `YM    MMMM  MMMM M  MMMMM  M M  MMMMM  M M  MMMMMooM M         `M \n" +
//                "M  MMM  MMM  M M  MMMMM  M M  MMM  MMM  M    MMMMMMM.  M    MMMM  MMMM M  MMMMM  M M  MMMMM  M M  MMMMMMMM M  MMMMM  MM \n" +
//                "M  MMM  MMM  M M. `MMM' .M M  MMM  MMM  M    M. .MMM'  M    MMMM  MMMM M. `MMM' .M M  `MMM'  M M. `MMM' .M M  MMMMM  MM \n" +
//                "M  MMM  MMM  M MMb     dMM M  MMM  MMM  M    Mb.     .dM    MMMM  MMMM MMb     dMM Mb       dM MM.     .dM M  MMMMM  MM \n" +
//                "MMMMMMMMMMMMMM MMMMMMMMMMM MMMMMMMMMMMMMM    MMMMMMMMMMM    MMMMMMMMMM MMMMMMMMMMM MMMMMMMMMMM MMMMMMMMMMM MMMMMMMMMMMM \n" +
//                "                                                                                                                        \n")

        // https://snskeyboard.com/asciitext/    글씨체: starwars
        println(
            "" +
                    " .___  ___.   ______   .___  ___.  __     _______.    .___________.  ______    __    __    ______  __    __  \n" +
                    " |   \\/   |  /  __  \\  |   \\/   | (_ )   /       |    |           | /  __  \\  |  |  |  |  /      ||  |  |  | \n" +
                    " |  \\  /  | |  |  |  | |  \\  /  |  |/   |   (----`    `---|  |----`|  |  |  | |  |  |  | |  ,----'|  |__|  | \n" +
                    " |  |\\/|  | |  |  |  | |  |\\/|  |        \\   \\            |  |     |  |  |  | |  |  |  | |  |     |   __   | \n" +
                    " |  |  |  | |  `--'  | |  |  |  |    .----)   |           |  |     |  `--'  | |  `--'  | |  `----.|  |  |  | \n" +
                    " |__|  |__|  \\______/  |__|  |__|    |_______/            |__|      \\______/   \\______/   \\______||__|  |__| \n"
        )
        println("[ 맘스터치 메뉴 ]")
        // 버거류 등의 카테고리를 선택지로 나열
        itemListList.forEachIndexed { index, itemList ->
            println("%d. %-10s\t| %s".format(index + 1, itemList.name, itemList.detail))
        }
        // 장바구니에 아이템이 있을때만 메뉴를 출력. 쿠폰 적용은 장바구니 메뉴에 포함되어있다.
        if (basket.isNotEmpty()) {
            println("[ 장바구니 메뉴 ]")
            println("%-13s\t| %s".format("5. 주문", "장바구니를 확인 후 주문합니다."))
            println("%-13s\t| %s".format("6. 쿠폰 적용", "쿠폰 바코드 또는 큐알코드를 스캔합니다."))
            println("%-13s\t| %s".format("7. 취소", "진행중인 주문을 취소합니다."))
        }
        println("[ 직원용 메뉴 ]")
        println("%-13s\t| %s".format("8. 잔액 조회", "카드 잔액을 확인합니다."))
        println("%-13s\t| %s".format("9. 배달 요청 목록", "들어온 배달 주문의 목록을 확인합니다."))
        println("%-13s\t| %s".format("0. 종료", "프로그램 종료"))

        while (true) {  // 올바른 입력 루프
            val s = readln()
            when (val n = s.toIntOrNull() ?: -1) {
                0 -> {
                    isTerminate = true
                    break
                }

                in 1..itemListList.size -> {  // 1~4 버거류, 치킨류, 음료류, 사이드류
                    // 음식 카테고리 들어가서 메뉴 선택하는 페이지 호출.
                    // 인자로 해당하는 아이템리스트를 넘겨준다.
                    page_itemSelect(itemListList[n - 1])
                    break
                }

                itemListList.size + 1 -> {  // 5. 주문
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        page_basket()  // 장바구니 페이지 호출
                        break
                    }
                }

                itemListList.size + 2 -> {  // 6. 쿠폰 적용
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

                itemListList.size + 3 -> {  // 7. 취소
                    if (basket.isEmpty()) {
                        println("잘못된 입력입니다: $s")
                    } else {
                        basket_init()
                        println("주문을 취소합니다.")
                        wait(1)
                        break
                    }
                }

                8 -> println("카드 잔액: ${Cashcard.money}")  // 8. 잔액 조회

                9 -> {  // 9. 배달 주문 목록
                    page_deliveryCheck()
                    break
                }

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    /** 메뉴 선택 페이지 */
    fun page_itemSelect(itemList: ItemList) {
        println("[ ${itemList.name} 메뉴 ]")
        // 메뉴 나열
        itemList.lst.forEachIndexed { index, item ->
            println("${index + 1}. ${item.info()}")
        }
        println("0. 뒤로가기")

        while (true) {  // 올바른 입력 루프
            val s = readln()
            when (val n = s.toIntOrNull() ?: -1) {
                0 -> break
                in 1..itemList.size() -> {
                    // 장바구니에 추가할지 묻는 페이지 호출.
                    // 인자로 아이템을 넘겨준다.
                    page_AddBasket(itemList[n - 1])
                    break
                }

                else -> println("잘못된 입력입니다: $s")
            }
        }
    }

    /** 장바구니에 추가할지 묻는 페이지 */
    fun page_AddBasket(item: Item) {
        println("\"${item.info()}\"")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("현재 합계: ${basket.sumOf { it.price }}")
        println("1. 확인    2. 취소")

        while (true) {  // 올바른 입력 루프
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

    /** 장바구니 페이지 */
    fun page_basket() {
        val total = basket.fold(0) { acc, item -> acc + item.price }
        val total_discounted = (total * basket_discount).toInt()

        println("아래와 같이 주문 하시겠습니까?")
        println("[ 장바구니 ]")
        basket.sortBy { it.name }  // 이름 기준으로 정렬한다.
        basket.forEachIndexed { index, item ->
            println("${index + 1}. ${item.info()}")
        }
        println("합계: $total 원")
        if (basket_forDiscount <= total) {  // 쿠폰 최소금액 이상이면 적용 금액 출력
            println("주문 금액이 ${basket_forDiscount}원 이상이므로 할인 쿠폰 적용되어 ${total_discounted}원이 결제됩니다.")
        }
        println()
        println("1. 주문    2 또는 0. 뒤로가기")

        while (true) {  // 올바른 입력 루프
            val s = readln()
            when (s.toIntOrNull() ?: -1) {
                1 -> {
                    if (isImpossibleTime()) {  // 은행 점검시간이면 결제 불가
                        println("은행 점검 시간은 ${Mytime.timeHHmm(t1)} ~ ${Mytime.timeHHmm(t2)} 이므로 결제할 수 없습니다.")
                        wait(3)
                        break
                    }

                    // 잔액이 부족하면 장바구니 자체를 폭파시켜버리고 처음으로 보냄.
                    // 개별 주문 취소 기능을 넣으면 좀 더 친절한 키오스크가 된다.
                    if (Cashcard.money < total) {
                        println("현재 잔액은 ${Cashcard.money}원으로 ${total - Cashcard.money}원이 부족해서 주문할 수 없습니다.")
                        // TODO: 개별 주문 취소
                        println("처음부터 다시 시도해주십시오.")
                        basket_init()
                        wait(3)
                        break
                    }

                    Cashcard.money -= (total * basket_discount).toInt()  // 할인율이 적용된 금액 계산
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

    /** 배달 주문 목록 페이지 */
    fun page_deliveryCheck() {
        println("[ 배달 주문 목록 ]")
        // 순서대로 번호 붙이고 금액, 위도, 경도 출력한 다음에 주문 목록 모두 출력.
        deliveryList.forEachIndexed { index, items ->
            println("[$index] 총 금액: ${items.total()}, 위도: ${items.latitude}, 경도: ${items.longitude}")
            items.lst.forEach { println(it.info()) }
        }
        // TODO: 번호 선택해서 배달 처리한 건 제거 기능
        println("(엔터를 누루면 뒤로 갑니다)")

        while (true) {  // 올바른 입력 루프
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

    /** 대기시키는 함수. 최소 1초. 2초 이상은 1초마다 타이머 출력. */
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

    /** 장바구니 초기화 함수 */
    fun basket_init() {
        basket = ArrayList<Item>()  // 가비지 컬렉터 알아서 돌아갈 거라고 믿는다
        basket_discount = 1.0
        basket_forDiscount = 0x70000000
    }
}
