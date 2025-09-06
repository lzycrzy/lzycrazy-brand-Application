package com.example.lzycrazy.withoutlogin.marketplace

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lzycrazy.R
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var tvEmpty: TextView
    private lateinit var tvCategoryTitle: TextView

    private lateinit var bannerContainer: View
    private lateinit var banner2: ShapeableImageView
    private lateinit var banner3: ShapeableImageView
    private lateinit var banner4: ShapeableImageView
    private lateinit var rvCategoryList: RecyclerView
    private lateinit var rvBannerList: RecyclerView

    private var allImagePosts: List<ImagePost> = listOf()
    private var allMarketPosts: List<MarketPost> = listOf()
    private var isShowingSubcategory = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        initViews()
        fetchData()
    }

    private fun initViews() {
        rvCategoryList = findViewById(R.id.rvCategoryList)
        rvBannerList = findViewById(R.id.rvBannerList)
        tvEmpty = findViewById(R.id.tvEmpty)
        tvCategoryTitle = findViewById(R.id.tvCategoryTitle)
        bannerContainer = findViewById(R.id.bannerContainer)

        banner2 = findViewById(R.id.banner2)
        banner3 = findViewById(R.id.banner3)
        banner4 = findViewById(R.id.banner4)

        rvCategoryList.layoutManager = LinearLayoutManager(this)
        rvBannerList.layoutManager = LinearLayoutManager(this)

        // Initialize banner adapter for subcategory items
        bannerAdapter = BannerAdapter(emptyList()) { item ->
            openPropertyDetailsFragment(item)
        }
        rvBannerList.adapter = bannerAdapter
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

                setupBanners(allImagePosts)
                setupCategoryList(categories)

            } catch (e: Exception) {
                Toast.makeText(this@MarketplaceActivity, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupBanners(posts: List<Any>) {
        // Hide static banners initially
        banner2.visibility = View.GONE
        banner3.visibility = View.GONE
        banner4.visibility = View.GONE

        if (posts.isEmpty()) {
            updateEmptyState(posts)
            return
        }

        val totalPosts = posts.size

        val staticBanners = if (totalPosts <= 3) emptyList() else posts.takeLast(3)
        val viewPagerPosts = if (totalPosts <= 3) posts else posts.dropLast(3)

        // Setup static banners
        val staticViews = listOf(banner2, banner3, banner4)
        staticBanners.forEachIndexed { index, post ->
            val imageView = staticViews.getOrNull(index)
            if (imageView != null) {
                imageView.visibility = View.VISIBLE
                loadMediaIntoImageView(imageView, post)
            }
        }

        // Setup ViewPager using Fragment
        val viewPagerImageUrls = viewPagerPosts.map { post ->
            when (post) {
                is ImagePost -> post.postUrl
                is MarketPost -> post.images.firstOrNull() ?: ""
                else -> ""
            }
        }

        val viewPagerFragment = MarketplaceBannerImagesFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList("imageUrls", ArrayList(viewPagerImageUrls))
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.viewPagerBannerNew, viewPagerFragment)
            .commit()

        updateEmptyState(posts)
    }

    private fun loadMediaIntoImageView(imageView: ShapeableImageView, post: Any) {
        val context = imageView.context
        val placeholder = R.drawable.ic_real_estate_banner

        when (post) {
            is ImagePost -> {
                Glide.with(context)
                    .load(post.postUrl)
                    .placeholder(placeholder)
                    .centerCrop()
                    .into(imageView)
            }
            is MarketPost -> {
                val imageUrl = post.images.firstOrNull()
                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(context)
                        .load(imageUrl)
                        .placeholder(placeholder)
                        .centerCrop()
                        .into(imageView)
                } else {
                    imageView.setImageResource(placeholder)
                }
            }
        }

        imageView.setOnClickListener {
            openPropertyDetailsFragment(post)
        }
    }

    private fun setupCategoryList(categories: List<Category>) {
        categoryAdapter = CategoryAdapter(
            categories,
            { selectedCategory ->
                // When a category is clicked, show banners and hide subcategory items
                isShowingSubcategory = false
                bannerContainer.visibility = View.VISIBLE
                rvBannerList.visibility = View.GONE

                tvCategoryTitle.text = selectedCategory.name

                val filteredPosts = allMarketPosts.filter {
                    it.category?._id == selectedCategory._id
                }

                if (filteredPosts.isNotEmpty()) {
                    setupBanners(filteredPosts)
                } else {
                    setupBanners(allImagePosts)
                }
            },
            { categoryId, subcategoryName ->
                // When a subcategory is clicked, fetch and show subcategory items
                isShowingSubcategory = true
                fetchSubcategoryPosts(categoryId, subcategoryName)
            }
        )
        rvCategoryList.adapter = categoryAdapter
    }

    private fun fetchSubcategoryPosts(categoryId: String, subcategoryName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    MarketplaceRetrofitInstance.api.getPostsBySubcategory(
                        categoryId,
                        subcategoryName
                    )
                }

                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    allMarketPosts = posts

                    // Hide banners and show subcategory items
                    bannerContainer.visibility = View.GONE
                    rvBannerList.visibility = View.VISIBLE

                    // Update the banner adapter with subcategory items
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

            if (isShowingSubcategory) {
                rvBannerList.visibility = View.GONE
            } else {
                bannerContainer.visibility = View.GONE
            }
        } else {
            tvEmpty.visibility = View.GONE

            if (isShowingSubcategory) {
                rvBannerList.visibility = View.VISIBLE
                bannerContainer.visibility = View.GONE
            } else {
                rvBannerList.visibility = View.GONE
                bannerContainer.visibility = View.VISIBLE
            }
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

    private fun resetCategoryTitle() {
        tvCategoryTitle.text = "Categories"
    }
}