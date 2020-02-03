package com.x5bart.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cheat.*

const val EXTRA_ANSWER_SHOWN = "com.x5bart.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.x5bart.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        show_answer_button.setOnClickListener {
            showAnswer()
        }

        showApi()
    }

    private fun setAnswerShowerResult(isAnswerShower: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShower)
        }
        setResult(Activity.RESULT_OK, data)
    }

    private fun showApi() {
        val currentApi = Build.VERSION.SDK_INT
        show_api_text_view.text = "API Level $currentApi"
    }

    private fun showAnswer() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        answer_text_view.setText(answerText)
        setAnswerShowerResult(true)
    }


}
