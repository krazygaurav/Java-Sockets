import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chat extends JFrame implements ActionListener{
	JTextField msgText;
	JTextArea msgArea;
	JScrollPane jsp;
	JButton send;
	JLabel name;
	JPanel top,center,bottom;

	public Chat(){
		super("Chat");
		initGui();
	}
	public void initGui(){
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		top = new JPanel();
		top.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		center = new JPanel(new BorderLayout());
		center.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		bottom = new JPanel(new FlowLayout());
		bottom.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		name = new JLabel("Gaurav");
		top.add(name);

		msgText = new JTextField(20);

		msgArea = new JTextArea();
		msgArea.setEditable(false);
		jsp = new JScrollPane(msgArea);
		//center.setBackground(Color.RED);
		msgText.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		msgText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//JOptionPane.showMessageDialog(null, "Written");
				String s = msgText.getText();
				msgText.setText("");
				msgArea.append("\n"+s);
			}
		});
		send = new JButton(">");
		send.addActionListener(this);
		bottom.add(msgText);
		bottom.add(send);

		center.add(jsp, BorderLayout.CENTER);
		this.add(top, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		this.setSize(320,450);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		
	}
	public static void main(String[] args){
		new Chat();
	}
}