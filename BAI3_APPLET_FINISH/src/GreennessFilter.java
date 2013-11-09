import java.awt.image.RGBImageFilter;

class GreennessFilter extends RGBImageFilter {

	int mGreen;

	public GreennessFilter(int mGreen) {
		this.mGreen = mGreen;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		// Trich ra cac thanh phan R,G,B va alpha
		int alpha = (rgb & 0xff000000);
		int red = (rgb & 0xff0000) >> 16;
		int green = (rgb & 0x00ff00) >> 8;
		int blue = (rgb & 0x0000ff);

		green = green + mGreen;
		if (green > 255)
			green = 255;
		if (green < 0)
			green = 0;

		return (alpha) + (red << 16) + (green << 8) + blue;
	}

}
