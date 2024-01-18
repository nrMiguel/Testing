package es.salle.pr2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPass = findViewById<EditText>(R.id.editTextPassword)
        val editTextPassConfirm = findViewById<EditText>(R.id.editTextPasswordConfirmation)
        val textViewHelp = findViewById<TextView>(R.id.textViewHelp)

        buttonRegister.setOnClickListener {
            //Check email
            if (Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString()).matches() && editTextPass.text.toString().length > 5 && editTextPass.text.toString() == editTextPassConfirm.text.toString()){
                startActivity(Intent(applicationContext, ResultActivity::class.java).apply {
                    putExtra("email", editTextEmail.text.toString())
                })
                finish()
            }
        }

        textViewHelp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(getString(R.string.url)))
            startActivity(intent)
        }
    }

    private fun createWebIntent(): Intent?{
        val intentToCall = Intent(Intent.ACTION_VIEW)
        intentToCall.data = Uri.parse(getString(R.string.url))

        return intentToCall
    }
}