package com.example.geoquizwithviewmodel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.geoquizwithviewmodel.databinding.CheatFragmentBinding


class CheatFragment : Fragment(R.layout.cheat_fragment) {
    private val navController by lazy { findNavController() }
    private lateinit var binding: CheatFragmentBinding

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
                answerTextView.text = answerText
                navController.previousBackStackEntry?.savedStateHandle?.set("isCheater", true)
            }
        }
    }
}
