
/**
 * Created by johnclayton on 07/10/2017.
 * Program will calculate the distance to a target using specified values
 * entered by a user via a GUI interface
 *
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
//    private static JLabel exampleLabel;

    //Radio Buttons
    private static JRadioButton iterativeRadButt;
    private static JRadioButton recursiveRadButt;

    //Button to perform the distance calculation
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

        //Creation of a JPanel
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


        //Creating a size text/label field for user to input their values
        enterNLabel = new JLabel("Enter n: ");
        textPanel.add(enterNLabel);
        enterNText = new JTextField(5);
        textPanel.add(enterNText);
        enterNText.setEditable(true);
        //User can hover their mouse over this field to get an explanation of what data needs to be entered
        enterNText.setToolTipText("Enter the number of iterations you would like to compute.");

        //Creating a mil Text/Label field for user to input their values
        resultsLabel = new JLabel("Results:  ");
        textPanel.add(resultsLabel);
        resultsText = new JTextField(5);
        textPanel.add(resultsText);
        resultsText.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        resultsText.setForeground(Color.RED);
        resultsText.setEditable(false);


        //Creating a results Text/Label to display the calculated distance information
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


        //Yards Radio Button
        iterativeRadButt = new JRadioButton("Iterative");
        iterativeRadButt.setText("Iterative");
        radButtonGrp.add(iterativeRadButt);
        panel2.add(iterativeRadButt);
        iterativeRadButt.setSelected(true);


        //Meters Radio Button
        recursiveRadButt = new JRadioButton("Recursive");
        recursiveRadButt.setText("Recurvise");
        radButtonGrp.add(recursiveRadButt);
        panel2.add(recursiveRadButt);
        recursiveRadButt.setSelected(false);


        button.addActionListener(this);

        //create an object to the inner class WindowAdapterImplementation
        WindowAdapterImplementations windowListener = new WindowAdapterImplementations();

        //invoke the add
        addWindowListener(windowListener);

    }

//Writes the data into the .csv file

    class WindowAdapterImplementations extends WindowAdapter {

        public void windowClosing(WindowEvent e) {

            try {

                FileWriter dataOutput = new FileWriter("outData.txt");

                //prepare header

                dataOutput.append("n");
                // comma inserted to help the excel program to add a column
                dataOutput.append(',');

                dataOutput.append("Recursive");
                // comma inserted to help the excel program to add a column
                dataOutput.append(',');

                dataOutput.append("Iterative");
                // comma inserted to help the excel program to add a column
                dataOutput.append(',');

                dataOutput.append('\n');

                for (int i = 0; i <= 10; i++) {

                    dataOutput.append(String.valueOf(i));
                    dataOutput.append(',');

                    Sequence.computeRecursive(i);
                    dataOutput.append(String.valueOf(Sequence.getEfficiency()));

                    dataOutput.append(',');
                    Sequence.computeIterative(i);

                    dataOutput.append(String.valueOf(Sequence.getEfficiency()));
                    dataOutput.append('\n');

                }

                dataOutput.flush();
                dataOutput.close();

            } catch (Exception ae){

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

        int IterationCount = 0, RecursiveCount = 0;

        if (button == ae.getSource()) {

            if (iterativeRadButt.isSelected()) {

                int itValue = Integer.parseInt(enterNText.getText());
                int iterationVal = Sequence.computeIterative(itValue);
                resultsText.setText(iterationVal + "");
                IterationCount = Sequence.getEfficiency();
                efficiencyText.setText(IterationCount + "");

            } else if (recursiveRadButt.isSelected()) {

                int recValue = Integer.parseInt(enterNText.getText());
                int recursiveVal = Sequence.computeRecursive(recValue);
                resultsText.setText(recursiveVal + "");
                RecursiveCount = Sequence.getEfficiency();
                efficiencyText.setText(RecursiveCount + "");

            }
        }
    }
} //End of Class




