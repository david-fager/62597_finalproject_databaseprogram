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
    public ArrayList<String[]> getTables(String uuid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return hs.getTables();
    }

    @Override
    public String adminLogin(String username, String password) {
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        return hs.adminLogin(username, password, exchange.getRemoteAddress().getAddress().toString());
    }


    @Override
    public boolean createUser(String uuid, String userName) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return um.createUser(userName);
    }

    @Override
    public ArrayList<String[]> getUser(String uuid, String userName) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return um.getUser(userName);
    }

    @Override
    public ArrayList<String[]> getUsers(String uuid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return um.getUsers();
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String uuid, String username) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return um.getCompleteUser(username);
    }

    @Override
    public boolean updateUser(String uuid, String newUserName, int fid, String userName) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return um.updateUser(newUserName, fid, userName);
    }

    @Override
    public boolean deleteUser(String uuid, String userName) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return um.deleteUser(userName);
    }


    @Override
    public boolean createItem(String uuid, String name, int typeID) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return im.createItem(name, typeID);
    }

    @Override
    public ArrayList<String[]> getItem(String uuid, int itemID) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return im.getItem(itemID);
    }

    @Override
    public ArrayList<String[]> getItems(String uuid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return im.getItems();
    }

    @Override
    public boolean updateItem(String uuid, int itemid, String itemName, int typeid, int newitemid) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return im.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public boolean deleteItem(String uuid, int itemid) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return im.deleteItem(itemid);
    }


    @Override
    public boolean createType(String uuid, String name, int keep) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return tm.createType(name, keep);
    }

    @Override
    public ArrayList<String[]> getType(String uuid, int typeID) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return tm.getType(typeID);
    }

    @Override
    public ArrayList<String[]> getTypes(String uuid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return tm.getTypes();
    }

    @Override
    public boolean updateType(String uuid, int typeID, String typeName, int keep, int newTypeID) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return tm.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public boolean deleteType(String uuid, int typeID) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return tm.deleteType(typeID);
    }


    @Override
    public boolean createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return fm.createFridgeRow(fid, itemid, expiration, amount);
    }

    @Override
    public ArrayList<String[]> getFridgeItem(String uuid, int fid, int itemid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return fm.getFridgeItem(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getFridge(String uuid, int fid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return fm.getFridge(fid);
    }

    @Override
    public ArrayList<String[]> getAllFridgeRows(String uuid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return fm.getAllFridgeRows();
    }

    @Override
    public ArrayList<String[]> getFridgeContents(String uuid, int fid) {
        if (!hs.validateUUID(uuid)) {
            return null;
        }
        return fm.getFridgeContents(fid);
    }

    @Override
    public boolean updateFridgeRow(String uuid, int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return fm.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public boolean deleteFridgeRow(String uuid, int fid, int itemid) {
        if (!hs.validateUUID(uuid)) {
            return false;
        }
        return fm.deleteFridgeRow(fid, itemid);
    }

}
