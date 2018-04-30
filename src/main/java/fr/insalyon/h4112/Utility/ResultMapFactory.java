package fr.insalyon.h4112.Utility;

import java.util.HashMap;
import java.util.Map;

public class ResultMapFactory {

    public static Map<String, Object> getSuccessResultMap(Object data) {
        return getResultMap("SUCCESS", "SUCCESS", data);
    }

    public static Map<String, Object> getSuccessResultMap(Object data, int size) {
        return getResultMap("SUCCESS", "SUCCESS", data, size);
    }

    public static Map<String, Object> getSuccessResultMap() {
        return getNoResultMap("SUCCESS", "SUCCESS");
    }

    public static Map<String, Object> getResultMap(String returnCode, String returnMsg, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("returnCode", returnCode);
        result.put("returnMsg", returnMsg);
        result.put("data", data);
        return result;
    }

    public static Map<String, Object> getResultMap(String returnCode, String returnMsg, Object data, int size) {
        Map<String, Object> result = new HashMap<>();
        result.put("returnCode", returnCode);
        result.put("returnMsg", returnMsg);
        result.put("data", data);
        result.put("total", size);
        return result;
    }

    public static Map<String, Object> getNoResultMap(String returnCode, String returnMsg) {
        Map<String, Object> result = new HashMap<>();
        result.put("returnCode", returnCode);
        result.put("returnMsg", returnMsg);
        return result;
    }

    public static Map<String, Object> getErrorResultMap(Exception e) {
        return null;
    }

    public static Map<String, String> getResultMap(String returnCode, String returnMsg) {
        Map<String, String> result = new HashMap<>();
        result.put("returnCode", returnCode);
        result.put("returnMsg", returnMsg);
        return result;
    }

    public static Map<String, Object> getErrorResultMap(String returnMsg) {
        Map<String, Object> result = new HashMap<>();
        result.put("returnCode", "FAILED");
        result.put("returnMsg", returnMsg);
        return result;
    }
}
