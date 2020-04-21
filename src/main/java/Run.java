import Common.SOAP.SkeletonSOAP;
import DB.RMI.DB_RMI;
import DB.SOAP.DB_SOAP;

import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Run {
    private static DateFormat df = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss] ");
    private static DB_SOAP soap;
    private static DB_RMI rmi;

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + "Starting server");

        // SOAP for the javaprogram terminal client
        java.rmi.registry.LocateRegistry.createRegistry(9920);
        rmi = new DB_RMI();
        Naming.rebind("rmi://localhost:9920/my_fridge_rmi_remote", rmi);


        soap = new DB_SOAP(rmi) ;
        Endpoint.publish("http://[::]:58008/my_fridge_soap_remote", soap);

        System.out.println(df.format(Calendar.getInstance().getTimeInMillis()) + "Server started");

        }
    }


