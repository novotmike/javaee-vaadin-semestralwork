package cz.novotm60.service;

import cz.novotm60.service.soap.CreateCustomerChangeOrder;
import cz.novotm60.service.soap.CustomerDetailType;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.service.webservice.CustomerDatabase;
import cz.novotm60.service.webservice.CustomerDatabaseWSDL;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.xml.ws.Holder;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional
@ApplicationScoped
public class AppService {

    private java.net.URL URL;
    private CustomerDatabaseWSDL serviceManager;

    public AppService() {
        try {
            URL = new URL("http://localhost:8088/mockCustomerDatabaseSOAP?WSDL");
            serviceManager = new CustomerDatabase(URL).getSOAP();
        } catch (MalformedURLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Wrong url!");
        }
    }

    public Object sendChangeOrder(CreateCustomerChangeOrder changeOrder) {
        return serviceManager.createCustomerChangeOrder(changeOrder);
    }

    public List<CustomerType> getAllCustomers(BigInteger from, BigInteger to) {
        return serviceManager.readAllCustomers(from, to);
    }

    public void getCustomerDetail(Holder<BigInteger> id, Holder<String> status, Holder<CustomerDetailType> detail) {
        serviceManager.readCustomerDetails(id, status, detail);
    }

}
