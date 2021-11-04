package com.urrecliner.markupphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.urrecliner.markupphoto.Vars.SUFFIX_JPG;
import static com.urrecliner.markupphoto.Vars.buildBitMap;
import static com.urrecliner.markupphoto.Vars.buildDB;
import static com.urrecliner.markupphoto.Vars.fabUndo;
import static com.urrecliner.markupphoto.Vars.mContext;
import static com.urrecliner.markupphoto.Vars.mActivity;
import static com.urrecliner.markupphoto.Vars.mainMenu;
import static com.urrecliner.markupphoto.Vars.multiMode;
import static com.urrecliner.markupphoto.Vars.nowPos;
import static com.urrecliner.markupphoto.Vars.photoAdapter;
import static com.urrecliner.markupphoto.Vars.photos;
import static com.urrecliner.markupphoto.Vars.spanWidth;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private static final int unMarkedTextColor = Color.parseColor("#000000");
    private static final int markedTextColor = Color.parseColor("#AAAAAA");

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iVImage;
        TextView tVInfo;

        ViewHolder(final View itemView) {
            super(itemView);
            tVInfo = itemView.findViewById(R.id.info);
            tVInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (multiMode)
                        toggleCheckBox(getBindingAdapterPosition());
                    else
                        loadMarkUpActivity();
                }
            });

            iVImage = itemView.findViewById(R.id.image);
            iVImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (multiMode)
                        toggleCheckBox(getAbsoluteAdapterPosition());
                    else
                        loadMarkUpActivity();
                }
            });

            iVImage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (multiMode)
                        loadMarkUpActivity();
                    else
                        toggleCheckBox(getAdapterPosition());
                    return true;
                }
            });
//            cbCheck = itemView.findViewById(R.id.checkBox);
//            cbCheck.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (multiMode)
//                        toggleCheckBox(getAdapterPosition());
//                }
//            });
        }

        private void loadMarkUpActivity() {
            nowPos = getAbsoluteAdapterPosition();
            Photo photo = photos.get(nowPos);
            Bitmap photoMap = photo.getBitmap().copy(Bitmap.Config.ARGB_8888, false);
            boolean checked = !photo.isChecked();
            iVImage.setImageBitmap(checked ? buildBitMap.makeChecked(photoMap):photoMap);
            Intent intent = new Intent(mContext, MarkupWithPlace.class);
            mActivity.startActivity(intent);
        }

        private void toggleCheckBox(int position) {

            fabUndo = mActivity.findViewById(R.id.undo);
            fabUndo.setVisibility(View.VISIBLE);
            Photo photo = photos.get(position);
            String shortName = photo.getShortName();
            Bitmap photoMap = photo.getBitmap().copy(Bitmap.Config.ARGB_8888, false);
            boolean checked = !photo.isChecked();
            iVImage.setImageBitmap(checked ? buildBitMap.makeChecked(photoMap):photoMap);
            tVInfo.setTextColor((shortName.endsWith(SUFFIX_JPG))? markedTextColor:unMarkedTextColor);
            tVInfo.setText(shortName);
            photo.setChecked(checked);
            photos.set(position, photo);
//            cbCheck.setChecked(checked);
            if (!multiMode) {
                multiMode = true;
                int start = position - 12; if (start < 0) start = 0;
                int finish = position + 12; if (finish > photos.size()) finish = photos.size();
                for (int pos = start; pos < finish; pos++)
                    photoAdapter.notifyItemChanged(pos);
            }
            MenuItem item = mainMenu.findItem(R.id.action_Delete);
            item.setVisible(true);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Photo photo = photos.get(position);
        if (photo.getOrientation() == 99 || photo.getBitmap() == null) {
            photo = buildDB.getPhotoWithMap(photo);
            photos.set(position, photo);
        }
        String shortName = photo.getShortName();
        boolean checked = photo.isChecked();
        Bitmap photoMap = (checked) ? photo.getBitmap().copy(Bitmap.Config.RGB_565, false):photo.getBitmap();
        boolean landscape = photoMap.getWidth() > photoMap.getHeight();
        int width = (landscape) ? spanWidth:spanWidth * 6 / 10;
        int height = width*photoMap.getHeight()/photoMap.getWidth();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.iVImage.getLayoutParams();
        params.width = width; params.height = height;
        holder.iVImage.setLayoutParams(params);
        holder.iVImage.setImageBitmap(checked ? buildBitMap.makeChecked(photoMap):photoMap);
        holder.tVInfo.setTextColor((shortName.endsWith(SUFFIX_JPG))? markedTextColor:unMarkedTextColor);
        holder.tVInfo.setText(shortName);
    }
}

/*
The values 1-8 represent the following descriptions (as shown by utilities that support EXIF field decode):

EXIF Orientation Value	Row #0 is:	Column #0 is:
1                   	Top	        Left side
2*	                    Top	        Right side
3	                    Bottom	    Right side
4*	                    Bottom	    Left side
5*	                    Left side	Top
6	                    Right side	Top
7*	                    Right side	Bottom
8	                    Left side	Bottom
NOTE: Values with "*" are uncommon since they represent "flipped" orientations.

 */