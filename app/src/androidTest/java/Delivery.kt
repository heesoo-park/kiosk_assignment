// Delivery 클래스는 Receipt 클래스를 상속받으면서 추가로 위도와 경도 정보를 받음
class Delivery: Receipt() {
    // 위도
    var altitude: Double = 0.0
    // 경도
    var longitude: Double = 0.0

    // 위도, 경도를 받는 함수
    fun addPosition(x: Double, y: Double) {
        altitude = x
        longitude = y
    }

    // Receipt 클래스의 함수를 오버라이딩하여 위도 경도 출력 부분과 총 금액 부분 추가
    override fun printProceedingOrders(num: Int) {
        println("[ 주문 ${num} ]")
        println("[ 위도 : ${altitude}, 경도 : ${longitude} ]")
        receipt.forEachIndexed { index, it ->
            println("%d. %-8s\t| %7s\t| %s".format(index + 1, it.name, it.price, it.desc))
        }
        println("\n[ 총 금액 ]")
        println("${sum}원\n")
    }
}