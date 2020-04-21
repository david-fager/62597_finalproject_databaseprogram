package Common.RMI;

import DB.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("NonAsciiCharacters")
public interface SkeletonRMI extends java.rmi.Remote {


    void createUser(int uid) throws java.rmi.RemoteException;

    void deleteUser(int uid) throws java.rmi.RemoteException;

    void deleteUsers() throws java.rmi.RemoteException;

    void updateUser(int uid, int newuid, int fid) throws java.rmi.RemoteException;

    int[] getUser(int uid) throws java.rmi.RemoteException;

    ArrayList<int[]> getUsers() throws java.rmi.RemoteException;

    void createItem(int id, String name, int typeid) throws java.rmi.RemoteException;

    void deleteItem(int itemid) throws java.rmi.RemoteException;

    void deleteItems() throws java.rmi.RemoteException;

    void updateItem(int itemid, String itemName, int typeid, int newItemid)throws java.rmi.RemoteException;

    String[]getItem(int itemid) throws java.rmi.RemoteException;

    ArrayList<String[]> getItems() throws java.rmi.RemoteException;

    void createType(int typeid, String name, int keep) throws java.rmi.RemoteException;

    void deleteType(int typeid) throws java.rmi.RemoteException;

    void deleteTypes() throws java.rmi.RemoteException;

    void updateType(int typeid, String typeName, int keep, int newTypeid) throws java.rmi.RemoteException;

    String[] getType(int typeid) throws java.rmi.RemoteException;

    ArrayList<String[]> getTypes() throws java.rmi.RemoteException;

    ArrayList<Item> getFridgeContents(int fid) throws java.rmi.RemoteException, SQLException;

    String[] getFridgeItem(int fid, int itemid) throws  java.rmi.RemoteException;

    ArrayList<String[]> getFridge(int fid) throws java.rmi.RemoteException;

    ArrayList<String[]> getAllFridgeRows() throws  java.rmi.RemoteException;

    void createFridgeRow(int fid, int itemid, Date expiration, int amount) throws java.rmi.RemoteException;

    void updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount ) throws java.rmi.RemoteException;

    void deleteFridgeRow(int fid, int itemid);



}
