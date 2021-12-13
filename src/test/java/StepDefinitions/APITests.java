package StepDefinitions;

import DataProviders.DataObjects;
import Utilities.APIBase;
import Utilities.JsonIgnoreAttribute;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import javax.xml.crypto.Data;

public class APITests {

    public static Logger logger = Logger.getLogger(APITests.class.getName());

    APIBase apiBase = new APIBase();

    @Given("User wants to get list of user based on {string}")
    public void user_has_product_api_with(String filterCriteria) {
        String finalURI = "https://reqres.in/api/users?page=".concat(filterCriteria);
        DataObjects.setURL(finalURI);
        logger.info("The final URL is : " + finalURI);
        Assert.assertTrue(!finalURI.isEmpty());
    }

    @When("User hit the {string} API {string}")
    public void user_hit_api(String HTTPMethod, String testName) throws IOException {
        logger.info("The http method is : " + HTTPMethod);
        DataObjects.setTestName(testName);
        Boolean testFlag = apiBase.testAPI(DataObjects.getURL(), HTTPMethod, testName);
        Assert.assertTrue(testFlag);
    }

    @Then("User gets successful response {string}")
    public void user_gets_successful_response(String responseCode) {
            Assert.assertEquals(DataObjects.getStatusCode(), Integer.parseInt(responseCode));
    }

    @And("User is able to validate the {string} Output")
    public void validate_output_response(String HTTPMethod) throws IOException {
        String ignoreTags = "updatedAt,createdAt";
        List attributesToIgnore = Arrays.asList(ignoreTags.split(","));
        JSONCompareMode mode = JSONCompareMode.LENIENT;
        JSONCompareResult compareResult;
        String baselineContent = null;
        String compareContent = null;

        baselineContent = new String(Files.readAllBytes(Paths.get("StoredReports/" + DataObjects.getTestName() + "_Set1" + ".json")), "UTF-8");
        compareContent = new String(Files.readAllBytes(Paths.get("StoredReports/" + DataObjects.getTestName() + "_Set2" + ".json")), "UTF-8");

        if(HTTPMethod.equals("GET")){
            compareResult = JSONCompare.compareJSON(baselineContent, compareContent, mode);
        }else{
            compareResult = JSONCompare.compareJSON(baselineContent,compareContent,new JsonIgnoreAttribute(mode, new HashSet<>(attributesToIgnore)));
        }

        String result = compareResult.getMessage();
        String[] finalResult = result.split(";");
        int length = finalResult.length;

        for (int i = 0; i < length; i++) {
            logger.info("Differences : " + finalResult[i]);
        }

        if (compareResult.passed()) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("There are mismatches in the expected and actual responses");
        }

    }

    @Given("User wants to query category API with {string}")
    public void user_has_category_api(String filterCriteria) {
        String finalURI = "http://localhost:3030/categories?".concat(filterCriteria);
        DataObjects.setURL(finalURI);
        logger.info("The final URL is : " + finalURI);
        Assert.assertNull(finalURI);
    }

    @Given("User wants to query stores API with {string}")
    public void user_has_stores_api(String filterCriteria) {
        String finalURI = "http://localhost:3030/stores?".concat(filterCriteria);
        DataObjects.setURL(finalURI);
        logger.info("The final URL is : " + finalURI);
        Assert.assertNull(finalURI);
    }

    @Given("User wants to add or update a new {string} in the database with {string}")
    public void create_new_entry(String typeOfEntry, String URL) {
        ResourceBundle rb = ResourceBundle.getBundle("requestpayload");

        switch (typeOfEntry) {
            case "Create":
                DataObjects.setURL(URL);
                DataObjects.setRequestPayload(rb.getString("createUserPayload"));
                break;
            case "Update":
                DataObjects.setURL(URL);
                DataObjects.setRequestPayload(rb.getString("updateUserPayload"));
                break;
            default:
                logger.info("Invalid entry");
                break;
        }

    }

}

