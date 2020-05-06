package DB;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
    public HashMap<String, UserProfile> sessions = new HashMap<>();
    private final long halfHourInMillis = 1800000; //10000;

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

    public String adminLogin(String username, String password, String ip) {
        String uuid = null;

        try {
            // RMI connection to the user authentication system on javabog.dk server
            Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            Bruger bruger = brugeradmin.hentBruger(username, password);

            if (!bruger.brugernavn.equals(username) || !bruger.adgangskode.equals(password)) {
                System.out.println(getCurrentTime() + " Username " + username + " failed to login");

            }

            // Giving the legitimate user their uuid
            uuid = UUID.randomUUID().toString();
            while(sessions.containsKey(uuid)) {
                uuid = UUID.randomUUID().toString();
            }
            System.out.println(getCurrentTime() + " New user got uuid: " + uuid);

            // Making a profile of the user
            UserProfile userProfile = new UserProfile();
            userProfile.setUsername(username);
            userProfile.setIp(ip);
            userProfile.setLoginAtTime(Calendar.getInstance().getTimeInMillis());
            userProfile.setLastSeenTime(Calendar.getInstance().getTimeInMillis());

            // Adding user profile to map of profiles
            sessions.put(uuid, userProfile);

            System.out.println(getCurrentTime() + " Made " + sessions.get(uuid).toString());

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(getCurrentTime() + " Exception in adminLogin(): " + e.getMessage());
        }

        return uuid;
    }

    public boolean validateUUID(String uuid) {
        System.out.println(getCurrentTime() + " Validating " + sessions.get(uuid).toString());

        if (!sessions.containsKey(uuid)) {
            System.out.println(getCurrentTime() + " Access denied, user (uuid) " + uuid + " not recognized");
            return false;
        }

        if (Calendar.getInstance().getTimeInMillis() - sessions.get(uuid).getLastSeenTime() > halfHourInMillis) {
            System.out.println(getCurrentTime() + " Access denied, user (uuid) " + uuid + " timed out, re-login required");
            return false;
        }

        return true;
    }

}
