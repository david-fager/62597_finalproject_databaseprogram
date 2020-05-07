package server.database;

import common.ResponseObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();

    //User
    //Creates a user and assigns a him a fridgeID
    public ResponseObject createUser(String uuid, String userName) {
        String sql = "INSERT INTO User(" + hs.Qusername + ", " + hs.QfridgeID + ") VALUES(?,?)";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            int nextFreeID = hs.getNextFreeID(uuid, "User", hs.QfridgeID);
            if (nextFreeID < 1) {
                System.out.println(hs.logInfo(uuid) + " ID out of bounds");
                return new ResponseObject(1, "Failed", null, null, null);
            }

            pstmt.setInt(2, nextFreeID);

            int i = pstmt.executeUpdate();

            if (i > 0) {
                System.out.println(hs.logInfo(uuid) + " Created new user {" + userName + ", " + nextFreeID + "}");
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in createUser(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

    //Returns an int array containing userid and fridgeid
    public ResponseObject getUser(String uuid, String userName) {
        String sql = "SELECT * FROM User WHERE " + hs.Qusername + "=?";
        ResultSet rset;
        ArrayList<String[]> users = new ArrayList<>();
        String[] header = {"UserID", hs.QfridgeID};
        users.add(header);

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            rset = pstmt.executeQuery();

            String[] userInfo = new String[2];
            userInfo[0] = rset.getString(hs.Qusername);
            userInfo[1] = Integer.toString(rset.getInt(hs.QfridgeID));
            users.add(userInfo);

        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in getUser(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending user with username " + userName);
        return new ResponseObject(0, "Success", null, null, users);
    }

    //Gets all users in an Arraylist of int arrays
    public ResponseObject getUsers(String uuid) {
        String sql = "SELECT * FROM User ";
        ResultSet rset;
        ArrayList<String[]> users = new ArrayList<>();
        String[] header = {"UserID", hs.QfridgeID};
        users.add(header);

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] userInfo = new String[2];
                userInfo[0] = rset.getString(hs.Qusername);
                userInfo[1] = Integer.toString(rset.getInt(hs.QfridgeID));
                users.add(userInfo);
            }

        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in getUsers(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending all users");
        return new ResponseObject(0, "Success", null, null, users);
    }

    public ResponseObject getCompleteUser(String uuid, String username) {
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.Qusername, hs.QfridgeID, hs.Qamount, hs.QitemID, hs.Qexp, hs.QitemName, hs.QtypeID, hs.QtypeName, hs.Qkeep};
        items.add(header);
        String sql = "SELECT UserName, User." + hs.QfridgeID + ", " + hs.Qamount + ", Fridge." + hs.QitemID + ", " + hs.Qexp + ", " + hs.QitemName + ", Item." + hs.QtypeID + ", " + hs.QtypeName + ", " + hs.Qkeep + "  " +
                "FROM User JOIN Fridge ON User." + hs.QfridgeID + " = Fridge." + hs.QfridgeID + " JOIN Item ON Fridge." + hs.QitemID + " = Item." + hs.QitemID + " " +
                "JOIN Type ON Item." + hs.QtypeID + " = Type." + hs.QtypeID + " WHERE " + hs.Qusername + " = ?";
        ResultSet rset;

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] item = new String[9];
                item[0] = rset.getString(hs.Qusername);
                item[1] = Integer.toString(rset.getInt(hs.QfridgeID));
                item[2] = Integer.toString(rset.getInt(hs.Qamount));
                item[3] = Integer.toString(rset.getInt(hs.QitemID));
                item[4] = rset.getString(hs.Qexp);
                item[5] = rset.getString(hs.QitemName);
                item[6] = Integer.toString(rset.getInt(hs.QtypeID));
                item[7] = rset.getString(hs.QtypeName);
                item[8] = Integer.toString(rset.getInt(hs.Qkeep));
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in getCompleteUser(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending all information stored on the user with username " + username);
        return new ResponseObject(0, "Success", null, null, items);
    }

    //Update userID and fridgeID of user with userID = uid
    public ResponseObject updateUser(String uuid, String userName, int fid, String newUserName) {
        String sql = "UPDATE User SET " + hs.Qusername + "=?, " + hs.QfridgeID + "=? WHERE " + hs.Qusername + "=?";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUserName);
            pstmt.setInt(2, fid);
            pstmt.setString(3, userName);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Updated user {" + newUserName + ", " + fid + "}");
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in updateUser(): " + e.getMessage());
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

    //Deletes a user by its userID
    public ResponseObject deleteUser(String uuid, String userName) {
        String sql = "DELETE FROM User WHERE " + hs.Qusername + "=?";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Deleted user with username " + userName);
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in deleteUser(): " + e.getMessage());
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

}
