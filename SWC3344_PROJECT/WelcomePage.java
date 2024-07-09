/* 
 * STUDENT NAME: NUR IZZAH BINTI BURHANUDDIN  
 * STUDENT NAME: NUR ATHIRAH BINTI HILALLUDDIN 
 * STUDENT ID: AM2307013907
 * STUDENT ID: AM2307013911
 * LECTURER NAME: MADAM FARAH FARZANA BINTI ABDUL AZIZ
 * PROGRAM DESCRIPTION: TO CREATE WELCOME PAGE FRAME AND SWICH TO MAIN RESTAURANT SELECTION FRAME
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame implements ActionListener
{
    JButton startButton;
    
    //constructor without parameter
    public WelcomePage()
    {
        //title for reservation
        setTitle("Welcome to 3 fingers licking good!");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //image icon logo
        ImageIcon sign = new ImageIcon("3 fingers.png");
        JLabel signlabel = new JLabel (sign);
        signlabel.setIcon(sign);
        
        //panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
        
        //start button
        startButton = new JButton ("START");
        startButton.addActionListener(this); //register to a listener
        panel.add(startButton, BorderLayout.SOUTH);
        
        //set the bounds of the other stuf
        startButton.setBounds(200, 250, 100, 90);
        panel.add(signlabel);
        
        setVisible(true);
    }//end of constructorr
    
    //method overriding
    public void actionPerformed(ActionEvent z)
    {
        if (z.getSource() == startButton)
        {
            MainRestaurant destination = new MainRestaurant ();
            dispose();
        }//end of if
    }//end of method
    
}//end of class