package ru.electrictower.bemo;

import java.util.Scanner;

/**
 * Created by v1-wizard on 30.4.16.
 */
class JsMaker {
    private final static String ENABLE_TEMPLATE = getTemplateAsString("xhook/enable.tmpl");
    private final static String HANDLERS_TEMPLATE = getTemplateAsString("xhook/handlers.tmpl");
    private final static String INITIALIZE_TEMPLATE = getTemplateAsString("xhook/initialize.tmpl");
    private final static String RELEASE_TEMPLATE = getTemplateAsString("xhook/release.tmpl");
    private final static String CALL_COUNT_TEMPLATE = getTemplateAsString("asserts/call_count.tmpl");

    private final static ClassLoader CLASS_LOADER = JsMaker.class.getClassLoader();


    private static String getTemplateAsString(String templateName) {
        return new Scanner(CLASS_LOADER.getResourceAsStream(templateName), "UTF-8").useDelimiter("\\A").next();
    }

    static String enableJs() {
        return ENABLE_TEMPLATE;
    }

    static String handlersJs(String context) {
        return String.format(HANDLERS_TEMPLATE, context);
    }

    static String initializeJs(String context) {
        return String.format(INITIALIZE_TEMPLATE, context);
    }

    static String releaseJs() {
        return RELEASE_TEMPLATE;
    }

    static String callCountJs(String context) {
        return String.format(CALL_COUNT_TEMPLATE, context);
    }

}
