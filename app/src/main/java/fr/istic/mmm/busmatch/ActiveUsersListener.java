package fr.istic.mmm.busmatch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by antoine on 14/03/18.
 */

public class ActiveUsersListener implements ValueEventListener {
    private final Logger logger = Logger.getLogger(ActiveUsersListener.class.getName());

    private static List<ActiveUser> activeUserList = new ArrayList<>();
    private static ActiveUsersListener instance;
    private static ActiveUser activeUser;

    private ActiveUsersListener() {
    }

    public static ActiveUsersListener getInstance(){
        if(instance==null){
            instance = new ActiveUsersListener();
        }
        return instance;
    }

    public static void setActiveUser(ActiveUser activeUser){
        ActiveUsersListener.activeUser = activeUser;
    }

    /**
     * Retourne la liste des utilisateurs actifs avec un potentiel match
     * @return
     */
    public static List<ActiveUser> getActiveUserList(){
        List<ActiveUser> activeUserListClone = new ArrayList<>();
        for (ActiveUser activeUser : activeUserList){
            activeUserListClone.add(activeUser);
        }
        return activeUserListClone;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        activeUserList.clear();
        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            ActiveUser activeUser = postSnapshot.getValue(ActiveUser.class);
            if(activeUser.getEmail()!=activeUser.getEmail()){
                activeUserList.add(activeUser);
                logger.info("Ajout de l'activeUser : " + activeUser.toString());
            }
        }
        logger.info("Taille actuelle de la liste des utilisateurs actifs : "
                + activeUserList.size());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        logger.info("Erreur lors d'une modification d'un utilisateur actif");
    }
}
