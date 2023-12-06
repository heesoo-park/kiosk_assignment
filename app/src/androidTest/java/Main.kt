import MenuInfo.burgerInfo

fun isInteger(s: String): Boolean {
    return try {
        s.toInt()
        true
    } catch (e: Exception) {
        false
    }
}

fun isInOption(s: String, start: Int, end: Int): Boolean {
    return s.toInt() in start..end
}

fun main() {
    val menuUI = MenuUI()

    while (true) {
        println("안녕하세요 맘스터치입니다.")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n")
        menuUI.mainMenu()

        val menu = readln()
        if (!isInteger(menu)) {
            System.err.println("메뉴 입력은 숫자만 가능합니다.")
            continue
        } else if (!isInOption(menu, 0, 4)) {
            System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
            continue
        }

        when (menu.toInt()) {
            1 -> {
                while (true) {
                    menuUI.bergerMenu()

                    val selectedBurger = readln()
                    if (!isInteger(selectedBurger)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedBurger.toInt()) {
                        0 -> break
                        in 1..burgerInfo.size -> {
                            burgerInfo[selectedBurger.toInt() - 1].displayInfo()
                            println("위 메뉴를 장바구니에 추가하시겠습니까?")
                            println("1. 확인\t 2. 취소")
                            val check = readln()

                            if (!isInteger(menu)) {
                                System.err.println("입력은 숫자만 가능합니다.")
                            } else if (!isInOption(menu, 1, 2)) {
                                System.err.println("잘못된 번호를 입력했어요 다시 입력해주세요.")
                            }

                            when (check.toInt()) {
                                1 -> {
                                    println("${burgerInfo[selectedBurger.toInt() - 1].name}가 장바구니에 추가되었습니다.\n")
                                    break
                                }
                                2 -> {
                                    println("처음화면으로 돌아갑니다.\n")
                                    break
                                }
                            }
                        }
                    }
                }
            }
            2 -> {
                while (true) {
                    menuUI.chickenMenu()

                    val selectedChicken = readln()
                    if (!isInteger(selectedChicken)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedChicken.toInt()) {
                        0 -> break
                    }
                }
            }
            3 -> {
                while (true) {
                    menuUI.drinkMenu()

                    val selectedDrink = readln()
                    if (!isInteger(selectedDrink)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedDrink.toInt()) {
                        0 -> break
                    }
                }
            }
            4 -> {
                while (true) {
                    menuUI.sideMenu()

                    val selectedSide = readln()
                    if (!isInteger(selectedSide)) {
                        System.err.println("메뉴 입력은 숫자만 가능합니다.")
                        continue
                    }

                    when (selectedSide.toInt()) {
                        0 -> break
                    }
                }
            }
            0 -> break
        }
    }

    println("프로그램을 종료합니다.")
}