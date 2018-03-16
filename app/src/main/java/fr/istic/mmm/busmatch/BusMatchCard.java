package fr.istic.mmm.busmatch;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by bob on 16/03/18.
 */

@Layout(R.layout.busmatch_card_view)
public class BusMatchCard {

    /*@View(R.id.profileImageView)
    private ImageView profileImageView;*/

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private ActiveUser activeUser;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public BusMatchCard(Context context, ActiveUser user, SwipePlaceHolderView swipeView) {
        mContext = context;
        activeUser = user;
        mSwipeView = swipeView;
    }

    /*@Resolve
    private void onResolved(){
        Glide.with(mContext).load(mProfile.getImageUrl()).into(profileImageView);
        nameAgeTxt.setText(mProfile.getName() + ", " + mProfile.getAge());
        locationNameTxt.setText(mProfile.getLocation());
    }*/

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