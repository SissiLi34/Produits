package fr.sissi.produits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.sissi.produits.MainActivity
import fr.sissi.produits.ProductRepository.Singleton.productList
import fr.sissi.produits.R
import fr.sissi.produits.adapter.ProductAdapter
import fr.sissi.produits.adapter.ProductItemDecoration

//class avec héritage de Fragment
class HomeFragment (
    //je récupère le context de MainActivity
    private val context: MainActivity
    //CollectionFragment hérite de la class d'Android
        ): Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container,false)



        //créer une liste qui va stocker les produits
        //val productList = arrayListOf<ProductModel>()
 /*     DONNÉES LOCALES EN DUR

       //enregistrer produit
        productList.add(
            ProductModel(
            name = "Bouquet de tulipes",
            description = "Bouquet de 19 tulipes",
            imageURL = "https://cdn.pixabay.com/photo/2018/02/23/11/38/bouquet-3175315_1280.jpg",
            liked = true
        ))

        productList.add(
            ProductModel(
                name = "Bouquet de pivoines",
                description = "Bouquet de 9 pivoines",
                imageURL = "https://cdn.pixabay.com/photo/2018/06/19/01/19/champagne-3483702_1280.jpg",
                liked = false
            ))

        productList.add(
            ProductModel(
                name = "Massage relaxant",
                description = "Massages aux huiles relaxantes",
                imageURL = "https://cdn.pixabay.com/photo/2019/04/06/19/22/glass-4108085_1280.jpg",
                liked = false
            ))
*/

        //récupération de la vue du recyclerView horizontal avec les détails des produits
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)

        //les produits se chargent automatiquement
        //je précise quel layout je veux utiliser --> ProductAdapter avec juste un id
        if (horizontalRecyclerView != null) {
            //je ne veux afficher sur la page d'accueil que les produit non liked
            horizontalRecyclerView.adapter = ProductAdapter(context, productList.filter { !it.liked }, R.layout.item_horizontal_product)

        //récupération du second recyclerView vertical avec les détails des produits
            val  verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
            verticalRecyclerView. adapter = ProductAdapter(context, productList, R.layout.item_vertical_product)
            //je rajoute l'espace d'itemDecoration
            verticalRecyclerView.addItemDecoration(ProductItemDecoration())
        }

        return  view
    }

}
