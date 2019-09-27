package avnpost.com.model;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.util.LruCache;

public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    public static int getDefaultLruCacheSize() {
        return ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8;
    }
    public LruBitmapCache() {
        this(getDefaultLruCacheSize());
    }
    public LruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }
    protected int sizeOf(String key, Bitmap value) {
        return (value.getRowBytes() * value.getHeight()) / 1024;
    }
    public Bitmap getBitmap(String url) {
        return (Bitmap) get(url);
    }
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}