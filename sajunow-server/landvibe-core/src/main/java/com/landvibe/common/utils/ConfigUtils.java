package com.landvibe.common.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.DefaultFileSystem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtils.class);

	private static final Properties CONFIG = new Properties();

	static {
		load("properties/config.properties");
	}

	private static void load(String path) {
		InputStream is = null;
		try {
			is = ConfigurationUtils.locate(DefaultFileSystem.getDefaultFileSystem(), null, path).openStream();
			CONFIG.load(is);
		} catch (Exception e) {
			LOG.error("Config Property Loading Error Occuerd!", e);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	public static String getValue(String key) {
		return getValue(key, StringUtils.EMPTY);
	}

	public static String getValue(String key, String defaultValue) {
		String value = StringUtils.trim(CONFIG.getProperty(key, defaultValue));
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static List<String> getValues(String key) {
		try {
			return Arrays.asList(StringUtils.split(getValue(key), ','));
		} catch (NullPointerException e) {
			return new ArrayList<String>();
		}
	}

	public static String getDeployPhase() {
		return ConfigUtils.getValue("phase");
	}

}
