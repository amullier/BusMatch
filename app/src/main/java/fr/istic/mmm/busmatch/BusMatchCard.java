package fr.istic.mmm.busmatch;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;

import java.util.logging.Logger;

import fr.istic.mmm.busmatch.domain.User;
import fr.istic.mmm.busmatch.service.LikeService;

/**
 * Created by bob on 16/03/18.
 */

@Layout(R.layout.busmatch_card_view)
public class BusMatchCard {

    @View(R.id.nameTxt)
    private TextView nameTxt;

    @View(R.id.ageTxt)
    private TextView ageTxt;

    @View(R.id.genreTxt)
    private TextView genreTxt;

    @View(R.id.descriptionTxt)
    private TextView descriptionTxt;

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
        nameTxt.setText(user.getUsername());
        ageTxt.setText(user.getAge().toString());
        genreTxt.setText(user.getGenre().toString());
        descriptionTxt.setText(user.getDescription());
    }

    /*@SwipeOut
    private void onSwipedOut(){
        mSwipeView.addView(this);
    }*/

    /*@SwipeCancelState
    private void onSwipeCancelState(){
    }*/

    @SwipeIn
    private void onSwipeIn(){
        LikeService.getInstance().addLike(user);
    }

    /*@SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }*/

    /*@SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }*/
}