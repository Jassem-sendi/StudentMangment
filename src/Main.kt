
enum class CourseType {
    MATH, SCIENCE, LITERATURE
}


data class Course(
    val courseId: Int,
    val courseName: String,
    var grade: Double,
    val courseType: CourseType
)


open class Student(
    val id: Int,
    var name: String,
     var age: Int,
    val courses: MutableList<Course>
) {
    init {
        println("Student created: $name, Age: ${age}")
    }





    lateinit var mentor: String

    companion object {
        var totalStudents: Int = 0


        fun incrementStudentCount() {
            totalStudents++
        }
    }

    init {
        incrementStudentCount()
    }


    inner class Address(var street: String, var city: String, var zipCode: String) {
        fun fullAddress(): String {
            return "$street, $city - $zipCode"
        }
    }
}


object StudentDatabase {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(id: Int, name: String? = null, age: Int? = null, courses: List<Course>? = null) {
        val student = students.find { it.id == id }
        student?.apply {
            name?.let { this.name = it }
            age?.let { this.age = it }
            courses?.let { this.courses.clear(); this.courses.addAll(it) }
        }
    }

    fun retrieveStudent(id: Int): Student? {
        return students.find { it.id == id }
    }

    fun calculateAverageGrade(student: Student): Double {
        return student.courses.map { it.grade }.average()
    }


    val allStudents: List<Student> by lazy {
        println("Loading all students...")
        students.toList()
    }

    fun printAllStudents() {
        for (student in allStudents) {
            println("Student: ${student.name}, Average Grade: ${calculateAverageGrade(student)}")
        }
    }
}


fun main() {
     println("Welcome to Student Management System ")
     println("1-Manage Students\n2-Exit")
     println("Enter your Choice: ")
    val x= readln()
    while (true){
        when(x){
            "2"->break
            "1"->{
                println("1-add Student\n2-update Student \n3-Show All Students")
                println("Enter your Choice: ")
                val x= readln()
                when(x){
                    "1"->{
                        println("Student Name : ")
                        val studentName= readln()
                        println("Student Age : ")
                        val studentAge= readln()
                        println("Choice Student Courses")
                        println("1-Math\n2-SCIENCE\n3-LITERATURE")
                        println("Enter your Choice: ")
                        var x= readln()
                        while (true){
                            if (x=="1" || x=="2"|| x=="3" ){
                                break
                            }
                            else{
                                println("Invalid choice")
                                println("choice again : ")
                                x= readln()
                            }
                        }

                        when(x){
                            "1"->{
                                println("Entre Math Grade For $studentName ")
                                val Note= readln()
                                val course=Course(1,"math",Note.toDouble(), CourseType.MATH)
                            }
                            "2"->{
                                println("Entre SCIENCE Grade For $studentName ")
                                val Note= readln()
                                val course=Course(2,"math",Note.toDouble(), CourseType.SCIENCE)
                            }
                            "3"->{
                                println("Entre LITERATURE Grade For $studentName ")
                                val Note= readln()
                                val course=Course(3,"math",Note.toDouble(), CourseType.LITERATURE)
                            }

                        }
                        val student=Student(Student.incrementStudentCount())



                    }
                    "2"->{

                    }
                    "3"->{

                    }
                }
            }
            else->{
                println("wrong choice")
                break
            }
        }
    }

}
