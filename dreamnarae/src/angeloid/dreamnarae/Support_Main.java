package angeloid.dreamnarae;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Support_Main extends Fragment {
	TextView tweakpriority;
	TextView tweakpriority_dnspica;
	TextView tweakpriority_dnmiracle;
	TextView tweakpriority_dnsave;
	TextView tweakpriority_dnprev;
	TextView howdoiapply;
	TextView howdoiapply_first;
	TextView howdoiapply_second;
	TextView howdoiapply_third;
	TextView howdoiapply_easy;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_support,
				null);
		tweakpriority = (TextView) root
				.findViewById(R.id.tab_support_tweakpriority);
		tweakpriority_dnspica = (TextView) root
				.findViewById(R.id.tab_support_tweakpriority_dnspica);
		tweakpriority_dnmiracle = (TextView) root
				.findViewById(R.id.tab_support_tweakpriority_dnmiracle);
		tweakpriority_dnsave = (TextView) root
				.findViewById(R.id.tab_support_tweakpriority_dnsave);
		tweakpriority_dnprev = (TextView) root
				.findViewById(R.id.tab_support_tweakpriority_dnprev);
		howdoiapply = (TextView) root
				.findViewById(R.id.tab_support_howdioapply);
		howdoiapply_first = (TextView) root
				.findViewById(R.id.tab_support_howdioapply_first);
		howdoiapply_second = (TextView) root
				.findViewById(R.id.tab_support_howdioapply_second);
		howdoiapply_third = (TextView) root
				.findViewById(R.id.tab_support_howdioapply_third);
		howdoiapply_easy = (TextView) root
				.findViewById(R.id.tab_support_howdioapply_easy);
		tweakpriority.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		tweakpriority_dnspica.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		tweakpriority_dnmiracle.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		tweakpriority_dnsave.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		tweakpriority_dnprev.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		howdoiapply.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		howdoiapply_first.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		howdoiapply_second.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		howdoiapply_third.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		howdoiapply_easy.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		return root;
	}
}
