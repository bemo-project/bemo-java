package ru.electrictower.bemo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static ru.electrictower.bemo.BeMoConstants.*;
import static ru.electrictower.bemo.BeMoConstants.Velocity.*;

/**
 * Created by v1_wizard.
 */
public class BeMo {
    private JavascriptExecutor jsExecutor;
    private VelocityEngine velocityEngine = new VelocityEngine();
    private VelocityContext velocityContext = new VelocityContext();
    private Map<String, MockImpl> mocks = new HashMap<String, MockImpl>();

    public BeMo(WebDriver webDriver, String xHookUrl) {
        jsExecutor = (JavascriptExecutor) webDriver;
        velocityEngine.init(VELOCITY_PROPERTIES);
        velocityContext.put(URL_TO_JS_MOCK, xHookUrl);
    }

    public BeMo(WebDriver webDriver) {
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

    public IResponseChoice mockFor(String url) {
        MockImpl mock = new MockImpl();
        mocks.put(url, mock);
        return mock;
    }

    public void mountAll() {
        throw new NotImplementedException();
    }

    public void mount(String url) {
        MockImpl mock = mocks.get(url);
        throw new NotImplementedException();
    }

    public void demountAll() {
        throw new NotImplementedException();
    }

    public void demount(String url) {
        throw new NotImplementedException();
    }
}
