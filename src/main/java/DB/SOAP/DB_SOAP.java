package DB.SOAP;

import Common.SOAP.SkeletonSOAP;
import DB.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.rmi.RemoteException;
import java.util.ArrayList;

@SuppressWarnings("/NonAsciiCharacters")
@WebService(endpointInterface = "Common.SOAP.SkeletonSOAP")
public class DB_SOAP implements SkeletonSOAP {

    private final HelperSingleton hs = HelperSingleton.getInstance();

    private UserMethods um = null;
    private FridgeMethods fm = null;
    private ItemMethods im = null;
    private TypeMethods tm = null;

    public DB_SOAP(UserMethods um, FridgeMethods fm, ItemMethods im, TypeMethods tm) {
        this.um = um;
        this.fm = fm;
        this.im = im;
        this.tm = tm;
    }

    public DB_SOAP() {
    }

    @Resource
    WebServiceContext context;

    @Override
    public ArrayList<String[]> getTables(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return hs.getTables();
    }

    @Override
    public String adminLogin(String username, String password) {
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println("[" + exchange.getRequestMethod() + "]");
        return hs.adminLogin(username, password);
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
        return um.getUsers();
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String uuid, String username) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.getCompleteUser(username);
    }

    @Override
    public boolean updateUser(String uuid, String newUserName, int fid, String userName) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.updateUser(newUserName, fid, userName);
    }

    @Override
    public boolean deleteUser(String uuid, String userName) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return um.deleteUser(userName);
    }


    @Override
    public boolean createItem(String uuid, String name, int typeID) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return im.createItem(name, typeID);
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
    public ArrayList<String[]> getType(String uuid, int typeID) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.getType(typeID);
    }

    @Override
    public ArrayList<String[]> getTypes(String uuid) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.getTypes();
    }

    @Override
    public boolean updateType(String uuid, int typeID, String typeName, int keep, int newTypeID) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public boolean deleteType(String uuid, int typeID) throws RemoteException {
        if (!hs.validateUUID(uuid)) {
            throw new RemoteException();
        }
        return tm.deleteType(typeID);
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
