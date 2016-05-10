package io.github.bemo_project;

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
    private final static String GET_CALLS_TEMPLATE = getTemplateAsString("asserts/get_calls.tmpl");

    private static String getTemplateAsString(String templateName) {
        return new Scanner(ClassLoader.getSystemResourceAsStream(templateName), "UTF-8").useDelimiter("\\A").next();
    }

    static String enable() {
        return ENABLE_TEMPLATE;
    }

    static String handlers(String context) {
        return String.format(HANDLERS_TEMPLATE, context);
    }

    static String initialize(String context) {
        return String.format(INITIALIZE_TEMPLATE, context);
    }

    static String release() {
        return RELEASE_TEMPLATE;
    }

    static String callCount(String context) {
        return String.format(CALL_COUNT_TEMPLATE, context);
    }

    static String getCalls(String context) {
        return String.format(GET_CALLS_TEMPLATE, context);
    }

}
