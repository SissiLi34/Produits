package fr.sissi.produits.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//hétite de ItemDecoration, propriété our définir l'espace par le bas
class ProductItemDecoration : RecyclerView.ItemDecoration() {
    //offset = décalage
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //je récupère le rectangle du composant
        outRect.bottom = 20
    }
}
