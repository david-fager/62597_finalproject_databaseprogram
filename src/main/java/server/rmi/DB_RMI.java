package server.rmi;

import common.rmi.SkeletonRMI;
import common.ResponseObject;
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
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getTables() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return hs.getTables(uuid);
    }

    @Override
    public ResponseObject login(String username, String password) {
        return hs.login(username, password, "");
    }

    @Override
    public ResponseObject validateUUID(String uuid) {
        return hs.validateUUID(uuid);
    }


    @Override
    public ResponseObject createUser(String uuid, String userName) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI createUser() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.createUser(uuid, userName);
    }

    @Override
    public ResponseObject getUser(String uuid, String userName) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getUser() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.getUser(uuid, userName);
    }

    @Override
    public ResponseObject getUsers(String uuid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getUsers() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.getUsers(uuid);
    }

    @Override
    public ResponseObject getCompleteUser(String uuid, String username) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getCompleteUser() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.getCompleteUser(uuid, username);
    }

    @Override
    public ResponseObject updateUser(String uuid, String userName, int fid, String newUserName) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI updateUser() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.updateUser(uuid, userName, fid, newUserName);
    }

    @Override
    public ResponseObject deleteUser(String uuid, String userName) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI deleteUser() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.deleteUser(uuid, userName);
    }


    @Override
    public ResponseObject createItem(String uuid, String name, int typeid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI createItem() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return im.createItem(uuid, name, typeid);
    }

    @Override
    public ResponseObject getItem(String uuid, int itemID) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getItem() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return im.getItem(uuid, itemID);
    }

    @Override
    public ResponseObject getItems(String uuid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getItems() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return im.getItems(uuid);
    }

    @Override
    public ResponseObject updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI updateItem() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return im.updateItem(uuid, itemid, itemName, typeid, newitemid);
    }

    @Override
    public ResponseObject deleteItem(String uuid, int itemid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI deleteItem() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return im.deleteItem(uuid, itemid);
    }


    @Override
    public ResponseObject createType(String uuid, String name, int keep) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI createType() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.createType(uuid, name, keep);
    }

    @Override
    public ResponseObject getType(String uuid, int typeid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getType() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.getType(uuid, typeid);
    }

    @Override
    public ResponseObject getTypes(String uuid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getTypes() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.getTypes(uuid);
    }

    @Override
    public ResponseObject updateType(String uuid, int typeid, String typeName, int keep, int newTypeid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI updateType() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.updateType(uuid, typeid, typeName, keep, newTypeid);
    }

    @Override
    public ResponseObject deleteType(String uuid, int typeid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI deleteType() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.deleteType(uuid, typeid);
    }


    @Override
    public ResponseObject createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI createFridgeRow() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.createFridgeRow(uuid, fid, itemid, expiration, amount);
    }

    @Override
    public ResponseObject getFridgeItem(String uuid, int fid, int itemid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getFridgeItem() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.getFridgeItem(uuid, fid, itemid);
    }

    @Override
    public ResponseObject getFridge(String uuid, int fid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getFridge() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.getFridge(uuid, fid);
    }

    @Override
    public ResponseObject getAllFridgeRows(String uuid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getAllFridgeRows() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.getAllFridgeRows(uuid);
    }

    @Override
    public ResponseObject getFridgeContents(String uuid, int fid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI getFridgeContents() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.getFridgeContents(uuid, fid);
    }

    @Override
    public ResponseObject updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI updateFridgeRow() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.updateFridgeRow(uuid, fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public ResponseObject deleteFridgeRow(String uuid, int fid, int itemid) {
        // Printing request info
        System.out.println(hs.logInfo(uuid) + " RMI deleteFridgeRow() " + getRef().remoteToString());

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.deleteFridgeRow(uuid, fid, itemid);
    }

}
