package fr.istic.mmm.busmatch.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import fr.istic.mmm.busmatch.ActiveUser;
import fr.istic.mmm.busmatch.ActiveUsersListener;
import fr.istic.mmm.busmatch.R;

public class ToMatchFragment extends ListFragment {

    //Référence à la base de donnée Firebase
    private DatabaseReference mDatabase;

    private List<ActiveUser> userToMatch = new ArrayList<>();

    private List<String> userName = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query queryActiveUsers = mDatabase.child("activeUsers").orderByChild("timestamp").limitToFirst(100);
        queryActiveUsers.addValueEventListener(ActiveUsersListener.getInstance());

        userToMatch.add(new ActiveUser("toto" , "toto@gmail.com"));
        userName.add("toto");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.user_list_view, userName);

        //ListView listView = (ListView)findViewById(R.id.personToMatch);

        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_match, container, false);
    }
}
