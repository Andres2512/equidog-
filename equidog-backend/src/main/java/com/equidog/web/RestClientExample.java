package com.equidog.web;

import com.equidog.adapter.manager.util.ClientUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClientExample {


    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate client = new RestTemplate();
        Map<String, String> headersValues = new HashMap<>();
        headersValues.put("accept", "application/json");
        headersValues.put("content-type", "application/json;charset=utf-8");
        HttpHeaders headers = ClientUtil.addHeaders(headersValues);
        String pathEndpoint = "http://dummy.restapiexample.com/api/v1/employees";
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<Example> responseEntity = client.exchange(pathEndpoint, HttpMethod.GET, new HttpEntity<>(headers), Example.class);
        String response = mapper.writeValueAsString(responseEntity.getBody());
        System.out.println(response);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "employee_name",
            "employee_salary",
            "employee_age",
            "profile_image"
    })
    static class Datum {

        @JsonProperty("id")
        private String id;
        @JsonProperty("employee_name")
        private String employeeName;
        @JsonProperty("employee_salary")
        private String employeeSalary;
        @JsonProperty("employee_age")
        private String employeeAge;
        @JsonProperty("profile_image")
        private String profileImage;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("employee_name")
        public String getEmployeeName() {
            return employeeName;
        }

        @JsonProperty("employee_name")
        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        @JsonProperty("employee_salary")
        public String getEmployeeSalary() {
            return employeeSalary;
        }

        @JsonProperty("employee_salary")
        public void setEmployeeSalary(String employeeSalary) {
            this.employeeSalary = employeeSalary;
        }

        @JsonProperty("employee_age")
        public String getEmployeeAge() {
            return employeeAge;
        }

        @JsonProperty("employee_age")
        public void setEmployeeAge(String employeeAge) {
            this.employeeAge = employeeAge;
        }

        @JsonProperty("profile_image")
        public String getProfileImage() {
            return profileImage;
        }

        @JsonProperty("profile_image")
        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "status",
            "data"
    })
    static class Example {

        @JsonProperty("status")
        private String status;
        @JsonProperty("data")
        private List<Datum> data = null;

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("data")
        public List<Datum> getData() {
            return data;
        }

        @JsonProperty("data")
        public void setData(List<Datum> data) {
            this.data = data;
        }

    }
}
