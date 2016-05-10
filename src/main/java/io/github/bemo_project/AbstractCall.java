package io.github.bemo_project;

import java.util.Map;

/**
 * Created by v1-wizard on 30.4.16.
 */
public abstract class AbstractCall {

    public abstract String getMethod();

    public abstract String getUrl();

    public abstract Object getBody();

    public abstract Map<String, String> getHeaders();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbstractCall)) {
            return false;
        }

        AbstractCall that = (AbstractCall) obj;

        if (that.getMethod() != null && this.getMethod() != null) {
            if (!that.getMethod().equals(this.getMethod())) {
                return false;
            }
        }

        if (that.getUrl() != null && this.getUrl() != null) {
            if (!that.getUrl().equals(this.getUrl())) {
                return false;
            }
        }

        if (that.getHeaders() != null && this.getHeaders() != null) {
            if (!that.getHeaders().equals(this.getHeaders())) {
                return false;
            }
        }

        if (that.getBody() != null && this.getBody() != null) {
            String thisBody = JsonMaker.makeBody(this);
            String thatBody = JsonMaker.makeBody(that);
            if (!thatBody.equals(thisBody)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "Call{method=%s, url=%s, body=%s, headers=%s}",
                getMethod(), getUrl(), JsonMaker.makeBody(this), getHeaders()
        );
    }
}
