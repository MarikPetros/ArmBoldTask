package com.marik.armboldtask.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.marik.armboldtask.R
import com.marik.armboldtask.R.*
import com.marik.armboldtask.adapter.CategoriesAdapter
import com.marik.armboldtask.adapter.CategoryViewHolder
import com.marik.armboldtask.model.Category
import com.marik.armboldtask.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*

/**
 *  Fragment for home screen of application
 */
class HomeFragment : Fragment() {
    // Originally this info will be brought from back end
    val catList = mutableListOf(
        Category(
            "Cakes",
            drawable.nopath28,
            drawable.cakes_card_background,
            color.cakes_card_shadow,
            listOf()
        ),
        Category(
            "Chocolate",
            drawable.nopath29,
            drawable.chocolate_card_background,
            color.chocolate_card_shadow,
            listOf()
        ),
        Category(
            "Cookies",
            drawable.nopath30,
            drawable.cookies_card_background,
            color.cookies_card_shadow,
            listOf()
        ),
    )

    private val priceText: String = "\u058F" + "/kg"

    // Originally this info will be brought from back end
    private val popularProducts = listOf(
        Product(
            "Biscuits with chocolate",
            "Biscuits",
            drawable.nopath31,
            drawable.healthier_chocolate_candy_bars_vegan_gluten_free_32,
            "2500",
            true,
            string.product_description,
            string.product_ingredients,
            string.product_shelf_life,
            1
        ),
        Product(
            "Candy chocolate circles",
            "Candy chocolates",
            drawable.nopath18,
            drawable.healthier_chocolate_candy_bars_vegan_gluten_free_32,
            "2500",
            true,
            string.product_description,
            string.product_ingredients,
            string.product_shelf_life,
            2
        ),
        Product(
            "Jelly Gummy",
            "Candies",
            drawable.nopath19,
            drawable.nopath_27,
            "2500",
            false,
            string.product_description,
            string.product_ingredients,
            string.product_shelf_life,
            3
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        activity?.bottom_nav_view?.visibility = View.VISIBLE
        // Initialize recycler view
        initializeRecyclerView()

        // Initialize popular products list
        initPopularProducts()
    }

    private fun initPopularProducts() {
        setPopularProduct(popularProducts[0])
        setPopularProduct(popularProducts[1])
        setPopularProduct(popularProducts[2])
    }

    private fun setPopularProduct(product: Product) {
        when (product.popularity) {
            1 -> {
                product_name1.text = product.itemName
                product_picture1_1.background =
                    ResourcesCompat.getDrawable(resources, product.itemImage1, null)
                txt_popular_price1.text = product.price + priceText
                setOnTouch(card_view_1, product)
            }
            2 -> {
                product_name2.text = product.itemName
                product_picture1_2.background =
                    ResourcesCompat.getDrawable(resources, product.itemImage1, null)
                txt_popular_price2.text = product.price + priceText
                setOnTouch(card_view_2, product)
            }
            3 -> {
                product_name3.text = product.itemName
                product_picture1_3.background =
                    ResourcesCompat.getDrawable(resources, product.itemImage1, null)
                txt_popular_price3.text = product.price + priceText
                setOnTouch(card_view_3, product)
            }
        }

        // Set animation on cart icon click
        changeContent(scene_popular)
        changeContent(scene_popular2)
        changeContent(scene_popular3)
    }

    /**
     *  Animate changes from cart icon to quantity view and visa versa
     */
    private fun changeContent(v: ViewGroup) {
        // Set scenes
        val sceneRoot: ViewGroup = v
        val cartScene: Scene = Scene.getSceneForLayout(
            sceneRoot,
            layout.cart_popular,
            this@HomeFragment.requireContext()
        )
        val quantityScene: Scene = Scene.getSceneForLayout(
            sceneRoot,
            layout.quantity_popular,
            this@HomeFragment.requireContext()
        )
        // Inflate transition
        val transitionPopular: Transition =
            TransitionInflater.from(this@HomeFragment.requireContext())
                .inflateTransition(transition.transition_popular_cart)

        v.setOnClickListener(object : View.OnClickListener {
            var isCountOn = false
            override fun onClick(v: View?) {
                isCountOn = !isCountOn
                if (isCountOn) TransitionManager.go(quantityScene, transitionPopular)
                else TransitionManager.go(cartScene, transitionPopular)
            }
        })
    }

    /**
     * Navigate to ProductDetailsFragment
     */
    private fun setOnTouch(view: View, product: Product) {
        val bundle: Bundle = Bundle()
        bundle.putParcelable("product", product)
        view.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.productDetailed, bundle)
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeRecyclerView() {
        val categoriesAdapter = CategoriesAdapter(catList)
        rv_categories.apply {
            // Set Horizontal Layout Manager
            // for Recycler view
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = categoriesAdapter
            itemAnimator = DefaultItemAnimator()

            val layoutManager = rv_categories.layoutManager as LinearLayoutManager?
            /**
             * Set animation RecyclerView's onScroll
             */
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    val lastPosition = layoutManager!!.findLastVisibleItemPosition()
                    val firstPosition = layoutManager.findFirstVisibleItemPosition()
                    for (i in firstPosition..lastPosition) {
                        val holder =
                            recyclerView.findViewHolderForLayoutPosition(i) as CategoryViewHolder

                        if (dx > 0) {
                            holder.image.animate().rotation(-45f).duration = 2000
                        }
                        if (dx < 0) {
                            holder.image.animate().rotation(0f).duration = 2000
                        }
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        rv_categories.clearOnScrollListeners()
        super.onDestroyView()
    }
}

