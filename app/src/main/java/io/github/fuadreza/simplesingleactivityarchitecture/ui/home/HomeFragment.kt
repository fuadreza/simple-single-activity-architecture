package io.github.fuadreza.simplesingleactivityarchitecture.ui.home

import androidx.navigation.fragment.findNavController
import io.github.fuadreza.simplesingleactivityarchitecture.R
import io.github.fuadreza.simplesingleactivityarchitecture.databinding.FragmentHomeBinding
import io.github.fuadreza.simplesingleactivityarchitecture.utils.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(){

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun initViews() {
        binding.tvHome.text = "This is home fragment"

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_featureAFragment)
        }
    }

}