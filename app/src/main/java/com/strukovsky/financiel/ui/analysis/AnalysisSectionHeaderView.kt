package com.strukovsky.financiel.ui.analysis

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.strukovsky.financiel.R

class AnalysisSectionHeaderView(context: Context, title: String): LinearLayout(context) {

    init {
        inflate(context, R.layout.view_analysis_section_header, this)
        findViewById<TextView>(R.id.title).text = title
    }
}