package fr.sissi.produits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.sissi.produits.R
import fr.sissi.produits.adapter.ProductAdapter
import fr.sissi.produits.adapter.ProductItemDecoration

//class avec héritage de Fragment
class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container,false)

        //récupération de la vue du recyclerView horizontal
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        //les produits se chargent automatiquement
        //je précise quel layout je veux utiliser --> ProductAdapter avec juste un id
        if (horizontalRecyclerView != null) {
            horizontalRecyclerView.adapter = ProductAdapter(R.layout.item_horizontal_product)

        //récupération du second recyclerView vertical
            val  verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
            verticalRecyclerView. adapter = ProductAdapter(R.layout.item_vertical_product)
            //je rajoute l'espace d'itemDecoration
            verticalRecyclerView.addItemDecoration(ProductItemDecoration())
        }

        return  view
    }

}
