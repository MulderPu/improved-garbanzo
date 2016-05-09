import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/1/2016.
 *
 * Display Main MainGui
 */
class MainGui extends JFrame{
    private JFrame frameHome;
    private JButton btnManageStudent, btnManageUnit ,btnManageClass, btnManageAssessment, btnManageExit;
    private MenuBar menubar = new MenuBar();

    MainGui(){
        displayFrameHome();
    }

    /**
     * Display home frame
     */
    private void displayFrameHome(){
        frameHome = new JFrame("Student Assessment Recording Application");
        frameHome.setSize(1000,700);
        frameHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Create menu bar
        menubar.displayMenuBar(frameHome);
        frameHome.setLayout(new GridLayout(1,2,0,0));
        //display left panel
        frameHome.add(new leftPanel());
        //display right panel
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

        leftPanel() {
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
            gbc.insets = new Insets(30,0,0,0);
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
            JTextArea textArea1 = new JTextArea(30, 40);
            textArea1.setLineWrap(true); //end then wrap to next line
            textArea1.setWrapStyleWord(true); //not split words
            textArea1.setEditable(false);
            JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.add(scrollbar1,gbc);
        }
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
                new StudentGUI();
            }
            else if(e.getSource() == btnManageUnit){
                System.out.println("Manage unit clicked");
                frameHome.setVisible(false);
                new UnitGUI();
            }
            else if(e.getSource() == btnManageClass){
                System.out.println("Manage class clicked");
                frameHome.setVisible(false);
                new ClassGUI();
            }
            else if(e.getSource() == btnManageAssessment){
                System.out.println("Manage assessment clicked");
                frameHome.setVisible(false);
                new AssessmentGUI();
            }
            else if(e.getSource() == btnManageExit){
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
        }
    }
}
