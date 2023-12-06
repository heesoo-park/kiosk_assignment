open class Menu(val name:String, val price:Int, val description:String) {
    fun displayInfo(){
        println("[$name] | W$price | $description")
    }
}