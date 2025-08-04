package com.example.lzycrazy.withoutlogin.marketplace

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var tvEmpty: TextView

    private lateinit var rvCategoryList: RecyclerView
    private lateinit var rvBannerList: RecyclerView

    private var allImagePosts: List<ImagePost> = listOf()
    private var allMarketPosts: List<MarketPost> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        rvCategoryList = findViewById(R.id.rvCategoryList)
        rvBannerList = findViewById(R.id.rvBannerList)
        tvEmpty = findViewById(R.id.tvEmpty)

        rvCategoryList.layoutManager = LinearLayoutManager(this)
        rvBannerList.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val categoriesDeferred = async { MarketplaceRetrofitInstance.api.getCategories() }
                val imagePostsDeferred = async { MarketplaceRetrofitInstance.api.getImagePosts() }

                val categoriesResponse = categoriesDeferred.await()
                val imagePostsResponse = imagePostsDeferred.await()

                val categories = categoriesResponse.body()?.data?.categories ?: emptyList()
                allImagePosts = imagePostsResponse.body()?.data?.filter { it.type == "image" } ?: emptyList()

                bannerAdapter = BannerAdapter(allImagePosts) { item ->
                    openPropertyDetailsFragment(item)
                }
                rvBannerList.adapter = bannerAdapter
                updateEmptyState(allImagePosts)

                categoryAdapter = CategoryAdapter(
                    categories,
                    { selectedCategory ->
                        val filteredPosts = allMarketPosts.filter { post ->
                            post.category?._id == selectedCategory._id
                        }
                        if (filteredPosts.isNotEmpty()) {
                            bannerAdapter.updatePosts(filteredPosts)
                            updateEmptyState(filteredPosts)
                        } else {
                            bannerAdapter.updatePosts(allImagePosts)
                            updateEmptyState(allImagePosts)
                        }
                    },
                    { categoryId, subcategoryName ->
                        fetchSubcategoryPosts(categoryId, subcategoryName)
                    }
                )

                rvCategoryList.adapter = categoryAdapter

            } catch (e: HttpException) {
                Toast.makeText(this@MarketplaceActivity, "Http Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@MarketplaceActivity, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun fetchSubcategoryPosts(categoryId: String, subcategoryName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    MarketplaceRetrofitInstance.api.getPostsBySubcategory(categoryId, subcategoryName)
                }

                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    allMarketPosts = posts
                    bannerAdapter.updatePosts(posts)
                    updateEmptyState(posts)
                } else {
                    Toast.makeText(this@MarketplaceActivity, "Failed to load posts", Toast.LENGTH_SHORT).show()
                    updateEmptyState(emptyList())
                }
            } catch (e: Exception) {
                Toast.makeText(this@MarketplaceActivity, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                updateEmptyState(emptyList())
            }
        }
    }

    private fun updateEmptyState(posts: List<Any>) {
        if (posts.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            tvEmpty.text = "There is no listing for this type of category"
            rvBannerList.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            rvBannerList.visibility = View.VISIBLE
        }
    }

    private fun openPropertyDetailsFragment(item: Any) {
        when (item) {
            is MarketPost -> {
                val fragment = MarketplacePropertyDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("marketPost", item)
                    }
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            is ImagePost -> {
                Toast.makeText(this, "Image post clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}