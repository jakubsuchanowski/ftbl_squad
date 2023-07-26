//package com.js.backend.controllers;
//
//import com.jayway.jsonpath.JsonPath;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class  EventControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(username = "user", password = "password", roles = "USER")
//    public void showAllEventsTest() throws Exception {
//        mockMvc.perform(get("/events"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "kuba", password = "kuba123", roles = "USER")
//    public void showEventsForUserTest() throws Exception {
//        mockMvc.perform(get("/user/events"))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//    }
//}