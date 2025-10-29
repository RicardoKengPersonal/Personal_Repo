package eapli.shodrone.app.simulatorserver.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();

    static {
        // CORREÇÃO: Usar getResourceAsStream para carregar o ficheiro a partir do classpath
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.err.println("Error: Could not find configuration file 'config.properties' in classpath.");

                throw new RuntimeException("Configuration file not found.");
            }

            properties.load(input);

        } catch (IOException ex) {
            System.err.println("Error: Could not load configuration file 'config.properties'.");
            throw new RuntimeException("Failed to load configuration.", ex);
        }
    }

    /**
     * Retorna o valor da propriedade para o host do servidor de simulação.
     * @return O hostname ou IP do servidor.
     */
    public static String getSimulatorHost() {
        return properties.getProperty("simulator.server.host");
    }

    /**
     * Retorna o valor da propriedade para o caminho do projeto de simulação no WSL.
     * @return O caminho absoluto no WSL.
     */
    public static String getWslSimulationPath() {
        return properties.getProperty("wsl.simulation.path");
    }
}