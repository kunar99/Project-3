
/**
 * Created by johnclayton on 07/22/2017.
 * Program will calculate the sequence of numbers (Recursive or Iterative)
 * from a value entered by the user. The program will calculate the
 * value entered a total of 11 times and export the values for both Recursive method
 * and Iterative method to a text file.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Project3 extends JFrame implements ActionListener {

    //Creation of the text fields for input/output
    private static JTextField enterNText;
    public static JTextField resultsText;
    private static JTextField efficiencyText;

    //Creation of the label names
    private static JLabel enterNLabel;
    private static JLabel resultsLabel;
    private static JLabel efficiencyLabel;

    //Radio Buttons
    private static JRadioButton iterativeRadButt;
    private static JRadioButton recursiveRadButt;

    //Button to execute calculation
    private static JButton button;

    //Height and weight for the JFrame
    private static final int WIDTH = 500;
    private static final int HEIGHT = 250;


    //constructor
    public Project3() {
        super("Project 3");

        //Specifies the layout of the frame
        setFrame(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        //Creation of the JPanel
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.AFTER_LAST_LINE);


        //Creating two textPanels to be used
        JPanel textPanel = new JPanel();
        //JPanel textPanel2 = new JPanel();
        textPanel.setLayout(new GridLayout(4, 2));
        add(textPanel, BorderLayout.WEST);
        //add(textPanel2, BorderLayout.NORTH);

        //Creating button group for Radio Buttons
        ButtonGroup radButtonGrp = new ButtonGroup();


        //Creating a "Enter N" text/label field for user to input their value
        enterNLabel = new JLabel("Enter n: ");
        textPanel.add(enterNLabel);
        enterNText = new JTextField(5);
        textPanel.add(enterNText);
        enterNText.setEditable(true);
        //User can hover their mouse over this field to get an explanation of what data needs to be entered
        enterNText.setToolTipText("Enter the number of iterations you would like to compute.");

        //Creating a "Results" Text/Label to display the sum
        resultsLabel = new JLabel("Results:  ");
        textPanel.add(resultsLabel);
        resultsText = new JTextField(5);
        textPanel.add(resultsText);
        resultsText.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        resultsText.setForeground(Color.RED);
        resultsText.setEditable(false);


        //Creating a "Efficiency" Text/Label to display the efficiency
        efficiencyLabel = new JLabel("Efficiency: ");
        textPanel.add(efficiencyLabel);
        efficiencyText = new JTextField(5);
        textPanel.add(efficiencyText);
        efficiencyText.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        efficiencyText.setForeground(Color.RED);
        efficiencyText.setEditable(false);

        //Creating a JButton so user can perform the calculation
        button = new JButton("Compute");
        panel3.add(button);
        //User can hover their mouse over this field to get an explanation of what action needs to be performed
        button.setToolTipText("Click to perform your calculations");


        //Iterative Radio Button
        iterativeRadButt = new JRadioButton("Iterative");
        iterativeRadButt.setText("Iterative");
        radButtonGrp.add(iterativeRadButt);
        panel2.add(iterativeRadButt);
        iterativeRadButt.setSelected(true);


        //Recursive Radio Button
        recursiveRadButt = new JRadioButton("Recursive");
        recursiveRadButt.setText("Recursive");
        radButtonGrp.add(recursiveRadButt);
        panel2.add(recursiveRadButt);
        recursiveRadButt.setSelected(false);


        //Creating the action listener for the button
        button.addActionListener(this);

        //Creating an object of Window Adapter Implementation
        WindowAdapterImplementations windowListener = new WindowAdapterImplementations();

        //Initializing the Window Listener
        addWindowListener(windowListener);

    }

//Writes the data into the .csv file

    class WindowAdapterImplementations extends WindowAdapter {

        public void windowClosing(WindowEvent e) {

            try {

                FileWriter output = new FileWriter("Output.txt");

                output.append("n");
                output.append(',');  //Formatting using "," to load comma delimited in excel
                output.append("Recursive");
                output.append(',');
                output.append("Iterative");
                output.append(',' + "\n");

                //Counter to run the for statement from 0 to 10 iterations
                for (int i = 0; i <= 10; i++) {

                    output.append(String.valueOf(i));
                    output.append(',');
                    Sequence.computeRecursive(i);
                    output.append(String.valueOf(Sequence.getEfficiency())); //Pulling return value from Efficiency Method
                    output.append(',');
                    Sequence.computeIterative(i);
                    output.append(String.valueOf(Sequence.getEfficiency())); //Pulling return value from Efficiency Method
                    output.append('\n');
                }

                output.flush();  //Flushes the output stream and forces any buffered output bytes to be written out.
                output.close();  //Closing the stream

            } catch (Exception ae){  //Catch exception is added to handle any problems of closing the text file.

                System.err.println("Unable to write to the file" + ae.getMessage() + " ");
                System.exit(0);

            }
        }
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Displays the GUI
    private void display() {
        setVisible(true);
    }


    //Start of Main Method
    public static void main(String[] args) {
        Project3 calc = new Project3();
        calc.display();

    }
    public void actionPerformed(ActionEvent ae) {

        int iterativeCnt = 0; // Initializing variable's values to be used
        int recursiveCnt = 0;

        if (button == ae.getSource()) {

            if (iterativeRadButt.isSelected()) { //Performs calculations if Iterative Radio Button selected

                int itValue = Integer.parseInt(enterNText.getText());
                int iterationVal = Sequence.computeIterative(itValue);
                resultsText.setText(iterationVal + "");
                iterativeCnt = Sequence.getEfficiency();
                efficiencyText.setText(iterativeCnt + "");

            } else if (recursiveRadButt.isSelected()) {  //Performs calculations if Recursive Radio Button selected

                int recValue = Integer.parseInt(enterNText.getText());
                int recursiveVal = Sequence.computeRecursive(recValue);
                resultsText.setText(recursiveVal + "");
                recursiveCnt = Sequence.getEfficiency();
                efficiencyText.setText(recursiveCnt + "");

            }
        }
    }
} //End of Class




