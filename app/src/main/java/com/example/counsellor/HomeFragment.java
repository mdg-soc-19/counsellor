package com.example.counsellor;



import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class HomeFragment extends Fragment {

    private RecyclerView blog_list_View;
    private List<BlogPost> blogList;
    private DocumentSnapshot lastVisible;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mauth;
    private MyAdapter myAdapter;
    private boolean firstPageLoaded = true;




    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        blog_list_View = view.findViewById(R.id.blog_list_view);
        blogList = new ArrayList<>();
        myAdapter = new MyAdapter(blogList);

        blog_list_View.setLayoutManager(new LinearLayoutManager(getActivity()));
        blog_list_View.setAdapter(myAdapter);

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();
            mauth = FirebaseAuth.getInstance();

            blog_list_View.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    boolean lastItem = !recyclerView.canScrollVertically(1);

                    if (lastItem) {
//                Toast.makeText(getContext(),"end of 3 posts",Toast.LENGTH_SHORT).show();
                        loadMorePosts();
                    }
                }
            });


            if (mauth.getCurrentUser() != null) {
                Query firstQuery = firebaseFirestore.collection("Posts")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(3);


                //getActivity bcoz to stop the on scroll listener after page closed bcause it will still call load more post
             /*   firstQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {


                        //get lastVisibile iff first page not loaded at starting
                        if (firstPageLoaded) {
                            // Get the last visible documentSnapshot
                            lastVisible = documentSnapshots.getDocuments()
                                    .get(documentSnapshots.size() - 1);
                        }

                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {
                                //Blog Id ..name same as that is Extender class
                                String BlogPostId = doc.getDocument().getId();
                                //USE MODEL CLASS and save one object obtained into Model class list
                                BlogPost blogPost = doc.getDocument().toObject(BlogPost.class).withId(BlogPostId);


                                if (firstPageLoaded) {
                                    blogList.add(blogPost);
                                }
                                //Add new post to top
                                else {
                                    blogList.add(0, blogPost);
                                }


                                myAdapter.notifyDataSetChanged();
                            }
                        }

                    }


                });*/

            }
        }
        return view;
    }


    private void loadMorePosts() {

        if (mauth.getCurrentUser() != null) {
            Query nextQuery = firebaseFirestore.collection("Posts")
                    .orderBy("timeStamp", Query.Direction.DESCENDING)
                    .startAfter(lastVisible)
                    .limit(3);

            nextQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (!documentSnapshots.isEmpty()) {

                        lastVisible = documentSnapshots.getDocuments()
                                .get(documentSnapshots.size() - 1);


                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                String BlogPostId = doc.getDocument().getId();
                                BlogPost blogPost = doc.getDocument().toObject(BlogPost.class).withId(BlogPostId);
                                blogList.add(blogPost);

                                myAdapter.notifyDataSetChanged();
                            }
                        }

                    }

                }
            });
        }
    }



}