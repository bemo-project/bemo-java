package ru.electrictower.bemo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.StringWriter;

import static ru.electrictower.bemo.Constants.Velocity.*;
import static ru.electrictower.bemo.Constants.*;

/**
 * Created by v1_wizard.
 */
public class Bemo {
    private JavascriptExecutor jsExecutor;
    private VelocityEngine velocityEngine = new VelocityEngine();
    private VelocityContext velocityContext = new VelocityContext();

    public Bemo(WebDriver webDriver, String xHookUrl) {
        jsExecutor = (JavascriptExecutor) webDriver;
        velocityEngine.init(VELOCITY_PROPERTIES);
        velocityContext.put(URL_TO_JS_MOCK, xHookUrl);
    }

    public Bemo(WebDriver webDriver) {
        this(webDriver, DEFAULT_X_HOOK_URL);
    }

    public void inject() {
        Template t = velocityEngine.getTemplate(INJECT_MOCK_TEMPLATE);
        StringWriter writer = new StringWriter();
        t.merge(velocityContext, writer);
        jsExecutor.executeScript(writer.toString());
    }

    public void enable() {
        jsExecutor.executeScript(JS_ENABLE_X_HOOK);
    }

    public void disable() {
        jsExecutor.executeScript(JS_DISABLE_X_HOOK);
    }

    public Mock mockFor(String url) {
        // TODO
        return new Mock();
    }
}
