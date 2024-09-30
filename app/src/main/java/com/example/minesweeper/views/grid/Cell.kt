package com.example.minesweeper.views.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.minesweeper.GameEngine
import com.example.minesweeper.R

class Cell(context: Context,x: Int,y: Int): BaseCell(context), View.OnClickListener, View.OnLongClickListener {

    init{
        setPosition(x,y)
        setOnClickListener(this)
        setOnLongClickListener(this)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
    override fun onClick(v: View){
        GameEngine.instance.click(getXPos(), getYPos());
    }
    override fun onLongClick(v: View): Boolean {
        // Llama al método flag del GameEngine usando las coordenadas x e y
        GameEngine.instance.flag(getXPos(), getYPos())

        // Devuelve true para indicar que el evento fue manejado
        return true
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        drawButton(canvas)
        if (isFlagged()) {
            drawFlag(canvas)
        } else if (isRevealed() && isBomb() && !isClicked()) {
            drawNormalBomb(canvas)
        } else {
            if (isClicked()) {
                if (getValue() == -1) {
                    drawBombExploded(canvas)
                } else {
                    drawNumber(canvas)
                }
            } else {
                drawButton(canvas)
            }
        }

    }
    private fun drawButton(canvas: Canvas) {
        // Obtén el drawable y asegúrate de que no sea nulo
        val drawable = ContextCompat.getDrawable(context, R.drawable.button)

        // Establece los límites del drawable
        drawable?.setBounds(0, 0, width, height)

        // Dibuja el drawable en el canvas
        drawable?.draw(canvas)
    }
    private fun drawBombExploded(canvas: Canvas) {

        val drawable = ContextCompat.getDrawable(context, R.drawable.bomb_exploded)
        drawable?.setBounds(0, 0, width, height)
        drawable?.draw(canvas)
    }
    private fun drawFlag( canvas: Canvas ){
        val drawable = ContextCompat.getDrawable(context, R.drawable.flag);
        drawable?.setBounds(0,0,getWidth(),getHeight());
        drawable?.draw(canvas);
    }
    private fun drawNormalBomb(canvas: Canvas) {

        val drawable = ContextCompat.getDrawable(context, R.drawable.bomb_normal)
        drawable?.setBounds(0, 0, width, height)
        drawable?.draw(canvas)
    }
    private fun drawNumber(canvas: Canvas){
        val drawable: Drawable? = when (getValue()) {
            0 -> ContextCompat.getDrawable(context, R.drawable.number_0)
            1 -> ContextCompat.getDrawable(context, R.drawable.number_1)
            2 -> ContextCompat.getDrawable(context, R.drawable.number_2)
            3 -> ContextCompat.getDrawable(context, R.drawable.number_3)
            4 -> ContextCompat.getDrawable(context, R.drawable.number_4)
            5 -> ContextCompat.getDrawable(context, R.drawable.number_5)
            6 -> ContextCompat.getDrawable(context, R.drawable.number_6)
            7 -> ContextCompat.getDrawable(context, R.drawable.number_7)
            8 -> ContextCompat.getDrawable(context, R.drawable.number_8)
            else -> null // En caso de que el valor no esté entre 0 y 8
        }

        drawable?.setBounds(0, 0, width, height)
        drawable?.draw(canvas)
    }


}