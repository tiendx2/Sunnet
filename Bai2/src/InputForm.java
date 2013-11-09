import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class InputForm extends JPanel{
	private JLabel lblA,lblB,lblC;
	private JTextField tfA,tfB,tfC;
	JButton btnDraw;
	
	// Ham kiem tra hop le textField
	private boolean check(String a){
		try {
			float s=Float.parseFloat(a);
			return true;
		} catch(Exception e){
			return false;
		}
	}
	public InputForm(){
		lblA = new JLabel("  a =");
		lblB = new JLabel("  b =");
		lblC = new JLabel("  c =");
		
		tfA = new JTextField(6);
		tfB = new JTextField(6);
		tfC = new JTextField(6);
		
		btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String strA=tfA.getText().toString();
				String strB=tfB.getText().toString();
				String strC=tfC.getText().toString();
				if(check(strA)==true&&check(strA)==true&&check(strA)==true){
					float numA=Float.parseFloat(strA);
					float numB=Float.parseFloat(strB);
					float numC=Float.parseFloat(strC);
					
					JFrame frGraph = new JFrame("Do thi ham so");
					frGraph.getContentPane().add(new Graph(numA,numB,numC));
					frGraph.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frGraph.setLocation(200,200);
					frGraph.setSize(Graph.WIDTH_FR,Graph.HEIGHT_FR);
					frGraph.setVisible(true);
				}else {
					 JOptionPane.showMessageDialog(null,"Nhap tham so khong hop le !");
				}
				
			}
		});
		
		this.setLayout(new GridLayout(1,3));
		
		JPanel pnLabel = new JPanel(new GridLayout(3,1));
		pnLabel.add(lblA);
		pnLabel.add(lblB);
		pnLabel.add(lblC);
		pnLabel.setBackground(Color.white);
		
		JPanel pnText = new JPanel(new GridLayout(3,1));
		pnText.add(tfA);
		pnText.add(tfB);
		pnText.add(tfC);
		
		pnText.setBackground(Color.white);
		
		JPanel pnButton = new JPanel(new GridLayout(3,1));
		pnButton.add(btnDraw);
		pnButton.setBackground(Color.white);
		
		this.add(pnLabel);
		this.add(pnText);
		this.add(pnButton);
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setBackground(Color.WHITE);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tham so ham bac 2");
		frame.getContentPane().add(new InputForm());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
