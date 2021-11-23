package com.example.gallery;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class FoldersFragment extends Fragment {
    Context context;
    RecyclerView folderPicturesRecView;
    private ArrayList<String> folderPaths;

    FoldersFragment(Context context) {this.context = context;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View foldersFragment = inflater.inflate(R.layout.folder_picture_fragment, container, false);

        ((MainActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        folderPicturesRecView = (RecyclerView) foldersFragment.findViewById(R.id.folderPicturesRecView);
        folderPaths = new ArrayList<String>();

        readFolders();
        
        return foldersFragment;
    }

    private void readFolders() {
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File inputFile = new File(sdPath, "Pictures");
        File[] folders = inputFile.listFiles();

        try {
            for (File folder : folders) {
                folderPaths.add(folder.getAbsolutePath());
            }
        }
        catch (Exception e) {
            getActivity().finish();
        }

        loadFolders();
    }
    
    private void loadFolders() {
        FolderAdapter folderAdapter = new FolderAdapter(context, folderPaths);
        folderPicturesRecView.setAdapter(folderAdapter);
        folderPicturesRecView.setLayoutManager(new GridLayoutManager(context, 2));
    }
}
