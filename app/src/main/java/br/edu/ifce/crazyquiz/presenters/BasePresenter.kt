package br.edu.ifce.crazyquiz.presenters

abstract class BasePresenter<V: BasePresenter.IView>(val view: V) {
    interface IView
}



