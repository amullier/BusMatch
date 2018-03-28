package fr.istic.mmm.busmatch.service;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import fr.istic.mmm.busmatch.domain.User;
import fr.istic.mmm.busmatch.fragment.ToMatchFragment;

/**
 * Created by antoine on 14/03/18.
 */

public class ActiveUsersListener {
    private final Logger logger = Logger.getLogger(ActiveUsersListener.class.getName());

    private static final String DB_FIELD_USER="users";

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userDataBaseReference = database.child(DB_FIELD_USER);

    private List<User> userList = new ArrayList<>();
    private static ActiveUsersListener instance = new ActiveUsersListener();
    private User user;

    private ActiveUsersListener() {
    }

    public static ActiveUsersListener getInstance(){
        return instance;
    }

    public void setActiveUser(User user){
        this.user = user;
    }

    /**
     * Retourne la liste des utilisateurs actifs avec un potentiel match
     * @return
     */
    public List<User> getActiveUserList(){
        List<User> userListClone = new ArrayList<>();
        for (User user : userList){
            userListClone.add(user);
        }
        return userListClone;
    }

    public User getUser(){
        return user;
    }

    public void updateUser(User user) {
        this.user = user;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> map = new HashMap<>();
        map.put(this.user.getUid(), this.user);
        mDatabase.child(DB_FIELD_USER).updateChildren(map);
    }

    public void setActiveUser(final FirebaseUser userFirebase) {
        userDataBaseReference.child(userFirebase.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    userDataBaseReference.child(userFirebase.getUid()).child("active").setValue(true);
                    user = dataSnapshot.getValue(User.class);
                } else {
                    // create user
                    System.out.println("createUser");
                    user = new User(userFirebase.getUid(), userFirebase.getDisplayName(),userFirebase.getEmail());
                    Map<String, Object> map = new HashMap<>();
                    map.put(user.getUid(), user);
                    userDataBaseReference.setValue(map);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        userDataBaseReference.child(userFirebase.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void getUserToMatch(final ToMatchFragment fragment) {
        //orderByChild("active").equalTo(true).
        userDataBaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fragment.addUserToMatch(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
