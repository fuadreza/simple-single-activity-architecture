package io.github.fuadreza.simplesingleactivityarchitecture.ui.feature_a

import androidx.navigation.fragment.findNavController
import io.github.fuadreza.simplesingleactivityarchitecture.R
import io.github.fuadreza.simplesingleactivityarchitecture.databinding.FragmentFeatureABinding
import io.github.fuadreza.simplesingleactivityarchitecture.utils.BaseFragment

class FeatureAFragment : BaseFragment<FragmentFeatureABinding>() {

    override fun getLayoutResource(): Int = R.layout.fragment_feature_a

    override fun initViews() {
        binding.tvFeatureA.text = "You just visited Feature A"

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_featureAFragment_to_featureBFragment)
        }
    }

}