import java.awt.image.RGBImageFilter;

class BrightnessFilter extends RGBImageFilter {

	int bright;

	public BrightnessFilter(int bright) {
		this.bright = bright;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		// Trich ra cac thanh phan R,G,B va alpha
		int alpha = (rgb & 0xff000000);
		int red = (rgb & 0xff0000) >> 16;
		int green = (rgb & 0x00ff00) >> 8;
		int blue = (rgb & 0x0000ff);

		// Dieu chinh cuong do sang
		red = red + bright;
		if (red > 255)
			red = 255;
		if (red < 0)
			red = 0;

		green = green + bright;
		if (green > 255)
			green = 255;
		if (green < 0)
			green = 0;

		blue = blue + bright;
		if (blue > 255)
			blue = 255;
		if (blue < 0)
			blue = 0;

		return (alpha) + (red << 16) + (green << 8) + blue;
	}

}