package com.mastery.java.task.story;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

public class EmployeeControllerSteps extends Steps {

    private Response response;

    private String url;

    @BeforeStory
    public void beforeMethod() {
        System.err.close();
        System.setErr(System.out);
    }

    @Given("Client and Employee Base with url $url")
    public void getRequestToServer(String url) {
        this.url = url;
    }

    @When("Client perform get request to Employee Base with id = $id")
    public void getEmployee(int id) {
        response = RestAssured.get(url + id);
    }

    @When("Client perform get request to Employee Base")
    public void getAllEmployees() {
        response = RestAssured.get(url);
    }

    @When("Client perform delete request to Employee Base with id = $id")
    public void deleteEmployee(int id) {
        response = RestAssured.delete(url + id);
    }

    @Then("Client get response with status $status")
    public void checkSuccessfulGetEmployee(String status) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status));
    }
}
