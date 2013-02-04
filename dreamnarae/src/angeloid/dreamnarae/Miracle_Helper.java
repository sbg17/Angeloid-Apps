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

public class Miracle_Helper {
	// 선언
	static final String LOGTAG = "Helper_miracle";
	// 스크립트 이름(영어 소문자를 추천한다)
	public final static String SCRIPT_NAME_miracle = "dnmiracle.sh";

	// b.append 연동 부분
	public static boolean instantExec_miracle(Context context, String command) {
		try {
			runSuCommand_miracle(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 복사(cat) 기능 연동 부분
	public static String dumpFile_miracle(String filename) {
		String line_miracle = "";
		try {
			Process ifc_miracle = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_miracle = new BufferedReader(
					new InputStreamReader(ifc_miracle.getInputStream()));
			line_miracle = bis_miracle.readLine();
			ifc_miracle.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_miracle;
	}

	// b.append 연동부분
	public static Process runSuCommandAsync_miracle(Context context,
			String command) throws IOException {
		DataOutputStream fout_miracle = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_miracle, 0));
		fout_miracle.writeBytes(command);
		fout_miracle.close();

		String[] args_miracle = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_miracle };
		Process proc_miracle = Runtime.getRuntime().exec(args_miracle);
		return proc_miracle;
	}

	// 시스템 마운트 부분
	public static void remountSystem_miracle(Context c) {
		try {
			runSuCommand_miracle(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU 명령어 체크
	public static int runSuCommand_miracle(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_miracle(context, command).waitFor();
	}
}
