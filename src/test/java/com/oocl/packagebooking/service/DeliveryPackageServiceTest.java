package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.repository.DeliveryPackageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeliveryPackageServiceTest {

    @Autowired
    private DeliveryPackageService deliveryPackageService;

    @MockBean
    private DeliveryPackageRepository deliveryPackageRepository;

    @Test
    public void should_return_null_when_create_package () {
        //given
        when(deliveryPackageRepository.findById(anyLong())).thenReturn(null);
        //when
        DeliveryPackage deliveryPackage = deliveryPackageService.createdPackage(null);
        //then
        assertNull(deliveryPackage);
    }

    @Test
    public void should_return_0_when_update_package () {
        //given
        when(deliveryPackageRepository.updatePackageTimeByWayBillNumber(any(Date.class),anyLong())).thenReturn(-1);
        //when
        int result = deliveryPackageService.updatePackage(new DeliveryPackage(1, "1", "2"));
        //then
        assertEquals(0, result);
    }
}