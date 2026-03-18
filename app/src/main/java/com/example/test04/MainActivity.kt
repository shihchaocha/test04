package com.example.test04

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etBillAmount = findViewById<EditText>(R.id.etBillAmount)
        val tvTipAmount = findViewById<TextView>(R.id.tvTipAmount)
        val currencyFormatter = NumberFormat.getCurrencyInstance()

        fun updateTipAmount() {
            val billAmount = etBillAmount.text.toString().trim().toDoubleOrNull() ?: 0.0
            val tipAmount = billAmount * 0.15
            tvTipAmount.text = "Tip Amount: ${currencyFormatter.format(tipAmount)}"
        }

        updateTipAmount()
        etBillAmount.doAfterTextChanged {
            updateTipAmount()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}