package DB.SOAP;

import Common.RMI.SkeletonRMI;
import Common.SOAP.SkeletonSOAP;

import javax.jws.WebService;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("/NonAsciiCharacters")
@WebService(endpointInterface = "Common.SOAP.SkeletonSOAP")
public class DB_SOAP implements SkeletonSOAP {

    private SkeletonRMI rmi;

    public DB_SOAP(SkeletonRMI rmi){
        this.rmi = rmi;
    }


    @Override
    public ArrayList<String[]> getTables() throws RemoteException {
        return rmi.getTables();
    }


    @Override
    public String adminLogin(String username, String password) throws RemoteException {
        return rmi.adminLogin(username, password);
    }


    @Override
    public boolean createUser(String userName) throws RemoteException {
        return rmi.createUser(userName);
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
    public ArrayList<String[]> getCompleteUser(String username) throws RemoteException, SQLException {
        return rmi.getCompleteUser(username);
    }

    @Override
    public boolean updateUser(String newUserName, int fid, String userName) throws RemoteException {
        return rmi.updateUser(newUserName, fid, userName);
    }

    @Override
    public boolean deleteUser(String userName) throws RemoteException {
        return rmi.deleteUser(userName);
    }


    @Override
    public boolean createItem(String name, int typeID) throws RemoteException {
        return rmi.createItem(name, typeID);
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
    public boolean updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException {
        return rmi.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public boolean deleteItem(int itemid) throws RemoteException {
        return rmi.deleteItem(itemid);
    }


    @Override
    public boolean createType(String name, int keep) throws RemoteException {
        return rmi.createType(name, keep);
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
    public boolean updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException {
        return rmi.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public boolean deleteType(int typeID) throws RemoteException {
        return rmi.deleteType(typeID);
    }


    @Override
    public boolean createFridgeRow(int fid, int itemid, String expiration, int amount) throws RemoteException {
        return rmi.createFridgeRow(fid, itemid, expiration, amount);
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
    public ArrayList<String[]> getFridgeContents(int fid) throws RemoteException, SQLException {
        return rmi.getFridgeContents(fid);
    }

    @Override
    public boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) throws RemoteException {
        return rmi.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public boolean deleteFridgeRow(int fid, int itemid) throws RemoteException {
        return rmi.deleteFridgeRow(fid, itemid);
    }

}
