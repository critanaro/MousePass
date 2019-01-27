
package mousepass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.util.*;

public class MousePass extends JFrame implements ActionListener {

    static int numQuestions = 20;
    static Random rand = new Random();

    public static void main(String[] args) throws InterruptedException, Exception {
        // Create interface
        JFrame f = new JFrame();//creating instance of JFrame

        // Add starting text
        JLabel labelBegin = new JLabel("Click the button below to login to your account", JLabel.CENTER);
        labelBegin.setFont(new Font("Arial", Font.ITALIC, 40));
        labelBegin.setSize(1500, 400);
        labelBegin.setLocation(5, 5);
        f.add(labelBegin);

        f.setSize(1500,750);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        JButton start = new JButton("Start");
        start.setBounds(650,400,200, 80);//x axis, y axis, width, height
        start.setFont(new Font("Arial", Font.BOLD, 40));
        f.add(start);//adding button in JFrame

        JButton no = new JButton("No");//creating instance of JButton
        no.setBounds(50,50,200, 80);//x axis, y axis, width, height
        no.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton yes = new JButton("Yes");//creating instance of JButton
        yes.setBounds(1225,50,200, 80);//x axis, y axis, width, height
        yes.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton na = new JButton("N/A");//creating instance of JButton
        na.setBounds(650,50,200, 80);//x axis, y axis, width, height
        na.setFont(new Font("Arial", Font.PLAIN, 20));

        f.add(no);//adding button in JFrame
        f.add(yes);//adding button in JFrame
        f.add(na);//adding button in JFrame

        no.setVisible(false);
        yes.setVisible(false);
        na.setVisible(false);

        // Read in questions
        String[] questions = readFile("questions.txt");
        boolean[] asked = new boolean[numQuestions];

        // Add question text
        JLabel labelQuestion = new JLabel("[Question]", JLabel.CENTER);
        labelQuestion.setFont(new Font("Arial", Font.PLAIN, 30));
        labelQuestion.setSize(1500, 900);
        labelQuestion.setLocation(5, 5);
        f.add(labelQuestion);
        labelQuestion.setVisible(false);


        // Button action listeners
        start.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                start.setVisible(false);
                no.setVisible(true);
                yes.setVisible(true);
                na.setVisible(true);

                //Generate question
                int toAsk = rand.nextInt(numQuestions);
                while(asked[toAsk]) toAsk = rand.nextInt(numQuestions);
                asked[toAsk] = true;
                labelQuestion.setText(questions[toAsk]);
                labelQuestion.setVisible(true);

                labelBegin.setText("");

                // Add instruction text
                JLabel labelInstr = new JLabel("Answer the following questions to access your account", JLabel.CENTER);
                labelInstr.setFont(new Font("Arial", Font.PLAIN, 25));
                labelInstr.setSize(1500, 1200);
                labelInstr.setLocation(5, 5);
                f.add(labelInstr);
              }
            }
          );

        ArrayList<int[]> mouseRecord = new ArrayList<int[]>();
        int x = 0;
        while (x<1000)
        {
        	mouseRecord.add(mousePos());
        	Thread.sleep(10);
        	x++;
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
        		new FileOutputStream("inputdata.txt"), "utf-8"))) 
        {
        	for (int[]points:mouseRecord)
        	{
        		writer.write(points[0]+ ", " + points[1] + "\n");
        	}        	
        } catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }

    public void actionPerformed(ActionEvent e) {}

    static String[] readFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String[] contents = new String[numQuestions];
        for(int i=0; i<numQuestions; i++) contents[i] = br.readLine();
        return contents;
    }

    static int[] mousePos() {
        // Returns array containing mouse position
        Point p = MouseInfo.getPointerInfo().getLocation();
        return new int[]{p.x, p.y};
    }

}
