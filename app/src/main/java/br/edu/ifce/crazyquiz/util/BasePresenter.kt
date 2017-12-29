package br.edu.ifce.crazyquiz.util

abstract class BasePresenter<out V>(val view: V) {
    abstract fun onCreateView()
}



