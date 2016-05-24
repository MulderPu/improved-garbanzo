import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.MouseInfoPeer;

/**
 * Project Name : improved-garbanzo
 * Created by Mulder on 5/24/2016.
 *
 * Mouse Listener
 */
public class ListenForMouse implements MouseListener{
    /**
     * Called when the mouse is clicked.
     * @param e stored the mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X-Axis: " + e.getX() + "  Y-Axis: " + e.getY());
        System.out.println("Mouse Clicked:" + e.getClickCount());
    }

    /**
     * Mouse button had been pressed.
     * @param e stored the mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Mouse button had been released.
     * @param e stored the mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Mouse entered.
     * @param e stored the mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Mouse Exited
     * @param e stored the mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
