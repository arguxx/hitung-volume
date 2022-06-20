package com.d3if2022.hitungvolume.ui.histori

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.d3if2022.hitungvolume.R
import com.d3if2022.hitungvolume.data.SettingDataStore
import com.d3if2022.hitungvolume.databinding.FragmentHistoryBinding
import com.d3if2022.hitungvolume.db.Db
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HistoryFragment : Fragment() {
    private lateinit var myAdapter: HistoryAdapter
    private lateinit var layoutDataStore: SettingDataStore

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by lazy {
        val db = Db.getInstance(requireContext())
        val factory = HistoryViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoryViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHistoryBinding.inflate(layoutInflater,
            container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        myAdapter = HistoryAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
            viewModel.data.observe(viewLifecycleOwner, {
                binding.emptyView.visibility = if (it.isEmpty())
                    View.VISIBLE else View.GONE
                myAdapter.submitList(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.hapusData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}
