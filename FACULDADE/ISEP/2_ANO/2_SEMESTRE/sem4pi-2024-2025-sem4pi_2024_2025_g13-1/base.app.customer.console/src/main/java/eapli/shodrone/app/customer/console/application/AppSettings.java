package eapli.shodrone.app.customer.console.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class to load and provide access to application settings from the
 * application.properties file.
 */
public class AppSettings {
    private static final String PROPERTIES_RESOURCE = "application.properties";
    private final Properties properties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {

            if (propertiesStream == null) {
                System.err.println("CRITICAL: Configuration file not found: " + PROPERTIES_RESOURCE);
                System.err.println("Using default settings. Please ensure the file exists in the classpath.");
                return;
            }
            this.properties.load(propertiesStream);

        } catch (final IOException e) {
            System.err.println("CRITICAL: Error loading configuration file: " + e.getMessage());
            // In case of an error, the application will run with default values.
        }
    }

    /**
     * @return The server host address. Defaults to "localhost" if not specified.
     */
    public String getServerHost() {
        return properties.getProperty("server.host", "localhost");
    }

    /**
     * @return The server port. Defaults to 8889 if not specified or invalid.
     */
    public int getServerPort() {
        final String portStr = properties.getProperty("server.port");
        if (portStr == null) {
            return 8889; // Default port
        }
        try {
            return Integer.parseInt(portStr);
        } catch (final NumberFormatException e) {
            System.err.println("Invalid port number in configuration file. Using default 8889.");
            return 8889;
        }
    }
}