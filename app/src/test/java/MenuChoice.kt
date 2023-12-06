fun main(){
    val order = Order()
    while(true){
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        println()
        println("[ MOM'S TOUCH ]")
        println("1. Burgers     | 햄버거")
        println("2. Chickens    | 치킨")
        println("3. Drinks      | 음료")
        println("4. SideMenu    | 사이드메뉴")
        println("0. 종료         | 프로그램 종료")
        if(order.myOrders.isNotEmpty()){
            println("[ ORDER MENU ]")
            println("5. Order     | 장바구니를 확인 후 주문합니다.")
            println("6. Cancle    | 진행중인 주문을 취소합니다.")
        }

        val mainChoice = choiceNumber("mainChoice").toString().toInt()
        when(mainChoice){
            1 -> {
                println("[ Bergers Menu ]")
                println("1. 싸이버거       | W4,600 | 바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 맘스터치 시그니처 버거.")
                println("2. 인크레더블버거  | W5,700 | 프리미엄 더블햄, 에그프라이, 통다리살 패티에 아삭아삭한 양상추와 양파까지, 풍성한 버거.")
                println("3. 화이트갈릭버거  | W5,200 | BEST 화이트갈릭이 싸이버거로 재탄생! 더블햄, 통다리살, 화이트갈릭소스의 환상 조합")
                println("0. 뒤로가기       | 뒤로가기")
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    1-> {
                        val burger = Burger("싸이버거", 4600, "바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 맘스터치 시그니처 버거.")
                        burger.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("싸이버거", 4600, "바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 맘스터치 시그니처 버거.")
                                println("주문목록에 ${burger.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    2-> {
                        val burger = Burger("인크레더블버거", 5700, "프리미엄 더블햄, 에그프라이, 통다리살 패티에 아삭아삭한 양상추와 양파까지, 풍성한 버거.")
                        burger.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("인크레더블버거", 5700, "프리미엄 더블햄, 에그프라이, 통다리살 패티에 아삭아삭한 양상추와 양파까지, 풍성한 버거.")
                                println("주문목록에 ${burger.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    3-> {
                        val burger = Burger("화이트갈릭버거", 5200, "BEST 화이트갈릭이 싸이버거로 재탄생! 더블햄, 통다리살, 화이트갈릭소스의 환상 조합")
                        burger.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("화이트갈릭버거", 5200, "BEST 화이트갈릭이 싸이버거로 재탄생! 더블햄, 통다리살, 화이트갈릭소스의 환상 조합")
                                println("주문목록에 ${burger.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                }
            }
            2 -> {
                println("[ Chickens Menu ]")
                println("1. 후라이드      | W11,900 | 케이준 양념레시피로 더 바삭하고 스파이시한 치킨")
                println("2. 간장마늘      | W13,900 | 알싸한 마늘 향의 매콤함, 특제 간장소스의 단짠이 조화로운 치킨")
                println("3. 맘스양념      | W13,900 | 국내산 벌꿀이 함유된 매콤달콤 특제 양념소스로 꿀맛나는 치킨")
                println("0. 뒤로가기      | 뒤로가기")
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    1-> {
                        val chicken = Chicken("후라이드", 11900, "케이준 양념레시피로 더 바삭하고 스파이시한 치킨")
                        chicken.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("후라이드", 11900, "케이준 양념레시피로 더 바삭하고 스파이시한 치킨")
                                println("주문목록에 ${chicken.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    2-> {
                        val chicken = Chicken("간장마늘", 13900, "알싸한 마늘 향의 매콤함, 특제 간장소스의 단짠이 조화로운 치킨")
                        chicken.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("간장마늘", 13900, "알싸한 마늘 향의 매콤함, 특제 간장소스의 단짠이 조화로운 치킨")
                                println("주문목록에 ${chicken.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    3-> {
                        val chicken = Chicken("맘스양념", 13900, "국내산 벌꿀이 함유된 매콤달콤 특제 양념소스로 꿀맛나는 치킨")
                        chicken.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("맘스양념", 13900, "국내산 벌꿀이 함유된 매콤달콤 특제 양념소스로 꿀맛나는 치킨")
                                println("주문목록에 ${chicken.name}를 추가했습니다.")
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
                println("[ Drinks Menu ]")
                println("1. 콜라     | W1,600 |     톡쏘는 콜라")
                println("2. 사이다   | W1,600 |     톡쏘는 사이다")
                println("3. 제로콜라  | W1,600 |     톡쏘는 제로콜라")
                println("0. 뒤로가기")
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    1-> {
                        val drink = Drink("콜라", 1600, "톡쏘는 콜라")
                        drink.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("콜라", 1600, "톡쏘는 콜라")
                                println("주문목록에 ${drink.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    2-> {
                        val drink = Drink("사이다", 1600, "톡쏘는 사이다")
                        drink.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("사이다", 1600, "톡쏘는 사이다")
                                println("주문목록에 ${drink.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    3-> {
                        val drink = Drink("제로콜라", 1600, "톡쏘는 제로콜라")
                        drink.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("제로콜라", 1600, "톡쏘는 제로콜라")
                                println("주문목록에 ${drink.name}를 추가했습니다.")
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
                println("[ Side Menu ]")
                println("1. 치즈스틱     | W2,000 |    치즈가 듬뿍 들어간 치즈스틱")
                println("2. 양념감자     | W2,000 |    감자에 섞어먹는 양념")
                println("3. 할라피뇨너겟  | W2,000 |    맛있는 너겟")
                println("0. 뒤로가기")
                val otherChoice = choiceNumber("otherChoice").toString().toInt()
                when(otherChoice){
                    1-> {
                        val side = Side("치즈스틱", 2000, "치즈가 듬뿍 들어간 치즈스틱")
                        side.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("치즈스틱", 2000, "치즈가 듬뿍 들어간 치즈스틱")
                                println("주문목록에 ${side.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    2-> {
                        val side = Side("양념감자", 2000, "감자에 섞어먹는 양념")
                        side.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("양념감자", 2000, "감자에 섞어먹는 양념")
                                println("주문목록에 ${side.name}를 추가했습니다.")
                            }
                            else -> {
                                println("취소")
                            }
                        }
                    }
                    3-> {
                        val side = Side("할라피뇨너겟", 2000, "맛있는 너겟")
                        side.displayInfo()
                        val orderOrCancle = choiceNumber("orderOrCancle").toString().toInt()
                        when(orderOrCancle){
                            1 -> {
                                order.addOrder("할라피뇨너겟", 2000, "맛있는 너겟")
                                println("주문목록에 ${side.name}를 추가했습니다.")
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
                        order.isMyMoneyOk()
                    }
                    else -> {}
                }
            }
            6 -> {}
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
                    if(result > 6 || result < 0){
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