package com.example.a2ndassignmentahhapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvFeedback = findViewById<TextView>(R.id.tvScoreFeedback)
        val tvMessage = findViewById<TextView>(R.id.tvScoreMessage)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 5)

        tvScore.text = "$score / $total"

        if (score == 5) {
            tvFeedback.text = "Master Hacker! 🎉"
            tvMessage.text = "Perfect score! You really know your life hacks from your urban myths!"
        } else if (score == 4) {
            tvFeedback.text = "Hack Expert! 👏"
            tvMessage.text = "Almost perfect! You clearly know your stuff. One wrong answer never hurt anybody!"
        } else if (score == 3) {
            tvFeedback.text = "Not Bad! 🗿"
            tvMessage.text = "You made it halfway! Keep trying and you will be a hack master in no time!"
        } else if (score == 2) {
            tvFeedback.text = "Keep Trying! 💪"
            tvMessage.text = "You're nearly there, but you need more practice! Review the answers and try again!"
        } else {
            tvFeedback.text = "Stay Safe Online! 😅"
            tvMessage.text = "Looks like you fell for the urban myths! Hit review to learn the correct answers."
        }

        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}