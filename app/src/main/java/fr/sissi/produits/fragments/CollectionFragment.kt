package fr.sissi.produits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.sissi.produits.MainActivity
import fr.sissi.produits.ProductRepository.Singleton.productList
import fr.sissi.produits.R
import fr.sissi.produits.adapter.ProductAdapter
import fr.sissi.produits.adapter.ProductItemDecoration


class CollectionFragment (
    //je récupère le context de MainActivity
    private val context: MainActivity
    //CollectionFragment hérite de la class d'Android
    ) : Fragment() {

    //instructions pour la création de la vue en injectant le layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        //récupération de recyclerView (le container)
        val collectionRecyclerView = view?.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView?.layoutManager = LinearLayoutManager(context)

        //les produits se chargent automatiquement
        //je précise quel layout je veux utiliser --> item_vertical_product et uniquement les produits liked
        if (collectionRecyclerView != null) {
            collectionRecyclerView.adapter = ProductAdapter(context, productList.filter { it.liked }, R.layout.item_vertical_product)

        //je rajoute l'espace d'itemDecoration
            collectionRecyclerView.addItemDecoration(ProductItemDecoration())
        }
            return view

    }
}
