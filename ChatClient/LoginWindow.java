
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

class LoginWindow extends JFrame {
    private final ChatClient client;
    JLabel lb1;
    JLabel lb2;
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public LoginWindow() {

        this.client = new ChatClient("localhost", 8818);
        client.connect();
        JFrame f = new JFrame("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lb1=new JLabel("username");lb1.setBounds(50,100,80,30); f.add(lb1);
        lb2=new JLabel("password");lb2.setBounds(50,200,80,30); f.add(lb2);
        f.add(loginField);
        loginField.setBounds(150,100,80,30);    
        f.add(passwordField);
        passwordField.setBounds(150,200,80,30);   
        f.add(loginButton);
        loginButton.setBounds(100,300,80,30);  
        
         f.setSize(400,400); 
         f.setLayout(null); 
           f.setVisible(true); 
           
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false); 
                doLogin();
                
            }
        });
    }

    private void doLogin()
     {
        String login = loginField.getText();
        String password = passwordField.getText();

        try {

            if ( client.login(login, password))
            {
                UserListPane userListPane = new UserListPane(client);
                JFrame frame = new JFrame(login);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);

                frame.getContentPane().add(userListPane, BorderLayout.CENTER);
                frame.setVisible(true);

                setVisible(false);
            } 
            else
             {
                JOptionPane.showMessageDialog(this, "Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public static void main(String[] args) 
    {
        LoginWindow loginWin = new LoginWindow();
        loginWin.setVisible(true);
    }
}


