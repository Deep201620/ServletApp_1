package dao;

import utility.DatabaseConnection;
import bean.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private static final String INSERT_UPDATE_RECORD = "INSERT INTO \"Login\"(\"email\", \"logincounter\") VALUES (?, ?) " +
            "ON CONFLICT (\"email\") DO UPDATE set \"logincounter\" = \"Login\".\"logincounter\"+1";
    private static final String SELECT_RECORD = "Select \"emailId\",password from \"Registration\" " +
            "where \"emailId\" = ? OR password = ?";
    private static Connection connection;

    public boolean login(LoginBean loginBean) {
        connection = DatabaseConnection.connect();
        boolean loginStatus = false;
        try {
            preparedStatement = connection.prepareStatement(SELECT_RECORD);
            preparedStatement.setString(1, loginBean.getUserEmail());
            preparedStatement.setString(2, loginBean.getUserPassword());
            resultSet = preparedStatement.executeQuery();
            loginStatus = MatchData(loginBean);
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginStatus;
    }

    private boolean MatchData(LoginBean loginBean) {
        boolean loginStatus = false;
        int logincounter = 1;
        try {
            while (resultSet.next()) {
                String userEmail = resultSet.getString(1);
                String userPassword = resultSet.getString(2);
                //Comparing the credentials from the login page with database
                if (userEmail.equals(loginBean.getUserEmail()) && userPassword.
                        equals(loginBean.getUserPassword())) {
                    loginStatus = true;
                    preparedStatement = connection.prepareStatement(INSERT_UPDATE_RECORD);
                    preparedStatement.setString(1, loginBean.getUserEmail());
                    preparedStatement.setInt(2, logincounter);
                    preparedStatement.executeUpdate();
                    break;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return loginStatus;
    }
}
