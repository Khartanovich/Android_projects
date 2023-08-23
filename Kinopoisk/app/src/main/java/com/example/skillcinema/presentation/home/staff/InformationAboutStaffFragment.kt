package com.example.skillcinema.presentation.home.staff

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentInformationAboutStaffBinding
import com.example.skillcinema.ui.home.BestFilmStaffAdapter
import com.example.skillcinema.ui.home.OnItemClickBestFilm
import com.example.skillcinema.data.home.dto.InformationAboutFilmDto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class InformationAboutStaffFragment : Fragment(), OnItemClickBestFilm {

    private var _binding: FragmentInformationAboutStaffBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InformationAboutStaffViewModel by viewModels()
    private val staffAdapter = BestFilmStaffAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationAboutStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.staffRecyclerBestFilm.adapter = staffAdapter
        val staffId = arguments?.getInt(KEY_ID_STAFF)
        if (staffId != null) {
            viewModel.getInfoAboutStaff(staffId)
        }
        setInfoAboutStaff()
        binding.goAllFilms.setOnClickListener {
            val bundle = Bundle().apply {
                if (staffId != null) {
                    putInt(KEY_ID_STAFF, staffId)
                }
            }
            findNavController().navigate(
                R.id.action_informationAboutStaffFragment_to_filmographyFragment,
                bundle
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(movie: InformationAboutFilmDto) {
        val bundle = Bundle().apply {
            movie.kinopoiskId?.let { putInt(KEY_ID_FILM, it) }
        }
        findNavController().navigate(
            R.id.action_informationAboutStaffFragment_to_informationAboutFilmFragment,
            bundle
        )
    }

    private fun setInfoAboutStaff() {
        viewModel.infoAboutStaff.onEach {
            binding.nameStaffRu.text = it?.nameRu
            binding.nameStaffEn.text = it?.nameEn
            binding.profession.text = it?.profession
            Glide.with(this).load(it?.posterUrl).into(binding.posterUrlStaff)
            binding.All.text = it?.films?.size.toString()
            viewModel.getBestFilmStaff()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.bestFilmStaff.onEach {
            staffAdapter.setData(it as List<InformationAboutFilmDto>)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        const val KEY_ID_STAFF = "staffId"
        const val KEY_ID_FILM = "idFilm"
    }
}