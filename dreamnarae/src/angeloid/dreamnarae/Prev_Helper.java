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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class Prev_Helper {
	// ����
	static final String LOGTAG = "Helper_prev";
	// ��ũ��Ʈ �̸�(���� �ҹ��ڸ� ��õ�Ѵ�)
	public final static String SCRIPT_NAME_prev = "dnprev.sh";

	// b.append ���� �κ�
	public static boolean instantExec_prev(Context context, String command) {
		try {
			runSuCommand_prev(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// ����(cat) ��� ���� �κ�
	public static String dumpFile_prev(String filename) {
		String line_prev = "";
		try {
			Process ifc_prev = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_prev = new BufferedReader(
					new InputStreamReader(ifc_prev.getInputStream()));
			line_prev = bis_prev.readLine();
			ifc_prev.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_prev;
	}

	// b.append �����κ�
	public static Process runSuCommandAsync_prev(Context context,
			String command) throws IOException {
		DataOutputStream fout_prev = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_prev, 0));
		fout_prev.writeBytes(command);
		fout_prev.close();

		String[] args_prev = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_prev };
		Process proc_prev = Runtime.getRuntime().exec(args_prev);
		return proc_prev;
	}

	// �ý��� ����Ʈ �κ�
	public static void remountSystem_prev(Context c) {
		try {
			runSuCommand_prev(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU ��ɾ� üũ
	public static int runSuCommand_prev(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_prev(context, command).waitFor();
	}
}
