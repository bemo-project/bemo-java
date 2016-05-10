package io.github.bemo_project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v1-wizard on 2.5.16.
 */
class JsonMaker {
    private static Gson gson = new Gson();

    static String init(String xHookScriptUrl) {
        return gson.toJson(new InitJson(xHookScriptUrl));
    }

    static String handlers(List<AbstractHandler> handlers) {
        List<JsonHandler> jsonHandlers = new ArrayList<JsonHandler>(handlers.size());
        for (AbstractHandler handler : handlers) {
            String body = makeBody(handler);
            jsonHandlers.add(new JsonHandler(handler.getUid(), body, handler.getHeaders(), handler.getStatus()));
        }
        return gson.toJson(new JsonHandlers(jsonHandlers));
    }

    static String handler(AbstractHandler handler) {
        String body = makeBody(handler);
        return gson.toJson(new JsonHandler(handler.getUid(), body, handler.getHeaders(), handler.getStatus()));
    }

    private static String makeBody(AbstractHandler handler) {
        Object rawBody = handler.getBody();
        String body;
        if (isString(rawBody)) {
            body = (String) handler.getBody();
        } else {
            body = gson.toJson(handler.getBody());
        }
        return body;
    }

    static String makeBody(AbstractCall call) {
        Object rawBody = call.getBody();
        String body;
        if (isString(rawBody)) {
            body = (String) call.getBody();
        } else {
            body = gson.toJson(call.getBody());
        }
        return body;
    }

    private static boolean isString(Object rawBody) {
        return rawBody instanceof String;
    }

    public static List<AbstractCall> decodeCalls(String jsonAsString) {
        Type listType = new TypeToken<ArrayList<CallJson>>() {
        }.getType();
        return gson.fromJson(jsonAsString, listType);
    }

    private static class CallJson extends AbstractCall {
        private String url;
        private String method;
        private String body;
        private Map<String, String> headers;

        @Override
        public String getMethod() {
            return method;
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public Object getBody() {
            return body;
        }

        @Override
        public Map<String, String> getHeaders() {
            return headers;
        }
    }

    private static class InitJson {

        private InitJson(String XHookScriptURL) {
            this.XHookScriptURL = XHookScriptURL;
        }

        private String XHookScriptURL;
    }

    private static class JsonHandlers {
        private JsonHandlers(List<JsonHandler> handlers) {
            this.handlers = handlers;
        }

        private List<JsonHandler> handlers;
    }

    private static class JsonHandler {

        public JsonHandler(String uid, String body, Map<String, String> headers, int status) {
            this.uid = uid;
            this.body = body;
            this.headers = headers;
            this.status = status;
            this.statusText = STATUS_TEXT_MAP.get(status);
        }

        private String uid;
        private String body;
        private Map<String, String> headers;
        private int status;
        private String statusText;
    }

    //Mapping status codes with ajax status texts.
    private final static Map<Integer, String> STATUS_TEXT_MAP = new HashMap<Integer, String>();

    static {
        STATUS_TEXT_MAP.put(100, "Continue");
        STATUS_TEXT_MAP.put(101, "Switching Protocol");

        STATUS_TEXT_MAP.put(200, "OK");

        STATUS_TEXT_MAP.put(201, "Created");
        STATUS_TEXT_MAP.put(202, "Accepted");
        STATUS_TEXT_MAP.put(203, "Non-Authoritative Information");
        STATUS_TEXT_MAP.put(204, "No Content");
        STATUS_TEXT_MAP.put(205, "Reset Content");
        STATUS_TEXT_MAP.put(206, "Partial Content");

        STATUS_TEXT_MAP.put(300, "Multiple Choice");
        STATUS_TEXT_MAP.put(301, "Moved Permanently");
        STATUS_TEXT_MAP.put(302, "Found");
        STATUS_TEXT_MAP.put(303, "See Other");
        STATUS_TEXT_MAP.put(304, "Not Modified");
        STATUS_TEXT_MAP.put(305, "Use Proxy");
        STATUS_TEXT_MAP.put(306, "unused");
        STATUS_TEXT_MAP.put(307, "Temporary Redirect");
        STATUS_TEXT_MAP.put(308, "Permanent Redirect");

        STATUS_TEXT_MAP.put(400, "Bad Request");
        STATUS_TEXT_MAP.put(401, "Unauthorized");
        STATUS_TEXT_MAP.put(402, "Payment Required");
        STATUS_TEXT_MAP.put(403, "Forbidden");
        STATUS_TEXT_MAP.put(404, "Not Found");
        STATUS_TEXT_MAP.put(405, "Method Not Allowed");
        STATUS_TEXT_MAP.put(406, "Not Acceptable");
        STATUS_TEXT_MAP.put(407, "Proxy Authentication Required");
        STATUS_TEXT_MAP.put(408, "Request Timeout");
        STATUS_TEXT_MAP.put(409, "Conflict");
        STATUS_TEXT_MAP.put(410, "Gone");
        STATUS_TEXT_MAP.put(411, "Length Required");
        STATUS_TEXT_MAP.put(412, "Precondition");
        STATUS_TEXT_MAP.put(413, "Payload Too Large");
        STATUS_TEXT_MAP.put(414, "URI Too Long");
        STATUS_TEXT_MAP.put(415, "Unsupported Media Type");
        STATUS_TEXT_MAP.put(416, "Requested Range Not Satisfiable");
        STATUS_TEXT_MAP.put(417, "Expectation Failed");
        STATUS_TEXT_MAP.put(418, "I'm a teapot"); //lol
        STATUS_TEXT_MAP.put(421, "Misdirected Request");
        STATUS_TEXT_MAP.put(426, "Upgrade Required");
        STATUS_TEXT_MAP.put(428, "Precondition Required");
        STATUS_TEXT_MAP.put(429, "Too Many Requests");
        STATUS_TEXT_MAP.put(431, "Request Header");

        STATUS_TEXT_MAP.put(500, "Internal Server Error");
        STATUS_TEXT_MAP.put(501, "Not Implemented");
        STATUS_TEXT_MAP.put(502, "Bad Gateway");
        STATUS_TEXT_MAP.put(503, "Service Unavailable");
        STATUS_TEXT_MAP.put(504, "Gateway Timeout");
        STATUS_TEXT_MAP.put(505, "HTTP Version Not Supported");
        STATUS_TEXT_MAP.put(506, "Variant Also Negotiates");
        STATUS_TEXT_MAP.put(507, "Variant Also Negotiates");
        STATUS_TEXT_MAP.put(511, "Network Authentication Required");
    }
}
