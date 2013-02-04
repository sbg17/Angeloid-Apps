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

public class SPiCa_Helper2 {
	// ����
	static final String LOGTAG = "Helper_spica2";
	// ��ũ��Ʈ �̸�(���� �ҹ��ڸ� ��õ�Ѵ�)
	public final static String SCRIPT_NAME_spica2 = "dnspica2.sh";

	// b.append ���� �κ�
	public static boolean instantExec_spica2(Context context, String command) {
		try {
			runSuCommand_spica2(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// ����(cat) ��� ���� �κ�
	public static String dumpFile_spica2(String filename) {
		String line_spica2 = "";
		try {
			Process ifc_spica2 = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_spica2 = new BufferedReader(
					new InputStreamReader(ifc_spica2.getInputStream()));
			line_spica2 = bis_spica2.readLine();
			ifc_spica2.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_spica2;
	}

	// b.append �����κ�
	public static Process runSuCommandAsync_spica2(Context context,
			String command) throws IOException {
		DataOutputStream fout_spica2 = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_spica2, 0));
		fout_spica2.writeBytes(command);
		fout_spica2.close();

		String[] args_spica2 = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_spica2 };
		Process proc_spica = Runtime.getRuntime().exec(args_spica2);
		return proc_spica;
	}

	// �ý��� ����Ʈ �κ�
	public static void remountSystem_spica2(Context c) {
		try {
			runSuCommand_spica2(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU ��ɾ� üũ
	public static int runSuCommand_spica2(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_spica2(context, command).waitFor();
	}
}
