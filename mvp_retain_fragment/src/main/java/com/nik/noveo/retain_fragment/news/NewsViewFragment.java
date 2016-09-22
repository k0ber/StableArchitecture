package com.nik.noveo.retain_fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nik.noveo.retain_fragment.R;
import com.nik.noveo.retain_fragment.base.BaseFragment;

import butterknife.BindView;
import rx.functions.Func0;

public class NewsViewFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.tv_news) TextView newsText;
    @BindView(R.id.progress_bar) View progressBar;


    static NewsViewFragment newInstance() {
        return new NewsViewFragment();
    }

    @Override
    protected Func0<NewsPresenter> getCreatePresenterFunc() {
        return () -> new NewsPresenter(new NewsRepository());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showText(String text) {
        newsText.setText(text);
    }

    public void loadNewsClicked() {
        presenter.loadNews();
    }

}
