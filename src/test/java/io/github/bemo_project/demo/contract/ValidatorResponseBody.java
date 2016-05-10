package io.github.bemo_project.demo.contract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by v1_wizard.
 */
public class ValidatorResponseBody {
    public Input01 input01 = new Input01();
    public String Locale = "ru";

    public class Input01 {
        public String Valid = "false";
        public String ErrorMessage = "Error!";
        public Map<String, String> Errors = new HashMap<String, String>();
        public String[] ErrorData = new String[0];

        {
            Errors.put("GmailAddress", "It's work.");
        }
    }
}
