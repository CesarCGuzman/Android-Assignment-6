package edu.uncc.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.assignment06.databinding.FragmentUsersBinding;
import edu.uncc.assignment06.databinding.ListItemUserBinding;
import edu.uncc.assignment06.models.User;
import edu.uncc.assignment06.models.UserResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UsersFragment extends Fragment {
    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    FragmentUsersBinding binding;
    ArrayList<User> mUsers = new ArrayList<>();
    UserAdapter adapter;

    // New stuff down here
    private final OkHttpClient client = new OkHttpClient();

    private void getUsers() {
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/mood/users")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace(); //Print the error
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String body = response.body().string();
                    mUsers.clear();

                    Gson gson = new Gson();
                    UserResponse userResponse = gson.fromJson(body, UserResponse.class);
                    mUsers.addAll(userResponse.getUsers());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    Log.d("demo", "onResponse: Not Successful");
                }
            }
        });
    }

    private void deleteUser(int uid) {
        RequestBody formBody = new FormBody.Builder()
                .add("uid", String.valueOf(uid))
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/mood/delete-user")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace(); //Print the error
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getUsers();
                        }
                    });
                } else {

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Users");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserAdapter();
        binding.recyclerView.setAdapter(adapter);
        getUsers();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_new_user_action) {
            //TODO: Handle the case of clicking on the add new user menu icon.
            //Navigate to the AddUserFragment Fragment
            Log.d("demo", "onOptionsItemSelected: menu item clicked");
            mListener.gotoAddUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu); //Inflate the menu
    }

    UsersListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof UsersListener) {
            mListener = (UsersListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement UsersListener");
        }
    }

    interface UsersListener{
        void gotoAddUser();
        void gotoProfile(User user);
    }

    class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemUserBinding userBinding = ListItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new UserViewHolder(userBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = mUsers.get(position);
            holder.setupUI(user);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            ListItemUserBinding mUserBinding;
            User mUser;
            public UserViewHolder(ListItemUserBinding userBinding) {
                super(userBinding.getRoot());
                mUserBinding = userBinding;
            }

            public void setupUI(User user){
                mUserBinding.textViewUserAgeGroup.setText(user.getAge_group_name());
                mUserBinding.textViewUserName.setText(user.getName());
                Picasso.get().load(user.getMood_imgUrl()).into(mUserBinding.imageViewUserMood);

                mUserBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteUser(user.getUid());
                    }
                });

                mUserBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.gotoProfile(user);
                    }
                });

                mUser = user;
            }
        }
    }
}

