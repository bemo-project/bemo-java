package ru.electrictower.bemo;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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

    private Map<String, Mock> mocks = new HashMap<String, Mock>();

    private VelocityContext velocityContext = new VelocityContext();
    private Template inject_mock_template = null;
    private Template create_mocks_on_page_template = null;
    private Template get_call_count_template = null;

    public BeMo(WebDriver webDriver, String xHookUrl) {
        jsExecutor = (JavascriptExecutor) webDriver;

        initVelocity(xHookUrl);
    }

    private void initVelocity(String xHookUrl) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init(VELOCITY_PROPERTIES);
        velocityContext.put(URL_TO_JS_MOCK, xHookUrl);
        velocityContext.put(MOCKS_IN_TEMPLATE, mocks);

        inject_mock_template = velocityEngine.getTemplate(INJECT_MOCK_TEMPLATE);
        create_mocks_on_page_template = velocityEngine.getTemplate(CREATE_MOCKS_ON_PAGE_TEMPLATE);
        get_call_count_template = velocityEngine.getTemplate(GET_CALL_COUNT_TEMPLATE);
    }

    public BeMo(WebDriver webDriver) {
        this(webDriver, DEFAULT_X_HOOK_URL);
    }

    public BeMo inject() {
        StringWriter writer = new StringWriter();
        inject_mock_template.merge(velocityContext, writer);
        jsExecutor.executeScript(writer.toString());
        return this;
    }

    public void enable() {
        StringWriter writer = new StringWriter();
        create_mocks_on_page_template.merge(velocityContext, writer);
        jsExecutor.executeScript(writer.toString());
        jsExecutor.executeScript(JS_ENABLE_X_HOOK);
    }

    public void disable() {
        jsExecutor.executeScript(JS_DESTROY_ALL_MOCKS);
        jsExecutor.executeScript(JS_DISABLE_X_HOOK);
        mocks.clear();
    }

    public IMockBuilder mockFor(String url) {
        Mock mock = new Mock();
        mocks.put(StringEscapeUtils.escapeJavaScript(url), mock);
        return mock;
    }

    public long getCallCountFor(String url) {
        Mock mock = mocks.get(StringEscapeUtils.escapeJavaScript(url));
        StringWriter writer = new StringWriter();
        velocityContext.put(MOCK_IN_TEMPLATE, mock);
        get_call_count_template.merge(velocityContext, writer);
        Long result = (Long) jsExecutor.executeScript(writer.toString());
        return result != null ? result.intValue() : 0;
    }
}
