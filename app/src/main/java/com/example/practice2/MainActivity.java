package com.example.practice2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practice2.databinding.ActivityMainLinearBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        3 problem
//        TextView textView = findViewById(R.id.text);
//        textView.setText(R.string.Recipient);
//
//        ImageView imageView = findViewById(R.id.imageCats);
//        imageView.setImageResource(R.drawable.cats);

//        4 problem
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Click programmatically", "Button is clicked");
//            }
//        });

        ActivityMainLinearBinding binding = ActivityMainLinearBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        5 problem
//        binding.text.setText(R.string.Recipient);
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Binding click", "Button is clicked");
//            }
//        });

        ActivityResultLauncher<Intent> mStartFroActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            Intent intent = result.getData();
                            String text;
                            if (!Objects.equals(intent.getStringExtra("result"), "")) {
                                text = "Вы отправили валентинку с текстом:\n" + intent.getStringExtra("result");
                            } else text = "Вы отправили пустую валентинку";
                            binding.lastRecipient.setText(text);
                        }
                    }
                });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("name", binding.editText.getText().toString());

                mStartFroActivityResult.launch(intent);
            }
        });
    }

}