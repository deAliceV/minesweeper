package com.example.minesweeper

import android.content.Context
import android.view.View
import android.widget.Toast
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
                    MinesweeperGrid[x][y] = Cell(context, x, y)
                }
                MinesweeperGrid[x][y]?.setValue(grid[x][y])
                MinesweeperGrid[x][y]?.invalidate();
            }
        }
    }

    fun getCellAt(position: Int): Cell?{
        val x = position % WIDTH
        val y = position / WIDTH

        return MinesweeperGrid[x][y]

    }
    fun getCellAt(x: Int, y: Int): Cell? {
        return MinesweeperGrid[x][y]
    }
    fun click(x: Int, y: Int) {
        // Verifica que las coordenadas estén dentro de los límites y que la celda no esté clicada
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !(getCellAt(x, y)?.isClicked() == true)) {
            // Marca la celda como clicada
            getCellAt(x, y)?.setClicked()

            // Verifica si el valor de la celda es 0
            if (getCellAt(x, y)?.getValue() == 0) {
                // Itera sobre las celdas adyacentes
                for (xt in -1..1) {
                    for (yt in -1..1) {
                        // Evita llamar a la misma celda
                        if (xt != 0 || yt != 0) {
                            click(x + xt, y + yt)
                        }
                    }
                }
            }

            // Verifica si la celda es una bomba
            if (getCellAt(x, y)?.isBomb() == true) {
                onGameLost()
            }
        }

        checkEnd()
    }
    fun flag(x: Int, y: Int) {
        val cell = getCellAt(x, y)
        cell?.let {
            val isCurrentlyFlagged = it.isFlagged() // Llama a la función isFlagged()
            it.setFlagged(!isCurrentlyFlagged) // Llama a setFlagged() con el valor opuesto
            it.invalidate() // Actualiza la vista
        }
    }



    private fun onGameLost() {
        // Manejar la pérdida del juego
        Toast.makeText(context, "Game lost", Toast.LENGTH_SHORT).show()

        for (x in 0 until WIDTH) {
            for (y in 0 until HEIGHT) {
                getCellAt(x, y)?.setRevealed() // Asegúrate de que la celda no sea nula
            }
        }
    }

    private fun checkEnd(): Boolean {
        var bombNotFound = BOMB_NUMBER
        var notRevealed = WIDTH * HEIGHT

        for (x in 0 until WIDTH) {
            for (y in 0 until HEIGHT) {
                val cell = getCellAt(x, y)
                if (cell?.isRevealed()==true || cell?.isFlagged() == true) {
                    notRevealed--
                }

                if (cell?.isFlagged() == true && cell.isBomb()) {
                    bombNotFound--
                }
            }
        }

        if (bombNotFound == 0 && notRevealed == 0) {
            Toast.makeText(context, "Game won", Toast.LENGTH_SHORT).show()
        }

        return false
    }


}






