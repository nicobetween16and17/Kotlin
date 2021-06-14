import java.time.LocalDateTime
import java.util.*

fun main() {
    println("Construisez un étudiant: ")
    var student:Etudiant=Etudiant("      ","         ",0,"                 ","55555555555")
    do{
        println("Prénom: ")
        var name:String=readLine()!!
        println("Nom de famille: ")
        var surname:String=readLine()!!
        println("Age: ")
        var age:Int=readLine()!!.toInt()
        println("Date de naissance(xx/xx/xxxx): ")
        var birthdate:String= readLine()!!
        println("Adresse Mail: ")
        var email:String=readLine()!!
        if(!isEtudiant(name,surname,age,birthdate,email)){
            println("Erreur, veuillez recommencer...")
        }else{
            student = Etudiant(name,surname,age,birthdate,email)
        }
    }while(!isEtudiant(name,surname,age,birthdate,email))

    println("Etudiant correspondant au matricule: ${student.matricule} du nom de ${student.name} ${student.surname}, né le ${student.birthdate}, contactable via l'adresse: ${student.email}")
    println("verification de l'age de l'étudiant ${verifyDate(student.birthdate,student.age)}")
}
class Etudiant(
    var name:String,
    var surname:String,
    var age:Int,
    var birthdate:String,
    var email:String,
    val matricule: String = ""+name[0]+
            name[1]+
            surname[0]+
            surname[1]+
            birthdate[0]+
            birthdate[1]+
            birthdate[3]+
            birthdate[4]+
            birthdate[8]+
            birthdate[9]
)
fun DateCompatible(date:String):Boolean{
    val regex="^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}\$".toRegex()
    return regex.containsMatchIn(date)
}
fun verifyDate(date:String, age:Int):Boolean{
    val now:LocalDateTime= LocalDateTime.now()
    var verification:Boolean=false
    if((""+date[3]+date[4]).toInt()>=now.monthValue){
        if((""+date[0]+date[1]).toInt()<=now.dayOfMonth){
            if(now.year-age==(""+date[6]+date[7]+date[8]+date[9]).toInt()){
                verification=true
            }
        }else{
            if(now.year-age==(""+date[6]+date[7]+date[8]+date[9]).toInt()+1){
                verification=true
            }
        }
    }else{
        if(now.year-age==(""+date[6]+date[7]+date[8]+date[9]).toInt()+1){
            verification=true
        }
    }
    return verification
}
fun isEtudiant(nom:Any,prenom:Any,age:Any,date:Any,mail:Any):Boolean{
    return (nom is String && prenom is String && age is Int && date is String && mail is String)&&DateCompatible(date)
}