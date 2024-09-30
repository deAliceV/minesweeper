package com.example.minesweeper.views.grid

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.example.minesweeper.R

class Cell(context: Context, position: Int): BaseCell(context) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        drawButton(canvas)
    }
    private fun drawButton(canvas: Canvas) {
        // Obtén el drawable y asegúrate de que no sea nulo
        val drawable = ContextCompat.getDrawable(context, R.drawable.button)

        // Establece los límites del drawable
        drawable?.setBounds(0, 0, width, height)

        // Dibuja el drawable en el canvas
        drawable?.draw(canvas)
    }


}