package com.example.minesweeper

import android.content.Context
import com.example.minesweeper.util.GeneratorGrid
import com.example.minesweeper.util.PrintGrid


class GameEngine private constructor() {
    private var context: Context? = null

    // Constantes
    companion object {
        private const val BOMB_NUMBER = 10
        private const val WIDTH = 10
        private const val HEIGHT = 10

        var instance: GameEngine? = null
            get() {
                if (field == null) {
                    field = GameEngine()
                }
                return field
            }
            private set
    }

    // Método para crear la cuadrícula
    fun createGrid(context: Context?) {
        this.context = context

        // Generar la cuadrícula usando la clase GeneratorGrid
        val generatedGrid: Array<IntArray> = GeneratorGrid.generate(BOMB_NUMBER, WIDTH, HEIGHT)

        // Imprimir la cuadrícula usando la clase PrintGrid
        PrintGrid.print(generatedGrid, WIDTH, HEIGHT)

        // Puedes agregar lógica adicional aquí si es necesario
    }
}






