import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/9/2016.
 */
public class ClassGUI extends JFrame {
    private JFrame frameClass;
    private JButton btnClassCreate, btnClassView, btnClassEdit, btnClassDelete, btnAddUnit, btnAddStd, btnRemoveStd, btnAddAss,
            btnAssignMark, btnViewMark, btnViewSummaryReport, btnViewIndividualReport, btnClassBack;
    private JTextArea textArea1;
    private String infoOnComponent = "";
    private MenuBar menubar = new MenuBar();
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static  ArrayList<Unit> unitList = new ArrayList<>();
    private static  ArrayList<Class> classList = new ArrayList<>();
    private static  ArrayList<Assessment> assessmentList = new ArrayList<>();
    private static  ArrayList<Assessment> assessmentList2 = new ArrayList<>();
    private static ArrayList<Submission> submissionsList = new ArrayList<>();

    public ClassGUI(){
        displayClassFrame();
    }

    public void displayClassFrame(){
        frameClass = new JFrame("Student Assessment Recording Application");
        frameClass.setSize(1000,700);
        //Create menu bar
        menubar.displayMenuBar(frameClass);
        frameClass.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameClass.setLayout(new GridLayout(1,2,0,0));
        frameClass.add(new classPanel());
        frameClass.add(new classRightPanel());
        displayClassList(); //display unit list in text area
        ListenForWindow lForWindow = new ListenForWindow();
        frameClass.addWindowListener(lForWindow);
        frameClass.setLocationRelativeTo(null);
        frameClass.setVisible(true);
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
            btnViewIndividualReport = new JButton("Individual Report");
            btnViewSummaryReport    = new JButton("Summary Report");
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
            btnViewIndividualReport.addActionListener(lForButton);
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
            this.add(btnViewIndividualReport,gbc);
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

    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnClassCreate){
                System.out.println("Create Class clicked");
                createClassDialogBox();
            }
            else if(e.getSource() == btnClassView){
                System.out.println("View Class clicked");
                viewClassDialogBox();
            }
            else if(e.getSource() == btnClassEdit){
                System.out.println("Edit Class clicked");
                editClassDialogBox();
            }
            else if(e.getSource() == btnClassDelete){
                System.out.println("Delete Class clicked");
                deleteClassDialogBox();
            }
            else if(e.getSource() == btnAddUnit){
                System.out.println("Add unit clicked");
                addUnitDialogBox();
            }
            else if(e.getSource() == btnAddStd){
                System.out.println("Add student clicked");
//                addStudentDialogBox();
            }
            else if(e.getSource() == btnRemoveStd){
                System.out.println("Remove student clicked");
//                removeStudentDialogBox();
            }
            else if(e.getSource() == btnAddAss){
                System.out.println("Add assessment clicked");
//                addAssessmentDialogBox();
            }
            else if(e.getSource() == btnAssignMark){
                System.out.println("Assign mark clicked");
//                assignMarkDialogBox();
            }
            else if(e.getSource() == btnViewMark){
                System.out.println("View mark clicked");
//                viewMarkDialogBox();
            }
            else if(e.getSource() == btnViewIndividualReport){
                System.out.println("View individual report clicked");
//                individualReportDialogBox();
            }
            else if(e.getSource() == btnViewSummaryReport){
                System.out.println("View Summary Report clicked");
//                summaryReportDialogBox();
            }
            else if(e.getSource() == btnClassBack){
                System.out.println("Back button clicked");
                frameClass.setVisible(false);
                new MainGui();
            }
        }
    }

    private class classRightPanel extends JPanel {
        public classRightPanel(){
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

    private void addUnitDialogBox() {
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
        System.out.println(classIndex);

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
        System.out.println(unitIndex);

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

                            infoOnComponent = classList.get(i).getUnit().getName() + " had been added to class: \n"+ classList.get(i).getName();
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

    private void deleteClassDialogBox() {
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

    private void editClassDialogBox() {
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
                        new JLabel("Class you want to edit: "),
                        labelClass,
                        new JLabel("Enter New Class Name: "),
                        className,
                        new JLabel("Enter New Class Code: "),
                        classCode,
                        new JLabel("Enter New Class Semester: "),
                        classSem,
                        new JLabel("Enter New Class Year: "),
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

    private void viewClassDialogBox() {
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
            if (i == index){
                String className = classList.get(i).getName();
                String classCode = classList.get(i).getCode();
                String classSem = classList.get(i).getSem();
                String classYear = classList.get(i).getYear();


                infoOnComponent = "Class Name: " + className + "\n"
                        + "Class Code: " + classCode + "\n"
                        + "Class Semester: " + classSem + "\n"
                        + "Class Year: " + classYear + "\n";
                infoOnComponent += classList.get(i).getUnit();
                JOptionPane.showMessageDialog(this, infoOnComponent, "Class Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
            }
        }
    }

    private void createClassDialogBox() {
        JTextField className = new JTextField();
        JTextField classCode = new JTextField();
        JTextField classSem  = new JTextField();
        JTextField classYear = new JTextField();

        final JComponent[] inputs = new JComponent[] {
                new JLabel("Enter Class Name: "),
                className,
                new JLabel("Enter Class Code: "),
                classCode,
                new JLabel("Enter Class Semester: "),
                classSem,
                new JLabel("Enter Class Year: "),
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

    private void displayClassList() {
        textArea1.setText("Class Lists: \n" );

        //read class file
        readClassFile();

        //loop for class list
        for (int i = 0; i < classList.size(); i++) {
            textArea1.append(i + "." + classList.get(i).getName() + "\n");
        }
    }

    //read class file
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

    //write class file
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
}
