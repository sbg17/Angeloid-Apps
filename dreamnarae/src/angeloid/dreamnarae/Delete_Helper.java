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

public class Delete_Helper {
	// ����
	static final String LOGTAG = "Helper_delete";
	// ��ũ��Ʈ �̸�(���� �ҹ��ڸ� ��õ�Ѵ�)
	public final static String SCRIPT_NAME_delete = "dndelete.sh";

	// b.append ���� �κ�
	public static boolean instantExec_delete(Context context, String command) {
		try {
			runSuCommand_delete(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// ����(cat) ��� ���� �κ�
	public static String dumpFile_delete(String filename) {
		String line_delete = "";
		try {
			Process ifc_delete = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_delete = new BufferedReader(
					new InputStreamReader(ifc_delete.getInputStream()));
			line_delete = bis_delete.readLine();
			ifc_delete.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_delete;
	}

	// b.append �����κ�
	public static Process runSuCommandAsync_delete(Context context,
			String command) throws IOException {
		DataOutputStream fout_delete = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_delete, 0));
		fout_delete.writeBytes(command);
		fout_delete.close();

		String[] args_delete = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_delete };
		Process proc_delete = Runtime.getRuntime().exec(args_delete);
		return proc_delete;
	}

	// �ý��� ����Ʈ �κ�
	public static void remountSystem_delete(Context c) {
		try {
			runSuCommand_delete(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU ��ɾ� üũ
	public static int runSuCommand_delete(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_delete(context, command).waitFor();
	}
}
