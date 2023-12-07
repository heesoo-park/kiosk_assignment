open class Menu(val name:String, val price:Int, val description:String) {
    fun displayInfo(){
        println("[$name] | W$price | $description")
    }
    fun displayInfoSetMenu(){
        println("[$name] | W$price | $description")
        println("세트 주문시 [양념감자], [코카콜라] 추가.")
        println("세트 주문시 가격 : W${price+2300}")
    }

    fun deliveryOrder(){
        println("배달 주문이 들어왔습니다.")
        println("상품명: $name           가격: $price")

    }

}