package angeloid.sopiane.sptwit;

import java.text.SimpleDateFormat;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class BasicInfo {

	public static final String TWIT_API_KEY = "8w3ZpHCoLLMgVSrgFU0cew";
	public static final String TWIT_CONSUMER_KEY = "8w3ZpHCoLLMgVSrgFU0cew";
	public static final String TWIT_CONSUMER_SECRET = "Rw0PsFS7jimZ0BHAeTZjI7G7NKfaGeDD1yBMdmunLY";
	public static final String TWIT_CALLBACK_URL = "http://www.sirospace.com";

	public static final int REQ_CODE_TWIT_LOGIN = 1001;

	public static boolean TwitLogin = false;
	public static Twitter TwitInstance = null;
	public static AccessToken TwitAccessToken = null;
	public static RequestToken TwitRequestToken = null;

	public static String TWIT_KEY_TOKEN = "";
	public static String TWIT_KEY_TOKEN_SECRET = "";
	public static String TwitScreenName = "";

	public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

}
