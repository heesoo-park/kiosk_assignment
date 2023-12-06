class Side(name:String, price:Int, val description : String): AbstractMenu(name, price){
    override fun displayInfo() {
        println("[$name] | W$price | $description")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("1. 확인       2. 취소")
    }
}