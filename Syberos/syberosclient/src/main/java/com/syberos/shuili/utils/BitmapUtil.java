package com.syberos.shuili.utils;

/**
 * Created by jidan on 18-1-24.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.BitmapEntity;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.view.TextDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class BitmapUtil {

    final static String TAG = "BitmapUtil";
    static public Drawable getScaleDraw(String imgPath, Context mContext) {

        Bitmap bitmap = null;
        try {
            if(TextUtils.isEmpty(imgPath))
                return null;
            Log.d("BitmapUtil",
                    "[getScaleDraw]imgPath is " + imgPath.toString());
            File imageFile = new File(imgPath);
            if (!imageFile.exists()) {
                Log.d("BitmapUtil", "[getScaleDraw]file not  exists");
                return null;
            }
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imgPath, opts);

            opts.inSampleSize = computeSampleSize(opts, -1, 800 * 480);
            // Log.d("BitmapUtil","inSampleSize===>"+opts.inSampleSize);
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(imgPath, opts);

        } catch (OutOfMemoryError err) {
            Log.d("BitmapUtil", "[getScaleDraw] out of memory");

        }
        if (bitmap == null) {
            return null;
        }
        Drawable resizeDrawable = new BitmapDrawable(mContext.getResources(),
                bitmap);
        return resizeDrawable;
    }

    public static void saveMyBitmap(Context mContext, Bitmap bitmap,
                                    String desName) throws IOException {
        FileOutputStream fOut = null;

        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            LogUtils.d("saveMyBitmap", "sdcard doesn't exit, save png to app dir");
            fOut = mContext.openFileOutput(desName + ".png",
                    Context.MODE_PRIVATE);
        } else {
            File f = new File(Environment.getExternalStorageDirectory()
                    .getPath() + "/Asst/cache/" + desName + ".png");
            f.createNewFile();
            fOut = new FileOutputStream(f);
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Bitmap getScaleBitmap(Resources res, int id) {

        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, id, opts);

            opts.inSampleSize = computeSampleSize(opts, -1, 800 * 480);
            // Log.d("BitmapUtil","inSampleSize===>"+opts.inSampleSize);
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeResource(res, id, opts);
        } catch (OutOfMemoryError err) {
            Log.d("BitmapUtil", "[getScaleBitmap] out of memory");

        }
        return bitmap;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {

        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;

    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {

        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }

    }

    public static Bitmap drawableToBitmap1(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getImageBitmap(ImageView image) {
        image.buildDrawingCache();
        return image.getDrawingCache();
    }

    /**
     * @param name
     * @param dp
     * @return
     */
    public static TextDrawable drawableFactory(Context mContext, String name, int dp, String userId) {
        float density = (float) SPUtils.get(GlobleConstants.DENSITY, 1.0f);
        int px = (int) (dp * density);
        TextDrawable drawable = TextDrawable.builder().beginConfig().
                useFont(Typeface.DEFAULT)
                .fontSize(px) /* size in px */
                .endConfig()
                .buildRound(StringUtils.generateName(name), Color.parseColor("#9e8dbe")); // radius in px

        return drawable;

    }
    /**
     * 生成文字bitmap
     *
     * @param context
     * @param name
     * @param dp
     * @return
     */
    public static TextDrawable drawableFactory(Context context, String name, int dp) {
        float density = (float) SPUtils.get( GlobleConstants.DENSITY, 1.0f);
        int px = (int) (dp * density);

        TextDrawable drawable = TextDrawable.builder().beginConfig().
                useFont(Typeface.DEFAULT)
                .fontSize(px) /* size in px */
                .endConfig()
                .buildRound(StringUtils.generateName(name),  Color.parseColor("#007AD7")); // radius in px

        return drawable;

    }
    /**
     * 生成文字bitmap
     *
     * @param context
     * @param userInformation
     * @param dp
     * @return
     */
    public static TextDrawable drawableFactory(Context context, UserExtendInfo userInformation, int dp) {
        float density = (float) SPUtils.get(GlobleConstants.DENSITY, 1.0f);
        int px = (int) (dp * density);
        TextDrawable drawable = TextDrawable.builder().beginConfig().
                useFont(Typeface.DEFAULT)
                .fontSize(px) /* size in px */
                .endConfig()
                .buildRound(StringUtils.generateName(userInformation.getPersName()), Color.parseColor("#007AD7")); // radius in px

        return drawable;

    }

    /**
     * 不存在图片，则生成新的图片
     *
     * @param bitmap
     * @param iconUrl
     * @param isUpdate 是否更新，是则删除旧图片
     */
    public static void saveBitmap(Bitmap bitmap, String iconUrl, boolean isUpdate) {
        if (bitmap == null) {
            return;
        }
        if (TextUtils.isEmpty(iconUrl)) {
            return;
        }
        File f = new File(iconUrl);
        if (f.exists() && isUpdate) {
            f.delete();
            Log.d(TAG, "文件存在,删除");
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.d(TAG, "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap decodeBitmap(Resources res, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeResource(res, id, options);
        if (bitmap == null) {
            LogUtils.d("decodeBitmap", "bitmap为空");
        }
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        LogUtils.d("decodeBitmap", "真实图片高度：" + realHeight + "宽度:" + realWidth);
        // 计算缩放比
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
        if (scale <= 0) {
            scale = 1;
        }
        LogUtils.d("decodeBitmap", "scale=>" + scale);
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。
        bitmap = BitmapFactory.decodeResource(res, id, options);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        LogUtils.d("decodeBitmap", "缩略图高度：" + h + "宽度:" + w);
        return bitmap;
    }

    public static Bitmap getCombineBitmaps(List<BitmapEntity> mEntityList,
                                           Bitmap... bitmaps) {
        LogUtils.d("getCombineBitmaps", "count=>" + mEntityList.size());
        Bitmap newBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        LogUtils.d("getCombineBitmaps", "newBitmap=>" + newBitmap.getWidth() + ","
                + newBitmap.getHeight());
        for (int i = 0; i < mEntityList.size(); i++) {
            LogUtils.d("getCombineBitmaps", "i==>" + i);
            newBitmap = mixtureBitmap(newBitmap, bitmaps[i], new PointF(
                    mEntityList.get(i).x, mEntityList.get(i).y));
        }
        return newBitmap;
    }

    /**
     * 将多个Bitmap合并成一个图片。
     *
     * @param
     * @param
     * @return
     */
    public static Bitmap combineBitmaps(int columns, Bitmap... bitmaps) {
        if (columns <= 0 || bitmaps == null || bitmaps.length == 0) {
            throw new IllegalArgumentException(
                    "Wrong parameters: columns must > 0 and bitmaps.length must > 0.");
        }
        int maxWidthPerImage = 20;
        int maxHeightPerImage = 20;
        for (Bitmap b : bitmaps) {
            maxWidthPerImage = maxWidthPerImage > b.getWidth() ? maxWidthPerImage
                    : b.getWidth();
            maxHeightPerImage = maxHeightPerImage > b.getHeight() ? maxHeightPerImage
                    : b.getHeight();
        }
        LogUtils.d("combineBitmaps", "maxWidthPerImage=>" + maxWidthPerImage
                + ";maxHeightPerImage=>" + maxHeightPerImage);
        int rows = 0;
        if (columns >= bitmaps.length) {
            rows = 1;
            columns = bitmaps.length;
        } else {
            rows = bitmaps.length % columns == 0 ? bitmaps.length / columns
                    : bitmaps.length / columns + 1;
        }
        Bitmap newBitmap = Bitmap.createBitmap(columns * maxWidthPerImage, rows
                * maxHeightPerImage, Bitmap.Config.ARGB_8888);
        LogUtils.d("combineBitmaps", "newBitmap=>" + newBitmap.getWidth() + ","
                + newBitmap.getHeight());
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                int index = x * columns + y;
                if (index >= bitmaps.length)
                    break;
                LogUtils.d("combineBitmaps", "y=>" + y + " * maxWidthPerImage=>"
                        + maxWidthPerImage + " = " + (y * maxWidthPerImage));
               LogUtils.d("combineBitmaps", "x=>" + x + " * maxHeightPerImage=>"
                        + maxHeightPerImage + " = " + (x * maxHeightPerImage));
                newBitmap = mixtureBitmap(newBitmap, bitmaps[index],
                        new PointF(y * maxWidthPerImage, x * maxHeightPerImage));
            }
        }
        return newBitmap;
    }

    /**
     * Mix two Bitmap as one.
     *
     * @param
     * @param
     * @param
     * @return
     */
    public static Bitmap mixtureBitmap(Bitmap first, Bitmap second,
                                       PointF fromPoint) {
        if (first == null || second == null || fromPoint == null) {
            return null;
        }
        Bitmap newBitmap = Bitmap.createBitmap(first.getWidth(),
                first.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newBitmap);
        cv.drawBitmap(first, 0, 0, null);
        cv.drawBitmap(second, fromPoint.x, fromPoint.y, null);
        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();
        return newBitmap;
    }

    public static void getScreenWidthAndHeight(Activity mContext) {
        DisplayMetrics metric = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        LogUtils.d("getScreenWidthAndHeight", "screen width=>" + width + ",height=>" + height);
    }


    /**
     * 放大缩小图片
     *
     * @param bgimage
     * @param
     * @param
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bgimage, double newWidth, double newHeight) {

        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 根据宽度和长度进行缩放图片
     *
     * @param path 图片的路径
     * @param w    宽度
     * @param h    长度
     * @return
     */
    public static Bitmap createBitmap(String path, int w, int h) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // 这里是整个方法的关键，inJustDecodeBounds设为true时将不为图片分配内存。
            BitmapFactory.decodeFile(path, opts);

            int srcWidth = opts.outWidth;// 获取图片的原始宽度
            int srcHeight = opts.outHeight;// 获取图片原始高度
            int destWidth = 0;
            int destHeight = 0;
            // 缩放的比例
            double ratio = 0.0;
            if (srcWidth < w || srcHeight < h) {
                ratio = 0.0;
                destWidth = srcWidth;
                destHeight = srcHeight;
            } else if (srcWidth > srcHeight) {// 按比例计算缩放后的图片大小，maxLength是长或宽允许的最大长度
                ratio = (double) srcWidth / w;
                destWidth = w;
                destHeight = (int) (srcHeight / ratio);
            } else {
                ratio = (double) srcHeight / h;
                destHeight = h;
                destWidth = (int) (srcWidth / ratio);
            }
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 缩放的比例，缩放是很难按准备的比例进行缩放的，目前我只发现只能通过inSampleSize来进行缩放，其值表明缩放的倍数，SDK中建议其值是2的指数值
            newOpts.inSampleSize = (int) ratio + 1;
            // inJustDecodeBounds设为false表示把图片读进内存中
            newOpts.inJustDecodeBounds = false;
            // 设置大小，这个差评是不准确的，是以inSampleSize的为准，但是如果不设置却不能缩放
            newOpts.outHeight = destHeight;
            newOpts.outWidth = destWidth;
            // 获取缩放后图片
            return BitmapFactory.decodeFile(path, newOpts);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}

