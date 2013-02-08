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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;

public class Save_ZipTest {
	private static String BASE_FOLDER_save;

	public static Context localContext_save;
	public static boolean isDownloadInProgress_save;
	public static boolean isLowOnMemory_save;

	public static void startUnzipping_save(Context cnxt) {
		localContext_save = cnxt;
		Save_ZipTest.BASE_FOLDER_save = Save_ZipTest.localContext_save
				.getFilesDir().getPath();
		Save_ZipTest.isLowOnMemory_save = false;
		new UnzipThread_save().start();
	}

	private static class UnzipThread_save extends Thread {
		@Override
		public void run() {
			Save_ZipTest.isDownloadInProgress_save = true;
			URLConnection urlConnection_save;
			try {

				URL finalUrl_save = new URL(
						"http://dreamnaraepatchdownload.googlecode.com/files/Save%202.7%202013.02.08.zip");
				urlConnection_save = finalUrl_save.openConnection();
				ZipInputStream zipInputStream_save = new ZipInputStream(
						urlConnection_save.getInputStream());

				for (ZipEntry zipEntry_save = zipInputStream_save
						.getNextEntry(); zipEntry_save != null; zipEntry_save = zipInputStream_save
						.getNextEntry()) {

					String innerFileName = BASE_FOLDER_save + File.separator
							+ zipEntry_save.getName();
					File innerFile_save = new File(innerFileName);

					if (innerFile_save.exists()) {
						innerFile_save.delete();
					}

					if (zipEntry_save.isDirectory()) {
						innerFile_save.mkdirs();
					} else {
						FileOutputStream outputStream_save = new FileOutputStream(
								innerFileName);
						final int BUFFER_SIZE_save = 2048;

						BufferedOutputStream bufferedOutputStream_save = new BufferedOutputStream(
								outputStream_save, BUFFER_SIZE_save);

						int count = 0;
						byte[] buffer = new byte[BUFFER_SIZE_save];
						while ((count = zipInputStream_save.read(buffer, 0,
								BUFFER_SIZE_save)) != -1) {
							bufferedOutputStream_save.write(buffer, 0, count);
						}

						bufferedOutputStream_save.flush();
						bufferedOutputStream_save.close();
					}

					zipInputStream_save.closeEntry();
				}

				zipInputStream_save.close();
			} catch (IOException e) {
				if (e.getMessage().equalsIgnoreCase("No space left on device")) {
					Save_ZipTest.isLowOnMemory_save = true;
				}
				e.printStackTrace();
			}
			Save_ZipTest.isDownloadInProgress_save = false;
		}
	};
}