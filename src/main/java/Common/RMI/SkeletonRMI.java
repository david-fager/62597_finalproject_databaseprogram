package Common.RMI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("NonAsciiCharacters")
public interface SkeletonRMI extends java.rmi.Remote {

    boolean createUser(int uid) throws java.rmi.RemoteException;

    boolean deleteUser(int uid) throws java.rmi.RemoteException;

    boolean deleteUsers() throws java.rmi.RemoteException;

    boolean updateUser(int uid, int newuid, int fid) throws java.rmi.RemoteException;

    ArrayList<String[]> getUser(int uid) throws java.rmi.RemoteException;

    ArrayList<String[]> getUsers() throws java.rmi.RemoteException;

    boolean createItem(int id, String name, int typeid) throws java.rmi.RemoteException;

    boolean deleteItem(int itemid) throws java.rmi.RemoteException;

    boolean deleteItems() throws java.rmi.RemoteException;

    boolean updateItem(int itemid, String itemName, int typeid, int newItemid)throws java.rmi.RemoteException;

    ArrayList<String[]> getItem(int itemid) throws java.rmi.RemoteException;

    ArrayList<String[]> getItems() throws java.rmi.RemoteException;

    boolean createType(int typeid, String name, int keep) throws java.rmi.RemoteException;

    boolean deleteType(int typeid) throws java.rmi.RemoteException;

    boolean deleteTypes() throws java.rmi.RemoteException;

    boolean updateType(int typeid, String typeName, int keep, int newTypeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getType(int typeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getTypes() throws java.rmi.RemoteException;

    ArrayList<String[]> getFridgeContents(int fid) throws java.rmi.RemoteException, SQLException;

    ArrayList<String[]> getFridgeItem(int fid, int itemid) throws  java.rmi.RemoteException;

    ArrayList<String[]> getFridge(int fid) throws java.rmi.RemoteException;

    ArrayList<String[]> getAllFridgeRows() throws  java.rmi.RemoteException;

    boolean createFridgeRow(int fid, int itemid, Date expiration, int amount) throws java.rmi.RemoteException;

    boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount ) throws java.rmi.RemoteException;

    boolean deleteFridgeRow(int fid, int itemid) throws java.rmi.RemoteException;

    ArrayList<String[]> getTables() throws java.rmi.RemoteException;

    ArrayList<String[]> getEverything(String username) throws java.rmi.RemoteException, SQLException;
}
