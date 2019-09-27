package avnpost.com.model;

import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AVNApplication extends Application {

    private static AVNApplication mInstance;
    public static final String TAG = AVNApplication.class.getSimpleName();

    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public static synchronized AVNApplication getInstance() {
        AVNApplication appController;
        synchronized (AVNApplication.class) {
            appController = mInstance;
        }
        return appController;
    }
    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return this.mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (this.mImageLoader == null) {
            this.mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        req.setTag(tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(tag);
        }
    }
}