package fr.istic.mmm.busmatch.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import fr.istic.mmm.busmatch.ActiveUsersListener;
import fr.istic.mmm.busmatch.BusMatchCard;
import fr.istic.mmm.busmatch.R;
import fr.istic.mmm.busmatch.domain.User;

public class ToMatchFragment extends ListFragment {

    private ActiveUsersListener userService = ActiveUsersListener.getInstance();
    private List<User> userToMatch;
    private List<String> userName = new ArrayList<>();


    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userToMatch = userService.getActiveUserList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.user_list_view, userName);

        setListAdapter(adapter);*/


        mSwipeView = (SwipePlaceHolderView)getActivity().findViewById(R.id.swipeView);
        mContext = getActivity().getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));



        for(User actUser : userToMatch) {
            mSwipeView.addView(new BusMatchCard(mContext, actUser, mSwipeView));
        }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_match, container, false);
    }
}
