package Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by dioni on 2/13/2017.
 */
public class DBConnection{
        private static Connection conn = null;
        private static Logger logger = LoggerFactory.getLogger(DBConnection.class);

        public static Connection getConnection() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            try {

                if (conn == null) {

                    conn = DriverManager.getConnection("jdbc:mysql://" + Configuration.DATABASE_URL + ":"
                            + Configuration.DATABASE_PORT + "/" + Configuration.DATABASE_NAME + "?" + "user="
                            + Configuration.DATABASE_USER + "&password=" + Configuration.DATABASE_PASSWORD + "&autoReconnect=true");

                    logger.info("Created new DataBase Connection at " + Configuration.DATABASE_URL);
                }
                if (conn.isClosed()) {

                    conn = DriverManager.getConnection("jdbc:mysql://" + Configuration.DATABASE_URL + ":"
                            + Configuration.DATABASE_PORT + "/" + Configuration.DATABASE_NAME + "?" + "user="
                            + Configuration.DATABASE_USER + "&password=" + Configuration.DATABASE_PASSWORD + "&autoReconnect=true");
                }

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                logger.error("Error while creating Database Connection");

            }
            return conn;
        }

}
