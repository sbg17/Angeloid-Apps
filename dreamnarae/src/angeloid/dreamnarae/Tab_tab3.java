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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Tab_tab3 extends Fragment {
	Button bugreport;
	Button donate;
	TextView developer;
	TextView rinne;
	TextView sopiane;
	TextView primes;
	TextView lonelysheep;
	TextView rie;
	TextView iren;
	TextView ayana;
	TextView senless;
	TextView russia;
	TextView neon;
	TextView appname;
	TextView company;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_tab3, null);
		bugreport = (Button) root.findViewById(R.id.tab_tab3_bug_report);
		donate = (Button) root.findViewById(R.id.tab_tab3_donate);
		developer = (TextView) root.findViewById(R.id.tab_tab3_developer);
		rinne = (TextView) root.findViewById(R.id.tab_tab3_rinne);
		sopiane = (TextView) root.findViewById(R.id.tab_tab3_sopiane);
		primes = (TextView) root.findViewById(R.id.tab_tab3_primes);
	    lonelysheep = (TextView) root.findViewById(R.id.tab_tab3_lonelysheep);
	    rie = (TextView) root.findViewById(R.id.tab_tab3_rie);
	    iren = (TextView) root.findViewById(R.id.tab_tab3_iren);
	    ayana = (TextView) root.findViewById(R.id.tab_tab3_ayana);
	    senless = (TextView) root.findViewById(R.id.tab_tab3_senless);
	    russia = (TextView) root.findViewById(R.id.tab_tab3_russia);
	    neon = (TextView) root.findViewById(R.id.tab_tab3_neon);
	    appname = (TextView) root.findViewById(R.id.tab_tab3_appname);
	    company = (TextView) root.findViewById(R.id.tab_tab3_company);
		developer.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		rinne.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		sopiane.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		primes.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		lonelysheep.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		rie.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		iren.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		ayana.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		senless.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		russia.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		neon.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		appname.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		company.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		bugreport.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		donate.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		bugreport.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// 웹페이지 띄우기
				Uri uri = Uri.parse("https://db.blueweb.co.kr/formmail/formmail.html?dataname=sopesewind0");
				Intent it  = new Intent(Intent.ACTION_VIEW,uri);
				startActivity(it);

			}
		});
		donate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// 웹페이지 띄우기
				Uri uri = Uri.parse("http://me2.do/5oU1vu3");
				Intent it  = new Intent(Intent.ACTION_VIEW,uri);
				startActivity(it);

			}
		});

		return root;

	}
}