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
    public boolean createUser(String userName) throws RemoteException {
        return rmi.createUser(userName);
    }

    @Override
    public boolean deleteUser(String userName) throws RemoteException {
        return rmi.deleteUser(userName);
    }

    @Override
    public boolean deleteUsers() throws RemoteException {
        return rmi.deleteUsers();
    }

    @Override
    public boolean updateUser(String newUserName, int fid, String userName) throws RemoteException {
        return rmi.updateUser(newUserName, fid, userName);
    }

    @Override
    public ArrayList<String[]> getUser(String userName) throws RemoteException {
        return rmi.getUser(userName);
    }

    @Override
    public ArrayList<String[]> getUsers() throws RemoteException {
        return rmi.getUsers();
    }

    @Override
    public boolean createItem(int id, String name, int typeID) throws RemoteException {
        return rmi.createItem(id, name, typeID);
    }

    @Override
    public boolean deleteItem(int itemid) throws RemoteException {
        return rmi.deleteItem(itemid);
    }

    @Override
    public boolean deleteItems() throws RemoteException {
        return rmi.deleteItems();
    }

    @Override
    public boolean updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException {
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
    public boolean createType(int typeID, String name, int keep) throws RemoteException {
        return rmi.createType(typeID, name, keep);
    }

    @Override
    public boolean deleteType(int typeID) throws RemoteException {
        return rmi.deleteType(typeID);
    }

    @Override
    public boolean deleteTypes() throws RemoteException {
        return rmi.deleteTypes();
    }

    @Override
    public boolean updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException {
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
    public boolean createFridgeRow(int fid, int itemid, Date expiration, int amount) throws RemoteException {
        return rmi.createFridgeRow(fid, itemid,expiration,amount);
    }

    @Override
    public boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, Date newExpiration, int newAmount) throws RemoteException {
        return rmi.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public boolean deleteFridgeRow(int fid, int itemid) throws RemoteException {
        return rmi.deleteFridgeRow(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getTables() throws RemoteException {
        return rmi.getTables();
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String username) throws RemoteException, SQLException {
        return rmi.getCompleteUser(username);
    }
}
