object MenuInfo {
    val burgerInfo = arrayListOf(
        Burger("싸이버거", 4600, "바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 맘스터치 시그니처 버거"),
        Burger("인크레더블버거", 5700, "프리미엄 더블햄, 에그프라이, 통다리살 패티에 아삭아삭한 양상추와 양파까지, 풍성한 버거"),
        Burger("화이트갈릭버거", 5200, "BEST 화이트갈릭이 싸이버거로 재탄생! 더블햄, 통다리살, 화이트갈릭소스의 환상 조합"),
    )

    val chickenInfo = arrayListOf(
        Chicken("후라이드치킨", 11900, "케이준 양념레시피로 더 바삭하고 스파이시한 치킨"),
        Chicken("간장마늘치킨", 13900, "알싸한 마늘 향의 매콤함, 특제 간장소스의 단짠이 조화로운 치킨"),
        Chicken("맘스양념치킨", 13900, "국내산 벌꿀이 함유된 매콤달콤 특제 양념소스로 꿀맛나는 치킨")
    )

    val drinkInfo = arrayListOf(
        Drink("코카콜라", 1600, "시원한 코카콜라"),
        Drink("사이다", 1600, "시원한 사이다"),
        Drink("제로콜라", 1600, "코카아니면 펩시")
    )

    val sideInfo = arrayListOf(
        Side("치즈스틱", 2000, "쭉쭉 늘어나는 치즈스틱"),
        Side("양념감자", 2000, "맘스터치 특유의 양념감자"),
        Side("할라피뇨너겟", 2000, "매콤한 맛이 추가된 치킨너겟")
    )

    val totalInfo = arrayListOf(burgerInfo, chickenInfo, drinkInfo, sideInfo)
}