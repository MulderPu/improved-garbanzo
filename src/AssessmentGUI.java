import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/9/2016.
 */
public class AssessmentGUI extends JFrame{
    private MenuBar menubar = new MenuBar();
    private JButton btnAssCreate, btnAssView, btnAssEdit, btnAssDelete, btnAssBack;
    private JFrame frameAss = new JFrame();

    public AssessmentGUI(){
        displayAssFrame();
    }

    public void displayAssFrame(){
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

    private void displayAssList() {

    }

    private class AssRightPanel extends JPanel {
        public AssRightPanel() {
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

    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAssBack){
                System.out.println("Back button clicked");
                frameAss.setVisible(false);
                new MainGui();
            }
        }
    }
}
