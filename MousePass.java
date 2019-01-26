
package mousepass;

import javax.swing.*;  

public class MousePass {


    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame  
          
        JButton yes = new JButton("Yes");//creating instance of JButton  
        yes.setBounds(50,50,200, 80);//x axis, y axis, width, height  

        f.add(yes);//adding button in JFrame  

        f.setSize(1500,750);//400 width and 500 height  
        f.setLayout(null);//using no layout managers  
        f.setVisible(true);//making the frame visible  
    }
    
}