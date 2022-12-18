#include "jni.h"
#include "jni_md.h"

#ifndef _Included_facecat_ctp_IFaceCatCTP
#define _Included_facecat_ctp_IFaceCatCTP
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_AskClose
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jchar, jint, jstring);


JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_AskCloseToday
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jchar, jint, jstring);


JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_AskOpen
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jchar, jint, jstring);


JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_BidClose
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jchar, jint, jstring);


JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_BidCloseToday
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jchar, jint, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_BidOpen
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jchar, jint, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_CancelOrder
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_Create
  (JNIEnv *, jobject);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_GenerateReqID
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_HasNewDatas
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsDataOk
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetAccountData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetBrokerID
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetCommissionRate
  (JNIEnv *, jobject, jint, jstring);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetDepthMarketData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetInstrumentsData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetInvestorID
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetMarginRate
  (JNIEnv *, jobject, jint, jstring);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetOrderInfo
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetOrderInfos
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetPassword
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetPositionData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetPositionDetailData
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_GetSessionID
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetTradeRecord
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetTradeRecords
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetTradingDate
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_facecat_ctp_IFaceCatCTP_GetTradingTime
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsClearanced
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsClearanceTime
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsMdLogined
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsTdLogined
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsTradingTime
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_IsTradingTimeExact
  (JNIEnv *, jobject, jint, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_LoginGaia
  (JNIEnv *, jobject, jstring, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqCommissionRate
  (JNIEnv *, jobject, jint, jstring, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqInstrumentInfo
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqMarginRate
  (JNIEnv *, jobject, jint, jstring, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqQryInvestorPosition
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqQryInvestorPositionDetail
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqQryOrderInfo
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqQrySettlementInfoConfirm
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqQryTradeInfo
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_ReqQryTradingAccount
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_Start
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jstring, jstring, jstring, jstring, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_SubMarketDatas
  (JNIEnv *, jobject, jint, jint, jstring);

JNIEXPORT jint JNICALL Java_facecat_ctp_IFaceCatCTP_UnSubMarketDatas
  (JNIEnv *, jobject, jint, jint, jstring);

#ifdef __cplusplus
}
#endif
#endif
