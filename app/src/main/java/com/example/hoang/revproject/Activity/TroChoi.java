package com.example.hoang.revproject.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.List;
import java.util.Random;

public class TroChoi extends AppCompatActivity {

    LinearLayout linear1, linear2, linear3, linear4;
    List <VocabularyModel> listVocab;
    AlarmDBHelper alarmDBHelper;
    ImageView image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_choi);

        linear2 = (LinearLayout)findViewById(R.id.linear2);
        linear3 = (LinearLayout)findViewById(R.id.linear3);
        linear4 = (LinearLayout)findViewById(R.id.linear4);
        image = (ImageView) findViewById(R.id.image);

        alarmDBHelper = new AlarmDBHelper(this);
        listVocab = alarmDBHelper.getListVocabs();
        Random rd = new Random();
        int index = rd.nextInt(listVocab.size());

        VocabularyModel model = listVocab.get(index);

        String tuSauChuanHoa = chuanHoa(model.getWord());

        for (int i = 0; tuSauChuanHoa.length() > 9; i++) {

            index = rd.nextInt(listVocab.size());

            model = listVocab.get(index);

            tuSauChuanHoa = chuanHoa(model.getWord());
        }

        khoiTaoManChoi(linear2, linear3, linear4, tuSauChuanHoa);

        int imageResource = this.getResources().getIdentifier(model.getImagePath(), null, this.getPackageName());
        Drawable res = this.getResources().getDrawable(imageResource);
        image.setImageDrawable(res);


    }

    public String chuanHoa(String tu) {

        String tuSauChuanHoa;
        tuSauChuanHoa = tu.toLowerCase().trim();
        char temp[] = tuSauChuanHoa.toCharArray();
        for (int i = 0; i < temp.length; i++) {

            if (temp[i] == ' ') {

                for (int j = i; j < temp.length - 1; j++) {

                    temp[j] = temp[j + 1];
                }

                temp[temp.length - 1] = '\0';
                i--;
            }
        }

        tuSauChuanHoa = tuSauChuanHoa.valueOf(temp);
        tuSauChuanHoa = tuSauChuanHoa.trim();

        return tuSauChuanHoa;
    }

    public void khoiTaoManChoi(LinearLayout linear1, LinearLayout linear2, LinearLayout linear3, String tumoi) {

        Button btnOChu[] = new Button[tumoi.length()];
        btnOChu = khoiTaoOChu(linear1, tumoi);

        Button btn[] = new Button[14];
        btn = khoiTaoButton(linear2, linear3, taoChuoi(tumoi));

        taoSuKien(btnOChu, btn, linear1, linear2, linear3, tumoi);
    }

    public Button[] khoiTaoOChu(LinearLayout linear, String a) {

        int temp;
        if (a.length()%2 == 0)
            temp = 8;
        else
            temp = 9;

        Button[] btnOChu = new Button[temp];

        for (int i = 0; i < temp; i++) {

            btnOChu[i] = new Button(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(5, 0, 5, 0);
            lp.weight = 1;
            btnOChu[i].setLayoutParams(lp);
            btnOChu[i].setBackgroundResource(R.drawable.btn_toper);
            btnOChu[i].setTextColor(Color.BLACK);
            btnOChu[i].setTextSize(18);
            btnOChu[i].setGravity(Gravity.CENTER);

            if (i < (temp - a.length()) / 2 || i >= (temp + a.length()) / 2) {
                btnOChu[i].setVisibility(View.INVISIBLE);
                btnOChu[i].setEnabled(false);
            }
            linear.addView(btnOChu[i]);
        }

        return btnOChu;
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
            lp.setMargins(6, 5, 6, 0);
            btn[i].setLayoutParams(lp);
            btn[i].setTextColor(Color.WHITE);
            btn[i].setTextSize(18);
            btn[i].setBackgroundResource(R.drawable.btn_below);

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

    public void taoSuKien(final Button btnOChu[], final Button btn[], final LinearLayout linear,
                          final LinearLayout linear1, final LinearLayout linear2, final String tu) {

        final int n = btnOChu.length;


        for (int i = 0; i < 14; i++) {

            final int a = i;

            btn[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    for (int j = 0; j < n; j++) {

                        if (btnOChu[j].getText() == "" && j >= (btnOChu.length - tu.length()) / 2 && j < (btnOChu.length + tu.length()) / 2) {

                            btnOChu[j].setText(btn[a].getText());

                            btn[a].setVisibility(View.INVISIBLE);

                            t[j] = a;

                            if ( !chuoiTv(btnOChu, tu.length()).equals(tu) && !chuoiTv(btnOChu, tu.length()).equals("") ) {

                                Toast toast = Toast.makeText(TroChoi.this, "Từ mới chưa đúng!", Toast.LENGTH_SHORT);
                                toast.show();
                            } else if ( chuoiTv(btnOChu, tu.length()).equals(tu) ) {

                                AlertDialog.Builder dialog = new AlertDialog.Builder(TroChoi.this);
                                dialog.setTitle("Chính xác!!!");
                                dialog.setMessage(tu);
                                dialog.setPositiveButton("Tiếp", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
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

            btnOChu[i].setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (btnOChu[a].getText() != "") {

                        btn[t[a]].setVisibility(View.VISIBLE);

                        btnOChu[a].setText("");

                    }
                }
            });
        }
    }

    public String chuoiTv(TextView tv[], int doDaiTu) {

        String a;
        a = "";

        int temp;
        if (doDaiTu%2 == 0)
            temp = 8;
        else
            temp = 9;

        for (int i = 0; i < tv.length; i++) {
            if (i >= (temp - doDaiTu) / 2 && i < (temp + doDaiTu) / 2 && tv[i].getText() == "")
                return "";
            else
                a += tv[i].getText();
        }
        return a;
    }
}
