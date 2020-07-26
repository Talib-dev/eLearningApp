
package app.com.eLearningApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;

import javax.annotation.Nullable;

import app.com.eLearningApp.fragments.HomeFragment;
import app.com.eLearningApp.login.Login;

public class Navigation extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private AdvanceDrawerLayout drawer;
    NavigationView mNavigationView;
    View mHeaderView;

    TextView textViewUsername;
    TextView textViewEmail;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle=getIntent().getExtras();
        drawer = (AdvanceDrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView
        NavigationView navigationView =  findViewById(R.id.nav_view);
        // NavigationView Header
        mHeaderView =  navigationView.getHeaderView(0);
        // View
        textViewUsername = (TextView) mHeaderView.findViewById(R.id.user_name);
        textViewEmail= (TextView) mHeaderView.findViewById(R.id.email_id);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Set username & email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            textViewUsername.setText(user.getDisplayName());
            textViewEmail.setText(user.getEmail());
        }
        else {
            userId = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fStore.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    textViewUsername.setText(documentSnapshot != null ? documentSnapshot.getString("fName") : null);
                    textViewEmail.setText(documentSnapshot != null ? documentSnapshot.getString("email") : null);
                }
            });
        }
        navigationView.setNavigationItemSelectedListener(this);
        //defult fragment
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
        fragmentTransaction.commit();
        drawer.useCustomBehavior(Gravity.START);
        drawer.useCustomBehavior(Gravity.END);

    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();
        switch (id)
        {
//            case R.id.nav_java:
//                HomeFragment fragment = new HomeFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
//                fragmentTransaction.commit();
////                PlayListFragment fragment = new PlayListFragment("PLKKfKV1b9e8ps6dD3QA5KFfHdiWj9cB1s");
////                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
////                fragmentTransaction.commit();
//                break;
//
//            case R.id.nav_Database:
//                PlayListFragment fragment1 = new PlayListFragment("PLdo5W4Nhv31b33kF46f9aFjoJPOkdlsRc");
//                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction1.replace(R.id.frame_layout, fragment1, "Home");
//                fragmentTransaction1.commit();
//                break;
//            case R.id.nav_compiler_design:
//                PlayListFragment fragment2 = new PlayListFragment("PLYwpaL_SFmcC6FupM--SachxUTOiQ7XHw");
//                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction2.replace(R.id.frame_layout, fragment2, "Home");
//                fragmentTransaction2.commit();
//                break;
//            case R.id.nav_theory_of_computation:
//                PlayListFragment fragment3 = new PlayListFragment("PLYwpaL_SFmcDXLUrW3JEq2cv8efNF6UeQ");
//                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction3.replace(R.id.frame_layout, fragment3, "Home");
//                fragmentTransaction3.commit();
//                break;
            case  R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;
            case R.id.nav_about:
                Toast.makeText(this, "In  Development", Toast.LENGTH_SHORT).show();
                break;

            default:
               // Toast.makeText(this, "Something clicked", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
