import MenuInfo.burgerInfo
import MenuInfo.chickenInfo
import MenuInfo.drinkInfo
import MenuInfo.sideInfo

// 메뉴 UI를 담당하는 클래스
class MenuUI {
    // 가장 기본적으로 출력되는 메인 메뉴
    fun mainMenu() {
        println("[맘스터치 메뉴 카테고리]")
        println("1. 버거\t | 다양한 버거")
        println("2. 치킨\t | 다양한 치킨")
        println("3. 음료\t | 다양한 음료")
        println("4. 사이드\t | 다양한 사이드")
        println("7. 쿠폰 발급\t | 주문금액이 20000원 이상일 때 사용가능한 쿠폰 발급")
        println("0. 종료\t | 프로그램 종료")
    }

    // 주문 중이거나 주문이 완료되었을 때 보여지는 주문 메뉴
    fun orderMenu() {
        println("[주문 메뉴 카테고리]")
        println("5. 주문하기\t | 장바구니를 확인 후 주문합니다.")
        println("6. 주문취소\t | 진행중인 주문을 취소합니다.")
    }

    // 배달이 들어왔을 때 보여지는 배달 메뉴
    fun deliveryMenu() {
        println("[배달 메뉴 카테고리]")
        println("8. 배달요청목록\t | 배달 주문을 확인합니다.")
    }

    // 메인 메뉴에서 버거를 선택했을 때 보여지는 버거 메뉴
    fun bergerMenu() {
        println("[버거 메뉴 카테고리]")
        burgerInfo.forEachIndexed { index, burger ->
            print("${index + 1}. ")
            burger.displayInfo()
        }
        println("0. 뒤로가기\t | 뒤로가기")
    }

    // 메인 메뉴에서 치킨을 선택했을 때 보여지는 치킨 메뉴
    fun chickenMenu() {
        println("[치킨 메뉴 카테고리]")
        chickenInfo.forEachIndexed { index, chicken ->
            print("${index + 1}. ")
            chicken.displayInfo()
        }
        println("0. 뒤로가기\t | 뒤로가기")
    }

    // 메인 메뉴에서 음료를 선택했을 때 보여지는 음료 메뉴
    fun drinkMenu() {
        println("[음료 메뉴 카테고리]")
        drinkInfo.forEachIndexed { index, drink ->
            print("${index + 1}. ")
            drink.displayInfo()
        }
        println("0. 뒤로가기\t | 뒤로가기")
    }

    // 메인 메뉴에서 사이드를 선택했을 때 보여지는 사이드 메뉴
    fun sideMenu() {
        println("[사이드 메뉴 카테고리]")
        sideInfo.forEachIndexed { index, side ->
            print("${index + 1}. ")
            side.displayInfo()
        }
        println("0. 뒤로가기\t | 뒤로가기")
    }
}