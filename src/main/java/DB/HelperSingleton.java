package DB;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class HelperSingleton {

    private static HelperSingleton helperSingleton = new HelperSingleton();
    public static final DateFormat df = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
    public static HashMap<String, String> sessions = new HashMap<>();

    //Global names for sql Queries
    //User
    public static final String Qusername = "UserName";
    public static final String QfridgeID = "FridgeID";

    //Fridge
    public static final String Qamount = "Amount";
    public static final String QitemID = "ItemID";
    public static final String Qexp = "Expiration";

    //Item
    public static final String QitemName = "ItemName";
    public static final String QtypeID = "TypeID";

    //Type
    public static final String QtypeName = "TypeName";
    public static final String Qkeep = "Keep";

    private HelperSingleton() {
    }

    public static HelperSingleton getInstance() {
        return helperSingleton;
    }

    // Helper methods
    public static Connection connectDB() {
        String url = "jdbc:sqlite:src/main/resources/Fridge.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Connected to Fridge.db at: '" + url + "'");
            }
        } catch (SQLException e) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in connectDB(): " + e.getMessage());
        }
        return conn;
    }

    public static int getNextFreeID(String tableName, String columnName) {
        String sql = "SELECT max(" + columnName + ") FROM " + tableName;
        ResultSet rset;
        int nextFreeID = 0;

        try (Connection conn = connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            nextFreeID = rset.getInt("max(" + columnName + ")") + 1;
        } catch (SQLException e) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getNextFreeID(): " + e.getMessage());
        }

        return nextFreeID;
    }

    public static ArrayList<String[]> getTables() {
        String sql = "SELECT name, sql FROM sqlite_master";
        ResultSet rset;
        ArrayList<String[]> tables = new ArrayList<>();
        String[] header = {"name", "sql"};
        tables.add(header);

        try (Connection conn = connectDB()) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Performing query: '" + sql + "'");
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] tableColumns = new String[2];
                tableColumns[0] = rset.getString("name");
                tableColumns[1] = rset.getString("sql");
                tables.add(tableColumns);
            }
        } catch (SQLException e) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getTables(): " + e.getMessage());
            return new ArrayList<>();
        }

        System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Sending info on all tables in the database");
        return tables;
    }

    public static String adminLogin(String username, String password) {

        String uuid = UUID.randomUUID().toString();

        while(uuid.equals("")) {
            uuid = UUID.randomUUID().toString();
        }

        return uuid;
    }

}
