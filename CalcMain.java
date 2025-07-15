import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CalcMain {
	private static String[] actions = new String[3];
	private static ArrayList<String> numbers = new ArrayList<String>();
	private static ArrayList<String> ops = new ArrayList<String>();
	private static JLabel label1 = new JLabel();
	private static JFrame calc = new JFrame();

	public static void main(String[] args) {
		ops.add("+");
		ops.add("-");
		ops.add("*");
		ops.add("/");
		numbers.add("0");
		numbers.add("1");
		numbers.add("2");
		numbers.add("3");
		numbers.add("4");
		numbers.add("5");
		numbers.add("6");
		numbers.add("7");
		numbers.add("8");
		numbers.add("9");
		// init frame
		// storing actions
		// init bounds
		//calc.setLayout(new BorderLayout());
		calc.setSize(400, 400);
		calc.setVisible(true);
		label1.setText("0");
		Dimension size = label1.getPreferredSize();
		label1.setFont(new Font("Arial", 0, 30));
		label1.setBounds(150, 10, 500, 50);
		calc.add(label1);
		label1.setLocation(0, 0);
		// init buttons
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String action = event.getActionCommand();
				operation(action, label1);
				equal(action, label1);
				floatNumber(action, label1);
				number(action, label1);
				calc.add(label1);
			}
		};

		// init bounds like a 2d array
		JButton[][] calcLayout = new JButton[4][4];
		// this will set the bounds of the buttons
		// Col
		for (int i = 0; i < 4; i++) {
			// row
			for (int o = 0; o < 4; o++) {
				if (i == 0 && o == 0) {
					calcLayout[o][i] = new JButton("7");
				} else if (i == 0 && o == 1) {
					calcLayout[o][i] = new JButton("4");
				} else if (i == 0 && o == 2) {
					calcLayout[o][i] = new JButton("1");
				} else if (i == 0 && o == 3) {
					calcLayout[o][i] = new JButton("0");
				} else if (i == 1 && o == 0) {
					calcLayout[o][i] = new JButton("8");
				} else if (i == 1 && o == 1) {
					calcLayout[o][i] = new JButton("5");
				} else if (i == 1 && o == 2) {
					calcLayout[o][i] = new JButton("2");
				} else if (i == 1 && o == 3) {
					calcLayout[o][i] = new JButton(".");
				} else if (i == 2 && o == 0) {
					calcLayout[o][i] = new JButton("9");
				} else if (i == 2 && o == 1) {
					calcLayout[o][i] = new JButton("6");
				} else if (i == 2 && o == 2) {
					calcLayout[o][i] = new JButton("3");
				} else if (i == 2 && o == 3) {
					calcLayout[o][i] = new JButton("=");
				} else if (i == 3 && o == 0) {
					calcLayout[o][i] = new JButton("/");
				} else if (i == 3 && o == 1) {
					calcLayout[o][i] = new JButton("*");
				} else if (i == 3 && o == 2) {
					calcLayout[o][i] = new JButton("-");
				} else if (i == 3 && o == 3) {
					calcLayout[o][i] = new JButton("+");
				}
				calcLayout[o][i].setBounds(i * 60 + 60, o * 60 + 60, 50, 50);
				calc.add(calcLayout[o][i]);
				calcLayout[o][i].addActionListener(actionListener);
			}
		}
		//calc.add(label1);
	}

	public static void operation(String action, JLabel label1) {
		if (actions[0] == null || actions[2] == null) {
			if (ops.contains(action)) {
				if (actions[0] == null) {
					actions[0] = "0";
				}
				if (actions[2] == null && actions[1] == null) {
					actions[1] = action;
					label1.setText(action);
					System.out.println(action);
				}
			}
		}
	}

	public static void equal(String action, JLabel label1) {
		if (action.equals("=")) {
			if (numbers.contains(actions[0].substring(0, 1)) && ops.contains(actions[1])
						&& numbers.contains(actions[2].substring(0, 1))) {
				// go through each and find what we need
				if (ops.contains(actions[1])) {
					if (actions[1] == "+") {
						float ttl = Float.parseFloat(actions[0]) + Float.parseFloat(actions[2]);
						System.out.println(ttl);
						actions[0] = Float.toString(ttl);
						label1.setText(Float.toString(ttl));
					}
					if (actions[1] == "-") {
						float ttl = Float.parseFloat(actions[0]) - Float.parseFloat(actions[2]);
						System.out.println(ttl);
						actions[0] = Float.toString(ttl);
						label1.setText(Float.toString(ttl));
					}
					if (actions[1] == "*") {
						float ttl = Float.parseFloat(actions[0]) * Float.parseFloat(actions[2]);
						System.out.println(ttl);
						actions[0] = Float.toString(ttl);
						label1.setText(Float.toString(ttl));
					}
					if (actions[1] == "/") {
						float ttl = Float.parseFloat(actions[0]) / Float.parseFloat(actions[2]);
						System.out.println(ttl);
						actions[0] = Float.toString(ttl);
						label1.setText(Float.toString(ttl));
					}
					actions[1] = null;
					actions[2] = null;
				}
			}
		}
	}

	public static void floatNumber(String action, JLabel label1) {
		if (action.equals(".")) {
			if (actions[0] == null) {
				actions[0] = "0";
				actions[0] += ".";
				System.out.println(actions[0]);
				label1.setText(actions[0]);
			} else if (actions[2] == null) {
				if (actions[1] == null) {
					if (!(actions[0].contains("."))) {
						actions[0] += ".";
						System.out.println(actions[0]);
						label1.setText(actions[0]);
					}
				} else {
					actions[2] = "0";
					actions[2] += ".";
					System.out.println(actions[2]);
					label1.setText(actions[2]);
				}
			} else if (!actions[2].contains(".")) {
				actions[2] += ".";
				System.out.println(actions[2]);
				label1.setText(actions[2]);
			}
		}
	}

	public static void number(String action, JLabel label1) {
		if(numbers.contains(action)) {
			if (actions[1] == null) {
				if (actions[0] == null) {
					actions[0] = "";
				}
				actions[0] += action;
				System.out.println(actions[0]);
				label1.setText(actions[0]);
			} else {
				if (actions[2] == null) {
					actions[2] = "";
				}
				actions[2] += action;
				System.out.println(actions[2]);
				label1.setText(actions[2]);
			}
		}
	}
}



