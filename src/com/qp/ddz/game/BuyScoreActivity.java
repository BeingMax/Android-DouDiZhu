package com.qp.ddz.game;

import com.qp.ddz.GameActivity;
import com.qp.ddz.R;
import com.qp.ddz.R.layout;
import com.qp.ddz.define.GDF;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

   
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

/*
*
* ��������Դ����ϵ q344717871
* 
* */

public class BuyScoreActivity extends Activity {

	 
	// Ӧ�û���Ϸ���Զ����֧������(ÿ��֧���������ݲ�����ͬ)
	String orderId = "";
	// �û���ʶ
	String userId = "";
	// ֧����Ʒ����
	String goodsName = "10000�ֶ�";
	// ֧�����
	float price = 0.0f;
	// ֧��ʱ��
	String time = "";
	// ֧������
	String goodsDesc = "";
	// Ӧ�û���Ϸ�̷������˻ص��ӿڣ��޷������ɲ���д��
	String notifyUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_score);
		
		// ��ʼ��ͳ����(�������)
        PayConnect.getInstance("d275369741d9d5afbca9af87e9a3c143", "WAPS", this);
		
		TextView goodsNameView = (TextView) this.findViewById(R.id.goodsName);
		goodsNameView.setText(goodsName);
		
		final EditText amountView = (EditText) this.findViewById(R.id.amount);
		
		TextView detailsView = (TextView) this.findViewById(R.id.details);
		goodsDesc = "����" + goodsName;
		detailsView.setText(goodsDesc);
		
		userId = PayConnect.getInstance(this).getDeviceId(this);
		
		Button submitButton = (Button) this.findViewById(R.id.submitBtn);
		
		// ֧��SDK�汾��
		TextView sdkVersionView = (TextView) this.findViewById(R.id.sdkVersion);
		sdkVersionView.setText("SDK�汾��" + PayConnect.LIBRARY_VERSION_NUMBER);
		
		submitButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
//					// ��Ϸ���Զ���֧�������ţ���֤�ö����ŵ�Ψһ�ԣ�������ִ��֧������ʱ�Ž��иö����ŵ�����
			        orderId = System.currentTimeMillis() + "";
			        
					String amountStr = amountView.getText().toString();
					if(!"".equals(amountStr)){
						price = Float.valueOf(amountStr);
					}else{
						price = 0.0f;
					}
					
					PayConnect.getInstance(BuyScoreActivity.this).pay(BuyScoreActivity.this, 
																	orderId, 
																	userId, 
																	price, 
																	goodsName, 
																	goodsDesc, 
																	notifyUrl,
																	new MyPayResultListener());
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Button closeBtn = (Button) this.findViewById(R.id.closeBtn);
		closeBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) { 
				GameActivity.getGameActivityInstance().m_gameclient.close();
				GameActivity.getInstance().onShowScene(GDF.SCENE_SERVER);
			}
		});
		
	}
	
  
	
	 

	@Override
	protected void onDestroy() {
		//��ǰ�汾��finalize()��������
		PayConnect.getInstance(this).close();
		super.onDestroy();
	}
	
	/**
	 * �Զ���Listenerʵ��PaySuccessListener�����ڼ���֧���ɹ�
	 * @author Administrator
	 *
	 */
	private class MyPayResultListener implements PayResultListener{

		@Override
		public void onPayFinish(Context payViewContext, String orderId, int resultCode, String resultString, int payType,
				float amount, String goodsName) {
			// �ɸ���resultCode�����ж�
			if(resultCode == 0){
				Toast.makeText(getApplicationContext(), resultString + "��" + amount + "Ԫ", Toast.LENGTH_LONG).show();
				// ֧���ɹ�ʱ�رյ�ǰ֧������
				PayConnect.getInstance(BuyScoreActivity.this).closePayView(payViewContext);	
				
				// TODO �ڿͻ��˴���֧���ɹ��Ĳ���
				
				//���� ��ҵĻ���ͳ��----------------------------------
				int Score= (int) (GameActivity.getScore() +10000*amount);
				GameActivity.setScore(Score);// ���� 
				//------------------------------
				
				// δָ��notifyUrl������£����׳ɹ��󣬱��뷢�ͻ�ִ
				PayConnect.getInstance(BuyScoreActivity.this).confirm(orderId, payType);
			}else{
				Toast.makeText(getApplicationContext(), resultString, Toast.LENGTH_LONG).show();
			}
		}
	}
	
}
