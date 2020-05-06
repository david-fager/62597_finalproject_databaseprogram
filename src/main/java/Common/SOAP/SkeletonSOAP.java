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
    ArrayList<String[]> getTables(String uuid) throws RemoteException;

    @WebMethod
    String adminLogin(String username, String password) throws RemoteException;


    @WebMethod
    boolean createUser(String uuid, String newUserName) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getUser(String uuid, String newUserName) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getUsers(String uuid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getCompleteUser(String uuid, String username) throws RemoteException, SQLException;

    @WebMethod
    boolean updateUser(String uuid, String newUserName, int fid, String userName) throws RemoteException;

    @WebMethod
    boolean deleteUser(String uuid, String newUserName) throws RemoteException;


    @WebMethod
    boolean createItem(String uuid, String name, int typeID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getItem(String uuid, int itemID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getItems(String uuid) throws RemoteException;

    @WebMethod
    boolean updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) throws RemoteException;

    @WebMethod
    boolean deleteItem(String uuid, int itemid) throws RemoteException;


    @WebMethod
    boolean createType(String uuid, String name, int keep) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getType(String uuid, int typeID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getTypes(String uuid) throws RemoteException;

    @WebMethod
    boolean updateType(String uuid, int typeID, String typeName, int keep, int newTypeID) throws RemoteException;

    @WebMethod
    boolean deleteType(String uuid, int typeID) throws RemoteException;


    @WebMethod
    boolean createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getFridgeItem(String uuid, int fid, int itemid) throws  RemoteException;

    @WebMethod
    ArrayList<String[]> getFridge(String uuid, int fid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getAllFridgeRows(String uuid) throws  RemoteException;

    @WebMethod
    ArrayList<String[]> getFridgeContents(String uuid, int fid) throws RemoteException, SQLException;

    @WebMethod
    boolean updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount ) throws RemoteException;

    @WebMethod
    boolean deleteFridgeRow(String uuid, int fid, int itemid) throws RemoteException;

}

