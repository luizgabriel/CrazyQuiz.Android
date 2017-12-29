package br.edu.ifce.crazyquiz.screens.main

interface IMainView {
    fun startGameScreen()
    fun openHighScoresScreen()
    fun openShareScreen()
    fun errorOnLoadQuestions()
    fun startLoading()
    fun finishLoading()
}