package br.edu.ifce.crazyquiz.screens.question

import br.edu.ifce.crazyquiz.data.QuestionOption
import br.edu.ifce.crazyquiz.data.QuestionnaireSession

interface IQuestionView {

    fun setQuestionText(text: String)
    fun setQuestionNumber(number: Int)
    fun setQuestionOptions(options: List<QuestionOption>)
    fun notifyWrongAnswer()
    fun setLifeCount(life: Int)
    fun setScoresCount(scores: Int)
    fun callFinishedGameScreen(scores: Int, mode: QuestionnaireSession.FinishedGameMode)
    fun setWrongAudio(audioId: Int)
    fun setRightAudio(audioId: Int)
    fun notifyRightAnswer()
    fun showSpecialSlide()

}