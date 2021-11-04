package com.urrecliner.markupphoto;

import static com.urrecliner.markupphoto.Vars.buildBitMap;
import static com.urrecliner.markupphoto.Vars.buildDB;
import static com.urrecliner.markupphoto.Vars.databaseIO;
import static com.urrecliner.markupphoto.Vars.mContext;
import static com.urrecliner.markupphoto.Vars.photoView;
import static com.urrecliner.markupphoto.Vars.photos;
import static com.urrecliner.markupphoto.Vars.squeezeDB;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


class BuildDB {

    private static boolean isCanceled = false;
    private static Snackbar snackBar = null;
    private static View mainLayout;

    void fillUp(View view) {

        isCanceled = false;
        mainLayout = view;
        try {
            new buildSumNailDB().execute("start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void cancel() {
        isCanceled = true;
        if (snackBar != null) {
            snackBar.dismiss();
            snackBar = null;
        }
    }

    static class buildSumNailDB extends AsyncTask<String, String, String> {

        int count, totCount;

        @Override
        protected void onPreExecute() {
            count = 0;
            totCount = photos.size();
            photoView.setBackgroundColor(Color.CYAN);
            String s = "Building SumNails for "+totCount+" photos";
            snackBar = Snackbar.make(mainLayout, s, Snackbar.LENGTH_INDEFINITE);
            snackBar.setAction("Hide", v -> {
                snackBar.dismiss();
                snackBar = null;
                Toast.makeText(mContext, "Status Bar hidden", Toast.LENGTH_LONG).show();
            });
            snackBar.show();
        }

        final String SAY_COUNT = "sc";
        @Override
        protected String doInBackground(String... inputParams) {

            for (int pos = 0; pos < photos.size(); pos++) {
                if (isCanceled)
                    break;
                try {
                    Photo photo = photos.get(pos);
                    if (photo.getBitmap() == null) {
                        photo = buildDB.getPhotoWithMap(photo);
                        photos.set(pos, photo);
                        publishProgress(SAY_COUNT);
                    }
                } catch (Exception e) {
                    break;
                }
                count++;
            }
            return "done";
        }

        @Override
        protected void onPostExecute(String doI) {

            if (snackBar != null) {
                snackBar.dismiss();
                snackBar = null;
            }
            photoView.setBackgroundColor(Color.WHITE);
            squeezeDB.run();
        }
    }

    Photo getPhotoWithMap(Photo photoIn) {

        Photo photo = databaseIO.retrievePhoto(photoIn);
        if (photo.getBitmap() == null) {
            photo = buildBitMap.makeSumNail(photoIn);
            return databaseIO.retrievePhoto(photo);
        }
        return photo;
    }
}