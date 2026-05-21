package com.legalai.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.legalai.app.R;
import com.legalai.app.activities.EditProfileActivity;
import com.legalai.app.activities.LegalGlossaryActivity;
import com.legalai.app.activities.LoginActivity;
import com.legalai.app.activities.NotificationsActivity;
import com.legalai.app.activities.SavedLawyersActivity;
import com.legalai.app.utils.AppUtils;
import com.legalai.app.utils.FirebaseHelper;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvName = view.findViewById(R.id.tv_profile_name);
        TextView tvEmail = view.findViewById(R.id.tv_profile_email);
        TextView tvInitials = view.findViewById(R.id.tv_profile_initials);
        SwitchCompat switchDark = view.findViewById(R.id.switch_dark_mode);
        LinearLayout rowEditProfile = view.findViewById(R.id.row_edit_profile);
        LinearLayout rowSavedLawyers = view.findViewById(R.id.row_saved_lawyers);
        LinearLayout rowNotifications = view.findViewById(R.id.row_notifications);
        LinearLayout rowGlossary = view.findViewById(R.id.row_glossary);
        LinearLayout rowLogout = view.findViewById(R.id.row_logout);

        // Load user info
        new FirebaseHelper().getUserProfile(profile -> {
            if (profile != null) {
                tvName.setText(profile.getName());
                tvEmail.setText(profile.getEmail());
                tvInitials.setText(AppUtils.getInitials(profile.getName()));
            } else {
                String cachedName = AppUtils.getCachedUserName(requireContext());
                String cachedEmail = AppUtils.getCachedUserEmail(requireContext());
                tvName.setText(cachedName.isEmpty() ? "User" : cachedName);
                tvEmail.setText(cachedEmail);
                tvInitials.setText(AppUtils.getInitials(cachedName));
            }
        });

        // Dark mode
        switchDark.setChecked(AppUtils.isDarkMode(requireContext()));
        switchDark.setOnCheckedChangeListener((btn, checked) -> {
            AppUtils.setDarkMode(requireContext(), checked);
            AppCompatDelegate.setDefaultNightMode(checked
                    ? AppCompatDelegate.MODE_NIGHT_YES
                    : AppCompatDelegate.MODE_NIGHT_NO);
        });

        rowEditProfile.setOnClickListener(v -> startActivity(new Intent(requireActivity(), EditProfileActivity.class)));
        rowSavedLawyers.setOnClickListener(v -> startActivity(new Intent(requireActivity(), SavedLawyersActivity.class)));
        rowNotifications.setOnClickListener(v -> startActivity(new Intent(requireActivity(), NotificationsActivity.class)));
        rowGlossary.setOnClickListener(v -> startActivity(new Intent(requireActivity(), LegalGlossaryActivity.class)));

        rowLogout.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Log Out", (d, w) -> {
                    new FirebaseHelper().signOut();
                    AppUtils.clearCache(requireContext());
                    startActivity(new Intent(requireActivity(), LoginActivity.class));
                    requireActivity().finishAffinity();
                })
                .setNegativeButton("Cancel", null)
                .show());

        return view;
    }
}
