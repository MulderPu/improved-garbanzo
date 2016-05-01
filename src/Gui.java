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
    JButton btnManageStudent;

    public Gui(){
        //Create menu bar
        createMenuBar();
        this.setTitle("Student Assessment Recording Application");
        this.setSize(1000,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,2,10,0));
        JPanel thePanel = new JPanel();

        //create a button
        btnManageStudent = new JButton("Get Answer");
        ListenForButton lForButton = new ListenForButton();
        btnManageStudent.addActionListener(lForButton);
        thePanel.add(btnManageStudent);

        this.add(thePanel);

        JLabel label2 = new JLabel("Area Text show here.");
        add(label2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    public class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnManageStudent){

            }
        }
    }

    public void createMenuBar(){
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
        this.setJMenuBar(menuBar);
    }
}
