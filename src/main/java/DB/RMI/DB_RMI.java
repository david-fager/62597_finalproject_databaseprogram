package DB.RMI;

import Common.RMI.SkeletonRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DB_RMI extends UnicastRemoteObject implements SkeletonRMI {
    private static DateFormat df = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");

    public DB_RMI() throws RemoteException {
    }

    //Global names for sql Queries

    //User
    private String Qusername = "UserName";
    private String QfridgeID = "FridgeID";

    //Fridge
    private String Qamount = "Amount";
    private String QitemID = "ItemID";
    private String Qexp = "Expiraiton";

    //Item
    private String QitemName = "ItemName";
    private String QtypeID = "TypeID";

    //Type
    private String QtypeName = "TypeName";
    private String Qkeep = "Keep";



    private static Connection connectDB() {
        String url = "jdbc:sqlite:src/main/resources/Fridge.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Connected to Fridge.db at: '" + url + "'");
                /*
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A database connection has been established!");
                */
            }
        } catch (SQLException e) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in connectDB(): " + e.getMessage());
        }
        return conn;
    }

    private int createUserID() throws SQLException {
        int id;
        ResultSet rset = null;
        String sql = "SELECT " + Qusername + " FROM User";
        List<Integer> ids = new ArrayList<>();
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //add id numbers to arraylist
        while (rset.next()) {
            ids.add(rset.getInt("UserID"));
        }

        //Find highest id number
        int max = ids.get(0);
        for (int j = 0; j < ids.size() - 1; j++) {
            if (ids.get(j + 1) > ids.get(j)) {
                max = ids.get(j + 1);
            }
        }
        id = max + 1;
        if (ids.isEmpty()) {
            id = 1;
        }

        return id;
    }

    private int createFridgeID() throws SQLException {
        int id;
        ResultSet rset = null;
        String sql = "SELECT "+ QfridgeID + " FROM User";
        List<Integer> ids = new ArrayList<>();
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //Get other fridgeID's
        while (rset.next()) {
            ids.add(rset.getInt("FridgeID"));
        }

        //Find highest ID
        int max = ids.get(0);
        for (int j = 0; j < ids.size() - 1; j++) {
            if (ids.get(j + 1) > ids.get(j)) {
                max = ids.get(j + 1);
            }
        }
        id = max + 1;
        if (ids.isEmpty()) {
            id = 1;
        }
        return id;
    }

    //User
//Creates a user and assigns a him a fridgeID
    @Override
    public boolean createUser(String userName) {
        String sql = "INSERT INTO User("+ Qusername + ", " + QfridgeID + ") VALUES(?,?)";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);
            pstmt.setInt(2, createFridgeID());
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;

    }

    //Deletes a user by its userID
    @Override
    public boolean deleteUser(String userName) {
        String sql = "DELETE FROM User WHERE " + Qusername + "=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;

    }

    @Override
    public boolean deleteUsers() {
        String sql = "DELETE FROM User ";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return i > 0;
    }


    //Update userID and fridgeID of user with userID = uid
    @Override
    public boolean updateUser(String newUserName, int fid, String userName) {
        String sql = "UPDATE User SET "+ Qusername +"=?, "+ QfridgeID+"=? WHERE "+Qusername+"=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUserName);
            pstmt.setInt(2, fid);
            pstmt.setString(3, userName);
            i =pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return i > 0;
    }

    //Returns an int array containing userid and fridgeid
    @Override
    public ArrayList<String[]> getUser(String userName) {
        String sql = "SELECT * FROM User WHERE "+Qusername+"=?";
        ResultSet rset = null;
        String[] userInfo = new String[2];
        ArrayList<String[]> users = new ArrayList<>();
        String[] header = {"UserID", "FridgeID"};
        users.add(header);
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);

            rset = pstmt.executeQuery();
            userInfo[0] = rset.getString("UserName");
            userInfo[1] = Integer.toString(rset.getInt("FridgeID"));
            users.add(userInfo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    //Gets all users in an Arraylist of int arrays
    @Override
    public ArrayList<String[]> getUsers() {
        String sql = "SELECT * FROM User ";
        ResultSet rset = null;
        ArrayList<String[]> users = new ArrayList<>();
        String[] userInfo = new String[2];
        String[] header = {"UserID", "FridgeID"};
        users.add(header);

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while (rset.next()) {
                userInfo[0] = rset.getString("UserName");
                userInfo[1] = Integer.toString(rset.getInt("FridgeID"));
                users.add(userInfo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    //FoodItems
    //Create a food item with itemID = id, Name = name and TypeID = typeID
    @Override
    public boolean createItem(int id, String name, int typeid) {
        String sql = "INSERT INTO Item("+QitemID+", "+ QitemName +", "+QtypeID+") VALUES(?,?,?)";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, typeid);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;

    }

    //Remove an item with ItemID = itemid
    @Override
    public boolean deleteItem(int itemid) {
        String sql = "DELETE FROM Item WHERE "+QitemID+"=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemid);

            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return i > 0;
    }

    @Override
    public boolean deleteItems() {
        String sql = "DELETE FROM Item";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;

    }

    //Update a fooditem by itemID
    @Override
    public boolean updateItem(int itemid, String itemName, int typeid, int newitemid) {
        String sql = "UPDATE ITEM SET "+QitemID+"=?, "+itemName+"=?, "+QtypeID+"=? WHERE "+QitemID+"=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newitemid);
            pstmt.setString(2, itemName);
            pstmt.setInt(3, typeid);
            pstmt.setInt(4, itemid);
            i = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return i > 0;
    }

    //Returns a string array with info about the item
    @Override
    public ArrayList<String[]> getItem(int itemID) {
        String sql = "SELECT * FROM Item WHERE "+QitemID+"=?";
        ResultSet rset = null;
        ArrayList<String[]> item = new ArrayList<>();
        String[] itemInfo = new String[3];
        String[] header = {"ItemID", "ItemName", "TypeID"};
        item.add(header);

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemID);

            rset = pstmt.executeQuery();
            itemInfo[0] = Integer.toString(rset.getInt("ItemID"));
            itemInfo[1] = rset.getString("ItemName");
            itemInfo[2] = Integer.toString(rset.getInt("TypeID"));
            item.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

    @Override
    public ArrayList<String[]> getItems() {
        String sql = "SELECT * FROM Item ";
        ResultSet rset = null;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {"ItemID", "ItemName", "TypeID"};
        items.add(header);

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while (rset.next()) {
                String[] itemInfo = new String[3];
                itemInfo[0] = Integer.toString(rset.getInt("ItemID"));
                itemInfo[1] = rset.getString("ItemName");
                itemInfo[2] = Integer.toString(rset.getInt("TypeID"));
                items.add(itemInfo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    //Foodtype
    @Override
    public boolean createType(int typeid, String name, int keep) {
        String sql = "INSERT INTO Type("+QtypeID+", "+ QtypeName +", "+Qkeep+") VALUES(?,?,?) ";
        int i = 0;

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeid);
            pstmt.setString(2, name);
            pstmt.setInt(3, keep);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i < 0;

    }

    @Override
    public boolean deleteType(int typeid) {
        String sql = "DELETE FROM Type WHERE "+QtypeID+"=?";
        int i = 0;

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeid);

            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;

    }

    @Override
    public boolean deleteTypes() {
        String sql = "DELETE FROM Type";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;
    }

    @Override
    public boolean updateType(int typeid, String typeName, int keep, int newTypeid) {
        String sql = "UPDATE Type SET "+QtypeID+"=?, "+QtypeName+"=?, "+Qkeep+"=? WHERE "+QtypeID+"=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newTypeid);
            pstmt.setString(2, typeName);
            pstmt.setInt(3, keep);
            pstmt.setInt(4, typeid);
            i = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;
    }

    //Returns string array containing info aobut type
    @Override
    public ArrayList<String[]> getType(int typeid) {
        String sql = "SELECT * FROM Type WHERE "+QtypeID+"=?";
        ResultSet rset = null;
        ArrayList<String[]> type = new ArrayList<>();
        String[] itemInfo = new String[3];
        String[] header = {"TypeID", "Name", "Keep"};
        type.add(header);
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeid);

            rset = pstmt.executeQuery();
            itemInfo[0] = Integer.toString(rset.getInt("TypeID"));
            itemInfo[1] = rset.getString("Name");
            itemInfo[2] = Integer.toString(rset.getInt("Keep"));
            type.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return type;
    }

    @Override
    public ArrayList<String[]> getTypes() {
        String sql = "SELECT * FROM Type ";
        ResultSet rset = null;
        ArrayList<String[]> types = new ArrayList<>();
        String[] header = {"TypeID", "Name", "Keep"};
        types.add(header);

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while (rset.next()) {
                String[] typeInfo = new String[3];
                typeInfo[0] = Integer.toString(rset.getInt("TypeID"));
                typeInfo[1] = rset.getString("Name");
                typeInfo[2] = Integer.toString(rset.getInt("Keep"));
                types.add(typeInfo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return types;
    }

    @Override
    public ArrayList<String[]> getFridgeContents(int fid) throws SQLException {
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {"Amount", "Expiration", "itemID", "ItemName", "Keep", "TypeID", "Name"};
        items.add(header);
        String sql = "SELECT "+QfridgeID+", Fridge."+QitemID+", "+Qamount+", "+Qexp+", "+QitemName+", Type."+QtypeID+", "+QtypeName+", "+Qkeep+"\n" +
                "FROM Fridge JOIN Item ON Fridge."+QitemID+" = Item."+QitemID+" JOIN Type ON Item."+QtypeID+" = Type."+QtypeID+" WHERE "+QfridgeID+"=?";
        ResultSet rset = null;

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);
            rset = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        while (rset.next()) {
            String[] item = new String[7];
            item[0] = Integer.toString(rset.getInt("Amount"));
            item[1] = rset.getString("Expiration");
            item[2] = Integer.toString(rset.getInt("ItemID"));
            item[3] = rset.getString("ItemName");
            item[4] = Integer.toString(rset.getInt("Keep"));
            item[5] = Integer.toString(rset.getInt("TypeID"));
            item[6] = rset.getString("Name");

            items.add(item);
        }
        return items;
    }

    @Override
    public ArrayList<String[]> getFridgeItem(int fid, int itemid) throws RemoteException {
        String sql = "SELECT * FROM Fridge WHERE "+QfridgeID+"=? AND "+QitemID+"=?";
        ResultSet rset = null;
        ArrayList<String[]> fridgeItem = new ArrayList<>();
        String[] itemInfo = new String[4];
        String[] header = {"FridgeID", "ItemID", "Expiration", "Amount" };
        fridgeItem.add(header);
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);
            pstmt.setInt(2, itemid);

            rset = pstmt.executeQuery();
            itemInfo[0] = Integer.toString(rset.getInt("FridgeID"));
            itemInfo[1] = Integer.toString(rset.getInt("ItemID"));
            itemInfo[2] = rset.getString("Expiration");
            itemInfo[3] = Integer.toString((rset.getInt("Amount")));

            fridgeItem.add(itemInfo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fridgeItem;
    }

    @Override
    public ArrayList<String[]> getFridge(int fid) throws RemoteException {
        String sql = "SELECT * FROM Fridge WHERE "+QfridgeID+"=?";
        ResultSet rset = null;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {"FridgeID", "ItemID", "Expiration", "Amount"};
        items.add(header);

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fid);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                String[] itemInfo = new String[4];
                itemInfo[0] = Integer.toString(rset.getInt("FridgeID"));
                itemInfo[1] = Integer.toString(rset.getInt("ItemID"));
                itemInfo[2] = rset.getString("Expiration");
                itemInfo[3] = Integer.toString((rset.getInt("Amount")));
                items.add(itemInfo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    @Override
    public ArrayList<String[]> getAllFridgeRows() throws RemoteException {
        String sql = "SELECT * FROM Fridge";
        ResultSet rset = null;
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {"FridgeID", "ItemID", "Expiration", "Amount"};
        items.add(header);

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                String[] itemInfo = new String[4];
                itemInfo[0] = Integer.toString(rset.getInt("FridgeID"));
                itemInfo[1] = Integer.toString(rset.getInt("ItemID"));
                itemInfo[2] = rset.getString("Expiration");
                itemInfo[3] = Integer.toString((rset.getInt("Amount")));
                items.add(itemInfo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    @Override
    public boolean createFridgeRow(int fid, int itemid, Date expiration, int amount) throws RemoteException {
        String sql = "INSERT INTO Fridge("+QfridgeID+", "+QitemID+", "+Qexp+", "+Qamount+") VALUES(?,?,?,?)";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fid);
            pstmt.setInt(2, itemid);
            pstmt.setDate(3, (java.sql.Date) expiration);
            pstmt.setInt(4, amount);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;
    }

    @Override
    public boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount) throws RemoteException {
        String sql = "UPDATE Fridge SET "+QfridgeID+"=?, "+QitemID+"=?, "+Qexp+"=?, "+Qamount+"=? WHERE "+QfridgeID+"=? AND "+QitemID+"=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newFid);
            pstmt.setInt(2, newItemid);
            pstmt.setDate(3, (java.sql.Date) newExpiration);
            pstmt.setInt(4, newAmount);
            pstmt.setInt(5, fid);
            pstmt.setInt(6, itemid);
            i = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;
    }

    @Override
    public boolean deleteFridgeRow(int fid, int itemid) {
        String sql = "DELETE FROM Fridge WHERE "+QfridgeID+"=? AND "+QitemID+"=?";
        int i = 0;
        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fid);
            pstmt.setInt(2, itemid);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i > 0;
    }

    @Override
    public ArrayList<String[]> getTables() throws RemoteException {
        String sql = "SELECT name, sql FROM sqlite_master";
        ResultSet rset = null;
        ArrayList<String[]> tables = new ArrayList<>();
        String[] header = {"name", "sql"};
        tables.add(header);

        try (Connection conn = this.connectDB()) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Performing query: '" + sql + "'");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                String[] tableColumns = new String[2];
                tableColumns[0] = rset.getString("name");
                tableColumns[1] = rset.getString("sql");
                tables.add(tableColumns);
            }

            return tables;
        } catch (SQLException e) {
            System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Exception in getTables(): " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String username) throws RemoteException, SQLException {
        ArrayList<String[]> items = new ArrayList<>();
        String[] header = {"UserName", "FridgeID", "Amount", "ItemID", "Expiration", "ItemName", "TypeID", "TypeName", "Keep"};
        items.add(header);
        String sql = "SELECT UserName, User."+QfridgeID+", "+Qamount+", Fridge."+QitemID+", "+Qexp+", "+QitemName+", Item."+QtypeID+", "+QtypeName+", "+Qkeep+"  " +
                "FROM User JOIN Fridge ON User."+QfridgeID+" = Fridge."+QfridgeID+" JOIN Item ON Fridge."+QitemID+" = Item."+QitemID+" " +
                "JOIN Type ON Item."+QtypeID+" = Type."+QtypeID+" WHERE QuserName = ? \n";
        ResultSet rset = null;

        try (Connection conn = this.connectDB()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            rset = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        while (rset.next()) {
            String[] item = new String[9];
            item[0] = rset.getString("UserName");
            item[1] = Integer.toString(rset.getInt("FridgeID"));
            item[2] = Integer.toString(rset.getInt("Amount"));
            item[3] = Integer.toString(rset.getInt("ItemID"));
            item[4] = rset.getString("Expiration");
            item[5] = rset.getString("ItemName");
            item[6] = Integer.toString(rset.getInt("TypeID"));
            item[7] = rset.getString("TypeName");
            item[8] = Integer.toString(rset.getInt("Keep"));

            items.add(item);
        }
        return items;
    }
}
