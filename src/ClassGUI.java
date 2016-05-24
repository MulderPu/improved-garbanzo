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
 * To display class GUI
 */
class ClassGUI extends JFrame {

    /**
     * init variables
     */
    private JFrame frameClass;
    private JButton btnClassCreate, btnClassView, btnClassEdit, btnClassDelete, btnAddUnit, btnAddStd, btnRemoveStd, btnAddAss,
            btnAssignMark, btnViewMark, btnViewSummaryReport, btnViewUnitReport, btnClassBack;
    private JTextArea textArea1;
    private String infoOnComponent = "";
    private MenuBar menubar = new MenuBar();
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Student> studentList2 = new ArrayList<>();
    private static  ArrayList<Unit> unitList = new ArrayList<>();
    private static  ArrayList<Class> classList = new ArrayList<>();
    private static  ArrayList<Assessment> assessmentList = new ArrayList<>();
    private static  ArrayList<Assessment> assessmentList2 = new ArrayList<>();
    private static ArrayList<Submission> submissionsList = new ArrayList<>();

    /**
     * Class GUI constructor
     */
    ClassGUI(){
        displayClassFrame();
    }

    /**
     * Attribute of class frame
     */
    private void displayClassFrame(){
        frameClass = new JFrame("Student Assessment Recording Application");
        frameClass.setSize(1000,700);
        //Create menu bar
        menubar.displayMenuBar(frameClass);
        frameClass.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameClass.setLayout(new GridLayout(1,2,0,0));
        frameClass.add(new classPanel());
        frameClass.add(new classRightPanel());
        displayClassList(); //display unit list in text area
        //add window listener
        ListenForWindow lForWindow = new ListenForWindow();
        frameClass.addWindowListener(lForWindow);
        frameClass.setLocationRelativeTo(null); //set mid
        frameClass.setVisible(true);
        //add mouse listener
        ListenForMouse lForMouse = new ListenForMouse();
        frameClass.addMouseListener(lForMouse);
    }

    /**
     * Class panel used to display on Class frame
     */
    private class classPanel extends JPanel {
        public classPanel(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            //create a button
            btnClassCreate          = new JButton("Create Class");
            btnClassView            = new JButton("View Class");
            btnClassEdit            = new JButton("Edit Class");
            btnClassDelete          = new JButton("Delete Class");
            btnAddUnit              = new JButton("Add Unit");
            btnAddStd               = new JButton("Add Student");
            btnRemoveStd            = new JButton("Remove Student");
            btnAddAss               = new JButton("Add Assessment");
            btnAssignMark           = new JButton("Assign Mark");
            btnViewMark             = new JButton("View Mark");
            btnViewUnitReport       = new JButton("Individual Report");
            btnViewSummaryReport    = new JButton("Unit Summary Report");
            btnClassBack            = new JButton("Back to Home");
            btnClassBack.setToolTipText("return to home");

            //Create a listener for button and add button to it
            ListenForButton lForButton = new ListenForButton();
            btnClassCreate.addActionListener(lForButton);
            btnClassView.addActionListener(lForButton);
            btnClassEdit.addActionListener(lForButton);
            btnClassDelete.addActionListener(lForButton);
            btnAddUnit.addActionListener(lForButton);
            btnAddStd.addActionListener(lForButton);
            btnRemoveStd.addActionListener(lForButton);
            btnAddAss.addActionListener(lForButton);
            btnAssignMark.addActionListener(lForButton);
            btnViewMark.addActionListener(lForButton);
            btnViewUnitReport.addActionListener(lForButton);
            btnViewSummaryReport.addActionListener(lForButton);
            btnClassBack.addActionListener(lForButton);

            //create random label just for show
            JLabel label1 = new JLabel("Class Menu");
            Font myFont = new Font("Serif", Font.BOLD, 20);
            label1.setFont(myFont);
            gbc.fill = GridBagConstraints.NORTH;
            gbc.insets = new Insets(30,0,0,0);
            gbc.gridwidth = 2;
            gbc.gridx=0;
            gbc.gridy=0;
            this.add(label1, gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=0;
            gbc.gridy=1;
            this.add(btnClassCreate,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=0;
            gbc.gridy=2;
            this.add(btnClassView,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=0;
            gbc.gridy=3;
            this.add(btnClassEdit,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=0;
            gbc.gridy=4;
            this.add(btnClassDelete,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=0;
            gbc.gridy=5;
            this.add(btnAddUnit,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=0;
            gbc.gridy=6;
            this.add(btnAddStd,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=1;
            gbc.gridy=1;
            this.add(btnRemoveStd,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=1;
            gbc.gridy=2;
            this.add(btnAddAss,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=1;
            gbc.gridy=3;
            this.add(btnAssignMark,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=1;
            gbc.gridy=4;
            this.add(btnViewMark,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=1;
            gbc.gridy=5;
            this.add(btnViewUnitReport,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,15,0,0);
            gbc.ipady = 25;
            gbc.ipadx = 90;
            gbc.gridwidth = 1;
            gbc.gridx=1;
            gbc.gridy=6;
            this.add(btnViewSummaryReport,gbc);
            gbc.fill = GridBagConstraints.CENTER;
            gbc.insets = new Insets(15,0,0,0);
            gbc.ipady = 25;
            gbc.gridwidth = 2;
            gbc.gridx=0;
            gbc.gridy=7;
            this.add(btnClassBack,gbc);
        }
    }

    /**
     * Class button listener
     */
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnClassCreate){
                System.out.println("Create Class clicked");
                createClass();
            }
            else if(e.getSource() == btnClassView){
                System.out.println("View Class clicked");
                viewClass();
            }
            else if(e.getSource() == btnClassEdit){
                System.out.println("Edit Class clicked");
                editClass();
            }
            else if(e.getSource() == btnClassDelete){
                System.out.println("Delete Class clicked");
                deleteClass();
            }
            else if(e.getSource() == btnAddUnit){
                System.out.println("Add unit clicked");
                addUnitToClass();
            }
            else if(e.getSource() == btnAddStd){
                System.out.println("Add student clicked");
                addStudentToClass();
            }
            else if(e.getSource() == btnRemoveStd){
                System.out.println("Remove student clicked");
                removeStudentFromClass();
            }
            else if(e.getSource() == btnAddAss){
                System.out.println("Add assessment clicked");
                addAssessmentToClass();
            }
            else if(e.getSource() == btnAssignMark){
                System.out.println("Assign mark clicked");
                assignMarkToStudent();
            }
            else if(e.getSource() == btnViewMark){
                System.out.println("View mark clicked");
                viewStdMark();
            }
            else if(e.getSource() == btnViewUnitReport){
                System.out.println("View individual report clicked");
                individualReport();
            }
            else if(e.getSource() == btnViewSummaryReport){
                System.out.println("View Unit Summary Report clicked");
                unitSummaryReport();
            }
            else if(e.getSource() == btnClassBack){
                System.out.println("Back button clicked");
                frameClass.setVisible(false);
                new MainGui();
            }
        }
    }

    /**
     * Display the result of Unit's Summary Report
     */
    private void unitSummaryReport() {
        //read class file
        readClassFile();

        //save class list to temp list and add into the input dialog
        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Select a class: ",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        //extract assessment list into variable from class list
        for (Class aClassList : classList) {
            studentList2 = aClassList.getStudentList();
            assessmentList2 = aClassList.getAssessmentList();
        }

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();

                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Class: "+ className)
                };

                //dialog box to confirm
                int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Unit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (responses == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                    break;
                } else if (responses == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");

                    //display unit summary results
                    if (classList.get(i).getName().equals(className)) {
                        assessmentList = classList.get(i).getAssessmentList();

                        textArea1.setText("Unit Summary Report\n\n");
                        textArea1.append("Unit: "+classList.get(i).getUnit().getName()+"\n");
                        textArea1.append("Unit Code: "+classList.get(i).getUnit().getCode()+"\n");
                        textArea1.append("Semester: " + classList.get(i).getSem() + ", " + classList.get(i).getYear()+"\n");

                        //print results
                        for(int j =0; j < assessmentList.size() ; j++){
                            submissionsList = assessmentList.get(j).getSubmissionsList();
                            textArea1.append("\n" + "Assessment: " +assessmentList.get(j).getName()+ "\n");
                            for(int k =0; k < submissionsList.size();k++) {
                                textArea1.append(k + ". " + submissionsList.get(k).getId() + "     " + submissionsList.get(k).getName() + "    " + " Mark: " + submissionsList.get(k).getMark() + "\n");
                            }
                        }
                        //number of candidates
                        textArea1.append("\n"+"Total Number of candidates presenting: "+ submissionsList.size());
                    }
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }

    }

    /**
     * Display the result of Student's Individual Report
     */
    private void individualReport() {
        //read class file
        readClassFile();

        //save class list into a temp list to use in input dialog
        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Select a class: ",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        //extract assessment list into variable from class list
        for (Class aClassList : classList) {
            studentList2 = aClassList.getStudentList();
            assessmentList2 = aClassList.getAssessmentList();
        }

        //save student list into temp list
        ArrayList<String> tempStdList = new ArrayList<>();
        for (Student aStudentList : studentList2) {
            String stdName = aStudentList.getName();
            tempStdList.add(stdName);
        }

        //place temp list into input dialog
        Object[] stdOptions = tempStdList.toArray();
        Object stdValue = JOptionPane.showInputDialog(null,
                "Which student to view?",
                "Select Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                stdOptions,
                stdOptions[0]);

        int stdIndex = tempStdList.indexOf(stdValue);

        //get the result for both choices and display the result
        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<studentList2.size(); j++){
                    if(j == stdIndex) {
                        String stdName = studentList2.get(j).getName();

                        final JComponent[] UserInput = new JComponent[] {
                                new JLabel("Class: "+ className),
                                new JLabel("Student:" + stdName)
                        };

                        //confirmation dialog box
                        int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm View Student's Mark",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responses == JOptionPane.NO_OPTION) {
                            System.out.println("No button clicked");
                            break;
                        } else if (responses == JOptionPane.YES_OPTION) {
                            System.out.println("Yes button clicked");

                            double totalStdMark = 0;

                            //get the total mark of the student from the submissions
                            for(int k =0; k < assessmentList2.size() ; k++){
                                submissionsList = assessmentList2.get(k).getSubmissionsList();
                                System.out.println(assessmentList2.get(k).getName());
                                for(int l =0; l < submissionsList.size(); l++){
                                    if(submissionsList.get(l).getName().equals(stdName)) {
                                        System.out.println(submissionsList.get(l).getMark());
                                        totalStdMark += submissionsList.get(l).getMark();
                                    }
                                }
                            }
                            System.out.println("\n" + "Total mark = " + totalStdMark);
                            infoOnComponent = "Total mark = " + totalStdMark + "\n";

                            //check grade of total mark for the student
                            if(totalStdMark < 50){
                                System.out.println("Grade: N" );
                                infoOnComponent += "Grade: N";
                            }
                            else if(totalStdMark >=50 && totalStdMark < 60){
                                System.out.println("Grade: P");
                                infoOnComponent += "Grade: P";
                            }
                            else if(totalStdMark >=60 && totalStdMark<70){
                                System.out.println("Grade: C");
                                infoOnComponent += "Grade: C";
                            }
                            else if(totalStdMark >=70 && totalStdMark<80){
                                System.out.println("Grade: D");
                                infoOnComponent += "Grade: D";
                            }
                            else {
                                System.out.println("Grade: HD");
                                infoOnComponent = "Grade: HD";
                            }

                            JOptionPane.showMessageDialog(this, infoOnComponent, "Student Information", JOptionPane.INFORMATION_MESSAGE);
                            infoOnComponent = "";

                        } else if (responses == JOptionPane.CLOSED_OPTION) {
                            System.out.println("JOptionPane closed");
                        }
                    }
                }
            }
        }
    }

    /**
     * Used to view student mark
     */
    private void viewStdMark() {
        //read class file
        readClassFile();

        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Which class to view?",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        //extract assessment list into variable from class list
        for (Class aClassList : classList) {
            assessmentList2 = aClassList.getAssessmentList();
        }

        ArrayList<String> tempAssessmentList = new ArrayList<>();
        for (Assessment anAssessmentList : assessmentList2) {
            String assessmentName = anAssessmentList.getName();
            tempAssessmentList.add(assessmentName);
        }

        Object[] assessmentOptions = tempAssessmentList.toArray();
        Object assessmentValue = JOptionPane.showInputDialog(null,
                "Which assessment?",
                "Select Assessment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                assessmentOptions,
                assessmentOptions[0]);

        int assessmentIndex = tempAssessmentList.indexOf(assessmentValue);

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<assessmentList2.size(); j++){
                    if(j == assessmentIndex) {
                        String assessmentName = assessmentList2.get(j).getName();

                        final JComponent[] UserInput = new JComponent[] {
                                new JLabel("Class: "+ className),
                                new JLabel("Assessment" + assessmentName)
                        };

                        int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm View Mark",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responses == JOptionPane.NO_OPTION) {
                            System.out.println("No button clicked");
                            break;
                        } else if (responses == JOptionPane.YES_OPTION) {
                            System.out.println("Yes button clicked");

                            submissionsList = assessmentList2.get(j).getSubmissionsList();
                            textArea1.setText("Class: "+className+"\n" );
                            textArea1.append("Assessment: "+assessmentName+"\n" );
                            textArea1.append("List of Student's mark: \n" );
                            for(int k =0; k < assessmentList2.size();k++) {
                                textArea1.append(k + ") " +  submissionsList.get(k).getName() + "         " + " Mark: " + submissionsList.get(k).getMark()+ "\n");
                            }
                        } else if (responses == JOptionPane.CLOSED_OPTION) {
                            System.out.println("JOptionPane closed");
                        }
                    }
                }
            }
        }
    }

    /**
     * Allow user to assign mark for the student
     */
    private void assignMarkToStudent() {
        //read class file
        readClassFile();

        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Which class to assign the mark?",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        //extract student list and assessment list into variable from class list
        for (Class aClassList : classList) {
            studentList2 = aClassList.getStudentList();
            assessmentList2 = aClassList.getAssessmentList();
        }

        ArrayList<String> tempAssessmentList = new ArrayList<>();
        for (Assessment anAssessmentList : assessmentList2) {
            String assessmentName = anAssessmentList.getName();
            tempAssessmentList.add(assessmentName);
        }

        Object[] assessmentOptions = tempAssessmentList.toArray();
        Object assessmentValue = JOptionPane.showInputDialog(null,
                "Which assessment?",
                "Select Assessment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                assessmentOptions,
                assessmentOptions[0]);

        int assessmentIndex = tempAssessmentList.indexOf(assessmentValue);

        ArrayList<String> tempStdList = new ArrayList<>();
        for (Student aStudentList : studentList2) {
            String stdName = aStudentList.getName();
            tempStdList.add(stdName);
        }

        Object[] stdOptions = tempStdList.toArray();
        Object stdValue = JOptionPane.showInputDialog(null,
                "Which student?",
                "Select Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                stdOptions,
                stdOptions[0]);

        int stdIndex = tempStdList.indexOf(stdValue);

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<assessmentList2.size(); j++){
                    if(j == assessmentIndex) {
                        String assessmentName = assessmentList2.get(j).getName();
                        for (int k = 0; k < studentList2.size(); k++) {
                            if (k == stdIndex) {
                                String stdName = studentList2.get(k).getName();

                                JTextField stdMark  = new JTextField();
                                final JComponent[] UserInput = new JComponent[] {
                                        new JLabel("Class: " + className),
                                        new JLabel("Assessment: " + assessmentName),
                                        new JLabel("Student: " + stdName),
                                        new JLabel("Weight : " + assessmentList2.get(j).getWeight()),
                                        new JLabel(" Student Mark: "),
                                        stdMark
                                };

                                int responses = JOptionPane.showConfirmDialog(null, UserInput , "Assign Mark",
                                        JOptionPane.OK_CANCEL_OPTION);
                                if (responses == JOptionPane.CANCEL_OPTION) {
                                    System.out.println("Cancel button clicked");
                                } else if (responses == JOptionPane.OK_OPTION) {
                                    System.out.println("Ok button clicked");

                                    if( stdMark.getText().equals("")){
                                        infoOnComponent = "Incomplete mark input.\n";
                                        infoOnComponent += "Please re-enter mark.";
                                        JOptionPane.showMessageDialog(this, infoOnComponent, "Assessment Mark", JOptionPane.WARNING_MESSAGE);
                                        infoOnComponent = "";
                                    }
                                    else{
                                        String inputMark = stdMark.getText();
                                        double inputStdMark = Double.parseDouble(inputMark);

                                        if(assessmentList2.get(j).getName().equals(assessmentName)){
                                            submissionsList = assessmentList2.get(j).getSubmissionsList();
                                            for(int l =0; l < submissionsList.size(); l++){
                                                if(submissionsList.get(l).getName().equals(stdName) ) {
                                                    submissionsList.get(l).setMark(inputStdMark);
                                                }
                                            }
                                        }


                                        //store arraylist in txt file
                                        writeClassFile();

                                        infoOnComponent = "Student's mark had been assigned.";
                                        JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                                        infoOnComponent = "";
                                        displayClassList();
                                    }
                                } else if (responses == JOptionPane.CLOSED_OPTION) {
                                    System.out.println("JOptionPane closed");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Allow user to add assessment created into the classes
     */
    private void addAssessmentToClass() {
        //read class and unit files
        readClassFile();
        readAssessmentFile();

        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Add assessment to which class?",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        ArrayList<String> tempAssessmentList = new ArrayList<>();
        for (Assessment anAssessmentList : assessmentList) {
            String assessmentName = anAssessmentList.getName();
            tempAssessmentList.add(assessmentName);
        }

        Object[] assessmentOptions = tempAssessmentList.toArray();
        Object assessmentValue = JOptionPane.showInputDialog(null,
                "Add assessment?",
                "Select Unit",
                JOptionPane.QUESTION_MESSAGE,
                null,
                assessmentOptions,
                assessmentOptions[0]);

        int assessmentIndex = tempAssessmentList.indexOf(assessmentValue);
        System.out.println(assessmentIndex);

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<assessmentList.size(); j++){
                    if(j == assessmentIndex){
                        String assessmentName = assessmentList.get(j).getName();

                        JLabel labelClass = new JLabel(className);
                        JLabel labelAssessment = new JLabel(assessmentName);
                        final JComponent[] UserInput = new JComponent[] {
                                new JLabel("Do you want to add assessment: \n"),
                                labelAssessment,
                                new JLabel("To this class? \n"),
                                labelClass
                        };

                        int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Add Assessment",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responses == JOptionPane.NO_OPTION) {
                            System.out.println("No button clicked");
                        } else if (responses == JOptionPane.YES_OPTION) {
                            System.out.println("Yes button clicked");

                            classList.get(i).setAssessment(assessmentList.get(j));

                            //extract student list and assessment list into variable from class list
                            studentList = classList.get(i).getStudentList();
                            assessmentList2 = classList.get(i).getAssessmentList();

                            for(int k =0; k < assessmentList2.size() ; k++){
                                if(assessmentList2.get(i).getName().equals(assessmentName)) {
                                    for (int l = 0; l < studentList.size(); l++) {
                                        String studName = studentList.get(l).getName();
                                        String studId = studentList.get(l).getId();

                                        Submission newSub = new Submission(studName, studId);
                                        assessmentList2.get(k).setSubmission(newSub);
                                    }
                                }
                            }

//                            System.out.println(classList.get(i).getAssessmentList());

                            writeClassFile();

                            infoOnComponent = "Assessment had been added to class.";
                            JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                            infoOnComponent = "";
                            displayClassList();
                        } else if (responses == JOptionPane.CLOSED_OPTION) {
                            System.out.println("JOptionPane closed");
                        }
                    }
                }
            }
        }
    }

    /**
     * Allow user to remove student from the class
     */
    private void removeStudentFromClass() {
        //read class file
        readClassFile();

        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Remove student from which class?",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        for(int i =0; i<classList.size();i++){
            studentList2 = classList.get(i).getStudentList();
        }

        ArrayList<String> tempStdList = new ArrayList<>();
        for (Student aStudentList : studentList2) {
            String stdName = aStudentList.getName();
            tempStdList.add(stdName);
        }

        Object[] stdOptions = tempStdList.toArray();
        Object stdValue = JOptionPane.showInputDialog(null,
                "Remove student?",
                "Select Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                stdOptions,
                stdOptions[0]);

        int stdIndex = tempStdList.indexOf(stdValue);

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<studentList2.size(); j++){
                    if(j == stdIndex){
                        String stdName = studentList2.get(j).getName();

                        JLabel labelClass = new JLabel(className);
                        JLabel labelStd = new JLabel(stdName);
                        final JComponent[] UserInput = new JComponent[] {
                                new JLabel("Do you want to remove this student: \n"),
                                labelStd,
                                new JLabel("From this class? \n"),
                                labelClass
                        };

                        int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Remove Student",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responses == JOptionPane.NO_OPTION) {
                            System.out.println("No button clicked");
                        } else if (responses == JOptionPane.YES_OPTION) {
                            System.out.println("Yes button clicked");

                            classList.get(i).removeStudent(studentList2.get(j).getName());

                            assessmentList2 = classList.get(i).getAssessmentList();
                            for(int k = 0; k < assessmentList2.size(); k++){
                                assessmentList2.get(k).removeSubmission(studentList2.get(k).getName());
                            }
                            writeClassFile();

                            infoOnComponent = "Student had been removed from class.";
                            JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                            infoOnComponent = "";
                            displayClassList();

                        } else if (responses == JOptionPane.CLOSED_OPTION) {
                            System.out.println("JOptionPane closed");
                        }
                    }
                }
            }
        }
    }

    /**
     * Allow user to add student into the class
     */
    private void addStudentToClass() {
        //read class and student files
        readClassFile();
        readStudentFile();

        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Which class you want to add student?",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        ArrayList<String> tempStdList = new ArrayList<>();
        for (Student aStudentList : studentList) {
            String stdName = aStudentList.getName();
            tempStdList.add(stdName);
        }

        Object[] stdOptions = tempStdList.toArray();
        Object stdValue = JOptionPane.showInputDialog(null,
                "Which student you want to add into the class?",
                "Select Student",
                JOptionPane.QUESTION_MESSAGE,
                null,
                stdOptions,
                stdOptions[0]);

        int stdIndex = tempStdList.indexOf(stdValue);

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<studentList.size(); j++){
                    if(j == stdIndex){
                        String stdName = studentList.get(j).getName();

                        JLabel labelClass = new JLabel(className);
                        JLabel labelStd = new JLabel(stdName);
                        final JComponent[] UserInput = new JComponent[] {
                                new JLabel("Do you want to add this student: \n"),
                                labelStd,
                                new JLabel("To this class? \n"),
                                labelClass
                        };

                        int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Add Student",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responses == JOptionPane.NO_OPTION) {
                            System.out.println("No button clicked");
                        } else if (responses == JOptionPane.YES_OPTION) {
                            System.out.println("Yes button clicked");

                            classList.get(i).enrollStudent(studentList.get(j));
                            writeClassFile();

                            infoOnComponent = "Student had been added to the class.";
                            JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                            infoOnComponent = "";
                            displayClassList();

                        } else if (responses == JOptionPane.CLOSED_OPTION) {
                            System.out.println("JOptionPane closed");
                        }
                    }
                }
            }
        }
    }

    /**
     * Class right panel
     */
    private class classRightPanel extends JPanel {
        classRightPanel(){
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
     * Add unit to class
     */
    private void addUnitToClass() {
        //read class and unit files
        readClassFile();
        readUnitFile();

        ArrayList<String> tempClassList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempClassList.add(name);
        }

        Object[] classOptions = tempClassList.toArray();
        Object classValue = JOptionPane.showInputDialog(null,
                "Which class you want to add unit?",
                "Select Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                classOptions,
                classOptions[0]);

        int classIndex = tempClassList.indexOf(classValue);

        ArrayList<String> tempUnitList = new ArrayList<>();
        for (Unit anUnitList : unitList) {
            String unitName = anUnitList.getName();
            tempUnitList.add(unitName);
        }

        Object[] unitOptions = tempUnitList.toArray();
        Object unitValue = JOptionPane.showInputDialog(null,
                "Which unit you want to add?",
                "Select Unit",
                JOptionPane.QUESTION_MESSAGE,
                null,
                unitOptions,
                unitOptions[0]);

        int unitIndex = tempUnitList.indexOf(unitValue);

        for(int i =0; i<classList.size(); i++){
            if(i == classIndex){
                String className = classList.get(i).getName();
                for (int j =0; j<unitList.size(); j++){
                    if(j == unitIndex){
                        String unitName = unitList.get(j).getName();

                        JLabel labelClass = new JLabel(className);
                        JLabel labelUnit = new JLabel(unitName);
                        final JComponent[] UserInput = new JComponent[] {
                                new JLabel("Do you want to add unit: \n"),
                                labelUnit,
                                new JLabel("To this class? \n"),
                                labelClass
                        };

                        int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Add",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (responses == JOptionPane.NO_OPTION) {
                            System.out.println("No button clicked");
                        } else if (responses == JOptionPane.YES_OPTION) {
                            System.out.println("Yes button clicked");

                            classList.get(i).setUnit(unitList.get(j));
                            writeClassFile();

                            infoOnComponent = "Unit had been added to class: \n"+ classList.get(i).getName();
                            JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                            infoOnComponent = "";
                            displayClassList();
                        } else if (responses == JOptionPane.CLOSED_OPTION) {
                            System.out.println("JOptionPane closed");
                        }

                    }
                }
            }
        }
    }

    /**
     * Delete class
     */
    private void deleteClass() {
        //read class file
        readClassFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();
        Object value = JOptionPane.showInputDialog(null,
                "Which class to delete?",
                "Delete Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < classList.size(); i++) {
            if (i == index){

                JLabel labelClass = new JLabel(classList.get(i).getName());
                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Do you want to delete this class?"),
                        labelClass
                };

                int responses = JOptionPane.showConfirmDialog(null, UserInput, "Confirm Delete",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (responses == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (responses == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    classList.remove(i);
                    writeClassFile();
                    infoOnComponent = "Class had been deleted. ";
                    JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                    infoOnComponent = "";
                    displayUnitList();
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }
    }

    /**
     * Edit Class Details
     */
    private void editClass() {
        //read class file
        readClassFile();
        ArrayList<String> tempList = new ArrayList<>();
        for (Class anClassList : classList) {
            String name = anClassList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();
        Object value = JOptionPane.showInputDialog(null,
                "Which class to edit?",
                "Edit Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < classList.size(); i++) {
            if (i == index){
                JLabel labelClass = new JLabel(classList.get(i).getName());
                JTextField className = new JTextField();
                JTextField classCode = new JTextField();
                JTextField classSem  = new JTextField();
                JTextField classYear = new JTextField();

                final JComponent[] UserInput = new JComponent[] {
                        new JLabel("Class: "),
                        labelClass,
                        new JLabel(" New Class Name: "),
                        className,
                        new JLabel(" New Class Code: "),
                        classCode,
                        new JLabel(" New Class Semester: "),
                        classSem,
                        new JLabel(" New Class Year: "),
                        classYear
                };

                int responses = JOptionPane.showConfirmDialog(null, UserInput , "Edit Class",
                        JOptionPane.OK_CANCEL_OPTION);
                if (responses == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Cancel button clicked");
                } else if (responses == JOptionPane.OK_OPTION) {
                    System.out.println("Ok button clicked");

                    if(className.getText().equals("") && classCode.getText().equals("") && classSem.getText().equals("") && classYear.getText().equals("")){
                        infoOnComponent = "Incomplete class info.\n";
                        infoOnComponent += "Please re-enter class info.";
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.WARNING_MESSAGE);
                        infoOnComponent = "";
                    }
                    else{
                        String inputName = className.getText();
                        String inputCode = classCode.getText();
                        String inputSem = classSem.getText();
                        String inputYear = classYear.getText();

                        classList.get(i).setName(inputName);
                        classList.get(i).setCode(inputCode);
                        classList.get(i).setSem(inputSem);
                        classList.get(i).setYear(inputYear);

                        //store arraylist in txt file
                        writeClassFile();

                        infoOnComponent = "Class had been edited. \n" +
                                className.getText() + ", " +
                                classCode.getText() + ", " +
                                classSem.getText()  + ", " +
                                classYear.getText();
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                        infoOnComponent = "";
                        displayClassList();
                    }
                } else if (responses == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            }
        }
    }

    /**
     * View Class Details
     */
    private void viewClass() {
        //read class file
        readClassFile();

        ArrayList<String> tempList = new ArrayList<>();
        for (Class anClassList : classList ) {
            String name = anClassList.getName();
            tempList.add(name);
        }

        Object[] options = tempList.toArray();
        Object value = JOptionPane.showInputDialog(null,
                "Which class to view?",
                "View Class",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int index = tempList.indexOf(value);
        System.out.println(index);

        for (int i = 0; i < classList.size(); i++) {

            studentList2 = classList.get(i).getStudentList();
            assessmentList2 = classList.get(i).getAssessmentList();

            if (i == index){
                String className = classList.get(i).getName();
                String classCode = classList.get(i).getCode();
                String classSem = classList.get(i).getSem();
                String classYear = classList.get(i).getYear();

                infoOnComponent = "Class Name: " + className + "\n"
                        + "Class Code: " + classCode + "\n"
                        + "Class Semester: " + classSem + "\n"
                        + "Class Year: " + classYear + "\n";
                infoOnComponent += classList.get(i).getUnit() + "\n";

                //display student list
                infoOnComponent += "List of students: " + "\n";
                for(int j=0; j<studentList2.size(); j++){
                    infoOnComponent += "\t" + j + "." + studentList2.get(j).getName() + "\n";
                }

                //display assessment list
                infoOnComponent += "List of assessments: " + "\n";
                for(int k=0; k<assessmentList2.size(); k++){
                    infoOnComponent += "\t" + k + "." + assessmentList2.get(k).getName() + "\n";
                }

                JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
            }
        }
    }

    /**
     * Create class
     */
    private void createClass() {
        JTextField className = new JTextField();
        JTextField classCode = new JTextField();
        JTextField classSem  = new JTextField();
        JTextField classYear = new JTextField();

        final JComponent[] inputs = new JComponent[] {
                new JLabel(" Class Name: "),
                className,
                new JLabel(" Class Code: "),
                classCode,
                new JLabel(" Class Semester: "),
                classSem,
                new JLabel(" Class Year: "),
                classYear,
        };

        int response = JOptionPane.showConfirmDialog(null, inputs , "Create Class",
                JOptionPane.OK_CANCEL_OPTION);
        if (response == JOptionPane.CANCEL_OPTION) {
            System.out.println("Cancel button clicked");
        } else if (response == JOptionPane.OK_OPTION) {
            System.out.println("Ok button clicked");

            if(className.getText().equals("") && classCode.getText().equals("")&& classSem.getText().equals("")&& classYear.getText().equals("") ){
                infoOnComponent = "Incomplete class info.\n";
                infoOnComponent += "Please re-enter class info.";
                JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.ERROR_MESSAGE);
                infoOnComponent = "";
            }
            else{
                String inputName = className.getText();
                String inputCode = classCode.getText();
                String inputSem = classSem.getText();
                String inputYear = classYear.getText();

                //create class, add to arraylist
                Class newClass = new Class(inputName, inputCode, inputSem, inputYear);
                classList.add(newClass);

                //store arraylist in txt file
                writeClassFile();

                infoOnComponent = "You entered " +
                        className.getText() + ", " +
                        classCode.getText() + ", " +
                        classSem.getText()  + ", " +
                        classYear.getText();
                JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";

                //display class list
                displayClassList();
            }
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("JOptionPane closed");
        }
    }

    /**
     * Display Class list
     */
    private void displayClassList() {
        textArea1.setText("Class Lists: \n" );

        //read class file
        readClassFile();

        //loop for class list
        for (int i = 0; i < classList.size(); i++) {
            textArea1.append(i + "." + classList.get(i).getName() + "\n");
        }
    }

    /**
     * read class file
     */
    private void readClassFile(){
        File classFile = new File("class.txt");
        if (classFile.length() == 0) {
            System.out.println("File is empty.");
        } else {
            try {
                FileInputStream fis3 = new FileInputStream("class.txt");
                ObjectInputStream ois3 = new ObjectInputStream(fis3);
                classList = (ArrayList) ois3.readObject();
                ois3.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write class file
     */
    private void writeClassFile(){
        try {
            FileOutputStream fos3 = new FileOutputStream("class.txt");
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            oos3.writeObject(classList);
            oos3.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * read unit file
     */
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
     * read assessment file
     */
    private static void readAssessmentFile(){
        File assessmentFile = new File("assessment.txt");
        if (assessmentFile.length() == 0) {
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
}
