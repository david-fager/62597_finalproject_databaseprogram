import javax.xml.transform.Result;
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
    public void createUser(){
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
        String sql = "SELECT FROM User WHERE UserID=?";
        ResultSet rset = null;
        int[] userInfo = new int[1];
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
    //FoodItems
    //Create a food item with itemID = id, Name = name and TypeID = typeID
    public void createItem(int id, String name, int typeID){
        String sql = "INSERT INTO Food(ItemID, Name, TypeID) VALUES(?,?,?)";

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
        String sql = "DELETE FROM Food WHERE ItemID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, itemid);

            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //Update a fooditem by itemID
    public void updateItem(int itemid, String itemName, int typeid, int newitemid){
        String sql = "UPDATE Type SET ItemID=?, ItemName=?, TypeID=? WHERE itemID=?";

        try(Connection conn = this.connectDB()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, );
            pstmt.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void getItem(){

    }
    //Foodtype
    public void createType(){

    }

    public void deleteType(){

    }

    public void UpdateType(){

    }

    public void getType(){

    }
}
