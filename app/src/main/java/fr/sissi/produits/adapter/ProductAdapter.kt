package fr.sissi.produits.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.sissi.produits.MainActivity
import fr.sissi.produits.ProductModel
import fr.sissi.produits.R


class ProductAdapter(
    //je récupère le context de mainActivity
    private val context: MainActivity,
    //je récupère la liste des produits
    private val productList: List<ProductModel>,
    //je récupère une valeur privé avec le num du layout à passer (ici l'id pour tous les layout de recycler)
    private val layoutId: Int
) : RecyclerView.Adapter<ProductAdapter.ViewHolder> (){


    //je dis à productAdapter de se servir de la class ViewHolder
    //class qui permet de porter la vue, de faire une boite pour ranger tous les composants à controler
    //view précupère l'image du produit
    //ViewHolder est un composant d'Android à qui je donne la vue de cette image j'indique que c'est la vue de recyclerView
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        //récupère l'image par l'id
        val  productImage = view.findViewById<ImageView>(R.id.image_item)
        //récupère le nom par l'id
        val productName:TextView? = view.findViewById(R.id.name_item)
        //récupère la description par l'id
        val productDescription: TextView? = view.findViewById(R.id.description_item)
        //Récupère l'étoile sur les 2 items, donc pas d'erreur possible, donc pas ?
        val starIcon = view.findViewById<ImageView>(R.id.star_icone)
    }

//overide indique que j'utilise une action qui vient de la class maitre (ProductAdapter)

    //Fonction qui injecte le layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            //false si le parent est attaché a un composant
            //je récupère l'id de la class ProductAdapter
            .inflate(layoutId, parent, false)

//onCreateViewHolder attend qu'on lui donne
        //j'ai généré la vue que je dois passer dans les paramètres
        return ViewHolder(view)

    }

    //permet de mettre à jour chaque modèle avec le bon produit
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Je récupère les infos du produit qui a cette position (la plante courrante)
        val currentProduct = productList[position]

        // utilisation de glide pour récupérer l'image à partir de son lien et l'ajouter au composant

        //context = bdd interne contenant toutes les infos du projet
        //je charge les images, je les convertis avec parse, je les inclus dans productImage
        //Uri format android
        Glide.with(context).load(Uri.parse(currentProduct.imageURL)).into(holder.productImage)

        //met à jour le nom de du produit
        holder.productName?.text = currentProduct.name

        //met à jour la description du
        holder.productDescription?.text = currentProduct.description

        //vérification du produit liké ou non
        if (currentProduct.liked) {
            //si c'est le cas, j'applique sur holder.StarIcon une nouvelle image de fond, l'étoile complète
            holder.starIcon.setImageResource(R.drawable.ic_like)
        }else{
            holder.starIcon.setImageResource(R.drawable.ic_unlike)
        }
    }


    //affiche automatiquement le nombre d'item existant
    override fun getItemCount(): Int = productList.size
}
