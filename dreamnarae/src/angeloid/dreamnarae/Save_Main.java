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

public class Save_Main extends Fragment {
	Button spicai;
	Button miraclei;
	Button savei;
	Button saveii;
	TextView progresstext_save;
	Handler mHandler_save;
	ProgressDialog dialog_save;
	ProgressDialog dialog_save2;
	ImageView usercheck_save;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.tab_tab2_save_install, null);
		savei = (Button) root.findViewById(R.id.tab_tab2_save_reboot_ok);
		saveii = (Button) root.findViewById(R.id.tab_tab2_save_reboot_no);
		savei.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		saveii.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		progresstext_save = (TextView) root
				.findViewById(R.id.tab_tab2_save_running);
		usercheck_save = (ImageView) root
				.findViewById(R.id.tab_tab2_save_usercheck);
		progresstext_save.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		// 사용자 체크 확인
		if (new File("/system/98banner_dreamnarae_save").exists()) {

			usercheck_save.setImageResource(R.drawable.apply);
			savei.setEnabled(false);
			savei.setFocusable(false);
			saveii.setEnabled(false);
			saveii.setFocusable(false);
			progresstext_save.setText(R.string.tab_tab2_installed);

		} else {
			usercheck_save.setImageResource(R.drawable.not_apply);

		}
		savei.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		saveii.setOnClickListener(new View.OnClickListener() {

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

	// 리붓함
	private void DialogProgress(boolean close) {
		if (!close) {
			dialog_save = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			Save_ZipTest.startUnzipping_save(getActivity());
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress(true); // 창을 내린다.
					Save_Helper.instantExec_save(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder save = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						save.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						save.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						save.append("rm /system/etc/init.d/99provision;");
						save.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						save.append("rm /system/SPiCa;");
						save.append("rm /system/DN;");
						save.append("rm /system/Miracle;");
						save.append("rm /system/Save;");
						save.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						save.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						save.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						save.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						save.append("rm /system/98banner_dreamnarae_spica;");
						save.append("rm /system/98banner_dreamnarae_save;");
						save.append("rm /system/98banner_dreamnarae_miracle;");
						save.append("rm /system/98banner_dreamnarae_prev;");

						// Delete old Script Manager Files
						save.append("rm /system/bin/EnableDN.sh;");
						save.append("rm /data/DNEnable;");
						save.append("rm /system/bin/RemoveDN.sh;");
						save.append("rm /data/DNPrev.sh;");
						save.append("rm /data/DNSPiCa.sh;");
						save.append("rm /data/DNSave.sh;");
						save.append("rm /data/DNMiracle.sh;");

						// Delete New Script Manager Files
						save.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						save.append("rm /system/etc/init.d/00prop;");
						save.append("rm /system/etc/init.d/01io;");
						save.append("rm /system/etc/init.d/02freq;");
						save.append("rm /system/etc/init.d/03zipalign;");
						save.append("rm /system/etc/init.d/01kswapd0;");
						save.append("rm /system/etc/init.d/02io;");
						save.append("rm /system/etc/init.d/03freq;");
						save.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						save.append("rm /system/etc/init.d/00set;");
						save.append("rm /system/etc/init.d/01property;");
						save.append("rm /system/etc/init.d/02vsls;");
						save.append("rm /system/etc/init.d/03dch;");
						save.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						save.append("rm /system/etc/init.d/00sp;");
						save.append("rm /system/etc/init.d/01v;");
						save.append("rm /system/etc/init.d/02deep;");
						save.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						save.append("rm /system/etc/init.d/00proppv;");
						save.append("rm /system/etc/init.d/01kswapd0pv;");
						save.append("rm /system/etc/init.d/02iopv;");
						save.append("rm /system/etc/init.d/03freqpv;");
						save.append("rm /system/etc/init.d/04zippv;");
						save.append("rm /system/etc/init.d/01iopv;");
						save.append("rm /system/etc/init.d/02freqpv;");
						
						// Create Bakcup "install-recovery.sh"
						save.append("mkdir /sdcard/dreamnarae/backup;");
						save.append("cat /system/etc/install-recovery.sh > /sdcard/dreamnarae/backup/install-recovery.sh;");

						// Copy save Files to /system/
						save.append("cat /data/data/angeloid.dreamnarae/files/00sp > /system/etc/init.d/00sp;");
						save.append("cat /data/data/angeloid.dreamnarae/files/01v > /system/etc/init.d/01v;");
						save.append("cat /data/data/angeloid.dreamnarae/files/02deep > /system/etc/init.d/02deep;");
						save.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_save > /system/98banner_dreamnarae_save;");
						save.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						save.append("rm /data/data/angeloid.dreamnarae.save/files/00sp;");
						save.append("rm /data/data/angeloid.dreamnarae.save/files/01v;");
						save.append("rm /data/data/angeloid.dreamnarae.save/files/02deep;");
						save.append("rm /data/data/angeloid.dreamnarae.save/files/98banner_dreamnarae_save;");
						save.append("rm /data/data/angeloid.dreamnarae.save/files/install-recovery.sh;");

						// Permission save Files
						save.append("chmod 755 /system/etc/init.d/00sp;");
						save.append("chmod 755 /system/etc/init.d/01v;");
						save.append("chmod 755 /system/etc/init.d/02deep;");
						save.append("chmod 755 /system/98banner_dreamnarae_save;");
						save.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						save.append("busybox mount -o ro,remount /system;");

						// Reboot Device(Only to Progress1)
						save.append("reboot;");

						try {
							Thread.sleep(3000);
							Save_Helper.instantExec_save(getActivity(),
									save.toString());

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
			dialog_save.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
		}
	}

	// 리붓안함
	private void DialogProgress2(boolean close) {
		if (!close) {
			dialog_save2 = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			Save_ZipTest.startUnzipping_save(getActivity());
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress2(true); // 창을 내린다.
					Save_Helper2.instantExec_save2(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder save2 = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						save2.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						save2.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						save2.append("rm /system/etc/init.d/99provision;");
						save2.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						save2.append("rm /system/SPiCa;");
						save2.append("rm /system/DN;");
						save2.append("rm /system/Miracle;");
						save2.append("rm /system/Save;");
						save2.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						save2.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						save2.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						save2.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						save2.append("rm /system/98banner_dreamnarae_spica;");
						save2.append("rm /system/98banner_dreamnarae_save;");
						save2.append("rm /system/98banner_dreamnarae_miracle;");
						save2.append("rm /system/98banner_dreamnarae_prev;");

						// Delete old Script Manager Files
						save2.append("rm /system/bin/EnableDN.sh;");
						save2.append("rm /data/DNEnable;");
						save2.append("rm /system/bin/RemoveDN.sh;");
						save2.append("rm /data/DNPrev.sh;");
						save2.append("rm /data/DNSPiCa.sh;");
						save2.append("rm /data/DNSave.sh;");
						save2.append("rm /data/DNMiracle.sh;");

						// Delete New Script Manager Files
						save2.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						save2.append("rm /system/etc/init.d/00prop;");
						save2.append("rm /system/etc/init.d/01io;");
						save2.append("rm /system/etc/init.d/02freq;");
						save2.append("rm /system/etc/init.d/03zipalign;");
						save2.append("rm /system/etc/init.d/01kswapd0;");
						save2.append("rm /system/etc/init.d/02io;");
						save2.append("rm /system/etc/init.d/03freq;");
						save2.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						save2.append("rm /system/etc/init.d/00set;");
						save2.append("rm /system/etc/init.d/01property;");
						save2.append("rm /system/etc/init.d/02vsls;");
						save2.append("rm /system/etc/init.d/03dch;");
						save2.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						save2.append("rm /system/etc/init.d/00sp;");
						save2.append("rm /system/etc/init.d/01v;");
						save2.append("rm /system/etc/init.d/02deep;");
						save2.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						save2.append("rm /system/etc/init.d/00proppv;");
						save2.append("rm /system/etc/init.d/01kswapd0pv;");
						save2.append("rm /system/etc/init.d/02iopv;");
						save2.append("rm /system/etc/init.d/03freqpv;");
						save2.append("rm /system/etc/init.d/04zippv;");
						save2.append("rm /system/etc/init.d/01iopv;");
						save2.append("rm /system/etc/init.d/02freqpv;");
						
						// Create Bakcup "install-recovery.sh"
						save2.append("mkdir /sdcard/dreamnarae/backup;");
						save2.append("cat /system/etc/install-recovery.sh > /sdcard/dreamnarae/backup/install-recovery.sh;");

						// Copy save Files to /system/
						save2.append("cat /data/data/angeloid.dreamnarae/files/00sp > /system/etc/init.d/00sp;");
						save2.append("cat /data/data/angeloid.dreamnarae/files/01v > /system/etc/init.d/01v;");
						save2.append("cat /data/data/angeloid.dreamnarae/files/02deep > /system/etc/init.d/02deep;");
						save2.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_save > /system/98banner_dreamnarae_save;");
						save2.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						save2.append("rm /data/data/angeloid.dreamnarae.save/files/00sp;");
						save2.append("rm /data/data/angeloid.dreamnarae.save/files/01v;");
						save2.append("rm /data/data/angeloid.dreamnarae.save/files/02deep;");
						save2.append("rm /data/data/angeloid.dreamnarae.save/files/98banner_dreamnarae_save;");
						save2.append("rm /data/data/angeloid.dreamnarae.save/files/install-recovery.sh;");

						// Permission save Files
						save2.append("chmod 755 /system/etc/init.d/00sp;");
						save2.append("chmod 755 /system/etc/init.d/01v;");
						save2.append("chmod 755 /system/etc/init.d/02deep;");
						save2.append("chmod 755 /system/98banner_dreamnarae_save;");
						save2.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						save2.append("busybox mount -o ro,remount /system;");

						try {
							Thread.sleep(3000);
							Save_Helper2.instantExec_save2(getActivity(),
									save2.toString());

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
			dialog_save2.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
			usercheck_save.setImageResource(R.drawable.apply);
			savei.setEnabled(false);
			savei.setFocusable(false);
			saveii.setEnabled(false);
			saveii.setFocusable(false);
			progresstext_save.setText(R.string.tab_tab2_installed);
		}
	}
}
