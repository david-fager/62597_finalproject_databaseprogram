package server.soap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import common.soap.SkeletonSOAP;
import server.ResponseObject;
import server.database.*;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@SuppressWarnings("/NonAsciiCharacters")
@WebService(endpointInterface = "common.soap.SkeletonSOAP")
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
    public ResponseObject getTables(String uuid) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getTables()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return hs.getTables(uuid);
    }

    @Override
    public ResponseObject adminLogin(String username, String password) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest("", "adminLogin()", exchange));

        // Calling the method that creates a proper response
        return hs.adminLogin(username, password, exchange.getRemoteAddress().getAddress().toString());
    }


    @Override
    public ResponseObject createUser(String uuid, String userName) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "createUser()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getUser()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getUsers()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getCompleteUser()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.getCompleteUser(uuid, username);
    }

    @Override
    public ResponseObject updateUser(String uuid, String newUserName, int fid, String userName) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "updateUser()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.updateUser(uuid, newUserName, fid, userName);
    }

    @Override
    public ResponseObject deleteUser(String uuid, String userName) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "deleteUser()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return um.deleteUser(uuid, userName);
    }


    @Override
    public ResponseObject createItem(String uuid, String name, int typeID) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "createItem()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return im.createItem(uuid, name, typeID);
    }

    @Override
    public ResponseObject getItem(String uuid, int itemID) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getItem()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getItems()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "updateItem()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "deleteItem()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "createType()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.createType(uuid, name, keep);
    }

    @Override
    public ResponseObject getType(String uuid, int typeID) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getType()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.getType(uuid, typeID);
    }

    @Override
    public ResponseObject getTypes(String uuid) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getTypes()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.getTypes(uuid);
    }

    @Override
    public ResponseObject updateType(String uuid, int typeID, String typeName, int keep, int newTypeID) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "updateType()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.updateType(uuid, typeID, typeName, keep, newTypeID);
    }

    @Override
    public ResponseObject deleteType(String uuid, int typeID) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "deleteType()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return tm.deleteType(uuid, typeID);
    }


    @Override
    public ResponseObject createFridgeRow(String uuid, int fid, int itemid, String expiration, int amount) {
        // Printing request info
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "createFridgeRow()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getFridgeItem()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getFridge()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getAllFridgeRows()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "getFridgeContents()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "updateFridgeRow()", exchange));

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
        HttpExchange exchange = (HttpExchange) context.getMessageContext().get(JAXWSProperties.HTTP_EXCHANGE);
        System.out.println(hs.logRequest(uuid, "deleteFridgeRow()", exchange));

        // Validating the uuid (user)
        ResponseObject ro = hs.validateUUID(uuid);
        if (ro.getStatusCode() != 0) {
            return ro;
        }

        // Calling the method that creates a proper response
        return fm.deleteFridgeRow(uuid, fid, itemid);
    }

}
