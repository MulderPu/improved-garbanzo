import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/9/2016.
 *
 * Window listener
 */
public class ListenForWindow implements WindowListener{
    /**
     * Window is created.
     * @param e for events happening when window is opened
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Whenever the window is close from the main frame.
     * @param e stored the event of the window when it is closing
     */
    @Override
    public void windowClosing(WindowEvent e) {
        //show dialog box to receive confirmation from the user
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

    /**
     * //this.displose()
     * Called whenever the window is closed.
     *
     * @param e stored the events of the window
     */
    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Window closed.");
    }

    /**
     * Called when the window is minimized.
     *
     * @param e stored the event for the window.
     */
    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Window is minimized.");
    }

    /**
     * Called when window is not in a normal state.
     *
     * @param e stored the event for window.
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Window is not normal.");
    }

    /**
     * Called whenever the window is activated.
     * @param e stored the event of the window.
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * Called whenever the window is de-activated.
     *
     * @param e stored the event of the window.
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
