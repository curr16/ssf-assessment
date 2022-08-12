package vttp2022.ssf.ssf_assessment.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class News {

    private String id;
    private String publishedOn;
    private String title;
    private String website;
    private String imageUrl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getPublishedOn() {return publishedOn;}
    public void setPublishedOn(String publishedOn) {this.publishedOn = publishedOn;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getWebsite() {return website;}
    public void setWebsite(String website) {this.website = website;}

    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}

    public String getTags() {return tags;}
    public void setTags(String tags) {this.tags = tags;}

    public String getCategories() {return categories;}
    public void setCategories(String categories) {this.categories =  categories;}

    //convert from Json to model objects

    public static News create(JsonObject jo) {
        News n = new News();
        n.setId(jo.getString("id"));
        n.setPublishedOn(jo.getString("published_on"));
        n.setTitle(jo.getString("title"));
        n.setWebsite(jo.getString("url"));
        n.setImageUrl(jo.getString("imageurl"));
        n.setBody(jo.getString("tags"));
        n.setCategories(jo.getString("categories"));
        return n;
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("publishedOn", publishedOn)
            .add("title", title)
            .add("website", website)
            .add("imageUrl", imageUrl)
            .add("body", body)
            .add("tags", tags)
            .add("categories", categories)
            .build();
    }
}
