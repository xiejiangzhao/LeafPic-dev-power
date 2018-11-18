package org.horaapps.leafpic;

import android.app.UiAutomation;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.Iconics;
import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.automationlib.util.AutomationUtil;
import com.tencent.qapmsdk.QAPM;

import org.horaapps.leafpic.util.ApplicationUtils;
import org.horaapps.leafpic.util.preferences.Prefs;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by dnld on 28/04/16.
 */
public class App extends MultiDexApplication {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ApplicationUtils.init(this);

        /** This process is dedicated to LeakCanary for heap analysis.
         *  You should not init your app in this process. */
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);


        AutomationUtil.enable();

        /**
         * Bug 1

        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
        /*
        Log.d("abis", Arrays.toString(Build.SUPPORTED_ABIS));
        QAPM.setProperty(QAPM.PropertyKeyAppInstance, this);
        QAPM.setProperty(QAPM.PropertyKeyAppId, "33e15431-1024").setProperty(QAPM.PropertyKeyAppVersion, "2.1").setProperty(QAPM.PropertyKeySymbolId, "e6ae1282-ceb8-4237-89bd-2d23d00a8e33");
        QAPM.setProperty(QAPM.PropertyKeyUserId, "11223344");
        QAPM.setProperty(QAPM.PropertyKeyLogLevel, QAPM.LevelDebug);
        QAPM.beginScene(QAPM.SCENE_ALL, QAPM.ModeAll);
        */

        registerFontIcons();
        initialiseStorage();
    }

    public static App getInstance() {
        return mInstance;
    }

    private void registerFontIcons() {
        Iconics.registerFont(new GoogleMaterial());
        Iconics.registerFont(new CommunityMaterial());
        Iconics.registerFont(new FontAwesome());
    }

    private void initialiseStorage() {
        Prefs.init(this);
        Hawk.init(this).build();
    }
}