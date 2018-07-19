package com.hackerkernel.user.sqrfactor.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hackerkernel.user.sqrfactor.Activities.HomeScreenActivity;
import com.hackerkernel.user.sqrfactor.Activities.ResetPasswordActivity;
import com.hackerkernel.user.sqrfactor.Constants.SPConstants;
import com.hackerkernel.user.sqrfactor.Constants.ServerConstants;
import com.hackerkernel.user.sqrfactor.Network.MyVolley;
import com.hackerkernel.user.sqrfactor.R;
import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;
import com.hackerkernel.user.sqrfactor.Utils.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private Button mLoginButton;
    private TextView mForgotTextView;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private ProgressBar mPb;
    private CheckBox mRememberMeBox;
    private LinearLayout mContentLayout;
    private MySharedPreferences mSp;
    private RequestQueue mRequestQueue;


    //static interface Listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_login, container, false);

        mLoginButton = rootView.findViewById(R.id.login);
        mForgotTextView = rootView.findViewById(R.id.forgot);
        mEmailEditText = rootView.findViewById(R.id.login_email);
        mPasswordEditText = rootView.findViewById(R.id.login_password);
        mRememberMeBox = rootView.findViewById(R.id.login_remember_me);

        mSp = MySharedPreferences.getInstance(getActivity());

        mPb = rootView.findViewById(R.id.pb);
        mContentLayout = rootView.findViewById(R.id.content_layout);

        String savedEmail = mSp.getKey(SPConstants.EMAIL);
        String savedPassword = mSp.getKey(SPConstants.PASSWORD);

        if (savedEmail != null && savedPassword != null) {
            mEmailEditText.setText(savedEmail);
            mPasswordEditText.setText(savedPassword);

            mRememberMeBox.setChecked(true);
        }

        mRequestQueue = MyVolley.getInstance().getRequestQueue();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                loginApi(email, password);

            }
        });

        mForgotTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), ResetPasswordActivity.class);
                startActivity(i);

            }
        });

        return rootView;

    }

    private void loginApi(final String email, final String password) {
        mPb.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
        if (!NetworkUtil.isNetworkAvailable()) {
            Toast.makeText(getActivity(), "No internet", Toast.LENGTH_SHORT).show();
            mPb.setVisibility(View.GONE);
            mContentLayout.setVisibility(View.VISIBLE);
            return;
        }

        mRequestQueue = MyVolley.getInstance().getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.POST, ServerConstants.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mPb.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.VISIBLE);
                Log.d(TAG, "onResponse: Dashboard response = " + response);
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONObject successObject = responseObject.getJSONObject("success");
                    JSONObject userObject = responseObject.getJSONObject("user");

                    String token = successObject.getString("token");
                    String profileUrl = userObject.getString("profile");

                    mSp.setKey(SPConstants.API_KEY, token);
                    mSp.setKey(SPConstants.PROFILE_URL, profileUrl);

                    if (mRememberMeBox.isChecked()) {
                        mSp.setKey(SPConstants.EMAIL, email);
                        mSp.setKey(SPConstants.PASSWORD, password);

                    } else {
                        mSp.removeKey(SPConstants.EMAIL);
                        mSp.removeKey(SPConstants.PASSWORD);
                    }

                    Intent i = new Intent(getActivity(), HomeScreenActivity.class);
                    startActivity(i);
                    Toast.makeText(getActivity(), "Logged In successfully", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPb.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.VISIBLE);
                NetworkUtil.handleSimpleVolleyRequestError(error, getActivity());
            }
        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                final Map<String, String> headers = new HashMap<>();
//                headers.put("AUTHORIZATION", mSp.getKey(SPConstants.API_KEY));
////                headers.put(getString(R.string.authorization_all_caps), "eb8ffe1a3d5a2eac01aab78496d49c88");  //TODO Remove this after testing
////                headers.put("Content-Type", contentType);
//                return headers;
//            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

}
