package ru.electrictower.bemo;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by v1-wizard on 2.5.16.
 */
public class JsonTest {

    @Test
    public void verity_init_json() {
        System.out.println(JsonMaker.initJson("hui"));
    }

    @Test
    public void verify_handlers_json() {
        System.out.println(JsonMaker.handlersJson(new ArrayList<AbstractHandler>()));
    }
}
