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
 * Display manage unit GUI
 */
class UnitGUI extends JFrame {
    private JFrame frameUnit;
    private MenuBar menubar = new MenuBar();
    private JTextArea textArea1;
    private String infoOnComponent = "";
    private static  ArrayList<Unit> unitList = new ArrayList<>();
    private JButton btnUnitCreate, btnUnitView, btnUnitEdit, btnUnitDelete, btnUnitBack;

    /**
     * Constructor that used to display unit frame when manage unit button is clicked
     */
    UnitGUI(){
        displayUnitFrame();
    }

    /**
     * Display unit frame
     */
    private void displayUnitFrame() {
        frameUnit = new JFrame("Student Assessment Recording Application");
        frameUnit.setSize(1000,700);
        //Create menu bar
        menubar.displayMenuBar(frameUnit);
        frameUnit.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameUnit.setLayout(new GridLayout(1,2,0,0));
        frameUnit.add(new unitPanel());
        frameUnit.add(new unitRightPanel());
        displayUnitList(); //display unit list in text area
        ListenForWindow lForWindow = new ListenForWindow();
        frameUnit.addWindowListener(lForWindow);
        frameUnit.setLocationRelativeTo(null);
        frameUnit.setVisible(true);
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

    /**
     * Delete unit
     */
    private void deleteUnit() {
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
                    JOptionPane.showMessageDialog(this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
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
    private void editUnit() {
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
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Unit Information", JOptionPane.WARNING_MESSAGE);
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
                        JOptionPane.showMessageDialog(this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
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
    private void viewUnit() {
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
                JOptionPane.showMessageDialog(this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent = "";
            }
        }
    }

    /**
     * Display dialog box for create unit
     */
    private void createUnit() {
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
                JOptionPane.showMessageDialog(this, infoOnComponent, "Unit Information", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, infoOnComponent, "Unit Information", JOptionPane.INFORMATION_MESSAGE);
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
     * write unit file
     */
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
     * Display right panel for unit frame
     */
    private class unitRightPanel extends JPanel {
        unitRightPanel(){
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
     * Unit frame button listener
     */
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnUnitCreate){
                System.out.println("Create unit clicked");
                createUnit();
            }
            else if(e.getSource() == btnUnitView){
                System.out.println("View unit clicked");
                viewUnit();
            }
            else if(e.getSource() == btnUnitEdit){
                System.out.println("Edit unit clicked");
                editUnit();
            }
            else if(e.getSource() == btnUnitDelete){
                System.out.println("Delete unit clicked");
                deleteUnit();
            }
            else if(e.getSource() == btnUnitBack){
                System.out.println("Back button clicked");
                frameUnit.setVisible(false);
                new MainGui();
            }
        }
    }
}
