package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.DeliveryPackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryPackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryPackageController deliveryPackageController;

    @Test
    @Transactional
    public void should_return_all_package_when_request_find_all_package_api() throws Exception{
        List<DeliveryPackage> deliveryPackages = new ArrayList<>();
        deliveryPackages.add(new DeliveryPackage(123456, "zxc", "666666"));
        deliveryPackages.add(new DeliveryPackage(654321, "cxz", "7777777"));

//        given().willReturn(deliveryPackages);


        mockMvc.perform(get("/deliveryPackages"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect();
    }

    @Test
    @Transactional
    public void should_return_package_when_request_created_all_package_api() throws Exception{
        mockMvc.perform(post("/deliveryPackages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"waybillNumber\": 12312213,\n" +
                        "\t\"customerName\": \"eeee\",\n" +
                        "\t\"phoneNumber\": \"23123\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.waybillNumber").value(12312213))
                .andExpect(jsonPath("$.customerName").value("eeee"));
    }

    @Test
    @Transactional
    public void should_return_packages_when_request_find_packages_by_status_api() throws Exception{
        mockMvc.perform(get("/deliveryPackages?status=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    public void should_return_1_when_request_update_package_by_waybillNumber_api() throws Exception{
        mockMvc.perform(patch("/deliveryPackages/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    @Transactional
    public void should_return_1_when_request_update_package_time_by_waybillNumber_api() throws Exception{
        mockMvc.perform(patch("/deliveryPackages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"waybillNumber\": 12312213,\n" +
                        "\t\"bookTime\": \"2019-07-24 18:00:00\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }


}