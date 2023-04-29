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

            System.out.printf("The most popular boy's name in Maryland in 1991: %s\n", resultSet.next());

            resultSet = statement.executeQuery("SELECT DATE_YEAR FROM BABYNAMES" +
                    "WHERE NAME = 'Christopher' AND GENDER = 'M'" +
                    "ORDER BY NUM_BABIES desc" +
                    "    FETCH FIRST 1 ROWS ONLY");

            System.out.printf("The year where Christopher was the least popular: %d\n", resultSet.next());

            resultSet = statement.executeQuery("SELECT DATE_YEAR FROM BABYNAMES" +
                    "WHERE NAME = 'Rosemary' AND GENDER = 'F'" +
                    "ORDER BY NUM_BABIES desc\n" +
                    "FETCH FIRST 1 ROWS ONLY\n");

            System.out.printf("The year where Rosemary was the most popular: %d\n", resultSet.next());

            resultSet = statement.executeQuery("SELECT NAME from BABYNAMES WHERE NUM_BABIES >= 500 AND US_STATE = 'MD' AND DATE_YEAR = 2000");

            System.out.printf("The names that appeared 500 or more times in 2014, regardless of state:\n");

            System.out.println(resultSet.next());

            resultSet = statement.executeQuery("SELECT US_STATE FROM BABYNAMES"
	+"WHERE NAME = 'Xavier' AND DATE_YEAR = 2014 AND GENDER = 'M'"
	+"ORDER BY NUM_BABIES asc"
	+"FETCH FIRST 1 ROWS ONLY");

            System.out.printf("The state with the fewest babies named Xavier in 2014: %s\n", resultSet.next());

            resultSet = statement.executeQuery("SELECT US_STATE FROM BABYNAMES WHERE NAME = 'Hannah' AND DATE_YEAR = 1997 AND GENDER = 'F' ORDER BY NUM_BABIES desc FETCH FIRST 1 ROWS ONLY");

            System.out.printf("The state that had the most babies named Hannah in 1991: %s\n", resultSet.next());

            statement.executeQuery("INSERT INTO BABYNAMES (id, name, date_year, gender, us_state, num_babies)"
	+ "VALUES  (10000000, 'Joseph', 2016, 'M', 'PA', 476)");

            statement.executeQuery("DELETE from BABYNAMES WHERE ID = 10000000");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
