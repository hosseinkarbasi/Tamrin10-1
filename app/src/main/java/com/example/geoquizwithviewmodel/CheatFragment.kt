package com.example.geoquizwithviewmodel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.geoquizwithviewmodel.databinding.CheatFragmentBinding

class CheatFragment : Fragment(R.layout.cheat_fragment) {
    private lateinit var binding: CheatFragmentBinding
    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CheatFragmentBinding.bind(view)

        val navArgs: CheatFragmentArgs by navArgs()

        with(binding) {
            showAnswerButton.setOnClickListener {
                val answerText = when {
                    navArgs.answerIsTrue -> "TRUE"
                    else -> "FALSE"
                }
                if (!navArgs.ischeat)
                    quizViewModel.isCheater()

                answerTextView.text = answerText
            }
        }
    }
}
