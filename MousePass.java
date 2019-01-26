
package mousepass;

import javax.swing.*;

public class MousePass {


    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame

        JButton no = new JButton("No");//creating instance of JButton
        no.setBounds(50,50,200, 80);//x axis, y axis, width, height
        f.add(no);//adding button in JFrame

        JButton yes = new JButton("Yes");//creating instance of JButton
        yes.setBounds(1225,50,200, 80);//x axis, y axis, width, height
        f.add(yes);//adding button in JFrame

        JButton na = new JButton("N/A");//creating instance of JButton
        na.setBounds(650,50,200, 80);//x axis, y axis, width, height
        f.add(na);//adding button in JFrame

        f.setSize(1500,750);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

}
