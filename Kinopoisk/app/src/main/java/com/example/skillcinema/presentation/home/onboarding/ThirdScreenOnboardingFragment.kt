package com.example.skillcinema.presentation.home.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.data.home.OnboardingSharedPrefRepository


class ThirdScreenOnboardingFragment() : Fragment() {
    private var onboardingSharedPrefRepository = OnboardingSharedPrefRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_third_screen_onboarding, container, false)
        onboardingSharedPrefRepository.initOnboardingPref(requireContext())
        view.rootView.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_mainHomePageFragment)
            onboardingSharedPrefRepository.saveStateShowOnboarding(false)
        }
        return view
    }
}