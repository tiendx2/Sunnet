import java.awt.image.RGBImageFilter;

class BluenessFilter extends RGBImageFilter {

	int mBlue;

	public BluenessFilter(int mBlue) {
		this.mBlue = mBlue;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		// Trich ra cac thanh phan R,G,B va alpha
		int alpha = (rgb & 0xff000000);
		int red = (rgb & 0xff0000) >> 16;
		int green = (rgb & 0x00ff00) >> 8;
		int blue = (rgb & 0x0000ff);

		blue = blue + mBlue;
		if (blue > 255)
			blue = 255;
		if (blue < 0)
			blue = 0;

		return (alpha) + (red << 16) + (green << 8) + blue;
	}

}