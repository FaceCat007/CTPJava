#include "ctp_IFCCTP.h"
#include <stdio.h>
#include <tchar.h>
#include <windows.h>
#include <stdlib.h>
#include <iostream>
#include <string>
#include <io.h>
using namespace std;

char* G2U(const char* gb2312)
{
	int len = MultiByteToWideChar(CP_ACP, 0, gb2312, -1, 0, 0);
	wchar_t* wstr = new wchar_t[len + 1];
	memset(wstr, 0, len + 1);
	MultiByteToWideChar(CP_ACP, 0, gb2312, -1, wstr, len);
	len = WideCharToMultiByte(CP_UTF8, 0, wstr, -1, 0, 0, 0, 0);
	char* str = new char[len + 1];
	memset(str, 0, len + 1);
	WideCharToMultiByte(CP_UTF8, 0, wstr, -1, str, len, 0, 0);
	if(wstr) delete[] wstr;
	return str;
}

typedef int(*funcAskClose)(int, int, char*, char*, double, int, char, char*);
typedef int(*funcAskCloseToday)(int, int, char*, char*, double, int, char, char*);
typedef int(*funcAskOpen)(int, int, char*, char*, double, int, char, char*);
typedef int(*funcBidClose)(int, int, char*, char*, double, int, char, char*);
typedef int(*funcBidCloseToday)(int, int, char*, char*, double, int, char, char*);
typedef int(*funcBidOpen)(int, int, char*, char*, double, int, char, char*);
typedef int(*funcCancelOrder)(int, int, char*, char*, char*);
typedef int(*funcCreate)(void);
typedef int(*funcGenerateReqID)(int);
typedef int(*funcGetAccountData)(int, char*);
typedef int(*funcGetBrokerID)(int, char*);
typedef int(*funcGetCommissionRate)(int, char *, char*);
typedef int(*funcGetDepthMarketData)(int, char*);
typedef int(*funcGetInstrumentsData)(int, char*);
typedef int(*funcGetInvestorID)(int, char*);
typedef int(*funcGetMarginRate)(int, char *, char*);
typedef int(*funcGetPositionData)(int, char*);
typedef int(*funcGetPositionDetailData)(int, char*);
typedef int(*funcGetOrderInfo)(int, char*);
typedef int(*funcGetOrderInfos)(int, char*);
typedef int(*funcGetPassword)(int, char*);
typedef int(*funcGetSessionID)(int);
typedef int(*funcGetTradeRecord)(int, char*);
typedef int(*funcGetTradeRecords)(int, char*);
typedef int(*funcGetTradingDate)(int, char*);
typedef int(*funcGetTradingTime)(int , char *);
typedef int(*funcHasNewDatas)(int);
typedef int(*funcIsClearanced)(int);
typedef int(*funcIsClearanceTime)(int);
typedef int(*funcIsDataOk)(int);
typedef int(*funcIsMdLogined)(int);
typedef int(*funcIsTdLogined)(int);
typedef int(*funcIsTradingTime)(int);
typedef int(*funcIsTradingTimeExact)(int, char*);
typedef int(*funcReqCommissionRate)(int, char*, int);
typedef int(*funcReqQrySettlementInfoConfirm)(int, int);
typedef int(*funcReqQrySettlementInfo)(int, int, char*);
typedef int(*funcReqMarginRate)(int, char*, int);
typedef int(*funcStart)(int, int, char *, char*, char*, char*, char*, char*, char*);
typedef int(*funcSubMarketDatas)(int, int, char*);
typedef int(*funcUnSubMarketDatas)(int, int, char*);

//////////////////////////////////////
//函数指针成员

HINSTANCE hdll = 0;
funcAskClose m_funcAskClose;
funcAskCloseToday m_funcAskCloseToday;
funcAskOpen m_funcAskOpen;
funcBidClose m_funcBidClose;
funcBidCloseToday m_funcBidCloseToday;
funcBidOpen m_funcBidOpen;
funcCancelOrder m_funcCancelOrder;
funcCreate m_funcCreate;
funcGenerateReqID m_funcGenerateReqID;
funcGetAccountData m_funcGetAccountData;
funcGetBrokerID m_funcGetBrokerID;
funcGetCommissionRate m_funcGetCommissionRate;
funcGetDepthMarketData m_funcGetDepthMarketData;
funcGetInstrumentsData m_funcGetInstrumentsData;
funcGetInvestorID m_funcGetInvestorID;
funcGetMarginRate m_funcGetMarginRate;
funcGetPositionData m_funcGetPositionData;
funcGetPositionDetailData m_funcGetPositionDetailData;
funcGetOrderInfo m_funcGetOrderInfo;
funcGetOrderInfos m_funcGetOrderInfos;
funcGetPassword m_funcGetPassword;
funcGetSessionID m_funcGetSessionID;
funcGetTradeRecord m_funcGetTradeRecord;
funcGetTradeRecords m_funcGetTradeRecords;
funcGetTradingDate m_funcGetTradingDate;
funcGetTradingTime m_funcGetTradingTime;
funcHasNewDatas m_funcHasNewDatas;
funcIsClearanced m_funcIsClearanced;
funcIsClearanceTime m_funcIsClearanceTime;
funcIsDataOk m_funcIsDataOk;
funcIsMdLogined m_funcIsMdLogined;
funcIsTdLogined m_funcIsTdLogined;
funcIsTradingTime m_funcIsTradingTime;
funcIsTradingTimeExact m_funcIsTradingTimeExact;
funcReqCommissionRate m_funcReqCommissionRate;
funcReqQrySettlementInfoConfirm m_funcReqQrySettlementInfoConfirm;
funcReqQrySettlementInfo m_funcReqQrySettlementInfo;
funcReqMarginRate m_funcReqMarginRate;
funcStart m_funcStart;
funcSubMarketDatas m_funcSubMarketDatas;
funcUnSubMarketDatas m_funcUnSubMarketDatas;

void InitFunctionPtr()
{
  TCHAR chCurDir[MAX_PATH] = {0};  
  GetCurrentDirectory(MAX_PATH, chCurDir);
  TCHAR* fileDef =_T("\\iCTP.dll");
  int len_a = lstrlen(chCurDir);
  int len_b = lstrlen(fileDef);
  TCHAR* absDllPath = new TCHAR[len_a + len_b + 1];
  absDllPath[0] = _T('\0');
  lstrcat(absDllPath, chCurDir);
  lstrcat(absDllPath, fileDef);
  hdll = ::LoadLibraryEx(absDllPath, NULL, LOAD_WITH_ALTERED_SEARCH_PATH);
  m_funcAskClose = (funcAskClose)GetProcAddress(hdll, "askClose");
	m_funcAskCloseToday = (funcAskCloseToday)GetProcAddress(hdll, "askCloseToday");
	m_funcAskOpen = (funcAskOpen)GetProcAddress(hdll, "askOpen");
	m_funcBidClose = (funcBidClose)GetProcAddress(hdll, "bidClose");
	m_funcBidCloseToday = (funcBidCloseToday)GetProcAddress(hdll, "bidCloseToday");
	m_funcBidOpen = (funcBidOpen)GetProcAddress(hdll, "bidOpen");
	m_funcCancelOrder = (funcCancelOrder)GetProcAddress(hdll, "cancelOrder");
	m_funcCreate = (funcCreate)GetProcAddress(hdll, "create");
	m_funcGenerateReqID = (funcGenerateReqID)GetProcAddress(hdll, "generateReqID");
	m_funcGetAccountData = (funcGetAccountData)GetProcAddress(hdll, "getAccountData");
	m_funcGetBrokerID = (funcGetBrokerID)GetProcAddress(hdll, "getBrokerID");
	m_funcGetCommissionRate = (funcGetCommissionRate)GetProcAddress(hdll, "getCommissionRate");
	m_funcGetDepthMarketData = (funcGetDepthMarketData)GetProcAddress(hdll, "getDepthMarketData");
	m_funcGetInstrumentsData = (funcGetInstrumentsData)GetProcAddress(hdll, "getInstrumentsData");
	m_funcGetInvestorID = (funcGetInvestorID)GetProcAddress(hdll, "getInvestorID");
	m_funcGetMarginRate = (funcGetMarginRate)GetProcAddress(hdll, "getMarginRate");
	m_funcGetPositionData = (funcGetPositionData)GetProcAddress(hdll, "getPositionData");
	m_funcGetPositionDetailData = (funcGetPositionDetailData)GetProcAddress(hdll, "getPositionDetailData");
	m_funcGetOrderInfo = (funcGetOrderInfo)GetProcAddress(hdll, "getOrderInfo");
	m_funcGetOrderInfos = (funcGetOrderInfos)GetProcAddress(hdll, "getOrderInfos");
	m_funcGetPassword = (funcGetPassword)GetProcAddress(hdll, "getPassword");
	m_funcGetSessionID = (funcGetSessionID)GetProcAddress(hdll, "getSessionID");
	m_funcGetTradeRecord = (funcGetTradeRecord)GetProcAddress(hdll, "getTradeRecord");
	m_funcGetTradeRecords = (funcGetTradeRecords)GetProcAddress(hdll, "getTradeRecords");
	m_funcGetTradingDate = (funcGetTradingDate)GetProcAddress(hdll, "getTradingDate");
	m_funcGetTradingTime = (funcGetTradingTime)GetProcAddress(hdll, "getTradingTime");
	m_funcHasNewDatas = (funcHasNewDatas)GetProcAddress(hdll, "hasNewDatas");
	m_funcIsClearanced = (funcIsClearanced)GetProcAddress(hdll, "isClearanced");
	m_funcIsClearanceTime = (funcIsClearanceTime)GetProcAddress(hdll, "isClearanceTime");
	m_funcIsDataOk = (funcIsDataOk)GetProcAddress(hdll, "isDataOk");
	m_funcIsMdLogined = (funcIsMdLogined)GetProcAddress(hdll, "isMdLogined");
	m_funcIsTdLogined = (funcIsTdLogined)GetProcAddress(hdll, "isTdLogined");
	m_funcIsTradingTime = (funcIsTradingTime)GetProcAddress(hdll, "isTradingTime");
	m_funcIsTradingTimeExact = (funcIsTradingTimeExact)GetProcAddress(hdll, "isTradingTimeExact");
	m_funcReqCommissionRate = (funcReqCommissionRate)GetProcAddress(hdll, "reqCommissionRate");
	m_funcReqQrySettlementInfoConfirm = (funcReqQrySettlementInfoConfirm)GetProcAddress(hdll, "reqQrySettlementInfoConfirm");
	m_funcReqQrySettlementInfo = (funcReqQrySettlementInfo)GetProcAddress(hdll, "reqQrySettlementInfo");
	m_funcReqMarginRate = (funcReqMarginRate)GetProcAddress(hdll, "reqMarginRate");
	m_funcStart = (funcStart)GetProcAddress(hdll, "start");
	m_funcSubMarketDatas = (funcSubMarketDatas)GetProcAddress(hdll, "subMarketDatas");
	m_funcUnSubMarketDatas = (funcUnSubMarketDatas)GetProcAddress(hdll, "unSubMarketDatas");
}

void JstringToString(char* dest, JNIEnv* env, jstring jstr)
{
	char* rtn = 0;
	jclass clsstring = env->FindClass("java/lang/String");
	jstring strencode = env->NewStringUTF("utf-8");
	jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
	jbyteArray barr= (jbyteArray)env->CallObjectMethod(jstr, mid, strencode);
	jsize alen = env->GetArrayLength(barr);
	jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
	if (alen > 0)
	{
		rtn = new char[alen + 1];
		memcpy(rtn, ba, alen);
		rtn[alen] = 0;
	}
	env->ReleaseByteArrayElements(barr, ba, 0);
	strcpy(dest, rtn);
	delete rtn;
	rtn = 0;
}

void StringToJstring(jstring dest, JNIEnv* env, const char* pat)
{
	jclass strClass = env->FindClass("Ljava/lang/String;");
	jmethodID ctorID = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
	jbyteArray bytes = env->NewByteArray((long)strlen(pat));
	env->SetByteArrayRegion(bytes, 0, (long)strlen(pat), (jbyte*)pat);
	jstring encoding = env->NewStringUTF("utf-8");
	dest = (jstring)env->NewObject(strClass, ctorID, bytes, encoding);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_askClose
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcode, jstring jExchangeID, jdouble price, jint qty, jchar timeCondition, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char code[100] = {0};
	char orderRef[100] = {0};
	char exchangeID[100] = {0};
	JstringToString(code, env, jcode);
	JstringToString(orderRef, env, jorderRef);
	JstringToString(exchangeID, env, jExchangeID);
	return (jint)m_funcAskClose(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_askCloseToday
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcode, jstring jExchangeID, jdouble price, jint qty, jchar timeCondition, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char code[100] = {0};
	char orderRef[100] = {0};
	char exchangeID[100] = {0};
	JstringToString(code, env, jcode);
	JstringToString(orderRef, env, jorderRef);
	JstringToString(exchangeID, env, jExchangeID);
	return (jint)m_funcAskCloseToday(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_askOpen
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcode, jstring jExchangeID, jdouble price, jint qty, jchar timeCondition, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char code[100] = {0};
	char orderRef[100] = {0};
	char exchangeID[100] = {0};
	JstringToString(code, env, jcode);
	JstringToString(orderRef, env, jorderRef);
	JstringToString(exchangeID, env, jExchangeID);
	return (jint)m_funcAskOpen(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_bidClose
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcode, jstring jExchangeID, jdouble price, jint qty, jchar timeCondition, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char code[100] = {0};
	char orderRef[100] = {0};
	char exchangeID[100] = {0};
	JstringToString(code, env, jcode);
	JstringToString(orderRef, env, jorderRef);
	JstringToString(exchangeID, env, jExchangeID);
	return (jint)m_funcBidClose(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_bidCloseToday
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcode, jstring jExchangeID, jdouble price, jint qty, jchar timeCondition, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char code[100] = {0};
	char orderRef[100] = {0};
	char exchangeID[100] = {0};
	JstringToString(code, env, jcode);
	JstringToString(orderRef, env, jorderRef);
	JstringToString(exchangeID, env, jExchangeID);
	return (jint)m_funcBidCloseToday(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_bidOpen
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcode, jstring jExchangeID, jdouble price, jint qty, jchar timeCondition, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char code[100] = {0};
	char orderRef[100] = {0};
	char exchangeID[100] = {0};
	JstringToString(code, env, jcode);
	JstringToString(orderRef, env, jorderRef);
	JstringToString(exchangeID, env, jExchangeID);
	return (jint)m_funcBidOpen(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_cancelOrder
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jexchangeID, jstring jorderSysID, jstring jorderRef)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char exchangeID[100] = {0};
	char orderSysID[100] = {0};
	char orderRef[100] = {0};
	JstringToString(exchangeID, env, jexchangeID);
	JstringToString(orderSysID, env, jorderSysID);
	JstringToString(orderRef, env, jorderRef);
	return (jint)m_funcCancelOrder(ctpID, requestID, exchangeID, orderSysID, orderRef);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_create
  (JNIEnv *env, jobject self)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	return (jint)m_funcCreate();
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_generateReqID
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcGenerateReqID(ctpID);
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getAccountData
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetAccountData(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getBrokerID
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char data[1024] = {0};
	int ret = m_funcGetBrokerID(ctpID, data);
	if(ret > 0)
	{
		return (env)->NewStringUTF(G2U(data));
	}
	else
	{
		return (env)->NewStringUTF("0");
	}
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getCommissionRate
  (JNIEnv *env, jobject self, jint jctpID, jstring jcode)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char code[100] = {0};
	JstringToString(code, env, jcode);
	char *data = new char[1024 * 1024];
	int ret = m_funcGetCommissionRate(ctpID, code, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getDepthMarketData
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetDepthMarketData(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getInstrumentsData
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
    int ret = m_funcGetInstrumentsData(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getInvestorID
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char data[1024] = {0};
	int ret = m_funcGetInvestorID(ctpID, data);
	if(ret > 0)
	{
		return (env)->NewStringUTF(G2U(data));
	}
	else
	{
		return (env)->NewStringUTF("0");
	}
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getMarginRate
  (JNIEnv *env, jobject self, jint jctpID, jstring jcode)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char code[100] = {0};
	JstringToString(code, env, jcode);
	char *data = new char[1024 * 1024];
	int ret = m_funcGetMarginRate(ctpID, code, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getOrderInfo
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetOrderInfo(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getOrderInfos
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetOrderInfos(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getPassword
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetPassword(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getPositionData
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetPositionData(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getPositionDetailData
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetPositionDetailData(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_getSessionID
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcGetSessionID(ctpID);
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradeRecord
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetTradeRecord(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradeRecords
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char *data = new char[1024 * 1024];
	int ret = m_funcGetTradeRecords(ctpID, data);
	jstring result = (env)->NewStringUTF("0");
	if(ret > 0)
	{
		result = (env)->NewStringUTF(G2U(data));
	}
	delete[] data;
	data = 0;
	return result;
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradingDate
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char data[1024] = {0};
	int ret = m_funcGetTradingDate(ctpID, data);
	if(ret > 0)
	{
		return (env)->NewStringUTF(G2U(data));
	}
	else
	{
		return (env)->NewStringUTF("0");
	}
}

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradingTime
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char data[1024] = {0};
	int ret = m_funcGetTradingTime(ctpID, data);
	if(ret > 0)
	{
		return (env)->NewStringUTF(G2U(data));
	}
	else
	{
		return (env)->NewStringUTF("0");
	}
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isClearanced
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcIsClearanced(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isClearanceTime
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcIsClearanceTime(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_hasNewDatas
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcHasNewDatas(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isDataOk
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcIsDataOk(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isMdLogined
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcIsMdLogined(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isTdLogined
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcIsTdLogined(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isTradingTime
  (JNIEnv *env, jobject self, jint jctpID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	return (jint)m_funcIsTradingTime(ctpID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isTradingTimeExact
  (JNIEnv *env, jobject self, jint jctpID, jstring jcode)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char code[100] = {0};
	JstringToString(code, env, jcode);
	return (jint)m_funcIsTradingTimeExact(ctpID, code);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqCommissionRate
  (JNIEnv *env, jobject self, jint jctpID, jstring jcode, jint jrequestID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char code[100] = {0};
	JstringToString(code, env, jcode);
	int requestID = (int)jrequestID;
	return (jint)m_funcReqCommissionRate(ctpID, code, requestID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqMarginRate
  (JNIEnv *env, jobject self, jint jctpID, jstring jcode, jint jrequestID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	char code[100] = {0};
	JstringToString(code, env, jcode);
	int requestID = (int)jrequestID;
	return (jint)m_funcReqMarginRate(ctpID, code, requestID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQrySettlementInfoConfirm
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
    int requestID = (int)jrequestID;
	return (jint)m_funcReqQrySettlementInfoConfirm(ctpID, requestID);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQrySettlementInfo
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jtradingDate)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
    int requestID = (int)jrequestID;
	char code[100] = {0};
	JstringToString(code, env, jtradingDate);
	char tradingDate[100] = {0};
	JstringToString(tradingDate, env, jtradingDate);
	return (jint)m_funcReqQrySettlementInfo(ctpID, requestID, tradingDate);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_start
  (JNIEnv *env, jobject self, jint jctpID, jint jreqID, jstring appID, jstring authCode, jstring jmdserver, jstring jtdserver, jstring jbrokerID,
  jstring jname, jstring jpwd)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int reqID = (int)jreqID;
	char mdserver[100] = {0};
	char tdserver[100] = {0};
	char brokerID[100] = {0};
	char name[100] = {0};
	char pwd[100] = {0};
	char jAppID[100] = {0};
	char jAuthCode[100] = {0};
	JstringToString(jAppID, env, appID);
	JstringToString(jAuthCode, env, authCode);
	JstringToString(mdserver, env, jmdserver);
	JstringToString(tdserver, env, jtdserver);
	JstringToString(brokerID, env, jbrokerID);
	JstringToString(name, env, jname);
	JstringToString(pwd, env, jpwd);
	return (jint)m_funcStart(ctpID, reqID, jAppID, jAuthCode, mdserver, tdserver, brokerID, name, pwd);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_subMarketDatas
  (JNIEnv *env, jobject self, jint jctpID, jint jreqID, jstring jStockCode)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int reqID = (int)jreqID;
	char stockCode[102400] = {0};
	JstringToString(stockCode, env, jStockCode);
	return (jint)m_funcSubMarketDatas(jctpID, reqID, stockCode);
}

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_unSubMarketDatas
  (JNIEnv *env, jobject self, jint jctpID, jint jrequestID, jstring jcodes)
{
	if(!hdll)
	{
		InitFunctionPtr();
	}
	int ctpID = (int)jctpID;
	int requestID = (int)jrequestID;
	char codes[102400] = {0};
	JstringToString(codes, env, jcodes);
	return (jint)m_funcUnSubMarketDatas(ctpID, requestID, codes);
}
