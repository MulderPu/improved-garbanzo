import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/1/2016.
 */
public class Gui extends JFrame implements ActionListener{
    JFrame frame1, frame2;
    JFrame frameCreateStd, frameEditStd, frameDeleteStd;
    JButton btnManageStudent, btnManageUnit ,btnManageClass, btnManageAssessment, btnManageExit;
    //Student Panel
    JButton btnStdCreate, btnStdView, btnStdEdit, btnStdDelete;
    JButton btnBackHome;
    JTextArea textArea1;
    String infoOnComponent = "";

    static  ArrayList<Student> studentList = new ArrayList<>();
    static  ArrayList<Unit> unitList = new ArrayList<>();
    static  ArrayList<Class> classList = new ArrayList<>();
    static  ArrayList<Assessment> assessmentList = new ArrayList<>();
    static  ArrayList<Assessment> assessmentList2 = new ArrayList<>();
    static ArrayList<Submission> submissionsList = new ArrayList<>();
    static boolean loop = true;

    public Gui(){
        displayFrameHome();
    }

    public void displayFrameHome(){
        frame1 = new JFrame("Student Assessment Recording Application");
        frame1.setSize(1000,700);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create menu bar
        createMenuBar(frame1);
        frame1.setLayout(new GridLayout(1,2,0,0));
        frame1.add(new leftPanel());
        frame1.add(new rightPanel());
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

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
            btnStdView   = new JButton("View Unit");
            btnStdEdit   = new JButton("Edit Class");
            btnStdDelete = new JButton("Delete Assessment");
            btnBackHome      = new JButton("Back to Home");
            btnBackHome.setToolTipText("return to home");

            //Create a listener for button and add button to it
            ListenForButton lForButton = new ListenForButton();
            btnStdCreate.addActionListener(lForButton);
            btnStdView.addActionListener(lForButton);
            btnStdEdit.addActionListener(lForButton);
            btnStdDelete.addActionListener(lForButton);
            btnBackHome.addActionListener(lForButton);

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
            this.add(btnBackHome,gbc);
        }
    }

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

    private class rightPanel extends JPanel{

        public rightPanel() {
            JLabel label1 = new JLabel("Information: ");
            this.add(label1);

            //create a textArea
            textArea1 = new JTextArea(35,40);
            textArea1.setLineWrap(true); //end then wrap to next line
            textArea1.setWrapStyleWord(true); //not split words
            JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.add(scrollbar1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    public class ListenForButton extends JFrame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnManageStudent){
                System.out.println("Manage student clicked");
                frame1.setVisible(false);
                frame2 = new JFrame("Student Assessment Recording Application");
                frame2.setSize(1000,700);
                //Create menu bar
                createMenuBar(frame2);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setLayout(new GridLayout(1,2,0,0));
                frame2.setLocationRelativeTo(null);
                System.out.println("Manage student clicked");
                frame2.add(new studentPanel());
                frame2.add(new rightPanel());
                displayStudentList(); //display student list in text area
                frame2.setVisible(true);
            }
            else if(e.getSource() == btnManageUnit){
                System.out.println("Manage unit clicked");
            }
            else if(e.getSource() == btnManageClass){
                System.out.println("Manage class clicked");
            }
            else if(e.getSource() == btnManageAssessment){
                System.out.println("Manage assessment clicked");
            }
            else if(e.getSource() == btnManageExit){
                int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
                
            }
            else if(e.getSource() == btnStdDelete){
                System.out.println("Delete student clicked");
            }
            else if(e.getSource() == btnBackHome){
                System.out.println("Back button clicked");
                frame2.setVisible(false);
                displayFrameHome();
            }
        }

        private void viewStdDialogBox() {
            JTextField stdIndex = new JTextField();

            final JComponent[] inputs = new JComponent[] {
                    new JLabel("Enter Student Name: "),
                    stdIndex
            };

            int response = JOptionPane.showConfirmDialog(null, inputs , "View Student",
                    JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.CANCEL_OPTION) {
                System.out.println("Cancel button clicked");
            } else if (response == JOptionPane.OK_OPTION) {
                System.out.println("Ok button clicked");

                if(stdIndex.getText().equals("") ){
                    infoOnComponent = "Empty is not accepted.\n";
                    infoOnComponent += "Please re-enter info.";
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.WARNING_MESSAGE);
                    infoOnComponent = "";
                }
                else{
                    try{
                        String userInput = stdIndex.getText();
                        int index = Integer.parseInt(userInput);
                        //read student file
                        readStudentFile();

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
                    }catch (NumberFormatException e){
                        infoOnComponent = "Enter number only.";
                        JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.WARNING_MESSAGE);
                        infoOnComponent = "";
                    }
                }
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }
        }

        private void displayStudentList() {
            textArea1.setText("Student Lists: \n" );
            File studentFile = new File("student.txt");
            if (studentFile.length() == 0) {
                System.out.println("Empty File");
            } else {
                readStudentFile();
            }

            //loop for student list
            for (int i = 0; i < studentList.size(); i++) {
                textArea1.append(i + "." + studentList.get(i).getName() + "\n");
            }
        }

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
                    JOptionPane.showMessageDialog(Gui.this, infoOnComponent, "Student Information", JOptionPane.WARNING_MESSAGE);
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

        //read student file
        private void readStudentFile(){
            try {
                FileInputStream fis = new FileInputStream("student.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                studentList = (ArrayList) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //write student file
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
    }

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
        JMenuItem menu1_Item1 = new JMenuItem("Exit", iconExit);
        menu1_Item1.setMnemonic('x');
        menu1_Item1.setToolTipText("Exit Application");
        menu1_Item1.addActionListener(e -> System.exit(0));

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
