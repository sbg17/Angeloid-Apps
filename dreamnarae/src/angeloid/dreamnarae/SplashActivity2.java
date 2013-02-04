/*
* DreamNarae (Root) Open Source
* Colorful Harmony Team- Angeloid Team, inc
* Copyright 2009-2013 Angeloid Team, inc Licensed under the Apache License
* Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
*
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed 
*
* Under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*
* See the License for the specific language governing permissions and limitations under the License.
*/
package angeloid.dreamnarae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class SplashActivity2 extends Activity {
	int splashSceneNumber;
	LinearLayout splashLayout;
	Handler mHandler;
	boolean isClick;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash2);
		// TODO Auto-generated method stub
		// xml 소스 참조
		splashLayout = (LinearLayout) findViewById(R.id.llsplash2);
		// 처음화면 0
		splashSceneNumber = 0;
		// 클릭 이벤트가 있었는지 확인
		isClick = true;
		// 핸들러 체크
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (splashSceneNumber) {
				case 0:
					splashSceneNumber = 1;
					mHandler.sendEmptyMessage(splashSceneNumber);
					break;

				case 1:
					splashSceneNumber = 2;
					mHandler.sendEmptyMessageDelayed(splashSceneNumber, 2000);
					break;

				case 2:
				    // 액티비티 전환 준비
				    // Prepare for switching activity
				    Intent i = new Intent(SplashActivity2.this, Tab_MainActivity.class);

				    // 액티비티 시작!
				    // Start main activity!
				    startActivity(i);
				    overridePendingTransition(R.anim.fade, R.anim.hold);
					break;

				case 3:
					// 딜레이이벤트 클리기 없을경우 바로 0 이벤트로 보낸다..
					if (isClick && splashSceneNumber == 0) {
						splashSceneNumber = 0;
						mHandler.sendEmptyMessage(splashSceneNumber);
					}
					break;
				}
			}

		};
		// 1500ms간 보여준다
		mHandler.sendEmptyMessageDelayed(3, 1000);
	}

	// 클릭했을 때의 핸들러
	public void hn_splashOnclick(View v) {

		switch (splashSceneNumber) {
		case 0:
			splashSceneNumber = 0;

			isClick = false;
			mHandler.sendEmptyMessage(splashSceneNumber);
			break;

		case 1:
			splashSceneNumber = 2;
			mHandler.sendEmptyMessage(splashSceneNumber);
			break;
		}
	}
}
