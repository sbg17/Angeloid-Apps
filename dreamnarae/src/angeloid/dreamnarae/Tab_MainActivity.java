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

import java.io.File;

import angeloid.dreamnarae.SwipeyTabs;
import angeloid.dreamnarae.SwipeyTabsAdapter;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab_MainActivity extends FragmentActivity {
	private SwipeyTabs mTabs;
	private ViewPager mViewPager;
	LinearLayout f; // 멤버필드
	String s = "a150ee65cb02f54";
	TextView tabtextview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_main);
		// typeface caching
		initializeTypefaces();
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTabs = (SwipeyTabs) findViewById(R.id.swipeytabs);
		tabtextview = (TextView) findViewById(R.id.tabtextview);
		f = (LinearLayout) findViewById(R.id.main_layout2);
		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(this,
				getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		mTabs.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(mTabs);
		mViewPager.setCurrentItem(0);
		// 루트 권한을 체크한다.
		// Root permission check!
		if (!(new File("/system/bin/su").exists())
				&& !(new File("/system/xbin/su").exists())
				&& !(new File("/system/bin/busybox").exists())) {
			// Print Root error message
			AlertDialog.Builder builder = new Builder(Tab_MainActivity.this);

			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface d, int w) {
							finish();
						}
					});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface d) {
					finish();
				}
			});

			builder.setMessage(String
					.format(getString(R.string.tab_tab2_noroot)));
			builder.create().show();

			return;
		}

		// Busybox를 체크한다.
		// Busybox check!
		if (!(new File("/system/bin/busybox").exists())
				&& !(new File("/system/xbin/busybox").exists())) {
			// Print busybox error message
			AlertDialog.Builder builder = new Builder(Tab_MainActivity.this);

			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface d, int w) {
							Uri uri = Uri
									.parse("market://search?q=pname:com.jrummy.busybox.installer");
							Intent it = new Intent(Intent.ACTION_VIEW, uri);
							startActivity(it);
							overridePendingTransition(R.anim.fade, R.anim.hold);
						}
					});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface d) {
					finish();
				}
			});

			builder.setMessage(R.string.tab_tab2_busybox);
			builder.create().show();

			return;
		}
		AlertDialog alert = new AlertDialog.Builder(this)
				.setIcon(R.drawable.alerterror)
				// alerterror라는 경고 아이콘을 띄운다
				.setTitle(R.string.tab_tab1_alerttitle)
				// alerttitle라는 제목을 선언
				.setMessage(R.string.tab_tab1_alertmessage)
				// 안의 내용
				.setPositiveButton(R.string.tab_tab1_alertok,
						new DialogInterface.OnClickListener() // OK를 클릭시(역시 선언)
						{
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss(); // 창 사라짐
							}
						})
				.setNegativeButton(R.string.tab_tab1_alertno,
						new DialogInterface.OnClickListener() // No를 클릭시
						{
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								finish(); // 어플 종료
							}
						}).show();
	}

	private class SwipeyTabsPagerAdapter extends FragmentPagerAdapter implements
			SwipeyTabsAdapter {

		private final Context mContext;

		// 탭 제목들
		// The tab titles.
		private String[] TITLES = { getString(R.string.main),
				getString(R.string.cl), getString(R.string.check),
				getString(R.string.spicatab), getString(R.string.miracletab),
				getString(R.string.savetab), getString(R.string.prevtab),
				getString(R.string.deletetab), getString(R.string.devinfo),
				getString(R.string.supporttab) };

		// 뷰페이져에 표시될 화면들.
		// The fragment which needs to show on the viewpager.
		private Fragment[] FRAGMENTS = { new Tab_tab1(), new Update_Main(),
				new CheckFuction(), new SPiCa_Main(), new Miracle_Main(),
				new Save_Main(), new Prev_Main(), new Delete_Main(),
				new Tab_tab3(), new Support_Main() };

		public SwipeyTabsPagerAdapter(Context context, FragmentManager fm) {
			super(fm);
			this.mContext = context;
		}

		@Override
		public Fragment getItem(int position) {
			return FRAGMENTS[position];
		}

		@Override
		public int getCount() {
			// 탭 갯수 리턴
			// Returns Tab count.
			return TITLES.length;
		}

		public TextView getTab(final int position, SwipeyTabs root) {
			// 탭 제목을 설정한다.
			// Set a tab title.
			TextView view = (TextView) LayoutInflater.from(mContext).inflate(
					R.layout.swipey_tab_indicator, root, false);
			view.setText(TITLES[position]);
			view.setTypeface(Tab_MainActivity.Fonts.THEOREM);
			tabtextview.setTypeface(Tab_MainActivity.Fonts.THEOREM);
			view.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// 탭을 누르면 해당 탭으로 화면을 넘긴다.
					// When user clicks tab title, We need to move to next page.
					mViewPager.setCurrentItem(position);
				}
			});

			return view;
		}
	}

	public static class Fonts {
		public static Typeface THEOREM;
	}

	private void initializeTypefaces() {
		Fonts.THEOREM = Typeface.createFromAsset(getAssets(),
				"fonts/NanumGothic.ttf");
	}
}
