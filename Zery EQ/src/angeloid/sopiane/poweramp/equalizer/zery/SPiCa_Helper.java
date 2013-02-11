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
package angeloid.sopiane.poweramp.equalizer.zery;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class SPiCa_Helper {
	// ����
	static final String LOGTAG = "Helper_spica";
	// ��ũ��Ʈ �̸�(���� �ҹ��ڸ� ��õ�Ѵ�)
	public final static String SCRIPT_NAME_spica = "zeryqinstall1.sh";

	// b.append ���� �κ�
	public static boolean instantExec_spica(Context context, String command) {
		try {
			runSuCommand_spica(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// ����(cat) ��� ���� �κ�
	public static String dumpFile_spica(String filename) {
		String line_spica = "";
		try {
			Process ifc_spica = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_spica = new BufferedReader(
					new InputStreamReader(ifc_spica.getInputStream()));
			line_spica = bis_spica.readLine();
			ifc_spica.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_spica;
	}

	// b.append �����κ�
	public static Process runSuCommandAsync_spica(Context context,
			String command) throws IOException {
		DataOutputStream fout_spica = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_spica, 0));
		fout_spica.writeBytes(command);
		fout_spica.close();

		String[] args_spica = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_spica };
		Process proc_spica = Runtime.getRuntime().exec(args_spica);
		return proc_spica;
	}

	// �ý��� ����Ʈ �κ�
	public static void remountSystem_spica(Context c) {
		try {
			runSuCommand_spica(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU ��ɾ� üũ
	public static int runSuCommand_spica(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_spica(context, command).waitFor();
	}
}