package com.urrecliner.markupphoto;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.urrecliner.markupphoto.placeNearby.PlaceInfo;
import com.urrecliner.markupphoto.placeNearby.PlaceRetrieve;

import java.util.ArrayList;
import java.util.List;

public class Vars {
    static Context mContext;
    static Activity mActivity;
    static Activity dirActivity;
    static Activity placeActivity;

    static PhotoAdapter photoAdapter;
    static DirectoryAdapter directoryAdapter;
    static Bitmap signatureMap;
    static RecyclerView photoView;

    static Utils utils;
    static SqueezeDB squeezeDB;
    static BuildDB buildDB;
    static MakeDirFolder makeDirFolder;
    static MarkUpOnePhoto markUpOnePhoto;
    static BuildBitMap buildBitMap;
    static ColorDraw colorDraw;

    static String shortFolder = null;
    static String longFolder = null;
    static int nowPos;
    static String nowPlace, nowAddress;
    static int spanWidth, sizeX;
    static String copyPasteText = "";
    static String copyPasteGPS;
    static boolean dirNotReady = true;

    static boolean multiMode = false;
    static List<Photo> photos = null;
    static ArrayList<DirectoryFolder> dirFolders = null;

    static Menu mainMenu;

    static DatabaseIO databaseIO = null;
    static final String SUFFIX_JPG = "_ha.jpg";

    static ImageView colorPlate;
    static ImageView colorRange;
    static SeekBar colorAlpha;
    static int colorRGB;
    static int markTextInColor;
    static int markTextOutColor;
    static TextView tvPlaceAddress;
    static String byPlaceName = "";
    static MenuItem menuItem;
    static FloatingActionButton fabUndo;

    /* --- shared Preference **/
    static SharedPreferences sharedPref;
    static String sharedRadius = "";
    static String sharedSort = "";
    static boolean sharedAutoLoad = false;
    static String sharedSpan = "";
    static String sharedAlpha = "";

    /* --- place select related variables --- */
    public static boolean nowDownLoading = false;
    public static ArrayList<PlaceInfo> placeInfos = null;

    static Activity selectActivity;
    static String [] iconNames = { "question",
            "airport", "amusement", "aquarium", "art_gallery", "atm", "baby",
            "bank_dollar", "bank_euro", "bank_pound", "bank_yen", "bar", "barber",
            "baseball", "beach", "bicycle", "bus", "cafe", "camping", "car_dealer",
            "car_repair", "casino", "civic_building", "convenience", "courthouse",
            "dentist", "doctor", "electronics", "fitness", "flower", "gas_station",
            "generic_business", "generic_recreational", "geocode", "golf", "government",
            "historic", "jewelry", "library", "lodging", "monument", "mountain",
            "movies", "museum", "pet", "police", "post_office", "repair", "restaurant",
            "school", "shopping", "ski", "stadium", "supermarket", "taxi", "tennis",
            "train", "travel_agent", "truck", "university", "wine", "worship_christian",
            "worship_general", "worship_hindu", "worship_islam", "worship_jewish", "zoo",
            "park", "bank","worship_dharma", "pharmacy", "parking", "hospital",
            "cemetery_grave"
    };
    static int [] iconRaws = { R.raw.question,
            R.raw.airport, R.raw.amusement, R.raw.aquarium, R.raw.art_gallery, R.raw.atm, R.raw.baby,
            R.raw.bank_dollar, R.raw.bank_euro, R.raw.bank_pound, R.raw.bank_yen, R.raw.bar, R.raw.barber,
            R.raw.baseball, R.raw.beach, R.raw.bicycle, R.raw.bus, R.raw.cafe, R.raw.camping, R.raw.car_dealer,
            R.raw.car_repair, R.raw.casino, R.raw.civic_building, R.raw.convenience, R.raw.courthouse,
            R.raw.dentist, R.raw.doctor, R.raw.electronics, R.raw.fitness, R.raw.flower, R.raw.gas_station,
            R.raw.generic_business, R.raw.generic_recreational, R.raw.geocode, R.raw.golf, R.raw.government,
            R.raw.historic, R.raw.jewelry, R.raw.library, R.raw.lodging, R.raw.monument, R.raw.mountain,
            R.raw.movies, R.raw.museum, R.raw.pet, R.raw.police, R.raw.post_office, R.raw.repair, R.raw.restaurant,
            R.raw.school, R.raw.shopping, R.raw.ski, R.raw.stadium, R.raw.supermarket, R.raw.taxi, R.raw.tennis,
            R.raw.train, R.raw.travel_agent, R.raw.truck, R.raw.university, R.raw.wine, R.raw.worship_christian,
            R.raw.worship_general, R.raw.worship_hindu, R.raw.worship_islam, R.raw.worship_jewish, R.raw.zoo,
            R.raw.park, R.raw.bank, R.raw.worship_dharma, R.raw.pharmacy, R.raw.parking, R.raw.hospital,
            R.raw.cemetery_grave
    };

    static ArrayList<TypeInfo> typeInfos = null;
    static TypeAdapter typeAdapter;
    static String placeType = "all";
    static int typeNumber = 0;
    static int[] typeIcons = {R.mipmap.place_holder, R.raw.restaurant, R.raw.cafe, R.raw.bar,
            R.raw.shopping, R.raw.shopping, R.raw.park, R.raw.worship_christian, R.raw.worship_islam,
            R.raw.parking, R.raw.school, R.raw.museum, R.raw.amusement, R.raw.amusement,
            R.raw.university,R.raw.atm, R.raw.zoo, R.raw.lodging,
            R.mipmap.place_holder};
    static String[] typeNames = {"all", "restaurant", "cafe", "bar",
            "store", "shopping_mall", "park", "church","mosque",
            "parking", "school", "museum", "tourist_attraction", "amusement",
            "university", "atm", "zoo", "lodging",
            "all"};

}