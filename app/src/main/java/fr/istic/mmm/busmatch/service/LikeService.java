package fr.istic.mmm.busmatch.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.istic.mmm.busmatch.domain.Like;
import fr.istic.mmm.busmatch.domain.User;

/**
 * Created by bob on 27/03/18.
 */

public class LikeService {

    private static final String DB_FIELD_LIKE = "likes";

    private static LikeService instance = new LikeService();
    public static LikeService getInstance(){
        return instance;
    }

    private static ActiveUsersListener userService = ActiveUsersListener.getInstance();



    public void addLike(User userLike) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child(DB_FIELD_LIKE).push().getKey();
        Like like = new Like(userLike.getUid(),userService.getUser());
        Map<String, Object> map = new HashMap<>();
        map.put(key, like);
        mDatabase.child(DB_FIELD_LIKE).setValue(map);
    }


}
