package DB.SOAP;

import Common.RMI.SkeletonRMI;
import Common.SOAP.SkeletonSOAP;
import DB.Item;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DB_SOAP implements SkeletonSOAP {

    private SkeletonRMI rmi;

    public DB_SOAP(SkeletonRMI rmi){
        this.rmi = rmi;
    }


    @Override
    public void createUser(int uid) throws RemoteException {
        rmi.createUser(uid);
    }

    @Override
    public void deleteUser(int uid) throws RemoteException {
        rmi.deleteUser(uid);
    }

    @Override
    public void deleteUsers() throws RemoteException {
        rmi.deleteUsers();
    }

    @Override
    public void updateUser(int uid, int newuid, int fid) throws RemoteException {
        rmi.updateUser(uid, newuid, fid);
    }

    @Override
    public int[] getUser(int uid) throws RemoteException {
        return rmi.getUser(uid);
    }

    @Override
    public ArrayList<int[]> getUsers() throws RemoteException {
        return rmi.getUsers();
    }

    @Override
    public void createItem(int id, String name, int typeID) throws RemoteException {
        rmi.createItem(id, name, typeID);
    }

    @Override
    public void deleteItem(int itemid) throws RemoteException {
        rmi.deleteItem(itemid);
    }

    @Override
    public void deleteItems() throws RemoteException {
        rmi.deleteItems();
    }

    @Override
    public void updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException {
        rmi.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public String[] getItem(int itemID) throws RemoteException {
        return rmi.getItem(itemID);
    }

    @Override
    public ArrayList<String[]> getItems() throws RemoteException {
        return rmi.getItems();
    }

    @Override
    public void createType(int typeID, String name, int keep) throws RemoteException {
        rmi.createType(typeID, name, keep);
    }

    @Override
    public void deleteType(int typeID) throws RemoteException {
        rmi.deleteType(typeID);
    }

    @Override
    public void deleteTypes() throws RemoteException {
        rmi.deleteTypes();
    }

    @Override
    public void updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException {
        rmi.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public String[] getType(int typeID) throws RemoteException {
        return rmi.getType(typeID);
    }

    @Override
    public ArrayList<String[]> getTypes() throws RemoteException {
        return rmi.getTypes();
    }

    @Override
    public ArrayList<Item> getFridgeContents(int fid) throws RemoteException, SQLException {
        return rmi.getFridgeContents(fid);
    }
}
