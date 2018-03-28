package fr.istic.mmm.busmatch.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import fr.istic.mmm.busmatch.service.ActiveUsersListener;
import fr.istic.mmm.busmatch.BusMatchCard;
import fr.istic.mmm.busmatch.R;
import fr.istic.mmm.busmatch.domain.User;

public class ToMatchFragment extends Fragment {

    private ActiveUsersListener userService = ActiveUsersListener.getInstance();
    private List<User> userToMatch = new ArrayList<>();

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    public void addUserToMatch(User user) {
        userToMatch.add(user);
        mSwipeView.addView(new BusMatchCard(mContext, user, mSwipeView));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSwipeView = (SwipePlaceHolderView)getActivity().findViewById(R.id.swipeView);
        mContext = getActivity().getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));


        getActivity().findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        getActivity().findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_match, container, false);
        userService.getUserToMatch(this);
        return view;
    }
}
