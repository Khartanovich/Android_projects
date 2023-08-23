package com.example.skillcinema.presentation.home.infofilm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentInformationAboutFilmBinding
import com.example.skillcinema.databinding.ItemMoreMenuBinding
import com.example.skillcinema.presentation.profile.DialogFragmentCreateNewCollection
import com.example.skillcinema.entity.home.AllActorsEntity
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.SimilarFilmEntity
import com.example.skillcinema.entity.profile.CollectionsAndFilms
import com.example.skillcinema.entity.profile.FilmEntity
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import com.example.skillcinema.presentation.home.allActors.AllActorsAdapter
import com.example.skillcinema.ui.home.informationAboutFilm.CreateNewCollectionInMoreMenuAdapter
import com.example.skillcinema.ui.home.informationAboutFilm.OnCheckBoxClick
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InformationAboutFilmFragment : Fragment(), OnCheckBoxClick {

    private var _binding: FragmentInformationAboutFilmBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InformationAboutFilmViewModel by viewModels()

    private val allActorsAdapter =
        AllActorsAdapter { allActorsModel -> onClickItemStaff(allActorsModel) }
    private val otherStaffAdapter =
        AllActorsAdapter { allActorsModel -> onClickItemStaff(allActorsModel) }
    private val imagesAdapter = ImagesGalleryPagingAdapter()
    private val similarAdapter =
        SimilarFilmsAdapter { similarFilm -> onClickItemSimilarFilm(similarFilm) }
    private var onlyActors = mutableListOf<AllActorsEntity>()
    private var otherStaff = mutableListOf<AllActorsEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationAboutFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idFilm = arguments?.getInt(KEY_ID_FILM)
        binding.recyclerAllActors.adapter = allActorsAdapter
        binding.recyclerOtherStaff.adapter = otherStaffAdapter
        binding.recyclerGallery.adapter = imagesAdapter
        binding.recyclerSimilarFilms.adapter = similarAdapter
        if (idFilm != null) {
            checkAndCleanDataBase(idFilm)
            stateFavoriteAndWantToSee(idFilm)
            stateIsViewed(idFilm)
            viewModel.getInformation(idFilm)
            viewModel.getAllActors(idFilm)
            viewModel.getSimilarFilms(idFilm)
            getActorsAndStaff(idFilm)
        }
        val bundleIdFilm = Bundle().apply {
            if (idFilm != null) {
                putInt(KEY_ID_FILM, idFilm)
            }
        }
        setInfoAboutFilm()
        idFilm?.let {
            setImagesGallery(it)
        }
        binding.allImagesGallery.setOnClickListener {
            findNavController().navigate(
                R.id.action_informationAboutFilmFragment_to_galleryFragment,
                bundleIdFilm
            )
        }
        setSimilarFilms()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setInfoAboutFilm() {
        viewModel.information.onEach { filmInfo ->
            if (filmInfo != null) {
                Glide.with(this).load(filmInfo.posterUrlPreview).into(binding.posterUrl)
                binding.ratingAndName.text = if (filmInfo.ratingImdb == null) {
                    filmInfo.nameRu
                } else {
                    "${filmInfo.ratingImdb} ${filmInfo.nameRu}"
                }
                binding.yerAndGenres.text =
                    "${filmInfo.year} ${filmInfo.genres?.joinToString(", ") { it?.genre ?: "" }}"
                if (filmInfo.logoUrl != null) {
                    Glide.with(this).load(filmInfo.logoUrl).into(binding.logoUrl)
                }
                binding.description.text = filmInfo.description
                clickViewed(filmInfo)
                clickFavorite(filmInfo)
                clickWantToSee(filmInfo)
                showMoreMenu(filmInfo)
                clickShare(filmInfo)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setImagesGallery(filmId: Int) {
        viewModel.getImagesGallery(filmId, TYPE_IMAGE).onEach { imageItem ->
            imagesAdapter.submitData(imageItem)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setSimilarFilms() {
        viewModel.similarFilms.onEach {
            if (it != null)
                similarAdapter.setSimilarFilm(it.items)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClickItemStaff(staff: AllActorsEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_STAFF, staff.staffId)
        }
        findNavController().navigate(
            R.id.action_informationAboutFilmFragment_to_informationAboutStaffFragment,
            bundle
        )
    }

    private fun onClickItemSimilarFilm(film: SimilarFilmEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_FILM, film.filmId)
        }
        findNavController().navigate(R.id.action_informationAboutFilmFragment_self, bundle)
    }

    private fun createFilmEntity(
        filmInfo: InformationAboutFilmEntity,
        viewed: Boolean,
    ): FilmEntity {
        return FilmEntity(
            kinopoiskId = filmInfo.kinopoiskId!!,
            nameRu = filmInfo.nameRu!!,
            posterUrlPreview = filmInfo.posterUrlPreview!!,
            genres = filmInfo.genres!!.joinToString(", ") { it!!.genre!! },
            year = filmInfo.year!!,
            ratingImdb = filmInfo.ratingImdb,
            isViewed = viewed,
        )
    }

    private fun stateIsViewed(filmId: Int) {
        viewModel.getFilmsIsViewed.onEach {
            for (film in it) {
                if (film.kinopoiskId == filmId) {
                    binding.viewed.isChecked = film.isViewed
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun stateFavoriteAndWantToSee(filmId: Int) {
        viewLifecycleOwner.lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getAllCrossReference.collect {
                        binding.isFavorite.isChecked =
                            it.contains(
                                FilmsAndCollectionsCrossRef(
                                    filmId,
                                    NAME_COLLECTION_FAVORITE
                                )
                            )
                        binding.wantToSee.isChecked = it.contains(
                            FilmsAndCollectionsCrossRef(
                                filmId,
                                NAME_COLLECTION_WANT_TO_SEE
                            )
                        )
                    }
                }
            }
    }

    private fun checkAndCleanDataBase(filmId: Int) {
        viewModel.getAllFilmsDataBase.onEach {
            for (filmAndCollection in it) {
                if (filmAndCollection.film.kinopoiskId == filmId && filmAndCollection.listCollections.isNullOrEmpty() && !filmAndCollection.film.isViewed) {
                    viewModel.deleteFilmFromDataBase(filmId)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun clickFavorite(filmInfo: InformationAboutFilmEntity) {
        binding.isFavorite.setOnClickListener { checkBox ->
            if (binding.isFavorite.isChecked) {
                viewModel.insertFilmsAndCollectionCrossRef(
                    filmInfo.kinopoiskId!!,
                    NAME_COLLECTION_FAVORITE
                )
                viewModel.insertFilmToDataBase(createFilmEntity(filmInfo, false))
            } else {
                viewModel.deleteCrossReference(filmInfo.kinopoiskId!!, NAME_COLLECTION_FAVORITE)
            }
        }
    }

    private fun clickWantToSee(filmInfo: InformationAboutFilmEntity) {
        binding.wantToSee.setOnClickListener {
            if (binding.wantToSee.isChecked) {
                viewModel.insertFilmsAndCollectionCrossRef(
                    filmInfo.kinopoiskId!!,
                    NAME_COLLECTION_WANT_TO_SEE
                )
                viewModel.insertFilmToDataBase(createFilmEntity(filmInfo, false))
            } else {
                viewModel.deleteCrossReference(filmInfo.kinopoiskId!!, NAME_COLLECTION_WANT_TO_SEE)
            }
        }
    }

    private fun clickViewed(filmInfo: InformationAboutFilmEntity) {
        binding.viewed.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                viewModel.insertFilmToDataBase(
                    createFilmEntity(filmInfo, true)
                )
                viewModel.updateFilmIsViewed(createFilmEntity(filmInfo, isChecked))
            } else {
                viewModel.insertFilmToDataBase(
                    createFilmEntity(filmInfo, false)
                )
                viewModel.updateFilmIsViewed(createFilmEntity(filmInfo, isChecked))
            }
        }
    }

    private fun showMoreMenu(filmInfo: InformationAboutFilmEntity) {
        binding.showMoreMenu.setOnClickListener {
            val bindingSheet = ItemMoreMenuBinding.inflate(layoutInflater)
            val bottomSheetDialogFragment = BottomSheetDialog(requireContext())
            val newCollectionInMoreMenuAdapter =
                CreateNewCollectionInMoreMenuAdapter(this, filmInfo)
            Glide.with(this).load(filmInfo.posterUrlPreview).into(bindingSheet.posterUrl)
            bindingSheet.rating.text = filmInfo.ratingImdb.toString()
            bindingSheet.title.text = filmInfo.nameRu
            bindingSheet.genres.text =
                "${filmInfo.year} ${filmInfo.genres?.joinToString(", ") { it?.genre ?: "" }}"
            bindingSheet.linearCreateNewCollection.setOnClickListener {
                showDialogCreateNewCollection()
            }
            setupDialogListener(filmInfo)
            bindingSheet.recyclerCollectionInMoreMenu.adapter = newCollectionInMoreMenuAdapter
            viewModel.getAllCollectionsDataBase.onEach {
                newCollectionInMoreMenuAdapter.setData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            bindingSheet.buttonClose.setOnClickListener {
                bottomSheetDialogFragment.dismiss()
            }
            bottomSheetDialogFragment.setCancelable(false)
            bottomSheetDialogFragment.setContentView(bindingSheet.root)
            bottomSheetDialogFragment.show()
        }
    }

    private fun showDialogCreateNewCollection() {
        val dialogFragment = DialogFragmentCreateNewCollection()
        dialogFragment.show(parentFragmentManager, DialogFragmentCreateNewCollection.TAG)
    }

    private fun setupDialogListener(filmInfo: InformationAboutFilmEntity) {
        parentFragmentManager.setFragmentResultListener(
            DialogFragmentCreateNewCollection.REQUEST_KEY,
            viewLifecycleOwner,
            FragmentResultListener { requestKey, result ->
                val collectionName =
                    result.getString(DialogFragmentCreateNewCollection.KEY_RESPONSE)
                if (collectionName != null) {
                    viewModel.insertNewCollection(collectionName)
                    viewModel.insertFilmToDataBase(createFilmEntity(filmInfo, false))
                    viewModel.insertFilmsAndCollectionCrossRef(
                        filmInfo.kinopoiskId!!,
                        collectionName
                    )
                }
            })
    }

    override fun addFilm(collection: CollectionsAndFilms, filmInfo: InformationAboutFilmEntity) {
        viewModel.insertFilmToDataBase(createFilmEntity(filmInfo, false))
        viewModel.insertFilmsAndCollectionCrossRef(
            filmInfo.kinopoiskId!!,
            collection.collection.collectionName
        )
    }

    override fun deleteFilm(collection: CollectionsAndFilms, filmInfo: InformationAboutFilmEntity) {
        viewModel.deleteCrossReference(filmInfo.kinopoiskId!!, collection.collection.collectionName)
        checkAndCleanDataBase(filmInfo.kinopoiskId!!)
    }

    override fun checkDataBase(
        collection: CollectionsAndFilms,
        filmInfo: InformationAboutFilmEntity
    ): Boolean {
        var isContains = false
        viewLifecycleOwner.lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getAllCrossReference.collect {
                        isContains = it.contains(
                            FilmsAndCollectionsCrossRef(
                                filmInfo.kinopoiskId!!,
                                collection.collection.collectionName
                            )
                        )
                    }
                }
            }
        return isContains
    }

    private fun clickShare(filmInfo: InformationAboutFilmEntity) {
        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, filmInfo.webUrl)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun getActorsAndStaff(idFilm: Int?) {
        viewModel.allActors.onEach {
            for (actor in it) {
                if (actor.professionKey == PROFESSION_KEY_ACTOR) {
                    onlyActors.add(actor)
                } else otherStaff.add(actor)
            }
            if (onlyActors.size > 20) {
                binding.countAllActors.isVisible = true
                binding.arrowRight.isVisible = true
                binding.countAllActors.text = onlyActors.size.toString()
                binding.countAllActors.setOnClickListener {
                    val bundle = Bundle().apply {
                        if (idFilm != null) {
                            putInt(KEY_ID_FILM, idFilm)
                        }
                    }
                    findNavController().navigate(
                        R.id.action_informationAboutFilmFragment_to_allActorsFragment,
                        bundle
                    )
                }
                allActorsAdapter.setActors(onlyActors.take(20))
            } else {
                allActorsAdapter.setActors(onlyActors)
                binding.countAllActors.isVisible = false
                binding.arrowRight.isVisible = false
            }
            if (otherStaff.size > 6) {
                binding.countOtherStaff.isVisible = true
                binding.arrowRight2.isVisible = true
                binding.countOtherStaff.text = otherStaff.size.toString()
                binding.countOtherStaff.setOnClickListener {
                    val bundle = Bundle().apply {
                        if (idFilm != null) {
                            putInt(KEY_ID_FILM, idFilm)
                        }
                    }
                    findNavController().navigate(
                        R.id.action_informationAboutFilmFragment_to_allActorsFragment,
                        bundle
                    )
                }
                otherStaffAdapter.setActors(otherStaff.take(6))
            } else {
                otherStaffAdapter.setActors(otherStaff)
                binding.countOtherStaff.isVisible = false
                binding.arrowRight2.isVisible = false
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        const val NAME_COLLECTION_FAVORITE = "Любимые"
        const val NAME_COLLECTION_WANT_TO_SEE = "Хочу посмотреть"
        const val PROFESSION_KEY_ACTOR = "ACTOR"
        const val KEY_ID_FILM = "idFilm"
        const val TYPE_IMAGE = "STILL"
        const val KEY_ID_STAFF = "staffId"
    }
}