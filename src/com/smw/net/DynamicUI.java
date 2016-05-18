package com.smw.net;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.LinearLayout;

import com.qp.ddz.GameActivity;
import com.qp.ddz.utility.HttpDownloadAsyncTask;
import com.qp.lib.help.SDCardHelp;
import com.qp.lib.utility.NetHelp;
import com.qp.lib.utility.Util;
import com.qp.ddz.define.GDF;
/**
*
* ��������Դ����ϵ q344717871
* 
*/


public class DynamicUI {

	public static String DYNAMIC_UI_PATH="/ui/";
	
	// �������� ����bg layoutIDΪ�������һ����id ��layout��id
	static public void LoadBG(String TAG, Activity ac, int layoutID) {
		Util.i(TAG, "== LoadBG ===");

		LinearLayout layout = (LinearLayout) ac.findViewById(layoutID);// ��Ҫ����linearlayout��idΪlayout
		if (layout == null) {
			Util.e(TAG, "LoadBG ���ز���!!");
			return;
		}
		// get
		SharedPreferences sp = ac.getSharedPreferences("shared_file", 0);
		boolean on = sp.getBoolean(TAG, false);
		String url = sp.getString(TAG + "url", "");

		if (on) {
			Util.i(TAG, "== On ===");
			// String test="http://www.qq.com/abcd/jk.jpg";
			int po = url.lastIndexOf("/");
			if (po != -1) {
				String filename = url.substring(po + 1);

				Bitmap bm = SDCardHelp.LoadImage(GDF.GAME_SD_PATH +  DYNAMIC_UI_PATH,
						filename);
				// byte st[]=SDCardHelp.LoadFile(GDF.GAME_SD_PATH+"/ui/",
				// filename);
				// תbmp
				// Bitmap bm =BitmapFactory.decodeByteArray( st , 0, st.length
				// );
				if (bm != null) {

					layout.setBackgroundDrawable(new BitmapDrawable(bm));

					// /if(!bm.isRecycled()) bm.recycle();

					Util.i(TAG, "== sd������ͼƬ ===" + filename);
					return;
				} else {

					Util.i(TAG, "== sd������ͼƬʧ�� ===" + filename);
					return;
				}
			} else {
				Util.i(TAG, "== url err ===" + url);
			}

		} else {
			Util.i(TAG, "== Off ===");

		}

		System.gc();
		System.out.println(Runtime.getRuntime().totalMemory());
	}

	static public byte[] LoadBG2(String TAG, Activity ac) {
		Util.i(TAG, "== LoadBG2 ===");

		// get
		SharedPreferences sp = ac.getSharedPreferences("shared_file", 0);
		boolean on = sp.getBoolean(TAG, false);
		String url = sp.getString(TAG + "url", "");

		if (!on) {
			Util.i(TAG, "== Off ===");
			return null;
		}

		Util.i(TAG, "== On ===");
		// String test="http://www.qq.com/abcd/jk.jpg";
		int po = url.lastIndexOf("/");
		if (po != -1) {
			String filename = url.substring(po + 1);

//			Bitmap bm = SDCardHelp.LoadImage(GDF.GAME_SD_PATH + "/ui/",
//					filename);
			 byte st[]=SDCardHelp.LoadFile(GDF.GAME_SD_PATH+DYNAMIC_UI_PATH, filename);
			// תbmp
			// Bitmap bm =BitmapFactory.decodeByteArray( st , 0, st.length );
			if (st != null) {

				// /if(!bm.isRecycled()) bm.recycle();

				Util.i(TAG, "== sd������ͼƬ ===" + filename);
				return st;
			} else {

				Util.i(TAG, "== sd������ͼƬʧ�� ===" + filename);
				return null;
			}
		} else {
			Util.i(TAG, "== url err ===" + url);
			return null;
		}

	}

	static public void SaveBG(Activity ac, String TAG, boolean on, String url) {
		SharedPreferences sp = ac.getSharedPreferences("shared_file", 0);
		sp.edit().putBoolean(TAG, on).commit();
		
		//ɾ��ԭ������Դ
//		String oulurl = sp.getString(TAG + "url", "");
//		int po = oulurl.lastIndexOf("/");
//		if (po != -1) {
//			String filename = url.substring(po + 1);//��Խ��!
//			SDCardHelp.rmFile(GDF.GAME_SD_PATH+DYNAMIC_UI_PATH+filename);
//		}
		 
		//��������Դ
		sp.edit().putString(TAG + "url", url).commit();

		if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) {
			// url�س� �ļ���
			int  po = url.lastIndexOf("/");
			if (po != -1) {
				String filename = url.substring(po + 1);

				// ����sdk��+����+ ·��
				String p = SDCardHelp.MakeFilePahtToSDCard(GDF.GAME_SD_PATH  +  DYNAMIC_UI_PATH );
				p = p + "/" + filename;

				// �Ѵ����� �Ͳ��������ˣ�
				if (SDCardHelp.fileExists(p)) {
					Util.i(TAG, "�Ѵ����� �Ͳ��������ˣ�" + p);
					return;
				}

				// ֻ��wifiʱ����.
				if (NetHelp.NetIsWifi(ac)) {

					// ��������
					HttpDownloadAsyncTask task = new HttpDownloadAsyncTask(url,
							GDF.GAME_SD_PATH+ DYNAMIC_UI_PATH );  
					task.execute("");
				}

			}

		}

	}

	static public void onDestroy(Activity ac, int layoutID) {
		LinearLayout layout = (LinearLayout) ac.findViewById(layoutID);
		// ����ʷ��ImageView ͼƬ����image_)�ͷ�
		BitmapDrawable bitmapDrawable = (BitmapDrawable) layout.getBackground();
		if (bitmapDrawable != null) {
			Bitmap hisBitmap = bitmapDrawable.getBitmap();

			if (hisBitmap!=null && !hisBitmap.isRecycled()  ) {
				//  hisBitmap.recycle();
				  hisBitmap = null;
			} 
			 
		}
	}

	static public void LoadBGM(String TAG){
			 
			
		// get bgm config
					SharedPreferences sp = GameActivity.getInstance().getSharedPreferences("shared_file", 0);
					boolean on = sp.getBoolean(TAG, false);
					String url = sp.getString(TAG+"url", "");

					 
					if (on)
					{
						Util.i(TAG, "== "+TAG+" On ===");
						// String test="http://www.qq.com/abcd/jk.jpg";
						int po = url.lastIndexOf("/");
						if (po != -1) {
							String filename = url.substring(po + 1);
							
							//no sd card
							 if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
							 {
								 Util.e(TAG," no sd card!!!");
								  
							 }
							String p=SDCardHelp.getSdCardPath()+"/"+GDF.GAME_SD_PATH +DynamicUI.DYNAMIC_UI_PATH;
							if(p.length()>1){
								Util.e(TAG,"   sd card!!! " + p );
								if(GameActivity.getOptionControl()!=null) GameActivity.getOptionControl().PlayBackGroundMusic(p +filename, true);
							}
							
							 
						}
					}
					else{
						if(GameActivity.getOptionControl()!=null) GameActivity.getOptionControl().StopBackGroundMusic() ;//PlayBackGroundMusic(R.raw.lgbg, true);
					}
	}
	
}
