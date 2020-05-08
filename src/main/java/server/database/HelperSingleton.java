package server.database;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import com.sun.net.httpserver.HttpExchange;
import common.ResponseObject;
import server.UserProfile;

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

    private static final HelperSingleton HELPERSINGLETON = new HelperSingleton();
    public final DateFormat DF = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
    private final long FULLDAYINMILLIS = 86400000;
    private HashMap<String, UserProfile> sessions = new HashMap<>();

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
        return HELPERSINGLETON;
    }

    // Helper methods
    // Returns the time and the best available user information as string
    public String logInfo(String uuid) {
        if (uuid.equals(""))
            return DF.format(Calendar.getInstance().getTimeInMillis());

        if (sessions.get(uuid) != null)
            return DF.format(Calendar.getInstance().getTimeInMillis()) + " (" + sessions.get(uuid).getUsername() + ")";
        else
            return DF.format(Calendar.getInstance().getTimeInMillis()) + " (" + uuid + ")";
    }

    // To print the request info
    public String logRequest(String uuid, String methodname, HttpExchange exchange) {
        String returnString = logInfo(uuid) + " " + methodname + " " + exchange.getRemoteAddress()
                + " -> " + exchange.getLocalAddress() + " " + exchange.getProtocol() + " " + exchange.getRequestMethod();
        for (String key : exchange.getRequestHeaders().keySet()) {
            returnString += " " + exchange.getRequestHeaders().get(key);
        }
        return returnString;
    }

    // Connects the calling method to the database file
    public Connection connectDB(String uuid) {
        String url = "jdbc:sqlite:src/main/resources/Fridge.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(logInfo(uuid) + " Exception in connectDB(): " + e.getMessage());
        }

        return conn;
    }

    // Finds the highest id value in the given table, adds one to it and return that
    public int getNextFreeID(String uuid, String tableName, String columnName) {
        String sql = "SELECT max(" + columnName + ") FROM " + tableName;
        ResultSet rset;
        int nextFreeID = 0;

        try (Connection conn = connectDB(uuid)) {
            System.out.println(logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            nextFreeID = rset.getInt("max(" + columnName + ")") + 1;
        } catch (SQLException e) {
            System.out.println(logInfo(uuid) + " Exception in getNextFreeID(): " + e.getMessage());
        }

        return nextFreeID;
    }

    // Returns all the table (their name and creating sql) in the database
    public ResponseObject getTables(String uuid) {
        String sql = "SELECT name, sql FROM sqlite_master";
        ResultSet rset;
        ArrayList<String[]> tables = new ArrayList<>();
        String[] header = {"name", "sql"};
        tables.add(header);

        try (Connection conn = connectDB(uuid)) {
            System.out.println(logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] tableColumns = new String[2];
                tableColumns[0] = rset.getString("name");
                tableColumns[1] = rset.getString("sql");
                tables.add(tableColumns);
            }
        } catch (SQLException e) {
            System.out.println(logInfo(uuid) + " Exception in getTables(): " + e.getMessage());
            return new ResponseObject(1, e.getMessage(), null, null, null);
        }

        System.out.println(logInfo(uuid) + " Sending info on all tables in the database");
        return new ResponseObject(0, "success", null, null, tables);
    }

    // Logs in users from the admin console (python) program
    public ResponseObject login(String username, String password, String ip) {
        try {
            // RMI connection to the user authentication system on javabog.dk server
            Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            Bruger bruger = brugeradmin.hentBruger(username, password);

            // Checking the user object we got from javabog
            if (bruger != null) {
                // Making sure the object from javabog has the same username and password, as the user provided
                if (!bruger.brugernavn.equals(username) || !bruger.adgangskode.equals(password)) {
                    System.out.println(logInfo("") + " Failed login by user " + username);
                    return new ResponseObject(1, "Failed", null, null, null);
                }

                // Checking if the user has already logged in to this server before
                for (String key : sessions.keySet()) {
                    if (sessions.get(key).getUsername().equals(bruger.brugernavn)) {
                        // If the user has an old login session, then remove that session, else send the old uuid to them
                        if (Calendar.getInstance().getTimeInMillis() - sessions.get(key).getLoginTime() > FULLDAYINMILLIS) {
                            sessions.remove(key);
                            break;
                        } else {
                            System.out.println(logInfo(key) + " Hello again: " + sessions.get(key).toString());
                            return new ResponseObject(0, "Success", sessions.get(key).getUuid(), null, null);
                        }
                    }
                }
            }

            // Giving the legitimate user their uuid
            String uuid = UUID.randomUUID().toString();
            while (sessions.containsKey(uuid)) {
                uuid = UUID.randomUUID().toString();
            }

            // Making a profile of the user
            UserProfile userProfile = new UserProfile();
            userProfile.setUsername(username);
            userProfile.setUuid(uuid);
            userProfile.setIp(ip);
            userProfile.setLoginTime(Calendar.getInstance().getTimeInMillis());
            userProfile.setLastSeenTime(Calendar.getInstance().getTimeInMillis());

            // Adding user profile to map of profiles
            sessions.put(uuid, userProfile);

            System.out.println(logInfo(uuid) + " Hello new user: " + sessions.get(uuid).toString());
            return new ResponseObject(0, "Success, new user", uuid, null, null);

        } catch (NotBoundException | MalformedURLException | RemoteException | IllegalArgumentException e) {
            System.out.println(logInfo("") + " Exception in login(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }
    }

    // Validates a users (admins) uuid (sees if it can be found in the sessions map)
    public ResponseObject validateUUID(String uuid) {

        // Checking if the uuid is even in the hashmap of users
        if (!sessions.containsKey(uuid)) {
            System.out.println(logInfo(uuid) + " Access denied: not recognized");
            return new ResponseObject(3, "Unauthorized access attempt", null, null, null);
        } else {
            // If user was in hashmap, then set latest seen time to now
            sessions.get(uuid).setLastSeenTime(Calendar.getInstance().getTimeInMillis());
        }

        // Checking if the user has not been seen by the database for more than the variable, then a re-login is required
        if (Calendar.getInstance().getTimeInMillis() - sessions.get(uuid).getLoginTime() > FULLDAYINMILLIS) {
            System.out.println(logInfo(uuid) + " Access denied: timed out, re-login required " + sessions.get(uuid).toString());
            sessions.remove(uuid);
            return new ResponseObject(4, "Timed out, re-login required", null, null, null);
        }

        // If this point is reached, then the user is granted access
        System.out.println(logInfo(uuid) + " Access granted " + sessions.get(uuid).toString());
        return new ResponseObject(0, "Success", null, null, null);
    }

}
