package br.edu.ifce.crazyquiz.data

class Player(var name: String = "", scores: Int = 0) {

    var scores: Int = scores
        set(value) {
            field = if (value > 0) value else 0
        }

}