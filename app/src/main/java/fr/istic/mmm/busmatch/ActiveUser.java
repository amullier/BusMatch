package fr.istic.mmm.busmatch;

import android.location.Location;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Entité qui représente un utilisateur actif
 */
@IgnoreExtraProperties
public class ActiveUser {

    private String username;
    private String email;
    private Date date;
    private Location location;

    public ActiveUser() {
        // Default constructor required for calls to DataSnapshot.getValue(ActiveUser.class)
    }

    /**
     * Constructeur par défaut, la date est mise a la date du moment
     * @param username
     * @param email
     */
    public ActiveUser(String username, String email) {
        this.username = username;
        this.email = email;
        this.date = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", location=" + location +
                '}';
    }
}