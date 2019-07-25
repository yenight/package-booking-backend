package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.service.DeliveryPackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(DeliveryPackageController.class)
public class DeliveryPackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeliveryPackageService deliveryPackageService;

    @Test
    public void should_return_all_package_when_request_find_all_package_api() throws Exception{
        List<DeliveryPackage> deliveryPackages = new ArrayList<>();
        deliveryPackages.add(new DeliveryPackage(123456, "zxc", "666666"));
        deliveryPackages.add(new DeliveryPackage(654321, "cxz", "7777777"));

        given(deliveryPackageService.getPackages()).willReturn(deliveryPackages);

        mockMvc.perform(get("/deliveryPackages"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].status").value(0))
                .andExpect(jsonPath("$.[0].waybillNumber").value(123456));
    }

    @Test
    public void should_return_package_when_request_created_all_package_api() throws Exception{
        DeliveryPackage deliveryPackage = new DeliveryPackage(123456, "zxc", "666666");
        given(deliveryPackageService.createdPackage(any(DeliveryPackage.class))).willReturn(deliveryPackage);

        mockMvc.perform(post("/deliveryPackages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(deliveryPackage)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.waybillNumber").value(123456))
                .andExpect(jsonPath("$.customerName").value("zxc"));
    }

    @Test
    public void should_return_packages_when_request_find_packages_by_status_api() throws Exception{
        List<DeliveryPackage> deliveryPackages = new ArrayList<>();
        deliveryPackages.add(new DeliveryPackage(123456, "zxc", "666666"));
        deliveryPackages.add(new DeliveryPackage(654321, "cxz", "7777777"));
        given(deliveryPackageService.getPackageByStatus(anyInt())).willReturn(deliveryPackages);

        mockMvc.perform(get("/deliveryPackages?status=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].status").value(0))
                .andExpect(jsonPath("$.[0].waybillNumber").value(123456));
    }

    @Test
    public void should_return_1_when_request_update_package_by_waybillNumber_api() throws Exception{
        DeliveryPackage deliveryPackage = new DeliveryPackage(123456, "zxc", "666666");
        given(deliveryPackageService.updatePackage(any(DeliveryPackage.class))).willReturn(1);

        mockMvc.perform(patch("/deliveryPackages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(deliveryPackage)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    public void should_return_1_when_request_update_package_time_by_waybillNumber_api() throws Exception{
        DeliveryPackage deliveryPackage = new DeliveryPackage(123456, "zxc", "666666", new Date());
        given(deliveryPackageService.updatePackage(any(DeliveryPackage.class))).willReturn(1);

        mockMvc.perform(patch("/deliveryPackages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(deliveryPackage)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }


}