package ru.electrictower.bemo;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v1-wizard on 2.5.16.
 */
class JsonMaker {
    private static Gson gson = new Gson();

    static String initJson(String xHookScriptUrl) {
        return gson.toJson(new InitJson(xHookScriptUrl));
    }

    static String handlersJson(List<AbstractHandler> handlers) {
        return null; //TODO
    }

    private static class InitJson {

        private InitJson(String XHookScriptURL) {
            this.XHookScriptURL = XHookScriptURL;
        }

        private String XHookScriptURL;
    }

    private static class HandlersJson {
        private HandlersJson(List<Handler> handlers) {
            this.handlers = handlers;
        }

        private List<Handler> handlers;
    }

    private static class Handler {
        //TODO
    }

    private static class JsonRequest implements IRequest {
        //TODO

        public Object getBody() {
            return null;
        }

        public int getStatus() {
            return 0;
        }

        public Map<String, String> getHeaders() {
            return null;
        }
    }

    static class AjaxStatusMapping {
        //Mapping status codes with ajax status texts.
        final static Map<Integer, String> statusTextMap = new HashMap<Integer, String>();

        static {
            statusTextMap.put(100, "Continue");
            statusTextMap.put(101, "Switching Protocol");

            statusTextMap.put(200, "OK");

            statusTextMap.put(201, "Created");
            statusTextMap.put(202, "Accepted");
            statusTextMap.put(203, "Non-Authoritative Information");
            statusTextMap.put(204, "No Content");
            statusTextMap.put(205, "Reset Content");
            statusTextMap.put(206, "Partial Content");

            statusTextMap.put(300, "Multiple Choice");
            statusTextMap.put(301, "Moved Permanently");
            statusTextMap.put(302, "Found");
            statusTextMap.put(303, "See Other");
            statusTextMap.put(304, "Not Modified");
            statusTextMap.put(305, "Use Proxy");
            statusTextMap.put(306, "unused");
            statusTextMap.put(307, "Temporary Redirect");
            statusTextMap.put(308, "Permanent Redirect");

            statusTextMap.put(400, "Bad Request");
            statusTextMap.put(401, "Unauthorized");
            statusTextMap.put(402, "Payment Required");
            statusTextMap.put(403, "Forbidden");
            statusTextMap.put(404, "Not Found");
            statusTextMap.put(405, "Method Not Allowed");
            statusTextMap.put(406, "Not Acceptable");
            statusTextMap.put(407, "Proxy Authentication Required");
            statusTextMap.put(408, "Request Timeout");
            statusTextMap.put(409, "Conflict");
            statusTextMap.put(410, "Gone");
            statusTextMap.put(411, "Length Required");
            statusTextMap.put(412, "Precondition");
            statusTextMap.put(413, "Payload Too Large");
            statusTextMap.put(414, "URI Too Long");
            statusTextMap.put(415, "Unsupported Media Type");
            statusTextMap.put(416, "Requested Range Not Satisfiable");
            statusTextMap.put(417, "Expectation Failed");
            statusTextMap.put(418, "I'm a teapot"); //lol
            statusTextMap.put(421, "Misdirected Request");
            statusTextMap.put(426, "Upgrade Required");
            statusTextMap.put(428, "Precondition Required");
            statusTextMap.put(429, "Too Many Requests");
            statusTextMap.put(431, "Request Header");

            statusTextMap.put(500, "Internal Server Error");
            statusTextMap.put(501, "Not Implemented");
            statusTextMap.put(502, "Bad Gateway");
            statusTextMap.put(503, "Service Unavailable");
            statusTextMap.put(504, "Gateway Timeout");
            statusTextMap.put(505, "HTTP Version Not Supported");
            statusTextMap.put(506, "Variant Also Negotiates");
            statusTextMap.put(507, "Variant Also Negotiates");
            statusTextMap.put(511, "Network Authentication Required");
        }
    }
}
