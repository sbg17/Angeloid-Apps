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
package angeloid.sopiane.vegaphoneinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SwipeyTabFragment extends Fragment {
    
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
    
        public static Fragment newInstance(String title) {
                SwipeyTabFragment f = new SwipeyTabFragment();
                Bundle args = new Bundle();
                args.putString("title", title);
                f.setArguments(args);
                return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
                ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_swipeytab, null);
                final String title = getArguments().getString("title");
                ((TextView) root.findViewById(R.id.text)).setText(title);
                return root;
        }

}