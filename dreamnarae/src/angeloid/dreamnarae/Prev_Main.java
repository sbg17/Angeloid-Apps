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

public class Prev_Main extends Fragment {
	Button savei;
	Button spicai;
	Button previ;
	Button previi;
	TextView progresstext_prev;
	Handler mHandler_prev;
	ProgressDialog dialog_prev1;
	ProgressDialog dialog_prev2;
	ImageView usercheck_prev;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.tab_tab2_prev_install, null);
		previ = (Button) root.findViewById(R.id.tab_tab2_prev_reboot_ok);
		previi = (Button) root.findViewById(R.id.tab_tab2_prev_reboot_no);
		previ.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		previi.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		usercheck_prev = (ImageView) root
				.findViewById(R.id.tab_tab2_prev_usercheck);
		progresstext_prev = (TextView) root
				.findViewById(R.id.tab_tab2_prev_running);
		progresstext_prev.setTypeface(Tab_MainActivity.Fonts.THEOREM);

		// ������ üũ Ȯ��

		if (new File("/system/98banner_dreamnarae_prev").exists()) {
			usercheck_prev.setImageResource(R.drawable.apply);
			previ.setEnabled(false);
			previ.setFocusable(false);
			previi.setEnabled(false);
			previi.setFocusable(false);
			progresstext_prev.setText(R.string.tab_tab2_installed);

		} else {
			usercheck_prev.setImageResource(R.drawable.not_apply);

		}
		// ���� ��
		previ.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress1(false);
					}
				}, 10); // 0.01 ������ �༭ ��ư ���� �ð��� ������
			}
		});
		// ���� ����
		previi.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress2(false);
					}
				}, 10); // 0.01 ������ �༭ ��ư ���� �ð��� ������
			}
		});
		return root;
	}

	private void DialogProgress1(boolean close) {
		if (!close) {
			dialog_prev1 = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			Prev_ZipTest.startUnzipping_prev(getActivity());
			Handler mHandler1 = new Handler();
			mHandler1.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress1(true); // â�� ������.
					Prev_Helper.instantExec_prev(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder prev = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						prev.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						prev.append("mkdir /system/etc/init.d;");

						// Avoid used in conjunction with other scripts
						prev.append("rm /system/etc/init.d/99provision;");
						prev.append("rm /system/etc/init.d/S99SoulTools;");
						
						// Delete Flag Files
						prev.append("rm /system/SPiCa;");
						prev.append("rm /system/DN;");
						prev.append("rm /system/Miracle;");
						prev.append("rm /system/Save;");
						prev.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						prev.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						prev.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						prev.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						prev.append("rm /system/98banner_dreamnarae_spica;");
						prev.append("rm /system/98banner_dreamnarae_prev;");
						prev.append("rm /system/98banner_dreamnarae_save;");
						prev.append("rm /system/98banner_dreamnarae_miracle;");

						// Delete old Script Manager Files
						prev.append("rm /system/bin/EnableDN.sh;");
						prev.append("rm /data/DNEnable;");
						prev.append("rm /system/bin/RemoveDN.sh;");
						prev.append("rm /data/DNPrev.sh;");
						prev.append("rm /data/DNSPiCa.sh;");
						prev.append("rm /data/DNSave.sh;");
						prev.append("rm /data/DNMiracle.sh;");

						// Delete New Script Manager Files
						prev.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						prev.append("rm /system/etc/init.d/00prop;");
						prev.append("rm /system/etc/init.d/01io;");
						prev.append("rm /system/etc/init.d/02freq;");
						prev.append("rm /system/etc/init.d/03zipalign;");
						prev.append("rm /system/etc/init.d/01kswapd0;");
						prev.append("rm /system/etc/init.d/02io;");
						prev.append("rm /system/etc/init.d/03freq;");
						prev.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						prev.append("rm /system/etc/init.d/00set;");
						prev.append("rm /system/etc/init.d/01property;");
						prev.append("rm /system/etc/init.d/02vsls;");
						prev.append("rm /system/etc/init.d/03dch;");
						prev.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						prev.append("rm /system/etc/init.d/00sp;");
						prev.append("rm /system/etc/init.d/01v;");
						prev.append("rm /system/etc/init.d/02deep;");
						prev.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						prev.append("rm /system/etc/init.d/00proppv;");
						prev.append("rm /system/etc/init.d/01kswapd0pv;");
						prev.append("rm /system/etc/init.d/02iopv;");
						prev.append("rm /system/etc/init.d/03freqpv;");
						prev.append("rm /system/etc/init.d/04zippv;");
						prev.append("rm /system/etc/init.d/01iopv;");
						prev.append("rm /system/etc/init.d/02freqpv;");
						
						// Create Bakcup "install-recovery.sh"
						prev.append("mkdir /sdcard/dreamnarae/backup;");
						prev.append("cat /system/etc/install-recovery.sh > /sdcard/dreamnarae/backup/install-recovery.sh;");

						// Copy Prev Files to /system/
						prev.append("cat /data/data/angeloid.dreamnarae/files/00proppv > /system/etc/init.d/00proppv;");
						prev.append("cat /data/data/angeloid.dreamnarae/files/01iopv > /system/etc/init.d/01iopv;");
						prev.append("cat /data/data/angeloid.dreamnarae/files/02freqpv > /system/etc/init.d/02freqpv;");
						prev.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_prev > /system/98banner_dreamnarae_prev;");
						prev.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						prev.append("rm /data/data/angeloid.dreamnarae/files/00proppv;");
						prev.append("rm /data/data/angeloid.dreamnarae/files/01iopv;");
						prev.append("rm /data/data/angeloid.dreamnarae/files/02freqpv;");
						prev.append("rm /data/data/angeloid.dreamnarae/files/install-recovery.sh;");
						prev.append("rm /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_prev;");

						// Permission prev Files
						prev.append("chmod 755 /system/etc/init.d/00proppv;");
						prev.append("chmod 755 /system/etc/init.d/01iopv;");
						prev.append("chmod 755 /system/etc/init.d/02freqpv;");
						prev.append("chmod 755 /system/98banner_dreamnarae_prev;");
						prev.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						prev.append("busybox mount -o ro,remount /system;");

						// Reboot Device(Only to Progress1)
						prev.append("reboot;");

						try {
							Thread.sleep(3000);
							Prev_Helper.instantExec_prev(getActivity(),
									prev.toString());

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
			dialog_prev1.dismiss(); // DialogProgress(true)�̸� ����,false�̸�
									// ����
		}

	}

	private void DialogProgress2(boolean close) {
		if (!close) {
			dialog_prev2 = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			Prev_ZipTest.startUnzipping_prev(getActivity());
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress2(true); // â�� ������.
					Prev_Helper2.instantExec_prev2(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder prev2 = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						prev2.append("mount -o rw,remount /system;");
						
						// Create Folder system/etc/init.d 
						prev2.append("mkdir /system/etc/init.d;");
						
						// Avoid used in conjunction with other scripts
						prev2.append("rm /system/etc/init.d/99provision;");
						prev2.append("rm /system/etc/init.d/S99SoulTools;");

						// Delete Flag Files
						prev2.append("rm /system/SPiCa;");
						prev2.append("rm /system/DN;");
						prev2.append("rm /system/Miracle;");
						prev2.append("rm /system/Save;");
						prev2.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						prev2.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						prev2.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						prev2.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						prev2.append("rm /system/98banner_dreamnarae_spica;");
						prev2.append("rm /system/98banner_dreamnarae_prev;");
						prev2.append("rm /system/98banner_dreamnarae_save;");
						prev2.append("rm /system/98banner_dreamnarae_miracle;");

						// Delete old Script Manager Files
						prev2.append("rm /system/bin/EnableDN.sh;");
						prev2.append("rm /data/DNEnable;");
						prev2.append("rm /system/bin/RemoveDN.sh;");
						prev2.append("rm /data/DNPrev.sh;");
						prev2.append("rm /data/DNSPiCa.sh;");
						prev2.append("rm /data/DNSave.sh;");
						prev2.append("rm /data/DNMiracle.sh;");

						// Delete New Script Manager Files
						prev2.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						prev2.append("rm /system/etc/init.d/00prop;");
						prev2.append("rm /system/etc/init.d/01io;");
						prev2.append("rm /system/etc/init.d/02freq;");
						prev2.append("rm /system/etc/init.d/03zipalign;");
						prev2.append("rm /system/etc/init.d/01kswapd0;");
						prev2.append("rm /system/etc/init.d/02io;");
						prev2.append("rm /system/etc/init.d/03freq;");
						prev2.append("rm /system/etc/init.d/04zipalign;");

						//Delete DreamNarae Miracle
						prev2.append("rm /system/etc/init.d/00set;");
						prev2.append("rm /system/etc/init.d/01property;");
						prev2.append("rm /system/etc/init.d/02vsls;");
						prev2.append("rm /system/etc/init.d/03dch;");
						prev2.append("rm /system/etc/init.d/04zip;");

						//Delete DreamNarae Save
						prev2.append("rm /system/etc/init.d/00sp;");
						prev2.append("rm /system/etc/init.d/01v;");
						prev2.append("rm /system/etc/init.d/02deep;");
						prev2.append("rm /system/etc/init.d/03zip;");

						// Delete DreamNarae Prev
						prev2.append("rm /system/etc/init.d/00proppv;");
						prev2.append("rm /system/etc/init.d/01kswapd0pv;");
						prev2.append("rm /system/etc/init.d/02iopv;");
						prev2.append("rm /system/etc/init.d/03freqpv;");
						prev2.append("rm /system/etc/init.d/04zippv;");
						prev2.append("rm /system/etc/init.d/01iopv;");
						prev2.append("rm /system/etc/init.d/02freqpv;");

						// Create Bakcup "install-recovery.sh"
						prev2.append("mkdir /sdcard/dreamnarae/backup;");
						prev2.append("cat /system/etc/install-recovery.sh > /sdcard/dreamnarae/backup/install-recovery.sh;");
						
						// Copy Prev Files to /system/
						prev2.append("cat /data/data/angeloid.dreamnarae/files/00proppv > /system/etc/init.d/00proppv;");
						prev2.append("cat /data/data/angeloid.dreamnarae/files/01iopv > /system/etc/init.d/01iopv;");
						prev2.append("cat /data/data/angeloid.dreamnarae/files/02freqpv > /system/etc/init.d/02freqpv;");
						prev2.append("cat /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_prev > /system/98banner_dreamnarae_prev;");
						prev2.append("cat /data/data/angeloid.dreamnarae/files/install-recovery.sh > /system/etc/install-recovery.sh;");

						// Delete Download Files
						prev2.append("rm /data/data/angeloid.dreamnarae/files/00proppv;");
						prev2.append("rm /data/data/angeloid.dreamnarae/files/01iopv;");
						prev2.append("rm /data/data/angeloid.dreamnarae/files/02freqpv;");
						prev2.append("rm /data/data/angeloid.dreamnarae/files/install-recovery.sh;");
						prev2.append("rm /data/data/angeloid.dreamnarae/files/98banner_dreamnarae_prev;");

						// Permission prev Files
						prev2.append("chmod 755 /system/etc/init.d/00proppv;");
						prev2.append("chmod 755 /system/etc/init.d/01iopv;");
						prev2.append("chmod 755 /system/etc/init.d/02freqpv;");
						prev2.append("chmod 755 /system/98banner_dreamnarae_prev;");
						prev2.append("chmod 755 /system/etc/install-recovery.sh;");

						// Mount ro /system
						prev2.append("busybox mount -o ro,remount /system;");

						try {
							Thread.sleep(3000);
							Prev_Helper2.instantExec_prev2(getActivity(),
									prev2.toString());

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
			dialog_prev2.dismiss(); // DialogProgress(true)�̸� ����,false�̸�
									// ����
			usercheck_prev.setImageResource(R.drawable.apply);
			previ.setEnabled(false);
			previ.setFocusable(false);
			previi.setEnabled(false);
			previi.setFocusable(false);
			progresstext_prev.setText(R.string.tab_tab2_installed);
		}

	}
}
