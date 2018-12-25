package com.elbraulio.survey.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class BProperties {
    private final String propertiesPath;
    private final Properties properties = new Properties();
    private final Logger logger = Logger.getLogger(BProperties.class);

    /**
     * Ctor.
     */
    public BProperties() {
        this("./config.properties");
    }

    /**
     * Ctor.
     *
     * @param propertiesPath properties
     */
    public BProperties(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    /**
     * Return selected property.
     *
     * @param property prop key
     * @return prop value
     */
    public String prop(String property) {
        if (properties.size() == 0) {
            try {
                properties.load(
                        this.getClass()
                                .getClassLoader()
                                .getResourceAsStream(this.propertiesPath)
                );
            } catch (IOException e) {
                this.logger.error(
                        "Error trying to read property '" +
                                property + "'",
                        e
                );
            }
        }
        return properties.getProperty(property);
    }
}
