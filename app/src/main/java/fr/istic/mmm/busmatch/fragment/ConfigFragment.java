package fr.istic.mmm.busmatch.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.istic.mmm.busmatch.ActiveUsersListener;
import fr.istic.mmm.busmatch.R;
import fr.istic.mmm.busmatch.domain.EGenre;
import fr.istic.mmm.busmatch.domain.User;

/**
 * Created by bob on 27/03/18.
 */

public class ConfigFragment extends Fragment {

    private ActiveUsersListener userService = ActiveUsersListener.getInstance();

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.age)
    EditText age;

    @BindView(R.id.description)
    EditText description;

    @BindView(R.id.genre)
    EditText genre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        User user = userService.getUser();
        username.setText(user.getUsername());
        if(user.getAge() != null){
            age.setText(user.getAge().toString());
        }
        if(user.getGenre() != null){
            genre.setText(user.getGenre().toString());
        }
        if(user.getDescription() != null){
            description.setText(user.getDescription());
        }
    }

    @OnClick(R.id.update)
    public void update() {
        User user = userService.getUser();
        user.setUsername(username.getText().toString());
        user.setAge(Integer.parseInt(age.getText().toString()));
        user.setGenre(EGenre.valueOf(genre.getText().toString()));
        user.setDescription(description.getText().toString());
        userService.updateUser(user);
    }
}