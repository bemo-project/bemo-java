package ru.electrictower.bemo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v1-wizard on 30.4.16.
 */
public class BeMo {
    public final static String DEFAULT_X_HOOK_URL = "https://jpillora.com/xhook/dist/xhook.js";

    private JavascriptExecutor jsExecutor;
    private String xHookUrl;
    private List<AbstractHandler> handlers = new ArrayList<AbstractHandler>();

    private BeMo(WebDriver webDriver, String xHookUrl) {
        this.xHookUrl = xHookUrl;
        this.jsExecutor = (JavascriptExecutor) webDriver;
    }

    public static BeMo sessionWith(WebDriver webDriver) {
        return new BeMo(webDriver, DEFAULT_X_HOOK_URL);
    }

    public static BeMo sessionWith(WebDriver webDriver, String xHookUrl) {
        return new BeMo(webDriver, xHookUrl);
    }

    public void addHandler(AbstractHandler handler) {
        handler.setJsExecutor(jsExecutor);
        handlers.add(handler);
    }

    public void inject() {
        String initJson = JsonMaker.initJson(xHookUrl);
        jsExecutor.executeScript(JsMaker.initializeJs(initJson));
        String handlersJson = JsonMaker.handlersJson(handlers);
        jsExecutor.executeScript(JsMaker.handlersJs(handlersJson));
        jsExecutor.executeScript(JsMaker.enableJs());
    }

    public void release() {
        jsExecutor.executeScript(JsMaker.releaseJs());
    }
}
