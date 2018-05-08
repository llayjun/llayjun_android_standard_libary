package com.tongju.pay.aliay;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.example.common.utils.HandlerUtil;
import com.tongju.pay.aliay.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 * Created by zhangyinlei on 2018/3/6 0006.
 */

public class AlipayUtil {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016080900196649";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public final static String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoScE2TONpqEpbbrqIYSu5RPgWb2PVnptlSwDf7owfHhyGpWG4EPYbOBOSppTUrPTrxbyqI/qDjgnLs7shATFEBaPNpWO7LHt0JJ4HsLuqGaOixEBamNFcw+8tOjA16Gt6WETs62W3+enB8ReY43+9thVQNbr1s+I6f+/cgYi2A42uxAfamRRP2IVGh5+vQXHHU2rRgizCWtscbW3q0gjw1LZAaxkR5xDKXTGxozRW2HwU9vftx1JymFuAOZBriMMvS/2gQFkN1j2s8V/q8prVR+q1LTpnFGvAC8HveADrF/LKfyje2djIYh9CndYsoRfWzCXzYewFzLDptg3KBEilAgMBAAECggEAeyiO6AhZHcf1svhUNt62Ov5IeVLHw1wx6W18lGVl0pgc1EVQQH7ZPY5KdLbIkOSK0cmCixmNip12ecdtGXhf76IZALUbSa4CIztxE7u5e4EMCmCJoEPaZINYFMdS5QDVN1JGDwUvCfcxpaelV1SW9eW9r1fDHXGqDJn4lm2N+XVr9IaXHIt4Yyh7H2A+IoGJwNbgs4BTnJPWJI/Z1ZEqcwL1CNvmAXNn1mBQ7pqHrw7+7FJ2Mp/jbAuAYXGSLkqFzBua381PN8Bxmke5xV+kNVgvyxlRrPeBeyrk8O2UlzHEZxIjcUw2RmNwYevTTQnI6rTtweCIKvny2UdMr4xvAQKBgQDcIy4/aEhMBohjXXZ4HeHTiqk9xPdT5+SbHfE7n405Mm6VNfd4rMoUlT06CSSForOZHfJGICz6EzhD0h2Ck55W2AeZlSBPS3v9vbT1D/PHnrxhi/0pmrlajrimuD0zaGlqLAdaigSRTvvPMW4M157FIrPqMcJKAIENpKoCelULxQKBgQDDtDN01/i6oSojYmSi9933qRE0OHyBJEtB5x4KMNI5hcVGsWUVGU061u//Q773agMvcfITCXfwZbNmGuvboN79W+dBfcOmdB3MzKxuX1PKYSW4mtx0Ns/3zbwFUnAS/Z1RHVbAyaRg9pPeu5pnK3SqG9YWga2VxK2oWu4ks+23YQKBgHMxXaQEd8CAsqH8aF/WkzNVHdaa2Slo8TFSa8ddaFMD1uXxhXv6PwyiQ5W47wmW6BA5oWucrqx1nj2vF44anQKorOFrLasQvuM6gBS0skZFBCKvVFyPAGsov+tOAYiDHJZYT8jK7DT1dcMoBIRn2EckL61fOGHs/ZscDXKafSJZAoGAUlTWLPrK/iDdLGOxeMoUTKOIxFJbNq1BJ+mGPr1JQ7/ePZ5Vqymozu8rqlUi2s1zp5slPxMFrVHrxjHfNdeY2De4s1moXLeO7eDRTeG2y3ZROzeTNST2GwcnaYnD8olVjJFuNM7f45b4oISGMY8blpD9YRyPHWnPsPsIItK7WCECgYEAnQLHaoE9vDhBtOJ4JEAKtWb5tvb2tJ4NdOEJWhlRHcvELDRgbO+O7PB6BQmFjj1mVvLhhfr9aiHzC3+YnLDYInpnn6zQMh98M10ePwp115oego2f7ouu+5lNCOg2KmSGZiQEwr7ZmOSvE8Jei3yEd+k1rhk+dooq/0Nz5tli/uM=";

    /**
     * 配置支付宝信息，此处应该在后台返回
     * <p>
     * /**
     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     * <p>
     * orderInfo的获取必须来自服务端；
     */
    public static String getAlipayInfo() {
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, RSA2_PRIVATE, rsa2);
        return orderParam + "&" + sign;
    }

    /**
     * 支付宝支付
     */
    public static void alipayUtil(Activity activity, String orderInfo, OnAlipayCallBack onAlipayCallBack) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                // 同步返回需要验证的信息
                PayResult payResult = new PayResult(result);
                // 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                HandlerUtil.getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        String resultInfo = payResult.getResult();
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            onAlipayCallBack.onAlipayCallBackSuccess(resultInfo);
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        } else {
                            onAlipayCallBack.onAlipayCallBackFailure(resultInfo);
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        }
                    }
                });
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付结果回调
     */
    public interface OnAlipayCallBack {
        void onAlipayCallBackSuccess(String pay);

        void onAlipayCallBackFailure(String pay);
    }

}
