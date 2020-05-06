package DB.RMI;

import Common.RMI.SkeletonRMI;
import DB.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DB_RMI extends UnicastRemoteObject implements SkeletonRMI {

    private final HelperSingleton hs = HelperSingleton.getInstance();

    private final UserMethods um;
    private final FridgeMethods fm;
    private final ItemMethods im;
    private final TypeMethods tm;

    public DB_RMI(UserMethods um, FridgeMethods fm, ItemMethods im, TypeMethods tm) throws RemoteException {
        this.um = um;
        this.fm = fm;
        this.im = im;
        this.tm = tm;
    }

    @Override
    public ArrayList<String[]> getTables() {
        return hs.getTables();
    }

    @Override
    public String adminLogin(String username, String password) {
        return hs.adminLogin(username, password);
    }


    @Override
    public boolean createUser(String userName) {
        return um.createUser(userName);
    }

    @Override
    public ArrayList<String[]> getUser(String userName) {
        return um.getUser(userName);
    }

    @Override
    public ArrayList<String[]> getUsers() {
        return um.getUsers();
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String username) {
        return um.getCompleteUser(username);
    }

    @Override
    public boolean updateUser(String userName, int fid, String newUserName) {
        return um.updateUser(userName, fid, newUserName);
    }

    @Override
    public boolean deleteUser(String userName) {
        return um.deleteUser(userName);
    }


    @Override
    public boolean createItem(String name, int typeid) {
        return im.createItem(name, typeid);
    }

    @Override
    public ArrayList<String[]> getItem(int itemID) {
        return im.getItem(itemID);
    }

    @Override
    public ArrayList<String[]> getItems() {
        return im.getItems();
    }

    @Override
    public boolean updateItem(int itemid, String itemName, int typeid, int newitemid) {
        return im.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public boolean deleteItem(int itemid) {
        return im.deleteItem(itemid);
    }


    @Override
    public boolean createType(String name, int keep) {
        return tm.createType(name, keep);
    }

    @Override
    public ArrayList<String[]> getType(int typeid) {
        return tm.getType(typeid);
    }

    @Override
    public ArrayList<String[]> getTypes() {
        return tm.getTypes();
    }

    @Override
    public boolean updateType(int typeid, String typeName, int keep, int newTypeid) {
        return tm.updateType(typeid, typeName, keep, newTypeid);
    }

    @Override
    public boolean deleteType(int typeid) {
        return tm.deleteType(typeid);
    }


    @Override
    public boolean createFridgeRow(int fid, int itemid, String expiration, int amount) {
        return fm.createFridgeRow(fid, itemid, expiration, amount);
    }

    @Override
    public ArrayList<String[]> getFridgeItem(int fid, int itemid) {
        return fm.getFridgeItem(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getFridge(int fid) {
        return fm.getFridge(fid);
    }

    @Override
    public ArrayList<String[]> getAllFridgeRows() {
        return fm.getAllFridgeRows();
    }

    @Override
    public ArrayList<String[]> getFridgeContents(int fid) {
        return fm.getFridgeContents(fid);
    }

    @Override
    public boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) {
        return fm.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public boolean deleteFridgeRow(int fid, int itemid) {
        return fm.deleteFridgeRow(fid, itemid);
    }

}
