package com.legalai.app.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.legalai.app.models.LegalCase;
import com.legalai.app.models.NotificationItem;
import com.legalai.app.models.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseHelper {

    private final FirebaseAuth auth;
    private final FirebaseFirestore firestore;
    private final FirebaseDatabase database;

    public FirebaseHelper() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    // ─── Auth ─────────────────────────────────────────────────

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }

    public String getCurrentUid() {
        FirebaseUser user = auth.getCurrentUser();
        return user != null ? user.getUid() : null;
    }

    public void signUp(String email, String password, String name, AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(result -> {
                    FirebaseUser user = result.getUser();
                    if (user != null) {
                        UserProfile profile = new UserProfile(user.getUid(), name, email, "", "");
                        saveUserProfile(profile, success -> callback.onResult(success, null));
                    } else {
                        callback.onResult(false, "User creation failed");
                    }
                })
                .addOnFailureListener(e -> callback.onResult(false, e.getMessage()));
    }

    public void signIn(String email, String password, AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(result -> callback.onResult(true, null))
                .addOnFailureListener(e -> callback.onResult(false, e.getMessage()));
    }

    public void sendPasswordReset(String email, AuthCallback callback) {
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(v -> callback.onResult(true, null))
                .addOnFailureListener(e -> callback.onResult(false, e.getMessage()));
    }

    public void signOut() {
        auth.signOut();
    }

    public void deleteAccount(AuthCallback callback) {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) { callback.onResult(false, "Not logged in"); return; }
        String uid = user.getUid();
        user.delete()
                .addOnSuccessListener(v -> {
                    firestore.collection("users").document(uid).delete();
                    callback.onResult(true, null);
                })
                .addOnFailureListener(e -> callback.onResult(false, e.getMessage()));
    }

    // ─── User Profile ─────────────────────────────────────────

    public void saveUserProfile(UserProfile profile, BoolCallback callback) {
        firestore.collection("users").document(profile.getUid())
                .set(profile)
                .addOnSuccessListener(v -> callback.onResult(true))
                .addOnFailureListener(e -> callback.onResult(false));
    }

    public void getUserProfile(ProfileCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(null); return; }
        firestore.collection("users").document(uid).get()
                .addOnSuccessListener(doc -> {
                    if (doc.exists()) callback.onResult(doc.toObject(UserProfile.class));
                    else callback.onResult(null);
                })
                .addOnFailureListener(e -> callback.onResult(null));
    }

    public void updateUserProfile(String name, String phone, String location, BoolCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(false); return; }
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", name);
        updates.put("phone", phone);
        updates.put("location", location);
        firestore.collection("users").document(uid)
                .update(updates)
                .addOnSuccessListener(v -> callback.onResult(true))
                .addOnFailureListener(e -> callback.onResult(false));
    }

    // ─── Cases ────────────────────────────────────────────────

    public void saveCase(LegalCase legalCase, BoolCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(false); return; }
        firestore.collection("users").document(uid)
                .collection("cases").document(legalCase.getId())
                .set(legalCase)
                .addOnSuccessListener(v -> callback.onResult(true))
                .addOnFailureListener(e -> callback.onResult(false));
    }

    public void getCases(CasesCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(new ArrayList<>()); return; }
        firestore.collection("users").document(uid)
                .collection("cases")
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(query -> {
                    List<LegalCase> cases = new ArrayList<>();
                    for (com.google.firebase.firestore.DocumentSnapshot doc : query.getDocuments()) {
                        LegalCase c = doc.toObject(LegalCase.class);
                        if (c != null) cases.add(c);
                    }
                    callback.onResult(cases);
                })
                .addOnFailureListener(e -> callback.onResult(new ArrayList<>()));
    }

    // ─── Saved Lawyers (Realtime DB) ──────────────────────────

    public DatabaseReference getSavedLawyersRef() {
        String uid = getCurrentUid();
        if (uid == null) return null;
        return database.getReference("saved_lawyers").child(uid);
    }

    public void saveLawyer(String lawyerId, String name, String specialty,
                            String initials, BoolCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(false); return; }
        Map<String, Object> data = new HashMap<>();
        data.put("id", lawyerId);
        data.put("name", name);
        data.put("specialty", specialty);
        data.put("avatarInitials", initials);
        database.getReference("saved_lawyers").child(uid).child(lawyerId)
                .setValue(data)
                .addOnSuccessListener(v -> callback.onResult(true))
                .addOnFailureListener(e -> callback.onResult(false));
    }

    public void removeSavedLawyer(String lawyerId, BoolCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(false); return; }
        database.getReference("saved_lawyers").child(uid).child(lawyerId)
                .removeValue()
                .addOnSuccessListener(v -> callback.onResult(true))
                .addOnFailureListener(e -> callback.onResult(false));
    }

    public void isLawyerSaved(String lawyerId, BoolCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(false); return; }
        database.getReference("saved_lawyers").child(uid).child(lawyerId)
                .get()
                .addOnSuccessListener(snap -> callback.onResult(snap.exists()))
                .addOnFailureListener(e -> callback.onResult(false));
    }

    // ─── Notifications ────────────────────────────────────────

    public void getNotifications(NotificationsCallback callback) {
        String uid = getCurrentUid();
        if (uid == null) { callback.onResult(new ArrayList<>()); return; }
        firestore.collection("users").document(uid)
                .collection("notifications")
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(50)
                .get()
                .addOnSuccessListener(query -> {
                    List<NotificationItem> items = new ArrayList<>();
                    for (com.google.firebase.firestore.DocumentSnapshot doc : query.getDocuments()) {
                        NotificationItem n = doc.toObject(NotificationItem.class);
                        if (n != null) items.add(n);
                    }
                    // Fallback to mock if empty
                    if (items.isEmpty()) items = MockDataGenerator.getMockNotifications();
                    callback.onResult(items);
                })
                .addOnFailureListener(e -> callback.onResult(MockDataGenerator.getMockNotifications()));
    }

    // ─── Callbacks ────────────────────────────────────────────

    public interface AuthCallback {
        void onResult(boolean success, String error);
    }

    public interface BoolCallback {
        void onResult(boolean success);
    }

    public interface ProfileCallback {
        void onResult(UserProfile profile);
    }

    public interface CasesCallback {
        void onResult(List<LegalCase> cases);
    }

    public interface NotificationsCallback {
        void onResult(List<NotificationItem> items);
    }
}
