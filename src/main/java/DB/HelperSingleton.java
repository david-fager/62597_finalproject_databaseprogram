package DB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class HelperSingleton {

    private static final HelperSingleton helperSingleton = new HelperSingleton();
    public final DateFormat df = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
    public HashMap<String, String> sessions = new HashMap<>();

    //Global names for sql Queries
    //User
    public final String Qusername = "UserName";
    public final String QfridgeID = "FridgeID";

    //Fridge
    public final String Qamount = "Amount";
    public final String QitemID = "ItemID";
    public final String Qexp = "Expiration";

    //Item
    public final String QitemName = "ItemName";
    public final String QtypeID = "TypeID";

    //Type
    public final String QtypeName = "TypeName";
    public final String Qkeep = "Keep";

    private HelperSingleton() {
    }

    public static HelperSingleton getInstance() {
        return helperSingleton;
    }
    
    // Helper methods
    
    public String getCurrentTime() {
        return df.format(Calendar.getInstance().getTimeInMillis());
    }
    
    public Connection connectDB() {
        String url = "jdbc:sqlite:src/main/resources/Fridge.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println(getCurrentTime() + " Connected to Fridge.db at: '" + url + "'");
            }
        } catch (SQLException e) {
            System.out.println(getCurrentTime() + " Exception in connectDB(): " + e.getMessage());
        }
        return conn;
    }

    public int getNextFreeID(String tableName, String columnName) {
        String sql = "SELECT max(" + columnName + ") FROM " + tableName;
        ResultSet rset;
        int nextFreeID = 0;

        try (Connection conn = connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            nextFreeID = rset.getInt("max(" + columnName + ")") + 1;
        } catch (SQLException e) {
            System.out.println(getCurrentTime() + " Exception in getNextFreeID(): " + e.getMessage());
        }

        return nextFreeID;
    }

    public ArrayList<String[]> getTables() {
        String sql = "SELECT name, sql FROM sqlite_master";
        ResultSet rset;
        ArrayList<String[]> tables = new ArrayList<>();
        String[] header = {"name", "sql"};
        tables.add(header);

        try (Connection conn = connectDB()) {
            System.out.println(getCurrentTime() + " Performing query: '" + sql + "'");
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] tableColumns = new String[2];
                tableColumns[0] = rset.getString("name");
                tableColumns[1] = rset.getString("sql");
                tables.add(tableColumns);
            }
        } catch (SQLException e) {
            System.out.println(getCurrentTime() + " Exception in getTables(): " + e.getMessage());
            return new ArrayList<>();
        }

        System.out.println(getCurrentTime() + " Sending info on all tables in the database");
        return tables;
    }

    public String adminLogin(String username, String password) {

        String uuid = UUID.randomUUID().toString();

        while(uuid.equals("")) {
            uuid = UUID.randomUUID().toString();
        }

        return uuid;
    }

}
