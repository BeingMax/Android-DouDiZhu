package com.qp.lib.cmd.logon;

import java.security.NoSuchAlgorithmException;

import com.qp.lib.interface_ex.net.ICmd;
import com.qp.lib.utility.NetEncoding;

public class CMD_MB_LogonOtherPlatform implements ICmd {

	public int		wModuleID;
	public long		dwPlazaVersion;
	public byte		cbDeviceType;
	public byte		cbGender;
	public long		dwUserUin;
	public String	szNickName;
	public String	szCompellation;
	public String	szMachineID;
	public String	szMobilePhone;

	public CMD_MB_LogonOtherPlatform() {
	}

	public int WriteToByteArray(byte data[], int pos) {
		int nIndex = pos;
		NetEncoding.write2byte(data, wModuleID, nIndex);
		nIndex += 2;
		NetEncoding.write4byte(data, dwPlazaVersion, nIndex);
		nIndex += 4;
		data[nIndex++] = cbDeviceType;
		data[nIndex++] = cbGender;
		NetEncoding.write4byte(data, dwUserUin, nIndex);
		nIndex += 4;
		if (szNickName != null && !szNickName.equals(""))
			NetEncoding.stringToWcharUnicodeBytes(szNickName, data, nIndex);
		nIndex += 64;
		if (szCompellation != null && !szCompellation.equals(""))
			NetEncoding.stringToWcharUnicodeBytes(szCompellation, data, nIndex);
		nIndex += 32;
		String MD5Str = "";
		if (szMachineID != null && !szMachineID.equals("")) {
			try {
				MD5Str = NetEncoding.changeToMD5(szMachineID);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			NetEncoding.stringToWcharUnicodeBytes(MD5Str, data, nIndex);
		}
		nIndex += 66;
		if (szMobilePhone != null && !szMobilePhone.equals(""))
			NetEncoding.stringToWcharUnicodeBytes(szMobilePhone, data, nIndex);
		return (nIndex += 24) - pos;
	}

	public int ReadFromByteArray(byte data[], int pos) {
		return 0;
	}

	public String toString() {
		String szinfo = super.toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[ģ���ʶ:").append(wModuleID).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[�㳡�汾:").append(dwPlazaVersion).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[�豸����:").append(cbDeviceType).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[�û��Ա�:").append(cbGender).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[�û�Uin:").append(dwUserUin).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[�û��ǳ�:").append(szNickName).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[��ʵ����:").append(szNickName).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[������ʶ:").append(wModuleID).append("]").toString();
		szinfo = (new StringBuilder(String.valueOf(szinfo))).append("[�绰����:").append(szMobilePhone).append("]").toString();
		return szinfo;
	}
}
