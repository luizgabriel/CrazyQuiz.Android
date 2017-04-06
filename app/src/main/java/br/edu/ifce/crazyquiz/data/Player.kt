package br.edu.ifce.crazyquiz.data

class Player(name: String = "", scores: Int = 0) {

    var name = name
    var scores = scores
        set(value) {
            field = if (value > 0) value else 0
        }

}