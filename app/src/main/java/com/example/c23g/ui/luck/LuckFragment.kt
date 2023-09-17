package com.example.c23g.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.c23g.R
import com.example.c23g.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {
    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.ivRoulette.setOnClickListener { spinRoulette() }
    }

    private fun spinRoulette() { // se hace con el ObjectAnimator que ya eso existe
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator = //hay de rotation translation alpha scale
            // funcion                xml                 clse de rotacion      de donde a donde
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator() //LinearInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() { // se hace por nosotros mismos  con AnimationUtils
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {
                binding.reverse.isVisible = true } // el view debe empezar con visibility="invisible"
            override fun onAnimationEnd(p0: Animation?) {
                growCard() }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.reverse.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.reverse.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        binding.reverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}