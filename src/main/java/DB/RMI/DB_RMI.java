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
    public ArrayList<String[]> getTables(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            String[] err = {"re-login"};
            ArrayList<String[]> error = new ArrayList<>();
            error.add(err);
            return error;
        }
        return hs.getTables();
    }

    @Override
    public String adminLogin(String username, String password) {
        return hs.adminLogin(username, password, "");
    }


    @Override
    public boolean createUser(String uuid, String userName) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.createUser(userName);
    }

    @Override
    public ArrayList<String[]> getUser(String uuid, String userName) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.getUser(userName);
    }

    @Override
    public ArrayList<String[]> getUsers(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return null;
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String uuid, String username) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.getCompleteUser(username);
    }

    @Override
    public boolean updateUser(String uuid, String userName, int fid, String newUserName) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.updateUser(userName, fid, newUserName);
    }

    @Override
    public boolean deleteUser(String uuid, String userName) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.deleteUser(userName);
    }


    @Override
    public boolean createItem(String uuid, String name, int typeid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return im.createItem(name, typeid);
    }

    @Override
    public ArrayList<String[]> getItem(String uuid, int itemID) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return im.getItem(itemID);
    }

    @Override
    public ArrayList<String[]> getItems(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return im.getItems();
    }

    @Override
    public boolean updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return im.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public boolean deleteItem(String uuid, int itemid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return im.deleteItem(itemid);
    }


    @Override
    public boolean createType(String uuid, String name, int keep) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.createType(name, keep);
    }

    @Override
    public ArrayList<String[]> getType(String uuid, int typeid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.getType(typeid);
    }

    @Override
    public ArrayList<String[]> getTypes(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.getTypes();
    }

    @Override
    public boolean updateType(String uuid, int typeid, String typeName, int keep, int newTypeid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.updateType(typeid, typeName, keep, newTypeid);
    }

    @Override
    public boolean deleteType(String uuid, int typeid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.deleteType(typeid);
    }


    @Override
    public boolean createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.createFridgeRow(fid, itemid, expiration, amount);
    }

    @Override
    public ArrayList<String[]> getFridgeItem(String uuid, int fid, int itemid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.getFridgeItem(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getFridge(String uuid, int fid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.getFridge(fid);
    }

    @Override
    public ArrayList<String[]> getAllFridgeRows(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.getAllFridgeRows();
    }

    @Override
    public ArrayList<String[]> getFridgeContents(String uuid, int fid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.getFridgeContents(fid);
    }

    @Override
    public boolean updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public boolean deleteFridgeRow(String uuid, int fid, int itemid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return fm.deleteFridgeRow(fid, itemid);
    }

}
