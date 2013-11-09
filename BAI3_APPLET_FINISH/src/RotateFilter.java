import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageFilter;

public class RotateFilter extends ImageFilter {
	public RotateFilter() {

	}

	/*
	 * Neu du lieu duoc chuyen den theo dang COMPLETESCANLINES hay
	 * TOPDOWNLEFTRIGHT thi khong chuyen giao ngay ma can cho de qua xu ly
	 */
	public void setHints(int hints) {
		consumer.setHints(hints
				& -(ImageConsumer.COMPLETESCANLINES + ImageConsumer.TOPDOWNLEFTRIGHT));
	}

	/*
	 * Hoan doi kich thuoc anh chieu rong thanh chieu cao va chieu cao thanh
	 * chieu rong
	 */
	public void setDimensions(int width, int height) {
		consumer.setDimensions(height, width);
	}

	public void setPixels(int x, int y, int width, int height,
			ColorModel model, byte[] pixels, int offset, int scansize) {
		//Tao mot day byte moi de sao chep du lieu do Producer chuyen den
		byte[] rotatePixels = new byte[pixels.length];
		for(int ry=0;ry<height;ry++){
			for(int rx=0;rx<width;rx++){
				//Sao chep cac pixel va hoan chuyen vi tri x,y
				rotatePixels[rx*height+ry]=pixels[(ry+1)*scansize-rx-1+offset];
			}
		}
		// chuyen giao lai cac pixel da bi hoan chuyen cho Consumer xu ly tiep
		consumer.setPixels(y, x, height, width, model, rotatePixels, 0, height);
		
	}
	
	public void setPixels(int x, int y, int width, int height,
			ColorModel model, int[] pixels, int offset, int scansize) {
		//Tao mot day int moi de sao chep du lieu do Producer chuyen den
		int[] rotatePixels = new int[pixels.length];
		for(int ry=0;ry<height;ry++){
			for(int rx=0;rx<width;rx++){
				//Sao chep cac pixel va hoan chuyen vi tri x,y
				rotatePixels[rx*height+ry]=pixels[(ry+1)*scansize-rx-1+offset];
			}
		}
		// chuyen giao lai cac pixel da bi hoan chuyen cho Consumer xu ly tiep
		consumer.setPixels(y, x, height, width, model, rotatePixels, 0, height);
		
	}

}
