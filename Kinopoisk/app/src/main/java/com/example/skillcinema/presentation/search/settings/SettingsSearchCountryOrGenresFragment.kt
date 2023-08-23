package com.example.skillcinema.presentation.search.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.data.search.SettingsSharedPrefRepository
import com.example.skillcinema.databinding.FragmentSettingsSearchCountryOrGenresBinding
import com.example.skillcinema.domain.search.usecase.SettingsUseCase
import com.example.skillcinema.entity.search.OverallCountryAndGenres
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SettingsSearchCountryOrGenresFragment : Fragment() {

    private var _binding: FragmentSettingsSearchCountryOrGenresBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsSearchCountryOrGenresViewModel by viewModels()
    private var sharedPrefRepository = SettingsSharedPrefRepository()
    private val settingsUseCase = SettingsUseCase(sharedPrefRepository)
    private val settingCountryAndGenresAdapter = CountryAndGenresForSearchAdapter(settingsUseCase){ countryOrGenres -> OnClickIemCountryOrGenres(countryOrGenres)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsSearchCountryOrGenresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val label = arguments?.let {
            it.getString(KEY_LABEL)
        }
        binding.label.text = label.toString()
        sharedPrefRepository.initSettingsSharedPrefRepository(requireContext())
        binding.recyclerForSearchCountryAndGenres.adapter = settingCountryAndGenresAdapter
        viewModel.countryAndGenres.onEach {
            if (it != null) {
                if (label == COUNTRY) {
                    settingCountryAndGenresAdapter.setData(it.countries)
                } else {
                    settingCountryAndGenresAdapter.setData(it.genres)
                }
                binding.searchView.setOnQueryTextListener(
                    object : OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            val newData = if (label == COUNTRY) {
                                it.countries.filter { country ->
                                    country.name.contains(newText.toString())
                                }
                            } else {
                                it.genres.filter { genres ->
                                    genres.name.contains(newText.toString())
                                }
                            }

                            settingCountryAndGenresAdapter.setData(newData)
                            return true
                        }
                    }
                )
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun OnClickIemCountryOrGenres(countryOrGenres: OverallCountryAndGenres) {
        findNavController().navigate(R.id.action_settingsSearchCountryOrGenresFragment_to_settingsSearchFragment)
    }

    companion object {
        const val KEY_LABEL = "label"
        const val COUNTRY = "Страна"
    }
}