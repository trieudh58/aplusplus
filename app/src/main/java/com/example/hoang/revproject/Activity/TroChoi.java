package com.example.hoang.revproject.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.revproject.R;

import java.util.Random;

public class TroChoi extends AppCompatActivity {

    LinearLayout linear1, linear2, linear3, linear4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_choi);

        linear2 = (LinearLayout)findViewById(R.id.linear2);
        linear3 = (LinearLayout)findViewById(R.id.linear3);
        linear4 = (LinearLayout)findViewById(R.id.linear4);

        khoiTaoManChoi(linear2, linear3, linear4, "songxobo");
    }

    public void khoiTaoManChoi(LinearLayout linear1, LinearLayout linear2, LinearLayout linear3, String tumoi) {

        TextView tv[] = new TextView[tumoi.length()];
        tv = khoiTaoTextView(linear1, tumoi);

        Button btn[] = new Button[14];
        btn = khoiTaoButton(linear2, linear3, taoChuoi(tumoi));

        taoSuKien(tv, btn, linear1, linear2, linear3, tumoi);
    }

    public TextView[] khoiTaoTextView(LinearLayout linear, String a) {

        TextView[] tv = new TextView[a.length()];

        for (int i = 0; i < a.length(); i++) {

            tv[i] = new TextView(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(10, 0, 0, 0);
            lp.weight = 1;
            tv[i].setLayoutParams(lp);
            tv[i].setBackgroundColor(Color.BLUE);
            tv[i].setTextColor(Color.WHITE);
            tv[i].setTextSize(15);
            tv[i].setGravity(Gravity.CENTER);
            linear.addView(tv[i]);
        }

        return tv;
    }

    public Button[] khoiTaoButton(LinearLayout linear1, LinearLayout linear2, char a[]) {

        Button[] btn = new Button[14];

        for (int i = 0; i < 14; i++) {
            btn[i] = new Button(this);
            btn[i].setText(String.valueOf(a[i]));
            btn[i].setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            btn[i].setLayoutParams(lp);
            btn[i].setTextColor(Color.WHITE);

            if (i < 7) {
                linear1.addView(btn[i]);
            } else {
                linear2.addView(btn[i]);
            }
        }
        return btn;
    }

    public char[] taoChuoi(String a) {

        Random r = new Random();

        for (int i = a.length(); i < 14; i++) {
            char d;
            d = (char)(r.nextInt(26) + 'a');
            a += d;
        }

        char []b = a.toCharArray();

        for (int i = 0; i < 14; i++) {

            int t1 = r.nextInt(14);
            int t2 = r.nextInt(14);

            char c = b[t1];
            b[t1]  =  b[t2];
            b[t2] = c;
        }

        return b;
    }

    int t[] = new int[14];

    public void taoSuKien(final TextView tv[], final Button btn[], final LinearLayout linear,
                          final LinearLayout linear1, final LinearLayout linear2, final String tu) {

        final int n = tv.length;


        for (int i = 0; i < 14; i++) {

            final int a = i;

            btn[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    for (int j = 0; j < n; j++) {

                        if (tv[j].getText() == "") {

                            tv[j].setText(btn[a].getText());

                            btn[a].setVisibility(View.INVISIBLE);

                            t[j] = a;

                            if ( !chuoiTv(tv).equals(tu) && !chuoiTv(tv).equals("") ) {

                                Toast toast = Toast.makeText(TroChoi.this, "Từ mới chưa đúng!", Toast.LENGTH_SHORT);
                                toast.show();
                            } else if ( chuoiTv(tv).equals(tu) ) {

                                AlertDialog.Builder dialog = new AlertDialog.Builder(TroChoi.this);
                                dialog.setTitle("Chính xác!!!");
                                dialog.setMessage(tu);
                                dialog.setPositiveButton("Tiếp", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        for (int i = 0; i < n; i++) {

                                            linear.removeView(tv[i]);
                                        }

                                        for (int i = 0; i < 14; i++) {

                                            if (i < 7)
                                                linear1.removeView(btn[i]);
                                            else
                                                linear2.removeView(btn[i]);
                                        }

                                        khoiTaoManChoi(linear, linear1, linear2, "songdaica");
                                    }
                                });

                                dialog.create().show();
                            }

                            break;
                        }
                    }

                }
            });
        }

        for (int i = 0; i < n; i++) {

            final int a = i;

            tv[i].setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (tv[a].getText() != "") {

                        btn[t[a]].setVisibility(View.VISIBLE);

                        tv[a].setText("");

                    }
                }
            });
        }
    }

    public String chuoiTv(TextView tv[]) {

        String a;
        a = "";

        for (int i = 0; i < tv.length; i++) {
            if (tv[i].getText() == "")
                return "";
            else
                a += tv[i].getText();
        }
        return a;
    }
}
