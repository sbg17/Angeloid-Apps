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

public class Save_Helper {
	// 선언
	static final String LOGTAG = "Helper_save";
	// 스크립트 이름(영어 소문자를 추천한다)
	public final static String SCRIPT_NAME_save = "dnsave.sh";

	// b.append 연동 부분
	public static boolean instantExec_save(Context context, String command) {
		try {
			runSuCommand_save(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 복사(cat) 기능 연동 부분
	public static String dumpFile_save(String filename) {
		String line_save = "";
		try {
			Process ifc_save = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_save = new BufferedReader(new InputStreamReader(
					ifc_save.getInputStream()));
			line_save = bis_save.readLine();
			ifc_save.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_save;
	}

	// b.append 연동부분
	public static Process runSuCommandAsync_save(Context context, String command)
			throws IOException {
		DataOutputStream fout_save = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_save, 0));
		fout_save.writeBytes(command);
		fout_save.close();

		String[] args_save = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_save };
		Process proc_save = Runtime.getRuntime().exec(args_save);
		return proc_save;
	}

	// 시스템 마운트 부분
	public static void remountSystem_save(Context c) {
		try {
			runSuCommand_save(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU 명령어 체크
	public static int runSuCommand_save(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_save(context, command).waitFor();
	}
}
