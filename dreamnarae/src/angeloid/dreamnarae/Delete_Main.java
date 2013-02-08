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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

public class Delete_Main extends Fragment {
	Button deletei;
	Button deleteii;
	TextView progresstext_delete;
	Handler mHandler_delete;
	ProgressDialog dialog_delete;
	ProgressDialog dialog_delete2;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_tab2_delete,
				null);
		deletei = (Button) root.findViewById(R.id.tab_tab2_delete_reboot_ok);
		deleteii = (Button) root.findViewById(R.id.tab_tab2_delete_reboot_no);
		progresstext_delete = (TextView) root
				.findViewById(R.id.tab_tab2_delete_running);
		progresstext_delete.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		deletei.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		deleteii.setTypeface(Tab_MainActivity.Fonts.THEOREM);

		deletei.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgress(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		deleteii.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
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
			dialog_delete = ProgressDialog.show(getActivity(), "","Loading..",
					true);
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress(true); // 창을 내린다.
					Delete_Helper.instantExec_delete(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder delete = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						delete.append("mount -o rw,remount /system;");

						// Delete Flag Files
						delete.append("rm /system/SPiCa;");
						delete.append("rm /system/DN;");
						delete.append("rm /system/Miracle;");
						delete.append("rm /system/Save;");
						delete.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						delete.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						delete.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						delete.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						delete.append("rm /system/98banner_dreamnarae_spica;");
						delete.append("rm /system/98banner_dreamnarae_miracle;");
						delete.append("rm /system/98banner_dreamnarae_save;");
						delete.append("rm /system/98banner_dreamnarae_prev;");
						
						// Delete old Script Manager Files
						delete.append("rm /system/bin/EnableDN.sh;");
						delete.append("rm /data/DNEnable;");
						delete.append("rm /system/bin/RemoveDN.sh;");
						delete.append("rm /data/DNMiracle.sh;");
						delete.append("rm /data/DNSPiCa.sh;");
						delete.append("rm /data/DNSave.sh;");
						delete.append("rm /data/DNPrev.sh;");

						// Delete New Script Manager Files
						delete.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						delete.append("rm /system/etc/init.d/00prop;");
						delete.append("rm /system/etc/init.d/01io;");
						delete.append("rm /system/etc/init.d/02freq;");
						delete.append("rm /system/etc/init.d/03zipalign;");
						delete.append("rm /system/etc/init.d/01kswapd0;");
						delete.append("rm /system/etc/init.d/02io;");
						delete.append("rm /system/etc/init.d/03freq;");
						delete.append("rm /system/etc/init.d/04zipalign;");
						delete.append("rm /system/angeloidteam/dreamnarae/00prop;");
						delete.append("rm /system/angeloidteam/dreamnarae/01io;");
						delete.append("rm /system/angeloidteam/dreamnarae/02freq;");

						//Delete DreamNarae Miracle
						delete.append("rm /system/etc/init.d/00set;");
						delete.append("rm /system/etc/init.d/01property;");
						delete.append("rm /system/etc/init.d/02vsls;");
						delete.append("rm /system/etc/init.d/03dch;");
						delete.append("rm /system/etc/init.d/04zip;");
						delete.append("rm /system/angeloidteam/dreamnarae/00set;");
						delete.append("rm /system/angeloidteam/dreamnarae/01property;");
						delete.append("rm /system/angeloidteam/dreamnarae/02vsls;");
						delete.append("rm /system/angeloidteam/dreamnarae/03dch;");

						//Delete DreamNarae Save
						delete.append("rm /system/etc/init.d/00sp;");
						delete.append("rm /system/etc/init.d/01v;");
						delete.append("rm /system/etc/init.d/02deep;");
						delete.append("rm /system/etc/init.d/03zip;");
						delete.append("rm /system/angeloidteam/dreamnarae/00sp;");
						delete.append("rm /system/angeloidteam/dreamnarae/01v;");
						delete.append("rm /system/angeloidteam/dreamnarae/02deep;");

						// Delete DreamNarae Prev
						delete.append("rm /system/etc/init.d/00proppv;");
						delete.append("rm /system/etc/init.d/01kswapd0pv;");
						delete.append("rm /system/etc/init.d/02iopv;");
						delete.append("rm /system/etc/init.d/03freqpv;");
						delete.append("rm /system/etc/init.d/04zippv;");
						delete.append("rm /system/etc/init.d/01iopv;");
						delete.append("rm /system/etc/init.d/02freqpv;");
						delete.append("rm /system/angeloidteam/dreamnarae/00propv;");
						delete.append("rm /system/angeloidteam/dreamnarae/01iopv;");
						delete.append("rm /system/angeloidteam/dreamnarae/02freqpv;");

						// Mount ro /system
						delete.append("busybox mount -o ro,remount /system;");

						// Reboot device(Only to Progress1)
						delete.append("reboot;");
						try {
							Thread.sleep(3000);
							Delete_Helper.instantExec_delete(getActivity(),
									delete.toString());

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
			dialog_delete.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
		}
	}

	// 리붓안함
	private void DialogProgress2(boolean close) {
		if (!close) {
			dialog_delete2 = ProgressDialog.show(getActivity(), "",
					"Loading..", true);
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress2(true); // 창을 내린다.
					Delete_Helper2.instantExec_delete2(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder delete2 = new StringBuilder();
					try {
						Thread.sleep(2000);
						// Mount rw /system
						delete2.append("mount -o rw,remount /system;");

						// Delete Flag Files
						delete2.append("rm /system/SPiCa;");
						delete2.append("rm /system/DN;");
						delete2.append("rm /system/Miracle;");
						delete2.append("rm /system/Save;");
						delete2.append("rm /system/etc/init.d/98banner_dreamnarae_spica;");
						delete2.append("rm /system/etc/init.d/98banner_dreamnarae_miracle;");
						delete2.append("rm /system/etc/init.d/98banner_dreamnarae_save;");
						delete2.append("rm /system/etc/init.d/98banner_dreamnarae_prev;");
						delete2.append("rm /system/98banner_dreamnarae_spica;");
						delete2.append("rm /system/98banner_dreamnarae_miracle;");
						delete2.append("rm /system/98banner_dreamnarae_save;");
						delete2.append("rm /system/98banner_dreamnarae_prev;");
						
						// Delete old Script Manager Files
						delete2.append("rm /system/bin/EnableDN.sh;");
						delete2.append("rm /data/DNEnable;");
						delete2.append("rm /system/bin/RemoveDN.sh;");
						delete2.append("rm /data/DNMiracle.sh;");
						delete2.append("rm /data/DNSPiCa.sh;");
						delete2.append("rm /data/DNSave.sh;");
						delete2.append("rm /data/DNPrev.sh;");

						// Delete New Script Manager Files
						delete2.append("rm /system/etc/install-recovery.sh;");

						// Delete DreamNarae SPiCa
						delete2.append("rm /system/etc/init.d/00prop;");
						delete2.append("rm /system/etc/init.d/01io;");
						delete2.append("rm /system/etc/init.d/02freq;");
						delete2.append("rm /system/etc/init.d/03zipalign;");
						delete2.append("rm /system/etc/init.d/01kswapd0;");
						delete2.append("rm /system/etc/init.d/02io;");
						delete2.append("rm /system/etc/init.d/03freq;");
						delete2.append("rm /system/etc/init.d/04zipalign;");
						delete2.append("rm /system/angeloidteam/dreamnarae/00prop;");
						delete2.append("rm /system/angeloidteam/dreamnarae/01io;");
						delete2.append("rm /system/angeloidteam/dreamnarae/02freq;");

						//Delete DreamNarae Miracle
						delete2.append("rm /system/etc/init.d/00set;");
						delete2.append("rm /system/etc/init.d/01property;");
						delete2.append("rm /system/etc/init.d/02vsls;");
						delete2.append("rm /system/etc/init.d/03dch;");
						delete2.append("rm /system/etc/init.d/04zip;");
						delete2.append("rm /system/angeloidteam/dreamnarae/00set;");
						delete2.append("rm /system/angeloidteam/dreamnarae/01property;");
						delete2.append("rm /system/angeloidteam/dreamnarae/02vsls;");
						delete2.append("rm /system/angeloidteam/dreamnarae/03dch;");

						//Delete DreamNarae Save
						delete2.append("rm /system/etc/init.d/00sp;");
						delete2.append("rm /system/etc/init.d/01v;");
						delete2.append("rm /system/etc/init.d/02deep;");
						delete2.append("rm /system/etc/init.d/03zip;");
						delete2.append("rm /system/angeloidteam/dreamnarae/00sp;");
						delete2.append("rm /system/angeloidteam/dreamnarae/01v;");
						delete2.append("rm /system/angeloidteam/dreamnarae/02deep;");

						// Delete DreamNarae Prev
						delete2.append("rm /system/etc/init.d/00proppv;");
						delete2.append("rm /system/etc/init.d/01kswapd0pv;");
						delete2.append("rm /system/etc/init.d/02iopv;");
						delete2.append("rm /system/etc/init.d/03freqpv;");
						delete2.append("rm /system/etc/init.d/04zippv;");
						delete2.append("rm /system/etc/init.d/01iopv;");
						delete2.append("rm /system/etc/init.d/02freqpv;");
						delete2.append("rm /system/angeloidteam/dreamnarae/00propv;");
						delete2.append("rm /system/angeloidteam/dreamnarae/01iopv;");
						delete2.append("rm /system/angeloidteam/dreamnarae/02freqpv;");

						// Mount ro /system
						delete2.append("busybox mount -o ro,remount /system;");
						try {
							Thread.sleep(3000);
							Delete_Helper2.instantExec_delete2(getActivity(),
									delete2.toString());

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 15000);
		} else {
			dialog_delete2.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
		}
	}
}