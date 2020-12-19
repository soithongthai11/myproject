package com.entertaiment.demo.commonbase;

import android.app.Application;

/**
 * Specialized tasks that need to run before the creation of your first activity
 * Global initialization that needs to be shared across all components
 * Static methods for easy access to static immutable data such as a shared network client object
 * Store any mutable shared data using persistence strategies such as files, SharedPreferences or SQLite.
 * Init Database to using in application
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
