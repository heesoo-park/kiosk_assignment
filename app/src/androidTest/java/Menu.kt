// 모든 메뉴들을 아우르는 상위 클래스
open class Menu(name: String, price: Int, desc: String) {
    // 메뉴 이름
    val name: String
    // 메뉴 가격
    val price: Int
    // 메뉴 설명
    val desc: String

    init {
        this.name = name
        this.price = price
        this.desc = desc
    }

    // 메뉴 하나에 대한 내용을 출력
    fun displayInfo() {
        println("${name}\t | W ${price}\t | ${desc}")
    }
}