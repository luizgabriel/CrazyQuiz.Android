package br.edu.ifce.crazyquiz.presenters

abstract class BasePresenter<V: BasePresenter.IView>(var view: V) {
    interface IView
}



