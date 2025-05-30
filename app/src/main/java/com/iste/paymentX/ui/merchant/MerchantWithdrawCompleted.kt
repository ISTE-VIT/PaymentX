package com.iste.paymentX.ui.merchant

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.iste.paymentX.R

class MerchantWithdrawCompleted : AppCompatActivity() {
    private lateinit var backarrow: ImageView
    private lateinit var amountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_merchant_withdraw_completed)

        // Initialize views
        amountTextView = findViewById(R.id.textView2)

        // Get and log the amount
        val amount = intent.getIntExtra("EXTRA_AMOUNT", 0)
        Log.d("MerchantCompleted", "Received amount: $amount")

        if (amount > 0) {
            val formattedAmount = String.format("%,d", amount)
            amountTextView.text = "₹$formattedAmount"
        } else {
            // For debugging, show a toast if amount is 0
            Toast.makeText(this, "Error: Amount not received", Toast.LENGTH_SHORT).show()
            Log.e("MerchantCompleted", "Amount is 0 or not received properly")
        }

        backarrow = findViewById(R.id.merchimageView3)
        backarrow.setOnClickListener {
            navigateToMainScreen()
        }

        // Handle system back button
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToMainScreen()
            }
        })
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MerchantMainScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}