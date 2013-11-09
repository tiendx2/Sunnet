import java.awt.image.RGBImageFilter;

class ContrastnessFilter extends RGBImageFilter {

	int mContrast;

	public ContrastnessFilter(int mContrast) {
		this.mContrast = mContrast;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		// Trich ra cac thanh phan R,G,B va alpha
		int alpha = (rgb & 0xff000000);
		int red = (rgb & 0xff0000) >> 16;
		int green = (rgb & 0x00ff00) >> 8;
		int blue = (rgb & 0x0000ff);

		// Dieu chinh cuong do sang
		alpha = alpha + mContrast;
		if (alpha > 255)
			alpha = 255;
		if (red < 0)
			alpha = 0;

		return (alpha) + (red << 16) + (green << 8) + blue;
	}

}