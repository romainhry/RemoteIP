import javax.swing.*;

/**
 * Created by jordan on 22/03/2016.
 */
public class main {
    public static mainFrame frame = new mainFrame();

    public static void main(String[] args) {

        frame.setTitle("RemoteIP");
        frame.setLocation(300, 100);
        frame.setResizable(false);
        frame.setModal(true);
        frame.pack();
        frame.setVisible(true);
        if (frame.isadmin())
            mainFrameServer.mainFrameServer();
        else if (frame.isnotadmin())
        {
            mainFrameClient.mainFrameClient();
        }

        System.exit(0);


        // System.out.println(System.getProperty("os.name"));
    }
}
