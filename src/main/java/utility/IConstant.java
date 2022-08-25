package utility;

public interface IConstant {
//Interface fields are by default final and static, so no need to explicitly declare.

    //Log Messages
    String CONNECTION_SUCCESSFUL_LOG = "Connection Successful";
    String INSERTING_LOG = "Inserting Record....";
    String INSERTED_SUCCESSFUL_LOG = "Data Inserted Successfully";
    String INVALID_MESSAGE_LOG = "Invalid username or password!";
    String REGISTRATION_LOG = "Registration Successful";
    String LOGIN_LOG = "Logged in successfully";

    //Form parameters
    String EMAIL = "email";
    String PASSWORD = "password";
    String BDATE = "bDate";
    String NAME = "name";

    //FILE NAME CONSTANTS
    String LOGIN_PAGE = "login.jsp";
    String WELCOME_PAGE = "welcome.jsp";
    String ERROR_PAGE = "error.jsp";

    //Header Constants
    String HEADER_PRAGMA = "Pragma";
    String HEADER_CACHE_CONTROL = "Cache-Control";
    String PRAGMA_VALUE = "no-cache";
    String CACHE_CONTROL_VALUE = "private, no-store, no-cache, must-revalidate";
    String EXPIRE_DATE_HEADER = "Expires";

}