package com.hackerkernel.user.sqrfactor.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.Adapters.QuestionCommentsAdapter;
import com.hackerkernel.user.sqrfactor.Constants.BundleConstants;
import com.hackerkernel.user.sqrfactor.Pojo.QuestionCommentClass;
import com.hackerkernel.user.sqrfactor.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionDetailActivity extends AppCompatActivity {
    private static final String TAG = "QuestionDetailActivity";

    private WebView mWebView;
    private RecyclerView mRecyclerView;
    private TextView mCommentsCountTV;

    List<QuestionCommentClass> mComments;
    QuestionCommentsAdapter mCommentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        mWebView = findViewById(R.id.question_detail_desc);
        mRecyclerView = findViewById(R.id.question_detail_rv);
        mCommentsCountTV = findViewById(R.id.question_detail_comment_count);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mComments = new ArrayList<>();
        mCommentsAdapter = new QuestionCommentsAdapter(this, mComments);
        mRecyclerView.setAdapter(mCommentsAdapter);

        Intent i = getIntent();

        try {
            JSONArray commentsArray = new JSONArray(i.getStringExtra(BundleConstants.COMMENTS_ARRAY));
            String description = i.getStringExtra(BundleConstants.QUESTION_DESCRIPTION);

            Log.d(TAG, "onCreate: comments array = " + i.getStringExtra(BundleConstants.COMMENTS_ARRAY));
            Log.d(TAG, "onCreate: question description = " + description);

            mWebView.loadData(description, "text/html", null);

            if (commentsArray.length() > 0) {
                mCommentsCountTV.setText(commentsArray.length() + " comments");

                for (int j = 0; j < commentsArray.length(); j++) {
                    JSONObject singleObject = commentsArray.getJSONObject(j);
                    JSONObject userObject = singleObject.getJSONObject("user");


                    String id = singleObject.getString("id");
                    String commentDescription = singleObject.getString("comment");
                    String askedBy = userObject.getString("name");
                    String imageUrl = userObject.getString("profile");

                    QuestionCommentClass comment = new QuestionCommentClass(id, commentDescription, askedBy, "", imageUrl);
                    mComments.add(comment);
                    mCommentsAdapter.notifyDataSetChanged();

                }

            } else {
                mCommentsCountTV.setVisibility(View.GONE);
            }





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
