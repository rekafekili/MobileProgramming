package com.example.maskinfokotlin

fun main() {
    var x = 10 // Int Variable
    var str = "Hello"
    var isMarried = true

    // 값 변경 가능
    x = 20
    str = "World"

    // 반드시 선언과 동시에 초기화를 하는 것이 코틀린 방침.
    var a: Int = 10
    // int, float, double ...
    // Int, Float, Double ...

    // Constant
    val b = 10

    // 값 변경 불가능
//    b = 20

    println("$str World")
    println("${myMethod(3, 4)}")

    val items = arrayOf(1, 2, 3, 4, 5)
    val list = listOf(6, 7, 8, 9, 0) // "List"는 한번 생성되면 원소를 수정할 수 없다.
    val arraylist = arrayListOf(1, 2, 3, 4, 5) // "ArrayList"는 한번 생성되도 수정할 수 있다.
    println(items.toString())

    arraylist.add(6)
    arraylist.remove(4)
    println(arraylist)
    println(arraylist[4])

    val numbers = listOf(1, 2, 3, 4, 5)
    for (i in numbers) {
        when (i) {
            1 -> println("1 입니다.")
            2 -> println("2 입니다.")
            in 3..5 -> println("3 ~ 5 입니다.")
        }
        println(i)
    }

    // Person person = new Person();
    val person = Person("홍길동", 24)
    println(person.name) // person.getName()
    person.name = "sss"
    println(person.name)

    println(person)

    println("alksdnflkjabnsdkjnf kasdf".myLength())

    val item = Item("항아리")
    println(item)
}

fun myMethod(a: Int, b: Int) = a + b

fun noReturnMethod(a: Int, b: Int): Unit {
    // Kotlin Unit 과 Java Void 는 같다.
}

// 확장 함수
fun String.myLength() = this.length

data class Person(
    var name: String,
    var age: Int
)