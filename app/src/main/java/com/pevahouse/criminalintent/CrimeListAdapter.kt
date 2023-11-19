package com.pevahouse.criminalintent

import android.text.format.DateFormat.getDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pevahouse.criminalintent.databinding.ActivityMainBinding.bind
import com.pevahouse.criminalintent.databinding.CrimeFragmentDetailBinding.bind
import com.pevahouse.criminalintent.databinding.ListItemCrimeBinding
import com.pevahouse.criminalintent.databinding.ListItemCrimePoliceBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        binding.crimeTitle.text = crime.title
        val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(crime.date)
        binding.crimeDate.text = formattedDate


        binding.root.setOnClickListener {
            onCrimeClicked(crime.id)
        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}


class CrimeHolderPolice(
    private val binding: ListItemCrimePoliceBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime){
        binding.crimeTitle.text = crime.title

        val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(crime.date)
        binding.crimeDate.text = formattedDate


        binding.root.setOnClickListener{
            Toast.makeText(binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT).show()
        }
    }
}


class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val onCrimeClicked: (crimeId: UUID) -> Unit ) : RecyclerView.Adapter<CrimeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime, onCrimeClicked)
    }

    override fun getItemCount() = crimes.size
}








//class CrimeHolder(
//    private val binding: ListItemCrimeBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(crime: Crime) {
//            binding.crimeTitle.text = crime.title
//            binding.crimeDate.text = crime.date.toString()
//
//            binding.root.setOnClickListener{
//                Toast.makeText(
//                    binding.root.context,
//                    "${crime.title} clicked!",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//}
//class CrimeListAdapter(
//    private val crimes: List<Crime>
//    ) : RecyclerView.Adapter<CrimeHolder>() {
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ) : CrimeHolder{
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
//        return CrimeHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CrimeHolder, position:Int) {
//        val crime = crimes[position]
////        holder.apply{
////            binding.crimeTitle.text = crime.title
////            binding.crimeDate.text = crime.date.toString()
////        }
//        holder.bind(crime)
//    }
//
//    override fun getItemCount() = crimes.size
//
//}