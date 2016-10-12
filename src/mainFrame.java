import ViewerApp.Viewer;
import ViewerApp.ViewerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by jordan on 30/03/2016.
 */
public class mainFrame extends JDialog {
    private static Viewer viewImpl;
    private boolean isadmin;
    private boolean isnotadmin;
    public mainFrame() {
        isadmin=false;
        isnotadmin=false;
        JPanel window = new JPanel();
        JButton btn_admin = new JButton("Administrer un poste distant");
        JButton btn_client = new JButton("Etre administré");
        btn_admin.addActionListener(this::btn_admin_actionPerformed);
        btn_client.addActionListener(this::btn_client_actionPerformed);

        btn_admin.setMaximumSize(new Dimension(250, 40));
        btn_client.setPreferredSize(new Dimension(400, 100));

        GridLayout grid = new GridLayout(3, 1);
        window.setLayout(grid);
        window.add(btn_admin);
        window.add(Box.createRigidArea(new Dimension(0, 10)));
        window.add(btn_client);

        this.setLayout(new BorderLayout());
        this.getContentPane().add(window, BorderLayout.CENTER);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.NORTH);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.SOUTH);
        this.getContentPane().add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.WEST);
        this.getContentPane().add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.EAST);
    }

    public void btn_client_actionPerformed(ActionEvent e) {
        Runtime runtime = Runtime.getRuntime();
        if (System.getProperty("os.name").startsWith("Windows")) { //Sytème Windows
            try {
                runtime.exec(new String[]{"cmd /c start orbd","-ORBInitialPort", "46293"});
            } catch (Exception exp) {
                System.err.println(exp.getMessage());
            }
        } else { //Tous les autres systèmes
            try {
                runtime.exec(new String[]{"orbd","-ORBInitialPort", "46293"});
                System.out.println("done");
            } catch (Exception exp) {
                System.err.println(exp.getMessage());
            }
        }

        this.isnotadmin=true;
        this.dispose();

    }

    public void btn_admin_actionPerformed(ActionEvent e) {

        this.isadmin=true;
        this.dispose();
        /*
        Runtime runtime = Runtime.getRuntime();

        String cmd = "java ViewerClient -ORBInitialPort 46293 -ORBInitialHost " + serverFrame.getIdentifiant();
        try {
            runtime.exec(cmd);
        } catch (Exception exp) {
            System.err.println(exp.getMessage());
        }*/

    }

    public boolean isadmin()
    {
        return this.isadmin;
    }
    public boolean isnotadmin()
    {
        return this.isnotadmin;
    }
}


