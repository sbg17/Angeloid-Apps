package angeloid.windsekirun.lu6200.imei;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Helper {
	static final String LOGTAG = "Helper";

	public final static String SCRIPT_NAME = "surunner.sh";

	// Instant Excute.
	public static boolean instantExec(Context context, String command) {
		try {
			runSuCommand(context, command.toString());
		} catch (Exception e) {
			Toast.makeText(context, "E:ScriptRunner: " + e.getMessage(),
					Toast.LENGTH_SHORT).show();
			Log.d(LOGTAG, "E:ScriptRunner: " + e.getMessage());
			return false;
		}
		return true;
	}

	public static String getSystemProp(String key) {
		String line = "";
		try {
			Process ifc = Runtime.getRuntime().exec("getprop " + key);
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					ifc.getInputStream()));
			line = bis.readLine();
			ifc.destroy();

		} catch (java.io.IOException e) {
			return new String(LOGTAG);
		}

		return line;
	}

	public static String dumpFile(String filename) {
		String line = "";
		try {
			Process ifc = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					ifc.getInputStream()));
			line = bis.readLine();
			ifc.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line;
	}

	public static Process runSuCommandAsync(Context context, String command)
			throws IOException {
		DataOutputStream fout = new DataOutputStream(context.openFileOutput(
				SCRIPT_NAME, 0));
		fout.writeBytes(command);
		fout.close();

		String[] args = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME };
		Process proc = Runtime.getRuntime().exec(args);
		return proc;
	}

	public static boolean isBuildPropertyAvaliable(Context c, String propName) {
		try {
			Process p = runSuCommandAsync(c, "busybox grep \"" + propName
					+ "=\" /system/build.prop");
			try {
				p.waitFor();
				Log.d("Helper",
						"isBuildPropAvali : exitValue is : "
								+ String.valueOf(p.exitValue()));
				if (p.exitValue() == 0)
					return true;

			} catch (InterruptedException d) {
				Log.e("Helper",
						"Failed grep build.prop and waiting for process. errcode:"
								+ d.toString());
			}
		} catch (Exception d) {
			Log.e("Helper", "Failed grep build.prop. errcode:" + d.toString());
		}
		return false;
	}

	public static void remountSystem(Context c) {
		try {
			runSuCommand(c, "mount -orw,remount /system ; ");
		} catch (Exception d) { /* Does not require error handler */
		}
	}

	public static Process changeBuildProperty(Context c, String propName,
			String newValue) {
		Process p = null;
		try {
			remountSystem(c);

			p = runSuCommandAsync(c, "busybox sed -i \"s/" + propName + "=.*/"
					+ propName + "=" + newValue + "/g\" /system/build.prop ; ");
			p.waitFor();
		} catch (Exception d) {
			Log.e("Helper",
					"Failed to change build.prop. errcode:" + d.toString());
		}
		return p;
	}

	public static Process deleteBuildProperty(Context c, String propName) {
		Process p = null;
		try {
			remountSystem(c);

			p = runSuCommandAsync(c, "busybox sed -i \"/" + propName
					+ "=.*/d\" /system/build.prop ; ");
			p.waitFor();
		} catch (Exception d) {
			Log.e("Helper", "d");
			Log.e("Helper",
					"Failed to delete build.prop. errcode:" + d.toString());
		}
		return p;
	}

	public static Process writeBuildProperty(Context c, String propName,
			String value) {
		Process p = null;
		try {
			remountSystem(c);

			p = runSuCommandAsync(c, "echo " + propName + "=" + value
					+ " >> /system/build.prop ; ");
			Log.d("Helper", "echo " + propName + "=" + value
					+ " >> /system/build.prop ; ");
			p.waitFor();
		} catch (Exception d) {
			Log.e("Helper",
					"Failed to write build.prop. errcode:" + d.toString());
		}
		return p;
	}

	public static int storeBuildProperty(Context c, String propName,
			String newValue) {

		if (isBuildPropertyAvaliable(c, propName)) {
			changeBuildProperty(c, propName, newValue);
			return 2;
		} else {
			writeBuildProperty(c, propName, newValue);
			return 1;
		}
	}

	public static void storeBuildPropertyBatched(Context c, String propArgument) {
		StringBuilder propName = new StringBuilder("");
		StringBuilder propValue = new StringBuilder("");
		StringBuilder commandLine = new StringBuilder("");
		boolean isValue = false;
		for (int i = 0; i < propArgument.length(); i++) {
			char ch = propArgument.charAt(i);
			if (ch == '=' && isValue == false) {
				isValue = true;
			} else if (ch == ';') {
				if (isBuildPropertyAvaliable(c, propName.toString())) {
					commandLine.append("busybox sed -i \"s/"
							+ propName.toString() + "=.*/"
							+ propName.toString() + "=" + propValue.toString()
							+ "/g\" /system/build.prop ; ");
				} else {
					commandLine.append("echo " + propName.toString() + "="
							+ propValue.toString()
							+ " >> /system/build.prop ; ");
				}

				isValue = false;
				propName = new StringBuilder("");
				propValue = new StringBuilder("");
			} else {
				if (isValue)
					propValue.append(ch);
				else
					propName.append(ch);
			}
		}
	}

	public static void deleteBuildPropertyBatched(Context c, String propArgument) {
		StringBuilder propName = new StringBuilder("");
		StringBuilder propValue = new StringBuilder("");
		StringBuilder commandLine = new StringBuilder("");
		boolean isValue = false;
		for (int i = 0; i < propArgument.length(); i++) {
			char ch = propArgument.charAt(i);
			if (ch == '=' && isValue == false) {
				isValue = true;
			} else if (ch == ';') {
				commandLine.append("busybox sed -i \"/" + propName
						+ "=.*/d\" /system/build.prop ; ");

				isValue = false;
				propName = new StringBuilder("");
				propValue = new StringBuilder("");
			} else {
				if (isValue)
					propValue.append(ch);
				else
					propName.append(ch);
			}
		}

		Process p = null;
		try {
			remountSystem(c);

			p = runSuCommandAsync(c, commandLine.toString());
			p.waitFor();
		} catch (Exception d) {
			Log.e("Helper",
					"Failed to batch delete build.prop. errcode:"
							+ d.toString());
		}
	}

	public static int runSuCommand(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync(context, command).waitFor();
	}
}
