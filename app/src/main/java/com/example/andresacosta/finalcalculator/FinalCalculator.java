package com.example.andresacosta.finalcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalCalculator extends AppCompatActivity {

    String textInfo, strNum1, strNum2, textResult;
    float num1, num2, numResult;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnResult, btnDiv, btnMul, btnAdd, btnSub, btnPoint, btnDel;
    TextView tvInfo, tvResult;
    Boolean opStatus = false;
    Boolean opDot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_calculator);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDel = findViewById(R.id.btnDel);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnPoint = findViewById(R.id.btnDec);
        btnResult = findViewById(R.id.btnResult);

        tvInfo = findViewById(R.id.tvInfo);
        tvResult = findViewById(R.id.tvResult);
    }

    public void btn7(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "7";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn8(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "8";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn9(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "9";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn4(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "4";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn5(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "5";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn6(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "6";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn1(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "1";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn2(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "2";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn3(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "3";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btn0(View view) {
        textInfo = tvInfo.getText().toString();
        textInfo = textInfo + "0";
        tvInfo.setText(textInfo);
        opStatus = false;
    }

    public void btnDel(View view){
        textInfo = tvInfo.getText().toString();
        if(textInfo!=null && textInfo.length()>0){
            System.out.println(textInfo.substring(textInfo.length()-1));
            if ((textInfo.substring(textInfo.length()-1).equals("*") || textInfo.substring(textInfo.length()-1).equals("+") || textInfo.substring(textInfo.length()-1).equals("-") || textInfo.substring(textInfo.length()-1).equals("÷"))){
                opStatus = false;
            }
            if (textInfo.substring(textInfo.length()-1)=="."){
                opDot = false;
            }
            textInfo = textInfo.substring(0, textInfo.length()-1);
        }else{
            opStatus = false;
            opDot = false;
        }
        tvInfo.setText(textInfo);
        tvResult.setText(textResult);
    }

    public void btnDiv(View view){
        if (opStatus){
        }else{
            textInfo = tvInfo.getText().toString();
            textInfo = textInfo + "÷";
            tvInfo.setText(textInfo);
            opStatus = true;
            opStatus = false;
        }
    }

    public void btnMul(View view){
        if (opStatus){
        }else{
            textInfo = tvInfo.getText().toString();
            textInfo = textInfo + "*";
            tvInfo.setText(textInfo);
            opStatus = true;
            opStatus = false;
        }
    }

    public void btnSub(View view){
        if (opStatus){
        }else{
            textInfo = tvInfo.getText().toString();
            textInfo = textInfo + "-";
            tvInfo.setText(textInfo);
            opStatus = true;
            opStatus = false;
        }
    }

    public void btnAdd(View view){
        if (opStatus){
        }else{
            textInfo = tvInfo.getText().toString();
            textInfo = textInfo + "+";
            tvInfo.setText(textInfo);
            opStatus = true;
            opStatus = false;
        }
    }

    public void btnPoint(View view){
        if (opStatus){
        }else{
            textInfo = tvInfo.getText().toString();
            textInfo = textInfo + ".";
            tvInfo.setText(textInfo);
            opStatus = true;
        }
    }

    public void btnResult(View view){ Equal(); }

    public void LayoutClick(View view){
        Intent iSecond = new Intent(this,HistActivity.class);
        iSecond.putExtra("textInfo",textInfo);
        startActivityForResult(iSecond,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                textResult = data.getStringExtra("textResult");
                tvInfo.setText(textResult);
                Equal();
            }
        }
    }

    public void Equal () {
        textInfo = tvInfo.getText().toString();
        ArrayList<String> Operator = new ArrayList<>();
        ArrayList<Float> Number = new ArrayList<>();

        Pattern p = Pattern.compile("[-*+÷]+|\\d+");
        Matcher m = p.matcher(textInfo);
        final ArrayList<String> allMatches = new ArrayList<>();
        while(m.find()){
            allMatches.add(m.group());
            if (isNumeric(m.group())){
                Number.add(Float.valueOf(m.group()));
                System.out.println("Number: "+m.group());
            }else{
                Operator.add(m.group());
                System.out.println("Operator: "+m.group());
            }
        }

        float Acumulado = 0;
        int x = 0;
        for(int i=0;i<Number.size();i=i+2){
            if (i>=Number.size()-1){
                switch (Operator.get(x)) {
                    case "+":
                        Acumulado = Acumulado + Number.get(i - 1);
                        break;
                    case "-":
                        Acumulado = Acumulado - Number.get(i - 1);
                        break;
                    case "*":
                        Acumulado = Acumulado * Number.get(i - 1);
                        break;
                    case "÷":
                        Acumulado = Acumulado / Number.get(i - 1);
                        break;
                }
            }else{
                switch (Operator.get(x)) {
                    case "+":
                        Acumulado = Acumulado + (Number.get(i) + Number.get(i + 1));
                        break;
                    case "-":
                        Acumulado = Acumulado + (Number.get(i) - Number.get(i + 1));
                        break;
                    case "*":
                        Acumulado = Acumulado + (Number.get(i) * Number.get(i + 1));
                        break;
                    case "÷":
                        Acumulado = Acumulado + (Number.get(i) / Number.get(i + 1));
                        break;
                }
            }
            x++;
        }
        tvResult.setText(String.valueOf(Acumulado));
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
