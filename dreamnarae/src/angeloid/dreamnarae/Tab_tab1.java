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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab_tab1 extends Fragment {

	TextView welcome1, welcome2;
	ImageView welcome_image;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_tab1, null);

		welcome_image = (ImageView) root
				.findViewById(R.id.tab_tab1_welcome_image);
		welcome1 = (TextView) root.findViewById(R.id.tab_tab1_welcome_text);
		welcome2 = (TextView) root.findViewById(R.id.tab_tab1_textview2);
		welcome1.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		welcome2.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		return root;
	}

}
