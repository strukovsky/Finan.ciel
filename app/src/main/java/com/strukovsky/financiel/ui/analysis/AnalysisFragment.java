package com.strukovsky.financiel.ui.analysis;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.strukovsky.financiel.R;
import com.strukovsky.financiel.analyzers.Analysis;
import com.strukovsky.financiel.analyzers.Recommendations;
import com.strukovsky.financiel.calculations.CreditRatio;
import com.strukovsky.financiel.calculations.Returns;
import com.strukovsky.financiel.db.entity.BalanceSheet;
import com.strukovsky.financiel.db.entity.CashFlow;
import com.strukovsky.financiel.db.entity.Share;
import com.strukovsky.financiel.db.task.FindAllDataByShareId;
import com.strukovsky.financiel.db.task.ShareDataBundle;
import com.strukovsky.financiel.db.task.TaskRunner;

public class AnalysisFragment extends Fragment {

    private AnalysisViewModel analysisViewModel;
    TextView ticker;
    TextView industry;
    TextView name;
    AnalysisView netIncomeToRevenue;
    TextView returnOnEquity;
    TextView returnOnAssets;
    TextView quickRatio;
    TextView currentRatio;
    TextView debtToEquity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        analysisViewModel =
                new ViewModelProvider(this).get(AnalysisViewModel.class);
        View root;
        if (getArguments() != null) {
            analysisViewModel.setShareId(getArguments().getInt("SHARE_ID"));
        }

        Integer shareId = analysisViewModel.getShareId();
        if (shareId == null) {
            root = setupAnalysisEmpty(inflater, container);
        } else {
            root = setupAnalysisView(inflater, container);
            performAnalysis(shareId);
        }

        return root;
    }

    private View setupAnalysisView(LayoutInflater inflater, ViewGroup container) {
        View scrollViewRoot = inflater.inflate(R.layout.fragment_analysis, container, false);
        LinearLayout root = scrollViewRoot.findViewById(R.id.root);
        ticker = scrollViewRoot.findViewById(R.id.share_ticker);
        industry = scrollViewRoot.findViewById(R.id.share_industry);
        name = scrollViewRoot.findViewById(R.id.share_name);
        /*netIncomeToRevenue = root.findViewById(R.id.net_income_to_revenue);*/
        netIncomeToRevenue =
                new AnalysisView(scrollViewRoot.getContext(),
                        new Analysis(Recommendations.Neutral, "Test", "0.5", "Net income"));
        root.addView(netIncomeToRevenue);
        returnOnAssets = scrollViewRoot.findViewById(R.id.return_on_assets);
        returnOnEquity = scrollViewRoot.findViewById(R.id.return_on_equity);
        quickRatio = scrollViewRoot.findViewById(R.id.quick_ratio);
        currentRatio = scrollViewRoot.findViewById(R.id.current_ratio);
        debtToEquity = scrollViewRoot.findViewById(R.id.debt_to_equity);
        View placeholder = new View(getContext());
        placeholder.setMinimumHeight(100);
        root.addView(placeholder);
        return scrollViewRoot;
    }

    private View setupAnalysisEmpty(LayoutInflater inflater, ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_analysis_empty, container, false);
        TextView labelAnalysisEmpty = root.findViewById(R.id.label_analysis_empty);
        labelAnalysisEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_search);
            }
        });
        return root;
    }

    Share share;
    CashFlow cashFlow;
    BalanceSheet balanceSheet;

    private void performAnalysis(int id) {
        prepareDataFromDB(id);
    }

    private void setShareInfo() {
        ticker.setText(share.getTicker());
        industry.setText(share.getIndustry());
        name.setText(share.getName());
    }

    private void makeAnalysisOfReturns() {
        returnOnEquity.setText(Returns.INSTANCE.returnOnEquity(balanceSheet, cashFlow));
        returnOnAssets.setText(Returns.INSTANCE.returnOnAssets(balanceSheet, cashFlow));
    }

    private void makeAnalysisOfEfficiency() {
        // netIncomeToRevenue.valueView.setText(Efficiency.INSTANCE.netIncomeToRevenue(cashFlow));
    }

    private void makeDebtToEquity()
    {
        debtToEquity.setText(CreditRatio.findDebtRatio(balanceSheet));
    }

    private void prepareDataFromDB(int id) {

        TaskRunner.INSTANCE.execute(new FindAllDataByShareId(requireContext(), id), new TaskRunner.Callback<ShareDataBundle>() {
            @Override
            public void onComplete(ShareDataBundle result) {
                share = result.getShare();
                cashFlow = result.getCashFlow();
                balanceSheet = result.getBalanceSheet();
                setShareInfo();
                makeAnalysisOfEfficiency();
                makeAnalysisOfReturns();
                makeDebtToEquity();
            }
        });
    }
}