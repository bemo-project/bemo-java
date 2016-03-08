package ru.electrictower.bemo;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
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
    private Template injectMockTemplate = null;
    private Template createMocksOnPageTemplate = null;
    private Template getCallCountTemplate = null;
    private Template getAjaxRequestTemplate = null;

    public BeMo(WebDriver webDriver, String xHookUrl) {
        jsExecutor = (JavascriptExecutor) webDriver;

        initVelocity(xHookUrl);
    }

    private void initVelocity(String xHookUrl) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init(VELOCITY_PROPERTIES);
        velocityContext.put(URL_TO_JS_MOCK, xHookUrl);
        velocityContext.put(MOCKS_IN_TEMPLATE, mocks);

        injectMockTemplate = velocityEngine.getTemplate(INJECT_MOCK_TEMPLATE);
        createMocksOnPageTemplate = velocityEngine.getTemplate(CREATE_MOCKS_ON_PAGE_TEMPLATE);
        getCallCountTemplate = velocityEngine.getTemplate(GET_CALL_COUNT_TEMPLATE);
        getAjaxRequestTemplate = velocityEngine.getTemplate(GET_AJAX_REQUEST_TEMPLATE);
    }

    public BeMo(WebDriver webDriver) {
        this(webDriver, DEFAULT_X_HOOK_URL);
    }

    public BeMo inject() {
        StringWriter writer = new StringWriter();
        injectMockTemplate.merge(velocityContext, writer);
        jsExecutor.executeScript(writer.toString());
        return this;
    }

    public void enable() {
        StringWriter writer = new StringWriter();
        createMocksOnPageTemplate.merge(velocityContext, writer);
        jsExecutor.executeScript(writer.toString());
        jsExecutor.executeScript(JS_ENABLE_X_HOOK);
    }

    public void disable() {
        jsExecutor.executeScript(JS_DESTROY_ALL_MOCKS);
        jsExecutor.executeScript(JS_DISABLE_X_HOOK);
        mocks.clear();
    }

    public IMockBuilder mockFor(String urlPart) {
        Mock mock = new Mock();
        mocks.put(StringEscapeUtils.escapeJavaScript(urlPart), mock);
        return mock;
    }

    public int getCallCountFor(String urlPart) {
        Mock mock = mocks.get(StringEscapeUtils.escapeJavaScript(urlPart));
        StringWriter writer = new StringWriter();
        velocityContext.put(MOCK_IN_TEMPLATE, mock);
        getCallCountTemplate.merge(velocityContext, writer);
        Long result = (Long) jsExecutor.executeScript(writer.toString());
        return result != null ? result.intValue() : 0;
    }

    @SuppressWarnings("unchecked")
    public AjaxRequest getRequestFor(String urlPart) {
        Mock mock = mocks.get(StringEscapeUtils.escapeJavaScript(urlPart));
        StringWriter writer = new StringWriter();
        velocityContext.put(MOCK_IN_TEMPLATE, mock);
        getAjaxRequestTemplate.merge(velocityContext, writer);
        List<String> jsList = (List<String>) jsExecutor.executeScript(writer.toString());
        return new AjaxRequest(jsList);
    }
}
