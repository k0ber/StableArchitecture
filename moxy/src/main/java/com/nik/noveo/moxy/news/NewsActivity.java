package com.nik.noveo.moxy.news;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.nik.noveo.moxy.R;
import com.nik.noveo.moxy.base.BaseActivity;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements NewsView {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_news)
    TextView messageTextView;

    //region todo Try to move it to base activity
    @Inject
    Provider<NewsPresenter> newsPresenterProvider;

    @InjectPresenter
    NewsPresenter newsPresenter;

    @ProvidePresenter
    NewsPresenter provideNewsPresenter() {
        return newsPresenterProvider.get();
    }
    //endregion

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.fab_load)
    public void onLoadCLicked() {
        newsPresenter.loadNews();
    }

    @Override
    public void showProgress(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        messageTextView.setText(message);
    }

}
