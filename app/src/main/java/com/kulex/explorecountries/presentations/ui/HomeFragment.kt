package com.kulex.explorecountries.presentations.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kulex.explorecountries.data._models.Country
import com.kulex.explorecountries.databinding.HomeFragmentLayoutBinding
import com.kulex.explorecountries.presentations.vms.CountryVM
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var vm: CountryVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        HomeFragmentLayoutBinding.inflate(inflater).let { _binding = it }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ViewModelProvider(this)[CountryVM::class.java].let { vm = it }
        super.onViewCreated(view, savedInstanceState)
        vm.getCountries()

        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            vm.uiState.collect {
                if (it.isError) showError(it.msg)
                if (it.isLoading) showLoader(it.msg)
                if (it.isSuccess) initRecycler(it.dataList, it.msg)
            }
        }
    }

    private fun initRecycler(dataList: MutableList<Country>, msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoader(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()

    }

    private fun hideLoader() {
    }

    private fun showError(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun hideError() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}