package za.ac.imadshistory

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity() : AppCompatActivity(), Parcelable{
    data class FamousPerson(val name: String, val ageAtDeath: Int)

    class HistoricalEvent(val year: Int, val description: String) {
        companion object {
            val famousPeople = listOf(
                FamousPerson("Nelson Mandela", 95),
                FamousPerson("William Shakespeare", 52),
                FamousPerson("Albert Einstein", 76),
                FamousPerson("Leonardo da Vinci", 67),
                FamousPerson("Mahatma Gandhi", 78),
                FamousPerson("Mother Teresa", 87),
                FamousPerson("Abraham Lincoln", 56),
                FamousPerson("Winston Churchill", 90),
                FamousPerson("Martin Luther King Jr.", 39),
                FamousPerson("Steve Jobs", 56)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting layout components
        val ageInput = findViewById<EditText>(R.id.entage)
        val Genratehistory = findViewById<Button>(R.id.genHistory)
        val clearApp = findViewById<Button>(R.id.clear)
        val txtResult = findViewById<TextView>(R.id.txtview2)

        Genratehistory.setOnClickListener {
            val inputAge = ageInput.text.toString().trim()
            val age: Int

            try {
                age = inputAge.toInt()
                if (age !in 20..100) {
                    txtResult.text = "The age is out of range (20-100)"
                    return@setOnClickListener
                }
            } catch (ex: NumberFormatException) {
                txtResult.text = "The age is an invalid format"
                return@setOnClickListener
            }

            val matchedPerson = HistoricalEvent.famousPeople.find { it.ageAtDeath == age }
            if (matchedPerson != null) {
                txtResult.text = "Famous person at age $age: ${matchedPerson.name}"
            } else {
                txtResult.text = "No famous person found at age $age"
            }
        }

        clearApp.setOnClickListener {
            ageInput.setText("")
            txtResult.text = ""
        }
    }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}