package com.kulex.explorecountries.presentations.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kulex.explorecountries.databinding.DetailFragmentLayoutBinding
import com.kulex.explorecountries.databinding.HomeFragmentLayoutBinding
import com.kulex.explorecountries.presentations.vms.CountryVM

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var vm: CountryVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[CountryVM::class.java].let { vm = it }
        DetailFragmentLayoutBinding.inflate(inflater).let { _binding = it }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}