package com.whenling.example.provider.support;

import java.util.Properties;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.whenling.castle.core.StaticConfigSupplier;
import com.whenling.castle.integration.dubbo.javaconfig.JavaConfigContainer;

public class DubboProviderMain {

	public static void main(String[] args) throws Exception {

		StaticConfigSupplier.append("config.properties");

		String[] customArgs = new String[] { "javaconfig" };
		Properties properties = new Properties();
		properties.setProperty(JavaConfigContainer.SPRING_JAVACONFIG, "com.whenling");
		ConfigUtils.setProperties(properties);
		com.alibaba.dubbo.container.Main.main(customArgs);
	}
}
