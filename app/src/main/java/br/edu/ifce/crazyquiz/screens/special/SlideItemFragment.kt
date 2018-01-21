package br.edu.ifce.crazyquiz.screens.special

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.crazyquiz.R
import kotlinx.android.synthetic.main.fragment_slide_item.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class SlideItemFragment : Fragment() {

    private lateinit var text: String
    private var last: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        text = arguments.getString("text")
        last = arguments.getBoolean("last")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_slide_item, container, false)!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        messageTextView.text = text
        finishBtn.visibility = if (last) View.VISIBLE else View.GONE
        slidetextView.visibility = if (last) View.GONE else View.VISIBLE

        finishBtn.onClick {
            (activity as EduardaActivity).finishedSlide()
        }
    }
}