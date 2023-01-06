package fr.sissi.produits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.sissi.produits.R
import fr.sissi.produits.adapter.ProductAdapter

//class avec héritage de Fragment
class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanceState: Bundle?): View? {

//je récupère la vue
        val view = inflater?.inflate(R.layout.fragment_home, container, false)
//je récupère le recyclerView
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        //si horizontalRecycler n'est pas vide il s'affiche
        if (horizontalRecyclerView != null) {
            horizontalRecyclerView.adapter = ProductAdapter()
        }
        return  view
    }

}
