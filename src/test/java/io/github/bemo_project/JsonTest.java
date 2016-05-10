package io.github.bemo_project;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by v1-wizard on 2.5.16.
 */
public class JsonTest {

    class Handler extends AbstractHandler{

        @Override
        public Object getBody() {
            return "{}";
        }

        @Override
        public Map<String, String> getHeaders() {
            return null;
        }

        @Override
        public String getUrlPart() {
            return "test";
        }

        @Override
        public int getStatus() {
            return 200;
        }
    }

    @Test
    public void verity_init_json() {
        System.out.println(JsonMaker.init("hui"));
    }

    @Test
    public void verify_handlers_json() {
        List<AbstractHandler> handlers = new ArrayList<AbstractHandler>();
        handlers.add(new Handler());
        System.out.println(JsonMaker.handlers(handlers));
    }
}
