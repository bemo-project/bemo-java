package ru.electrictower.bemo.demonstration.contract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by v1_wizard.
 */
public class GoogleValidatorRequest {

    public Map<String, String> input01 = new HashMap<String, String>();
    public String Locale = "ru";

    {
        input01.put("Input", "GmailAddress");
        input01.put("GmailAddress", "aliaksei.boole");
        input01.put("FirstName", "");
        input01.put("LastName", "");
    }

}
