package avnpost.com.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUriExposedException;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import avnpost.com.R;
import avnpost.com.api.URLs;
import avnpost.com.database.Contact;
import avnpost.com.database.DatabaseHandler;
import avnpost.com.model.MyBounceInterpolator;
import avnpost.com.model.VolleySingleton;

public class SinglePost extends AppCompatActivity {

    ImageView whishlist_off,whishlist_on,back,show_image;
    String ids;
    String title,image,link;
    TextView news_title,news_text,dates,authors;
    ProgressBar load_img;
    ImageView whats,twit,fb;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        verifyStoragePermissions(SinglePost.this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ids = bundle.getString("ids");
            GetSinglePost();
        }
        DatabaseHandler db = new DatabaseHandler(this);
        news_text=(TextView)findViewById(R.id.news_text);
        news_title=(TextView)findViewById(R.id.news_title);
        show_image=(ImageView)findViewById(R.id.show_image);
        load_img = (ProgressBar)findViewById(R.id.load_img);

        dates=(TextView)findViewById(R.id.date);
        authors=(TextView)findViewById(R.id.author);

        whishlist_off = (ImageView) findViewById(R.id.whishlist_off);
        whishlist_on = (ImageView) findViewById(R.id.whishlist_on);

        whishlist_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whishlist_off.setVisibility(View.GONE);
                whishlist_on.setVisibility(View.VISIBLE);
                final Animation myAnim = AnimationUtils.loadAnimation(SinglePost.this, R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);
                whishlist_on.startAnimation(myAnim);
                db.addContact(new Contact(ids));
            }
        });

        whishlist_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whishlist_off.setVisibility(View.VISIBLE);
                whishlist_on.setVisibility(View.GONE);
                final Animation myAnim = AnimationUtils.loadAnimation(SinglePost.this, R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);
                whishlist_off.startAnimation(myAnim);
                db.deleteContact(new Contact(ids));
            }
        });

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SinglePost.this,NewsBoard.class);
                startActivity(intent);
                finish();
            }
        });
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = cn.getName();
           if(ids.equals(log)){
               whishlist_on.setVisibility(View.VISIBLE);
               whishlist_off.setVisibility(View.GONE);
           }
    }
        whats=(ImageView)findViewById(R.id.whats);
        whats.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                shareItem(image);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void shareItem(String url) {
        Picasso.get().load(url).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setType("text/plain");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT,title+" "+link);
                    whatsappIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                    whatsappIntent.setType("image/jpeg");
                    whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    try {
                        startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                    }
                }catch (FileUriExposedException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
    public void GetSinglePost() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.SINGLE_ROOT_URL+ids,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String id = obj.getString("id");
                            title = obj.getString("title");
                            String date = obj.getString("date");
                            String author_name = obj.getString("author_name");
                            String author = obj.getString("author");
                            String content = obj.getString("content");
                            image = obj.getString("image");
                            link = obj.getString("link");
                            news_title.setText(title);
                            try {
                                Glide.with(SinglePost.this).load(image)
                                        .error(R.drawable.no_image_available)
                                        .centerCrop()
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(show_image);
                                load_img.setVisibility(View.GONE);
                            }catch (IllegalArgumentException e){
                                e.printStackTrace();
                            }
                            dates.setText(date);
                            authors.setText(author_name);
                            news_text.setText(content);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

}
