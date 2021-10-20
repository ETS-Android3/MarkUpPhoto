package com.urrecliner.markupphoto;

import android.location.Location;

import java.util.Locale;

public class LatLngConv {

    static String latLng2String(double latitude, double longitude, double altitude) {
        StringBuilder builder = new StringBuilder();

        if (latitude < 0) {
            builder.append("S ");
        } else {
            builder.append("N ");
        }

        String latitudeDegrees = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builder.append(latitudeSplit[0]);
        builder.append("°");
        builder.append(latitudeSplit[1]);
        builder.append("'");
        builder.append(latitudeSplit[2]);
        builder.append("\"");

        builder.append(" ");

        if (longitude < 0) {
            builder.append("W ");
        } else {
            builder.append("E ");
        }

        String longitudeDegrees = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builder.append(longitudeSplit[0]);
        builder.append("°");
        builder.append(longitudeSplit[1]);
        builder.append("'");
        builder.append(longitudeSplit[2]);
        builder.append("\"");
        builder.append(String.format(Locale.ENGLISH, " A %.1f", altitude));
        //        nowLatLng = String.format(Locale.ENGLISH, "%.5f ; %.5f ; %.1f", latitude, longitude, altitude);

        return builder.toString();
    }

    static double DMS2GPS(String dmsString, String NEWS) {
        if (dmsString != null) {
            String[] dms = dmsString.split(",");
            if (dms.length == 3) {
                double degree = Double.parseDouble(dms[0].substring(0, dms[0].length() - 2));
                double min = Double.parseDouble(dms[1].substring(0, dms[1].length() - 2));
                double sec = Double.parseDouble(dms[2].substring(0, dms[2].indexOf("/")));
                double secDiv = Double.parseDouble(dms[2].substring(dms[2].indexOf("/") + 1));
                sec /= secDiv;
                double result = degree + min / 60f + sec / 3600f;
                if (NEWS.equals("S") || NEWS.equals("W"))
                    result *= -1;
                return result;
            } else
                return 0;
        }
        else
            return 0;
    }

    static double ALT2GPS(String altString, String UpDown) {
        if (altString != null) {
            double val = Double.parseDouble(altString.substring(0, altString.indexOf("/"))) /
                    Double.parseDouble(altString.substring(altString.indexOf("/")+1));
            return (UpDown == null || UpDown.equals("0")) ? val: -val;
        }
        else
            return 0;
    }

    static String GPS2DMS(double latitude) {
        latitude = Math.abs(latitude);
        int degree = (int) latitude;
        latitude *= 60;
        latitude -= (degree * 60.0d);
        int minute = (int) latitude;
        latitude *= 60;
        latitude -= (minute * 60.0d);
        int second = (int) (latitude * 10000.d);
        return degree + "/1," + minute + "/1," + second + "/10000";
    }

}