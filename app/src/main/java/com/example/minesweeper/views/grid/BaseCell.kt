package com.example.minesweeper.views.grid

import android.content.Context
import android.view.View
import com.example.minesweeper.GameEngine

abstract class BaseCell(context: Context) : View(context) {
    private var _value: Int?= null;
    private var isBomb: Boolean = false
    private var isRevealed: Boolean = false
    private var isClicked: Boolean = false
    private var isFlagged: Boolean = false

    private var _x: Int = 0
    private var _y: Int = 0
    private var _position: Int = 0

    var value: Int?
        get() = _value // Devuelve el valor privado
        set(value) {
            isBomb = false;
            isRevealed = false;
            isClicked = false;
            isFlagged = false;
            if(value == -1){
                isBomb = true;
            }
            _value = value // Asigna el nuevo valor
        }

    var bomb: Boolean
        get() = isBomb // Devuelve el estado de isBomb
        set(value) {
            isBomb = value // Asigna el nuevo estado de isBomb
        }

    var revealed: Boolean
        get() = isRevealed // Devuelve el estado de isRevealed
        set(value) {
            isRevealed = value // Asigna el nuevo estado de isRevealed
        }

    var clicked: Boolean
        get() = isClicked // Devuelve el estado de isClicked
        set(_) {
            isClicked = true // Asigna el nuevo estado de isClicked
            isRevealed = true

            invalidate()
        }

    var flagged: Boolean
        get() = isFlagged // Devuelve el estado de isFlagged
        set(value) {
            isFlagged = value // Asigna el nuevo estado de isFlagged
        }

    val x: Int
        get() = _x // Devuelve la posición x


    val y: Int
        get() = _y // Devuelve la posición y


    var position: Int
        get() = _position // Devuelve la posición
        set(value) {
            _position = value // Asigna la nueva posición
            _x= value % GameEngine.WIDTH
            _y= value / GameEngine.WIDTH
            invalidate()
        }



}