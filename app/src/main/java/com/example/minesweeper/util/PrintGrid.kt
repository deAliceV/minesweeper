package com.example.minesweeper.util

import android.util.Log

class PrintGrid {
    companion object {
        fun print(grid: Array<IntArray>, width: Int, height: Int) {
            for (x in 0 until width) {
                var printedText = "| "
                for (y in 0 until height) {
                    printedText += grid[x][y].toString().replace("-1", "B") + " | "
                }
                Log.e("",printedText) // En Kotlin usamos println en lugar de System.out.print
            }
        }
    }
}
