package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String operator;
    private double lastNumber;
    private boolean isLastOperator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface lcdFont = Typeface.createFromAsset( getAssets(), "lcdfont.ttf");



        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this , "Hello :) " , Toast.LENGTH_SHORT).show();
        Button btn0= findViewById(R.id.btn0);
        Button btn1= findViewById(R.id.btn1);
        Button btn2= findViewById(R.id.btn2);
        Button btn3= findViewById(R.id.btn3);
        Button btn4= findViewById(R.id.btn4);
        Button btn5= findViewById(R.id.btn5);
        Button btn6= findViewById(R.id.btn6);
        Button btn7= findViewById(R.id.btn7);
        Button btn8= findViewById(R.id.btn8);
        Button btn9= findViewById(R.id.btn9);
        Button btnMultiple= findViewById(R.id.btnMultiple);
        Button btnDivide= findViewById(R.id.btnDivide);
        Button btnDot= findViewById(R.id.btnDot);
        Button btnEqual= findViewById(R.id.btnEqual);
        Button btnMinus= findViewById(R.id.btnMinus);
        Button btnPlus= findViewById(R.id.btnPlus);
        final TextView txtMax = findViewById(R.id.txtMax);
        final TextView txtMin = findViewById(R.id.txtMin);
        Button btnClear =  findViewById(R.id.btnClear);
        txtMax.setTypeface(lcdFont);
        txtMin.setTypeface(lcdFont);
        View.OnClickListener numberClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                String newDigit = btn.getText().toString();
                if(isLastOperator){
                    txtMax.setText("0");
                    isLastOperator=false;
                    if(newDigit.equals(".")) return;

                }
                String oldMassage = txtMax.getText().toString();
                if(newDigit.equals(".") && oldMassage.contains("."))
                    return;
                if(oldMassage.length()>10)
                    return;
                if(oldMassage.equals("0")&& !(newDigit.equals("."))) oldMassage = newDigit;
                else
                    oldMassage+=newDigit;
                txtMax.setText(oldMassage);
            }
        };


        View.OnClickListener operatorClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                String newOperator = btn.getText().toString();
                String oldTextMin =  txtMin.getText().toString();
                String  textMax =  txtMax.getText().toString();
                if(isLastOperator)
                {
                    if(operator.equals("=")) {
                        if(!newOperator.equals("=")){
                            txtMin.setText(textMax+" " +newOperator+" ");
                            lastNumber =Double.parseDouble(textMax);
                            operator=newOperator;

                        }
                        return;
                    }
                    else if(newOperator.equals("=")){
                        operator ="=";
                        txtMin.setText("");
                        return;

                    }
                    operator=newOperator ;
                    String newString = oldTextMin.substring(0 , oldTextMin.length()-2)
                            + newOperator +" ";
                    txtMin.setText(newString);
                    return;
                }
                isLastOperator=true;
                String oldMassage = txtMax.getText().toString();
                double result = Double.parseDouble(textMax) ;
                if(oldTextMin!=""){

                    double number1 = lastNumber;
                    double number2 = Double.parseDouble(textMax);
                    if(operator.equals("+")) result = number1+number2;
                    else if(operator.equals("-")) result = number1-number2;
                    else if(operator.equals("*")) result = number1*number2;
                    else if(operator.equals("/")) result = number1/number2;
                }
                if(!(newOperator.equals("=")))
                    txtMin.setText(oldTextMin +textMax+" " +newOperator+" ");
                else
                    txtMin.setText("");

                txtMax.setText(""+result);
                lastNumber =Double.parseDouble(textMax);
                operator=newOperator;
            }
        };


        View.OnClickListener clearClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMax.setText("0");
                txtMin.setText("");
                isLastOperator=false;
                return;
            }
        };
        //numbers
        btn0.setOnClickListener(numberClick);
        btn1.setOnClickListener(numberClick);
        btn2.setOnClickListener(numberClick);
        btn3.setOnClickListener(numberClick);
        btn4.setOnClickListener(numberClick);
        btn5.setOnClickListener(numberClick);
        btn6.setOnClickListener(numberClick);
        btn7.setOnClickListener(numberClick);
        btn8.setOnClickListener(numberClick);
        btn9.setOnClickListener(numberClick);
        btnDot.setOnClickListener(numberClick);

        //operators
        btnMinus.setOnClickListener(operatorClick);
        btnPlus.setOnClickListener(operatorClick);
        btnMultiple.setOnClickListener(operatorClick);
        btnDivide.setOnClickListener(operatorClick);
        btnEqual.setOnClickListener(operatorClick);






        //clear
        btnClear.setOnClickListener(clearClick);



    }
}
