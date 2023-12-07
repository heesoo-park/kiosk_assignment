// 치킨 메뉴들을 묶어놓는 클래스
class Chicken(name: String, price: Int, desc: String): Menu(name, price, desc) {
    // 순살 기준 가격
    val bonelessPrice = price
    // 뼈 기준 가격
    val bonePrice = price + 5000

    // 사이즈에 대한 가격 차이를 출력
    override fun comparePrice() {
        println("순살 기준, 치킨 단품은 ${bonelessPrice}원이고 치킨 세트는 ${bonelessPrice + 2300}원입니다.")
        println("뼈 기준, 치킨 단품은 ${bonePrice}원이고 치킨 세트는 ${bonePrice + 2300}원입니다.")
    }
}