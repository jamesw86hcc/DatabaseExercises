// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        final String DATABASE_URL = "jdbc:derby:C:/Users/james/Downloads/babynames-derby/babynames";

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "user", "pass");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NAME FROM BABYNAMES" +
                    "WHERE GENDER = 'M' AND US_STATE = 'MD' AND DATE_YEAR = 1991" +
                    "ORDER BY NUM_BABIES desc" +
                    "FETCH FIRST 1 ROWS ONLY");

            System.out.printf("The most popular boy's name in Maryland: %s", resultSet.next());

            resultSet = statement.executeQuery("SELECT DATE_YEAR FROM BABYNAMES" +
                    "WHERE NAME = 'Christopher' AND GENDER = 'M'" +
                    "ORDER BY NUM_BABIES desc" +
                    "    FETCH FIRST 1 ROWS ONLY");

            System.out.printf("The year where Christopher was the most popular: %d", resultSet.next());

            resultSet = statement.executeQuery("SELECT DATE_YEAR FROM BABYNAMES" +
                    "WHERE NAME = 'Rosemary' AND GENDER = 'F'" +
                    "ORDER BY NUM_BABIES desc\n" +
                    "FETCH FIRST 1 ROWS ONLY\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}