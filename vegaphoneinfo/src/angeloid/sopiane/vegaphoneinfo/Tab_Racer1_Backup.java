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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class Tab_Racer1_Backup {
	// 선언
	static final String LOGTAG = "Helper_racer_backup";
	// 스크립트 이름(영어 소문자를 추천한다)
	public final static String SCRIPT_NAME_racer_backup = "racer_backup.sh";

	// b.append 연동 부분
	public static boolean instantExec_racer_backup(Context context, String command) {
		try {
			runSuCommand_racer_backup(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 복사(cat) 기능 연동 부분
	public static String dumpFile_racer_backup(String filename) {
		String line_racer_backup = "";
		try {
			Process ifc_racer_backup = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_racer_backup = new BufferedReader(
					new InputStreamReader(ifc_racer_backup.getInputStream()));
			line_racer_backup = bis_racer_backup.readLine();
			ifc_racer_backup.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_racer_backup;
	}

	// b.append 연동부분
	public static Process runSuCommandAsync_racer_backup(Context context,
			String command) throws IOException {
		DataOutputStream fout_racer_backup = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_racer_backup, 0));
		fout_racer_backup.writeBytes(command);
		fout_racer_backup.close();

		String[] args_racer_backup = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_racer_backup };
		Process proc_racer_backup = Runtime.getRuntime().exec(args_racer_backup);
		return proc_racer_backup;
	}

	// 시스템 마운트 부분
	public static void remountSystem_racer_backup(Context c) {
		try {
			runSuCommand_racer_backup(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU 명령어 체크
	public static int runSuCommand_racer_backup(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_racer_backup(context, command).waitFor();
	}
}
