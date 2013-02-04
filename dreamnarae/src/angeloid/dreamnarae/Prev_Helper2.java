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

public class Prev_Helper2 {
	// 선언
	static final String LOGTAG = "Helper_prev2";
	// 스크립트 이름(영어 소문자를 추천한다)
	public final static String SCRIPT_NAME_prev2 = "dnprev2.sh";

	// b.append 연동 부분
	public static boolean instantExec_prev2(Context context, String command) {
		try {
			runSuCommand_prev2(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 복사(cat) 기능 연동 부분
	public static String dumpFile_prev2(String filename) {
		String line_prev2 = "";
		try {
			Process ifc_prev2 = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_prev2 = new BufferedReader(
					new InputStreamReader(ifc_prev2.getInputStream()));
			line_prev2 = bis_prev2.readLine();
			ifc_prev2.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_prev2;
	}

	// b.append 연동부분
	public static Process runSuCommandAsync_prev2(Context context,
			String command) throws IOException {
		DataOutputStream fout_prev2 = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_prev2, 0));
		fout_prev2.writeBytes(command);
		fout_prev2.close();

		String[] args_prev2 = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_prev2 };
		Process proc_prev2 = Runtime.getRuntime().exec(args_prev2);
		return proc_prev2;
	}

	// 시스템 마운트 부분
	public static void remountSystem_prev2(Context c) {
		try {
			runSuCommand_prev2(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU 명령어 체크
	public static int runSuCommand_prev2(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_prev2(context, command).waitFor();
	}
}
