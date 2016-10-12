import ViewerApp.Viewer;
import ViewerApp.ViewerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.*;


/**
 * Created by jordan on 22/03/2016.
 */
public class mainFrameServer {
    private static List<String> identifiants;
    private static List<String> pseudos;
    static List<Viewer> viewImpl;
    static List<JLabel> imageLabels;
    static boolean mouseIn;
    static boolean isMultiple = false;
    static JPanel vignette;


    public static void mainFrameServer()
    {
        identifiants = new ArrayList<String>();
        pseudos = new ArrayList<String>();
        viewImpl=new ArrayList<Viewer>();
        imageLabels = new ArrayList<JLabel>();
        getDialog();

        JFrame fr = new JFrame();
        fr.getContentPane().setLayout(new FlowLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Administration");
        fr.setLocation(100, 100);

        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
        KeyListener keylistener = new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if(isMultiple) {
                    for (Viewer v : viewImpl) {
                        v.keyPress(e.getKeyCode());
                    }
                }
                else
                    viewImpl.get(0).keyPress(e.getKeyCode());

            }


            public void keyReleased(KeyEvent e) {
                if(isMultiple) {
                    for (Viewer v : viewImpl) {
                        v.keyRelease(e.getKeyCode());
                    }
                }
                else
                    viewImpl.get(0).keyRelease(e.getKeyCode());
            }

            public void keyTyped(KeyEvent e) {
            }

        };

        MouseListener mouselistener = new MouseListener() {
            public void mouseClicked(MouseEvent e) {

                if(isMultiple) {
                    for (Viewer v : viewImpl) {
                        if (e.getButton() == MouseEvent.BUTTON1)
                            v.mouseClick();
                        else if (e.getButton() == MouseEvent.BUTTON3)
                            v.mouseRightClick();
                    }
                }
                else
                {
                    if (e.getButton() == MouseEvent.BUTTON1)
                        viewImpl.get(0).mouseClick();
                    else if (e.getButton() == MouseEvent.BUTTON3)
                        viewImpl.get(0).mouseRightClick();
                }

            }

            public void mousePressed(MouseEvent e) {
                if(isMultiple) {
                    for (Viewer v : viewImpl) {
                        if (e.getButton() == MouseEvent.BUTTON1)
                            v.mousePress();
                        else if (e.getButton() == MouseEvent.BUTTON3)
                            v.mouseRightPress();
                    }
                }
                else
                {
                    if (e.getButton() == MouseEvent.BUTTON1)
                        viewImpl.get(0).mousePress();
                    else if (e.getButton() == MouseEvent.BUTTON3)
                        viewImpl.get(0).mouseRightPress();
                }
            }

            public void mouseReleased(MouseEvent e) {
                if(isMultiple) {
                    for (Viewer v : viewImpl) {
                        if (e.getButton() == MouseEvent.BUTTON1)
                            v.mouseRelease();
                        else if (e.getButton() == MouseEvent.BUTTON3)
                            v.mouseRightRelease();
                    }
                }
                else
                {
                    if (e.getButton() == MouseEvent.BUTTON1)
                        viewImpl.get(0).mouseRelease();
                    else if (e.getButton() == MouseEvent.BUTTON3)
                        viewImpl.get(0).mouseRightRelease();
                }
            }

            public void mouseEntered(MouseEvent e) {
                setMouseIn(true);
            }
            public void mouseExited(MouseEvent e) {
                setMouseIn(false);
            }
        };

        boolean init = false;

        //Espion espion = new Espion(viewImpl,fr);

        JMenuBar menuBar;
        JMenu menuSession;
        JMenu menuGestion;
        JMenuItem menuItem1;
        JMenuItem menuItem2;
        JMenuItem menuItem3;
        ButtonGroup gestion;
        JRadioButtonMenuItem radioUnique;
        JRadioButtonMenuItem radioMultiple;

        //Menu
        menuBar = new JMenuBar();
        menuSession = new JMenu("Session");
        menuGestion = new JMenu("Gestion");
        menuItem1 = new JMenuItem("Nouvelle session");
        menuItem2 = new JMenuItem("Déconnexion");
        menuItem3 = new JMenuItem("Tout déconnecter");
        gestion = new ButtonGroup();
        radioUnique = new JRadioButtonMenuItem("Unique");
        radioMultiple = new JRadioButtonMenuItem("Multiple");
        gestion.add(radioUnique);
        gestion.add(radioMultiple);
        radioUnique.setSelected(true);

        radioMultiple.addActionListener(e -> btn_multiple(e));
        radioUnique.addActionListener(e -> btn_unique(e));

        menuItem1.addActionListener(e -> btn_session_actionPerformed(e));
        menuItem2.addActionListener(e -> btn_deconnexion_actionPerformed(e));
        menuItem3.addActionListener(e -> btn_tout_deconnecter_actionPerformed(e));

        menuSession.add(menuItem1);
        menuSession.addSeparator();
        menuSession.add(menuItem2);
        menuSession.add(menuItem3);
        menuGestion.add(radioUnique);
        menuGestion.add(radioMultiple);

        menuBar.add(menuSession);
        menuBar.add(menuGestion);
        fr.setJMenuBar(menuBar);
        JLabel imageLabel= new JLabel();
        imageLabel.setLayout(new BoxLayout(imageLabel, BoxLayout.LINE_AXIS));

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double height = dimension.getHeight();
        double width = dimension.getWidth();
        //double height_main = height*0.75;
        double height_little = height*0.15;

        ImageIcon image = getImage(0);
        Image new_img = image.getImage();//.getScaledInstance(-1, (int) height_main, Image.SCALE_SMOOTH)
        imageLabel.setIcon(new ImageIcon(new_img));

        // adding new image labels ---------------------
        for(int i=0; i<4; i++) {
            final int j = i;
            imageLabels.add(new JLabel());
            imageLabels.get(i).addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    Viewer sw = viewImpl.get(0);
                    viewImpl.set(0, viewImpl.get(j+1));
                    viewImpl.set(j+1, sw);

                    String p = pseudos.get(0);
                    pseudos.set(0,pseudos.get(j+1));
                    pseudos.set(j+1,p);
                }
            });
        }

        FlowLayout main_fl = new FlowLayout();
        JPanel main_panel = new JPanel();
        main_panel.setLayout(main_fl);
        main_panel.add(imageLabel);

        FlowLayout vignette_fl = new FlowLayout();
        vignette_fl.setHgap((int)(width*0.03));
        vignette= new JPanel();
        vignette.setLayout(vignette_fl);
        for(int i=0; i<4; i++) {
            vignette.add(imageLabels.get(i));
        }

        JPanel window = new JPanel();
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
        window.add(main_panel);
        window.add(vignette);

        fr.getContentPane().add(window);

        imageLabel.addMouseListener(mouselistener);
        //fr.getContentPane().add(imageLabel);
        textField.addKeyListener(keylistener);
        fr.getContentPane().add(textField, BorderLayout.NORTH);
        fr.pack();
        fr.setDefaultLookAndFeelDecorated(true);
        fr.setExtendedState(fr.MAXIMIZED_BOTH);
        //fr.setResizable(false);
        fr.setLocation(0, 0);
        fr.setVisible(true);

        while(true){
            int i=0;
            image = getImage(i);
            new_img = image.getImage();//.getScaledInstance(-1, (int) height_main, Image.SCALE_SMOOTH)
            imageLabel.setIcon(new ImageIcon(new_img));
            if (isMouseIn()) {
                //fr.setCursor((Cursor) Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(32, 32, BufferedImage.TRANSLUCENT), new Point(0, 0), "curseurInvisible"));
                int x = imageLabel.getMousePosition().x;
                int y = imageLabel.getMousePosition().y;
                viewImpl.get(i).mouseMove(x, y);
            }

            for(i=0;i<imageLabels.size();i++) {
                // adding new image labels ---------------------
                if(i+1<viewImpl.size()) {
                    image = getImage(i + 1);
                    new_img = image.getImage().getScaledInstance(-1, (int) height_little, Image.SCALE_SMOOTH);
                    image = new ImageIcon(new_img);
                    imageLabels.get(i).setIcon(image);

                    if(isMouseIn() && isMultiple) {
                        //fr.setCursor((Cuj'ai faimrsor) Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(32, 32, BufferedImage.TRANSLUCENT), new Point(0, 0), "curseurInvisible"));
                        int x = imageLabel.getMousePosition().x;
                        int y = imageLabel.getMousePosition().y;
                        viewImpl.get(i+1).mouseMove(x, y);
                    }
                }
                else {
                    imageLabels.get(i).setIcon(new ImageIcon());
                }
            }
        }
    }

    public static void setIdentifiant(String id, String pseudo) {

        identifiants.add(id);
        pseudos.add(pseudo);
        doConnections();
    }

    public static List<String> getIdentifiant() {
        return identifiants;
    }

    private static boolean isMouseIn()
    {
        return mouseIn;
    }

    private static void setMouseIn(boolean b)
    {
        mouseIn=b;
    }

    private static ImageIcon getImage(int i) {
        // lire l'image.
        try {
            byte img[] = viewImpl.get(i).view();
            InputStream in = new ByteArrayInputStream(img);
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedImage imageRecup = ImageIO.read(bis);

            Font font = new Font("Arial Black", Font.BOLD, 16);

            Graphics2D g = imageRecup.createGraphics();
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString(pseudos.get(i),12,24);
            g.dispose();
            ImageIcon im = new ImageIcon(imageRecup);
            return im;
        }catch (Exception ex) {
            System.out.println("ERROR : " + ex);
            ex.printStackTrace(System.out);
        }

        return null;
    }

    private static void getDialog()
    {
        serverDialog dlg = new serverDialog();
        dlg.setTitle("Identification");
        dlg.setResizable(false);
        dlg.pack();
        dlg.setLocation(350,150);
        dlg.setModal(true);
        dlg.setVisible(true);
        setIdentifiant(dlg.getId(),dlg.getPseudo());
    }

    public static void btn_session_actionPerformed(ActionEvent e) {
        getDialog();
    }

    public static  void btn_deconnexion_actionPerformed(ActionEvent e) {
        viewImpl.remove(0);
        if(viewImpl.size() == 0) {
            System.exit(1);
        }
    }

    public static void btn_tout_deconnecter_actionPerformed(ActionEvent e) {
        for(int i=0; i<viewImpl.size(); i++) {
            viewImpl.remove(i);
        }
        System.exit(1);
    }

    public static void btn_multiple(ActionEvent e) {
        isMultiple= true;
    }

    public static void btn_unique(ActionEvent e) {
        isMultiple= false;
    }


    public static void doConnections()
    {
        try {

            String args[] = {
                    "-ORBInitialPort",
                    "46293",
                    "-ORBInitialHost",
                    identifiants.get(identifiants.size()-1)
            };

            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "View";
            viewImpl.add(ViewerHelper.narrow(ncRef.resolve_str(name)));
            System.out.println("Obtained a handle on server object: " + viewImpl.get(viewImpl.size()-1));
        } catch (Exception ex) {
            System.out.println("ERROR : " + ex);
            ex.printStackTrace(System.out);
        }
        System.out.println("new connection : " + viewImpl.size());
    }
}