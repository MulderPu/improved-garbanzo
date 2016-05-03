import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/1/2016.
 */
public class Gui extends JFrame implements ActionListener{
    JFrame frameHome, frameStd, frameUnit, frameClass, frameAssessment;
    JButton btnManageStudent, btnManageUnit ,btnManageClass, btnManageAssessment, btnManageExit;
    JButton btnStdCreate, btnStdView, btnStdEdit, btnStdDelete, btnStdBack;
    JButton btnUnitCreate, btnUnitView, btnUnitEdit, btnUnitDelete, btnUnitBack;
    JTextArea textArea1;
    String infoOnComponent = "";
    JMenuItem menu1_Item1;

    static  ArrayList<Student> studentList = new ArrayList<>();
    static  ArrayList<Unit> unitList = new ArrayList<>();
    static  ArrayList<Class> classList = new ArrayList<>();
    static  ArrayList<Assessment> assessmentList = new ArrayList<>();
    static  ArrayList<Assessment> assessmentList2 = new ArrayList<>();
    static ArrayList<Submission> submissionsList = new ArrayList<>();

    public Gui(){
        displayFrameHome();
    }

    /**
     * Display home frame
     */
    public void displayFrameHome(){
        frameHome = new JFrame("Student Assessment Recording Application");
        frameHome.setSize(1000,700);
        frameHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Create menu bar
        createMenuBar(frameHome);
        frameHome.setLayout(new GridLayout(1,2,0,0));
        frameHome.add(new leftPanel());
        frameHome.add(new rightPanel());
        frameHome.setLocationRelativeTo(null);
        ListenForWindow lForWindow = new ListenForWindow();
        frameHome.addWindowListener(lForWindow);
        frameHome.setVisible(true);
    }

    /**
     * Display left panel for home frame
     */
    private class leftPanel extends JPanel{

        public leftPanel() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            //create random label just for show
            JLabel label1 = new JLabel("Welcome to the system! \n");
            Font myFont = new Font("Serif", Font.BOLD, 20);
            label1.setFont(myFont);
            this.add(label1, gbc);
            gbc.gridy++;
            JLabel label2 = new JLabel("Home Menu \n");
            label2.setFont(myFont);
            this.add(label2, gbc);
            gbc.gridy++;

            //create a button
            btnManageStudent    = new JButton("Manage Student");
            btnManageUnit       = new JButton("Manage Unit");
            btnManageClass      = new JButton("Manage Class");
            btnManageAssessment = new JButton("Manage Assessment");
            btnManageExit       = new JButton("Exit Application");
            btnManageExit.setToolTipText("Exit");

            //Create a listener for button and add button to it
            ListenForButton lForButton = new ListenForButton();
            btnManageStudent.addActionListener(lForButton);
            btnManageUnit.addActionListener(lForButton);
            btnManageClass.addActionListener(lForButton);
            btnManageAssessment.addActionListener(lForButton);
            btnManageExit.addActionListener(lForButton);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30,10,0,0);
            gbc.ipady = 40;
            gbc.ipadx = 100;
            this.add(btnManageStudent,gbc);
            gbc.gridy++;
            this.add(btnManageUnit,gbc);
            gbc.gridy++;
            this.add(btnManageClass,gbc);
            gbc.gridy++;
            this.add(btnManageAssessment,gbc);
            gbc.gridy++;
            this.add(btnManageExit,gbc);
        }
    }

    /**
     * Display right panel for home frame
     */
    private class rightPanel extends JPanel{

        public rightPanel() {
            JLabel label1 = new JLabel("Information: ");
            this.add(label1);

            //create a textArea
            textArea1 = new JTextArea(35,40);
            textArea1.setLineWrap(true); //end then wrap to next line
            textArea1.setWrapStyleWord(true); //not split words
            textArea1.setEditable(false);
            JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.add(scrollbar1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    /**
     * Button listener, used to set listener to each of the button
     */
    private class ListenForButton extends JFrame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnManageStudent){
                System.out.println("Manage student clicked");
                frameHome.setVisible(false);
                frameStd = new JFrame("Student Assessment Recording Application");
                frameStd.setSize(1000,700);
                //Create menu bar
                createMenuBar(frameStd);
                frameStd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frameStd.setLayout(new GridLayout(1,2,0,0));
                frameStd.add(new studentPanel());
                frameStd.add(new rightPanel());
                displayStudentList(); //display student list in text area
                ListenForWindow lForWindow = new ListenForWindow();
                frameStd.addWindowListener(lForWindow);
                frameStd.setLocationRelativeTo(null);
                frameStd.setVisible(true);
            }
            else if(e.getSource() == btnManageUnit){
                System.out.println("Manage unit clicked");
                frameHome.setVisible(false);
                frameUnit = new JFrame("Student Assessment Recording Application");
                frameUnit.setSize(1000,700);
                //Create menu bar
                createMenuBar(frameUnit);
                frameUnit.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frameUnit.setLayout(new GridLayout(1,2,0,0));
                frameUnit.add(new unitPanel());
                frameUnit.add(new rightPanel());
                displayUnitList(); //display unit list in text area
                ListenForWindow lForWindow = new ListenForWindow();
                frameUnit.addWindowListener(lForWindow);
                frameUnit.setLocationRelativeTo(null);
                frameUnit.setVisible(true);
            }
            else if(e.getSource() == btnManageClass){
                System.out.println("Manage class clicked");
            }
            else if(e.getSource() == btnManageAssessment){
                System.out.println("Manage assessment clicked");
            }
            else if(e.getSource() == btnManageExit || e.getSource() == menu1_Item1){
                int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    System.exit(0);
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
            else if(e.getSource() == btnStdCreate){
                System.out.println("Student Create clicked");
                createStdDialogBox();
            }
            else if(e.getSource() == btnStdView){
                System.out.println("View student clicked");
                viewStdDialogBox();
            }
            else if(e.getSource() == btnStdEdit){
                System.out.println("Edit student clicked");
                editStdDialogBox();
            }
            else if(e.getSource() == btnStdDelete){
                System.out.println("Delete student clicked");
                deleteStdDialogBox();
            }
            else if(e.getSource() == btnStdBack){
                System.out.println("Back button clicked");
                frameStd.setVisible(false);
                displayFrameHome();
            }
            else if(e.getSource() == btnUnitCreate){
                System.out.println("Create unit clicked");
                createUnitDialogBox();
            }
            else if(e.getSource() == btnUnitView){
                System.out.println("View unit clicked");
                viewUnitDialogBox();
            }
            else if(e.getSource() == btnUnitEdit){
                System.out.println("Edit unit clicked");
                editUnitDialogBox();
            }
            else if(e.getSource() == btnUnitDelete){
                System.out.println("Delete unit clicked");
                deleteUnitDialogBox();
            }
            else if(e.getSource() == btnUnitBack){
                System.out.println("Back button clicked");
                frameUnit.setVisible(false);
                displayFrameHome();
            }
        }

        private void deleteUnitDialogBox() {
            //read unit file
            readUnitFile();

            ArrayList<String> tempList = new ArrayList<>();
            for (Unit anUnitList : unitList) {
                String name = anUnitList.getName();
                tempList.add(name);
            }

            Object[] options = tempList.toArray();
            Object value = JOptionPane.showInputDialog(null,
                    "Which unit to delete?",
                    "Delete Unit",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            int index = tempList.indexOf(value);
            System.out.println(index);

            for (int i = 0; i < unitList.size(); i++) {
                if (i == index){

                    JLabel labelUnit = new JLabel(unitList.get(i).getName());
                    final JComponent[] UserInput = new JComponent[] {
                            new JLabel("Do you want to delete this unit?"),
                            labelUnit
                    };

                    int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Delete",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (responses == JOptionPane.NO_OPTION) {
                        System.out.println("No button clicked");
                    } else if (responses == JOptionPane.YES_OPTION) {
                        System.out.println("Yes button clicked");
                        unitList.remove(i);
                        writeUnitFile();
                        infoOnComponent = "Unit had been deleted. ";
                        JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
                        infoOnComponent = "";
                        displayUnitList();
                    } else if (responses == JOptionPane.CLOSED_OPTION) {
                        System.out.println("JOptionPane closed");
                    }
                }
            }
        }

        /**
         * Display dialog box to allow edit unit details
         */
        private void editUnitDialogBox() {
            //read unit file
            readUnitFile();
            ArrayList<String> tempList = new ArrayList<>();
            for (Unit anUnitList : unitList) {
                String name = anUnitList.getName();
                tempList.add(name);
            }

            Object[] options = tempList.toArray();
            Object value = JOptionPane.showInputDialog(null,
                    "Which unit to edit?",
                    "Edit Unit",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            int index = tempList.indexOf(value);
            System.out.println(index);

            for (int i = 0; i < unitList.size(); i++) {
                if (i == index){
                    JLabel labelUnit = new JLabel(unitList.get(i).getName());
                    JTextField unitName = new JTextField();
                    JTextField unitCode = new JTextField();

                    final JComponent[] UserInput = new JComponent[] {
                            new JLabel("Unit you want to edit: "),
                            labelUnit,
                            new JLabel("Enter New Unit Name: "),
                            unitName,
                            new JLabel("Enter New Unit ID: "),
                            unitCode,

                    };

                    int responses = JOptionPane.showConfirmDialog(null, UserInput , "Edit Unit",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (responses == JOptionPane.CANCEL_OPTION) {
                        System.out.println("Cancel button clicked");
                    } else if (responses == JOptionPane.OK_OPTION) {
                        System.out.println("Ok button clicked");

                        if(unitName.getText().equals("") && unitCode.getText().equals("")){
                            infoOnComponent = "Incomplete unit info.\n";
                            infoOnComponent += "Please re-enter unit info.";
                            JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Unit Information", JOptionPane.WARNING_MESSAGE);
                            infoOnComponent = "";
                        }
                        else{
                            String inputName = unitName.getText();
                            String inputCode = unitCode.getText();

                            unitList.get(i).setName(inputName);
                            unitList.get(i).setCode(inputCode);

                            //store arraylist in txt file
                            writeUnitFile();

                            infoOnComponent = "Student had been edited. \n" +
                                    unitName.getText() + ", " +
                                    unitCode.getText();
                            JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
                            infoOnComponent = "";
                            displayUnitList();
                        }
                    } else if (responses == JOptionPane.CLOSED_OPTION) {
                        System.out.println("JOptionPane closed");
                    }
                }
            }
        }

        /**
         * Display dialog box for view each unit details
         */
        private void viewUnitDialogBox() {
            //read unit file
            readUnitFile();
            ArrayList<String> tempList = new ArrayList<>();
            for (Unit anUnitList : unitList) {
                String name = anUnitList.getName();
                tempList.add(name);
            }

            Object[] options = tempList.toArray();
            Object value = JOptionPane.showInputDialog(null,
                    "Which unit to view?",
                    "View Unit",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            int index = tempList.indexOf(value);
            System.out.println(index);

            for (int i = 0; i < unitList.size(); i++) {
                if (i == index){
                    String unitName = unitList.get(i).getName();
                    String unitCode = unitList.get(i).getCode();

                    infoOnComponent = "Unit Name: " + unitName + "\n"
                            + "Unit Code: " + unitCode + "\n";
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
                    infoOnComponent = "";
                }
            }
        }

        /**
         * Display dialog box for create unit
         */
        private void createUnitDialogBox() {
            JTextField unitName = new JTextField();
            JTextField unitCode = new JTextField();
            final JComponent[] inputs = new JComponent[] {
                    new JLabel("Enter Unit Name: "),
                    unitName,
                    new JLabel("Enter Unit Code: "),
                    unitCode
            };

            int response = JOptionPane.showConfirmDialog(null, inputs , "Create Unit",
                    JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.CANCEL_OPTION) {
                System.out.println("Cancel button clicked");
            } else if (response == JOptionPane.OK_OPTION) {
                System.out.println("Ok button clicked");

                if(unitName.getText().equals("") && unitCode.getText().equals("") ){
                    infoOnComponent = "Incomplete unit info.\n";
                    infoOnComponent += "Please re-enter unit info.";
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Unit Information", JOptionPane.ERROR_MESSAGE);
                    infoOnComponent = "";
                }
                else{
                    String inputName = unitName.getText();
                    String inputCode = unitCode.getText();

                    //create unit, add to arraylist
                    Unit newUnit = new Unit(inputName, inputCode);
                    unitList.add(newUnit);

                    //store arraylist in txt file
                    writeUnitFile();

                    infoOnComponent = "You entered " +
                            unitName.getText() + ", " +
                            unitCode.getText();
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
                    infoOnComponent = "";

                    //display unit list
                    displayUnitList();
                }
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }
        }

        /**
         * Display student list to text area
         */
        private void displayUnitList() {
            textArea1.setText("Unit Lists: \n" );

            //read unit file
            readUnitFile();

            //loop for unit list
            for (int i = 0; i < unitList.size(); i++) {
                textArea1.append(i + "." + unitList.get(i).getName() + "\n");
            }
        }

        //read unit file
        private void readUnitFile(){
            File unitFile = new File("unit.txt");
            if (unitFile.length() == 0) {
                System.out.println("File is empty.");
            } else {
                try {
                    FileInputStream fis2 = new FileInputStream("unit.txt");
                    ObjectInputStream ois2 = new ObjectInputStream(fis2);
                    unitList = (ArrayList) ois2.readObject();
                    ois2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //write unit file
        private void writeUnitFile(){
            try {
                FileOutputStream fos2 = new FileOutputStream("unit.txt");
                ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                oos2.writeObject(unitList);
                oos2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Display dialog that allow user to delete student
         */
        private void deleteStdDialogBox() {
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
                        JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
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
        private void editStdDialogBox() {
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

                    int responses = JOptionPane.showConfirmDialog(null, UserInput , "Create Student",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (responses == JOptionPane.CANCEL_OPTION) {
                        System.out.println("Cancel button clicked");
                    } else if (responses == JOptionPane.OK_OPTION) {
                        System.out.println("Ok button clicked");

                        if(stdName.getText().equals("") && stdId.getText().equals("") && stdProgram.getText().equals("")){
                            infoOnComponent = "Incomplete student info.\n";
                            infoOnComponent += "Please re-enter student info.";
                            JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.WARNING_MESSAGE);
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
                            JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
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
        private void viewStdDialogBox() {
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
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
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
        private void createStdDialogBox() {
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
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
                    infoOnComponent = "";
                    displayStudentList();
                }
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
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
         * Unit panel that display manage unit event
         */
        private class unitPanel extends JPanel {
            public unitPanel(){
                this.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;

                //create random label just for show
                JLabel label1 = new JLabel("Unit Menu");
                Font myFont = new Font("Serif", Font.BOLD, 20);
                label1.setFont(myFont);
                this.add(label1, gbc);
                gbc.gridy++;

                //create a button
                btnUnitCreate = new JButton("Create Unit");
                btnUnitView   = new JButton("View Unit");
                btnUnitEdit   = new JButton("Edit Unit");
                btnUnitDelete = new JButton("Delete Unit");
                btnUnitBack = new JButton("Back to Home");
                btnUnitBack.setToolTipText("return to home");

                //Create a listener for button and add button to it
                ListenForButton lForButton = new ListenForButton();
                btnUnitCreate.addActionListener(lForButton);
                btnUnitView.addActionListener(lForButton);
                btnUnitEdit.addActionListener(lForButton);
                btnUnitDelete.addActionListener(lForButton);
                btnUnitBack.addActionListener(lForButton);

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(30,10,0,0);
                gbc.ipady = 40;
                gbc.ipadx = 100;
                this.add(btnUnitCreate,gbc);
                gbc.gridy++;
                this.add(btnUnitView,gbc);
                gbc.gridy++;
                this.add(btnUnitEdit,gbc);
                gbc.gridy++;
                this.add(btnUnitDelete,gbc);
                gbc.gridy++;
                this.add(btnUnitBack,gbc);
            }
        }
    }

    /**
     * Window listener
     */
    private class ListenForWindow implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
            } else if (response == JOptionPane.YES_OPTION) {
                System.out.println("Yes button clicked");
                System.exit(0);
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

    /**
     * Create Menu bar for each frames
     *
     * @param frame
     */
    public void createMenuBar(JFrame frame){
        //Create menu bar to hold list of menu
        JMenuBar menuBar = new JMenuBar();
        ImageIcon iconExit = new ImageIcon("images/exit.png");
        ImageIcon iconCut = new ImageIcon("images/cut.png");
        ImageIcon iconCopy = new ImageIcon("images/copy.png");
        ImageIcon iconPaste = new ImageIcon("images/paste.png");
        ImageIcon iconDelete = new ImageIcon("images/delete.png");
        ImageIcon iconAbout = new ImageIcon("images/about.png");

        //Create menus to hold menu items
        JMenu menu1 = new JMenu("File");
        menu1.setMnemonic('F'); // underlining first occurrence (mnemonic)
        JMenu menu2 = new JMenu("Edit");
        menu2.setMnemonic('E');
        JMenu menu3 = new JMenu("Help");
        menu3.setMnemonic('H');

        //Create some menu items for menu1
        menu1_Item1 = new JMenuItem("Exit", iconExit);
        menu1_Item1.setMnemonic('x');
        menu1_Item1.setToolTipText("Exit Application");
        ListenForButton lForButton = new ListenForButton();
        menu1_Item1.addActionListener(lForButton);

        //Create some menu items for menu2
        JMenuItem menu2_Item1 = new JMenuItem("Cut", iconCut);
        menu2_Item1.setMnemonic('t');
        menu2_Item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        JMenuItem menu2_Item2 = new JMenuItem("Copy", iconCopy);
        menu2_Item2.setMnemonic('C');
        menu2_Item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem menu2_Item3 = new JMenuItem("Paste", iconPaste);
        menu2_Item3.setMnemonic('P');
        menu2_Item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        JMenuItem menu2_Item4 = new JMenuItem("Delete", iconDelete);
        menu2_Item4.setMnemonic('D');
        menu2_Item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));

        //Create some menu items for menu3
        JMenuItem menu3_Item1 = new JMenuItem("About", iconAbout);
        menu3_Item1.setMnemonic('A');

        //add the menu item into menu1
        menu1.add(menu1_Item1);

        //add the menu item into menu2
        menu2.add(menu2_Item1);
        menu2.add(menu2_Item2);
        menu2.add(menu2_Item3);
        menu2.add(menu2_Item4);

        //add the menu item into menu3
        menu3.add(menu3_Item1);

        //add menu into menu bar
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        //add menu bar to frame
        frame.setJMenuBar(menuBar);
    }

}
