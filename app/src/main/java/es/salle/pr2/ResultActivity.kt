package es.salle.pr2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bundle = intent.extras
        val data = bundle?.getString("email")

        val buttonFinish = findViewById<Button>(R.id.buttonFinish)
        val editTextViewEmail = findViewById<TextView>(R.id.textViewEmail)

        editTextViewEmail.text = data

        buttonFinish.setOnClickListener {
            finish()
        }
    }
}