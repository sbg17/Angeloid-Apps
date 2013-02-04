package angeloid.sopiane.sptwit;


import java.util.Date;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String TAG = "SPTwit";

	TextView nameText;
	Button connectBtn;
	StatusListView statusList;
	StatusListAdapter statusAdapter;
	ImageView status;

	Button writeBtn;
	EditText writeInput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connectBtn = (Button) findViewById(R.id.connectBtn);
		status = (ImageView) findViewById(R.id.status);
        connectBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		connect();
        	}
        });
        // 3.0 이상 메인에서 네트워크 호출 시 error 해결 StrictMode
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()             
             .detectDiskWrites()
        .detectNetwork()              
             .penaltyLog().build());    
        
        nameText = (TextView) findViewById(R.id.nameText);


        statusList = (StatusListView) findViewById(R.id.statusList);
        statusAdapter = new StatusListAdapter(this);
        statusList.setAdapter(statusAdapter);
        statusList.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v, int position, long id) {
				Status curItem = (Status) statusAdapter.getItem(position);
				String curText = curItem.getText();

			}
		});

        writeBtn = (Button) findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		String statusText = writeInput.getText().toString();
        		if (statusText.length() < 1) {
  
        			return;
        		}

        		updateStatus(statusText);
        	}
        });

        writeInput = (EditText) findViewById(R.id.writeInput);

    }
	 private void updateStatus(String statusText) {
	    	try {
	    		Status status = BasicInfo.TwitInstance.updateStatus(statusText);
	    		Date curDate = status.getCreatedAt();


	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	}

	    }
	 private void connect() {
			Log.d(TAG, "connect() called.");

			if (BasicInfo.TwitLogin) {
				Log.d(TAG, "twitter already logged in.");
				

				try {
					ConfigurationBuilder builder = new ConfigurationBuilder();

					builder.setOAuthAccessToken(BasicInfo.TWIT_KEY_TOKEN);
					builder.setOAuthAccessTokenSecret(BasicInfo.TWIT_KEY_TOKEN_SECRET);
					builder.setOAuthConsumerKey(BasicInfo.TWIT_CONSUMER_KEY);
					builder.setOAuthConsumerSecret(BasicInfo.TWIT_CONSUMER_SECRET);

					Configuration config = builder.build();
					TwitterFactory tFactory = new TwitterFactory(config);
					BasicInfo.TwitInstance = tFactory.getInstance();
					status.setImageResource(R.drawable.apply);
				

		    	} catch (Exception ex) {
					ex.printStackTrace();
				}


			} else {

		    	try {
					ConfigurationBuilder builder = new ConfigurationBuilder();
					builder.setDebugEnabled(true);
					builder.setOAuthConsumerKey(BasicInfo.TWIT_CONSUMER_KEY);
					builder.setOAuthConsumerSecret(BasicInfo.TWIT_CONSUMER_SECRET);

					TwitterFactory factory = new TwitterFactory(builder.build());
					Twitter mTwit = factory.getInstance();
					RequestToken mRequestToken = mTwit.getOAuthRequestToken();
					String outToken = mRequestToken.getToken();
					String outTokenSecret = mRequestToken.getTokenSecret();

					Log.d(TAG, "Request Token : " + outToken + ", " + outTokenSecret);
					Log.d(TAG, "AuthorizationURL : " + mRequestToken.getAuthorizationURL());

					BasicInfo.TwitInstance = mTwit;
					BasicInfo.TwitRequestToken = mRequestToken;

					Intent intent = new Intent(this, TwitLogin.class);
					intent.putExtra("authUrl", mRequestToken.getAuthorizationURL());
					startActivityForResult(intent, BasicInfo.REQ_CODE_TWIT_LOGIN);
					status.setImageResource(R.drawable.apply);
		    	} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

	    }


		protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
			super.onActivityResult(requestCode, resultCode, resultIntent);

			if (resultCode == RESULT_OK) {
				if (requestCode == BasicInfo.REQ_CODE_TWIT_LOGIN) {
					try {
						Twitter mTwit = BasicInfo.TwitInstance;

						AccessToken mAccessToken = mTwit.getOAuthAccessToken(BasicInfo.TwitRequestToken, resultIntent.getStringExtra("oauthVerifier"));

						BasicInfo.TwitLogin = true;
						BasicInfo.TWIT_KEY_TOKEN = mAccessToken.getToken();
						BasicInfo.TWIT_KEY_TOKEN_SECRET = mAccessToken.getTokenSecret();

						BasicInfo.TwitAccessToken = mAccessToken;

						BasicInfo.TwitScreenName = mTwit.getScreenName();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}

		protected void onPause() {
			super.onPause();

			saveProperties();
		}

		protected void onResume() {
			super.onResume();

			loadProperties();
		}

		private void saveProperties() {
			SharedPreferences pref = getSharedPreferences("TWIT", MODE_PRIVATE);
			SharedPreferences.Editor editor = pref.edit();

			editor.putBoolean("TwitLogin", BasicInfo.TwitLogin);
			editor.putString("TWIT_KEY_TOKEN", BasicInfo.TWIT_KEY_TOKEN);
			editor.putString("TWIT_KEY_TOKEN_SECRET", BasicInfo.TWIT_KEY_TOKEN_SECRET);

			editor.commit();
		}

		private void loadProperties() {
			SharedPreferences pref = getSharedPreferences("TWIT", MODE_PRIVATE);

			BasicInfo.TwitLogin = pref.getBoolean("TwitLogin", false);
			BasicInfo.TWIT_KEY_TOKEN = pref.getString("TWIT_KEY_TOKEN", "");
			BasicInfo.TWIT_KEY_TOKEN_SECRET = pref.getString("TWIT_KEY_TOKEN_SECRET", "");

		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
