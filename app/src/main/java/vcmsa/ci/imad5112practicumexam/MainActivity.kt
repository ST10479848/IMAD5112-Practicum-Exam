package vcmsa.ci.imad5112practicumexam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val songNames = mutableListOf<String>()
    private val songCategories = mutableListOf<String>()
    private val artistsName = mutableListOf<String>()
    private val songRating = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // here we are declaring the variables
        val addButton = findViewById<Button>(R.id.addSongButton)
        val nextButton = findViewById<Button>(R.id.nextScreenButton)
        val exitButton = findViewById<Button>(R.id.exitAppButton)
        //adding our buttons
        addButton.setOnClickListener {
            showInputDialog()
        }
        // this button is going to drag all the things in our array and put it in the next page
        nextButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putStringArrayListExtra("songs", ArrayList(songNames))
                putStringArrayListExtra("categories", ArrayList(songCategories))
                putStringArrayListExtra("artists", ArrayList(artistsName))
                putIntegerArrayListExtra("rating", ArrayList(songRating))
            }
            startActivity(intent)
        }
        // this is just to immediatly close the app convenient i know
        exitButton.setOnClickListener {
            finish()
        }
    }   // tbh this was very hard to do but i made sure to go over it its almost like intent but without moving screens
       private fun showInputDialog() {
            val dialogView = layoutInflater.inflate(R.layout.dialog_input, null)

            val dialog = AlertDialog.Builder(this)
                .setTitle("Add Songs to Playlist")
                .setView(dialogView)
                .setPositiveButton("Add", null)
                .setNegativeButton("Cancel", null)
                .create()

            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val name = dialogView.findViewById<EditText>(R.id.etSongNames).text.toString()
                    val category =
                        dialogView.findViewById<EditText>(R.id.etCategory).text.toString()
                    val musicArtists =
                        dialogView.findViewById<EditText>(R.id.etArtists).text.toString()
                    val ratingSong =
                        dialogView.findViewById<EditText>(R.id.etRating).text.toString()
                        // more complicated stuff but basically when our EditTexts are blank its just used for error handle
                    if (name.isBlank() || category.isBlank() || ratingSong.isBlank()) {
                        Toast.makeText(this, "Please fill in all the required fields", Toast.LENGTH_SHORT).show()
                    } else {
                        val rating = ratingSong.toIntOrNull()
                        if (rating == null || rating <= 0) {
                            Toast.makeText(this, "Enter a valid rating", Toast.LENGTH_SHORT).show()
                        } else {
                            songNames.add(name)
                            songCategories.add(category)
                            artistsName.add(musicArtists)
                            songRating.add(rating)

                            Toast.makeText(this, "Song added", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                    }
                }
            }
           dialog.show()
        }
    }


































