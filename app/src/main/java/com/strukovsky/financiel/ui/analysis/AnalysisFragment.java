package com.strukovsky.financiel.ui.analysis;

import android.os.Bundle;
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
import com.strukovsky.financiel.analyzers.Analyzer;
import com.strukovsky.financiel.calculations.CreditRatio;
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
    AnalysisView returnOnEquity;
    AnalysisView returnOnAssets;
    AnalysisView quickRatio;
    AnalysisView currentRatio;
    AnalysisView debtToEquity;

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
            prepareDataFromDB(shareId);
            root = setupAnalysisView(inflater, container);
        }

        return root;
    }

    private View setupAnalysisView(LayoutInflater inflater, ViewGroup container) {
        View scrollViewRoot = inflater.inflate(R.layout.fragment_analysis, container, false);
        LinearLayout root = scrollViewRoot.findViewById(R.id.root);
        ticker = scrollViewRoot.findViewById(R.id.share_ticker);
        industry = scrollViewRoot.findViewById(R.id.share_industry);
        name = scrollViewRoot.findViewById(R.id.share_name);

        root.addView(new AnalysisSectionHeaderView(requireContext(), "Efficiency analysis"));

        netIncomeToRevenue =
                new AnalysisView(requireContext());
        root.addView(netIncomeToRevenue);


        root.addView(new AnalysisSectionHeaderView(requireContext(), "Returns"));
        returnOnAssets = new AnalysisView(requireContext());
        root.addView(returnOnAssets);

        returnOnEquity = new AnalysisView(requireContext());
        root.addView(returnOnEquity);

        root.addView(new AnalysisSectionHeaderView(requireContext(), "Bankrupt analysis"));

        quickRatio = new AnalysisView(requireContext());
        currentRatio =  new AnalysisView(requireContext());
        debtToEquity = new AnalysisView(requireContext());

        root.addView(quickRatio);
        root.addView(currentRatio);
        root.addView(debtToEquity);

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

    private void setShareInfo() {
        ticker.setText(share.getTicker());
        industry.setText(share.getIndustry());
        name.setText(share.getName());
    }

    private void makeAnalysisOfReturns() {
        returnOnEquity.populate(Analyzer.INSTANCE.analyzeReturnOnEquity(balanceSheet, cashFlow));
        returnOnAssets.populate(Analyzer.INSTANCE.analyzeReturnOnAssets(balanceSheet, cashFlow));
    }

    private void makeAnalysisOfEfficiency() {
        netIncomeToRevenue.populate( Analyzer.INSTANCE.analyzeNetIncomeToRevenue(cashFlow));
    }

    private void makeAnalysisOfBankrupt()
    {
        quickRatio.populate(Analyzer.INSTANCE.analyzeQuickRatio(balanceSheet));
        currentRatio.populate(Analyzer.INSTANCE.analyzeCurrentRatio(balanceSheet));
        debtToEquity.populate(Analyzer.INSTANCE.analyzeDebtToEquity(balanceSheet));
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
                makeAnalysisOfBankrupt();
            }
        });
    }
}