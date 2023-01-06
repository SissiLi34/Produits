package fr.sissi.produits.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.sissi.produits.MainActivity
import fr.sissi.produits.ProductModel
import fr.sissi.produits.R

//class que je donne au recyclerView chaque produit avec son équivalent en image
class ProductAdapter (
    //je récupère le context du MainActivity pour passer les info d'une class à l'autre
    private val context: MainActivity,
    //je récolte la liste de produits
    private val productList: List<ProductModel>,
    private val layoutId: Int
    ): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

   //boite qui range tous les composants à controler
   class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
       val productImage = view.findViewById<ImageView>(R.id.image_item)
    //je récupère les infos du produit
       val productName:TextView? = view.findViewById(R.id.name_item)
       val productDescription:TextView? = view.findViewById(R.id.description_item)
       val starIcone = view.findViewById<ImageView>(R.id.star_icone)
    }
//méthode qui injecte le layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    //génère la vue
        val view = LayoutInflater
            .from(parent.context)
            //false si le parent est attaché à un composant
            .inflate(layoutId, parent, false)
    //passe la vue de val dans les paramètres
        return ViewHolder(view)
    }
    //récupère toutes les informations du produit dans cette position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recup pour ce produit spécifique (current)
val currentProduct = productList[position]

        //utilisation Glid pour récupérer l'image à partir de son lien
        //convertie l'url de l'image en format Uri (android)
        //charge l'image et l'inclut ( dans un composant
        Glide.with(context).load(Uri.parse(currentProduct.imageURL)).into(holder.productImage)

        //met à jour le nom du produit
        holder.productName?.text = currentProduct.name

        //met à jour la descrition
        holder.productDescription?.text = currentProduct.description

        //verification du like de la plante
        if (currentProduct.liked){
            holder.starIcone.setImageResource(R.drawable.ic_like)
        }
        else{
            holder.starIcone.setImageResource(R.drawable.ic_unlike)
        }
    }

    //affiche automatiquement le nombre de produits
    override fun getItemCount(): Int = productList.size

}