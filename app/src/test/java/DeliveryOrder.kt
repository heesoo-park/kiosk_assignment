import kotlin.random.Random

data class randomDelivery(
    val menuName: String,
    val menuPrice: Int,
    val latitude: Double,
    val longitude: Double
)

object DeliveryOrder {
    public val myDeliveryOrder: MutableList<randomDelivery> = mutableListOf()
    fun delivery() {
        var food : Food

        //서울 위도경도
        val seoulLatitude = 37.5665
        val seoulLongitude = 126.9780

        //랜덤 위치 생성 범위
        val latitudeRange = 0.1
        val longitudeRange = 0.1

        //랜덤한 위도경도 생성
        val randomLatitude = seoulLatitude + (Random.nextDouble(-latitudeRange, latitudeRange))
        val randomLongitude = seoulLongitude + (Random.nextDouble(-longitudeRange, longitudeRange))

        val menuRandom = (0..3).random()
        val choiceRandom = (0..2).random()
        val sideRandom = (0..4).random()

        when (menuRandom) {
            0 -> {
                food = MenuInfo.burgerInfo[choiceRandom]
                food.deliveryOrder()
                println("주문 위치")
                println("위도 : $randomLatitude, 경도 : $randomLongitude")
                val myorder = randomDelivery(food.name, food.price, randomLatitude, randomLongitude)
                println()
                myDeliveryOrder.add(myorder)

            }

            1 -> {
                food = MenuInfo.chickenInfo[choiceRandom]
                food.deliveryOrder()
                println("주문 위치")
                println("위도 : $randomLatitude, 경도 : $randomLongitude")
                val myorder = randomDelivery(food.name, food.price, randomLatitude, randomLongitude)
                println()
                myDeliveryOrder.add(myorder)
            }

            2 -> {
                food = MenuInfo.drinkInfo[choiceRandom]
                food.deliveryOrder()
                println("주문 위치")
                println("위도 : $randomLatitude, 경도 : $randomLongitude")
                val myorder = randomDelivery(food.name, food.price, randomLatitude, randomLongitude)
                println()
                myDeliveryOrder.add(myorder)
            }

            3 -> {
                food = MenuInfo.sideInfo[sideRandom]
                food.deliveryOrder()
                println("주문 위치")
                println("위도 : $randomLatitude, 경도 : $randomLongitude")
                val myorder = randomDelivery(food.name, food.price, randomLatitude, randomLongitude)
                println()
                myDeliveryOrder.add(myorder)
            }
        }
    }
    //배달내역 확인
    fun deliveryListCheck(){
        for((index, delivery) in myDeliveryOrder.withIndex()){
            println("${index+1}. ${delivery.menuName}, 가격: ${delivery.menuPrice} ,주문위치 - 위도:${delivery.latitude} 경도:${delivery.longitude}")
        }
    }
}




