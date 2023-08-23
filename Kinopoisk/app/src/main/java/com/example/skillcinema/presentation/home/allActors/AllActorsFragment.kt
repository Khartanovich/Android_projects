package com.example.skillcinema.presentation.home.allActors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.data.home.InfoAboutFilmScreenRepositoryIml
import com.example.skillcinema.data.App
import com.example.skillcinema.databinding.FragmentAllActorsBinding
import com.example.skillcinema.domain.home.usecaseinfofilm.GetAllActorsUseCase
import com.example.skillcinema.entity.home.AllActorsEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AllActorsFragment : Fragment() {

    private var _binding : FragmentAllActorsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllActorsViewModel by viewModels()
    private val allActorsAdapter = AllActorsAdapter { allActorsModel -> onClickItemStaff(allActorsModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            it.getInt("idFilm")
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllActorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idFilm = arguments?.getInt(KEY_ID_FILM)
        if(idFilm != null) {
            viewModel.getAllActors(idFilm)
        }

        binding.recyclerAllActors.adapter = allActorsAdapter
        viewModel.allActors.onEach {
            allActorsAdapter.setActors(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickItemStaff(staff: AllActorsEntity){
        val bundle = Bundle().apply {
            putInt(KEY_ID_STAFF, staff.staffId)
        }
        findNavController().navigate(R.id.action_allActorsFragment_to_informationAboutStaffFragment, bundle)
    }

    companion object {
        private const val KEY_ID_STAFF = "staffId"
        private const val KEY_ID_FILM = "idFilm"
    }
}