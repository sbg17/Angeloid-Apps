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

public class Miracle_Main extends Fragment {
	Button savei;
	Button spicai;
	Button miraclei;
	Button miracleii;
	TextView progresstext_miracle;
	Handler mHandler_miracle;
	ProgressDialog dialog_miracle1;
	ProgressDialog dialog_miracle2;
	ImageView usercheck_miracle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.tab_tab2_miracle_install, null);
		miraclei = (Button) root.findViewById(R.id.tab_tab2_miracle_reboot_ok);
		miracleii = (Button) root.findViewById(R.id.tab_tab2_miracle_reboot_no);
		miraclei.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		miracleii.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		usercheck_miracle = (ImageView) root
				.findViewById(R.id.tab_tab2_miracle_usercheck);
		progresstext_miracle = (TextView) root
				.findViewById(R.id.tab_tab2_miracle_running);
		progresstext_miracle.setTypeface(Tab_MainActivity.Fonts.THEOREM);

		// 사용자 체크 확인

		if (new File("/system/98banner_dreamnarae_miracle").exists()) {
			usercheck_miracle.setImageResource(R.drawable.apply);
			miraclei.setEnabled(false);
			miraclei.setFocusable(false);
			miracleii.setEnabled(false);
			miracleii.setFocusable(false);
			progresstext_miracle.setText(R.string.tab_tab2_installed);
		} else {

			usercheck_miracle.setImageResource(R.drawable.not_apply);

		}
		// 리붓 함
		miraclei.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress1(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		// 리붓 안함
		miracleii.setOnClickListener(new View.OnClickListener() {

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

	private void DialogProgress1(boolean close) {
		if (!close) {
			dialog_miracle1 = ProgressDialog.show(getActivity(), "",
					"Loading..", true);
			Miracle_ZipTest.startUnzipping_miracle(getActivity());
			Handler mHandler1 = new Handler();
			mHandler1.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress1(true); // 창을 내린다.
					Miracle_Helper.instantExec_miracle(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder miracle = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						miracle.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						miracle.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						miracle.append("rm /system/etc/init.d/99provision;");
						miracle.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						miracle.append("rm /system/SPiCa;");
						miracle.append("rm /system/DN;");
						miracle.append("rm /system/Miracle;");
						miracle.append("rm /system/Save;");
						miracle.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						miracle.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						miracle.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						miracle.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						miracle.append("rm /system/98banner_dreamnarae_spica;");
						miracle.append("rm /system/98banner_dreamnarae_miracle;");
						miracle.append("rm /system/98banner_dreamnarae_save;");
						miracle.append("rm /system/98banner_dreamnarae_prev;");

						// Delete old Script Manager Files
						miracle.append("rm /system/bin/EnableDN.sh;");
						miracle.append("rm /data/DNEnable;");
						miracle.append("rm /system/bin/RemoveDN.sh;");
						miracle.append("rm /data/DNMiracle.sh;");
						miracle.append("rm /data/DNSPiCa.sh;");
						miracle.append("rm /data/DNSave.sh;");
						miracle.append("rm /data/DNPrev.sh;");

						// Delete New Script Manager Files
						miracle.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						miracle.append("rm /system/etc/init.d/00prop;");
						miracle.append("rm /system/etc/init.d/01io;");
						miracle.append("rm /system/etc/init.d/02freq;");
						miracle.append("rm /system/etc/init.d/03zipalign;");
						miracle.append("rm /system/etc/init.d/01kswapd0;");
						miracle.append("rm /system/etc/init.d/02io;");
						miracle.append("rm /system/etc/init.d/03freq;");
						miracle.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						miracle.append("rm /system/etc/init.d/00set;");
						miracle.append("rm /system/etc/init.d/01property;");
						miracle.append("rm /system/etc/init.d/02vsls;");
						miracle.append("rm /system/etc/init.d/03dch;");
						miracle.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						miracle.append("rm /system/etc/init.d/00sp;");
						miracle.append("rm /system/etc/init.d/01v;");
						miracle.append("rm /system/etc/init.d/02deep;");
						miracle.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						miracle.append("rm /system/etc/init.d/00proppv;");
						miracle.append("rm /system/etc/init.d/01kswapd0pv;");
						miracle.append("rm /system/etc/init.d/02iopv;");
						miracle.append("rm /system/etc/init.d/03freqpv;");
						miracle.append("rm /system/etc/init.d/04zippv;");
						miracle.append("rm /system/etc/init.d/01iopv;");
						miracle.append("rm /system/etc/init.d/02freqpv;");
						

						// Copy Miracle Files to /system/
						miracle.append("cat /data/data/angeloid.dreamnarae/files/00set > /system/etc/init.d/00set;");
						miracle.append("cat /data/data/angeloid.dreamnarae/files/01property >  /system/etc/init.d/01property;");
						miracle.append("cat /data/data/angeloid.dreamnarae/files/02vsls > /system/etc/init.d/02vsls;");
						miracle.append("cat /data/data/angeloid.dreamnarae/files/03dch > /system/etc/init.d/03dch;");
						miracle.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_miracle > /system/98banner_dreamnarae_miracle;");
						miracle.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						miracle.append("rm /data/data/angeloid.dreamnarae/files/00set;");
						miracle.append("rm /data/data/angeloid.dreamnarae/files/01property;");
						miracle.append("rm /data/data/angeloid.dreamnarae/files/02vsls;");
						miracle.append("rm /data/data/angeloid.dreamnarae/files/03dch;");
						miracle.append("rm /data/data/angeloid.dreamnarae/files/install-recovery.sh;");
						miracle.append("rm /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_miracle;");

						// Permission Miracle Files
						miracle.append("chmod 755 /system/etc/init.d/00set;");
						miracle.append("chmod 755 /system/etc/init.d/01property;");
						miracle.append("chmod 755 /system/etc/init.d/02vsls;");
						miracle.append("chmod 755 /system/etc/init.d/03dch;");
						miracle.append("chmod 755 /system/98banner_dreamnarae_miracle;");
						miracle.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						miracle.append("busybox mount -o ro,remount /system;");

						// Reboot Device(Only to Progress1)
						miracle.append("reboot;");

						try {
							Thread.sleep(3000);
							Miracle_Helper.instantExec_miracle(getActivity(),
									miracle.toString());

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
			dialog_miracle1.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
		}

	}

	private void DialogProgress2(boolean close) {
		if (!close) {
			dialog_miracle2 = ProgressDialog.show(getActivity(), "",
					"Loading..", true);
			Miracle_ZipTest.startUnzipping_miracle(getActivity());
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress2(true); // 창을 내린다.
					Miracle_Helper2.instantExec_miracle2(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder miracle2 = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						miracle2.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						miracle2.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						miracle2.append("rm /system/etc/init.d/99provision;");
						miracle2.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						miracle2.append("rm /system/SPiCa;");
						miracle2.append("rm /system/DN;");
						miracle2.append("rm /system/Miracle;");
						miracle2.append("rm /system/Save;");
						miracle2.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						miracle2.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						miracle2.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						miracle2.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						miracle2.append("rm /system/98banner_dreamnarae_spica;");
						miracle2.append("rm /system/98banner_dreamnarae_miracle;");
						miracle2.append("rm /system/98banner_dreamnarae_save;");
						miracle2.append("rm /system/98banner_dreamnarae_prev;");

						// Delete old Script Manager Files
						miracle2.append("rm /system/bin/EnableDN.sh;");
						miracle2.append("rm /data/DNEnable;");
						miracle2.append("rm /system/bin/RemoveDN.sh;");
						miracle2.append("rm /data/DNMiracle.sh;");
						miracle2.append("rm /data/DNSPiCa.sh;");
						miracle2.append("rm /data/DNSave.sh;");
						miracle2.append("rm /data/DNPrev.sh;");

						// Delete New Script Manager Files
						miracle2.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						miracle2.append("rm /system/etc/init.d/00prop;");
						miracle2.append("rm /system/etc/init.d/01io;");
						miracle2.append("rm /system/etc/init.d/02freq;");
						miracle2.append("rm /system/etc/init.d/03zipalign;");
						miracle2.append("rm /system/etc/init.d/01kswapd0;");
						miracle2.append("rm /system/etc/init.d/02io;");
						miracle2.append("rm /system/etc/init.d/03freq;");
						miracle2.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						miracle2.append("rm /system/etc/init.d/00set;");
						miracle2.append("rm /system/etc/init.d/01property;");
						miracle2.append("rm /system/etc/init.d/02vsls;");
						miracle2.append("rm /system/etc/init.d/03dch;");
						miracle2.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						miracle2.append("rm /system/etc/init.d/00sp;");
						miracle2.append("rm /system/etc/init.d/01v;");
						miracle2.append("rm /system/etc/init.d/02deep;");
						miracle2.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						miracle2.append("rm /system/etc/init.d/00proppv;");
						miracle2.append("rm /system/etc/init.d/01kswapd0pv;");
						miracle2.append("rm /system/etc/init.d/02iopv;");
						miracle2.append("rm /system/etc/init.d/03freqpv;");
						miracle2.append("rm /system/etc/init.d/04zippv;");
						miracle2.append("rm /system/etc/init.d/01iopv;");
						miracle2.append("rm /system/etc/init.d/02freqpv;");
						

						// Copy Miracle Files to /system/
						miracle2.append("cat /data/data/angeloid.dreamnarae/files/00set > /system/etc/init.d/00set;");
						miracle2.append("cat /data/data/angeloid.dreamnarae/files/01property >  /system/etc/init.d/01property;");
						miracle2.append("cat /data/data/angeloid.dreamnarae/files/02vsls > /system/etc/init.d/02vsls;");
						miracle2.append("cat /data/data/angeloid.dreamnarae/files/03dch > /system/etc/init.d/03dch;");
						miracle2.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_miracle > /system/98banner_dreamnarae_miracle;");
						miracle2.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						miracle2.append("rm /data/data/angeloid.dreamnarae/files/00set;");
						miracle2.append("rm /data/data/angeloid.dreamnarae/files/01property;");
						miracle2.append("rm /data/data/angeloid.dreamnarae/files/02vsls;");
						miracle2.append("rm /data/data/angeloid.dreamnarae/files/03dch;");
						miracle2.append("rm /data/data/angeloid.dreamnarae/files/install-recovery.sh;");
						miracle2.append("rm /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_miracle;");

						// Permission Miracle Files
						miracle2.append("chmod 755 /system/etc/init.d/00set;");
						miracle2.append("chmod 755 /system/etc/init.d/01property;");
						miracle2.append("chmod 755 /system/etc/init.d/02vsls;");
						miracle2.append("chmod 755 /system/etc/init.d/03dch;");
						miracle2.append("chmod 755 /system/98banner_dreamnarae_miracle;");
						miracle2.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						miracle2.append("busybox mount -o ro,remount /system;");
						try {
							Thread.sleep(3000);
							Miracle_Helper2.instantExec_miracle2(getActivity(),
									miracle2.toString());

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
			dialog_miracle2.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
			usercheck_miracle.setImageResource(R.drawable.apply);
			miraclei.setEnabled(false);
			miraclei.setFocusable(false);
			miracleii.setEnabled(false);
			miracleii.setFocusable(false);
			progresstext_miracle.setText(R.string.tab_tab2_installed);
		}

	}
}