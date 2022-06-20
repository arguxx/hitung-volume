package com.d3if2022.hitungvolume.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.d3if2022.hitungvolume.R
import com.d3if2022.hitungvolume.databinding.FragmentHitungBinding
import com.d3if2022.hitungvolume.db.Db
import com.d3if2022.hitungvolume.model.HasilHitung

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = Db.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater,container, false)
        setHasOptionsMenu(true)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.cari.setOnClickListener { cari() }
        binding.clear.setOnClickListener {
            binding.minInp.setText("")
            binding.hasil.setText("")
            binding.hasil2.setText("")
            binding.buttonGroup.visibility = View.GONE
        }
        viewModel.getHasilHitung().observe(requireActivity(), { showResult(it) })
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.scheduleUpdater(requireActivity().application)

    }
    private fun cari() {
        val masukan = binding.minInp.text.toString()
        if (TextUtils.isEmpty(masukan)) {
            Toast.makeText(context, R.string.blank, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitung(
            masukan.toFloat()
        )
    }

    private fun showResult(result: HasilHitung?) {
        if (result == null) return
        binding.hasil.text = getString(R.string.hasil_x, result.luas)
        binding.hasil2.text = getString(R.string.hasil_xx, result.volume)
        binding.buttonGroup.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> { findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun shareData(){
        val message = getString(R.string.bagikan_template,
            binding.hasil.text,
            binding.hasil2.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) { startActivity(shareIntent)
        }
    }

}