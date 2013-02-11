package angeloid.sopiane.poweramp.equalizer.z;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button spicai;
	Button spicaii;
	TextView progresstext_spica;
	Handler mHandler_spica;
	ProgressDialog dialog_spica;
	ProgressDialog dialog_spica2;
	ImageView usercheck_spica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spicai = (Button) findViewById(R.id.tab_tab2_spica_reboot_ok);
		spicaii = (Button) findViewById(R.id.tab_tab2_spica_reboot_no);
		usercheck_spica = (ImageView) findViewById(R.id.tab_tab2_spica_usercheck);
		if (new File("/system/zeq").exists()) {
			usercheck_spica.setImageResource(R.drawable.apply);
			spicai.setEnabled(false);
			spicai.setFocusable(false);
			spicaii.setEnabled(false);
			spicaii.setFocusable(false);

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
	}

	// 리붓 함
	private void DialogProgress(boolean close) {
		if (!close) {
			dialog_spica = ProgressDialog.show(MainActivity.this, "",
					"Loading..", true);
			SPiCa_ZipTest.startUnzipping_spica(MainActivity.this);
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress(true); // 창을 내린다.
					SPiCa_Helper.instantExec_spica(MainActivity.this,
							"busybox mount -o rw,remount /system ; ");
					StringBuilder spica = new StringBuilder();
					try {
						Thread.sleep(2000);
						spica.append("mount -o rw,remount /system;");
						spica.append("busybox touch /system/zeq;");
						spica.append("rm /data/data/com.maxmpz.audioplayer/databases/folders.db;");
						spica.append("rm /data/data/com.maxmpz.audioplayer/databases/folders.db-shm;");
						spica.append("rm /data/data/com.maxmpz.audioplayer/databases/folders.db-wal;");
						spica.append("cat /data/data/angeloid.sopiane.poweramp.equalizer.z/files/folders.db > /data/data/com.maxmpz.audioplayer/databases/folders.db;");
						spica.append("cat /data/data/angeloid.sopiane.poweramp.equalizer.z/files/folders.db-shm > /data/data/com.maxmpz.audioplayer/databases/folders.db-shm;");
						spica.append("cat /data/data/angeloid.sopiane.poweramp.equalizer.z/files/folders.db-wal > /data/data/com.maxmpz.audioplayer/databases/folders.db-wal;");
						spica.append("chmod 664 /data/data/com.maxmpz.audioplayer/databases/folders.db;");
						spica.append("chmod 664 /data/data/com.maxmpz.audioplayer/databases/folders.db-shm;");
						spica.append("chmod 664 /data/data/com.maxmpz.audioplayer/databases/folders.db-wal;");
						// Reboot Device(Only to Progress1)
						spica.append("reboot;");

						try {
							Thread.sleep(3000);
							SPiCa_Helper.instantExec_spica(MainActivity.this,
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
			dialog_spica2 = ProgressDialog.show(MainActivity.this, "",
					"Loading..", true);
			SPiCa_ZipTest.startUnzipping_spica(MainActivity.this);
			Handler mHandler2 = new Handler();
			mHandler2.postDelayed(new Runnable() {
				@Override
				public void run() {
					DialogProgress2(true); // 창을 내린다.
					SPiCa_Helper2.instantExec_spica2(MainActivity.this,
							"busybox mount -o rw,remount /system ; ");
					StringBuilder spica2 = new StringBuilder();
					try {
						Thread.sleep(2000);
						spica2.append("busybox touch /system/zeq;");
						spica2.append("rm /data/data/com.maxmpz.audioplayer/databases/folders.db;");
						spica2.append("rm /data/data/com.maxmpz.audioplayer/databases/folders.db-shm;");
						spica2.append("rm /data/data/com.maxmpz.audioplayer/databases/folders.db-wal;");
						spica2.append("cat /data/data/angeloid.sopiane.poweramp.equalizer.z/files/folders.db > /data/data/com.maxmpz.audioplayer/databases/folders.db;");
						spica2.append("cat /data/data/angeloid.sopiane.poweramp.equalizer.z/files/folders.db-shm > /data/data/com.maxmpz.audioplayer/databases/folders.db-shm;");
						spica2.append("cat /data/data/angeloid.sopiane.poweramp.equalizer.z/files/folders.db-wal > /data/data/com.maxmpz.audioplayer/databases/folders.db-wal;");
						spica2.append("chmod 664 /data/data/com.maxmpz.audioplayer/databases/folders.db;");
						spica2.append("chmod 664 /data/data/com.maxmpz.audioplayer/databases/folders.db-shm;");
						spica2.append("chmod 664 /data/data/com.maxmpz.audioplayer/databases/folders.db-wal;");
						try {
							Thread.sleep(3000);
							SPiCa_Helper2.instantExec_spica2(MainActivity.this,
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
				
			}, 10000);
			
		} else {
			dialog_spica2.dismiss(); // DialogProgress(true)이면 종료,false이면 실행
			usercheck_spica.setImageResource(R.drawable.apply);
			spicai.setEnabled(false);
			spicai.setFocusable(false);
			spicaii.setEnabled(false);
			spicaii.setFocusable(false);
		}
	}
}
