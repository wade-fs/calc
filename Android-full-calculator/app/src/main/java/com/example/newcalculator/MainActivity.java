package com.example.newcalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.enter_a_value).equals(display.getText().toString()))
                {
                    display.setText("");
                }
            }
        });
    }
    private void updateText(String StrToADD)
    {
        String oldStr = display.getText().toString();
        int CursorPos = display.getSelectionStart();

        String leftStr = oldStr.substring(0,CursorPos);
        String rightStr = oldStr.substring(CursorPos);
        if(getString(R.string.enter_a_value).equals(display.getText().toString())) {
            display.setText(StrToADD);
        }
        else{
            display.setText(String.format("%s%s%s",leftStr,StrToADD,rightStr));
        }
        display.setSelection(CursorPos + 1);
    }
    public void zeroBTN(View view){
        updateText("0");
    }
    public void oneBTN(View view){
        updateText("1");
    }
    public void twoBTN(View view){
        updateText("2");
    }
    public void threeBTN(View view){
        updateText("3");
    }
    public void fourBTN(View view){
        updateText("4");
    }
    public void fiveBTN(View view){
        updateText("5");
    }
    public void sixBTN(View view){
        updateText("6");
    }
    public void sevenBTN(View view){
        updateText("7");
    }
    public void eightBTN(View view){
        updateText("8");
    }
    public void nineBTN(View view){
        updateText("9");
    }
    public void plusorminusBTN(View view){
        updateText("-");
    }
    public void clearBTN(View view){
        display.setText("");
    }
    public void parenthesesBTN(View view){
        int CursorPos = display.getSelectionStart();
        int closePar = 0;
        int openPar = 0;
        int textLen = display.getText().toString().length();

        for(int i = 0 ; i < CursorPos ; i++) {
            if(display.getText().toString().charAt(i) == '('){
                openPar+=1;
            }
            if(display.getText().toString().charAt(i) == ')') {
                closePar += 1;
            }
        }
        if(closePar == openPar || display.getText().toString().charAt(textLen - 1) == '('){
            updateText("(");
        }
        else if(closePar < openPar && !(display.getText().toString().charAt(textLen - 1) == '(')){
            updateText(")");
        }
        display.setSelection(CursorPos + 1);
    }
    public void powerBTN(View view){
        updateText("^");
    }
    public void divideBTN(View view){
        updateText("÷");
    }
    public void multiplyBTN(View view){
        updateText("×");
    }
    public void minusBTN(View view){
        updateText("-");
    }
    public void plusBTN(View view){
        updateText("+");
    }
    public void equalsBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷" , "/");
        userExp = userExp.replaceAll("×" , "*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
    public void pointBTN(View view){
        updateText(".");
    }
    public void backspaceBTN(View view){
        int CursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(CursorPos != 0 && textLen != 0)
        {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(CursorPos - 1,CursorPos , "");
            display.setText(selection);
            display.setSelection(CursorPos - 1);
        }
    }
}