package com.vv.mvp;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by VV on 2016/8/21.
 */
public class BitmapUtil {
    public static Bitmap getBitmapJava(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int heigth = bitmap.getHeight();
        //创建一个与原图等宽高的空白图片
        Bitmap result = Bitmap.createBitmap(width, heigth, Bitmap.Config.RGB_565);
        //对比度
        int cab = (int) ((1.0f + 0.5f) * 65536) + 1;
        //美化程度
        int bab = (int) (0.2f * 255);
        int a, r, g, b;
        //遍历每一个像素点
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < heigth; y++) {
                int color = bitmap.getPixel(x, y);
                a = Color.alpha(color);
                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);
                //降低颜色值 -
                int ai, ri, gi, bi;
                ai = a - bab;
                ri = r - bab;
                gi = g - bab;
                bi = b - bab;
                //边界值处理
                a = ai > 255 ? 255 : (ai < 0 ? 0 : ai);
                r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
                //改变对比度
//                ai = a -128;
                ri = r - 128;
                gi = g - 128;
                bi = b - 128;

                ri = (ri * cab) >> 16;
                gi = (gi * cab) >> 16;
                bi = (bi * cab) >> 16;

                ri = ri + 128;
                gi = gi + 128;
                bi = bi + 128;

                r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);

                result.setPixel(x, y, Color.argb(a, r, g, b));
            }
        }
        return result;
    }
}
