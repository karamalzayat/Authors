package com.example.authors.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authors.R
import com.example.authors.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: AuthorsListAdapter

    private val mViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
        setContentView(binding.root)
        adapter = AuthorsListAdapter(
            this@HomeActivity,
            this@HomeActivity
        )
        binding.recyclerCommon.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = this@HomeActivity.adapter
        }

        setupFlowMonitor()
        mViewModel.getAuthors()
        binding.homeImgLoading.visibility = View.VISIBLE
    }

    private fun setupFlowMonitor() {
        mViewModel.statusList.observe(this){
            binding.homeImgLoading.visibility = View.GONE
            adapter.submitList(it.toList())
        }
    }

    private fun openSelectedAuthorActivity(context: Context, selectedAuthor: AuthorModel): Intent {
        val openSelectedAuthorIntent = Intent(context, SelectedPhotoActivity::class.java)
        openSelectedAuthorIntent.putExtra("selectedAuthor", selectedAuthor)
        return openSelectedAuthorIntent
    }

    override fun onClick(authorModel: AuthorModel) {
        startActivity(
            openSelectedAuthorActivity(
                this@HomeActivity,
                authorModel
            )
        )

        Toast.makeText(
            applicationContext,
            "${authorModel.authorName}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onLoadMore(position: Int) {
        mViewModel.loadMore(position)
    }
}