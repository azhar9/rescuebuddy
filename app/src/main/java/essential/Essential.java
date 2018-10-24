package essential;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.astudios.rescuebuddy.R;
import com.onurkaganaldemir.ktoastlib.KToast;


public class Essential {


    public static final String BASE_URL = "http://35.200.240.176/";
    public static final String REGISTER_URL = BASE_URL + "register";
    public static final String BAG_URL = BASE_URL + "gobag";
    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String LOGOUT_URL = BASE_URL+"logout";
    public static final String DISASTER_INFO_URL = BASE_URL + "disaster";


    public static final String PACKAGE_NAME = "com.astudios.rescuebuddy";

    public static final String SOMETHING_OCCURED = "Something Occured!";

    public static final String NOT_VALID_NUM = "Not a VALID number!";


    //toast constants
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int INFO = 2;
    public static final int NORMAL = 3;
    public static final int WARNING = 4;


    //keys
    public static final String NAME_KEY = "name";
    public static final String EMAIL_KEY = "email";
    public static final String AGE_KEY = "age";
    public static final String GENDER_KEY = "gender";
    public static final String MOBILE_KEY = "contact";
    public static final String HEIGHT_KEY = "height";
    public static final String WEIGHT_KEY = "weight";
    public static final String PASS_KEY = "pass";
    public static final String ID_KEY = "_id";
    //public static final String LOGIN_SAVED = "loginsaved";

    public static final String WIFI_NAME = "nasadm_rescue";
    public static final String NO_INTERNET = "No Internet!";
    public static final String REQUIRED = "This field is required";
    public static final String FILL_FORM = "Fill the Form";
    public static final String LOGIN_FAILED = "Login Failed";
    public static final String LOGIN_SUCCESS = "Login Success";
    public static final String LOGIN = "Logging into App";
    public static final String REGISTERED_SUCCESS = "Registration Success";
    public static final String REGISTERED_FAILED = "Registration FAILED!";
    public static final String APP_NAME = "Disaster Management";


    private Dialog dialog;
    private io.supercharge.funnyloader.FunnyLoader loader;
    private Context context;
    public static boolean showToast = true;


    private Essential() {

    }

    public Essential(Context context) {
        this.context = context;
    }

    public void show(String msg, int status) {
        switch (status) {
            case ERROR:
                KToast.errorToast((AppCompatActivity) context, msg, Gravity.BOTTOM, KToast.LENGTH_SHORT);
                break;
            case SUCCESS:
                KToast.successToast((AppCompatActivity) context, msg, Gravity.BOTTOM, KToast.LENGTH_SHORT);
                break;
            case INFO:
                KToast.infoToast((AppCompatActivity) context, msg, Gravity.BOTTOM, KToast.LENGTH_SHORT);
                break;
            case NORMAL:
                KToast.normalToast((AppCompatActivity) context, msg, Gravity.BOTTOM, KToast.LENGTH_SHORT);
                break;
            case WARNING:
                KToast.warningToast((AppCompatActivity) context, msg, Gravity.BOTTOM, KToast.LENGTH_SHORT);
                break;

        }
    }

    public boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        return false;
    }


    public static void hideKeyboard(AppCompatActivity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    public void setFont(TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/opensans.ttf"));
    }

    public void showDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loader);
        loader = dialog.findViewById(R.id.loaderId);

        if (!loader.isRunning())
            loader.start();
        dialog.setCancelable(false);
        if (!dialog.isShowing())
            dialog.show();
    }

    public void cancelDialog() {
        loader.stop();
        if (dialog.isShowing())
            dialog.cancel();
    }
}
