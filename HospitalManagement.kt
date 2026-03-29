// Data Classes
class Patient(val id: Int, var name: String, var age: Int)
class Doctor(val id: Int, var name: String, var dept: String)
class Appointment(val id: Int, var patientId: Int, var doctorId: Int)
class Bill(val id: Int, var patientId: Int, var amount: Double, var status: String)
class Staff(val id: Int, var name: String)
class Medicine(val id: Int, var name: String, var stock: Int)

class HospitalSystem {

    private val patients = mutableListOf<Patient>()
    private val doctors = mutableListOf<Doctor>()
    private val appointments = mutableListOf<Appointment>()
    private val bills = mutableListOf<Bill>()
    private val staffList = mutableListOf<Staff>()
    private val medicines = mutableListOf<Medicine>()

    init {
        patients.add(Patient(1, "Rahul", 22))
        patients.add(Patient(2, "Abhinav", 30))

        doctors.add(Doctor(1, "Dr. Sharma", "Cardiology"))
        doctors.add(Doctor(2, "Dr. Mehta", "Neurology"))

        staffList.add(Staff(1, "Nurse A"))
        staffList.add(Staff(2, "Ward Boy B"))

        medicines.add(Medicine(1, "Paracetamol", 100))
        medicines.add(Medicine(2, "Crocin", 50))
    }

    // Dashboard
    fun dashboard() {
        val totalRevenue = bills.sumOf { it.amount }

        println("\n+----------------------+----------------+")
        println("|      DASHBOARD       |     COUNT      |")
        println("+----------------------+----------------+")
        println("| Total Patients       | ${patients.size.toString().padEnd(14)}|")
        println("| Total Doctors        | ${doctors.size.toString().padEnd(14)}|")
        println("| Appointments         | ${appointments.size.toString().padEnd(14)}|")
        println("| Revenue              | ₹${totalRevenue.toString().padEnd(13)}|")
        println("+----------------------+----------------+")
    }

    // Patient
    fun addPatient() {
        print("ID: "); val id = readLine()!!.toInt()
        print("Name: "); val name = readLine()!!
        print("Age: "); val age = readLine()!!.toInt()
        patients.add(Patient(id, name, age))
        println("Added")
    }

    fun viewPatients() {
        println("\n+----+----------------------+------+" )
        println("| ID | Name                 | Age  |")
        println("+----+----------------------+------+")

        for (p in patients) {
            println("| ${p.id.toString().padEnd(2)} | ${p.name.padEnd(20)} | ${p.age.toString().padEnd(4)} |")
        }

        println("+----+----------------------+------+" )
    }

    fun searchPatient() {
        print("Enter Name: ")
        val name = readLine()!!
        patients.filter { it.name.contains(name, true) }
            .forEach { println("Found: ${it.id} ${it.name}") }
    }

    fun updatePatient() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        patients.find { it.id == id }?.let {
            print("New Name: "); it.name = readLine()!!
            print("New Age: "); it.age = readLine()!!.toInt()
            println("Updated")
        }
    }

    fun deletePatient() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        patients.removeIf { it.id == id }
        println("Deleted")
    }

    // Doctor
    fun addDoctor() {
        print("ID: "); val id = readLine()!!.toInt()
        print("Name: "); val name = readLine()!!
        print("Dept: "); val dept = readLine()!!
        doctors.add(Doctor(id, name, dept))
        println("Added")
    }

    fun viewDoctors() {
        println("\n+----+----------------------+----------------------+" )
        println("| ID | Name                 | Department           |")
        println("+----+----------------------+----------------------+")

        for (d in doctors) {
            println("| ${d.id.toString().padEnd(2)} | ${d.name.padEnd(20)} | ${d.dept.padEnd(20)} |")
        }

        println("+----+----------------------+----------------------+" )
    }

    fun updateDoctor() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        doctors.find { it.id == id }?.let {
            print("New Name: "); it.name = readLine()!!
            print("New Dept: "); it.dept = readLine()!!
            println("Updated")
        }
    }

    fun deleteDoctor() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        doctors.removeIf { it.id == id }
        println("Removed")
    }

    // Appointment
    fun bookAppointment() {
        print("ID: "); val id = readLine()!!.toInt()
        print("Patient ID: "); val p = readLine()!!.toInt()
        print("Doctor ID: "); val d = readLine()!!.toInt()
        appointments.add(Appointment(id, p, d))
        println("Booked")
    }

    fun viewAppointments() {
        println("\n+----+------------+------------+" )
        println("| ID | Patient ID | Doctor ID  |")
        println("+----+------------+------------+")

        for (a in appointments) {
            println("| ${a.id.toString().padEnd(2)} | ${a.patientId.toString().padEnd(10)} | ${a.doctorId.toString().padEnd(10)} |")
        }

        println("+----+------------+------------+" )
    }

    fun cancelAppointment() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        appointments.removeIf { it.id == id }
        println("Cancelled")
    }

    // Billing
    fun generateBill() {
        print("ID: "); val id = readLine()!!.toInt()
        print("Patient ID: "); val p = readLine()!!.toInt()
        print("Amount: "); val amt = readLine()!!.toDouble()
        print("Status: "); val st = readLine()!!
        bills.add(Bill(id, p, amt, st))
        println("Bill Generated")
    }

    fun viewBills() {
        println("\n+----+------------+----------+----------+" )
        println("| ID | Patient ID | Amount   | Status   |")
        println("+----+------------+----------+----------+")

        for (b in bills) {
            println("| ${b.id.toString().padEnd(2)} | ${b.patientId.toString().padEnd(10)} | ₹${b.amount.toString().padEnd(7)} | ${b.status.padEnd(8)} |")
        }

        println("+----+------------+----------+----------+" )
    }

    // Staff
    fun addStaff() {
        print("ID: "); val id = readLine()!!.toInt()
        print("Name: "); val name = readLine()!!
        staffList.add(Staff(id, name))
        println("Added")
    }

    fun viewStaff() {
        println("\n+----+----------------------+" )
        println("| ID | Name                 |")
        println("+----+----------------------+")

        for (s in staffList) {
            println("| ${s.id.toString().padEnd(2)} | ${s.name.padEnd(20)} |")
        }

        println("+----+----------------------+" )
    }

    fun removeStaff() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        staffList.removeIf { it.id == id }
        println("Removed")
    }

    // Medicine
    fun addMedicine() {
        print("ID: "); val id = readLine()!!.toInt()
        print("Name: "); val name = readLine()!!
        print("Stock: "); val st = readLine()!!.toInt()
        medicines.add(Medicine(id, name, st))
        println("Added")
    }

    fun viewMedicine() {
        println("\n+----+----------------------+--------+" )
        println("| ID | Name                 | Stock  |")
        println("+----+----------------------+--------+")

        for (m in medicines) {
            println("| ${m.id.toString().padEnd(2)} | ${m.name.padEnd(20)} | ${m.stock.toString().padEnd(6)} |")
        }

        println("+----+----------------------+--------+" )
    }

    fun updateStock() {
        print("Enter ID: ")
        val id = readLine()!!.toInt()
        medicines.find { it.id == id }?.let {
            print("New Stock: ")
            it.stock = readLine()!!.toInt()
            println("Updated")
        }
    }

    // Reports
    fun reports() {
        println("\n+----------------------+")
        println("|       REPORT         |")
        println("+----------------------+")
        println("| Patients: ${patients.size}")
        println("| Appointments: ${appointments.size}")
        println("| Revenue: ₹${bills.sumOf { it.amount }}")
        println("+----------------------+")
    }
}

fun main() {

    val h = HospitalSystem()

    while (true) {

        println("\n+--------------------------------------+")
        println("|      HOSPITAL MANAGEMENT MENU        |")
        println("+----+--------------------------------+")
        println("| 1  | Dashboard                      |")
        println("| 2  | Add Patient                    |")
        println("| 3  | View Patients                  |")
        println("| 4  | Search Patient                 |")
        println("| 5  | Update Patient                 |")
        println("| 6  | Delete Patient                 |")
        println("| 7  | Add Doctor                     |")
        println("| 8  | View Doctors                   |")
        println("| 9  | Update Doctor                  |")
        println("| 10 | Delete Doctor                  |")
        println("| 11 | Book Appointment               |")
        println("| 12 | View Appointments              |")
        println("| 13 | Cancel Appointment             |")
        println("| 14 | Generate Bill                  |")
        println("| 15 | View Bills                     |")
        println("| 16 | Add Staff                      |")
        println("| 17 | View Staff                     |")
        println("| 18 | Remove Staff                   |")
        println("| 19 | Add Medicine                   |")
        println("| 20 | View Medicine                  |")
        println("| 21 | Update Stock                   |")
        println("| 22 | Reports                        |")
        println("| 23 | Exit                           |")
        println("+----+--------------------------------+")

        print("Enter Choice: ")

        when (readLine()!!.toInt()) {
            1 -> h.dashboard()
            2 -> h.addPatient()
            3 -> h.viewPatients()
            4 -> h.searchPatient()
            5 -> h.updatePatient()
            6 -> h.deletePatient()
            7 -> h.addDoctor()
            8 -> h.viewDoctors()
            9 -> h.updateDoctor()
            10 -> h.deleteDoctor()
            11 -> h.bookAppointment()
            12 -> h.viewAppointments()
            13 -> h.cancelAppointment()
            14 -> h.generateBill()
            15 -> h.viewBills()
            16 -> h.addStaff()
            17 -> h.viewStaff()
            18 -> h.removeStaff()
            19 -> h.addMedicine()
            20 -> h.viewMedicine()
            21 -> h.updateStock()
            22 -> h.reports()
            23 -> break
            else -> println("Invalid Choice")
        }
    }
}