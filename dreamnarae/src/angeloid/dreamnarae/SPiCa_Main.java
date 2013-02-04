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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SPiCa_Main extends Fragment {
	Button spicai;
	Button spicaii;
	TextView progresstext_spica;
	Handler mHandler_spica;
	ProgressDialog dialog_spica;
	ProgressDialog dialog_spica2;
	ImageView usercheck_spica;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.tab_tab2_spica_install, null);
		spicai = (Button) root.findViewById(R.id.tab_tab2_spica_reboot_ok);
		spicaii = (Button) root.findViewById(R.id.tab_tab2_spica_reboot_no);
		spicai.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		spicaii.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		progresstext_spica = (TextView) root
				.findViewById(R.id.tab_tab2_spica_running);
		usercheck_spica = (ImageView) root
				.findViewById(R.id.tab_tab2_spica_usercheck);
		progresstext_spica.setTypeface(Tab_MainActivity.Fonts.THEOREM);

		if (new File("/system/98banner_dreamnarae_spica").exists()) {
			usercheck_spica.setImageResource(R.drawable.apply);
			spicai.setEnabled(false);
			spicai.setFocusable(false);
			spicaii.setEnabled(false);
			spicaii.setFocusable(false);
			progresstext_spica.setText(R.string.tab_tab2_installed);

		} else {
			usercheck_spica.setImageResource(R.drawable.not_apply);

		}
		spicai.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		spicaii.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress2(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		return root;
	}

	// 리붓 함
	private void DialogProgress(boolean close) {
		if (!close) {
			dialog_spica = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			SPiCa_ZipTest.startUnzipping_spica(getActivity());
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress(true); // 창을 내린다.
					SPiCa_Helper.instantExec_spica(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder spica = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						spica.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						spica.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						spica.append("rm /system/etc/init.d/99provision;");
						spica.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						spica.append("rm /system/SPiCa;");
						spica.append("rm /system/DN;");
						spica.append("rm /system/miracle;");
						spica.append("rm /system/Save;");
						spica.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						spica.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						spica.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						spica.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						spica.append("rm /system/98banner_dreamnarae_spica;");
						spica.append("rm /system/98banner_dreamnarae_prev;");
						spica.append("rm /system/98banner_dreamnarae_save;");
						spica.append("rm /system/98banner_dreamnarae_miracle;");

						// Delete old Script Manager Files
						spica.append("rm /system/bin/EnableDN.sh;");
						spica.append("rm /data/DNEnable;");
						spica.append("rm /system/bin/RemoveDN.sh;");
						spica.append("rm /data/DNPrev.sh;");
						spica.append("rm /data/DNSPiCa.sh;");
						spica.append("rm /data/DNSave.sh;");
						spica.append("rm /data/DNMiracle.sh;");

						// Delete New Script Manager Files
						spica.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						spica.append("rm /system/etc/init.d/00prop;");
						spica.append("rm /system/etc/init.d/01io;");
						spica.append("rm /system/etc/init.d/02freq;");
						spica.append("rm /system/etc/init.d/03zipalign;");
						spica.append("rm /system/etc/init.d/01kswapd0;");
						spica.append("rm /system/etc/init.d/02io;");
						spica.append("rm /system/etc/init.d/03freq;");
						spica.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						spica.append("rm /system/etc/init.d/00set;");
						spica.append("rm /system/etc/init.d/01property;");
						spica.append("rm /system/etc/init.d/02vsls;");
						spica.append("rm /system/etc/init.d/03dch;");
						spica.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						spica.append("rm /system/etc/init.d/00sp;");
						spica.append("rm /system/etc/init.d/01v;");
						spica.append("rm /system/etc/init.d/02deep;");
						spica.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						spica.append("rm /system/etc/init.d/00proppv;");
						spica.append("rm /system/etc/init.d/01kswapd0pv;");
						spica.append("rm /system/etc/init.d/02iopv;");
						spica.append("rm /system/etc/init.d/03freqpv;");
						spica.append("rm /system/etc/init.d/04zippv;");
						spica.append("rm /system/etc/init.d/01iopv;");
						spica.append("rm /system/etc/init.d/02freqpv;");

						
						// Copy spica Files to /system/
						spica.append("cat /data/data/angeloid.dreamnarae/files/00prop > /system/etc/init.d/00prop;");
						spica.append("cat /data/data/angeloid.dreamnarae/files/01io > /system/etc/init.d/01io;");
						spica.append("cat /data/data/angeloid.dreamnarae/files/02freq > /system/etc/init.d/02freq;");
						spica.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_spica > /system/98banner_dreamnarae_spica;");
						spica.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						spica.append("rm /data/data/angeloid.dreamnarae/files/00prop;");
						spica.append("rm /data/data/angeloid.dreamnarae/files/01io;");
						spica.append("rm /data/data/angeloid.dreamnarae/files/02freq;");
						spica.append("rm /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_spica;");
						spica.append("rm /data/data/angeloid.dreamnarae/files/install-recovery.sh;");

						// Permission spica Files
						spica.append("chmod 755 /system/etc/init.d/00prop;");
						spica.append("chmod 755 /system/etc/init.d/01io;");
						spica.append("chmod 755 /system/etc/init.d/02freq;");
						spica.append("chmod 755 /system/98banner_dreamnarae_spica;");
						spica.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						spica.append("busybox mount -o ro,remount /system;");

						// Reboot Device(Only to Progress1)
						spica.append("reboot;");

						try {
							Thread.sleep(3000);
							SPiCa_Helper.instantExec_spica(getActivity(),
									spica.toString());

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 10000);
		} else {
			dialog_spica.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
		}
	}

	// 리붓 안함
	private void DialogProgress2(boolean close) {
		if (!close) {
			dialog_spica2 = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			SPiCa_ZipTest.startUnzipping_spica(getActivity());
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress2(true); // 창을 내린다.
					SPiCa_Helper2.instantExec_spica2(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder spica2 = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						spica2.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						spica2.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						spica2.append("rm /system/etc/init.d/99provision;");
						spica2.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						spica2.append("rm /system/SPiCa;");
						spica2.append("rm /system/DN;");
						spica2.append("rm /system/miracle;");
						spica2.append("rm /system/Save;");
						spica2.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						spica2.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						spica2.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						spica2.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						spica2.append("rm /system/98banner_dreamnarae_spica;");
						spica2.append("rm /system/98banner_dreamnarae_prev;");
						spica2.append("rm /system/98banner_dreamnarae_save;");
						spica2.append("rm /system/98banner_dreamnarae_miracle;");

						// Delete old Script Manager Files
						spica2.append("rm /system/bin/EnableDN.sh;");
						spica2.append("rm /data/DNEnable;");
						spica2.append("rm /system/bin/RemoveDN.sh;");
						spica2.append("rm /data/DNPrev.sh;");
						spica2.append("rm /data/DNSPiCa.sh;");
						spica2.append("rm /data/DNSave.sh;");
						spica2.append("rm /data/DNMiracle.sh;");

						// Delete New Script Manager Files
						spica2.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						spica2.append("rm /system/etc/init.d/00prop;");
						spica2.append("rm /system/etc/init.d/01io;");
						spica2.append("rm /system/etc/init.d/02freq;");
						spica2.append("rm /system/etc/init.d/03zipalign;");
						spica2.append("rm /system/etc/init.d/01kswapd0;");
						spica2.append("rm /system/etc/init.d/02io;");
						spica2.append("rm /system/etc/init.d/03freq;");
						spica2.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						spica2.append("rm /system/etc/init.d/00set;");
						spica2.append("rm /system/etc/init.d/01property;");
						spica2.append("rm /system/etc/init.d/02vsls;");
						spica2.append("rm /system/etc/init.d/03dch;");
						spica2.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						spica2.append("rm /system/etc/init.d/00sp;");
						spica2.append("rm /system/etc/init.d/01v;");
						spica2.append("rm /system/etc/init.d/02deep;");
						spica2.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						spica2.append("rm /system/etc/init.d/00proppv;");
						spica2.append("rm /system/etc/init.d/01kswapd0pv;");
						spica2.append("rm /system/etc/init.d/02iopv;");
						spica2.append("rm /system/etc/init.d/03freqpv;");
						spica2.append("rm /system/etc/init.d/04zippv;");
						spica2.append("rm /system/etc/init.d/01iopv;");
						spica2.append("rm /system/etc/init.d/02freqpv;");
						
						// Copy spica Files to /system/
						spica2.append("cat /data/data/angeloid.dreamnarae/files/00prop > /system/etc/init.d/00prop;");
						spica2.append("cat /data/data/angeloid.dreamnarae/files/01io > /system/etc/init.d/01io;");
						spica2.append("cat /data/data/angeloid.dreamnarae/files/02freq > /system/etc/init.d/02freq;");
						spica2.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_spica > /system/98banner_dreamnarae_spica;");
						spica2.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						spica2.append("rm /data/data/angeloid.dreamnarae/files/00prop;");
						spica2.append("rm /data/data/angeloid.dreamnarae/files/01io;");
						spica2.append("rm /data/data/angeloid.dreamnarae/files/02freq;");
						spica2.append("rm /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_spica;");
						spica2.append("rm /data/data/angeloid.dreamnarae/files/install-recovery.sh;");

						// Permission spica Files
						spica2.append("chmod 755 /system/etc/init.d/00prop;");
						spica2.append("chmod 755 /system/etc/init.d/01io;");
						spica2.append("chmod 755 /system/etc/init.d/02freq;");
						spica2.append("chmod 755 /system/98banner_dreamnarae_spica;");
						spica2.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						spica2.append("busybox mount -o ro,remount /system;");

						try {
							Thread.sleep(3000);
							SPiCa_Helper2.instantExec_spica2(getActivity(),
									spica2.toString());

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 25000);
		} else {
			dialog_spica2.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
			usercheck_spica.setImageResource(R.drawable.apply);
			spicai.setEnabled(false);
			spicai.setFocusable(false);
			spicaii.setEnabled(false);
			spicaii.setFocusable(false);
			progresstext_spica.setText(R.string.tab_tab2_installed);
		}
	}
}