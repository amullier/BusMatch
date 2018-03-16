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
public class User {

    private String username;
    private String email;
    private Long timestamp;
    private Location location;
    private boolean active;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    /**
     * Constructeur par défaut, la date est mise a la date du moment
     * @param username
     * @param email
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.timestamp = -(System.currentTimeMillis()/1000);
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", timestamp=" + timestamp +
                ", location=" + location +
                '}';
    }
}