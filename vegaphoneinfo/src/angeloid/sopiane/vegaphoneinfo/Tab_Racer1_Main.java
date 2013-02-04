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

public class Tab_Racer1_Main extends Fragment {
	Button backup;
	Button restore;
	Handler mHandler;
	ProgressDialog dialog_backup;
	ProgressDialog dialog_restore;
	TextView racer_info1;
	TextView racer_info2;
	TextView racer_info3;
	ImageView racer_backup_info;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.tab_racer1, null);
		backup = (Button) root.findViewById(R.id.tab_racer_backup);
		restore = (Button) root.findViewById(R.id.tab_racer_restore);
		racer_info1 = (TextView) root.findViewById(R.id.tab_racer_subject);
		racer_info2 = (TextView) root.findViewById(R.id.tab_racer_device);
		racer_info3 = (TextView) root.findViewById(R.id.tab_racer_partinfo);
		racer_backup_info = (ImageView) root.findViewById(R.id.racer_backup_info);
		backup.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		restore.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		racer_info1.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		racer_info2.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		racer_info3.setTypeface(Tab_MainActivity.Fonts.THEOREM);
		if (new File("/sdcard/imeibackup/imei.img").exists()) {
			racer_backup_info.setImageResource(R.drawable.apply);
			backup.setEnabled(false);
			backup.setFocusable(false);
			
		} else {

			racer_backup_info.setImageResource(R.drawable.not_apply);
			restore.setEnabled(false);
			restore.setFocusable(false);

		}
		backup.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgressbackup(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		restore.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO
				v.postDelayed(new Runnable() {
					public void run() {
						DialogProgressrestore(false);
					}
				}, 10); // 0.01 간격을 줘서 버튼 해제 시간을 벌어줌
			}
		});
		return root;
	}

	// 백업
	private void DialogProgressbackup(boolean close) {
		if (!close) {
			dialog_backup = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgressbackup(true); // 창을 내린다.
					Tab_Racer1_Backup.instantExec_racer_backup(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder backup = new StringBuilder();
					try {
						Thread.sleep(2000);
						backup.append("mount -o rw,remount /system;");
						backup.append("mkdir /sdcard/imeibackup;");
						backup.append("dd if=/dev/block/mmcblk0p10 of=/sdcard/imeibackup/imei.img;");
						backup.append("busybox mount -o ro,remount /system;");

						try {
							Thread.sleep(3000);
							Tab_Racer1_Backup.instantExec_racer_backup(getActivity(),
									backup.toString());

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
			dialog_backup.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
			racer_backup_info.setImageResource(R.drawable.apply);
			backup.setEnabled(false);
			backup.setFocusable(false);
		}
	}

	// 복원
	private void DialogProgressrestore(boolean close) {
		if (!close) {
			dialog_restore = ProgressDialog.show(getActivity(), "", "Loading..",
					true);
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgressrestore(true); // 창을 내린다.
					Tab_Racer1_Restore.instantExec_racer_restore(getActivity(),
							"busybox mount -o rw,remount /system ; ");
					StringBuilder restore = new StringBuilder();
					try {
						Thread.sleep(2000);
						restore.append("mount -o rw,remount /system;");
						restore.append("dd if=/sdcard/imeibackup/imei.img of=/dev/block/mmcblk0p10;");
						restore.append("busybox mount -o ro,remount /system;");

						try {
							Thread.sleep(3000);
							Tab_Racer1_Restore.instantExec_racer_restore(getActivity(),
									restore.toString());

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
			dialog_restore.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
			racer_backup_info.setImageResource(R.drawable.not_apply);
			restore.setEnabled(false);
			restore.setFocusable(false);
		}
	}
}
