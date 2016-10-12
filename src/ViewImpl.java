/**
 * Created by romainhry on 23/03/2016.
 */

import ViewerApp.ViewerPOA;
import org.omg.CORBA.ORB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class ViewImpl extends ViewerPOA {

    private double ratio =0.65;
    private Robot robot;

    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
        try {
            robot=new Robot();
        } catch (AWTException e) {
        }

    }

    // implement sayHello() method
    public byte[] view() {




        Image cursor = null;

        try {
            cursor = ImageIO.read(new File("./cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataBufferByte data;

        BufferedImage screenCapture= robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));


        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;

        Graphics2D graphics2D = screenCapture.createGraphics();
        graphics2D.drawImage(cursor, x, y, 11, 16, null);

        ImageIcon icon = new ImageIcon(screenCapture);

        ImageIcon lasticon = new ImageIcon(icon.getImage().getScaledInstance((int)(screenCapture.getWidth()*ratio), (int)(screenCapture.getHeight()*ratio),Image.SCALE_SMOOTH));

        Image img = lasticon.getImage();

        /** On crée la nouvelle image */
        BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_BGR );
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(img,0,0,null);
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        byte buffer[] = out.toByteArray();

        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (buffer);

    }



    public void mouseMove(int x,int y)
    {
        robot.mouseMove((int)(x/ratio),(int)(y/ratio));
    }

    public void mouseClick()
    {
    }

    public void mousePress()
    {
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public void mouseRelease()
    {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }


    public void mouseRightClick()
    {
        robot.mousePress(InputEvent.BUTTON3_MASK);

    }

    public void mouseRightPress()
    {
        //robot.mousePress(InputEvent.BUTTON3_MASK);
    }

    public void mouseRightRelease()
    {
        //robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }



    public void keyClick(int code)
    {
    }

    public void keyPress(int code)
    {

        code=getCode(code);
        System.out.println((char)code);
        robot.keyPress(code);
    }

    public int getCode(int code)
    {
        if((char)code=='A')
            code='Q';
        else if ((char)code=='Z')
            code='W';
        else if ((char)code=='Q')
            code='A';
        else if ((char)code=='W')
            code='Z';
        else if ((char)code=='M')
            code=';';
        else if ((char)code==';')
            code='M';
        else if ((char)code==':')
            code='.';
        return code;
    }

    public void keyRelease(int code)
    {
        code=getCode(code);

        robot.keyRelease(code);
    }

    // implement shutdown() method
    public void shutdown() {
        orb.shutdown(false);
    }
}
