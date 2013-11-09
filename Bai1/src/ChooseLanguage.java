import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChooseLanguage extends JPanel {

	JComboBox<String> cbLanguage;
	int init = 0;
	JTextField tfSay;
	String course[] = { "Viet Nam", "English", "France" };
	String course2[] = { "Xin Chào", "Hello", "Bonjour" };

	public ChooseLanguage() {

		JPanel pnCombo = new JPanel();
		JLabel lbLang = new JLabel("Language:");
		pnCombo.add(lbLang);
		cbLanguage = new JComboBox<>(course);
		cbLanguage.setBackground(Color.white);
		cbLanguage.setSelectedIndex(init);
		pnCombo.add(cbLanguage);

		JPanel pnTexfield = new JPanel();
		JLabel lbSay = new JLabel("Say:");
		pnTexfield.add(lbSay);
		tfSay = new JTextField(13);
		tfSay.setText(course2[init]);
		pnTexfield.add(tfSay);

		lbSay.setPreferredSize(lbLang.getPreferredSize());
		cbLanguage.setPreferredSize(tfSay.getPreferredSize());
		pnCombo.setBackground(Color.white);

		cbLanguage.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				init = cbLanguage.getSelectedIndex();
				tfSay.setText(course2[cbLanguage.getSelectedIndex()]);

			}

		});

		pnTexfield.setBackground(Color.white);
		this.add(pnCombo);
		this.add(pnTexfield);
		this.setBackground(Color.WHITE);

	}

	public static void main(String args[]) {

		JFrame jFrame = new JFrame("Multi language hello world");
		jFrame.add(new ChooseLanguage());

		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		jFrame.setIconImage(icon);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setSize(250, 110);
	}
}
