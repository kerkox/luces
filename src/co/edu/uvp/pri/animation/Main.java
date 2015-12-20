package co.edu.uvp.pri.animation;

/**
 *
 * @author zeus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new edu.uvpalmira.pri.eval.pt01.Application().setVisible(true);
            }
        });
    }

}
