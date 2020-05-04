package Common.RMI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("NonAsciiCharacters")
public interface SkeletonRMI extends java.rmi.Remote {

    String createUser(int uid) throws java.rmi.RemoteException;

    String deleteUser(int uid) throws java.rmi.RemoteException;

    String deleteUsers() throws java.rmi.RemoteException;

    String updateUser(int uid, int newuid, int fid) throws java.rmi.RemoteException;

    ArrayList<String[]> getUser(int uid) throws java.rmi.RemoteException;

    ArrayList<String[]> getUsers() throws java.rmi.RemoteException;

    String createItem(int id, String name, int typeid) throws java.rmi.RemoteException;

    String deleteItem(int itemid) throws java.rmi.RemoteException;

    String deleteItems() throws java.rmi.RemoteException;

    String updateItem(int itemid, String itemName, int typeid, int newItemid)throws java.rmi.RemoteException;

    ArrayList<String[]> getItem(int itemid) throws java.rmi.RemoteException;

    ArrayList<String[]> getItems() throws java.rmi.RemoteException;

    String createType(int typeid, String name, int keep) throws java.rmi.RemoteException;

    String deleteType(int typeid) throws java.rmi.RemoteException;

    String deleteTypes() throws java.rmi.RemoteException;

    String updateType(int typeid, String typeName, int keep, int newTypeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getType(int typeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getTypes() throws java.rmi.RemoteException;

    ArrayList<String[]> getFridgeContents(int fid) throws java.rmi.RemoteException, SQLException;

    ArrayList<String[]> getFridgeItem(int fid, int itemid) throws  java.rmi.RemoteException;

    ArrayList<String[]> getFridge(int fid) throws java.rmi.RemoteException;

    ArrayList<String[]> getAllFridgeRows() throws  java.rmi.RemoteException;

    String createFridgeRow(int fid, int itemid, Date expiration, int amount) throws java.rmi.RemoteException;

    String updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount ) throws java.rmi.RemoteException;

    String deleteFridgeRow(int fid, int itemid) throws java.rmi.RemoteException;

    ArrayList<String[]> getTables() throws java.rmi.RemoteException;

}
