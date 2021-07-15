package com.example.a15july_retrofit_i_wepart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mEtUserId;
    private Button mBtnCallApi;
    private TextView mTvFirstName;
    private TextView mTvLastName;
    private ImageView mIvAvatar;
    private TextView mTvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mEtUserId = findViewById(R.id.etUserId);
        mTvEmail = findViewById(R.id.tvEmail);
        mTvFirstName = findViewById(R.id.tvfirstName);
        mTvLastName = findViewById(R.id.tvLastName);
        mBtnCallApi = findViewById(R.id.btnCallApi);
        mIvAvatar = findViewById(R.id.ivAvatar);
        mBtnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = Network.getInstance().create(ApiService.class);
                int userId = Integer.parseInt(mEtUserId.getText().toString());
                apiService.getUser(userId).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        ResponseModel model = response.body();
                        String firstName = model.getData().getFirstName();
                        String lastName = model.getData().getLastName();
                        String email = model.getData().getEmail();
                        mTvFirstName.setText(firstName);
                        mTvLastName.setText(lastName);
                        mTvEmail.setText(email);

                        Glide.with(mIvAvatar).load(model.getData().getAvatar()).into(mIvAvatar);
                        //Glide.with(mIvAvatar).load(model.getData().getAvatar().into(mIvAvatar);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });

            }
        });
    }
}