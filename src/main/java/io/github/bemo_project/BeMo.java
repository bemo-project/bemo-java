package io.github.bemo_project;

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

    public BeMo(WebDriver webDriver, String xHookUrl) {
        this.xHookUrl = xHookUrl;
        this.jsExecutor = (JavascriptExecutor) webDriver;
    }

    public BeMo(WebDriver webDriver) {
        this(webDriver, DEFAULT_X_HOOK_URL);
    }

    public BeMo addHandler(AbstractHandler handler) {
        handler.setJsExecutor(jsExecutor);
        handlers.add(handler);
        return this;
    }

    public void inject() {
        String initJson = JsonMaker.init(xHookUrl);
        jsExecutor.executeScript(JsMaker.initialize(initJson));
        String jsonHandlers = JsonMaker.handlers(handlers);
        jsExecutor.executeScript(JsMaker.handlers(jsonHandlers));
        jsExecutor.executeScript(JsMaker.enable());
    }

    public void release() {
        handlers.clear();
        jsExecutor.executeScript(JsMaker.release());
    }
}
