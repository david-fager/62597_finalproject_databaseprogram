package DB.SOAP;

import Common.SOAP.SkeletonSOAP;
import DB.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
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
    public ArrayList<String[]> getTables() {
        return hs.getTables();
    }

    @Override
    public String adminLogin(String username, String password) {
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println("[" + exchange.getRequestMethod() + "]");
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
    public boolean updateUser(String newUserName, int fid, String userName) {
        return um.updateUser(newUserName, fid, userName);
    }

    @Override
    public boolean deleteUser(String userName) {
        return um.deleteUser(userName);
    }


    @Override
    public boolean createItem(String name, int typeID) {
        return im.createItem(name, typeID);
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
    public ArrayList<String[]> getType(int typeID) {
        return tm.getType(typeID);
    }

    @Override
    public ArrayList<String[]> getTypes() {
        return tm.getTypes();
    }

    @Override
    public boolean updateType(int typeID, String typeName, int keep, int newTypeID) {
        return tm.updateType(typeID, typeName, keep, newTypeID);
    }

    @Override
    public boolean deleteType(int typeID) {
        return tm.deleteType(typeID);
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
