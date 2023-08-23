package com.example.skillcinema.presentation.search.settings

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class YearItemDecoration(
    private val leftOfSet : Int,
    private val topOfSet : Int,
    private val rightOfSet : Int,
    private val bottomOfSet : Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(leftOfSet, topOfSet, rightOfSet, bottomOfSet)
    }
}