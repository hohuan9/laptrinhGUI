package laptrinhvuiv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LapTrinhVuiV implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton, sqrtButton;
    JPanel panel;

    Font myFont = new Font("Arial", Font.BOLD, 20);
    Color numberColor = new Color(240, 248, 255); // Light blue
    Color functionColor = new Color(255, 228, 225); // Light pink
    Color bgColor = new Color(250, 250, 250);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public LapTrinhVuiV() {
        frame = new JFrame("Máy Tính Siêu Đơn Giản Của Hồ Huân :D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(bgColor);

        // Textfield
        textfield = new JTextField();
        textfield.setBounds(50, 25, 380, 70);
        textfield.setFont(new Font("Arial", Font.PLAIN, 30));
        textfield.setEditable(false);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setBackground(Color.WHITE);
        textfield.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("±");
        sqrtButton = new JButton("√");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = sqrtButton;

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(myFont);
            button.setFocusable(false);
            button.setBackground(functionColor);
            button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(numberColor);
            numberButtons[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
        }

        delButton.setBounds(50, 470, 100, 50);
        clrButton.setBounds(190, 470, 100, 50);
        negButton.setBounds(330, 470, 100, 50);

        // Panel 5x4 layout
        panel = new JPanel();
        panel.setBounds(50, 120, 380, 330);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // Hàng 1: 7 8 9 ÷
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);

        // Hàng 2: 4 5 6 ×
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);

        // Hàng 3: 1 2 3 -
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(subButton);

        // Hàng 4: . 0 = +
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(addButton);
        panel.add(equButton);

        // Hàng 5: √ ± Del Clr
        panel.add(sqrtButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(clrButton);

        frame.add(panel);
        frame.add(textfield);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText().concat("."));
            }
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textfield.setText("Err");
                        return;
                    }
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            if (!string.isEmpty()) {
                textfield.setText(string.substring(0, string.length() - 1));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
        if (e.getSource() == sqrtButton) {
            double temp = Double.parseDouble(textfield.getText());
            if (temp >= 0) {
                temp = Math.sqrt(temp);
                textfield.setText(String.valueOf(temp));
            } else {
                textfield.setText("Err");
            }
        }
    }

    public static void main(String[] args) {
        new LapTrinhVuiV();
    }
}
