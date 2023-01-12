package fr.sissi.produits

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.sissi.produits.ProductRepository.Singleton.databaseRef
import fr.sissi.produits.ProductRepository.Singleton.productList



class ProductRepository {

    //singleton structure qui permet d'ajouter un nouvel objet dans la liste
    object Singleton {
        //connection à la référence 'Produits'
        val databaseRef = FirebaseDatabase.getInstance().getReference("Produits")

        //création d'une liste qui contient les produits
        val productList = arrayListOf<ProductModel>()
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
            //mettre à jour un produit en bdd
            //setValue met à jour la valeur actuelle de l'objet
            fun updateProduct(product: ProductModel) = databaseRef.child(product.id).setValue(product)

            //suprimer un produit de la bdd
            fun deleteProduct (product: ProductModel) = databaseRef.child(product.id).removeValue()

}