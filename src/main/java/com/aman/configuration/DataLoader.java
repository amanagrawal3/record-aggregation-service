package com.aman.configuration;

import com.aman.dto.request.CreateRecord;
import com.aman.dto.request.RegisterUser;
import com.aman.service.RecordService;
import com.aman.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {

    /**
     * Load record and user data into db
     *
     * @param recordService
     * @param userService
     * @param objectMapper
     * @return
     */
    @Bean
    CommandLineRunner runner(RecordService recordService, UserService userService, ObjectMapper objectMapper) {
        return args -> {
            // read json and write to db
            loadRecords(recordService, objectMapper);
            loadUsers(userService, objectMapper);
        };
    }

    private void loadRecords(RecordService recordService, ObjectMapper objectMapper) {
        TypeReference<List<CreateRecord>> typeReference = new TypeReference<List<CreateRecord>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/records.json");
        try {
            List<CreateRecord> createRecords = objectMapper.readValue(inputStream, typeReference);
            createRecords.forEach(recordService::createRecord);
            System.out.println("Records Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save records: " + e.getMessage());
        }
    }

    private void loadUsers(UserService userService, ObjectMapper objectMapper) {
        TypeReference<List<RegisterUser>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
        try {
            List<RegisterUser> registerUsers = objectMapper.readValue(inputStream, typeReference);
            registerUsers.forEach(userService::registerUser);
            System.out.println("Users Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }
}
