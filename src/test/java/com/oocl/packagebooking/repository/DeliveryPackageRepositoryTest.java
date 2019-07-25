package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.DeliveryPackage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeliveryPackageRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DeliveryPackageRepository deliveryPackageRepository;

    @Before
    public void initData () {
        DeliveryPackage deliveryPackage = new DeliveryPackage(1, "z", "111");
        DeliveryPackage deliveryPackage2 = new DeliveryPackage(2, "x", "222");
        DeliveryPackage deliveryPackage3 = new DeliveryPackage(3, "c", "333");
        testEntityManager.persist(deliveryPackage);
        testEntityManager.persist(deliveryPackage2);
        testEntityManager.persist(deliveryPackage3);
    }

    @Test
    public void should_return_size_0_when_find_by_status () {
        int size = deliveryPackageRepository.findByStatusIs(1).size();
        Assert.assertEquals(0, size);
    }

//    @Test
//    public void should_return_size_3_when_find_by_status () {
//        int size = deliveryPackageRepository.findByStatusIs(0).size();
//        Assert.assertEquals(3, size);
//    }
//
//    @Test
//    public void should_return_1_when_update_by_status_is_2 () {
//        int status = deliveryPackageRepository.updatePackageByStatusIsTwo(1);
//        Assert.assertEquals(1, status);
//    }
//
//    @Test
//    public void should_return_1_when_update_by_waybill_number_to_update_bookTime () {
//        int status = deliveryPackageRepository.updatePackageTimeByWayBillNumber(new Date(), 1);
//        Assert.assertEquals(1, status);
//    }
}