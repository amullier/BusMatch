package fr.istic.mmm.busmatch;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.logging.Logger;

import fr.istic.mmm.busmatch.domain.User;

/**
 * Created by bob on 16/03/18.
 */

@Layout(R.layout.busmatch_card_view)
public class BusMatchCard {

    private final Logger logger = Logger.getLogger(MainActivity.class.getName());

    /*@View(R.id.profileImageView)
    private ImageView profileImageView;*/

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private User user;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public BusMatchCard(Context context, User user, SwipePlaceHolderView swipeView) {
        mContext = context;
        this.user = user;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved(){
        //Glide.with(mContext).load(mProfile.getImageUrl()).into(profileImageView);
        nameAgeTxt.setText(user.getUsername());
        locationNameTxt.setText(user.getEmail());
    }

    /*@SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }*/

    /*@SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }*/

    /*@SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }*/

    /*@SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }*/

    /*@SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }*/
}