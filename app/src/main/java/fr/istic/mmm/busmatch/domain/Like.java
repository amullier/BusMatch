package fr.istic.mmm.busmatch.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bob on 27/03/18.
 */

public class Like {
    private String userId;
    private List<User> users;

    public Like(){

    }

    public Like(String userId, User user){
        this.userId = userId;
        users = new LinkedList<>();
        users.add(user);
    }

    public String getUserId(){
        return userId;
    }

    public List<User> getUsers() {
        return users;
    }
}
