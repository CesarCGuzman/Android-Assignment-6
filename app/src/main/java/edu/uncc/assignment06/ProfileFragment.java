package edu.uncc.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import edu.uncc.assignment06.databinding.FragmentProfileBinding;
import edu.uncc.assignment06.models.User;
public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM_USER = "user";
    private User mParamUser;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamUser = (User) getArguments().getSerializable(ARG_PARAM_USER);
        }
    }

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
            binding = FragmentProfileBinding.inflate(inflater, container, false);
            return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        binding.textViewUserName.setText(mParamUser.getName());
        binding.textViewUserAgeGroup.setText(mParamUser.getAge_group_name());
        binding.textViewUserMood.setText(mParamUser.getMood_name());
        Picasso.get().load(mParamUser.getMood_imgUrl()).into(binding.imageViewMood);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.doneAddingUser();
            }
        });
    }

    ProfileFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ProfileFragmentListener) context;
    }

    public interface ProfileFragmentListener {
        void doneAddingUser();
    }
}