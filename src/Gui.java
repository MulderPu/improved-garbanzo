import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/1/2016.
 */
public class Gui extends JFrame implements ActionListener{
    JFrame frame1, frame2;
    JButton btnManageStudent, btnManageUnit ,btnManageClass, btnManageAssessment, btnManageExit;
    JButton btnCreateStd, btnViewStd, btnEditStd, btnDeleteStd;
    JButton btnBack;
    JTextArea textArea1;

    public Gui(){
        displayFrame1();
    }

    public void displayFrame1(){
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

    protected  class studentPanel extends JPanel{
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
            btnCreateStd = new JButton("Create Student");
            btnViewStd   = new JButton("View Unit");
            btnEditStd   = new JButton("Edit Class");
            btnDeleteStd = new JButton("Delete Assessment");
            btnBack      = new JButton("Back to Home");
            btnBack.setToolTipText("return to home");

            //Create a listener for button and add button to it
            ListenForButton lForButton = new ListenForButton();
            btnCreateStd.addActionListener(lForButton);
            btnViewStd.addActionListener(lForButton);
            btnEditStd.addActionListener(lForButton);
            btnDeleteStd.addActionListener(lForButton);
            btnBack.addActionListener(lForButton);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30,10,0,0);
            gbc.ipady = 40;
            gbc.ipadx = 100;
            this.add(btnCreateStd,gbc);
            gbc.gridy++;
            this.add(btnViewStd,gbc);
            gbc.gridy++;
            this.add(btnEditStd,gbc);
            gbc.gridy++;
            this.add(btnDeleteStd,gbc);
            gbc.gridy++;
            this.add(btnBack,gbc);
        }
    }

    protected class leftPanel extends JPanel{

        public leftPanel() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            //create random label just for show
            JLabel label1 = new JLabel("Welcome to the system!");
            Font myFont = new Font("Serif", Font.BOLD, 20);
            label1.setFont(myFont);
            this.add(label1, gbc);
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

    protected class rightPanel extends JPanel{

        public rightPanel() {
            JLabel label1 = new JLabel("Information: ");
            this.add(label1);

            //create a textArea
            textArea1 = new JTextArea(35,40);
            textArea1.setText("Welcome to the Student Assessment Recording Application\n");
            textArea1.setLineWrap(true); //end then wrap to next line
            textArea1.setWrapStyleWord(true); //not split words

            int numOfLines = textArea1.getLineCount();
            textArea1.append("Number of lines: " + numOfLines);
            this.add(textArea1);

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
                frame2.setVisible(true);
            }
            else if(e.getSource() == btnManageUnit){

            }
            else if(e.getSource() == btnManageClass){

            }
            else if(e.getSource() == btnManageAssessment){

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
