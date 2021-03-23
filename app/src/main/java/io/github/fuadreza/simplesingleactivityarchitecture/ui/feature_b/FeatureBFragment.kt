package io.github.fuadreza.simplesingleactivityarchitecture.ui.feature_b

import androidx.navigation.fragment.findNavController
import io.github.fuadreza.simplesingleactivityarchitecture.R
import io.github.fuadreza.simplesingleactivityarchitecture.databinding.FragmentFeatureBBinding
import io.github.fuadreza.simplesingleactivityarchitecture.utils.BaseFragment

class FeatureBFragment : BaseFragment<FragmentFeatureBBinding>() {

    override fun getLayoutResource(): Int = R.layout.fragment_feature_b

    override fun initViews() {
        binding.tvFeatureB.text = "Now you are on Feature B"

        binding.btnNext.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}