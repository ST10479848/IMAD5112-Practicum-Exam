package vcmsa.ci.imad5112practicumexam

import android.os.Bundle
import android.widget.*
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList


class SecondActivity : AppCompatActivity() {

    private lateinit var songName: ArrayList<String>
    private lateinit var songCategory: ArrayList<String>
    private lateinit var artistName: ArrayList<String>
    private lateinit var songRating: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val output = findViewById<TextView>(R.id.viewOutputText)
        val all = findViewById<Button>(R.id.showAllButton)
        val highRating = findViewById<Button>(R.id.highRatingButton)
        val back = findViewById<Button>(R.id.backButton)
        // here we pulling our Array values to our SecondActivity
        songName = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        songCategory = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        artistName = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        songRating = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
//setting up more buttons
all.setOnClickListener{
    output.text = buildFullList()
}
        highRating.setOnClickListener {
            output.text = buildFilteredList()
        }
        back.setOnClickListener {
            finish()
        }
    } // the builder is just used to filter the inputted stuff neatly
    private fun buildFullList(): String {
        val builder = StringBuilder()
        for (i in songName.indices) {
            builder.append("Songs: ${songName[i]}\n")
            builder.append("Category: ${songCategory[i]}\n")
            builder.append("Artists: ${artistName[i]}\n")
            builder.append("Rating: ${songRating[i]}\n")
        }
        return builder.toString()
    }
    private fun buildFilteredList(): String {
        val builder = StringBuilder()
        for (i in songName.indices) {
            if (songRating[i] >= 2) {
                builder.append("Song: ${songName[i]} - Rating: ${songRating[i]}\n")
            }
        }
        return builder.toString().ifBlank {"No itmes with quantity ≥ 2 "}
    }
}