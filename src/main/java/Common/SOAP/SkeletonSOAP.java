package Common.SOAP;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

@SuppressWarnings("NonAsciiCharacters")
@WebService
public interface SkeletonSOAP {

    @WebMethod
    ArrayList<String[]> getTables() throws RemoteException;


    @WebMethod
    String adminLogin(String username, String password) throws RemoteException;


    @WebMethod
    boolean createUser(String newUserName) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getUser(String newUserName) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getUsers() throws RemoteException;

    @WebMethod
    ArrayList<String[]> getCompleteUser(String username) throws RemoteException, SQLException;

    @WebMethod
    boolean updateUser(String newUserName, int fid, String userName) throws RemoteException;

    @WebMethod
    boolean deleteUser(String newUserName) throws RemoteException;


    @WebMethod
    boolean createItem(String name, int typeID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getItem(int itemID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getItems() throws RemoteException;

    @WebMethod
    boolean updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException;

    @WebMethod
    boolean deleteItem(int itemid) throws RemoteException;


    @WebMethod
    boolean createType(String name, int keep) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getType(int typeID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getTypes() throws RemoteException;

    @WebMethod
    boolean updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException;

    @WebMethod
    boolean deleteType(int typeID) throws RemoteException;


    @WebMethod
    boolean createFridgeRow(int fid, int itemid, String expiration, int amount) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getFridgeItem(int fid, int itemid) throws  RemoteException;

    @WebMethod
    ArrayList<String[]> getFridge(int fid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getAllFridgeRows() throws  RemoteException;

    @WebMethod
    ArrayList<String[]> getFridgeContents(int fid) throws RemoteException, SQLException;

    @WebMethod
    boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount ) throws RemoteException;

    @WebMethod
    boolean deleteFridgeRow(int fid, int itemid) throws RemoteException;

}

