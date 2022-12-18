#include "jni.h"
#include "jni_md.h"

#ifndef _Included_ctp_IFCCTP
#define _Included_ctp_IFCCTP
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_askClose
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jint, jchar, jstring);


JNIEXPORT jint JNICALL Java_ctp_IFCCTP_askCloseToday
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jint, jchar, jstring);


JNIEXPORT jint JNICALL Java_ctp_IFCCTP_askOpen
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jint, jchar, jstring);


JNIEXPORT jint JNICALL Java_ctp_IFCCTP_bidClose
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jint, jchar, jstring);


JNIEXPORT jint JNICALL Java_ctp_IFCCTP_bidCloseToday
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jint, jchar, jstring);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_bidOpen
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jdouble, jint, jchar, jstring);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_cancelOrder
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jstring);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_create
  (JNIEnv *, jobject);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_generateReqID
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_hasNewDatas
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isDataOk
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getAccountData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getBrokerID
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getCommissionRate
  (JNIEnv *, jobject, jint, jstring);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getDepthMarketData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getInstrumentsData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getInvestorID
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getMarginRate
  (JNIEnv *, jobject, jint, jstring);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getOrderInfo
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getOrderInfos
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getPassword
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getPositionData
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getPositionDetailData
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_getSessionID
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradeRecord
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradeRecords
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradingDate
  (JNIEnv *, jobject, jint);

JNIEXPORT jstring JNICALL Java_ctp_IFCCTP_getTradingTime
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isClearanced
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isClearanceTime
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isMdLogined
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isTdLogined
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isTradingTime
  (JNIEnv *, jobject, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_isTradingTimeExact
  (JNIEnv *, jobject, jint, jstring);


JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqCommissionRate
  (JNIEnv *, jobject, jint, jstring, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqInstrumentInfo
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqMarginRate
  (JNIEnv *, jobject, jint, jstring, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQryInvestorPosition
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQryInvestorPositionDetail
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQryOrderInfo
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQrySettlementInfoConfirm
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQryTradeInfo
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_reqQryTradingAccount
  (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_start
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jstring, jstring, jstring, jstring, jstring);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_subMarketDatas
  (JNIEnv *, jobject, jint, jint, jstring);

JNIEXPORT jint JNICALL Java_ctp_IFCCTP_unSubMarketDatas
  (JNIEnv *, jobject, jint, jint, jstring);

#ifdef __cplusplus
}
#endif
#endif
