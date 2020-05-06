package Common.RMI;

import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("NonAsciiCharacters")
public interface SkeletonRMI extends java.rmi.Remote {

    ArrayList<String[]> getTables(String uuid) throws java.rmi.RemoteException;

    String adminLogin(String username, String password) throws java.rmi.RemoteException;


    boolean createUser(String uuid, String userName) throws java.rmi.RemoteException;

    ArrayList<String[]> getUser(String uuid, String userName) throws java.rmi.RemoteException;

    ArrayList<String[]> getUsers(String uuid) throws java.rmi.RemoteException;

    ArrayList<String[]> getCompleteUser(String uuid, String username) throws java.rmi.RemoteException, SQLException;

    boolean updateUser(String uuid, String newUserName, int fid, String userName) throws java.rmi.RemoteException;

    boolean deleteUser(String uuid, String userName) throws java.rmi.RemoteException;


    boolean createItem(String uuid, String name, int typeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getItem(String uuid, int itemid) throws java.rmi.RemoteException;

    ArrayList<String[]> getItems(String uuid) throws java.rmi.RemoteException;

    boolean updateItem(String uuid, int itemid, String itemName, int typeid, int newItemid)throws java.rmi.RemoteException;

    boolean deleteItem(String uuid, int itemid) throws java.rmi.RemoteException;


    boolean createType(String uuid, String name, int keep) throws java.rmi.RemoteException;

    ArrayList<String[]> getType(String uuid, int typeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getTypes(String uuid) throws java.rmi.RemoteException;

    boolean updateType(String uuid, int typeid, String typeName, int keep, int newTypeid) throws java.rmi.RemoteException;

    boolean deleteType(String uuid, int typeid) throws java.rmi.RemoteException;


    boolean createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) throws java.rmi.RemoteException;

    ArrayList<String[]> getFridgeItem(String uuid, int fid, int itemid) throws  java.rmi.RemoteException;

    ArrayList<String[]> getFridge(String uuid, int fid) throws java.rmi.RemoteException;

    ArrayList<String[]> getAllFridgeRows(String uuid) throws  java.rmi.RemoteException;

    ArrayList<String[]> getFridgeContents(String uuid, int fid) throws java.rmi.RemoteException, SQLException;

    boolean updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount ) throws java.rmi.RemoteException;

    boolean deleteFridgeRow(String uuid, int fid, int itemid) throws java.rmi.RemoteException;

}
