package info.youhavethewrong.jsonfest;

import java.io.*;

public abstract class JsonFestTest {

    abstract public void findVistedOnReddit() throws Exception;

    abstract public void findOldestAndYoungest() throws Exception;

    abstract public void findLinkCountInDeliciousDump() throws Exception;

    protected FileReader getRedditFileReader() throws Exception {
	return new FileReader(new File(System.getProperty("user.dir") + "/src/test/resources/reddit.json"));
    }

    protected FileReader getGeneratedFileReader() throws Exception {
	return new FileReader(new File(System.getProperty("user.dir") + "/src/test/resources/generated.json"));
    }

    protected FileReader getDeliciousFileReader() throws Exception {
	return new FileReader(new File(System.getProperty("user.dir") + "/src/test/resources/delicious.json"));
    }
}
