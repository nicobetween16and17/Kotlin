fun main() {
    println("Début du jeu!")
    var generatedList:List<Int>
    var list=ArrayList<Int>()
    var isOver:Boolean=false
    generatedList=generateList()
    var nbtour=1
    while (!isOver){
        print("Veuillez entrez votre $nbtour proposition (ex: $generatedList: ")
        list = askCombi()
        isOver=isOver(list, generatedList)
        println(resultCombi(list,generatedList))
        nbtour++
    }
    println("vous avez gagner et avec la combinaison $list $generatedList")


}

fun askCombi():ArrayList<Int>{
    var combiList:List<String>
    var arrayCombi=ArrayList<Int>()
    do{
        var combi:CharSequence= readLine()!!
        combiList = combi.split(" ")
        if(!isFormatCorrect(combi)){
            print("Format incorrect...essayez [0-9] [0-9] [0-9] [0-9]: ")
        }
    }while(!isFormatCorrect(combi))

    for (i in 0..combiList.size-1){
        arrayCombi.add(combiList[i].toInt())
        i.inc()
    }
    return arrayCombi
}
fun resultCombi(list:ArrayList<Int>, generated:ArrayList<Int>):String{
    var wellPlaceds = 0
    var wrongplaceds= 0
    var wellplacedList=ArrayList<Int>()
    var wrongplacedList=ArrayList<Int>()
    for (i in 0..list.size-1){
        if(generated.contains(list[i])&&!isAlreadyUsed(wellplacedList,list[i])){
            if(generated[i]==list[i]){
                wellplacedList.add(list[i])
                wellPlaceds++
            }else{
                wrongplacedList.add(list[i])
                wrongplaceds++
            }
        }
        i.inc()
    }
    if(hasDoublon(wrongplacedList)&&wrongplacedList.size>1){
        val wrongrepet=nbRepet(wrongplacedList,finddoublon(wrongplacedList))
        if(wellplacedList.contains(finddoublon(wrongplacedList))){
            wrongplacedList-=wrongrepet
        }
    }


    return " Il y a $wellPlaceds éléments bien placés et $wrongplaceds éléments mal placés"
}
fun isAlreadyUsed(wellplacedList: ArrayList<Int>, position:Int):Boolean{
    if (!wellplacedList.isEmpty()){
        return wellplacedList.contains(position)
    }else return false

}
fun hasDoublon(list: ArrayList<Int>):Boolean{
    var currentnb:Int=0
    for (i in 0..list.size-1){
        var listcopied = list
        currentnb=list[i]
        listcopied.remove(list[i])
        if(listcopied.contains(currentnb)){
            return true
        }

        i.inc()
        println(i)
    }
    return false
}
fun isOver(list: ArrayList<Int>, generated: ArrayList<Int>):Boolean{
    var isOver:Boolean=true
    for(i in 0..generated.size-1){
        if (generated[i] != list[i]){
            isOver=false
        }

    }
    return isOver
}
fun finddoublon(list: ArrayList<Int>):Int {
    var currentnb:Int=0
    if(hasDoublon(list)){
        for (i in 0..list.size-1){
            var listcopied = list
            currentnb=list[i]
            listcopied.remove(list[i])
            if(listcopied.contains(currentnb)){
                return currentnb
            }
        }
    }
    return 0
}
fun nbRepet(list: ArrayList<Int>, number:Int):Int{
    var listcopied = list
    if (listcopied.contains(number) && listcopied.isNotEmpty()){
        listcopied.remove(number)
        nbRepet(listcopied, number)
    }
    return (4-list.size)
}
fun generateList(): ArrayList<Int>{
    var randoms= ArrayList<Int>()

    for (i in 0..3){
        randoms.add((0..6).random())
        i.inc()
    }
    return randoms
}
fun isFormatCorrect(charSequence: CharSequence):Boolean{
    val regex="^[0-9] [0-9] [0-9] [0-9]$".toRegex()
    return regex.containsMatchIn(charSequence)
}



