open class Item(name: String, price: Int, desc: String) {
    val name: String
    val price: Int
    val desc: String

    init {
        this.name = name
        this.price = price
        this.desc = desc
    }

    fun displayInfo() {
        println("${name}\t | W ${price}\t | ${desc}")
    }

    open fun comparePrice() {
        println("사이즈 별로 가격 차이가 존재합니다.")
    }
}