package Common.SOAP;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

@SuppressWarnings("NonAsciiCharacters")
@WebService
public interface SkeletonSOAP {

    @WebMethod
    String createUser(int uid) throws RemoteException;

    @WebMethod
    String deleteUser(int uid) throws RemoteException;

    @WebMethod
    String deleteUsers() throws RemoteException;

    @WebMethod
    String updateUser(int uid, int newuid, int fid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getUser(int uid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getUsers() throws RemoteException;

    @WebMethod
    String createItem(int id, String name, int typeID) throws RemoteException;

    @WebMethod
    String deleteItem(int itemid) throws RemoteException;

    @WebMethod
    String deleteItems() throws RemoteException;

    @WebMethod
    String updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getItem(int itemID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getItems() throws RemoteException;

    @WebMethod
    String createType(int typeID, String name, int keep) throws RemoteException;

    @WebMethod
    String deleteType(int typeID) throws RemoteException;

    @WebMethod
    String deleteTypes() throws RemoteException;

    @WebMethod
    String updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getType(int typeID) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getTypes() throws RemoteException;

    @WebMethod
    ArrayList<String[]> getFridgeContents(int fid) throws RemoteException, SQLException;

    @WebMethod
    ArrayList<String[]> getFridgeItem(int fid, int itemid) throws  RemoteException;

    @WebMethod
    ArrayList<String[]> getFridge(int fid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getAllFridgeRows() throws  RemoteException;

    @WebMethod
    String createFridgeRow(int fid, int itemid, Date expiration, int amount) throws RemoteException;

    @WebMethod
    String updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount ) throws RemoteException;

    @WebMethod
    String deleteFridgeRow(int fid, int itemid) throws RemoteException;

    @WebMethod
    ArrayList<String[]> getTables() throws RemoteException;

}

