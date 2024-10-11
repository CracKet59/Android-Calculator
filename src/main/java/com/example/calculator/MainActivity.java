package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv, nameTv; // TextViews for result, solution, and name/SID
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonPlus, buttonMinus, buttonMul, buttonDivide, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextViews for result, solution, and name/SID
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        nameTv = findViewById(R.id.name_tv); // Ensure you have a TextView in your layout for the name/SID

        // Display name and SID
        nameTv.setText("Phu Nguyen - SID: 49991024");

        // Assign button IDs
        assignID(buttonC, R.id.button_c);
        assignID(buttonBrackOpen, R.id.button_open_bracket);
        assignID(buttonBrackClose, R.id.button_close_bracket);
        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(buttonPlus, R.id.button_plus);
        assignID(buttonMinus, R.id.button_minus);
        assignID(buttonMul, R.id.button_mul);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonAC, R.id.button_ac);
        assignID(buttonDot, R.id.button_dot);
        assignID(buttonEqual, R.id.button_equals);
    }

    // Method to assign button IDs and set click listeners
    void assignID(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v; // Fixed reference to `v`
        String buttonText = button.getText().toString();
        String dataTocalculate = solutionTv.getText().toString();

        // Clear all
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("");
            return;
        }

        // Equals calculation
        if (buttonText.equals("=")) {
            // Calculate and display the result
            String finalResult = calculate(dataTocalculate);
            resultTv.setText(finalResult);
            return;
        }

        // Clear last input
        if (buttonText.equals("C")) {
            if (!dataTocalculate.isEmpty()) {
                dataTocalculate = dataTocalculate.substring(0, dataTocalculate.length() - 1);
            }
        } else {
            // Allow decimal input
            if (buttonText.equals(".")) {
                // If input starts with a dot, treat it as "0."
                if (dataTocalculate.isEmpty() || dataTocalculate.equals("0")) {
                    dataTocalculate += "0."; // Prefix with "0."
                } else if (dataTocalculate.contains(".")) {
                    return; // Prevent adding multiple decimal points
                } else {
                    dataTocalculate += "."; // Simply add the decimal point
                }
            } else {
                dataTocalculate += buttonText; // Add the button text
            }
        }

        // Set up expression
        solutionTv.setText(dataTocalculate);
    }

    // Simple function to perform calculation
    private String calculate(String expression) {
        try {
            // Handle basic arithmetic operations
            char operator = ' ';
            double result = 0;

            // Split expression into numbers and operators
            String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");
            double num1 = Double.parseDouble(tokens[0]);

            for (int i = 1; i < tokens.length; i++) {
                operator = tokens[i].charAt(0);
                double num2 = Double.parseDouble(tokens[0]);

                switch (operator) {
                    case '+':
                        num1 += num2;
                        break;
                    case '-':
                        num1 -= num2;
                        break;
                    case '*':
                        num1 *= num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            num1 /= num2;
                        } else {
                            return "Error"; // Handle division by zero
                        }
                        break;
                    default:
                        return "Error"; // Handle invalid operator
                }
            }
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error"; // Handle invalid input or calculation error
        }
    }
}
