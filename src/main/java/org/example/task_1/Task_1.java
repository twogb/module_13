package org.example.task_1;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import java.io.FileWriter;


public class Task_1 {

    private final static String URL_STRING = "https://jsonplaceholder.typicode.com/users";

    private final static String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static String createNewCustomer(String newJsonCustomer) throws Exception {
        Response response = Request.Post(URL_STRING)
                .bodyString(newJsonCustomer, ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();
        String body = EntityUtils.toString(httpResponse.getEntity());

        if (httpResponse.getStatusLine().getStatusCode() == 201) {
            return body;
        } else {
            throw new Exception("Error" + httpResponse.getStatusLine().getStatusCode());
        }
    }


    public static String updateCustomer(int idCustomer, String updateCustomer) throws Exception {
        Response response = Request.Put(URL_STRING + "/" + idCustomer)
                .bodyString(updateCustomer, ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();
        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return responseBody;
        } else {
            throw new Exception("Error" + httpResponse.getStatusLine().getStatusCode());
        }
    }


    public static void deleteCustomer(int idCustomer) throws Exception {
        Response response = Request.Delete(URL_STRING + "/" + idCustomer).execute();

        HttpResponse httpResponse = response.returnResponse();

        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            throw new Exception("Error" + httpResponse.getStatusLine().getStatusCode());

        }

    }

    public static String getCustomerById(int idCustomer) throws Exception {

        Response response = Request.Get(URL_STRING + "/" + idCustomer).execute();

        HttpResponse httpResponse = response.returnResponse();
        String responseEntity = EntityUtils.toString(httpResponse.getEntity());

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return responseEntity;
        } else {
            throw new Exception("Error" + httpResponse.getStatusLine().getStatusCode());

        }

    }

    public static String getAllCustomer() throws Exception {
        Response response = Request.Get(URL_STRING).execute();

        HttpResponse httpResponse = response.returnResponse();
        String responseEntity = EntityUtils.toString(httpResponse.getEntity());

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return responseEntity;
        } else {
            throw new Exception("Error" + httpResponse.getStatusLine().getStatusCode());

        }
    }


    public static String getCustomerByName(String customerName) throws Exception {
        Response response = Request.Get(URL_STRING + "/" + customerName).execute();

        HttpResponse httpResponse = response.returnResponse();
        String responseEntity = EntityUtils.toString(httpResponse.getEntity());

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return responseEntity;
        } else {
            throw new Exception("Error" + httpResponse.getStatusLine().getStatusCode());

        }

    }

    public static void getAllCommentForLast(int userId) throws Exception {

        String exampleJson = Request.Get(BASE_URL + "/users/" + userId + "/posts")
                .execute()
                .returnContent()
                .asString();

        int lastUserId = 0;

        String comnents = Request.Get(BASE_URL + "/posts/" + lastUserId + "/comments")
                .execute()
                .returnContent()
                .asString();

        String fileName = "user-" + userId + "-post-" + lastUserId + "-comments.json";
        try(FileWriter fileWriter = new FileWriter(fileName))
        {
            fileWriter.write(comnents);
        }

        System.out.println(fileName);
    }

    public static String getOpenTask(int userId) throws Exception
    {
        String json = Request.Get(BASE_URL + "/users/" + userId + "/todos")
                .execute()
                .returnContent()
                .asString();

        return json;

    }


    public static void main(String[] args) throws Exception {
        try {
            String createCustomer = "{" +
                    "\"name\":\"John Doe\"," +
                    "\"username\":\"johndoe\"," +
                    "\"email\":\"johndoe@example.com\"," +
                    "\"address\":{" +
                    "    \"street\":\"123 Main St\"," +
                    "    \"suite\":\"Apt 45\"," +
                    "    \"city\":\"City\"," +
                    "    \"zipcode\":\"12345\"," +
                    "    \"geo\":{" +
                    "        \"lat\":\"40.7128\"," +
                    "        \"lng\":\"-74.0060\"" +
                    "    }" +
                    "}," +
                    "\"phone\":\"123-456-7890\"," +
                    "\"website\":\"www.example.com\"," +
                    "\"company\":{" +
                    "    \"name\":\"Example Inc\"," +
                    "    \"catchPhrase\":\"Catch Phrase\"," +
                    "    \"bs\":\"BS\"" +
                    "}" +
                    "}";
            String resultCreate = createNewCustomer(createCustomer);
            System.out.println("New customer add " + resultCreate);

/*    System.out.println("---------------------------------------------------------");
    int customerId = 10;
    deleteCustomer(customerId);
    System.out.println("Delete");

    System.out.println("---------------------------------------------------------");
    int getCustomerId = 10;
    String getCustomerById = getCustomerById(getCustomerId);
    System.out.println(getCustomerById);

    System.out.println("---------------------------------------------------------");
    String getAllCustomer = getAllCustomer();
    System.out.println(getAllCustomer)
    ;
    System.out.println("---------------------------------------------------------");
    String customerByName = "John";
    String getCustomerName = getCustomerByName(customerByName);
    System.out.println(getCustomerName);*/

        } catch (Exception e) {
            e.printStackTrace();
        }


      /*  System.out.println("---------------------------------------------------------");

        int userId = 1;
        getAllCommentForLast(userId);

        System.out.println("---------------------------------------------------------");

        String openTask = getOpenTask(userId);
        System.out.println(userId + " " + openTask);*/

    }
}