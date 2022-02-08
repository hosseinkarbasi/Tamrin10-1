package com.example.geoquizwithviewmodel

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    var currentIndex = 0
    private val questionBank = listOf(
        Question(R.string.question_1, false, isCheat = false),
        Question(R.string.question_2, true, isCheat = false),
        Question(R.string.question_3, false, isCheat = false),
        Question(R.string.question_4, true, isCheat = false),
        Question(R.string.question_5, true, isCheat = false),
        Question(R.string.question_6, true, isCheat = false),
        Question(R.string.question_7, false, isCheat = false),
        Question(R.string.question_8, true, isCheat = false),
        Question(R.string.question_9, false, isCheat = false),
        Question(R.string.question_10, true, isCheat = false)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val cheat: Boolean
        get() = questionBank[currentIndex].isCheat

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1) % questionBank.size
    }

    fun isCheater() {
        questionBank[currentIndex].isCheat = true
    }

}