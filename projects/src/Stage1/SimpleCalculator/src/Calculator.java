
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by zhoujihao on 15-7-11.
 */
public class Calculator extends JFrame {
    private JTextField operand1;
    private JTextField operand2;
    private JLabel operation;
    private JLabel equal;
    private JLabel outcome;
    private JButton plus;
    private JButton minus;
    private JButton multi;
    private JButton div;
    private JButton ok;

    Calculator() {
        new JFrame();
        Container c = getContentPane();
        // set frame
        setTitle("SimpleCalculator");
        //setSize(400, 100);
        setBounds(400,200,400,100);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add widgets
        c.setLayout(new GridLayout(2, 6, 5, 10));

        operand1 = new JTextField("12");
        operation = new JLabel("");
        operand2 = new JTextField("2");
        equal = new JLabel("=");
        outcome = new JLabel("");

        operand1.setHorizontalAlignment(SwingConstants.CENTER);
        operation.setHorizontalAlignment(SwingConstants.CENTER);
        operand2.setHorizontalAlignment(SwingConstants.CENTER);
        equal.setHorizontalAlignment(SwingConstants.CENTER);
        outcome.setHorizontalAlignment(SwingConstants.CENTER);

        operand1.setBorder(BorderFactory.createEtchedBorder());
        operation.setBorder(BorderFactory.createEtchedBorder());
        operand2.setBorder(BorderFactory.createEtchedBorder());
        equal.setBorder(BorderFactory.createEtchedBorder());
        outcome.setBorder(BorderFactory.createEtchedBorder());

        //c.add(new JLabel("Compute: "));
        c.add(operand1);
        c.add(operation);
        c.add(operand2);
        c.add(equal);
        c.add(outcome);

        plus = new JButton("+");
        minus = new JButton("-");
        multi = new JButton("*");
        div = new JButton("/");
        ok = new JButton("OK");
        //c.add(new JLabel("Operations: "));
        c.add(plus);
        c.add(minus);
        c.add(multi);
        c.add(div);
        c.add(ok);

        //pack();
        setResizable(false);

        setVisible(true);

        guiEvent();
    }

    public void guiEvent() {
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Plus Button clicked");
                operation.setText("+");
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Plus Button clicked");
                operation.setText("-");
            }
        });

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Plus Button clicked");
                operation.setText("*");
            }
        });

        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                operation.setText("/");
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!(outcome.getText().equals(""))) {
                    //operand1.setText("");
                    //operand2.setText("");
                    //operation.setText("");
                    outcome.setText("");
                    ok.setText("OK");
                } else {
                    String as = operand1.getText();
                    String bs = operand2.getText();
                    String op = operation.getText();

                    if (as.equals("") || bs.equals("")) {
                        outcome.setText("Error!");
                        ok.setText("Clear");
                        return;
                    }

                    char[] carr;
                    carr = as.toCharArray();
                    for (int i = 0; i < as.length(); i++) {
                        if ((carr[i] < '0' || carr[i] > '9') && carr[i] != '.') {
                            outcome.setText("Error!");
                            ok.setText("Clear");
                            return;
                        }
                    }
                    carr = bs.toCharArray();
                    for (int i = 0; i < bs.length(); i++) {
                        if ((carr[i] < '0' || carr[i] > '9') && carr[i] != '.') {
                            outcome.setText("Error!");
                            ok.setText("Clear");
                            return;
                        }
                    }

                    double ai = Double.parseDouble(as);
                    double bi = Double.parseDouble(bs);

                    if (op.equals("")) {
                        operation.setText("?");
                        outcome.setText("?");
                        ok.setText("Clear");
                    }
                    if (op.equals("+")) {
                        double c = ai + bi;
                        String s = String.valueOf(c);
                        outcome.setText(s);
                        ok.setText("Clear");
                    }
                    if (op.equals("-")) {
                        double c = ai - bi;
                        String s = String.valueOf(c);
                        outcome.setText(s);
                        ok.setText("Clear");
                    }
                    if (op.equals("*")) {
                        double c = ai * bi;
                        String s = String.valueOf(c);
                        outcome.setText(s);
                        ok.setText("Clear");
                    }
                    if (op.equals("/")) {
                        if (bi == 0) {
                            outcome.setText("Error!");
                            ok.setText("Clear");
                        } else {
                            double c = ai / bi;
                            String s = String.valueOf(c);
                            outcome.setText(s);
                            ok.setText("Clear");
                        }
                    }
                }
            }
        });
    }

}
