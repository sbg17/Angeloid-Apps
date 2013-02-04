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

public class Delete_Helper2 {
	// 선언
	static final String LOGTAG = "Helper_delete2";
	// 스크립트 이름(영어 소문자를 추천한다)
	public final static String SCRIPT_NAME_delete2 = "dndelete2.sh";

	// b.append 연동 부분
	public static boolean instantExec_delete2(Context context, String command) {
		try {
			runSuCommand_delete2(context, command.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 복사(cat) 기능 연동 부분
	public static String dumpFile_delete2(String filename) {
		String line_delete2 = "";
		try {
			Process ifc_delete2 = Runtime.getRuntime().exec("cat " + filename);
			BufferedReader bis_delete2 = new BufferedReader(
					new InputStreamReader(ifc_delete2.getInputStream()));
			line_delete2 = bis_delete2.readLine();
			ifc_delete2.destroy();

		} catch (java.io.IOException e) {
			return new String("");
		}

		return line_delete2;
	}

	// b.append 연동부분
	public static Process runSuCommandAsync_delete2(Context context,
			String command) throws IOException {
		DataOutputStream fout_delete2 = new DataOutputStream(
				context.openFileOutput(SCRIPT_NAME_delete2, 0));
		fout_delete2.writeBytes(command);
		fout_delete2.close();

		String[] args_delete2 = new String[] {
				"su",
				"-c",
				". " + context.getFilesDir().getAbsolutePath() + "/"
						+ SCRIPT_NAME_delete2 };
		Process proc_delete = Runtime.getRuntime().exec(args_delete2);
		return proc_delete;
	}

	// 시스템 마운트 부분
	public static void remountSystem_delete2(Context c) {
		try {
			runSuCommand_delete2(c, "mount -orw,remount /system ; ");
		} catch (Exception d) {
		}
	}

	// SU 명령어 체크
	public static int runSuCommand_delete2(Context context, String command)
			throws IOException, InterruptedException {
		return runSuCommandAsync_delete2(context, command).waitFor();
	}
}
