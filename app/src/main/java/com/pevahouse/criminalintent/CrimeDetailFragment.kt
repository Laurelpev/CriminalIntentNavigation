package com.pevahouse.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pevahouse.criminalintent.databinding.CrimeFragmentDetailBinding
import kotlinx.coroutines.launch

private const val TAG = "CrimeFragmentDetail"
class CrimeFragmentDetail : Fragment() {

//    private lateinit var binding: CrimeFragmentDetailBinding
    private var _binding: CrimeFragmentDetailBinding? = null
    val binding get() = checkNotNull(_binding) {"Cannot access binding because it is null. Is the view Visible?"}
//    lateinit var crime: Crime

    private val args: CrimeFragmentDetailArgs by navArgs()

    private val crimeDetailViewModel: CrimeDetailViewModel by viewModels {
        CrimeDetailViewModelFactory(args.crimeId)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        crime = Crime(
//            id = UUID.randomUUID(),
//            title = "",
//            date = Date(),
//            isSolved = false
//        )
//        Log.d(TAG, "The crime ID is: ${args.crimeId}")
//
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CrimeFragmentDetailBinding.inflate(inflater, container, false)
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crimeDetailViewModel.updateCrime { oldCrime ->
                    oldCrime.copy(title = text.toString())
                }
            }
            crimeDate.apply {
                isEnabled = false
            }
            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crimeDetailViewModel.updateCrime { oldCrime ->
                    oldCrime.copy(isSolved = isChecked)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                crimeDetailViewModel.crime.collect { crime ->
                    crime?.let { updateUi(it) }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
        callback.remove()
    }

    private fun updateUi(crime: Crime) {
        binding.apply {
            if (crimeTitle.text.toString() != crime.title) {
                crimeTitle.setText(crime.title)
            }

            crimeDate.text = crime.date.toString()
            crimeSolved.isChecked = crime.isSolved
        }
    }

    private val callback = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() {
            if (crimeDetailViewModel.crime.value?.title.isNullOrBlank()) {
                Toast.makeText(requireContext(), "A Description of the Incident is Needed", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().popBackStack()
            }
        }
    }
}