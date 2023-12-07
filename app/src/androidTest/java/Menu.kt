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

    // 사이즈에 대한 가격 차이를 출력
    // 오버라이딩할 수 있게 open 키워드를 붙여놓음
    // 현재 코드 상에서는 사용하지 않고 있음
    open fun comparePrice() {
        println("사이즈 별로 가격 차이가 존재합니다.")
    }
}