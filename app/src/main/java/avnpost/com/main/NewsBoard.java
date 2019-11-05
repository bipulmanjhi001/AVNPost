package avnpost.com.main;

import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import avnpost.com.R;
import avnpost.com.drawer.Favourite;
import avnpost.com.drawer.Follow;
import avnpost.com.drawer.Home;
import avnpost.com.drawer.Video;

public class NewsBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView title_avn;
    FrameLayout frame;
    ImageView check_notifications;
    Animation shake;
    private static final String TAG = NewsBoard.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(NewsBoard.this);
        setContentView(R.layout.activity_news_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(NewsBoard.this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        title_avn=(TextView)findViewById(R.id.title_avn);
        frame=(FrameLayout)findViewById(R.id.frame);

        check_notifications=(ImageView)findViewById(R.id.check_notifications);
        check_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_notifications.setBackgroundResource(R.drawable.ic_turn_notifications_on_button);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                check_notifications.startAnimation(shake);

                FirebaseMessaging.getInstance().subscribeToTopic("News")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    msg = getString(R.string.msg_subscribe_failed);
                                }
                                Log.d(TAG, msg);
                                 Toast.makeText(NewsBoard.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new Home()).commit();
        NewsBoard.this.setTitle("होम");

        FirebaseMessaging.getInstance().subscribeToTopic("News")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                       // Toast.makeText(NewsBoard.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            title_avn.setText("होम");

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame, new Home()).commit();

        } else if (id == R.id.nav_video) {
            title_avn.setText("वीडियो");

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame, new Video()).commit();

        } else if (id == R.id.nav_fav) {
            title_avn.setText("पसंदीदा");

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame, new Favourite()).commit();

        } else if (id == R.id.nav_follow) {
            title_avn.setText("फ़ॉलो");

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame, new Follow()).commit();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
