package common.soap;

import common.ResponseObject;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.rmi.RemoteException;
import java.sql.SQLException;

@SuppressWarnings("NonAsciiCharacters")
@WebService
public interface SkeletonSOAP {

    @WebMethod
    ResponseObject getTables(String uuid) throws RemoteException;

    @WebMethod
    ResponseObject adminLogin(String username, String password) throws RemoteException;

    @WebMethod
    ResponseObject serverConnect(String stipulatedUUID) throws RemoteException;


    @WebMethod
    ResponseObject createUser(String uuid, String newUserName) throws RemoteException;

    @WebMethod
    ResponseObject getUser(String uuid, String newUserName) throws RemoteException;

    @WebMethod
    ResponseObject getUsers(String uuid) throws RemoteException;

    @WebMethod
    ResponseObject getCompleteUser(String uuid, String username) throws RemoteException, SQLException;

    @WebMethod
    ResponseObject updateUser(String uuid, String newUserName, int fid, String userName) throws RemoteException;

    @WebMethod
    ResponseObject deleteUser(String uuid, String newUserName) throws RemoteException;


    @WebMethod
    ResponseObject createItem(String uuid, String name, int typeID) throws RemoteException;

    @WebMethod
    ResponseObject getItem(String uuid, int itemID) throws RemoteException;

    @WebMethod
    ResponseObject getItems(String uuid) throws RemoteException;

    @WebMethod
    ResponseObject updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) throws RemoteException;

    @WebMethod
    ResponseObject deleteItem(String uuid, int itemid) throws RemoteException;


    @WebMethod
    ResponseObject createType(String uuid, String name, int keep) throws RemoteException;

    @WebMethod
    ResponseObject getType(String uuid, int typeID) throws RemoteException;

    @WebMethod
    ResponseObject getTypes(String uuid) throws RemoteException;

    @WebMethod
    ResponseObject updateType(String uuid, int typeID, String typeName, int keep, int newTypeID) throws RemoteException;

    @WebMethod
    ResponseObject deleteType(String uuid, int typeID) throws RemoteException;


    @WebMethod
    ResponseObject createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) throws RemoteException;

    @WebMethod
    ResponseObject getFridgeItem(String uuid, int fid, int itemid) throws RemoteException;

    @WebMethod
    ResponseObject getFridge(String uuid, int fid) throws RemoteException;

    @WebMethod
    ResponseObject getAllFridgeRows(String uuid) throws RemoteException;

    @WebMethod
    ResponseObject getFridgeContents(String uuid, int fid) throws RemoteException, SQLException;

    @WebMethod
    ResponseObject updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) throws RemoteException;

    @WebMethod
    ResponseObject deleteFridgeRow(String uuid, int fid, int itemid) throws RemoteException;

}

