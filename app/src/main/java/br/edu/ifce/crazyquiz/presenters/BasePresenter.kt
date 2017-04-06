package br.edu.ifce.crazyquiz.presenters

abstract class BasePresenter<out View : BasePresenter.IView>(val view: View) {
    interface IView
}



