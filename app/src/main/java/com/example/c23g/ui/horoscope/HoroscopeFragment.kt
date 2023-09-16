package com.example.c23g.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.c23g.R
import com.example.c23g.databinding.FragmentHoroscopeBinding
import com.example.c23g.domain.model.HoroscopeInfo
import com.example.c23g.domain.model.HoroscopeModel
import com.example.c23g.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel: HoroscopeViewModel by viewModels()
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
        initRV()
    }

    private fun initRV() {
//        horoscopeAdapter = HoroscopeAdapter {
//            // Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
//            Log.d("TAG", "horoscope Frag in initRV  $it ")
//            //findNavController().navigate(R.id.action_horoscopeFragment_to_detailActivity)
//            findNavController().navigate(HoroscopeFragmentDirections.actionHoroscopeFragmentToDetailActivity())
//        }

        horoscopeAdapter = HoroscopeAdapter( onClickLis = {
            val type = when(it){
                HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                HoroscopeInfo.Gemini -> HoroscopeModel.Gemini
                HoroscopeInfo.Leo -> HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Pisces -> HoroscopeModel.Pisces
                HoroscopeInfo.Sagittarius -> HoroscopeModel.Sagittarius
                HoroscopeInfo.Scorpio -> HoroscopeModel.Scorpio
                HoroscopeInfo.Taurus -> HoroscopeModel.Taurus
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }
            Log.d("TAG", "horoscope Frag in initRV  $it ")
           // findNavController().navigate(R.id.action_horoscopeFragment_to_detailActivity)
            findNavController().navigate(HoroscopeFragmentDirections.actionHoroscopeFragmentToDetailActivity(type))
        })


        binding.rvHoroscope.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvHoroscope.adapter = horoscopeAdapter
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    //carga y actualiza cambio en la lista de signos
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}