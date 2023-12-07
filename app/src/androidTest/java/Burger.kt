// 버거 메뉴들을 묶어놓는 클래스
class Burger(name: String, price: Int, desc: String): Menu(name, price, desc) {
    // 사이즈에 대한 가격 차이를 출력
    override fun comparePrice() {
        println("버거 단품은 ${price}원이고 버거 세트는 ${price + 2300}원입니다.")
    }
}