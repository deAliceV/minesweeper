package com.example.minesweeper

import android.content.Context
import android.view.View
import com.example.minesweeper.util.GeneratorGrid
import com.example.minesweeper.util.PrintGrid
import com.example.minesweeper.views.grid.Cell


class GameEngine private constructor() {
    private var context: Context? = null
    private val MinesweeperGrid: Array<Array<Cell?>> = Array(WIDTH) { arrayOfNulls<Cell>(HEIGHT) }


    // Constantes
    companion object {
        const val BOMB_NUMBER = 10
        const val WIDTH = 10
        const val HEIGHT = 10


        val instance: GameEngine by lazy { GameEngine() }
    }

    // Método para crear la cuadrícula
    fun createGrid(context: Context) {
        this.context = context

        // Generar la cuadrícula usando la clase GeneratorGrid
        val generatedGrid: Array<IntArray> = GeneratorGrid.generate(BOMB_NUMBER, WIDTH, HEIGHT)

        // Imprimir la cuadrícula usando la clase PrintGrid
        PrintGrid.print(generatedGrid, WIDTH, HEIGHT)

        setGrid(context!!, generatedGrid)
    }
    private fun setGrid(context: Context, grid: Array<IntArray>) {
        for (x in 0 until WIDTH) {
            for (y in 0 until HEIGHT) {
                if(MinesweeperGrid[x][y] == null){
                    MinesweeperGrid[x][y] = Cell(context, y * HEIGHT + x)
                }
                MinesweeperGrid[x][y]?.value = grid[x][y]
                MinesweeperGrid[x][y]?.invalidate();
            }
        }
    }

    fun getCellAt(position: Int): View?{
        val x = position % WIDTH
        val y = position / WIDTH

        return MinesweeperGrid[x][y]

    }
}






