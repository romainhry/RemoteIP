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
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by jordan on 30/03/2016.
 */
public class mainFrameClient {
    public static void mainFrameClient() {
        JFrame fr = new JFrame();
        JPanel window = new JPanel();
        JLabel mdp = new JLabel();
        JTextField motDePasse = new JTextField();
        motDePasse.setText(getClientText());
        motDePasse.setEditable(false);
        motDePasse.setHorizontalAlignment(JTextField.CENTER);
        motDePasse.setPreferredSize(new Dimension(200, 50));
        mdp.setHorizontalAlignment(JTextField.CENTER);
        mdp.setText("Mot de passe :");

        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
        window.add(mdp);
        window.add(Box.createRigidArea(new Dimension(0, 3)));
        window.add(motDePasse);

        fr.setLayout(new BorderLayout());
        fr.getContentPane().add(window, BorderLayout.CENTER);
        fr.getContentPane().add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.NORTH);
        fr.getContentPane().add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.SOUTH);
        fr.getContentPane().add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.WEST);
        fr.getContentPane().add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.EAST);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Paramètres");
        fr.setLocation(400, 220);
        fr.pack();
        fr.setVisible(true);
        try {
            String args[] = {
                    "-ORBInitialPort",
                    "46293"
            };
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            ViewImpl viewImpl = new ViewImpl();
            viewImpl.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(viewImpl);
            Viewer href = ViewerHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = "View";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("viewServer ready and waiting ...");

            // wait for invocations from clients
            while (true) {
                orb.run();
            }
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex);
            ex.printStackTrace(System.out);
        }
        System.out.println("HelloServer Exiting ...");
    }

    public static String getClientText() {
        Random rand = new Random();
        String motDePasse = "";
        String ipAddress = "";
        try {
            ipAddress = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("IP : "+ipAddress);
        for(int i=0; i<ipAddress.length(); i++) {
            int randomNumber;
            switch(ipAddress.charAt(i)) {
                case '0' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'A';
                            break;
                        case 1 : motDePasse += 'Z';
                            break;
                        case 2 : motDePasse += '6';
                            break;
                    }
                    break;
                case '1' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'E';
                            break;
                        case 1 : motDePasse += 'R';
                            break;
                        case 2 : motDePasse += '7';
                            break;
                    }
                    break;
                case '2' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'T';
                            break;
                        case 1 : motDePasse += 'Y';
                            break;
                        case 2 : motDePasse += '8';
                            break;
                    }
                    break;
                case '3' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'U';
                            break;
                        case 1 : motDePasse += 'I';
                            break;
                        case 2 : motDePasse += '9';
                            break;
                    }
                    break;
                case '4' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'O';
                            break;
                        case 1 : motDePasse += 'P';
                            break;
                        case 2 : motDePasse += '1';
                            break;
                    }
                    break;
                case '5' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'Q';
                            break;
                        case 1 : motDePasse += 'S';
                            break;
                        case 2 : motDePasse += '2';
                            break;
                    }
                    break;
                case '6' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'D';
                            break;
                        case 1 : motDePasse += 'F';
                            break;
                        case 2 : motDePasse += '3';
                            break;
                    }
                    break;
                case '7' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'G';
                            break;
                        case 1 : motDePasse += 'H';
                            break;
                        case 2 : motDePasse += '4';
                            break;
                    }
                    break;
                case '8' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'J';
                            break;
                        case 1 : motDePasse += 'K';
                            break;
                        case 2 : motDePasse += '5';
                            break;
                    }
                    break;
                case '9' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'L';
                            break;
                        case 1 : motDePasse += 'M';
                            break;
                        case 2 : motDePasse += 'W';
                            break;
                    }
                    break;
                case '.' :
                    randomNumber = rand.nextInt(3);
                    switch(randomNumber) {
                        case 0 : motDePasse += 'X';
                            break;
                        case 1 : motDePasse += 'C';
                            break;
                        case 2 : motDePasse += 'V';
                            break;
                    }
                    break;
            }
        }
        System.out.println("MDP : " + motDePasse);
        return motDePasse;
    }
}