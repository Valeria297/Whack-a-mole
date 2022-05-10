package com.example.whackawole.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.whackawole.R
import com.example.whackawole.databinding.FragmentStartBinding
import com.example.whackawole.storage.ScoreHolder

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentStartBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            var scoreRecord = ScoreHolder().highScore
            score.text = "Your record: " + ScoreHolder().updateHighScore(scoreRecord).toString()

            buttonStart.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_start_to_fragment_game)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}