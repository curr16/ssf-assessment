package vttp2022.ssf.ssf_assessment.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.ssf_assessment.models.News;

@Service
public class NewsService {
    
    private String URL = "https://min-api.cryptocompare.com/data/v2/news/";

    @Value("${CRYPTO_KEY}")
    private String key;

    public List<News> getArticles() {
       
        String payload;

        // Create url with query string (add parameters)
        String uri = UriComponentsBuilder.fromUriString(URL)
        .queryParam("api_key", key)
        .toUriString();

        // Create the GET request, GET url
        RequestEntity<Void> req = RequestEntity.get(uri).build();

        // Make the call to the website
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp;

        try {
            // Throws exception if status code is not between 200 - 399
            resp = template.exchange(req, String.class);
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e);
            return Collections.emptyList();
        }

        // Check status code
        if (resp.getStatusCodeValue() != 200) {
            System.err.println("Error status code is not 200\n");
            return Collections.emptyList();
        }

        // Get payload 
        payload = resp.getBody();

        // Convert payload to JsonObject
        // Convert the String to a Reader
        Reader strReader = new StringReader(payload);

        // Create a JsonReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);

        // Read the payload as Json object
        JsonObject newsResult = jsonReader.readObject();

        // should tally with the object name from api
        JsonArray resultList = newsResult.getJsonArray("Data");

        List<News> list = new LinkedList<>();

        for (int i = 0; i < resultList.size(); i++) {
            JsonObject jo = resultList.getJsonObject(i);
            list.add(News.create(jo));    
        }
        return list;
    }

    public static void saveArticles() {


    }
     
}