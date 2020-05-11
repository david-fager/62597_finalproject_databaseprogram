import server.database.FridgeMethods;
import server.database.ItemMethods;
import server.database.TypeMethods;
import server.database.UserMethods;
import server.rmi.DB_RMI;
import server.soap.DB_SOAP;

import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Run {
    private static DateFormat df = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");

    private static DB_SOAP soap;
    private static DB_RMI rmi;

    private static UserMethods um;
    private static FridgeMethods fm;
    private static ItemMethods im;
    private static TypeMethods tm;

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Starting server");

        um = new UserMethods();
        fm = new FridgeMethods();
        im = new ItemMethods();
        tm = new TypeMethods();

        // Preparing the RMI
        System.setProperty("java.rmi.server.hostname","130.225.170.204");
        java.rmi.registry.LocateRegistry.createRegistry(9921);
        rmi = new DB_RMI(um, fm, im, tm);
        Naming.rebind("rmi://localhost:9921/my_fridge_rmi_remote", rmi);

        // Preparing the SOAP
        soap = new DB_SOAP(um, fm, im, tm);
        Endpoint.publish("http://[::]:58008/my_fridge_soap_remote", soap);

        printASCII();

        System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + " Server started");

    }

    private static void printASCII() {
        System.out.println("   ____   ____    _   _   ____    ____    _____     _    ___        ");
        System.out.println("  / ___| |  _ \\  | | | | |  _ \\  |  _ \\  | ____|   / |  / _ \\   ");
        System.out.println(" | |  _  | |_) | | | | | | |_) | | |_) | |  _|     | | | | | |      ");
        System.out.println(" | |_| | |  _ <  | |_| | |  __/  |  __/  | |___    | | | |_| |      ");
        System.out.println("  \\____| |_| \\_\\  \\___/  |_|     |_|     |_____|   |_|  \\___/  ");
        System.out.println("                                                                    ");
    }

}


