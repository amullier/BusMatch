package fr.istic.mmm.busmatch;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    private final Logger logger = Logger.getLogger(MainActivity.class.getName());

    //Référence à la base de donnée Firebase
    private DatabaseReference mDatabase;

    // Stockage de l'utilisateur actif
    private ActiveUser activeUser;

    //TODO : Service de localisation  : en développement
    private FusedLocationProviderClient mFusedLocationClient;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lancement du service d'authentification
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

        //Lancement du service de localisation
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        BottomNavigationView bottomNavigation = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case (R.id.tomatch):
                            case (R.id.ismatch):
                            case (R.id.discution):
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.frame_layout, //select first fragment);
        transaction.commit();
    }

    /**
     * Méthode appelée lorsque l'authentification est finalisée
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();

                //Ajouter l'utilisateur dans la base des utilisateurs actifs
                activeUser = new ActiveUser(userFirebase.getDisplayName(), userFirebase.getEmail());
                setLocationToActiveUser(null);

                logger.info("Connexion de l'utilisateur : " + activeUser);

                mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference activeUserRef = mDatabase.child("activeUsers").push();
                activeUserRef.setValue(activeUser);

                ActiveUsersListener.getInstance().setActiveUser(activeUser);

                Query queryActiveUsers = mDatabase.child("activeUsers").orderByChild("timestamp").limitToFirst(100);
                queryActiveUsers.addValueEventListener(ActiveUsersListener.getInstance());

            } else {
                logger.info("La connexion a échoué");
            }
        }
    }

    public void signOut(View v) {
        FirebaseAuth.getInstance().signOut();
        logger.info("Deconnexion de l'utilisateur : " + activeUser);
    }

    /**
     * Permet de setter la localisation à l'utilisateur en cours
     */
    public void setLocationToActiveUser(View v) {
        //Permission check
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            logger.warning("La localisation n'est pas activée ou pas autorisée");
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(activeUser!=null){
                            activeUser.setLocation(location);
                            if (location == null) {
                                logger.warning("La localisation est null");
                            }
                        }
                    }
                });
    }
}
