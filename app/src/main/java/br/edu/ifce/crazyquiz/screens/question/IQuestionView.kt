package br.edu.ifce.crazyquiz.screens.question

import br.edu.ifce.crazyquiz.data.QuestionOption

interface IQuestionView {

    fun setQuestionText(text: String)
    fun setQuestionNumber(number: Int)
    fun setQuestionOptions(options: List<QuestionOption>)
    fun notifyWrongAnswer()
    fun setLifeCount(life: Int)
    fun setScoresCount(scores: Int)
    fun finish()
    fun callFinishedGameScreen()
    fun callGameOverScreen()
    fun setWrongAudio(audioId: Int)
    fun setRightAudio(audioId: Int)
    fun notifyRightAnswer()

}