package angeloid.dreamnarae;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CheckFuction extends Fragment {
	Button busybox;
	Button supersu;
	TextView su_text;
	TextView prev_text;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_user_check,
				null);
		su_text = (TextView) root.findViewById(R.id.tab_usercheck_su);
		prev_text = (TextView) root
				.findViewById(R.id.tab_usercheck_prevversion);
		busybox = (Button) root.findViewById(R.id.tab_tab2_requisite_busy);
		supersu = (Button) root.findViewById(R.id.tab_tab2_requisite_ssu);
		su_text.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		prev_text.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		StartUserCheck();
		busybox.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		supersu.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		busybox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri
						.parse("market://search?q=pname:com.jrummy.busybox.installer");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});
		supersu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri
						.parse("market://search?q=pname:eu.chainfire.supersu");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});
		return root;
	}

	void StartUserCheck() {
		sucheck();
		prevcheck();
	}

	void sucheck() {
		if (!(new File("/system/bin/su").exists()))
			su_text.setText(R.string.tab_usercheck_su);
		else
			su_text.setText(R.string.tab_usercheck_suneed);
	}

	void prevcheck() {
		if (!(new File("/system/SPiCa").exists()))
			prev_text.setText(R.string.tab_usercheck_oldplagdelete);
		else
			prev_text.setText(R.string.tab_usercheck_prevversion);
		if (!(new File("/system/Miracle").exists()))
			prev_text.setText(R.string.tab_usercheck_oldplagdelete);
		else
			prev_text.setText(R.string.tab_usercheck_prevversion);
		if (!(new File("/system/Save").exists()))
			prev_text.setText(R.string.tab_usercheck_oldplagdelete);
		else
			prev_text.setText(R.string.tab_usercheck_prevversion);
		if (!(new File("/system/DN").exists()))
			prev_text.setText(R.string.tab_usercheck_oldplagdelete);
		else
			prev_text.setText(R.string.tab_usercheck_prevversion);
	}

}
