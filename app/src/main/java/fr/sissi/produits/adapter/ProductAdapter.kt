package fr.sissi.produits.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.sissi.produits.R

//class que je donne au recyclerView chaque produit avec son équivalent en image
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
   //boite qui range tous les composants à controler
   class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    //je récupère dans item_horizontal_product le produit

    }
//méthode qui injecte le layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    //génère la vue
        val view = LayoutInflater
            .from(parent.context)
            //false si le parent est attaché à un composant
            .inflate(R.layout.item_horizontal_product, parent, false)
    //passe la vue de val dans les paramètres
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    //affiche un nombre défini d'image
    override fun getItemCount(): Int = 5

}