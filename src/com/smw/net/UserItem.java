package com.smw.net;

 
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qp.ddz.GameActivity;
import com.qp.ddz.utility.CustomFaceManage;
import com.qp.ddz.R;

/**
*
* ��������Դ����ϵ q344717871
* 
*/


public class UserItem {
	public	int uid;//Ψһid 
	public String  nickname;//char nickname[32]; //�ǳ�
	public	int money;//���
	public  int rank;
	public  int bankscore;//���д��
	public int wincount;//Ӯ
	public int losecount;//
	public int escapecount;//����
	public String maxim;
	
	public void SetFaceCheckSum(int s){facechecksum=s;}
	public int GetFaceCheckSum(){return facechecksum;}
	private int facechecksum; 
	
	public Bitmap GetFaceImage(){
		
		//ûͷ����id�������ͷ��
		if(facechecksum==0){
			
			Resources r =  GameActivity.getGameActivityInstance().getResources();
			
			int x=uid%6;
			 
			switch (x)
			{
				case 0: return BitmapFactory.decodeResource(r,R.drawable.head_00   );
				case 1: return BitmapFactory.decodeResource(r,R.drawable.head_01   );
				case 2: return BitmapFactory.decodeResource(r,R.drawable.head_02   );
				case 3: return BitmapFactory.decodeResource(r,R.drawable.head_03   );
				case 4: return BitmapFactory.decodeResource(r,R.drawable.head_04   );
				case 5: return BitmapFactory.decodeResource(r,R.drawable.head_05   );
			}
			
			return null;//Ϊ0 ��ʾ ��ͷ��.
		}
		
		//���ͷ��
		if (  CustomFaceManage.getInstance() != null) {
			return CustomFaceManage.getInstance().getBitmap( Integer.toHexString(facechecksum), facechecksum);
		}
		
		
		return null;
	}
	
	public int TableID;
	public int ChairID;
	public int UserStatus; // 
	public int GetUserStatus(){return UserStatus;}
	
	public int GetGameID(){return uid;}
	public int GetGender(){return 1;}
	public int GetUserScore(){return money;}
	public String GetNickName(){return nickname;}
	public int GetUserWinCount(){return wincount;}
	public int GetUserLostCount(){return losecount;}
	//public abstract int GetTableID();

	//public abstract int GetChairID();
}
