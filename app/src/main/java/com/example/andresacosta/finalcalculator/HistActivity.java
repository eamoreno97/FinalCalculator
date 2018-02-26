package com.example.andresacosta.finalcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistActivity extends AppCompatActivity {

    private String textInfo, strNum1, strNum2, textResult;
    private Float num1, num2, numResult;
    private LinearLayout parent;
    private ScrollView scrollView;
    private int positionChar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        int numStr = 1;

        Intent iSecond = getIntent();
        textInfo = iSecond.getStringExtra("textInfo");

        parent = findViewById(R.id.Parent);

        ScrollView scrollView = new ScrollView(HistActivity.this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        parent.addView(scrollView);

        LinearLayout childScroll = new LinearLayout(HistActivity.this);
        childScroll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        childScroll.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(childScroll);

        if (textInfo != null){

            System.out.println("Recibi: "+textInfo);
            String returnString = "";
            Pattern p = Pattern.compile("[-*+÷]+|\\d+");
            Matcher m = p.matcher(textInfo);
            final ArrayList<String> allMatches = new ArrayList<>();
            while (m.find()) {
                allMatches.add(m.group());
                System.out.println(m.group());

                LinearLayout child = new LinearLayout(HistActivity.this);
                child.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                childScroll.addView(child);

                final TextView tv = new TextView(this);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(20, 20, 20, 20);
                tv.setTextSize(20);
                tv.setText(m.group());

                final Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
                btn.setGravity(Gravity.CENTER);
                btn.setPadding(20, 20, 20, 20);
                btn.setTextSize(20);
                btn.setText("#"+String.valueOf(numStr)+" Edit");
                numStr++;

                child.addView(tv);
                child.addView(btn);

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String numChange = btn.getText().toString();
                        positionChar = Character.getNumericValue(numChange.charAt(1));
                        Character checkChar = numChange.charAt(2);
                        if(isNumeric(checkChar.toString())){
                            System.out.println("Es Valido");
                            String newStr = String.valueOf(positionChar) + String.valueOf(Character.getNumericValue(numChange.charAt(2)));
                            positionChar = Integer.valueOf(newStr);
                        };
                        if(isNumeric(allMatches.get(positionChar-1))){
                            positionChar = Character.getNumericValue(numChange.charAt(1));
                            checkChar = numChange.charAt(2);
                            if(isNumeric(checkChar.toString())){
                                System.out.println("Es Valido");
                                String newStr = String.valueOf(positionChar) + String.valueOf(Character.getNumericValue(numChange.charAt(2)));
                                positionChar = Integer.valueOf(newStr);
                            };
                            AlertDialog.Builder alert = new AlertDialog.Builder(HistActivity.this);
                            alert.setTitle("Editar Numero");
                            alert.setMessage("Ingrese el numero caracter: ");
                            final EditText input = new EditText(HistActivity.this);
                            input.setInputType(InputType.TYPE_CLASS_NUMBER);
                            input.setRawInputType(Configuration.KEYBOARD_12KEY);
                            alert.setView(input);
                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String EditNumber = input.getText().toString();
                                    tv.setText(EditNumber);
                                    System.out.println(positionChar);
                                    System.out.println(allMatches.get(positionChar-1));
                                    allMatches.set(positionChar-1,EditNumber);
                                }
                            });

                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            });
                            alert.show();
                        }else{
                            final CharSequence operators[] = new CharSequence[] {"*", "÷", "+", "-"};
                            positionChar = Character.getNumericValue(numChange.charAt(1));
                            checkChar = numChange.charAt(2);
                            if(isNumeric(checkChar.toString())){
                                String newStr = String.valueOf(positionChar) + String.valueOf(Character.getNumericValue(numChange.charAt(2)));
                                positionChar = Integer.valueOf(newStr);
                            };
                            AlertDialog.Builder alert = new AlertDialog.Builder(HistActivity.this);
                            alert.setTitle("Editar Operación");
                            alert.setItems(operators, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String EditNumber = operators[i].toString();
                                    tv.setText(EditNumber);
                                    System.out.println(positionChar);
                                    System.out.println(allMatches.get(positionChar-1));
                                    allMatches.set(positionChar-1,EditNumber);
                                }
                            });
                            alert.show();
                        }

                    }
                });
            }

            LinearLayout childFinal = new LinearLayout(HistActivity.this);
            childFinal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
            childFinal.setOrientation(LinearLayout.HORIZONTAL);
            childScroll.addView(childFinal);

            Button btnReturn = new Button(this);
            btnReturn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
            btnReturn.setGravity(Gravity.CENTER);
            btnReturn.setPadding(20, 20, 20, 20);
            btnReturn.setTextSize(20);
            btnReturn.setText("Regresar");

            childFinal.addView(btnReturn);

            btnReturn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent returnIntent = new Intent();
                    textResult = "";
                    for(int i=0; i<allMatches.size(); i++){
                        textResult += allMatches.get(i);
                    }

                    System.out.println("Texto de Salida: " + textResult);
                    returnIntent.putExtra("textResult",textResult.toString());
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            });
        }
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
