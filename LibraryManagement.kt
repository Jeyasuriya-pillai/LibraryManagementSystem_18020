class Book(
    val id: Int,
    var name: String,
    var author: String,
    var isIssued: Boolean = false,
    var issuedTo: Int? = null,
    var dueDays: Int = 0
)

class Student(
    val id: Int,
    var name: String
)

class LibrarySystem {

    private val books = mutableListOf<Book>()
    private val students = mutableListOf<Student>()
    private val finePerDay = 5

    // Pre-added data
    init {
        books.add(Book(1, "Java Basics", "James Gosling"))
        books.add(Book(2, "Kotlin Guide", "JetBrains"))
        books.add(Book(3, "Data Structures", "Mark Allen"))
        books.add(Book(4, "Operating System", "Galvin"))
        books.add(Book(5, "DBMS Concepts", "Korth"))

        students.add(Student(1, "Jeyasuriya"))
        students.add(Student(2, "Rajan"))
        students.add(Student(3, "Abhinav"))
    }

    //  Dashboard
    fun dashboard() {
        val totalBooks = books.size
        val issuedBooks = books.count { it.isIssued }
        val availableBooks = totalBooks - issuedBooks
        val totalStudents = students.size

        println("\n+----------------------+----------------+")
        println("|       DASHBOARD      |     COUNT      |")
        println("+----------------------+----------------+")
        println("| Total Books          | ${totalBooks.toString().padEnd(14)}|")
        println("| Total Students       | ${totalStudents.toString().padEnd(14)}|")
        println("| Books Issued         | ${issuedBooks.toString().padEnd(14)}|")
        println("| Books Available      | ${availableBooks.toString().padEnd(14)}|")
        println("+----------------------+----------------+")
    }

    //  Book Management
    fun addBook() {
        print("Enter Book ID: ")
        val id = readLine()!!.toInt()
        print("Enter Book Name: ")
        val name = readLine()!!
        print("Enter Author: ")
        val author = readLine()!!

        books.add(Book(id, name, author))
        println("Book Added")
    }

    fun viewBooks() {
        println("\n+----+----------------------+----------------------+-----------+")
        println("| ID | Name                 | Author               | Status    |")
        println("+----+----------------------+----------------------+-----------+")

        for (b in books) {
            val status = if (b.isIssued) "Issued" else "Available"
            println("| ${b.id.toString().padEnd(2)} | ${b.name.padEnd(20)} | ${b.author.padEnd(20)} | ${status.padEnd(9)} |")
        }
        println("+----+----------------------+----------------------+-----------+")
    }

    fun searchBook() {
        print("Enter Book Name: ")
        val name = readLine()!!

        val found = books.filter { it.name.contains(name, true) }
        if (found.isEmpty()) println("Not Found")
        else found.forEach { println("Found: ${it.name}") }
    }

    fun updateBook() {
        print("Enter Book ID: ")
        val id = readLine()!!.toInt()

        val book = books.find { it.id == id }
        if (book != null) {
            print("New Name: ")
            book.name = readLine()!!
            print("New Author: ")
            book.author = readLine()!!
            println("Updated")
        } else println("Not Found")
    }

    fun deleteBook() {
        print("Enter Book ID: ")
        val id = readLine()!!.toInt()

        val removed = books.removeIf { it.id == id }
        if (removed) println("Deleted") else println("Not Found")
    }

    //  Student Management
    fun addStudent() {
        print("Enter Student ID: ")
        val id = readLine()!!.toInt()
        print("Enter Name: ")
        val name = readLine()!!

        students.add(Student(id, name))
        println("Student Added")
    }

    fun viewStudents() {
        println("\n+----+----------------------+")
        println("| ID | Name                 |")
        println("+----+----------------------+")

        for (s in students) {
            println("| ${s.id.toString().padEnd(2)} | ${s.name.padEnd(20)} |")
        }
        println("+----+----------------------+")
    }

    fun searchStudent() {
        print("Enter Name: ")
        val name = readLine()!!

        val found = students.filter { it.name.contains(name, true) }
        if (found.isEmpty()) println("Not Found")
        else found.forEach { println("Found: ${it.name}") }
    }

    fun updateStudent() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()

        val student = students.find { it.id == id }
        if (student != null) {
            print("New Name: ")
            student.name = readLine()!!
            println("Updated")
        } else println("Not Found")
    }

    fun deleteStudent() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()

        val removed = students.removeIf { it.id == id }
        if (removed) println("Deleted") else println("Not Found")
    }

    //  Issue / Return
    fun issueBook() {
        print("Book ID: ")
        val bookId = readLine()!!.toInt()
        print("Student ID: ")
        val studentId = readLine()!!.toInt()
        print("Due Days: ")
        val days = readLine()!!.toInt()

        val book = books.find { it.id == bookId }

        if (book != null && !book.isIssued) {
            book.isIssued = true
            book.issuedTo = studentId
            book.dueDays = days
            println("Issued")
        } else println("Cannot Issue")
    }

    fun returnBook() {
        print("Book ID: ")
        val id = readLine()!!.toInt()

        val book = books.find { it.id == id }

        if (book != null && book.isIssued) {
            val lateDays = (0..10).random()
            val fine = lateDays * finePerDay

            println("Late Days: $lateDays")
            println("Fine: ₹$fine")

            book.isIssued = false
            book.issuedTo = null
            book.dueDays = 0

            println("Returned")
        } else println("Not Issued")
    }

    fun viewIssuedBooks() {
        println("\n+----+----------------------+------------+----------+")
        println("| ID | Name                 | Student ID | Due Days |")
        println("+----+----------------------+------------+----------+")

        for (b in books.filter { it.isIssued }) {
            println("| ${b.id.toString().padEnd(2)} | ${b.name.padEnd(20)} | ${b.issuedTo.toString().padEnd(10)} | ${b.dueDays.toString().padEnd(8)} |")
        }

        println("+----+----------------------+------------+----------+")
    }

    //  Fine Management
    fun calculateFine() {
        print("Enter Late Days: ")
        val days = readLine()!!.toInt()
        println("Fine = ₹${days * finePerDay}")
    }

    fun pendingFines() {
        println("Fine system depends on return time (simulated).")
    }
}

fun main() {

    val lib = LibrarySystem()

    while (true) {

        println("\n+--------------------------------------+")
        println("|        LIBRARY MANAGEMENT MENU       |")
        println("+----+--------------------------------+")
        println("| 1  | Dashboard                      |")
        println("| 2  | Add Book                       |")
        println("| 3  | View Books                     |")
        println("| 4  | Search Book                    |")
        println("| 5  | Update Book                    |")
        println("| 6  | Delete Book                    |")
        println("| 7  | Add Student                    |")
        println("| 8  | View Students                  |")
        println("| 9  | Search Student                 |")
        println("| 10 | Update Student                 |")
        println("| 11 | Delete Student                 |")
        println("| 12 | Issue Book                     |")
        println("| 13 | Return Book                    |")
        println("| 14 | View Issued Books              |")
        println("| 15 | Calculate Fine                 |")
        println("| 16 | Pending Fines                  |")
        println("| 17 | Exit                           |")
        println("+----+--------------------------------+")

        print("Enter Choice: ")

        when (readLine()!!.toInt()) {
            1 -> lib.dashboard()
            2 -> lib.addBook()
            3 -> lib.viewBooks()
            4 -> lib.searchBook()
            5 -> lib.updateBook()
            6 -> lib.deleteBook()
            7 -> lib.addStudent()
            8 -> lib.viewStudents()
            9 -> lib.searchStudent()
            10 -> lib.updateStudent()
            11 -> lib.deleteStudent()
            12 -> lib.issueBook()
            13 -> lib.returnBook()
            14 -> lib.viewIssuedBooks()
            15 -> lib.calculateFine()
            16 -> lib.pendingFines()
            17 -> break
            else -> println("Invalid Choice")
        }
    }
}