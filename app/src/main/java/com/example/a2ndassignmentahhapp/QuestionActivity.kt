package com.example.a2ndassignmentahhapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    val questions = listOf(
        "Placing a wet spoon on a mosquito bite relieves the itch.",
        "You swallow 8 spiders a year in your sleep.",
        "Chewing gum takes 7 years to digest.",
        "Putting a wooden spoon over a boiling pot stops it from boiling over.",
        "You only use 10% of your brain."
    )

    val answers = listOf<Boolean>(true, false, false, true, false)

    val explanations = listOf(
        "Hack! The heat from the spoon breaks down the protein causing the itch!",
        "Myth! Spiders avoid humans when they sleep. This is just an urban legend!",
        "Myth! Gum base is not digested but it passes through your system normally!",
        "Hack! The wooden spoon breaks the surface tension and stops the boil over!",
        "Myth! Brain scans show we use virtually all of our brain every day!"
    )

    var currentIndex = 0
    var score = 0
    var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val tvCounter = findViewById<TextView>(R.id.tvQuestionCounter)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)
        val btnNext = findViewById<Button>(R.id.btnNext)

        fun loadQuestion() {
            answered = false
            tvQuestion.text = questions[currentIndex]
            tvCounter.text = "Question ${currentIndex + 1} of ${questions.size}"
            tvFeedback.visibility = View.GONE
            btnNext.visibility = View.GONE
            btnHack.isEnabled = true
            btnMyth.isEnabled = true
        }

        loadQuestion()

        // Hack button = user says TRUE
        btnHack.setOnClickListener {
            if (!answered) {
                answered = true
                btnHack.isEnabled = false
                btnMyth.isEnabled = false

                val isCorrect: Boolean = answers[currentIndex] == true

                if (isCorrect) {
                    score++
                    tvFeedback.text = "Correct! ${explanations[currentIndex]}"
                    tvFeedback.setTextColor(0xFF2E7D32.toInt())
                } else {
                    tvFeedback.text = "Wrong! ${explanations[currentIndex]}"
                    tvFeedback.setTextColor(0xFF7B0000.toInt())
                }
                tvFeedback.visibility = View.VISIBLE
                btnNext.visibility = View.VISIBLE
            }
        }

        // Myth button = user says FALSE
        btnMyth.setOnClickListener {
            if (!answered) {
                answered = true
                btnHack.isEnabled = false
                btnMyth.isEnabled = false

                val isCorrect: Boolean = answers[currentIndex] == false

                if (isCorrect) {
                    score++
                    tvFeedback.text = "Correct! ${explanations[currentIndex]}"
                    tvFeedback.setTextColor(0xFF2E7D32.toInt())
                } else {
                    tvFeedback.text = "Wrong! ${explanations[currentIndex]}"
                    tvFeedback.setTextColor(0xFF7B0000.toInt())
                }
                tvFeedback.visibility = View.VISIBLE
                btnNext.visibility = View.VISIBLE
            }
        }

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                startActivity(intent)
            }
        }
    }
}