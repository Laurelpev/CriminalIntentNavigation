package com.pevahouse.criminalintent

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

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime){
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener{
            Toast.makeText(binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT).show()
        }
    }
}

class CrimeHolderPolice(
    private val binding: ListItemCrimePoliceBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime){
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.crimePolice.text = crime.needPolice.toString()

        binding.root.setOnClickListener{
            Toast.makeText(binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT).show()
        }
    }
}

class CrimeListAdapter(private val crimes: List<Crime>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
//        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
//        return CrimeHolder(binding)
        return when (viewType) {
            1 -> {
                val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                CrimeHolderPolice(binding)
            }
            else -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> {
                holder.bind(crime)
            }
            is CrimeHolderPolice -> {
                holder.bind(crime)
            }
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        return when(crimes[position].needPolice){
            true -> 1
            else -> 2
        }
    }
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