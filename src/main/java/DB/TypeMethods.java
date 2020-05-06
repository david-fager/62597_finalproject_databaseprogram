package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeMethods {

    private HelperSingleton hs = HelperSingleton.getInstance();

    //Foodtype
    public boolean createType(String name, int keep) {
        String sql = "INSERT INTO Type(" + hs.QtypeID + ", " + hs.QtypeName + ", " + hs.Qkeep + ") VALUES(?,?,?) ";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int nextFreeID = hs.getNextFreeID("Type", hs.QtypeID);
            if (nextFreeID < 1) {
                System.out.println(hs.getCurrentTime() + " ID out of bounds");
                return false;
            }

            pstmt.setInt(1, nextFreeID);
            pstmt.setString(2, name);
            pstmt.setInt(3, keep);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.getCurrentTime() + " Created new type {" + nextFreeID + ", " + name + ", " + keep + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in createType(): " + e.getMessage());
        }

        return false;
    }

    //Returns string array containing info aobut type
    public ArrayList<String[]> getType(int typeid) {
        String sql = "SELECT * FROM Type WHERE " + hs.QtypeID + "=?";
        ResultSet rset;
        ArrayList<String[]> type = new ArrayList<>();
        String[] itemInfo = new String[3];
        String[] header = {hs.QtypeID, hs.QtypeName, hs.Qkeep};
        type.add(header);

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeid);

            rset = pstmt.executeQuery();

            itemInfo[0] = Integer.toString(rset.getInt(hs.QtypeID));
            itemInfo[1] = rset.getString(hs.QtypeName);
            itemInfo[2] = Integer.toString(rset.getInt(hs.Qkeep));
            type.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in getType(): " + e.getMessage());
        }

        System.out.println(hs.getCurrentTime() + " Sending type with ID " + typeid);
        return type;
    }

    public ArrayList<String[]> getTypes() {
        String sql = "SELECT * FROM Type ";
        ResultSet rset;
        ArrayList<String[]> types = new ArrayList<>();
        String[] header = {hs.QtypeID, hs.QtypeName, hs.Qkeep};
        types.add(header);

        try (Connection conn = hs.connectDB()) {
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
            System.out.println(hs.getCurrentTime() + " Exception in : getTypes()" + e.getMessage());
        }

        System.out.println(hs.getCurrentTime() + " Sending all types");
        return types;
    }

    public boolean updateType(int typeid, String typeName, int keep, int newTypeid) {
        String sql = "UPDATE Type SET " + hs.QtypeID + "=?, " + hs.QtypeName + "=?, " + hs.Qkeep + "=? WHERE " + hs.QtypeID + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newTypeid);
            pstmt.setString(2, typeName);
            pstmt.setInt(3, keep);
            pstmt.setInt(4, typeid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.getCurrentTime() + " Updated type with ID " + typeid + " to {" + newTypeid + ", " + typeName + ", " + keep + "}");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in updateType: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteType(int typeid) {
        String sql = "DELETE FROM Type WHERE " + hs.QtypeID + "=?";

        try (Connection conn = hs.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeid);

            if (pstmt.executeUpdate() > 0) {
                System.out.println(hs.getCurrentTime() + " Deleted type with ID " + typeid);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(hs.getCurrentTime() + " Exception in deleteType(): " + e.getMessage());
        }

        return false;
    }

}
