package cz.novotm60.util;

import cz.novotm60.model.dao.ChangeOrderDao;
import cz.novotm60.model.entity.ChangeOrder;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.CreateCustomerChangeOrder;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class BatchUpdater {

    @Inject
    AppService appService;

    @Inject
    ChangeOrderDao changeOrderDao;

    @Schedule(second = "*", minute = "*", hour = "*")
    public void sendBatch() {
        for(ChangeOrder ch : changeOrderDao.getAll()) {
            //Create Change Order Type
            CreateCustomerChangeOrder cho = Converter.createCustomerChangeOrder(ch);
            Object res = appService.sendChangeOrder(cho);
            if(res != null) {
                ch.setSent(true);
                changeOrderDao.update(ch);
            }
        }
    }
}
