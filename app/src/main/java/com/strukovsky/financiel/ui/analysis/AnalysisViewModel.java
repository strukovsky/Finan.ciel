package com.strukovsky.financiel.ui.analysis;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class AnalysisViewModel extends ViewModel {

    private final SavedStateHandle savedStateHandle;
    public AnalysisViewModel() {
        this.savedStateHandle = new SavedStateHandle();
    }

    public AnalysisViewModel(SavedStateHandle savedStateHandle)
    {
        this.savedStateHandle = savedStateHandle;
    }

    public Integer getShareId() {
        return savedStateHandle.get("share_id");
    }

    public void setShareId(Integer id) {
        savedStateHandle.set("share_id", id);
    }
}