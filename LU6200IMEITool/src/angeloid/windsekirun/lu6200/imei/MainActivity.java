package angeloid.windsekirun.lu6200.imei;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button backupbutton, restorebutton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, SplashActivity.class));
		setContentView(R.layout.activity_main);
	}

	void rootcheck() {
		if (!(new File("/system/bin/su").exists())
				&& !(new File("/system/xbin/su").exists())
				&& !(new File("/system/bin/busybox").exists())) {
			AlertDialog.Builder builder = new Builder(MainActivity.this);
			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface d, int w) {
							finish();
						}
					});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface d) {
					finish();
				}
			});
			builder.setMessage(R.string.noroot);
			builder.create().show();
			return;
		}
	}

	void busyboxcheck() {
		if (!(new File("/system/bin/busybox").exists())
				&& !(new File("/system/xbin/busybox").exists())) {
			AlertDialog.Builder builder = new Builder(MainActivity.this);

			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface d, int w) {
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri
									.parse("market://search?q=busybox"));
							startActivity(intent);
							overridePendingTransition(R.anim.fade, R.anim.hold);
						}
					});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface d) {
					finish();
				}
			});
		}
	}

	void startcheck() {
		rootcheck();
		busyboxcheck();
	}

	public void backupbuttonclicked(View v) {
		backup();
	}

	public void restorebuttonclicked(View v) {
		restore();
	}

	public void backbuttonclicked(View v) {
		setContentView(R.layout.activity_main);
	}

	public void infobuttonclicked(View v) {
		setContentView(R.layout.activity_help);
	}

	public void emailbuttonclicked(View v) {
		// 이메일 보내기
		Uri uri = Uri.parse("mailto:sirospace@sirospace.com");
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		startActivity(it);
	}

	public void facebookbuttonclicked(View v) {
		// 웹페이지 띄우기
		Uri uri = Uri.parse("http://facebook.com/windsekirun");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}

	public void twitterbuttonclicked(View v) {
		// 웹페이지 띄우기
		Uri uri = Uri.parse("http://twitter.com/windsekirun");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}

	void backup() {
		startcheck();
		Helper.instantExec(MainActivity.this,
				"busybox mount -orw,remount /system;");
		StringBuilder b = new StringBuilder();
		b.append("mount -o rw,remount /dev/block/mtdblock0 /system;");
		b.append("mkdir /sdcard/imeibackup;");
		b.append("dd if=/dev/block/mmcblk0p5 of=/sdcard/imeibackup/imei1.img;");
		b.append("dd if=/dev/block/mmcblk0p10 of=/sdcard/imeibackup/imei2.img;");
		b.append("dd if=/dev/block/mmcblk0p11 of=/sdcard/imeibackup/imei3.img;");
		Helper.instantExec(this, b.toString());

		AlertDialog.Builder builder = new Builder(MainActivity.this);

		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface d, int w) {
						
					}
				});
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface d) {
			
			}
		});

		builder.setMessage(R.string.applysuccess);
		builder.create().show();

		return;

	}

	void restore() {
		startcheck();
		AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
		d.setMessage(R.string.uninstall_confirm);
		d.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						Helper.instantExec(
								MainActivity.this,
								"busybox mount -o rw,remount /system ; dd if=/sdcard/imeibackup/imei1.img of=/dev/block/mmcblk0p5 ; dd if=/sdcard/imeibackup/imei2.img of=/dev/block/mmcblk0p10 ; dd if=/sdcard/imeibackup/imei3.img of=/dev/block/mmcblk0p11 ; ");
						AlertDialog.Builder builder = new Builder(
								MainActivity.this);

						builder.setPositiveButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface d, int w) {
								
									}
								});
						builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface d) {
							
							}
						});

						builder.setMessage(R.string.uninstallsuccess);
						builder.create().show();

						return;

					}

				});
		d.show();

		return;
	};

}