class Chicken(name: String, price: Int, desc: String): Item(name, price, desc) {
    val bonelessPrice = price
    val bonePrice = price + 5000

    override fun comparePrice() {
        println("순살 기준, 치킨 단품은 ${bonelessPrice}원이고 치킨 세트는 ${bonelessPrice + 2300}원입니다.")
        println("뼈 기준, 치킨 단품은 ${bonePrice}원이고 치킨 세트는 ${bonePrice + 2300}원입니다.")
    }
}