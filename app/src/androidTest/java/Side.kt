// 사이드 메뉴들을 묶어놓는 클래스
class Side(name: String, price: Int,desc: String): Menu(name, price, desc) {
    // 사이즈에 대한 가격 차이를 출력
    override fun comparePrice() {
        println("중 사이즈는 ${price}원이고 대 사이즈는 ${price + 2000}원입니다.")
    }
}