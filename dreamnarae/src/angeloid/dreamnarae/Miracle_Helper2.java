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

public class Miracle_Helper2 {
	// 선언
	static final String LOGTAG = "Helper_miracle2";
	// 스크립트 이름(영어 소문자를 추천한다)
	public final static String SCRIPT_NAME_miracle2 = "dnmiracle2.sh";

	// b.append 연동 부분
	public static boolean instantExec_miracle2(Context context, String command) {
		try {
			runSuCommand_miracle2(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 복사(cat) 기능 연동 부분
	public static String dumpFile_miracle2(String filename) {
		String line_miracle2 = "";
		try {
			Process ifc_miracle2 = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_miracle2 = new BufferedReader(
					new InputStreamReader(ifc_miracle2.getInputStream()));
			line_miracle2 = bis_miracle2.readLine();
			ifc_miracle2.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_miracle2;
	}

	// b.append 연동부분
	public static Process runSuCommandAsync_miracle2(Context context,
			String command) throws IOException {
		DataOutputStream fout_miracle2 = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_miracle2, 0));
		fout_miracle2.writeBytes(command);
		fout_miracle2.close();

		String[] args_miracle2 = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_miracle2 };
		Process proc_miracle2 = Runtime.getRuntime().exec(args_miracle2);
		return proc_miracle2;
	}

	// 시스템 마운트 부분
	public static void remountSystem_miracle2(Context c) {
		try {
			runSuCommand_miracle2(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU 명령어 체크
	public static int runSuCommand_miracle2(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_miracle2(context, command).waitFor();
	}
}
