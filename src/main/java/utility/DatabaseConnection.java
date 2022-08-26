package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static utility.IConstant.CONNECTION_SUCCESSFUL_LOG;

public class DatabaseConnection {

    private static Connection connection = null;
    private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class);

    public static Connection connect() {
        try {
            InputStream inputStream = DatabaseConnection.class.getClassLoader()
                    .getResourceAsStream("database.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            Class.forName(properties.getProperty("DB_DRIVER"));
            connection = DriverManager.getConnection(properties.getProperty("CONNECTION_URL"),
                    properties.getProperty("CONNECTION_USERNAME")
                    , properties.getProperty("CONNECTION_PASSWORD"));
            connection.setAutoCommit(false);
            LOGGER.info(CONNECTION_SUCCESSFUL_LOG);
        } catch (ClassNotFoundException | SQLException | IOException exception) {
            exception.printStackTrace();
        }
        return connection;
    }
}
