package com.marik.armboldtask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.transition.Scene
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.marik.armboldtask.R
import com.marik.armboldtask.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_details_fragment.view.*

/**
 *  Fragment for popular product details representation
 */
class ProductDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_details_fragment, container, false)
        val args: ProductDetailsFragmentArgs by navArgs()
        val product = args.product
        initView(view, product)

        return view
    }

    private fun initView(view: View, product: Product) {
        activity?.bottom_nav_view?.visibility = View.GONE
        // Set image
        view.img_product_2.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                product.itemImage2,
                null
            )
        )
        // Set favorite
        if (product.isFavorite) {
            view.btn_like.background =
                ResourcesCompat.getDrawable(resources, R.drawable.background_like, null)
        } else view.btn_like.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_not_like, null)
        // Set subcategory
        view.txt_subcategory.text = product.subcategoryName
        // Set product name
        view.txt_product_name.text = product.itemName
        // Set price
        view.txt_product_price.text = product.price + " AMD"
        // Set description
        view.txt_product_description.text = resources.getText(product.description)
        // Set ingredients
        view.txt_product_ingredients.text = resources.getText(product.ingredients)
        // Set shelf life
        view.txt_product_shelf_life.text = resources.getText(product.shelfLife)

        // Animate click on cart icon
        view.anim_frame_details.setOnClickListener {
            setOnCartTouch(view)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.arrow_down.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_productDetailsFragment_dest_to_homeFragment,
                null
            )
        )
    }

    /**
     *  Function for cart icon to "Add to basket" view animation
     *  @params view [anim_frame_details] animation root
     */
    private fun setOnCartTouch(view: View) {
        val sceneRoot: ViewGroup = view.anim_frame_details
        val cartScene: Scene = Scene.getSceneForLayout(
            sceneRoot,
            R.layout.cart_details,
            this@ProductDetailsFragment.requireContext()
        )
        val quantityScene: Scene = Scene.getSceneForLayout(
            sceneRoot,
            R.layout.quantity_details,
            this@ProductDetailsFragment.requireContext()
        )
        val transitionDetails = TransitionInflater.from(this.requireContext())
            .inflateTransition(R.transition.transition_detail)
        TransitionManager.go(quantityScene, transitionDetails)

    }
}