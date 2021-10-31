package com.strukovsky.financiel.ui.analysis;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.strukovsky.financiel.R;
import com.strukovsky.financiel.db.entity.Share;
import com.strukovsky.financiel.db.task.TaskRunner;
import com.strukovsky.financiel.db.task.share.FindShareByIdTask;

public class AnalysisFragment extends Fragment {

    private AnalysisViewModel analysisViewModel;
    TextView ticker;
    TextView industry;
    TextView name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        analysisViewModel =
                new ViewModelProvider(this).get(AnalysisViewModel.class);
        View root = inflater.inflate(R.layout.fragment_analysis, container, false);
        ticker = root.findViewById(R.id.share_ticker);
        industry = root.findViewById(R.id.share_industry);
        name = root.findViewById(R.id.share_name);
        if (getArguments() == null)
        {

        }
        else performAnalysis();

        return root;
    }

    private void performAnalysis()
    {
        int id = getArguments().getInt("SHARE_ID");
        TaskRunner.INSTANCE.execute(new FindShareByIdTask(requireContext(), id), new TaskRunner.Callback<Share>() {
            @Override
            public void onComplete(Share share) {
                ticker.setText(share.getTicker());
                industry.setText(share.getIndustry());
                name.setText(share.getName());
            }
        });
    }
}