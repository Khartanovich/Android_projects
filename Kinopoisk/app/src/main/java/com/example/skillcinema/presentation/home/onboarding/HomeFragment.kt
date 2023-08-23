package com.example.skillcinema.presentation.home.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentHomeBinding
import com.example.skillcinema.data.home.OnboardingSharedPrefRepository
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var onboardingSharedPrefRepository = OnboardingSharedPrefRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

    onboardingSharedPrefRepository.initOnboardingPref(requireContext())
    val showOnboarding = onboardingSharedPrefRepository.getStateShowOnboarding()

    if (showOnboarding){
        findNavController().navigate(R.id.action_navigation_home_to_onboardingFragment)
    } else {
        findNavController().navigate(R.id.action_navigation_home_to_mainHomePageFragment)
    }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}