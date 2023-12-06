class Side(name: String, price: Int,desc: String): Item(name, price, desc) {
    override fun comparePrice() {
        println("중 사이즈는 ${price}원이고 대 사이즈는 ${price + 2000}원입니다.")
    }
}