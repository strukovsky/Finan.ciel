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
class AnalysisView(context: Context) : ConstraintLayout(context), View.OnClickListener {
      private val descriptionView: TextView
      private val valueView: TextView
      private var adviceIsShown = false
      private val adviceView:TextView
      private val container: ConstraintLayout

    init {
        inflate(getContext(), R.layout.view_analysis, this)
        valueView = findViewById(R.id.value)
        descriptionView = findViewById(R.id.description)
        adviceView = findViewById(R.id.advice)
        container = findViewById(R.id.container)
        setOnClickListener(this)
    }

    public fun populate(analysis: Analysis)
    {
        valueView.text = analysis.value
        descriptionView.text = analysis.field
        adviceView.text = analysis.message
        setBackgroundColorByRecommendation(analysis.recommendation)
    }



    private fun setBackgroundColorByRecommendation(recommendations: Recommendations)
    {
        var color = 0;
        when (recommendations)
        {
            Recommendations.Negative -> color = Color.rgb(200, 20, 20)
            Recommendations.Positive -> color = Color.rgb(20, 200, 20)
        }
        container.setBackgroundColor(color)
    }

    override fun onClick(v: View?) {
        if(adviceIsShown)
        {
            adviceView.visibility = View.INVISIBLE
        }
        else
        {
            adviceView.visibility = View.VISIBLE
        }
        adviceIsShown = !adviceIsShown

    }


}