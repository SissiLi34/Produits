package fr.sissi.produits

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.sissi.produits.adapter.ProductAdapter

//indique à Android que cette class est un dialogue
class ProductPopup(
    private val adapter: ProductAdapter,
    //pour avoir accès au ProductModel sur toute la class
    private val currentProduct: ProductModel,
    //je passe l'adapter (context) qui contient l'activité principale
    ) :  Dialog(adapter.context){

        //code qui gère la popup

        //création du popup et
        //ajout du layout associé
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //sur la popup je ne veux pas de titre
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            //injecte le layout popup
            setContentView(R.layout.popup_product_details)
            //méthode de chargement des composant
            setupComponents()
            //méthode intercation fermeture popup
            setupCloseButton()
            //méthode delete
            setupDeleteButton()
            //méthode liked
            setupStarButton()
    }

    //fonction privée de chargement de l'icone like
    private fun updateStar(button: ImageView){
        //si l'étoile est liked
        if (currentProduct.liked){
            //je charge l'icone liked
            button.setImageResource(R.drawable.ic_like)
        }
        else {
            //si c'est l'inverse
            button.setImageResource(R.drawable.ic_unlike)
        }
    }

    private fun setupStarButton() {
        //je récupère le composant de l'étoile
        //création d'un val parce que je l'utilise à plusieurs endroits, optimisation du code
        val starButton = findViewById<ImageView>(R.id.star_button)
        //j'appelle ma function liked or not
        updateStar(starButton)

        //l'interaction qui doit mettre à jour la bdd
        starButton.setOnClickListener{
            //je change le currentProduct en lui donnant la valeur opposée
            currentProduct.liked =! currentProduct.liked
            //je mets à jour la bdd
            val repo = ProductRepository()
            //et je mets à jours la plante current
            repo.updateProduct(currentProduct)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton(){
        //je récupère le composant delete (poubelle)
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
        //supprimer la plante de la bdd
            //je génère l'instance du repostory
            val repo = ProductRepository()
            //le repo fait l'action sur la databaseref
            repo.deleteProduct(currentProduct)
            //fermeture de la popup
            dismiss()
        }
    }

    //interaction button
    private fun setupCloseButton() {
        //je récupère le composant close
      findViewById<ImageView>(R.id.close_button).setOnClickListener{
          //fermeture de la fenettre popup dismiss()
        dismiss()
}
    }

    private fun setupComponents() {
        //actualisation de l'image du produit
        val productImage = findViewById<ImageView>(R.id.image_item)
        //adapter.contexte accède à la MainActivity
        Glide.with(adapter.context).load(Uri.parse(currentProduct.imageURL)).into(productImage)

        //actualisation du nom
        findViewById<TextView>(R.id.popup_product_name).text = currentProduct.name

        //actualisation de la description
        findViewById<TextView>(R.id.popup_product_description_subtitle).text = currentProduct.description

        //actualisation des catégories
        findViewById<TextView>(R.id.popup_product_category_subtitle).text = currentProduct.categorie

        //actualisation du budget
        findViewById<TextView>(R.id.popup_product_budget_subtitle).text = currentProduct.budget

    }

}