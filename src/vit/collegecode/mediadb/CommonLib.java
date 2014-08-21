package vit.collegecode.mediadb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class CommonLib {
	
	public static Bitmap createCenterCroppedImage(Bitmap bmp, int reqHeight,
			int reqWidth) {
		// if(bmp.getHeight() > bmp.getWidth()) {
		int xdiff = bmp.getWidth() - reqWidth;
		int ydiff = bmp.getHeight() - reqHeight;
		if (xdiff >= 0 && ydiff >= 0) {
			bmp = Bitmap.createBitmap(bmp, xdiff / 2, ydiff / 2, reqWidth,
					reqHeight);
		} else if (xdiff >= 0 && ydiff < 0) {
			xdiff = bmp.getWidth() - bmp.getHeight();
			bmp = Bitmap.createBitmap(bmp, xdiff / 2, 0, bmp.getHeight(),
					bmp.getHeight());
		} else if (xdiff < 0 && ydiff >= 0) {
			ydiff = bmp.getHeight() - bmp.getWidth();
			bmp = Bitmap.createBitmap(bmp, 0, ydiff / 2, bmp.getWidth(),
					bmp.getWidth());
		} else {
			if (bmp.getHeight() > bmp.getWidth()) {
				ydiff = bmp.getHeight() - bmp.getWidth();
				bmp = Bitmap.createBitmap(bmp, 0, ydiff / 2, bmp.getWidth(),
						bmp.getWidth());
			} else {
				xdiff = bmp.getWidth() - bmp.getHeight();
				bmp = Bitmap.createBitmap(bmp, xdiff / 2, 0, bmp.getHeight(),
						bmp.getHeight());
			}
		}

		return bmp;
	}
	
	public static Bitmap getRoundedCornerBitmap(final Bitmap bitmap , final float roundPx) {

		if(bitmap != null) {
			try {
				final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
				Canvas canvas = new Canvas(output);

				final Paint paint = new Paint();
				final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
				final RectF rectF = new RectF(rect);

				paint.setAntiAlias(true);
				canvas.drawARGB(0, 0, 0, 0);
				canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

				paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
				canvas.drawBitmap(bitmap, rect, rect, paint);


				return output;

			} catch(OutOfMemoryError e) {
			} catch (Exception e) {
			}
		}
		return bitmap;
	}

}
