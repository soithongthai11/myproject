import Constant.Companion.TAG

fun main(args: Array<String>) {
    var obj = myClass()
    myClass.sayHello()
    println("$TAG")
}

class myClass {

    companion object {
        @JvmStatic
        fun sayHello() {
            val listItems = listOf(1, 22, 33, 56, 3)
            for ((index, value) in listItems.withIndex()) {
                println("The element at $index is $value")
            }
        }
    }


}
