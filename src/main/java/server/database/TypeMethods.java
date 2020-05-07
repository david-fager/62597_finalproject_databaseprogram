package server.database;

import server.ResponseObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();

    //Foodtype
    public ResponseObject createType(String uuid, String name, int keep) {
        String sql = "INSERT INTO Type(" + hs.QtypeID + ", " + hs.QtypeName + ", " + hs.Qkeep + ") VALUES(?,?,?) ";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int nextFreeID = hs.getNextFreeID(uuid, "Type", hs.QtypeID);
            if (nextFreeID < 1) {
                System.out.println(hs.logInfo(uuid) + " ID out of bounds");
                return new ResponseObject(1, "Failed", null, null, null);
            }

            pstmt.setInt(1, nextFreeID);
            pstmt.setString(2, name);
            pstmt.setInt(3, keep);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Created new type {" + nextFreeID + ", " + name + ", " + keep + "}");
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in createType(): " + e.getMessage());
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

    //Returns string array containing info aobut type
    public ResponseObject getType(String uuid, int typeid) {
        String sql = "SELECT * FROM Type WHERE " + hs.QtypeID + "=?";
        ResultSet rset;
        ArrayList<String[]> type = new ArrayList<>();
        String[] itemInfo = new String[3];
        String[] header = {hs.QtypeID, hs.QtypeName, hs.Qkeep};
        type.add(header);

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeid);

            rset = pstmt.executeQuery();

            itemInfo[0] = Integer.toString(rset.getInt(hs.QtypeID));
            itemInfo[1] = rset.getString(hs.QtypeName);
            itemInfo[2] = Integer.toString(rset.getInt(hs.Qkeep));
            type.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in getType(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending type with ID " + typeid);
        return new ResponseObject(0, "Success", null, null, type);
    }

    public ResponseObject getTypes(String uuid) {
        String sql = "SELECT * FROM Type ";
        ResultSet rset;
        ArrayList<String[]> types = new ArrayList<>();
        String[] header = {hs.QtypeID, hs.QtypeName, hs.Qkeep};
        types.add(header);

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                String[] typeInfo = new String[3];
                typeInfo[0] = Integer.toString(rset.getInt(hs.QtypeID));
                typeInfo[1] = rset.getString(hs.QtypeName);
                typeInfo[2] = Integer.toString(rset.getInt(hs.Qkeep));
                types.add(typeInfo);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in : getTypes()" + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        System.out.println(hs.logInfo(uuid) + " Sending all types");
        return new ResponseObject(0, "Success", null, null, types);
    }

    public ResponseObject updateType(String uuid, int typeid, String typeName, int keep, int newTypeid) {
        String sql = "UPDATE Type SET " + hs.QtypeID + "=?, " + hs.QtypeName + "=?, " + hs.Qkeep + "=? WHERE " + hs.QtypeID + "=?";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newTypeid);
            pstmt.setString(2, typeName);
            pstmt.setInt(3, keep);
            pstmt.setInt(4, typeid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Updated type with ID " + typeid + " to {" + newTypeid + ", " + typeName + ", " + keep + "}");
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in updateType: " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

    public ResponseObject deleteType(String uuid, int typeid) {
        String sql = "DELETE FROM Type WHERE " + hs.QtypeID + "=?";

        try (Connection conn = hs.connectDB(uuid)) {
            System.out.println(hs.logInfo(uuid) + " Querying database: " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.logInfo(uuid) + " Deleted type with ID " + typeid);
                return new ResponseObject(0, "Success", null, null, null);
            }
        } catch (SQLException e) {
            System.out.println(hs.logInfo(uuid) + " Exception in deleteType(): " + e.getMessage());
            return new ResponseObject(2, e.getMessage(), null, null, null);
        }

        return new ResponseObject(1, "Failed", null, null, null);
    }

}
