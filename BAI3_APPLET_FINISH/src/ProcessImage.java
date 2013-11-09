import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RGBImageFilter;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProcessImage extends Applet {
	final int WIDTH_APP = 720;
	final int HEIGHT_APP = 550;
	Image img;
	Image newImage;

	Panel pnFun;
	JSlider sliderR, sliderG, sliderB, sliderCon, sliderBr, sliderAlpha;

	Button btn90, btn180, btn270, btnVertical, btnHorizon;
	Label lblRed, lblGreen, lblBlue, lblBright, lblContrast, lblRotate,
			lblFlip;

	GridBagLayout gb;
	GridBagConstraints gbc;

	public void init() {
		this.setSize(WIDTH_APP, HEIGHT_APP);
		this.setLayout(new BorderLayout());
		img = getImage(getDocumentBase(), "java.jpg");

		// Tao anh voi do sang binh thuong
		BrightnessFilter filt = new BrightnessFilter(0);
		newImage = createImage(new FilteredImageSource(img.getSource(), filt));

		pnFun = new Panel();
		gb = new GridBagLayout();
		pnFun.setLayout(gb);

		gbc = new GridBagConstraints();

		sliderR = getSlider(-120, 120, 0, 60, 12);
		sliderG = getSlider(-120, 120, 0, 60, 12);
		sliderB = getSlider(-120, 120, 0, 60, 12);
		sliderBr = getSlider(-120, 120, 0, 60, 12);
		sliderCon = getSlider(-120, 120, 0, 60, 12);

		lblRed = new Label("Red", Label.RIGHT);
		lblGreen = new Label("Green", Label.RIGHT);
		lblBlue = new Label("Blue", Label.RIGHT);
		lblBright = new Label("Bright", Label.RIGHT);
		lblContrast = new Label("Contrast", Label.RIGHT);
		lblRotate = new Label("Rotate", Label.RIGHT);
		lblFlip = new Label("Flip", Label.RIGHT);

		btn90 = new Button("90");
		btn180 = new Button("180");
		btn270 = new Button("270");
		btnVertical = new Button("Vertical");
		btnHorizon = new Button("Horizon");

		btnHorizon.addActionListener(new myEvent());
		btnVertical.addActionListener(new myEvent());
		btn90.addActionListener(new myEvent());
		btn180.addActionListener(new myEvent());
		btn270.addActionListener(new myEvent());

		// colum1
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblRed, 0, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblGreen, 1, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblBlue, 2, 0, 1, 1);

		// colum2
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(sliderR, 0, 1, 1, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(sliderG, 1, 1, 1, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(sliderB, 2, 1, 1, 2);

		// colum3
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblBright, 0, 3, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblContrast, 1, 3, 1, 1);

		// colum4
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(sliderBr, 0, 4, 1, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(sliderCon, 1, 4, 1, 2);

		// colum5
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblRotate, 0, 6, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(lblFlip, 1, 6, 1, 1);

		// colum6
		Panel pnRotate = new Panel();
		pnRotate.add(btn90);
		pnRotate.add(btn180);
		pnRotate.add(btn270);
		Panel pnFlip = new Panel();
		pnFlip.add(btnVertical);
		pnFlip.add(btnHorizon);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(pnRotate, 0, 7, 1, 3);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(pnFlip, 1, 7, 1, 3);

		this.add(pnFun, BorderLayout.SOUTH);

	}

	public void addComponent(Component comp, int row, int col, int nrow,
			int ncol) {
		gbc.gridx = col;
		gbc.gridy = row;

		gbc.gridwidth = ncol;
		gbc.gridheight = nrow;

		gb.setConstraints(comp, gbc);
		pnFun.add(comp, gbc);
	}

	public void paint(Graphics g) {
		// ve anh moi da qua bo loc de tang hay giam cuong do sang
		g.drawImage(newImage, 160, 20, this);
	}

	private void createPictrue(int bright, RGBImageFilter filt) {
		// TODO Auto-generated method stub
		// BrightnessFilter filter = (BrightnessFilter) filt;
		newImage = createImage(new FilteredImageSource(img.getSource(), filt));

	}

	public JSlider getSlider(int min, int max, int init, int mjrTkSp,
			int mnrTkSp) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(mjrTkSp);
		slider.setMinorTickSpacing(mnrTkSp);
		slider.setPaintLabels(false);
		slider.setBackground(Color.white);
		slider.addChangeListener(new SliderListener());
		return slider;
	}

	class SliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			JSlider slider = (JSlider) e.getSource();
			if (slider == sliderR) {
				int x = slider.getValue();
				RednessFilter filte = new RednessFilter(x);
				createPictrue(x, filte);
				repaint();
			} else if (slider == sliderG) {
				int x = slider.getValue();
				GreennessFilter filte = new GreennessFilter(x);
				createPictrue(x, filte);
				repaint();
			} else if (slider == sliderB) {
				int x = slider.getValue();
				BluenessFilter filte = new BluenessFilter(x);
				createPictrue(x, filte);
				repaint();
			} else if (slider == sliderBr) {
				int x = slider.getValue();
				BrightnessFilter filte = new BrightnessFilter(x);
				createPictrue(x, filte);
				repaint();
			} else if (slider == sliderCon) {
				int x = slider.getValue();
				ContrastnessFilter filte = new ContrastnessFilter(x);
				createPictrue(x, filte);
				repaint();
				System.out.println("Value Constrast=" + slider.getValue());
			}

		}

	}

	class myEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnHorizon) {
				processHorizon();
				System.out.println("Clicked btnHorizon");
			} else if (e.getSource() == btnVertical) {
				processVertical();
				System.out.println("Clicked btnVertical");
			} else if (e.getSource() == btn90) {
				processRotate90();
				System.out.println("Clicked btn90");
			} else if (e.getSource() == btn180) {
				processRotate180();
				System.out.println("Clicked btn180");
			} else if (e.getSource() == btn270) {
				processRotate270();
				System.out.println("Clicked btn270");
			}
		}

		private void processRotate270() {
			ImageProducer producer = img.getSource();
			int w = 400;
			int h = 178;
			int pixs[] = new int[w * h];
			PixelGrabber pg = new PixelGrabber(producer, 0, 0, w, h, pixs, 0, w);
			try {
				pg.grabPixels();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Error !");
				return;
			}

			int pixs2[] = new int[w * h];
			int i = 0, j;
			int w1 = h;
			int h1 = w;
			int[] B = new int[w * h];
			for (i = 0; i < h; i++) {
				for (j = 0; j < w; j++) {
					int i1 = h - 1 - i;
					pixs2[j * w1 + i] = pixs[i1 * w + j];
				}
				System.out.println("");
			}

			newImage = createImage(new MemoryImageSource(w1, h1, pixs2, 0, w1));
			repaint();
		}

		private void processRotate180() {
			ImageProducer producer = img.getSource();
			int w = 400;
			int h = 178;
			int pixs[] = new int[w * h];
			PixelGrabber pg = new PixelGrabber(producer, 0, 0, w, h, pixs, 0, w);
			try {
				pg.grabPixels();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Error !");
				return;
			}

			int pixs2[] = new int[w * h];
			int i, j;
			for (i = 0; i < h; i++) {
				for (j = 0; j < w; j++) {
					int i1 = h - i - 1;
					int j1 = w - j - 1;
					pixs2[i * w + j] = pixs[i1 * w + j1];
				}
			}

			newImage = createImage(new MemoryImageSource(w, h, pixs2, 0, w));
			repaint();
		}

		private void processRotate90() {
			// TODO Auto-generated method stub
			RotateFilter filt = new RotateFilter();
			newImage = createImage(new FilteredImageSource(img.getSource(),
					filt));
			repaint();
		}

		private void processVertical() {
			ImageProducer producer = img.getSource();
			int w = 400;
			int h = 178;
			int pixs[] = new int[w * h];
			PixelGrabber pg = new PixelGrabber(producer, 0, 0, w, h, pixs, 0, w);
			try {
				pg.grabPixels();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Error !");
				return;
			}

			int pixs2[] = new int[w * h];
			int i, j;
			for (i = 0; i < h; i++) {
				for (j = 0; j < w; j++) {
					int i1 = h - i - 1;
					int j1 = j;
					pixs2[i * w + j] = pixs[i1 * w + j1];
				}
			}

			newImage = createImage(new MemoryImageSource(w, h, pixs2, 0, w));
			repaint();
		}

		private void processHorizon() {
			// TODO Auto-generated method stub
			ImageProducer producer = img.getSource();
			int w = 400;
			int h = 178;
			int pixs[] = new int[w * h];
			PixelGrabber pg = new PixelGrabber(producer, 0, 0, w, h, pixs, 0, w);
			try {
				pg.grabPixels();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Error !");
				return;
			}

			int pixs2[] = new int[w * h];
			int i, j;
			for (j = 0; j < w; j++) {
				for (i = 0; i < h; i++) {
					int i1 = i;
					int j1 = w - j - 1;
					pixs2[i * w + j] = pixs[i1 * w + j1];
				}
			}

			newImage = createImage(new MemoryImageSource(w, h, pixs2, 0, w));
			repaint();
		}

	}

}
