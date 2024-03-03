package com.example.greendzinetechnologies

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.Loginbtn)

        button.setOnClickListener {
            val usernameEditText = findViewById<EditText>(R.id.emailedit)
            val passwordEditText = findViewById<EditText>(R.id.passedit)

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform your login validation here
            val isValidLogin = validateLogin(username, password)

            if (isValidLogin) {
                // Proceed to the next activity
                val intent = Intent(this, Employee::class.java)
                startActivity(intent)
            } else {
                // Show dialog box for incorrect login credentials
                showInvalidLoginDialog()
            }
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        // Here, you would perform your actual login validation
        // For demonstration purposes, let's assume a simple validation
        return username == "admin" && password == "admin"
    }

    private fun showInvalidLoginDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Invalid Login")
        builder.setMessage("Username or password is incorrect.")
        builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}