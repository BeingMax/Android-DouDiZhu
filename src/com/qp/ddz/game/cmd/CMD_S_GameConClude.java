package com.qp.ddz.game.cmd;

 
import com.qp.lib.utility.NetEncoding;
import com.qp.ddz.define.GDF;
import com.smw.net.ICmd;


/**
 * ��Ϸ����
 * 
 */
public class CMD_S_GameConClude implements ICmd{
	public int cmd;//S_GameConClude
	// ���ֱ���
	public long lCellScore; // ��Ԫ����
	public long[] lGameScore = new long[GDF.GAME_PLAYER]; // ��Ϸ����
	// �����־
	public int nChunTian; // �����־
	public int nFanChunTian; // �����־
	// ը����Ϣ
	public int nBombCount; // ը������
	public int[] nEachBombCount = new int[GDF.GAME_PLAYER]; // ը������
	// ��Ϸ��Ϣ
	public int nBankerScore; // �з���Ŀ
	public int[] nCardCount = new int[GDF.GAME_PLAYER]; // �˿���Ŀ
	public int[] nHandCardData = new int[GDF.FULL_COUNT]; // �˿��б�

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		
		lCellScore = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		for (int i = 0; i < GDF.GAME_PLAYER; i++) {
			lGameScore[i] = NetEncoding.read8Byte(data, nIndex);
			nIndex += 8;
		}

		nChunTian = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		nFanChunTian = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		nBombCount = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		for (int i = 0; i < GDF.GAME_PLAYER; i++) {
			nEachBombCount[i] = (data[nIndex] < 0
					? (data[nIndex] + 256)
					: data[nIndex]);
			nIndex++;
		}
		nBankerScore = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		int nCount = 0;
		for (int i = 0; i < GDF.GAME_PLAYER; i++) {
			nCardCount[i] = (data[nIndex] < 0
					? (data[nIndex] + 256)
					: data[nIndex]);
			nIndex++;
			nCount += nCardCount[i];
		}
		for (int i = 0; i < GDF.FULL_COUNT; i++) {
			nHandCardData[i] = (data[nIndex] < 0
					? (data[nIndex] + 256)
					: data[nIndex]);
			nIndex++;
		}
		return nIndex - pos;
	}

	@Override
	public int WriteToByteArray(byte[] arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
