package com.pevahouse.criminalintent

import android.widget.CheckBox
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.pevahouse.criminalintent.databinding.CrimeFragmentDetailBinding
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CrimeFragmentDetailTest {

    private lateinit var scenario: FragmentScenario<CrimeFragmentDetail>
    private  var _binding: CrimeFragmentDetailBinding? = null
    private val binding get() = checkNotNull(_binding) {"Cannot access binding because it is null. Is the view Visible?"}
    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
        scenario.onFragment { fragment ->
            _binding = fragment.binding
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }


    @Test fun checkbox_updates_crime() {
//        binding.crime_Solved.performClick()
//
//        assertTrue(binding.crime_Solved.isChecked)
        binding.root.findViewById<CheckBox>(R.id.crime_Solved).performClick()

        // Check if the checkbox is checked using View Binding
        assertTrue(binding.root.findViewById<CheckBox>(R.id.crime_Solved).isChecked)
    }
}
//class CrimeDetailFragmentTest {
//
//    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>
//
//    @Before
//    fun setUp() {
//        scenario = launchFragmentInContainer()
//    }
//
//    @After
//    fun tearDown() {
//        scenario.close()
//    }
//
//    @Test
//    fun checkBoxStateEqualsCrimeSolvedState() {
//        scenario.onFragment {
//                fragment ->
//            val crimeSolved = fragment.crime.isSolved
//
//            assertEquals(crimeSolved, true)
////            onView(withId(R.id.crime_solved)).check(matches(isChecked()))
//        }
//
//    }
//}