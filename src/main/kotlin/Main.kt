fun main(args: Array<String>) {
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
    var isInsideGenerated: Boolean
    for (i in 0..list.size-1){
        isInsideGenerated=false
        for (j in 0..generated.size-1){
            if(list[i] == generated[j]){
                isInsideGenerated = true
            }
            if(isInsideGenerated){
                if(i==j){
                    wellPlaceds++
                }else{
                    wrongplaceds++
                }
                break;
            }
            j.inc()
        }
        i.inc()
    }
    return " Il y a $wellPlaceds éléments bien placés et $wrongplaceds éléments mal placés"
}
fun generateList(): ArrayList<Int>{
    var randoms= ArrayList<Int>()

    for (i in 0..3){
        randoms.add((0..6).random())
        i.inc()
    }
    return randoms
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
fun isFormatCorrect(charSequence: CharSequence):Boolean{
    val regex="^[0-9] [0-9] [0-9] [0-9]$".toRegex()

    return regex.containsMatchIn(charSequence)
}