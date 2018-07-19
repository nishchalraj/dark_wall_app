package com.hackerkernel.user.sqrfactor.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private Toolbar mToolbar;

    List<QuestionCommentClass> mComments;
    QuestionCommentsAdapter mCommentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationIcon(R.drawable.back_arrow);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.question_menu_edit: {
                showQuestionEditDialog();
            }

            case R.id.question_menu_delete: {

            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showQuestionEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_question_dialog,null);

        Intent i = getIntent();
        String questionId = i.getStringExtra(BundleConstants.QUESTION_ID);
        String questionDesc = i.getStringExtra(BundleConstants.QUESTION_DESCRIPTION);

        final EditText questionEditText = view.findViewById(R.id.question);

        questionEditText.setText(questionDesc);
        questionEditText.setSelection(questionEditText.getText().length());

        builder.setTitle("Edit your question").setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String question = questionEditText.getText().toString();
                        if (question.length() < 1){
                            Toast.makeText(QuestionDetailActivity.this, "Type at least one character before saving changes", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        wallQuestionUpdateApi();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void wallQuestionUpdateApi() {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();

    }
}
