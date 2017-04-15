package br.edu.ifce.crazyquiz.views

import br.edu.ifce.crazyquiz.data.QuestionOption
import br.edu.ifce.crazyquiz.presenters.BasePresenter

interface IQuestionView: BasePresenter.IView {

    enum class WrongAudio {
        BUZZER,
        DERP,
        FART,
        NOPE,
        REALLY_NIGGA,
        SCREAM
    }

    enum class RightAudio {
        CHEERING,
        DING,
        HEAVENLY
    }

    fun setQuestionText(text: String)
    fun setQuestionNumber(number: Int)
    fun setQuestionOptions(options: List<QuestionOption>)
    fun setQuestionLevel(level: Int)
    fun showHint(hint: String)
    fun notifyWrongAnswer()
    fun showNoHintMessage()
    fun setLifeCount(life: Int)
    fun setScoresCount(scores: Int)
    fun finish()
    fun callFinishedGameScreen()
    fun callGameOverScreen()
    fun setWrongAudio(audio: WrongAudio)
    fun setRightAudio(audio: RightAudio)
    fun notifyRightAnswer()
}