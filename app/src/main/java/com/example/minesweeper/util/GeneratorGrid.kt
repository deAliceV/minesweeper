package com.example.minesweeper.util

import kotlin.random.Random

class GeneratorGrid {

    companion object {
        fun generate(bombNumber: Int, width: Int, height: Int): Array<IntArray> {
            val grid = Array(width) { IntArray(height) }
            var remainingBombs = bombNumber
            val r = Random

            // Generar bombas en posiciones aleatorias
            while (remainingBombs > 0) {
                val x = r.nextInt(width)
                val y = r.nextInt(height)

                // -1 es la bomba
                if (grid[x][y] != -1) {
                    grid[x][y] = -1
                    remainingBombs--
                }
            }

            return calculateNeighbours(grid, width, height)
        }

        private fun calculateNeighbours(grid: Array<IntArray>, width: Int, height: Int): Array<IntArray> {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    grid[x][y] = getNeighbourNumber(grid, x, y, width, height)
                }
            }
            return grid
        }

        private fun getNeighbourNumber(grid: Array<IntArray>, x: Int, y: Int, width: Int, height: Int): Int {
            if (grid[x][y] == -1) {
                return -1
            }

            var count = 0
            if (isMineAt(grid, x - 1, y + 1, width, height)) count++ // top-left
            if (isMineAt(grid, x, y + 1, width, height)) count++     // top
            if (isMineAt(grid, x + 1, y + 1, width, height)) count++ // top-right
            if (isMineAt(grid, x - 1, y, width, height)) count++     // left
            if (isMineAt(grid, x + 1, y, width, height)) count++     // right
            if (isMineAt(grid, x - 1, y - 1, width, height)) count++ // bottom-left
            if (isMineAt(grid, x, y - 1, width, height)) count++     // bottom
            if (isMineAt(grid, x + 1, y - 1, width, height)) count++ // bottom-right

            return count
        }

        private fun isMineAt(grid: Array<IntArray>, x: Int, y: Int, width: Int, height: Int): Boolean {
            return x in 0 until width && y in 0 until height && grid[x][y] == -1
        }
    }
}
