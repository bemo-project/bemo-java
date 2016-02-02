package ru.electrictower.bemo;

import java.util.Properties;

/**
 * Created by wizard.
 */
class Constants {

    public final static String DEFAULT_X_HOOK_URL = "https://jpillora.com/xhook/dist/xhook.js";

    // JS commands
    public final static String JS_ENABLE_X_HOOK = "xhook.enable();";
    public final static String JS_DISABLE_X_HOOK = "xhook.disable();";

    static class Velocity {
        // Velocity Configs
        public static final Properties VELOCITY_PROPERTIES;

        static {
            VELOCITY_PROPERTIES = new Properties();
            VELOCITY_PROPERTIES.setProperty("resource.loader", "file");
            VELOCITY_PROPERTIES.setProperty(
                    "file.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
            );
        }

        // Templates
        public static final String INJECT_MOCK_TEMPLATE = "inject_mock.vm";

        //Variables
        public static final String URL_TO_JS_MOCK = "url_to_mock";

    }
}
