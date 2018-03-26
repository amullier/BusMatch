package fr.istic.mmm.busmatch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.istic.mmm.busmatch.domain.User;

/**
 * Created by antoine on 14/03/18.
 */

public class ActiveUsersListener implements ValueEventListener {
    private final Logger logger = Logger.getLogger(ActiveUsersListener.class.getName());

    private static List<User> userList = new ArrayList<>();
    private static ActiveUsersListener instance;
    private static User user;

    private ActiveUsersListener() {
    }

    public static ActiveUsersListener getInstance(){
        if(instance==null){
            instance = new ActiveUsersListener();
        }
        return instance;
    }

    public void setActiveUser(User user){
        ActiveUsersListener.user = user;
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

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        userList.clear();
        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            User userInList = postSnapshot.getValue(User.class);
            if( user ==null || !user.getEmail().equals(userInList.getEmail())){
                userList.add(userInList);
                logger.info("Ajout de l'user : " + userInList.toString());
            }
        }
        logger.info("Taille actuelle de la liste des utilisateurs actifs : "
                + userList.size());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        logger.info("Erreur lors d'une modification d'un utilisateur actif");
    }
}
