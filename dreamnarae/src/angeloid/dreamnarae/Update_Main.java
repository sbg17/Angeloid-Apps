package angeloid.dreamnarae;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Update_Main extends Fragment {
	TextView appversion;
	TextView builddate;
	TextView spicaversion;
	TextView miracleversion;
	TextView saveversion;
	TextView prevversion;
	TextView updateapp1;
	TextView updateapp2;
	TextView updateapp3;
	TextView updateapp4;
	TextView updateapp5;
	TextView updateapp6;
	TextView updateapp7;
	TextView updateapp8;
	TextView thankyou;


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_change_log,
				null);
		appversion = (TextView) root.findViewById(R.id.tab_change_appversion);
		builddate = (TextView) root.findViewById(R.id.tab_change_builddate);
		spicaversion = (TextView) root.findViewById(R.id.tab_change_spicaversion);
		miracleversion = (TextView) root.findViewById(R.id.tab_change_miracleversion);
		saveversion = (TextView) root.findViewById(R.id.tab_change_saveversion);
		prevversion = (TextView) root.findViewById(R.id.tab_change_prevversion);
		updateapp1 = (TextView) root.findViewById(R.id.tab_change_updateapp1);
		updateapp2 = (TextView) root.findViewById(R.id.tab_change_updateapp2);
		updateapp3 = (TextView) root.findViewById(R.id.tab_change_updateapp3);
		updateapp4 = (TextView) root.findViewById(R.id.tab_change_updateapp4);
		updateapp5 = (TextView) root.findViewById(R.id.tab_change_updateapp5);
		updateapp6 = (TextView) root.findViewById(R.id.tab_change_updateapp6);
		updateapp7 = (TextView) root.findViewById(R.id.tab_change_updateapp7);
	//	updateapp8 = (TextView) root.findViewById(R.id.tab_change_updateapp8);
		thankyou = (TextView) root.findViewById(R.id.tab_change_thankyou);
		appversion.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		builddate.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		spicaversion.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		miracleversion.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		saveversion.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		prevversion.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp1.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp2.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp3.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp4.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp5.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp6.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		updateapp7.setTypeface(Tab_MainActivity.Fonts.THEOREM);
	//	updateapp8.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		thankyou.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		return root;
	}
}