package ru.electrictower.bemo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by v1_wizard.
 */
public class Bemo {
    private JavascriptExecutor jsExecutor;
    private VelocityEngine velocityEngine;
    private VelocityContext velocityContext;

    private static final String INJECT_MOCK_TEMPLATE = "inject_mock.vm";

    public Bemo() {
        initVelocityEngineAndContext();
    }

    private void initVelocityEngineAndContext() {
        this.velocityEngine = new VelocityEngine();
        this.velocityContext = new VelocityContext();
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "file");
        properties.setProperty(
                "file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
        );
        velocityEngine.init(properties);
        velocityContext.put("url_to_mock", "https://jpillora.com/xhook/dist/xhook.js");
    }

    public void inject(WebDriver webDriver) {
        this.jsExecutor = (JavascriptExecutor) webDriver;

        Template t = velocityEngine.getTemplate(INJECT_MOCK_TEMPLATE);
        StringWriter writer = new StringWriter();
        t.merge(velocityContext, writer);
        jsExecutor.executeScript(writer.toString());
    }

    public FirefoxProfile getFirefoxProfile() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("security.mixed_content.block_active_content", false);
        firefoxProfile.setPreference("security.mixed_content.block_display_content", true);
        return firefoxProfile;
    }

    public void enable() {
        // TODO
    }

    public void disable() {
        // TODO
    }

    public void mock(Action action) {
        // TODO
    }
}
