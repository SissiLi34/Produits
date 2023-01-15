package fr.sissi.produits.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import fr.sissi.produits.MainActivity
import fr.sissi.produits.ProductModel
import fr.sissi.produits.ProductRepository
import fr.sissi.produits.R
import java.util.UUID


//je récupère le fragment pour pouvoir le manipuler
class AddProductFragment(
    //je récupère le context MainActivity
    private val context: MainActivity
    //je rajoute l'héritage qui dit que fragment doit être compris par Android
) : Fragment() {

    //attribut accessible de partout
    //var car variable (val fixe)
    private var file: Uri? = null
    private var uploadedImage:ImageView? = null


    //injection du layout
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater?.inflate(R.layout.fragment_add_product, container, false)


        //récupération du bouton enregistrer
       val confirmButton = view?.findViewById<Button>(R.id.confirm_button)
        //au click la méthode sendForm envoie le form
       if (confirmButton != null) {
            //je passe la vue qui contient tous les élements
           confirmButton.setOnClickListener{ sendForm(view)}
       }

        return view
    }


        //méthode d'envoi du form
        private fun sendForm(view: View) {
            //récupération de l'image à l'enregistrement
            val repo = ProductRepository()
            //lien du fichier
            //repo.uploadImage(file!!) {
                //récupération du nom
                val productName = view.findViewById<EditText>(R.id.name_input).text.toString()
                //récupération de la valeur de la description
                val productDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
                //récupération catégorie
                val category = view.findViewById<Spinner>(R.id.category_spinner).selectedItem.toString()
                //récupération budget
                val budget = view.findViewById<Spinner>(R.id.budget_spinner).selectedItem.toString()
                //récupération du lien d'une image
               // val downloadImageUrl = downLoadUri


                //CONSTRUCTION NOUVEL OBGET A PARTIR DU FORMULAIRE
                val product = ProductModel (
                //génértaion automatique d'un id
                UUID.randomUUID().toString(),
                //nom du produit
                productName,
                //description
                productDescription,
                //lien de l'image
                //downloadImageUrl.toString()
                //catégorie
                category,
                //budget
                budget
                )

                //envoi bdd
                repo.insertProduct(product)
            }
}
