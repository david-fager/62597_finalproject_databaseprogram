package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FridgeMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();

    public boolean createFridgeRow(int fid, int itemid, String expiration, int amount) {
        String sql = "INSERT INTO Fridge(" + hs.QfridgeID + ", " + hs.QitemID + ", " + hs.Qexp + ", " + hs.Qamount + ") VALUES(?,?,?,?)";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);
            pstmt.setInt(2, itemid);
            pstmt.setString(3, expiration);
            pstmt.setInt(4, amount);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.getCurrentTime() + " Created new fridge item {" + fid + ", " + itemid + ", " + expiration + ", " + amount + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in createFridgeRow(): " + e.getMessage());
        }

        return false;
    }

    public ArrayList<String[]> getFridgeItem(int fid, int itemid) {
        String sql = "SELECT * FROM Fridge WHERE " + hs.QfridgeID + "=? AND " + hs.QitemID + "=?";
        ResultSet rset;
        ArrayList<String[]> fridgeItem = new ArrayList<>();
        String[] itemInfo = new String[4];
        String[] header = {hs.QfridgeID, hs.QitemID, hs.Qexp, hs.Qamount};
        fridgeItem.add(header);
        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);
            pstmt.setInt(2, itemid);

            rset = pstmt.executeQuery();

            itemInfo[0] = Integer.toString(rset.getInt(hs.QfridgeID));
            itemInfo[1] = Integer.toString(rset.getInt(hs.QitemID));
            itemInfo[2] = rset.getString(hs.Qexp);
            itemInfo[3] = Integer.toString((rset.getInt(hs.Qamount)));
            fridgeItem.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in getFridgeItem(): " + e.getMessage());
        }

        System.out.println(hs.getCurrentTime() + " Sending fridge item with fridgeID " + fid + " and itemID " + itemid);
        return fridgeItem;
    }

    public ArrayList<String[]> getFridge(int fid) {
        String sql = "SELECT * FROM Fridge WHERE " + hs.QfridgeID + "=?";
        ResultSet rset;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.QfridgeID, hs.QitemID, hs.Qexp, hs.Qamount};
        items.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] itemInfo = new String[4];
                itemInfo[0] = Integer.toString(rset.getInt(hs.QfridgeID));
                itemInfo[1] = Integer.toString(rset.getInt(hs.QitemID));
                itemInfo[2] = rset.getString(hs.Qexp);
                itemInfo[3] = Integer.toString((rset.getInt(hs.Qamount)));
                items.add(itemInfo);
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in getFridge: " + e.getMessage());
        }

        System.out.println(hs.getCurrentTime() + " Sending items in fridge with ID " + fid);
        return items;
    }

    public ArrayList<String[]> getAllFridgeRows() {
        String sql = "SELECT * FROM Fridge";
        ResultSet rset;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.QfridgeID, hs.QitemID, hs.Qexp, hs.Qamount};
        items.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] itemInfo = new String[4];
                itemInfo[0] = Integer.toString(rset.getInt(hs.QfridgeID));
                itemInfo[1] = Integer.toString(rset.getInt(hs.QitemID));
                itemInfo[2] = rset.getString(hs.Qexp);
                itemInfo[3] = Integer.toString((rset.getInt(hs.Qamount)));
                items.add(itemInfo);
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in getAllFridgeRows(): " + e.getMessage());
        }

        System.out.println(hs.getCurrentTime() + " Sending everything in the fridge table");
        return items;
    }

    public ArrayList<String[]> getFridgeContents(int fid) {
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.Qamount, hs.Qexp, hs.QitemID, hs.QitemName, hs.Qkeep, hs.QtypeID, "Name"};
        items.add(header);
        String sql = "SELECT " + hs.QfridgeID + ", Fridge." + hs.QitemID + ", " + hs.Qamount + ", " + hs.Qexp + ", " + hs.QitemName + ", Type." + hs.QtypeID + ", " + hs.QtypeName + ", " + hs.Qkeep + "\n" +
                "FROM Fridge JOIN Item ON Fridge." + hs.QitemID + " = Item." + hs.QitemID + " JOIN Type ON Item." + hs.QtypeID + " = Type." + hs.QtypeID + " WHERE " + hs.QfridgeID + "=?";
        ResultSet rset;

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] item = new String[7];
                item[0] = Integer.toString(rset.getInt(hs.Qamount));
                item[1] = rset.getString(hs.Qexp);
                item[2] = Integer.toString(rset.getInt(hs.QitemID));
                item[3] = rset.getString(hs.QitemName);
                item[4] = Integer.toString(rset.getInt(hs.Qkeep));
                item[5] = Integer.toString(rset.getInt(hs.QtypeID));
                item[6] = rset.getString("Name");
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in getFridgeContents(): " + e.getMessage());
        }

        System.out.println(hs.getCurrentTime() + " Sending all info on fridge with ID " + fid);
        return items;
    }

    public boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) {
        String sql = "UPDATE Fridge SET " + hs.QfridgeID + "=?, " + hs.QitemID + "=?, " + hs.Qexp + "=?, " + hs.Qamount + "=? WHERE " + hs.QfridgeID + "=? AND " + hs.QitemID + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newFid);
            pstmt.setInt(2, newItemid);
            pstmt.setString(3, newExpiration);
            pstmt.setInt(4, newAmount);
            pstmt.setInt(5, fid);
            pstmt.setInt(6, itemid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.getCurrentTime() + " Updated fridge item with fridgeID " + fid + " and itemID " + itemid + " to {" + newFid + ", " + newItemid + ", " + newExpiration + ", " + newAmount + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in updateFridgeRow(): " + e.getMessage());
        }

        return false;
    }

    public boolean deleteFridgeRow(int fid, int itemid) {
        String sql = "DELETE FROM Fridge WHERE " + hs.QfridgeID + "=? AND " + hs.QitemID + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);
            pstmt.setInt(2, itemid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.getCurrentTime() + " Deleted fridge item with fridgeID " + fid + " and itemID " + itemid);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in deleteFridgeRow(): " + e.getMessage());
        }

        return false;
    }

}
