package io.github.bemo_project.demo.contract;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by v1_wizard.
 */
public class ValidatorRequestBody {

    public Map<String, String> input01 = new LinkedHashMap<String, String>();
    public String Locale = "ru";

    {
        input01.put("Input", "GmailAddress");
        input01.put("GmailAddress", "aliaksei.boole");
        input01.put("FirstName", "");
        input01.put("LastName", "");
    }

}
