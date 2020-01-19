package com.example.fixingapp.firebase;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.fixingapp.models.Job;
import com.example.fixingapp.models.User;
import com.example.fixingapp.models.Worker;
import com.example.fixingapp.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class Database {
    Activity activity;

    String authUIDWorker ;

    private UserSignIn userSignIn;
    private UserSignUp userSignUp;
    private WorkerSignIn workerSignIn;
    private WorkerSignUp workerSignUp;
    private JobsArrive jobsArrive;

    public void setJobsArrive(JobsArrive jobsArrive) {
        this.jobsArrive = jobsArrive;
    }

    public void setUserSignIn(UserSignIn userSignIn) {
        this.userSignIn = userSignIn;
    }

    public void setUserSignUp(UserSignUp userSignUp) {
        this.userSignUp = userSignUp;
    }

    public void setWorkerSignIn(WorkerSignIn workerSignIn) {
        this.workerSignIn = workerSignIn;
    }

    public void setWorkerSignUp(WorkerSignUp workerSignUp) {
        this.workerSignUp = workerSignUp;
    }

    public Database(Activity activity) {
        this.activity = activity;
    }

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = firestore.collection(Constants.USERS_COLLECTION);
    CollectionReference jobsCollection=firestore.collection(Constants.JOBS_COLLECTION);


    public void addWorker(final Worker worker){
        usersCollection.add(worker).addOnSuccessListener(activity,new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                workerSignUp.onWorkerSignUpSuccess(worker.getUID());
            }
             }).addOnFailureListener(activity,new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                workerSignUp.onWorkerSignUpFailed(e);
            }
        });
    }

    public void addUser(final User user){
        usersCollection.add(user).addOnSuccessListener(activity,new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                userSignUp.onSignUpSuccess(user.getUID());
            }
             }).addOnFailureListener(activity,new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                userSignUp.onSignUpFailed(e);
            }
        });
    }

    public void signInUser(String phone , String password){

        usersCollection.whereEqualTo(Constants.PHONE_NUMBER,phone)
                    .whereEqualTo(Constants.PASSWORD,password)
                    .get().addOnSuccessListener(activity,new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                        User user =snapshot.toObject(User.class);
                        String authUIDUser=user.getUID();
                        userSignIn.onUserSignInSuccess(authUIDUser);
                    }
                }
            }).addOnFailureListener(activity,new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    userSignIn.onUserSignInFailed(e);
                }
            });
    }

    public void signInWorker(String phone , String password){
        usersCollection.whereEqualTo(Constants.PHONE_NUMBER,phone)
                .whereEqualTo(Constants.PASSWORD,password).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                    Worker worker= snapshot.toObject(Worker.class);
                    String authUIDWorker=worker.getUID();
                    workerSignIn.onWorkerSignInSuccess(authUIDWorker);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    workerSignIn.onWorkerSignInFaild(e);
            }
        });

    }


    public interface UserSignIn{
        void onUserSignInSuccess(String id);
        void onUserSignInFailed( Exception e);
    }
    public interface UserSignUp{
        void onSignUpSuccess(String id);
        void onSignUpFailed( Exception e);
    }
    public interface WorkerSignIn{
        void onWorkerSignInSuccess(String id);
        void onWorkerSignInFaild( Exception e);
    }
    public interface WorkerSignUp{
        void onWorkerSignUpSuccess(String id);
        void onWorkerSignUpFailed( Exception e);
    }


    public ArrayList<Job> getAllJobs(){
        final ArrayList<Job> jobs =new ArrayList<>();
        jobsCollection.get()
                .addOnSuccessListener(activity,new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot :queryDocumentSnapshots){
                            Job job = snapshot.toObject(Job.class);
                            jobs.add(job);
                        }
                        jobsArrive.onJobsArrive();
                    }
                }).addOnFailureListener(activity,new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        return jobs;
    }


    public interface JobsArrive{
        void onJobsArrive();
    }

    public void provideJob(Job job){
        jobsCollection.add(job).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(activity, "added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
