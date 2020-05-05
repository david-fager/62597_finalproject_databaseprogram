package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class ItemMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();

    //FoodItems
    //Create a food item with itemID = id, Name = name and TypeID = typeID
    public boolean createItem(String name, int typeid) {
        String sql = "INSERT INTO Item(" + hs.QitemID + ", " + hs.QitemName + ", " + hs.QtypeID + ") VALUES(?,?,?)";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int nextFreeID = hs.getNextFreeID("Item", hs.QitemID);
            if (nextFreeID < 1) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " ID out of bounds");
                return false;
            }

            pstmt.setInt(1, nextFreeID);
            pstmt.setString(2, name);
            pstmt.setInt(3, typeid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Created new item {" + nextFreeID + ", " + name + ", " + typeid + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in createItem(): " + e.getMessage());
        }

        return false;
    }

    //Returns a string array with info about the item
    public ArrayList<String[]> getItem(int itemID) {
        String sql = "SELECT * FROM Item WHERE " + hs.QitemID + "=?";
        ResultSet rset;
        ArrayList<String[]> item = new ArrayList<>();
        String[] itemInfo = new String[3];
        String[] header = {hs.QitemID, hs.QitemName, hs.QtypeID};
        item.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemID);

            rset = pstmt.executeQuery();

            itemInfo[0] = Integer.toString(rset.getInt(hs.QitemID));
            itemInfo[1] = rset.getString(hs.QitemName);
            itemInfo[2] = Integer.toString(rset.getInt(hs.QtypeID));
            item.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getItem(): " + e.getMessage());
        }

        System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Sending item with ID " + itemID);
        return item;
    }

    public ArrayList<String[]> getItems() {
        String sql = "SELECT * FROM Item ";
        ResultSet rset;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.QitemID, hs.QitemName, hs.QtypeID};
        items.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] itemInfo = new String[3];
                itemInfo[0] = Integer.toString(rset.getInt(hs.QitemID));
                itemInfo[1] = rset.getString(hs.QitemName);
                itemInfo[2] = Integer.toString(rset.getInt(hs.QtypeID));
                items.add(itemInfo);
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getItems(): " + e.getMessage());
        }

        System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Sending all items");
        return items;
    }

    //Update a fooditem by itemID
    public boolean updateItem(int itemid, String itemName, int typeid, int newitemid) {
        String sql = "UPDATE ITEM SET " + hs.QitemID + "=?, " + hs.QitemName + "=?, " + hs.QtypeID + "=? WHERE " + hs.QitemID + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newitemid);
            pstmt.setString(2, itemName);
            pstmt.setInt(3, typeid);
            pstmt.setInt(4, itemid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Updated item with ID " + itemid + " to {" + newitemid + ", " + itemName + ", " + typeid + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in updateItem(): " + e.getMessage());
        }

        return false;
    }

    //Remove an item with ItemID = itemid
    public boolean deleteItem(int itemid) {
        String sql = "DELETE FROM Item WHERE " + hs.QitemID + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Deleted item with ID " + itemid);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in deleteItem(): " + e.getMessage());
        }

        return false;
    }

}
