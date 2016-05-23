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
 * Display manage student gui
 */
class StudentGUI extends JFrame {
    private JFrame frameStd;
    private MenuBar menubar = new MenuBar();
    private JButton btnStdCreate, btnStdView, btnStdEdit, btnStdDelete, btnStdBack;
    private String infoOnComponent = "";
    private static  ArrayList<Student> studentList = new ArrayList<>();
    private JTextArea textArea1;

    /**
     * Constructor to display the GUI
     */
    StudentGUI(){
        displayStudentFrame();
    }

    /**
     * Attribute of Student GUI
     */
    private void displayStudentFrame(){
        frameStd = new JFrame("Student Assessment Recording Application");
        frameStd.setSize(1000,700);
        //Create menu bar
        menubar.displayMenuBar(frameStd);
        frameStd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameStd.setLayout(new GridLayout(1,2,0,0));
        frameStd.add(new studentPanel());
        frameStd.add(new stdRightPanel());
        displayStudentList(); //display student list in text area
        ListenForWindow lForWindow = new ListenForWindow();
        frameStd.addWindowListener(lForWindow);
        frameStd.setLocationRelativeTo(null);
        frameStd.setVisible(true);
    }

    /**
     * Display dialog that allow user to delete student
     */
    private void deleteStd() {
        //read student file
        readStudentFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Student aStudentList : studentList) {
            String name = aStudentList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();

        Object value = JOptionPane.showInputDialog(null,
                "Which student to delete?",
                "Delete Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < studentList.size(); i++) {
            if (i == index){

                JLabel labelStd = new JLabel(studentList.get(i).getName());
                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Do you want to delete this student?"),
                        labelStd
                };

                int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Delete",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (responses == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (responses == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    studentList.remove(i);
                    writeStudentFile();
                    infoOnComponent = "Student had been deleted. ";
                    JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
                    infoOnComponent = "";
                    displayStudentList();
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }
    }

    /**
     * Display dialog box that used to edit student information
     */
    private void editStd() {
        //read student file
        readStudentFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Student aStudentList : studentList) {
            String name = aStudentList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();

        Object value = JOptionPane.showInputDialog(null,
                "Which student to edit?",
                "Edit Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < studentList.size(); i++) {
            if (i == index){
                JLabel labelStd = new JLabel(studentList.get(i).getName());
                JTextField stdName = new JTextField();
                JTextField stdId = new JTextField();
                JTextField stdProgram = new JTextField();
                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Student you want to edit: "),
                        labelStd,
                        new JLabel("Enter New Student Name: "),
                        stdName,
                        new JLabel("Enter New Student ID: "),
                        stdId,
                        new JLabel("Enter New Student Program: "),
                        stdProgram
                };

                int responses = JOptionPane.showConfirmDialog(null, UserInput , "Edit Student",
                        JOptionPane.OK_CANCEL_OPTION);
                if (responses == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Cancel button clicked");
                } else if (responses == JOptionPane.OK_OPTION) {
                    System.out.println("Ok button clicked");

                    if(stdName.getText().equals("") && stdId.getText().equals("") && stdProgram.getText().equals("")){
                        infoOnComponent = "Incomplete student info.\n";
                        infoOnComponent += "Please re-enter student info.";
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.WARNING_MESSAGE);
                        infoOnComponent = "";
                    }
                    else{
                        String inputName = stdName.getText();
                        String inputId = stdId.getText();
                        String inputProgram = stdProgram.getText();

                        studentList.get(i).setName(inputName);
                        studentList.get(i).setId(inputId);
                        studentList.get(i).setProgram(inputProgram);

                        //store arraylist in txt file
                        writeStudentFile();

                        infoOnComponent = "Student had been edited. \n" +
                                stdName.getText() + ", " +
                                stdId.getText() + ", " +
                                stdProgram.getText();
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
                        infoOnComponent = "";
                        displayStudentList();
                    }
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }
    }

    /**
     * Display dialog box to allow view of each student
     */
    private void viewStd() {
        //read student file
        readStudentFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Student aStudentList : studentList) {
            String name = aStudentList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();

        Object value = JOptionPane.showInputDialog(null,
                "Which student to view?",
                "View Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < studentList.size(); i++) {
            if (i == index){
                String stdName = studentList.get(i).getName();
                String stdId = studentList.get(i).getId();
                String stdProgram = studentList.get(i).getProgram();

                infoOnComponent = "Student Name: " + stdName + "\n"
                        + "Student ID: " + stdId + "\n"
                        + "Student Program: " + stdProgram;
                JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
            }
        }
    }

    /**
     * Display student list to text area
     */
    private void displayStudentList() {
        textArea1.setText("Student Lists: \n" );

        //read student file
        readStudentFile();

        //loop for student list
        for (int i = 0; i < studentList.size(); i++) {
            textArea1.append(i + "." + studentList.get(i).getName() + "\n");
        }
    }

    /**
     * Display dialog box to create student
     */
    private void createStd() {
        JTextField stdName = new JTextField();
        JTextField stdId = new JTextField();
        JTextField stdProgram = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Enter Student Name: "),
                stdName,
                new JLabel("Enter Student ID: "),
                stdId,
                new JLabel("Enter Student Program: "),
                stdProgram
        };

        int response = JOptionPane.showConfirmDialog(null, inputs , "Create Student",
                JOptionPane.OK_CANCEL_OPTION);
        if (response == JOptionPane.CANCEL_OPTION) {
            System.out.println("Cancel button clicked");
        } else if (response == JOptionPane.OK_OPTION) {
            System.out.println("Ok button clicked");

            if(stdName.getText().equals("") && stdId.getText().equals("") && stdProgram.getText().equals("")){
                infoOnComponent = "Incomplete student info.\n";
                infoOnComponent += "Please re-enter student info.";
                JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.ERROR_MESSAGE);
                infoOnComponent = "";
            }
            else{
                String inputName = stdName.getText();
                String inputId = stdId.getText();
                String inputProgram = stdProgram.getText();

                //create student, add to arraylist
                Student newStudent = new Student(inputName, inputId, inputProgram);
                studentList.add(newStudent);

                //store arraylist in txt file
                writeStudentFile();

                infoOnComponent = "You entered " +
                        stdName.getText() + ", " +
                        stdId.getText() + ", " +
                        stdProgram.getText();
                JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
                displayStudentList();
            }
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("JOptionPane closed");
        }
    }

    /**
     * Display student menu
     */
    private class studentPanel extends JPanel{
        public studentPanel() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            //create random label just for show
            JLabel label1 = new JLabel("Student Menu");
            Font myFont = new Font("Serif", Font.BOLD, 20);
            label1.setFont(myFont);
            this.add(label1, gbc);
            gbc.gridy++;

            //create a button
            btnStdCreate = new JButton("Create Student");
            btnStdView   = new JButton("View Student");
            btnStdEdit   = new JButton("Edit Student");
            btnStdDelete = new JButton("Delete Student");
            btnStdBack = new JButton("Back to Home");
            btnStdBack.setToolTipText("return to home");

            //Create a listener for button and add button to it
            ListenForButton lForButton = new ListenForButton();
            btnStdCreate.addActionListener(lForButton);
            btnStdView.addActionListener(lForButton);
            btnStdEdit.addActionListener(lForButton);
            btnStdDelete.addActionListener(lForButton);
            btnStdBack.addActionListener(lForButton);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30,10,0,0);
            gbc.ipady = 40;
            gbc.ipadx = 100;
            this.add(btnStdCreate,gbc);
            gbc.gridy++;
            this.add(btnStdView,gbc);
            gbc.gridy++;
            this.add(btnStdEdit,gbc);
            gbc.gridy++;
            this.add(btnStdDelete,gbc);
            gbc.gridy++;
            this.add(btnStdBack,gbc);
        }
    }

    /**
     * Read student file
     */
    private void readStudentFile(){
        File studentFile = new File("student.txt");
        if (studentFile.length() == 0) {
            System.out.println("File is empty.");
        } else {
            try {
                FileInputStream fis = new FileInputStream("student.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                studentList = (ArrayList) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write student file
     */
    private void writeStudentFile(){
        try {
            FileOutputStream fos = new FileOutputStream("student.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(studentList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Button listener
     */
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnStdCreate){
                System.out.println("Student Create clicked");
                createStd();
            }
            else if(e.getSource() == btnStdView){
                System.out.println("View student clicked");
                viewStd();
            }
            else if(e.getSource() == btnStdEdit){
                System.out.println("Edit student clicked");
                editStd();
            }
            else if(e.getSource() == btnStdDelete){
                System.out.println("Delete student clicked");
                deleteStd();
            }
            else if(e.getSource() == btnStdBack){
                System.out.println("Back button clicked");
                frameStd.setVisible(false);
                new MainGui();
            }
        }
    }

    /**
     * Display right panel
     */
    private class stdRightPanel extends JPanel {

        stdRightPanel(){
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
}
