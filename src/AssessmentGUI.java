import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/9/2016.
 *
 * Display assessment GUI
 */
class AssessmentGUI extends JFrame{
    private MenuBar menubar = new MenuBar();
    private JButton btnAssCreate, btnAssView, btnAssEdit, btnAssDelete, btnAssBack;
    private JFrame frameAss = new JFrame();
    private JTextArea textArea1;
    private String infoOnComponent = "";
    private static ArrayList<Assessment> assessmentList = new ArrayList<>();

    /**
     * Constructor for AssessmentGUI
     */
    AssessmentGUI(){
        displayAssFrame();
    }

    /**
     * To display assessment frame
     */
    private void displayAssFrame(){
        frameAss.setTitle("Student Assessment Recording Application");
        frameAss.setSize(1000,700);
        //Create menu bar
        menubar.displayMenuBar(frameAss);
        frameAss.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameAss.setLayout(new GridLayout(1,2,0,0));
        frameAss.add(new AssessmentPanel());
        frameAss.add(new AssRightPanel());
        displayAssList(); //display unit list in text area
        ListenForWindow lForWindow = new ListenForWindow();
        frameAss.addWindowListener(lForWindow);
        frameAss.setLocationRelativeTo(null);
        frameAss.setVisible(true);
    }

    /**
     * To display assessment list
     */
    private void displayAssList() {
        textArea1.setText("Assessment Lists: \n" );

        //read class file
        readAssessmentFile();

        //loop for class list
        for (int i = 0; i < assessmentList.size(); i++) {
            textArea1.append(i + "." + assessmentList.get(i).getName() + "\n");
        }
    }

    /**
     * Read assessment file
     */
    private void readAssessmentFile() {
        File unitFile = new File("assessment.txt");
        if (unitFile.length() == 0) {
            System.out.println("File is empty.");
        } else {
            try {
                FileInputStream fis4 = new FileInputStream("assessment.txt");
                ObjectInputStream ois4 = new ObjectInputStream(fis4);
                assessmentList = (ArrayList) ois4.readObject();
                ois4.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write assessment file
     */
    private static void writeAssessmentFile(){
        try {
            FileOutputStream fos4 = new FileOutputStream("assessment.txt");
            ObjectOutputStream oos4 = new ObjectOutputStream(fos4);
            oos4.writeObject(assessmentList);
            oos4.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The right panel of Assessment frame
     */
    private class AssRightPanel extends JPanel {
        AssRightPanel() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel label1 = new JLabel("Information: ");
            this.add(label1,gbc);
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10,0,0,0); //padding

            //create a textArea
            textArea1 = new JTextArea(30, 40);
            textArea1.setLineWrap(true); //end then wrap to next line
            textArea1.setWrapStyleWord(true); //not split words
            textArea1.setEditable(false);
            JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.add(scrollbar1,gbc);
        }
    }

    /**
     * Left panel of assessment frame
     */
    private class AssessmentPanel extends JPanel {

        public AssessmentPanel(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            //create random label just for show
            JLabel label1 = new JLabel("Assessment Menu");
            Font myFont = new Font("Serif", Font.BOLD, 20);
            label1.setFont(myFont);
            this.add(label1, gbc);
            gbc.gridy++;

            //create a button
            btnAssCreate = new JButton("Create Assessment");
            btnAssView   = new JButton("View Assessment");
            btnAssEdit   = new JButton("Edit Assessment");
            btnAssDelete = new JButton("Delete Assessment");
            btnAssBack = new JButton("Back to Home");
            btnAssBack.setToolTipText("return to home");

            //Create a listener for button and add button to it
            ListenForButton lForButton = new ListenForButton();
            btnAssCreate.addActionListener(lForButton);
            btnAssView.addActionListener(lForButton);
            btnAssEdit.addActionListener(lForButton);
            btnAssDelete.addActionListener(lForButton);
            btnAssBack.addActionListener(lForButton);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30,10,0,0);
            gbc.ipady = 40;
            gbc.ipadx = 100;
            this.add(btnAssCreate,gbc);
            gbc.gridy++;
            this.add(btnAssView,gbc);
            gbc.gridy++;
            this.add(btnAssEdit,gbc);
            gbc.gridy++;
            this.add(btnAssDelete,gbc);
            gbc.gridy++;
            this.add(btnAssBack,gbc);
        }
    }

    /**
     * button listener
     */
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAssBack){
                System.out.println("Back button clicked");
                frameAss.setVisible(false);
                new MainGui();
            }
            else if(e.getSource() == btnAssCreate ){
                System.out.println("Create assessment clicked.");
                createAssessment();
            }
            else if(e.getSource() == btnAssView ){
                System.out.println("View assessment clicked.");
                viewAssessment();
            }
            else if(e.getSource() == btnAssEdit ){
                System.out.println("Edit assessment clicked.");
                editAssessment();
            }
            else if(e.getSource() == btnAssDelete ){
                System.out.println("Delete assessment clicked.");
                deleteAssessment();
            }
        }
    }

    /**
     * Delete Assessment
     */
    private void deleteAssessment() {
        //read assessment file
        readAssessmentFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Assessment aAssessmentList : assessmentList) {
            String name = aAssessmentList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();

        Object value = JOptionPane.showInputDialog(null,
                "Which assessment you want to delete?",
                "Delete Assessment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < assessmentList.size(); i++) {
            if (i == index){

                JLabel labelAss = new JLabel(assessmentList.get(i).getName());
                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Do you want to delete this assessment?"),
                        labelAss
                };

                int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Delete",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (responses == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (responses == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    assessmentList.remove(i);
                    writeAssessmentFile();
                    infoOnComponent = "Assessment had been deleted. ";
                    JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Information", JOptionPane.INFORMATION_MESSAGE);
                    infoOnComponent = "";
                    displayAssList();
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }
    }

    /**
     * Edit Assessment Details
     */
    private void editAssessment() {
        //read assessment file
        readAssessmentFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Assessment aAssessmentList : assessmentList) {
            String name = aAssessmentList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();

        Object value = JOptionPane.showInputDialog(null,
                "Which assessment you want to edit?",
                "Edit Assessment Details",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < assessmentList.size(); i++) {
            if (i == index){
                JLabel labelAss = new JLabel(assessmentList.get(i).getName());
                JTextField assName = new JTextField();
                JTextField assWeight = new JTextField();

                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Assessment you want to edit: "),
                        labelAss,
                        new JLabel("Enter New Assessment Name: "),
                        assName,
                        new JLabel("Enter New Assessment ID: "),
                        assWeight
                };

                int responses = JOptionPane.showConfirmDialog(null, UserInput , "Edit Assessment",
                        JOptionPane.OK_CANCEL_OPTION);
                if (responses == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Cancel button clicked");
                } else if (responses == JOptionPane.OK_OPTION) {
                    System.out.println("Ok button clicked");

                    if(assName.getText().equals("") && assWeight.getText().equals("") ){
                        infoOnComponent = "Incomplete assessment info.\n";
                        infoOnComponent += "Please re-enter assessment info.";
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Information", JOptionPane.WARNING_MESSAGE);
                        infoOnComponent = "";
                    }
                    else{
                        String inputName = assName.getText();
                        String inputWeight = assWeight.getText();

                        assessmentList.get(i).setName(inputName);
                        assessmentList.get(i).setWeight(inputWeight);

                        //store arraylist in txt file
                        writeAssessmentFile();

                        infoOnComponent = "Assessment had been edited. \n" +
                                assName.getText() + ", " +
                                assWeight.getText();
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Information", JOptionPane.INFORMATION_MESSAGE);
                        infoOnComponent = "";
                        displayAssList();
                    }
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }
    }

    /**
     * View an assessment details
     */
    private void viewAssessment() {
        //read assessment file
        readAssessmentFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Assessment aAssessmentList : assessmentList) {
            String name = aAssessmentList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();

        Object value = JOptionPane.showInputDialog(null,
                "Which assessment you want to view?",
                "View Assessment Details",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < assessmentList.size(); i++) {
            if (i == index){
                String assName = assessmentList.get(i).getName();
                String assWeight = assessmentList.get(i).getWeight();

                infoOnComponent = "Assessment Name: " + assName + "\n"
                        + "Assessment Weight: " + assWeight;
                JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
            }
        }
    }

    /**
     * Create assessment
     */
    private void createAssessment() {
        JTextField assName = new JTextField();
        JTextField assWeight = new JTextField();

        final JComponent[] inputs = new JComponent[] {
                new JLabel("Enter Assessment Name: "),
                assName,
                new JLabel("Enter Assessment Weight: "),
                assWeight
        };

        int response = JOptionPane.showConfirmDialog(null, inputs , "Create Assessment",
                JOptionPane.OK_CANCEL_OPTION);
        if (response == JOptionPane.CANCEL_OPTION) {
            System.out.println("Cancel button clicked");
        } else if (response == JOptionPane.OK_OPTION) {
            System.out.println("Ok button clicked");

            if(assName.getText().equals("") && assWeight.getText().equals("") ){
                infoOnComponent = "Incomplete assessment info.\n";
                infoOnComponent += "Please re-enter assessment info.";
                JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Information", JOptionPane.ERROR_MESSAGE);
                infoOnComponent = "";
            }
            else{
                String inputName = assName.getText();
                String inputWeight = assWeight.getText();

                //create assessment, add to arraylist
                Assessment newAssessment = new Assessment(inputName, inputWeight);
                assessmentList.add(newAssessment);

                //store arraylist in txt file
                writeAssessmentFile();

                infoOnComponent = "You entered " +
                        assName.getText() + ", " +
                        assWeight.getText();
                JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
                displayAssList();
            }
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("JOptionPane closed");
        }
    }
}
