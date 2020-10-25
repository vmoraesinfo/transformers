package com.test.aequilibrium.Controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonBuilder {

    private static JSONObject jsonObject;

    public JsonBuilder() {
        jsonObject = new JSONObject();
    }

    public JsonBuilder changeKeyValue(String key, Object value) throws JSONException {
        jsonObject.put(key, value);
        return this;
    }

    public JsonBuilder changeKeyValue(List<String> keys, List<Integer> values) throws JSONException {
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = values.get(i);
            jsonObject.put(key, value);
        }
        return this;
    }

    public String build() {
        return jsonObject.toString();
    }

    public JSONObject getJson() {
        return jsonObject;
    }

    public JsonBuilder createTransformerWithFakeValues() throws JSONException {
        jsonObject.put("name", "Player 1");
        jsonObject.put("type", "D");
        jsonObject.put("strength", 20);
        jsonObject.put("intelligence", 15);
        jsonObject.put("speed", 45);
        jsonObject.put("endurance", 87);
        jsonObject.put("rank", 100);
        jsonObject.put("courage", 1);
        jsonObject.put("firepower", 31);
        jsonObject.put("skill", 66);

        return this;
    }

    public JsonBuilder removeAnElementFromJson(String key) {
        jsonObject.remove(key);
        return this;
    }

    public JsonBuilder removeMultipleElementsFromJson(List<String> keys) {
        for (String key : keys) {
            jsonObject.remove(key);
        }
        return this;
    }

}
