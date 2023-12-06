class Burger(name: String, price: Int, desc: String): Item(name, price, desc) {
    override fun comparePrice() {
        println("버거 단품은 ${price}원이고 버거 세트는 ${price + 2300}원입니다.")
    }
}