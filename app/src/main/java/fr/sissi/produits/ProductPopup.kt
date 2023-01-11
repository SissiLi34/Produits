package fr.sissi.produits

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import fr.sissi.produits.adapter.ProductAdapter

//indique à Android que cette class est un dialogue
class ProductPopup(
    private val adapter: ProductAdapter
    //je passe l'adapter (context) qui contient l'activité principale
    ) :  Dialog(adapter.context){

        //code qui gère la popup

        //ajout du layout associé
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //sur la popup je ne veux pas de titre
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            //injecte le layout popup
            setContentView(R.layout.popup_product_details)
    }

}