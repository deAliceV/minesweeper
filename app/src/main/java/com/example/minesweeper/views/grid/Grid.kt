package com.example.minesweeper.views.grid

import android.content.Context;
import android.util.AttributeSet;
import android.view.View
import android.view.ViewGroup
import android.widget.GridView;
import android.widget.BaseAdapter
import com.example.minesweeper.GameEngine

class Grid(context: Context, attrs: AttributeSet) : GridView(context, attrs) {
    // Aquí puedes añadir más lógica o métodos personalizados si es necesario.
  init{
        GameEngine.instance.createGrid(context)
        numColumns=GameEngine.WIDTH;
        adapter=(GridAdapter());
  }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}


private class GridAdapter: BaseAdapter(){
    override fun getCount(): Int {
        return GameEngine.WIDTH * GameEngine.HEIGHT;
    }

    override fun getItem(position: Int): Any? {
        return null;
    }

    override fun getItemId(position: Int): Long {
        return 0;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val gameEngine = GameEngine.instance
        return gameEngine?.getCellAt(position) ?: View(parent?.context);
    }

}