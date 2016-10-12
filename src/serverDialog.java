import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by jordan on 30/03/2016.
 */
public class serverDialog extends JDialog {
    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private Pattern pattern;
    private Matcher matcher;
    private JTextField mdp;
    private JTextField txt_pseudo;


    public serverDialog() {
        JPanel window = new JPanel();
        JPanel panel_pseudo = new JPanel();
        JPanel panel_mdp = new JPanel();
        JPanel panel_btn = new JPanel();
        panel_pseudo.setLayout(new FlowLayout());
        panel_mdp.setLayout(new FlowLayout());
        panel_btn.setLayout(new FlowLayout());

        txt_pseudo = new JTextField();
        txt_pseudo.setHorizontalAlignment(JTextField.CENTER);
        JLabel lbl_pseudo = new JLabel("Nom du poste :");
        JLabel lbl_mdp = new JLabel("Mot de passe :");
        lbl_pseudo.setHorizontalAlignment(JLabel.CENTER);
        lbl_mdp.setHorizontalAlignment(JLabel.CENTER);
        mdp = new JTextField();
        mdp.setHorizontalAlignment(JTextField.CENTER);
        JButton btn_connexion = new JButton("Se connecter");
        btn_connexion.addActionListener(this::btn_connexion_actionPerformed);

        txt_pseudo.setPreferredSize(new Dimension(195, 40));
        mdp.setPreferredSize(new Dimension(205, 40));
        btn_connexion.setPreferredSize(new Dimension(300, 70));

        panel_pseudo.add(lbl_pseudo);
        panel_pseudo.add(txt_pseudo);
        panel_mdp.add(lbl_mdp);
        panel_mdp.add(mdp);
        panel_btn.add(btn_connexion);

        //GridLayout grid = new GridLayout(3, 1);
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
        window.add(panel_pseudo);
        window.add(Box.createRigidArea(new Dimension(0, 20)));
        window.add(panel_mdp);
        window.add(Box.createRigidArea(new Dimension(0, 20)));
        window.add(panel_btn);

        this.setLayout(new BorderLayout());
        this.getContentPane().add(window, BorderLayout.CENTER);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.NORTH);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.SOUTH);
        this.getContentPane().add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.WEST);
        this.getContentPane().add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.EAST);
    }

    public void btn_connexion_actionPerformed(ActionEvent e) {
        String pwd = mdp.getText();
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        matcher = pattern.matcher(getIPAddress(pwd));
        if(!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Adresse IP non valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            this.dispose();
        }
    }

    public String getIPAddress(String message) {
        String ipAddress = "";
        System.out.println("Msg : "+message);

        for(int i=0; i<message.length(); i++) {
            if (message.charAt(i) == 'A' || message.charAt(i) == 'Z' || message.charAt(i) == '6') {
                ipAddress += '0';
            } else if (message.charAt(i) == 'E' || message.charAt(i) == 'R' || message.charAt(i) == '7') {
                ipAddress += '1';
            } else if (message.charAt(i) == 'T' || message.charAt(i) == 'Y' || message.charAt(i) == '8') {
                ipAddress += '2';
            } else if (message.charAt(i) == 'U' || message.charAt(i) == 'I' || message.charAt(i) == '9') {
                ipAddress += '3';
            } else if (message.charAt(i) == 'O' || message.charAt(i) == 'P' || message.charAt(i) == '1') {
                ipAddress += '4';
            } else if (message.charAt(i) == 'Q' || message.charAt(i) == 'S' || message.charAt(i) == '2') {
                ipAddress += '5';
            } else if (message.charAt(i) == 'D' || message.charAt(i) == 'F' || message.charAt(i) == '3') {
                ipAddress += '6';
            } else if (message.charAt(i) == 'G' || message.charAt(i) == 'H' || message.charAt(i) == '4') {
                ipAddress += '7';
            } else if (message.charAt(i) == 'J' || message.charAt(i) == 'K' || message.charAt(i) == '5') {
                ipAddress += '8';
            } else if (message.charAt(i) == 'L' || message.charAt(i) == 'M' || message.charAt(i) == 'W') {
                ipAddress += '9';
            } else if (message.charAt(i) == 'X' || message.charAt(i) == 'C' || message.charAt(i) == 'V') {
                ipAddress += '.';
            }
        }

        System.out.println("IP : " + ipAddress);
        return ipAddress;
    }

    public String getId() {
        return getIPAddress(mdp.getText()) ;
    }

    public String getPseudo() { return txt_pseudo.getText(); }
}


