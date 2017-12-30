package br.edu.ifce.crazyquiz.screens.highscores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameComplete
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameOver
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_high_scores.*
import kotlinx.android.synthetic.main.high_scores_list_item.*
import org.jetbrains.anko.*
import java.security.InvalidParameterException

class HighScoresActivity : AppCompatActivity(), IHighScoresView {

    private val presenter = HighScoresPresenter(this, PlayerStore(applicationContext))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)

        highScoresList.setHasFixedSize(true)
        highScoresList.layoutManager = LinearLayoutManager(this)

        val scores = intent.getIntExtra("scores", 0)
        val mode = intent.getSerializableExtra("mode") as FinishedGameMode
        if (scores > 0)
            showGameFinishedDialog(scores, mode)

        presenter.onCreateView()
    }

    private fun showGameFinishedDialog(scores: Int, mode: QuestionnaireSession.FinishedGameMode) {
        alert {
            titleResource = when (mode) {
                GameOver -> R.string.game_over
                GameComplete -> R.string.congratulations
            }

            var name: EditText? = null

            customView {

                verticalLayout {
                    padding = dip(30)

                    textView {
                        textResource = when (mode) {
                            GameOver -> R.string.game_over
                            GameComplete -> R.string.game_complete
                        }
                        textSize = 16f
                    }

                    textView {
                        text = getString(R.string.earn_scores, scores)
                        textSize = 24f
                    }

                    name = editText {
                        hintResource = R.string.say_name
                        textSize = 18f
                    }

                }
            }

            okButton { dialog ->
                try {
                    presenter.onSaveScores(name?.text.toString(), scores)
                    toast(R.string.save_score)
                    dialog.dismiss()
                } catch (e: InvalidParameterException) {
                    name?.error = getString(R.string.required_name)
                }
            }

            cancelButton { dialog -> dialog.dismiss() }

        }.show()
    }

    override fun setScoresList(players: List<Player>) {
        val adapter = HighScoresListAdapter(players)
        highScoresList.adapter = adapter
    }

    class HighScoresListAdapter(private val players: List<Player>) : RecyclerView.Adapter<HighScoresListAdapter.ViewHolder>() {

        class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            fun bind(player: Player) {
                highScoresName.text = player.name
                highScoresValue.text = player.scores.toString()
                highScoresPlace.text = """${adapterPosition + 1}º"""
            }
        }

        override fun getItemCount() = players.count()

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val player = players[position]
            holder?.bind(player)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.high_scores_list_item, parent, false)
            return ViewHolder(view)
        }
    }

}

