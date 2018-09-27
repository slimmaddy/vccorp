package com.mycompany.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;

public class ObjectToJson {
    public static String Convert(HashSet<Employee> tmp){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(tmp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
