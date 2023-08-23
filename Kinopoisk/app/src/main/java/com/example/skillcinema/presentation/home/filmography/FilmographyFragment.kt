package com.example.skillcinema.presentation.home.filmography

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FilmographyChipBinding
import com.example.skillcinema.databinding.FragmentFilmographyBinding
import com.example.skillcinema.entity.home.staff.FilmStaffEntity
import com.example.skillcinema.entity.home.staff.ProfessionEntity
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FilmographyFragment : Fragment() {
    private var _binding: FragmentFilmographyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmographyViewModel by viewModels()

    private var filmographyAdapter = FilmographyAdapter { film -> onFilmClick(film) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmographyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val staffId = arguments?.getInt(KEY_ID_STAFF)
        if (staffId != null) {
            viewModel.getInfoAboutStaff(staffId)
        }
        setFilmsStaffInChips()
    }

    private fun setFilmsStaffInChips(){
        viewModel.infoAboutStaff.onEach {
            binding.nameRuStaff.text = it?.nameRu
            it?.films?.let { it1 -> rangeFilms(it1) }
            if (it != null) {
                for (listFilm in listsFilms) {
                    setupChip(listFilm)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupChip(films: List<FilmStaffEntity>) {
        if (films.isNotEmpty()) {
            val chip = createChip(films)
            binding.chipGroup.addView(chip)
        }
    }

    private fun createChip(films: List<FilmStaffEntity>): Chip {
        val chip = FilmographyChipBinding.inflate(layoutInflater).root
        chip.text = films[0].professionKey?.profession + " " + films.size
        chip.setOnClickListener {
            binding.recyclerMovieFilmography.adapter = filmographyAdapter
            filmographyAdapter.setFilm(films)
        }
        return chip
    }

    private fun onFilmClick(film: FilmStaffEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_FILM, film.filmId)
        }
        findNavController().navigate(
            R.id.action_filmographyFragment_to_informationAboutFilmFragment,
            bundle
        )
    }

    private fun rangeFilms(films: List<FilmStaffEntity>) {
        for (film in films) {
            when (film.professionKey) {
                ProfessionEntity.WRITER -> listWriter.add(film)
                ProfessionEntity.OPERATOR -> listOperator.add(film)
                ProfessionEntity.EDITOR -> listEditor.add(film)
                ProfessionEntity.COMPOSER -> listComposer.add(film)
                ProfessionEntity.PRODUCER_USSR -> listProducerUssr.add(film)
                ProfessionEntity.HIMSELF -> listHimself.add(film)
                ProfessionEntity.HERSELF -> listHerself.add(film)
                ProfessionEntity.HRONO_TITR_MALE -> listHronoTitrMale.add(film)
                ProfessionEntity.HRONO_TITR_FEMALE -> listHronoTitrFemale.add(film)
                ProfessionEntity.TRANSLATOR -> listTraslator.add(film)
                ProfessionEntity.DIRECTOR -> listDirector.add(film)
                ProfessionEntity.DESIGN -> listDesign.add(film)
                ProfessionEntity.PRODUCER -> listProducer.add(film)
                ProfessionEntity.ACTOR -> listActor.add(film)
                ProfessionEntity.VOICE_DIRECTOR -> listVoiceDirector.add(film)
                else -> {
                    listUnknown.add(film)
                }
            }
        }
    }

    private var listWriter: MutableList<FilmStaffEntity> =
        emptyList<FilmStaffEntity>().toMutableList()
    private var listOperator: MutableList<FilmStaffEntity> = mutableListOf()
    private var listEditor: MutableList<FilmStaffEntity> = mutableListOf()
    private var listComposer: MutableList<FilmStaffEntity> = mutableListOf()
    private var listProducerUssr: MutableList<FilmStaffEntity> = mutableListOf()
    private var listHimself: MutableList<FilmStaffEntity> = mutableListOf()
    private var listHerself: MutableList<FilmStaffEntity> = mutableListOf()
    private var listHronoTitrMale: MutableList<FilmStaffEntity> = mutableListOf()
    private var listHronoTitrFemale: MutableList<FilmStaffEntity> = mutableListOf()
    private var listTraslator: MutableList<FilmStaffEntity> = mutableListOf()
    private var listDirector: MutableList<FilmStaffEntity> = mutableListOf()
    private var listDesign: MutableList<FilmStaffEntity> = mutableListOf()
    private var listProducer: MutableList<FilmStaffEntity> = mutableListOf()
    private var listActor: MutableList<FilmStaffEntity> =
        emptyList<FilmStaffEntity>().toMutableList()
    private var listVoiceDirector: MutableList<FilmStaffEntity> = mutableListOf()
    private var listUnknown: MutableList<FilmStaffEntity> = mutableListOf()

    private var listsFilms: MutableList<MutableList<FilmStaffEntity>> = mutableListOf(
        listActor,
        listWriter,
        listOperator,
        listEditor,
        listComposer,
        listProducerUssr,
        listHimself,
        listHerself,
        listHronoTitrMale,
        listHronoTitrFemale,
        listTraslator,
        listDirector,
        listDesign,
        listProducer,
        listVoiceDirector,
        listUnknown
    )

    companion object {
        private const val KEY_ID_FILM = "idFilm"
        private const val KEY_ID_STAFF = "staffId"
    }
}