package info.youhavethewrong.jsonfest;

import java.util.*;

import org.json.*;
import org.junit.Test;

public class OrgJsonFestTest extends JsonFestTest {

    @Test
    @Override
    public void findVistedOnReddit() throws Exception {
	long start = System.currentTimeMillis();
	Map<String, String> visitedLinks = new HashMap<String, String>();
	JSONArray articles = new JSONObject(new JSONTokener(getRedditFileReader())).getJSONObject("data").getJSONArray("children");
	for (int i = 0; i < articles.length(); i++) {
	    JSONObject object = articles.getJSONObject(i).getJSONObject("data");
	    visitedLinks.put(object.getString("url"), "" + object.getBoolean("visited"));
	}
	long end = System.currentTimeMillis() - start;
	System.out.println("Org.JSON found " + visitedLinks.size() + " links on reddit's front page in " + end + " ms.");
    }

    @Test
    @Override
    public void findOldestAndYoungest() throws Exception {
	long start = System.currentTimeMillis();
	int oldest = 0;
	int youngest = 999;
	JSONArray people = new JSONArray(new JSONTokener(getGeneratedFileReader()));
	for (int i = 0; i < people.length(); i++) {
	    int age = people.getJSONObject(i).getInt("age");
	    if (age > oldest) {
		oldest = age;
	    }
	    if (age < youngest) {
		youngest = age;
	    }
	}
	long end = System.currentTimeMillis() - start;
	System.out.println("Org.JSON found an age range of " + youngest + " to " + oldest + " years after " + end + " ms.");
    }

    @Override
    public void findLinkCountInDeliciousDump() throws Exception {
	// TODO Auto-generated method stub

    }
}
