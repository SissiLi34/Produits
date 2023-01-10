package fr.sissi.produits


//Class qui permet d'enregistrer de nouveau produit sur l'application
//contient la liste et toute les caractéristique d'un produit
class ProductModel (
    //id par défaut
    val id: String = "produit0",
    //titre avec une valeur par défaut
    val name: String = "Massage",
    //descrition avec valeur par défaut
    val description: String = "Petite description",
    //lien image
    val imageURL: String = "https://images.unsplash.com/photo-1542848284-8afa78a08ccb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1286&q=80",
    //variable qui peut changer lors de l'exécution du programme sur produit liké ou non
    //par défaut le produit est non liké
    var liked : Boolean = false
)

