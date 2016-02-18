package ru.electrictower.bemo.contract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1_wizard.
 */
public class Input01 {
    public String Valid = "false";
    public String ErrorMessage = "Welcome to selenium camp 2016.";
    public Map<String, String> Errors = new HashMap<String, String>();
    public String[] ErrorData = new String[0];

    {
        Errors.put("GmailAddress", "Welcome to Hell 2016.");
    }
}
