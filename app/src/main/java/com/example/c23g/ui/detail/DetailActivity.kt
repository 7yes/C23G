package com.example.c23g.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.c23g.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        intiUI()
        detailViewModel.getHoroscope(args.type.name)
        setContentView(binding.root)
    }

    private fun intiUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect {
                    when (it) {
                        is DetailsUIState.Error -> errorState()
                        DetailsUIState.Loading -> loadingState()
                        is DetailsUIState.Sucess -> sucessState(it)
                    }
                }
            }
        }
    }

    private fun sucessState(state: DetailsUIState.Sucess) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sing
        binding.tvBody.text = state.prediction

//        val image = when (state.data) {
//            Aries -> R.drawable.detail_aries
//            Taurus -> R.drawable.detail_taurus
//            Gemini -> R.drawable.detail_gemini
//            Cancer -> R.drawable.detail_cancer
//            Leo -> R.drawable.detail_leo
//            Virgo -> R.drawable.detail_virgo
//            Libra -> R.drawable.detail_libra
//            Scorpio -> R.drawable.detail_scorpio
//            Sagittarius -> R.drawable.detail_sagittarius
//            Capricorn -> R.drawable.detail_capricorn
//            Aquarius -> R.drawable.detail_aquarius
//            Pisces -> R.drawable.detail_pisces
//        }

   //     binding.ivDetail.setImageResource(image)

    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }
}