// 미리 정해놓은 메뉴(음식)들을 모아놓은 object 클래스
// 메뉴 추가가 필요할 시 해당 카테고리 안에 카테고리 클래스로 인스턴스를 만들면 됨
// 카테고리 추가가 필요할 시 Menu를 상속받는 카테고리 클래스를 생성하고 아래와 같이 리스트를 구성하면 됨
object MenuInfo {
    // 버거 메뉴 정보
    val burgerInfo = arrayListOf(
        Food("싸이버거   ", 4600, "바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 맘스터치 시그니처 버거"),
        Food("인크레더블버거 ", 5700, "프리미엄 더블햄, 에그프라이, 통다리살 패티에 아삭아삭한 양상추와 양파까지, 풍성한 버거"),
        Food("화이트갈릭버거 ", 5200, "BEST 화이트갈릭이 싸이버거로 재탄생! 더블햄, 통다리살, 화이트갈릭소스의 환상 조합"),
    )

    // 치킨 메뉴 정보
    val chickenInfo = arrayListOf(
        Food("후라이드치킨  ", 11900, "케이준 양념레시피로 더 바삭하고 스파이시한 치킨"),
        Food("간장마늘치킨  ", 13900, "알싸한 마늘 향의 매콤함, 특제 간장소스의 단짠이 조화로운 치킨"),
        Food("맘스양념치킨  ", 13900, "국내산 벌꿀이 함유된 매콤달콤 특제 양념소스로 꿀맛나는 치킨")
    )

    // 음료 메뉴 정보
    val drinkInfo = arrayListOf(
        Food("코카콜라   ", 1600, "시원한 코카콜라"),
        Food("사이다    ", 1600, "시원한 사이다"),
        Food("제로콜라   ", 1600, "코카아니면 펩시")
    )

    // 사이드 메뉴 정보
    val sideInfo = arrayListOf(
        Food("치즈스틱   ", 2000, "쭉쭉 늘어나는 치즈스틱"),
        Food("양념감자   ", 2000, "맘스터치 특유의 양념감자"),
        Food("할라피뇨너겟 ", 2000, "매콤한 맛이 추가된 치킨너겟")
    )

    // 모든 메뉴 정보 리스트
    val totalInfo = arrayListOf(Pair2("버거 ", burgerInfo), Pair2("치킨 ", chickenInfo), Pair2("음료 ", drinkInfo), Pair2("사이드", sideInfo))
}

// 타입과 리스트를 동시에 저장하기 위한 data class
// 수정이 가능하게 var 타입으로 지정
data class Pair2(var type: String, var info: ArrayList<Food>)