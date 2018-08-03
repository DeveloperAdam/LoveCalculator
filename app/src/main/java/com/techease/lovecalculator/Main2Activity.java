package com.techease.lovecalculator;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    InterstitialAd interstitialAd;
    AdView adView,adView2;
    EditText etName,etLover;
    Button btnFInd;
    String loverName,youName;
    Random r = new Random();
    int i1 = r.nextInt(101 - 50) + 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((AppCompatActivity) this).getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);

        etName=findViewById(R.id.etName);
        etLover=findViewById(R.id.etLover);
        btnFInd=findViewById(R.id.btnFind);

        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));

        adView =  findViewById(R.id.adView2ndScreen);
        adView2 = findViewById(R.id.adView2ndScreen2);

        final AdRequest adRequest = new AdRequest.Builder().build();
    //<!--<string name="interstitial_1st">ca-app-pub-9382445490119921/9143748077</string>-->
    //<!--<string name="interstitial_2nd">ca-app-pub-9382445490119921/9826274352</string>-->
        adView.loadAd(adRequest);
        adView2.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(String.valueOf(R.string.interstitial_2nd));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                loverName=etLover.getText().toString();

                final Dialog dialog = new Dialog(Main2Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom);

                TextView text = (TextView) dialog.findViewById(R.id.tvResult);
                TextView tvPercentage = (TextView) dialog.findViewById(R.id.tvPercentage);
                tvPercentage.setText(String.valueOf(i1+"%"));
                Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        btnFInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loverName=etLover.getText().toString();
                youName=etName.getText().toString();
                if (youName.equals(""))
                {
                    etName.setError("Please enter your name here");
                }
                else
                    if (loverName.equals(""))
                    {
                        etLover.setError("Please enter your lover name here");
                    }
                    else

                    {
                        if (interstitialAd.isLoaded())
                        {
                            interstitialAd.show();
                        }
                        else
                        {
                            final Dialog dialog = new Dialog(Main2Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.setContentView(R.layout.custom);

                            TextView text = (TextView) dialog.findViewById(R.id.tvResult);
                            TextView tvPercentage = (TextView) dialog.findViewById(R.id.tvPercentage);
                            tvPercentage.setText(String.valueOf(i1+"%"));
                            Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    startActivity(new Intent(Main2Activity.this,Main2Activity.class));
                                    finish();
                                }
                            });

                            dialog.show();
                        }
                    }


            }
        });
    }
}
