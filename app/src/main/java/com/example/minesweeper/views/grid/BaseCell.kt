package com.example.minesweeper.views.grid

import android.content.Context
import android.view.View
import com.example.minesweeper.GameEngine

abstract class BaseCell(context: Context) : View(context) {
    private var value: Int = 0
    private var isBomb: Boolean = false
    private var isRevealed: Boolean = false
    private var isClicked: Boolean = false
    private var isFlagged: Boolean = false

    private var x: Int = 0
    private var y: Int = 0
    private var position: Int = 0

    fun getValue(): Int = value

    fun setValue(value: Int) {
        isBomb = false
        isRevealed = false
        isClicked = false
        isFlagged = false

        if (value == -1) {
            isBomb = true
        }
        this.value = value
    }

    fun isBomb(): Boolean = isBomb

    fun setBomb(bomb: Boolean) {
        isBomb = bomb
    }

    fun isRevealed(): Boolean = isRevealed

    fun setRevealed() {
        isRevealed = true
        invalidate()
    }

    fun isClicked(): Boolean = isClicked

    fun setClicked() {
        isClicked = true
        isRevealed = true
        invalidate()
    }

    fun isFlagged(): Boolean = isFlagged

    fun setFlagged(flagged: Boolean) {
        isFlagged = flagged
    }

    fun getXPos(): Int = x

    fun getYPos(): Int = y

    fun getPosition(): Int = position

    fun setPosition(x: Int, y: Int) {
        this.x = x
        this.y = y
        this.position = y * GameEngine.WIDTH + x
        invalidate()
    }
}




