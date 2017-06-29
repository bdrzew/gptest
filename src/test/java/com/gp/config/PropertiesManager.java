package com.gp.config;

import java.text.MessageFormat;
import java.util.*;

public class PropertiesManager {

    private final List<ResourceBundle> bundleList;
    private final String defaultString;

    private static class ResourceManagerHolder {
        public static final PropertiesManager INSTANCE = new PropertiesManager();
    }

    private PropertiesManager() {
        bundleList = new ArrayList<>();
        bundleList.add(ResourceBundle.getBundle("default"));
        bundleList.add(ResourceBundle.getBundle("api"));
        bundleList.add(ResourceBundle.getBundle("user"));
        defaultString = getValue("default");
    }

    private static PropertiesManager get() {
        return ResourceManagerHolder.INSTANCE;
    }

    public static String getMessage(String key) {
        return PropertiesManager.get().getValue(key);
    }

    public static String getMessage(String key, String ... params) {
        return MessageFormat.format(PropertiesManager.get().getValue(key), params);
    }

    private String getValue(String key) {
        String returnValue = defaultString;
        for(ResourceBundle bundle : bundleList)
        {
            try {
                returnValue = bundle.getString(key);
                break;
            } catch (MissingResourceException e) {
                returnValue = defaultString;
            }
        }
        return returnValue;
    }
}