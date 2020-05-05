package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();
    
    //User
    //Creates a user and assigns a him a fridgeID
    public boolean createUser(String userName) {
        String sql = "INSERT INTO User(" + hs.Qusername + ", " + hs.QfridgeID + ") VALUES(?,?)";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            int nextFreeID = hs.getNextFreeID("User", hs.QfridgeID);
            if (nextFreeID < 1) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " ID out of bounds");
                return false;
            }

            pstmt.setInt(2, nextFreeID);

            int i = pstmt.executeUpdate();

            if (i > 0) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Created new user {" + userName + ", " + nextFreeID + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in createUser(): " + e.getMessage());
        }

        return false;
    }

    //Returns an int array containing userid and fridgeid
    public ArrayList<String[]> getUser(String userName) {
        String sql = "SELECT * FROM User WHERE " + hs.Qusername + "=?";
        ResultSet rset;
        ArrayList<String[]> users = new ArrayList<>();
        String[] header = {"UserID", hs.QfridgeID};
        users.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            rset = pstmt.executeQuery();

            String[] userInfo = new String[2];
            userInfo[0] = rset.getString(hs.Qusername);
            userInfo[1] = Integer.toString(rset.getInt(hs.QfridgeID));
            users.add(userInfo);

        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getUser(): " + e.getMessage());
        }

        System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Sending user with username " + userName);
        return users;
    }

    //Gets all users in an Arraylist of int arrays
    public ArrayList<String[]> getUsers() {
        String sql = "SELECT * FROM User ";
        ResultSet rset;
        ArrayList<String[]> users = new ArrayList<>();
        String[] header = {"UserID", hs.QfridgeID};
        users.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] userInfo = new String[2];
                userInfo[0] = rset.getString(hs.Qusername);
                userInfo[1] = Integer.toString(rset.getInt(hs.QfridgeID));
                users.add(userInfo);
            }

        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getUsers(): " + e.getMessage());
        }

        System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Sending all users");
        return users;
    }

    public ArrayList<String[]> getCompleteUser(String username) {
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.Qusername, hs.QfridgeID, hs.Qamount, hs.QitemID, hs.Qexp, hs.QitemName, hs.QtypeID, hs.QtypeName, hs.Qkeep};
        items.add(header);
        String sql = "SELECT UserName, User." + hs.QfridgeID + ", " + hs.Qamount + ", Fridge." + hs.QitemID + ", " + hs.Qexp + ", " + hs.QitemName + ", Item." + hs.QtypeID + ", " + hs.QtypeName + ", " + hs.Qkeep + "  " +
                "FROM User JOIN Fridge ON User." + hs.QfridgeID + " = Fridge." + hs.QfridgeID + " JOIN Item ON Fridge." + hs.QitemID + " = Item." + hs.QitemID + " " +
                "JOIN Type ON Item." + hs.QtypeID + " = Type." + hs.QtypeID + " WHERE " + hs.Qusername + " = ?";
        ResultSet rset;

        try (Connection conn = hs.connectDB()) {
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
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getCompleteUser(): " + e.getMessage());
        }

        System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Sending all information stored on the user with username " + username);
        return items;
    }

    //Update userID and fridgeID of user with userID = uid
    public boolean updateUser(String userName, int fid, String newUserName) {
        String sql = "UPDATE User SET " + hs.Qusername + "=?, " + hs.QfridgeID + "=? WHERE " + hs.Qusername + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUserName);
            pstmt.setInt(2, fid);
            pstmt.setString(3, userName);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Updated user with username " + userName + " to {" + newUserName + ", " + fid + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in updateUser(): " + e.getMessage());
        }

        return false;
    }

    //Deletes a user by its userID
    public boolean deleteUser(String userName) {
        String sql = "DELETE FROM User WHERE " + hs.Qusername + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Deleted user with username " + userName);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in deleteUser(): " + e.getMessage());
        }

        return false;
    }
    
}
