package info.youhavethewrong.jsonfest;

import java.util.*;

import org.junit.Test;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class GsonJsonFestTest extends JsonFestTest {

    @Test
    @Override
    public void findVistedOnReddit() throws Exception {
	long start = System.currentTimeMillis();
	Map<String, String> visitedLinks = new HashMap<String, String>();
	JsonParser parser = new JsonParser();
	JsonObject redditStuff = parser.parse(getRedditFileReader()).getAsJsonObject();
	JsonArray articles = redditStuff.get("data").getAsJsonObject().get("children").getAsJsonArray();
	for (JsonElement article : articles) {
	    JsonObject object = article.getAsJsonObject().get("data").getAsJsonObject();
	    visitedLinks.put(object.get("url").getAsString(), object.get("visited").getAsString());
	}
	long end = System.currentTimeMillis() - start;
	System.out.println("Gson found " + visitedLinks.size() + " links on reddit's front page in " + end + " ms.");
    }

    @Test
    @Override
    public void findOldestAndYoungest() throws Exception {
	long start = System.currentTimeMillis();
	int oldest = 0;
	int youngest = 999;
	JsonParser parser = new JsonParser();
	JsonArray people = parser.parse(getGeneratedFileReader()).getAsJsonArray();
	for (JsonElement person : people) {
	    int age = person.getAsJsonObject().get("age").getAsInt();
	    if (age > oldest) {
		oldest = age;
	    }
	    if (age < youngest) {
		youngest = age;
	    }
	}
	long end = System.currentTimeMillis() - start;
	System.out.println("Gson found an age range of " + youngest + " to " + oldest + " years after " + end + " ms.");
    }

    @Test
    @Override
    public void findLinkCountInDeliciousDump() throws Exception {
	long start = System.currentTimeMillis();
	List<String> articleIds = new ArrayList<String>();
	JsonParser parser = new JsonParser();
	JsonReader reader = new JsonReader(getDeliciousFileReader());
	reader.setLenient(true);
	JsonArray deli = parser.parse(reader).getAsJsonArray();
	for (JsonElement update : deli) {
	    if (update.isJsonObject() && update.getAsJsonObject().has("id")) {
		articleIds.add(update.getAsJsonObject().get("id").getAsString());
	    }
	}
	long end = System.currentTimeMillis() - start;
	System.out.println("Gson found " + articleIds.size() + " links in a delicious dump.  It took " + end + " ms.");
    }
}
