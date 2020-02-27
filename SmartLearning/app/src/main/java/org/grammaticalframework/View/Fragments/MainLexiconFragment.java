package org.grammaticalframework.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.grammaticalframework.R;
import org.grammaticalframework.View.FragmentFactory;
import org.grammaticalframework.ViewModel.LexiconViewModel;
import org.grammaticalframework.ViewModel.LexiconWord;
import org.grammaticalframework.ViewModel.LexiconWordAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainLexiconFragment extends BaseFragment {

    private LexiconViewModel lexiconVM;
    private List<LexiconWord> lexiconWordList;
    private final BaseFragment lexiconDetailsFragment = FragmentFactory.createLexiconDetailsFragment();
    private EditText search_bar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_lexicon, container, false);
        search_bar = fragmentView.findViewById(R.id.lexicon_searchbar);

        search_bar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    search_bar.clearFocus();
                    hideKeyboard(textView);
                }
                return false;
            }
        });

        RecyclerView rvLexicon = (RecyclerView) fragmentView.findViewById(R.id.lexicon_recyclerview);

        lexiconWordList = testPopulateWords(6);

        LexiconWordAdapter wordAdapter = new LexiconWordAdapter(lexiconWordList, lexiconDetailsFragment);

        rvLexicon.setAdapter(wordAdapter);
        rvLexicon.setLayoutManager(new LinearLayoutManager(getActivity()));

        return fragmentView;

    }

    private List<LexiconWord> testPopulateWords(int amount) {
        List<LexiconWord> wordList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            wordList.add(new LexiconWord("Word " + (i + 1), "Explanation"));
        }

        return wordList;
    }

    private void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
