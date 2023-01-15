package fr.sissi.produits

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.sissi.produits.ProductRepository.Singleton.databaseRef
//import fr.sissi.produits.ProductRepository.Singleton.downloadUri
import fr.sissi.produits.ProductRepository.Singleton.productList
//import fr.sissi.produits.ProductRepository.Singleton.storageReference
import java.util.UUID


class ProductRepository {

    //singleton structure qui permet d'ajouter un nouvel objet dans la liste
    object Singleton {
        //propriété qui va générer le lien de connection au bucket (lien de firebase)
        //private val BUCKET_URL: String = "gs://produits-e8070.appspot.com/"

        //connection à l'espace de stockage
        //bucket_url = nom donné au lien de connection
      //  val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        //connection à la référence 'Produits'
        val databaseRef = FirebaseDatabase.getInstance().getReference("Produits")

        //création d'une liste qui contient les produits
        val productList = arrayListOf<ProductModel>()

        //contenir le lien de l'image courante
       // var downloadUri: Uri? = null
    }

    //callback =liste d'instructions à passer après la récupération des données
    //Unit = paquet d'instructions qui va s'exécuter
    fun updateData(callback: () -> Unit){
        //absorbe les données depuis la databaseRef et les donne à la liste de produits
        //EvenListener évènement qu'on écoute et sur lequel il peut se passer quelque chose(ex: MAJ bdd)
        databaseRef.addValueEventListener(object : ValueEventListener{

            //méthodes implémentées
            //La liste est récoltée
            //absorber les données qui sont dans databaseRef et les donner à : liste de produit
            //Snapshot : objet qui contient toutes les données récupérées
            override fun onDataChange(snapshot: DataSnapshot) {
                //retire les anciens produits pour recréer une liste toute neuve
                productList.clear()
                //boucle qui parcours chacun des éléments de Snapchot
                //récolte la liste (tous les enfants)
                //ds = data Snapshot
                for (ds in snapshot.children) {
                    //construit un objet produit (avec les données ds)
                    val product = ds.getValue(ProductModel::class.java)

                    //vérification du non null du produit, qu'il a bien été chargé
                    if (product != null) {
                        //ajout du produit à la liste
                        productList.add(product)
                    }
                }
                //activation du callback pour afficher le résultats qu'une fois les données récupérées
                callback()
            }

            //si les éléments n'ont pas pu etre récoltés
            override fun onCancelled(error: DatabaseError) {}

        })

    }

            //fonction qui envoie des fichiers sur le storage
    //je passe le fichier interne
          //  fun uploadImage(file: Uri, callback: () -> Unit){
                //vérification du fichier non null (invalide)
             //   if(file != null){
                    //pour envoyer un fichier il faut lui donner un nom (ici au hasard) en text et le covertir en image jpg
                  //  val fileName = UUID.randomUUID().toString() + ".jpg"
                    //création d'une référence pour l'image avec le nom du fichier
                  //  val ref = storageReference.child(fileName)
                    //envoie le fichier dans une tache d'envoi
                   // val uploadTask = ref.putFile(file)

                    //démarage de la tache d'envoi
                  //  uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>{task ->

                        //il y a t'il eut un problème lors de l'envoi du fichier

                    //    if (!task.isSuccessful) {
                     //       task.exception?.let { throw it }
                       // }

                        //renvoyer pour indiquer quel est le lien de référence
                     //   return@Continuation ref.downloadUrl

                //    }).addOnCompleteListener { task ->
                        //vérifier si tout à bien fonctionner
                   //     if (task.isSuccessful) {
                            //je peux récupérer l'image en ligne sur le bucket
                    //        downloadUri = task.result
                    //        callback()
                     //   }

                  //  }

//}

           // }

            //mettre à jour un produit en bdd
            //setValue met à jour la valeur actuelle de l'objet
            fun updateProduct(product: ProductModel) = databaseRef.child(product.id).setValue(product)

            //ajouter un nouveau produit à la bdd
            fun insertProduct(product: ProductModel) = databaseRef.child(product.id).setValue(product)

            //suprimer un produit de la bdd
            fun deleteProduct (product: ProductModel) = databaseRef.child(product.id).removeValue()

}