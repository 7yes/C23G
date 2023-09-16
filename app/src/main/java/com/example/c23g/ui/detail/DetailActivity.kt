package com.example.c23g.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.c23g.R
import com.example.c23g.databinding.ActivityDetailBinding
import com.example.c23g.domain.model.HoroscopeModel.Aquarius
import com.example.c23g.domain.model.HoroscopeModel.Aries
import com.example.c23g.domain.model.HoroscopeModel.Cancer
import com.example.c23g.domain.model.HoroscopeModel.Capricorn
import com.example.c23g.domain.model.HoroscopeModel.Gemini
import com.example.c23g.domain.model.HoroscopeModel.Leo
import com.example.c23g.domain.model.HoroscopeModel.Libra
import com.example.c23g.domain.model.HoroscopeModel.Pisces
import com.example.c23g.domain.model.HoroscopeModel.Sagittarius
import com.example.c23g.domain.model.HoroscopeModel.Scorpio
import com.example.c23g.domain.model.HoroscopeModel.Taurus
import com.example.c23g.domain.model.HoroscopeModel.Virgo
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
        detailViewModel.getHoroscope(args.type)
        setContentView(binding.root)
    }

    private fun intiUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressed() }
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


        val image = when (state.horoscopeModel) {
            Aries -> R.drawable.aries
            Taurus -> R.drawable.tauro
            Gemini -> R.drawable.geminis
            Cancer -> R.drawable.cancer
            Leo -> R.drawable.leo
            Virgo -> R.drawable.virgo
            Libra -> R.drawable.libra
            Scorpio -> R.drawable.escorpio
            Sagittarius -> R.drawable.sagitario
            Capricorn -> R.drawable.capricornio
            Aquarius -> R.drawable.aquario
            Pisces -> R.drawable.piscis
        }

        binding.ivDetail.setImageResource(image)
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }
}