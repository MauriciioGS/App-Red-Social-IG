package com.unam.appredsocialig.ui.fragments

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unam.appredsocialig.BaseFragment
import com.unam.appredsocialig.R
import com.unam.appredsocialig.data.PostApi
import com.unam.appredsocialig.data.PostList
import com.unam.appredsocialig.databinding.FragmentFeedBinding
import com.unam.appredsocialig.network.RetrofitInstance
import com.unam.appredsocialig.ui.fragments.recyclerviews.AdapterHistories
import com.unam.appredsocialig.ui.fragments.recyclerviews.AdapterPosts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment : BaseFragment<FragmentFeedBinding>(
    R.layout.fragment_feed, FragmentFeedBinding::bind){

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var layoutManagerHist: RecyclerView.LayoutManager? = null
    private var adapterPost: RecyclerView.Adapter<AdapterPosts.ViewHolder>? = null
    private var adapterHistories: RecyclerView.Adapter<AdapterHistories.ViewHolder>? = null
    private var postList = mutableListOf<PostApi>()
    private lateinit var call : Call<PostList>

    override fun initElements() {
        showCollapsingToolBar(true)

        layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFeed.layoutManager = layoutManager

        layoutManagerHist = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewHistories.layoutManager = layoutManagerHist

        adapterPost = AdapterPosts(postList, requireContext())
        binding.recyclerViewFeed.adapter = adapterPost
        binding.recyclerViewFeed.setHasFixedSize(true)

        adapterHistories = AdapterHistories(postList, requireContext())
        binding.recyclerViewHistories.adapter = adapterHistories
        binding.recyclerViewHistories.setHasFixedSize(true)

        val apiService = RetrofitInstance.api
        call = apiService.getPost("character")
        call.enqueue(object: Callback<PostList> {
            override fun onResponse(call: Call<PostList>, response: Response<PostList>) {
                postList.addAll(response.body()!!.results)
                binding.recyclerViewFeed.adapter?.notifyDataSetChanged()
                binding.recyclerViewHistories.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PostList>, t: Throwable) {
                val toast = Toast.makeText(requireContext(),"ERROR: "+t.message+t.stackTrace+" TRY AGAIN",
                    Toast.LENGTH_LONG)
                toast.show()
                t.message?.let { Log.e("RETROFIT: ", it) }
                t.stackTrace
            }
        })
    }


}