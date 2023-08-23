package com.example.skillcinema.data.home

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class OnboardingSharedPrefRepository @Inject constructor(){

    private lateinit var onboardingPreference: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun initOnboardingPref(context: Context){
        onboardingPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = onboardingPreference.edit()
    }

    fun saveStateShowOnboarding(show: Boolean){
        editor.putBoolean(PREF_KEY_ONBOARDING, show)
        editor.apply()
    }

    fun getStateShowOnboarding(): Boolean{
        return onboardingPreference.getBoolean(PREF_KEY_ONBOARDING, true)
    }

    companion object {
        private const val PREF_NAME = "OnboardingPref"
        private const val PREF_KEY_ONBOARDING = "onboarding"
    }
}