package server.database;

import common.ResponseObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();

    //FoodItems
    //Create a food item with itemID = id, Name = name and TypeID = typeID
    public ResponseObject createItem(String uuid, String name, int typeid) {
        String sql = "INSERT INTO Item(" + hs.QitemID + ", " + hs.QitemName + ", " + hs.QtypeID + ") VALUES(?,?,?)";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int nextFreeID = hs.getNextFreeID(uuid, "Item", hs.QitemID);
            if (nextFreeID < 1) {
                System.out.println(hs.logInfo(uuid) + " ID out of bounds");
                return new ResponseObject(1, "Failed", null, null, null);
            }

            pstmt.setInt(1, nextFreeID);
            pstmt.setString(2, name);
            pstmt.setInt(3, typeid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Created new item {" + nextFreeID + ", " + name + ", " + typeid + "}");
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in createItem(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

    //Returns a string array with info about the item
    public ResponseObject getItem(String uuid, int itemID) {
        String sql = "SELECT * FROM Item WHERE " + hs.QitemID + "=?";
        ResultSet rset;
        ArrayList<String[]> item = new ArrayList<>();
        String[] itemInfo = new String[3];
        String[] header = {hs.QitemID, hs.QitemName, hs.QtypeID};
        item.add(header);

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemID);

            rset = pstmt.executeQuery();

            itemInfo[0] = Integer.toString(rset.getInt(hs.QitemID));
            itemInfo[1] = rset.getString(hs.QitemName);
            itemInfo[2] = Integer.toString(rset.getInt(hs.QtypeID));
            item.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in getItem(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending item with ID " + itemID);
        return new ResponseObject(0, "Success", null, null, item);
    }

    public ResponseObject getItems(String uuid) {
        String sql = "SELECT * FROM Item ";
        ResultSet rset;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {hs.QitemID, hs.QitemName, hs.QtypeID};
        items.add(header);

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
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
            System.out.println(hs.logInfo(uuid) + " Exception in getItems(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending all items");
        return new ResponseObject(0, "Success", null, null, items);
    }

    //Update a fooditem by itemID
    public ResponseObject updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) {
        String sql = "UPDATE ITEM SET " + hs.QitemID + "=?, " + hs.QitemName + "=?, " + hs.QtypeID + "=? WHERE " + hs.QitemID + "=?";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newitemid);
            pstmt.setString(2, itemName);
            pstmt.setInt(3, typeid);
            pstmt.setInt(4, itemid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Updated item with ID " + itemid + " to {" + newitemid + ", " + itemName + ", " + typeid + "}");
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in updateItem(): " + e.getMessage());
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

    //Remove an item with ItemID = itemid
    public ResponseObject deleteItem(String uuid, int itemid) {
        String sql = "DELETE FROM Item WHERE " + hs.QitemID + "=?";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Deleted item with ID " + itemid);
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in deleteItem(): " + e.getMessage());
        }

        return new ResponseObject(0, "Failed", null, null, null);
    }

}
