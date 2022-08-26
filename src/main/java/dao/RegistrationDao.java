package dao;


import bean.RegistrationBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utility.IConstant.INSERTED_SUCCESSFUL_LOG;
import static utility.IConstant.INSERTING_LOG;

public class RegistrationDao {

    boolean status = false;
    private static final String INSERT_RECORD = "INSERT INTO \"Registration\" (name, \"emailId\", \"bDate\", password)" +
            " VALUES (?,?,?,?)";
    private static final Logger LOGGER = LogManager.getLogger(RegistrationDao.class);

    public boolean registration(RegistrationBean registrationBean) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD)) {
             LOGGER.info(INSERTING_LOG);

            preparedStatement.setString(1, registrationBean.getUserName());
            preparedStatement.setString(2, registrationBean.getEmailId());
            preparedStatement.setDate(3, Date.valueOf(registrationBean.getbDate()));
            preparedStatement.setString(4, registrationBean.getPassword());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                status = true;
                LOGGER.info(INSERTED_SUCCESSFUL_LOG);
            }
            connection.commit();
            return status;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
