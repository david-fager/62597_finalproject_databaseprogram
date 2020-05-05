package DB.SOAP;

import Common.SOAP.SkeletonSOAP;
import DB.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("/NonAsciiCharacters")
@WebService(endpointInterface = "Common.SOAP.SkeletonSOAP")
public class DB_SOAP implements SkeletonSOAP {

    private HelperSingleton hs = HelperSingleton.getInstance();

    private UserMethods um;
    private FridgeMethods fm;
    private ItemMethods im;
    private TypeMethods tm;

    public DB_SOAP(UserMethods um, FridgeMethods fm, ItemMethods im, TypeMethods tm) {
        this.um = um;
        this.fm = fm;
        this.im = im;
        this.tm = tm;
    }

    @Resource
    WebServiceContext context;

    @Override
    public ArrayList<String[]> getTables() throws RemoteException {
        return hs.getTables();
    }

    @Override
    public String adminLogin(String username, String password) throws RemoteException {
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println("[" + exchange.getRequestMethod() + "]");
        return hs.adminLogin(username, password);
    }


    @Override
    public boolean createUser(String userName) throws RemoteException {
        return um.createUser(userName);
    }

    @Override
    public ArrayList<String[]> getUser(String userName) throws RemoteException {
        return um.getUser(userName);
    }

    @Override
    public ArrayList<String[]> getUsers() throws RemoteException {
        return um.getUsers();
    }

    @Override
    public ArrayList<String[]> getCompleteUser(String username) throws RemoteException, SQLException {
        return um.getCompleteUser(username);
    }

    @Override
    public boolean updateUser(String newUserName, int fid, String userName) throws RemoteException {
        return um.updateUser(newUserName, fid, userName);
    }

    @Override
    public boolean deleteUser(String userName) throws RemoteException {
        return um.deleteUser(userName);
    }


    @Override
    public boolean createItem(String name, int typeID) throws RemoteException {
        return im.createItem(name, typeID);
    }

    @Override
    public ArrayList<String[]> getItem(int itemID) throws RemoteException {
        return im.getItem(itemID);
    }

    @Override
    public ArrayList<String[]> getItems() throws RemoteException {
        return im.getItems();
    }

    @Override
    public boolean updateItem(int itemid, String itemName, int typeid, int newitemid) throws RemoteException {
        return im.updateItem(itemid, itemName, typeid, newitemid);
    }

    @Override
    public boolean deleteItem(int itemid) throws RemoteException {
        return im.deleteItem(itemid);
    }


    @Override
    public boolean createType(String name, int keep) throws RemoteException {
        return tm.createType(name, keep);
    }

    @Override
    public ArrayList<String[]> getType(int typeID) throws RemoteException {
        return tm.getType(typeID);
    }

    @Override
    public ArrayList<String[]> getTypes() throws RemoteException {
        return tm.getTypes();
    }

    @Override
    public boolean updateType(int typeID, String typeName, int keep, int newTypeID) throws RemoteException {
        return tm.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public boolean deleteType(int typeID) throws RemoteException {
        return tm.deleteType(typeID);
    }


    @Override
    public boolean createFridgeRow(int fid, int itemid, String expiration, int amount) throws RemoteException {
        return fm.createFridgeRow(fid, itemid, expiration, amount);
    }

    @Override
    public ArrayList<String[]> getFridgeItem(int fid, int itemid) throws RemoteException {
        return fm.getFridgeItem(fid, itemid);
    }

    @Override
    public ArrayList<String[]> getFridge(int fid) throws RemoteException{
        return fm.getFridge(fid);
    }

    @Override
    public ArrayList<String[]> getAllFridgeRows() throws RemoteException {
        return fm.getAllFridgeRows();
    }

    @Override
    public ArrayList<String[]> getFridgeContents(int fid) throws RemoteException, SQLException {
        return fm.getFridgeContents(fid);
    }

    @Override
    public boolean updateFridgeRow(int fid, int itemid, int newFid, int newItemid, String newExpiration, int newAmount) throws RemoteException {
        return fm.updateFridgeRow(fid, itemid, newFid, newItemid, newExpiration, newAmount);
    }

    @Override
    public boolean deleteFridgeRow(int fid, int itemid) throws RemoteException {
        return fm.deleteFridgeRow(fid, itemid);
    }

}
