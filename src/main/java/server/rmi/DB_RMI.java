package server.rmi;

import common.rmi.SkeletonRMI;
import server.ResponseObject;
import server.database.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    public ResponseObject getTables(String uuid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return hs.getTables(uuid);
    }

    @Override
    public ResponseObject adminLogin(String username, String password) {
        return hs.adminLogin(username, password, "");
    }


    @Override
    public ResponseObject createUser(String uuid, String userName) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return um.createUser(uuid, userName);
    }

    @Override
    public ResponseObject getUser(String uuid, String userName) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return um.getUser(uuid, userName);
    }

    @Override
    public ResponseObject getUsers(String uuid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return null;
    }

    @Override
    public ResponseObject getCompleteUser(String uuid, String username) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return um.getCompleteUser(uuid, username);
    }

    @Override
    public ResponseObject updateUser(String uuid, String userName, int fid, String newUserName) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return um.updateUser(uuid, userName, fid, newUserName);
    }

    @Override
    public ResponseObject deleteUser(String uuid, String userName) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return um.deleteUser(uuid, userName);
    }


    @Override
    public ResponseObject createItem(String uuid, String name, int typeid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return im.createItem(uuid, name, typeid);
    }

    @Override
    public ResponseObject getItem(String uuid, int itemID) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return im.getItem(uuid, itemID);
    }

    @Override
    public ResponseObject getItems(String uuid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return im.getItems(uuid);
    }

    @Override
    public ResponseObject updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return im.updateItem(uuid, itemid, itemName, typeid, newitemid);
    }

    @Override
    public ResponseObject deleteItem(String uuid, int itemid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return im.deleteItem(uuid, itemid);
    }


    @Override
    public ResponseObject createType(String uuid, String name, int keep) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return tm.createType(uuid, name, keep);
    }

    @Override
    public ResponseObject getType(String uuid, int typeid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return tm.getType(uuid, typeid);
    }

    @Override
    public ResponseObject getTypes(String uuid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return tm.getTypes(uuid);
    }

    @Override
    public ResponseObject updateType(String uuid, int typeid, String typeName, int keep, int newTypeid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return tm.updateType(uuid, typeid, typeName, keep, newTypeid);
    }

    @Override
    public ResponseObject deleteType(String uuid, int typeid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return tm.deleteType(uuid, typeid);
    }


    @Override
    public ResponseObject createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.createFridgeRow(uuid, fid, itemid, expiration, amount);
    }

    @Override
    public ResponseObject getFridgeItem(String uuid, int fid, int itemid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.getFridgeItem(uuid, fid, itemid);
    }

    @Override
    public ResponseObject getFridge(String uuid, int fid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.getFridge(uuid, fid);
    }

    @Override
    public ResponseObject getAllFridgeRows(String uuid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.getAllFridgeRows(uuid);
    }

    @Override
    public ResponseObject getFridgeContents(String uuid, int fid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.getFridgeContents(uuid, fid);
    }

    @Override
    public ResponseObject updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.updateFridgeRow(uuid, fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public ResponseObject deleteFridgeRow(String uuid, int fid, int itemid) {
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }
        return fm.deleteFridgeRow(uuid, fid, itemid);
    }

}
