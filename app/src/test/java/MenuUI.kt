class MenuUI {
    fun mainMenu(){
        println("[ MOM'S TOUCH ]")
        println("1. Burgers     | 햄버거")
        println("2. Chickens    | 치킨")
        println("3. Drinks      | 음료")
        println("4. SideMenu    | 사이드메뉴")
        println("0. 종료         | 프로그램 종료")
    }
    fun subMenu(){
        println("[ ORDER MENU ]")
        println("5. Order      | 장바구니를 확인 후 주문합니다.")
        println("6. Cancle     | 진행중인 주문을 취소합니다.")
        println("7. Coupon     | 할인권 발급하기.")
    }
    fun deliveryMenu(){
        println("8.배달목록 확인  | 배달내역 확인")
    }
    fun burgerMenu(){
        println("[ Bergers Menu ]")
        println("1. 싸이버거       | W4,600 | 바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 맘스터치 시그니처 버거.")
        println("2. 인크레더블버거  | W5,700 | 프리미엄 더블햄, 에그프라이, 통다리살 패티에 아삭아삭한 양상추와 양파까지, 풍성한 버거.")
        println("3. 화이트갈릭버거  | W5,200 | BEST 화이트갈릭이 싸이버거로 재탄생! 더블햄, 통다리살, 화이트갈릭소스의 환상 조합")
        println("0. 뒤로가기       | 뒤로가기")
    }

    fun chickenMenu(){
        println("[ Chickens Menu ]")
        println("1. 후라이드      | W11,900 | 케이준 양념레시피로 더 바삭하고 스파이시한 치킨")
        println("2. 간장마늘      | W13,900 | 알싸한 마늘 향의 매콤함, 특제 간장소스의 단짠이 조화로운 치킨")
        println("3. 맘스양념      | W13,900 | 국내산 벌꿀이 함유된 매콤달콤 특제 양념소스로 꿀맛나는 치킨")
        println("0. 뒤로가기      | 뒤로가기")
    }

    fun drinkMenu(){
        println("[ Drinks Menu ]")
        println("1. 콜라     | W1,600 |     톡쏘는 콜라")
        println("2. 사이다   | W1,600 |     톡쏘는 사이다")
        println("3. 제로콜라  | W1,600 |     톡쏘는 제로콜라")
        println("0. 뒤로가기")
    }
    fun sideMenu(){
        println("[ Side Menu ]")
        println("1. 치즈스틱           | W2,000 |    치즈가 듬뿍 들어간 치즈스틱")
        println("2. 양념감자(중)        | W2,000 |    감자에 섞어먹는 양념")
        println("3. 할라피뇨너겟(4조각)  | W2,000 |    맛있는 너겟")
        println("4. 양념감자(대)        | W3,500 |    감자에 섞어먹는 양념")
        println("5. 할라피뇨너겟(10조각) | W4,500 |    맛있는 너겟")
        println("0. 뒤로가기")
    }
}