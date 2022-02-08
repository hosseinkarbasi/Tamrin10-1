package com.example.geoquizwithviewmodel

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.geoquizwithviewmodel.databinding.GeoquizFragmentBinding

class GeoQuizFragment : Fragment(R.layout.geoquiz_fragment) {

    private val navController by lazy { findNavController() }
    private lateinit var binding: GeoquizFragmentBinding
    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GeoquizFragmentBinding.bind(view)

        with(binding) {

            trueButton.setOnClickListener {
                checkAnswer(true)
            }

            falseButton.setOnClickListener {
                checkAnswer(false)
            }

            nextButton.setOnClickListener {
                quizViewModel.moveToNext()
                updateQuestion()
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            }

            previousButton.setOnClickListener {
                quizViewModel.moveToPrevious()
                updateQuestion()
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            }

            cheatButton.setOnClickListener {
                navController.navigate(
                    GeoQuizFragmentDirections.actionGeoQuizFragmentToCheatFragment(
                        quizViewModel.currentQuestionAnswer, quizViewModel.cheat
                    )
                )
            }
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
        binding.nextButton.isEnabled = quizViewModel.currentIndex != 9
        binding.previousButton.isEnabled = quizViewModel.currentIndex != 0
        quizViewModel.cheat
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.cheat -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_SHORT)
            .show()

        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
    }
}
