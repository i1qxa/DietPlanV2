package dietplan.app.oqvvwe.ui

fun main(){
//    val response = "url:location: https://clckto.com/r/cb2d919c-a8a0-46f1-abc5-1d910883347a"
    val response = "url:location: https://clckto.com/r/cb2d919c-a8a0-46f1-abc5-1d910883347a"
    val regex = Regex("""(https://[a-z\d./-]*)""")
    println(regex.find(response, 0)?.value)
}