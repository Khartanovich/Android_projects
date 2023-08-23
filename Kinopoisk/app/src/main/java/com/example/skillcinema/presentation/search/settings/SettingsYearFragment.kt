package com.example.skillcinema.presentation.search.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.data.search.SettingsSharedPrefRepository
import com.example.skillcinema.databinding.FragmentSettingsYearBinding
import com.example.skillcinema.domain.search.usecase.SettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SettingsYearFragment : Fragment() {

    private var _binding : FragmentSettingsYearBinding? = null
    private val binding get() = _binding!!
    private  val viewModel: SettingsYearViewModel by viewModels()
    private var sharedPrefRepository = SettingsSharedPrefRepository()
    private val settingsUseCase = SettingsUseCase(sharedPrefRepository)
    private val yearFromAdapter = YearFromForSearchAdapter(settingsUseCase)
    private val yearToAdapter = YearToForSearchAdapter(settingsUseCase)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsYearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefRepository.initSettingsSharedPrefRepository(requireContext())
        binding.recyclerSearchFromYear.addItemDecoration(YearItemDecoration(24,8,24,8))
        binding.recyclerSearchToYear.addItemDecoration(YearItemDecoration(24,8,24,8))
        binding.recyclerSearchFromYear.adapter = yearFromAdapter
        binding.recyclerSearchToYear.adapter = yearToAdapter

        setYearFrom()
        setYearTo()
        binding.saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_settingsYearFragment_to_settingsSearchFragment)
        }
    }

    private fun setYearFrom(){
        viewModel.yearsFrom.onEach { yearsFrom ->
            yearFromAdapter.setYears(yearsFrom)
            binding.searchFromForward.setOnClickListener {
                viewModel.getYearsFromUp(yearsFrom.map { it + 12 })
            }
            binding.searchFromBack.setOnClickListener {
                viewModel.getYearsFromBack(yearsFrom.map { it - 12 })
            }
            binding.searchFromFirst.text = yearsFrom.first().toString()
            binding.searchFromLast.text = yearsFrom.last().toString()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun setYearTo(){
        viewModel.yearsTo.onEach { yearsTo ->
            yearToAdapter.setYears(yearsTo)
            binding.searchToForward.setOnClickListener {
                viewModel.getYearsToUp(yearsTo.map { it + 12 })
            }
            binding.searchToBack.setOnClickListener {
                viewModel.getYearsToBack(yearsTo.map { it - 12 })
            }
            binding.searchToFirst.text = yearsTo.first().toString()
            binding.searchToLast.text = yearsTo.last().toString()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}