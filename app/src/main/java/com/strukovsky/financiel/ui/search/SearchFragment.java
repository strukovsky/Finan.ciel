package com.strukovsky.financiel.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.strukovsky.financiel.R;
import com.strukovsky.financiel.db.entity.Share;
import com.strukovsky.financiel.db.task.TaskRunner;
import com.strukovsky.financiel.db.task.share.FindShareByTickerTask;
import com.strukovsky.financiel.db.task.share.ReadAllSharesTask;
import com.strukovsky.financiel.ui.adapter.ShareAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements TextWatcher {

    private SearchViewModel searchViewModel;
    private  NavController navController;
    private ShareAdapter shareAdapter = new ShareAdapter(new ArrayList<Share>());
    private final List<Share> allShares = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        EditText query = root.findViewById(R.id.query);
        RecyclerView searchResult = root.findViewById(R.id.search_result);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        shareAdapter.setNavController(navController);
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
            TaskRunner.INSTANCE.execute(new FindShareByTickerTask(requireContext(), queryString), new TaskRunner.Callback<List<? extends Share>>() {
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