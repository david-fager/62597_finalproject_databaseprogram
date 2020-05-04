package DB.SOAP;

import Common.RMI.SkeletonRMI;
import Common.SOAP.SkeletonSOAP;

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
    public String createUser(int uid) throws RemoteException {
        return rmi.createUser(uid);
    }

    @Override
    public String deleteUser(int uid) throws RemoteException {
        return rmi.deleteUser(uid);
    }

    @Override
    public String deleteUsers() throws RemoteException {
        return rmi.deleteUsers();
    }

    @Override
    public String updateUser(int uid, int newuid, int fid) throws RemoteException {
        return rmi.updateUser(uid, newuid, fid);
    }

    @Override
    public ArrayList<String[]> getUser(int uid) throws RemoteException {
        return rmi.getUser(uid);
    }

    @Override
    public ArrayList<String[]> getUsers() throws RemoteException {
        return rmi.getUsers();
    }

    @Override
    public String createItem(int id, String name, int typeID) throws RemoteException {
        return rmi.createItem(id, name, typeID);
    }

    @Override
    public String deleteItem(int itemid) throws RemoteException {
        return rmi.deleteItem(itemid);
    }

    @Override
    public String deleteItems() throws RemoteException {
        return rmi.deleteItems();
    }

    @Override
    public String updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException {
        return rmi.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public ArrayList<String[]> getItem(int itemID) throws RemoteException {
        return rmi.getItem(itemID);
    }

    @Override
    public ArrayList<String[]> getItems() throws RemoteException {
        return rmi.getItems();
    }

    @Override
    public String createType(int typeID, String name, int keep) throws RemoteException {
        return rmi.createType(typeID, name, keep);
    }

    @Override
    public String deleteType(int typeID) throws RemoteException {
        return rmi.deleteType(typeID);
    }

    @Override
    public String deleteTypes() throws RemoteException {
        return rmi.deleteTypes();
    }

    @Override
    public String updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException {
        return rmi.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public ArrayList<String[]> getType(int typeID) throws RemoteException {
        return rmi.getType(typeID);
    }

    @Override
    public ArrayList<String[]> getTypes() throws RemoteException {
        return rmi.getTypes();
    }

    @Override
    public ArrayList<String[]> getFridgeContents(int fid) throws RemoteException, SQLException {
        return rmi.getFridgeContents(fid);
    }

    @Override
    public ArrayList<String[]> getFridgeItem(int fid, int itemid) throws RemoteException {
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
    public String createFridgeRow(int fid, int itemid, Date expiration, int amount) throws RemoteException {
        return rmi.createFridgeRow(fid, itemid,expiration,amount);
    }

    @Override
    public String updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount) throws RemoteException {
        return rmi.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public String deleteFridgeRow(int fid, int itemid) throws RemoteException {
        return rmi.deleteFridgeRow(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getTables() throws RemoteException {
        return rmi.getTables();
    }
}
