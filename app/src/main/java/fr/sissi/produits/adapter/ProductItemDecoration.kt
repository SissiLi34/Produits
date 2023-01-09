package fr.sissi.produits.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//cette class hérite de ItemDecoratio

class ProductItemDecoration : RecyclerView.ItemDecoration() {

    //récupère un offset= décalage fait par le bas
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 20
    }
}