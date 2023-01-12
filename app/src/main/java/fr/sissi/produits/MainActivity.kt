package fr.sissi.produits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.sissi.produits.fragments.AddProductFragment
import fr.sissi.produits.fragments.CollectionFragment
import fr.sissi.produits.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //charger le ProductRepository
        //passer un nouvel objet
        val repo = ProductRepository()

        //mettre à jour la liste de produits
        repo.updateData {
            //injecte le fragment dans la boite horizontale (fragment_container)
            //je stocke une valeur qui ne changer pas
            //supportFragment gère les fragment sur Android et begin permet la manipulation de ces derniers
            val transaction = supportFragmentManager.beginTransaction()
            //je remplace le container (layout fragment_container) par fragment HomeFragment
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            //transaction.replace(R.id.fragment_container, AddProductFragment(this))
            //j'ajoute un retour null
            transaction.addToBackStack(null)
            //j'envoie les changements
            transaction.commit()

        }


    }
}