package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class ListFragment extends Fragment {

    private OnItemImageClickListener mCallBack;

    public ListFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnItemImageClickListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must implement OnItemImageClickListener");
        }
    }

    public interface OnItemImageClickListener {
        void onItemClickImage(int position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        GridView gridView = view.findViewById(R.id.images_grid_view);
        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallBack.onItemClickImage(position);
            }
        });

        return view;
    }

    // Custom adapter class that displays a list of Android-Me images in a GridView
    private class MasterListAdapter extends BaseAdapter {

        // Keeps track of the context and list of images to display
        private Context mContext;
        private List<Integer> mImageIds;

        /**
         * Constructor method
         *
         * @param imageIds The list of images to display
         */
        public MasterListAdapter(Context context, List<Integer> imageIds) {
            mContext = context;
            mImageIds = imageIds;
        }

        /**
         * Returns the number of items the adapter will display
         */
        @Override
        public int getCount() {
            return mImageIds.size();
        }

        @Override
        public Object getItem(int i) {
            return mImageIds.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        /**
         * Creates a new ImageView for each item referenced by the adapter
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // If the view is not recycled, this creates a new ImageView to hold an image
                imageView = new ImageView(mContext);
                // Define the layout parameters
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            // Set the image resource and return the newly created ImageView
            imageView.setImageResource(mImageIds.get(position));
            return imageView;
        }
    }
}
