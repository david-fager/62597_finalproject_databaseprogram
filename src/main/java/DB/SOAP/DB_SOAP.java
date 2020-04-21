package DB.SOAP;

import Common.RMI.SkeletonRMI;
import Common.SOAP.SkeletonSOAP;
import DB.Item;

import javax.jws.WebService;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


@SuppressWarnings("/NonAsciiCharacters")
@WebService(endpointInterface = "Common.SOAP.SkeletonSOAP")
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

    @Override
    public String[] getFridgeItem(int fid, int itemid) throws RemoteException {
        return rmi.getFridgeItem(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getFridge(int fid) throws RemoteException{
        return rmi.getFridge(fid);
    }

    @Override
    public ArrayList<String[]> getAllFridgeRows() throws RemoteException {
        return rmi.getAllFridgeRows();
    }

    @Override
    public void createFridgeRow(int fid, int itemid, Date expiration, int amount) throws RemoteException {
        rmi.createFridgeRow(fid, itemid,expiration,amount);
    }

    @Override
    public void updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount) throws RemoteException {
        rmi.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public void deleteFridgeRow(int fid, int itemid) throws RemoteException {
        rmi.deleteFridgeRow(fid, itemid);
    }

}
