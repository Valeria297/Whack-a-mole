package com.example.whackawole.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.whackawole.R
import com.example.whackawole.databinding.FragmentGameBinding
import com.example.whackawole.storage.ScoreHolder
import java.util.*
import kotlin.collections.ArrayList

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = requireNotNull(_binding)

    var score = ScoreHolder().score
    var highScore = ScoreHolder().highScore
    var moleArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable { }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentGameBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            //ImageArray
            moleArray.add(imageMole1)
            moleArray.add(imageMole2)
            moleArray.add(imageMole3)
            moleArray.add(imageMole4)
            moleArray.add(imageMole5)
            moleArray.add(imageMole6)
            moleArray.add(imageMole7)
            moleArray.add(imageMole8)
            moleArray.add(imageMole9)

            invisibleMole()

            //Timer
            object : CountDownTimer(30000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(p0: Long) {
                    textTime.text = "Time: " + p0 / 1000
                }

                override fun onFinish() {
                    this.cancel()
                    textTime.text = "Time: 0"
                    handler.removeCallbacks(runnable)
                    for (image in moleArray) {
                        image.visibility = View.INVISIBLE
                    }

                    findNavController().navigate(R.id.action_fragment_game_to_fragment_result)
                }

            }.start()

            buttonMenu.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_game_to_fragment_start)
            }
        }
    }

    fun invisibleMole() {
        runnable = Runnable {
            for (image in moleArray) {
                image.visibility = View.INVISIBLE
            }

            val random = Random()
            val randomMole = random.nextInt(9)
            moleArray[randomMole].visibility = View.VISIBLE

            handler.postDelayed(runnable, 500)
        }

        handler.post(runnable)
    }

    fun onClick(v: View) {
        with(binding) {
            when (v.id) {
                R.id.imageMole1 -> animateHit(0)
                R.id.imageMole2 -> animateHit(1)
                R.id.imageMole3 -> animateHit(2)
                R.id.imageMole4 -> animateHit(3)
                R.id.imageMole5 -> animateHit(4)
                R.id.imageMole6 -> animateHit(5)
                R.id.imageMole7 -> animateHit(6)
                R.id.imageMole8 -> animateHit(7)
                R.id.imageMole9 -> animateHit(8)
            }
        }
    }

    fun animateHit(p: Int) {
        if (moleArray[p].getTranslationY() < 0) {
            moleArray[p].animate().translationY(0.0f).setDuration(20);
            incrementScore();
        }
    }

    fun incrementScore() {
        ScoreHolder().score += 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}