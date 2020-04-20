
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Run {


    private static Connection connectDB(){
        String url = "jdbc:sqlite:C:/sqlite/db/Fridge.db";
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url);
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A database connection has been established!");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public int createUserID() throws SQLException {
        int id;
        ResultSet rset = null;
        String sql = "SELECT UserID FROM User";
        List<Integer> ids = new ArrayList<>();
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //add id numbers to arraylist
        while(rset.next()){
            ids.add(rset.getInt("UserID"));
        }

        //Find highest id number
        int max = ids.get(0);
        for(int j = 0; j < ids.size() - 1; j++){
            if(ids.get(j+1) > ids.get(j)){
                max = ids.get(j+1);
            }
        }
        id = max + 1;
        if(ids.isEmpty()){
            id = 1;
        }

        return id;
    }

    public int createFridgeID() throws SQLException {
        int id;
        ResultSet rset = null;
        String sql = "SELECT FridgeID FROM User";
        List<Integer> ids = new ArrayList<>();
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //Get other fridgeID's
        while(rset.next()){
            ids.add(rset.getInt("FridgeID"));
        }

        //Find highest ID
        int max = ids.get(0);
        for(int j = 0; j < ids.size() - 1; j++){
            if(ids.get(j+1) > ids.get(j)){
                max = ids.get(j+1);
            }
        }
        id = max + 1;
        if(ids.isEmpty()){
            id = 1;
        }
        return id;
    }

    //User
//Creates a user and assigns a him a fridgeID
    public void createUser(int uid){
        String sql = "INSERT INTO User(UserID, FridgeID) VALUES(?,?)";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, createUserID());
            pstmt.setInt(2, createFridgeID());
            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Deletes a user by its userID
    public void deleteUser(int uid){
        String sql = "DELETE FROM User WHERE UserID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, uid);

            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteUsers(){
        String sql = "DELETE FROM User ";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

        //Redundant??
    //Update userID and fridgeID of user with userID = uid
    public void updateUser(int uid, int newuid, int fid){
        String sql = "UPDATE User SET UserID=?, FridgeID=? WHERE UserID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newuid);
            pstmt.setInt(2, fid);
            pstmt.setInt(3, uid);
            pstmt.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
        //Returns an int array containing userid and fridgeid
    public int[] getUser(int uid){
        String sql = "SELECT * FROM User WHERE UserID=?";
        ResultSet rset = null;
        int[] userInfo = new int[2];
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, uid);

            rset = pstmt.executeQuery();
            userInfo[0] = rset.getInt("UserID");
            userInfo[1] = rset.getInt("FridgeID");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userInfo;
    }

    //Gets all users in an Arraylist of int arrays
    public ArrayList<int[]> getUsers(){
        String sql = "SELECT * FROM User ";
        ResultSet rset = null;
        int[] userInfo = new int[2];
        ArrayList<int[]> users = new ArrayList<>();
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while(rset.next()) {
                userInfo[0] = rset.getInt("UserID");
                userInfo[1] = rset.getInt("FridgeID");
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }


    //FoodItems
    //Create a food item with itemID = id, Name = name and TypeID = typeID
    public void createItem(int id, String name, int typeID){
        String sql = "INSERT INTO Item(ItemID, Name, TypeID) VALUES(?,?,?)";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, typeID);
            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //Remove an item with ItemID = itemid
    public void deleteItem(int itemid){
        String sql = "DELETE FROM Item WHERE ItemID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemid);

            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteItems(){
        String sql = "DELETE FROM Item";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //Update a fooditem by itemID
    public void updateItem(int itemid, String itemName, int typeid, int newitemid){
        String sql = "UPDATE ITEM SET ItemID=?, ItemName=?, TypeID=? WHERE itemID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newitemid );
            pstmt.setString(2, itemName);
            pstmt.setInt(3, typeid);
            pstmt.setInt(4, itemid);
            pstmt.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
        //Returns a string array with info about the item
    public String[] getItem(int itemID){
        String sql = "SELECT * FROM Item WHERE ItemID=?";
        ResultSet rset = null;
        String[] itemInfo = new String[3];
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemID);

            rset = pstmt.executeQuery();
            itemInfo[0] = Integer.toString(rset.getInt("ItemID"));
            itemInfo[1] = rset.getString("ItemName");
            itemInfo[2] = Integer.toString(rset.getInt("TypeID"));

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return itemInfo;
    }

    public ArrayList<String[]> getItems(){
        String sql = "SELECT * FROM Item ";
        ResultSet rset = null;
        ArrayList<String[]> items = new ArrayList<>();

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while(rset.next()) {
                String[] itemInfo = new String[3];
                itemInfo[0] = Integer.toString(rset.getInt("ItemID"));
                itemInfo[1] = rset.getString("ItemName");
                itemInfo[2] = Integer.toString(rset.getInt("TypeID"));
                items.add(itemInfo);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return items;
    }

    //Foodtype
    public void createType(int typeID, String name, int keep){
        String sql = "INSERT INTO Type(TypeID, TypeName, Keep) VALUES(?,?,?) ";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeID);
            pstmt.setString(2, name);
            pstmt.setInt(3, keep);
            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteType(int typeID){
        String sql = "DELETE FROM Type WHERE TypeID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeID);

            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteTypes(){
        String sql = "DELETE FROM Type";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateType(int typeID, String typeName, int keep, int newTypeID){
        String sql = "UPDATE Type SET TypeID=?, typeName=?, Keep=? WHERE TypeID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newTypeID );
            pstmt.setString(2, typeName);
            pstmt.setInt(3, keep);
            pstmt.setInt(4, typeID);
            pstmt.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
        //Returns string array containing info aobut type
    public String[] getType(int typeID){
        String sql = "SELECT * FROM Type WHERE TypeID=?";
        ResultSet rset = null;
        String[] itemInfo = new String[3];
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, typeID);

            rset = pstmt.executeQuery();
            itemInfo[0] = Integer.toString(rset.getInt("TypeID"));
            itemInfo[1] = rset.getString("Name");
            itemInfo[2] = Integer.toString(rset.getInt("Keep"));

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return itemInfo;
    }

    public ArrayList<String[]> getTypes(){
        String sql = "SELECT * FROM Type ";
        ResultSet rset = null;
        ArrayList<String[]> types = new ArrayList<>();

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();
            while(rset.next()) {
                String[] typeInfo = new String[3];
                typeInfo[0] = Integer.toString(rset.getInt("TypeID"));
                typeInfo[1] = rset.getString("Name");
                typeInfo[2] = Integer.toString(rset.getInt("Keep"));
                types.add(typeInfo);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return types;
    }

    public ArrayList<Item> getFridgeContents(int fid) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT FridgeID, Fridge.ItemID, Amount, Expiration, ItemName, Type.TypeID, Name, Keep\n" +
                "FROM Fridge JOIN Item ON Fridge.ItemID = Item.ItemID JOIN Type ON Item.TypeID = Type.TypeID WHERE FridgeID=?";
        ResultSet rset = null;
        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fid);
            rset = pstmt.executeQuery();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        while (rset.next()){
            Item item = new Item();
            item.setAmount(rset.getInt("Amount"));
            item.setExpDate(rset.getDate("Expiration"));
            item.setItemId(rset.getInt("ItemID"));
            item.setItemName(rset.getString("ItemName"));
            item.setKeep(rset.getInt("Keep"));
            item.setTypeId(rset.getInt("TypeID"));
            item.setTypeName(rset.getString("Name"));

            items.add(item);
        }
        return items;
    }
}
