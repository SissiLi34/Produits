package fr.sissi.produits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.sissi.produits.fragments.AddProductFragment
import fr.sissi.produits.fragments.CollectionFragment
import fr.sissi.produits.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //première page de connection
        loadFragment(HomeFragment(this), R.string.home_page_title)

        //importation de la bottomNavigationView
        val navigationItemView = findViewById<BottomNavigationView>(R.id.navigation_view)
        //évènement au click
        navigationItemView.setOnItemReselectedListener {
            //multi conditions avec when de Kotlin (switch java)
            //je vérifie l'id
            when(it.itemId){
                //si je click sur l'id home_page
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    true
                }
                //si click on sélection
                R.id.selection_page -> {
                    loadFragment(CollectionFragment(this), R.string.selection_page_title)
                    true
                }
                //si click on add_product
                R.id.add_product_page -> {
                    loadFragment(AddProductFragment(this), R.string.add_product_page_title)
                true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment, string: Int) {

        //charger le ProductRepository
        //passer un nouvel objet
        val repo = ProductRepository()

        //actualisation du titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)


        //mettre à jour la liste de produits
        repo.updateData {
            //injecte le fragment dans la boite horizontale (fragment_container)
            //je stocke une valeur qui ne changer pas
            //supportFragment gère les fragment sur Android et begin permet la manipulation de ces derniers
            val transaction = supportFragmentManager.beginTransaction()
            //je remplace le container (layout fragment_container) par fragment HomeFragment
            //met à jour le fragment demandé au click sur le menu
            transaction.replace(R.id.fragment_container, fragment)
            //transaction.replace(R.id.fragment_container, AddProductFragment(this))
            //j'ajoute un retour null
            transaction.addToBackStack(null)
            //j'envoie les changements
            transaction.commit()
        }
    }
}