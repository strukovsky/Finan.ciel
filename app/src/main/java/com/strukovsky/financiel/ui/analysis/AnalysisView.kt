package com.strukovsky.financiel.ui.analysis

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.strukovsky.financiel.R
import com.strukovsky.financiel.analyzers.Analysis
import com.strukovsky.financiel.analyzers.Recommendations

/**
 * Компонент анализа акции
 */
class AnalysisView(context: Context, val analysis: Analysis) : ConstraintLayout(context), View.OnClickListener {
      private val descriptionView: TextView
      private val valueView: TextView
      private var adviceIsShown = false
      private val adviceView = TextView(context)

    init {
        inflate(getContext(), R.layout.view_analysis, this)
        valueView = findViewById(R.id.value)
        valueView.text = analysis.value
        descriptionView = findViewById(R.id.description)
        descriptionView.text = analysis.field
        adviceView.text = analysis.message
        setBackgroundColorByRecommendation()
        setOnClickListener(this)
    }



    private fun setBackgroundColorByRecommendation()
    {
        var color = 0;
        when (analysis.recommendation)
        {
            Recommendations.Negative -> color = Color.rgb(200, 20, 20)
            Recommendations.Positive -> color = Color.rgb(20, 200, 20)
        }
        setBackgroundColor(color)
    }

    override fun onClick(v: View?) {
        if(adviceIsShown)
        {
            removeView(adviceView)
        }
        else
        {
            addView(adviceView)
        }
        adviceIsShown = !adviceIsShown

    }


}