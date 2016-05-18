package com.qp.ddz.scene.shop.adapter;
 
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qp.ddz.GameActivity;
import com.qp.lib.cmd.CMD;
import com.qp.ddz.R;
import com.smw.cmd.plazz.MSG_C2S_SHOP_BUY;
//
//import com.nd.commplatform.NdCommplatform;
//import com.nd.commplatform.NdErrorCode;
//import com.nd.commplatform.NdMiscCallbackListener;
//import com.nd.commplatform.entry.NdBuyInfo;
//import com.nd.commplatform.entry.NdProductInfo;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

public class ProductAdapter extends BaseAdapter implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String	TAG	= "GamePropAdapter";
	List<Product>			ProductItem;
	HashMap<String, Bitmap>		ImageHashMap;

	 
	public class GamePropItemView {
		ImageView	img_pic;
		TextView	txt_name;
		TextView	txt_describe;
		ImageButton	bt_buy;
	}

	HashMap<String, Bitmap>	m_GamePropsImageList;

	public ProductAdapter(List< Product > listitem) {

	}

	public ProductAdapter(List<Product> productInfoItem, HashMap<String, Bitmap> imageHashMap) {
		this.ProductItem = productInfoItem;
		ImageHashMap = imageHashMap;
	}

	@Override
	public int getCount() {
		if (ProductItem != null)
			return ProductItem.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (ProductItem != null && ProductItem.size() > 0)
			return ProductItem.get(ProductItem.size() - position - 1);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final GamePropItemView itemview;
		if (convertView == null) {
			itemview = new GamePropItemView();
			convertView = LayoutInflater.from(GameActivity.getInstance()).inflate(R.layout.shop_item_view, null);
			itemview.img_pic = (ImageView) convertView.findViewById(R.id.shop_item_img);
			itemview.txt_name = (TextView) convertView.findViewById(R.id.shop_item_txt_name);
			itemview.txt_describe = (TextView) convertView.findViewById(R.id.shop_item_txt_price);
			itemview.bt_buy = (ImageButton) convertView.findViewById(R.id.shop_item_bt_buy);

			convertView.setTag(itemview);
		} else {
			itemview = (GamePropItemView) convertView.getTag();
		}

		Product info = ProductItem.get(ProductItem.size() - position - 1);
		Bitmap bitmap = ImageHashMap.get(info.getCheckSum());
		if (bitmap == null) {
			itemview.img_pic.setImageDrawable(GameActivity.getInstance().getResources().getDrawable(R.drawable.shop_qian));
		} else {
			itemview.img_pic.setImageBitmap(bitmap);
		}
		itemview.txt_name.setText(info.getProductName());
		itemview.txt_describe.setText(info.getDesc() + "");
		itemview.bt_buy.setTag(ProductItem.size() - position - 1);
		itemview.bt_buy.setOnClickListener(this);

		return convertView;
	}

	//����btn
	@Override
	public void onClick(View view) {

		int index = (Integer) view.getTag();

		 Product info = ProductItem.get(index);

	//	NdCommplatform nd = NdCommplatform.getInstance();
/*
		NdBuyInfo order = checkBuyInfo(info);
		if (order == null) {
			Toast.makeText(GameActivity.getInstance(), "��������ʧ��", Toast.LENGTH_SHORT).show();
			return;
		}
		if (SerialRecord.checkSerial(order.getSerial()) > -1) {
			Toast.makeText(GameActivity.getInstance(), "��¼����ʧ��", Toast.LENGTH_SHORT).show();
			return;
		}*/
	//	SerialItem item = new SerialItem();
	//	item.szUin = nd.getLoginUin();
	//	item.szProductName = order.getProductName();
	//	item.szSerial = order.getSerial();
	//	item.lRecordTime = System.currentTimeMillis();

		//GameActivity.onShowDialog("�̵�", "���͹�����Ϣ");
		 
		 //�¶���
		 MSG_C2S_SHOP_BUY o=new MSG_C2S_SHOP_BUY();
		o.cmd=CMD.C2S_SHOP_BUY;
		 o.goodsid=info.id;
		GameActivity.getGameActivityInstance().m_loginclient.send(o);
		 
		// ��ʱ���鿪���߼�¼�¶����ţ��Ա��ֻ��ͻ���֪ͨ�쳣��������˺�����ͨ����ֵ������ʱ�ڵõ�91����������ɹ�ȷ��֪ͨ������ҷ��ŵ���
		// ��Ʒ�ļ۸���ౣ��2λС��
	//	int aError = nd.ndUniPayAsyn(order, GameActivity.getInstance(), new myPayListener(item));
	//	if (aError != 0) {
	//		Toast.makeText(GameActivity.getInstance(), "���������Ʒ��Ϣ��ʽ����ȷ", Toast.LENGTH_SHORT).show();
	//	}
	}

//	private NdBuyInfo checkBuyInfo(NdProductInfo info) {// У����Ʒ��Ϣ
//		NdBuyInfo buyInfo = new NdBuyInfo();
//		String szSerial = makeSerial();
//		buyInfo.setSerial(szSerial);
//		buyInfo.setProductId(info.getProductId());
//		buyInfo.setProductName(info.getProductName());
//		buyInfo.setProductPrice(info.getSalePrice());
//		buyInfo.setCount(1);
//		buyInfo.setPayDescription("Android_Buy");
//		return buyInfo;
//	}

	private String makeSerial() {// ���ɶ�����
		UUID guid = UUID.randomUUID();
		String text = guid.toString();
		text = text.replace("-", "".trim());
		return text;
	}
}   
