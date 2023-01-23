package com.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.example.mapper.dto.User;
import com.example.mapper.dto.Car;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) throws JsonProcessingException {
        System.out.println("main");

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22나2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);
        //System.out.println(user);


        // objectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // objectMapper 를 통해 user 객체를 String 형태로 변환
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // objectMapper 를 통해 String 값을 Json형태로 변환
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        // cars 는 json 안에 또 하나의 json 배열로 묶여있기 때문에 JsonNode 타입으로 꺼냄
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;
        // arrayNode 에 담아둔 cars 값을 List<Car> 타입으로 변환
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        // ObjectNode 클래스를 사용하면 put 으로 json 특정 값을 변경할 수 있다.
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());
        System.out.println(jsonNode.toPrettyString());


    }

}
