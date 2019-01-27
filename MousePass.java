package mousepass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class MousePass extends JFrame implements ActionListener {

    public static void main(String[] args) throws InterruptedException {
        // Creates frame and buttons
        JFrame f = new JFrame();//creating instance of JFrame

        JButton start = new JButton("Start");
        start.setBounds(650,400,200, 80);//x axis, y axis, width, height
        start.setFont(new Font("Arial", Font.BOLD, 40));
        f.add(start);//adding button in JFrame

        f.setSize(1500,750);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible


        JButton no = new JButton("No");//creating instance of JButton
        no.setBounds(50,50,200, 80);//x axis, y axis, width, height
        no.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(no);//adding button in JFrame

        JButton yes = new JButton("Yes");//creating instance of JButton
        yes.setBounds(1225,50,200, 80);//x axis, y axis, width, height
        yes.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(yes);//adding button in JFrame

        JButton na = new JButton("N/A");//creating instance of JButton
        na.setBounds(650,50,200, 80);//x axis, y axis, width, height
        na.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(na);//adding button in JFrame

        // Button action listeners
        start.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                start.setVisible(false);
                no.setVisible(true);
                yes.setVisible(true);
                na.setVisible(true);
              }
            }
          );

    }

    public void actionPerformed(ActionEvent e) {}

    static int[] mousePos() {
        // Returns array containing mouse position
        Point p = MouseInfo.getPointerInfo().getLocation();
        return new int[]{p.x, p.y};
    }

}
