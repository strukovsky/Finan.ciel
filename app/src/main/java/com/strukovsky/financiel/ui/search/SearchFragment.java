package com.strukovsky.financiel.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.strukovsky.financiel.MainActivity;
import com.strukovsky.financiel.R;
import com.strukovsky.financiel.db.entity.Share;
import com.strukovsky.financiel.db.task.TaskRunner;
import com.strukovsky.financiel.db.task.share.FindShareByTicker;
import com.strukovsky.financiel.db.task.share.ReadAllSharesTask;
import com.strukovsky.financiel.ui.adapter.ShareAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment implements TextWatcher {

    private SearchViewModel searchViewModel;
    private final ShareAdapter shareAdapter = new ShareAdapter(new ArrayList<>());
    private final List<Share> allShares = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        EditText query = root.findViewById(R.id.query);
        RecyclerView searchResult = root.findViewById(R.id.search_result);
        searchResult.setAdapter(shareAdapter);
        searchResult.setLayoutManager(new LinearLayoutManager(root.getContext()));

        TaskRunner.INSTANCE.execute(new ReadAllSharesTask(this.requireContext()), new TaskRunner.Callback<List<? extends Share>>() {
            @Override
            public void onComplete(List<? extends Share> result) {
                allShares.addAll(result);
                shareAdapter.updateData(allShares);

            }
        });

        query.addTextChangedListener(this);

        return root;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String queryString = s.toString();
        if (queryString.equals("")) {
           shareAdapter.updateData(allShares);
        } else
            TaskRunner.INSTANCE.execute(new FindShareByTicker(requireContext(), queryString), new TaskRunner.Callback<List<? extends Share>>() {
                @Override
                public void onComplete(List<? extends Share> result) {
                    shareAdapter.updateData(result);
                }
            });
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}