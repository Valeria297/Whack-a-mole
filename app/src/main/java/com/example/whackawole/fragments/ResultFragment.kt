package com.example.whackawole.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.whackawole.R
import com.example.whackawole.databinding.FragmentResultBinding
import com.example.whackawole.storage.ScoreHolder

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentResultBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            score.text = "Your score: " + ScoreHolder.score.toString()
            record.text = "High score: " + ScoreHolder.highScore.toString()

            buttonMenu.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_result_to_fragment_start)
            }

            buttonRepeat.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_result_to_fragment_game)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}