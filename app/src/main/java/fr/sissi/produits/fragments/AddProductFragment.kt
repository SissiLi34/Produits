package fr.sissi.produits.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import fr.sissi.produits.MainActivity
import fr.sissi.produits.ProductModel
import fr.sissi.produits.ProductRepository
import fr.sissi.produits.R
import java.util.UUID

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





        //récupérer uploadImage pour lui associer un composant
      //  if (view != null) {
        //    uploadedImage = view.findViewById(R.id.preview_image)
       // }

        //OK 1
        //récupérer le bouton pour charger l'image
       // val pickupImageButton = view?.findViewById<Button>(R.id.upload_button)

        //OK 2
        //lorsque le bouton est clické il ouvre les images du téléphone
       // if (pickupImageButton != null) {
            //évènement associé au click qui déclanche la méthode pickupImage
          //  pickupImageButton.setOnClickListener { pickupImage()}
      //  }

        //récupération du bouton enregistrer
       // val confirmButton = view?.findViewById<Button>(R.id.confirm_button)
        //au click la méthode sendForm envoie le form
       // if (confirmButton != null) {
            //je passe la vue qui contient tous les élements
         //   confirmButton.setOnClickListener{ sendForm(view)}
     //   }

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

    //OK 3
    //méthode qui ouvre les images du téléphone,intent ouvre les contact etc
   // private fun pickupImage() {
       // val intent = Intent()
        //je passe le type d'action que je souhaite
       // intent.type = "image/"
        //je définis le type d'action que je veux faire avec ces images (récupération au click)
       // intent.action = Intent.ACTION_GET_CONTENT

        //Démarer une instruction et attendre le résultat
        //starActivityForResult(Intent.createChooser(intent,"Select Picture"), 40)

        //test nouveau staractivity
        //ActivityResultLauncher<Intent> = new Intent()

    //}



//récolter ce qu'on a récupérer

   // override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        //vérification
       // if(requestCode == 40 && resultCode == Activity.RESULT_OK){

            //je vérifie si les données sont nulles
           // if (data == null || data.data == null) return

            //si c'est valide je récupère l'image sélectionnée
            //val file = data.data

            //met à jour l'apperçu de l'image
            //uploadedImage?.setImageURI(file)

            //heberger sur le bucket
           // val repo= ProductRepository()
            //repo.uploadImage(file!!)
        //}
    //}
//}
