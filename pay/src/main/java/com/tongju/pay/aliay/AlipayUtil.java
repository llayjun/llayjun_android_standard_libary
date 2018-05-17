package com.tongju.pay.aliay;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.example.common.sutils.utils.MainHandlerUtil;
import com.tongju.pay.aliay.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 * Created by zhangyinlei on 2018/3/6 0006.
 */

public class AlipayUtil {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016091500514006";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public final static String RSA2_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDO7nvFmwmGw7rQeQMYS3LP7u6mVrq00548AREvWtXpqxALL1A9SA4Mkj5xPP4pNO+PA6Mqxa1tQigSHStJ0dwgOLh66oPnedleYCxWqkFCa5H7OFPJ06iXJx3TO3Ws3ePOW1TgTlN+zQCHA4Pt1grUWQKR5cKJBiQ0wVrr4ZAwnZ6gxfuYNbvx3vaZHa9JfQkAi0S9gmRoj+DbUI91VPYgQgvcXQEurtv3Sv1SJx2JbC4Ozd9Ckq2PqqFgxONpG1WjkbsK2dg63tq+VtQsLRaCAL269AP0JBgvWNTagcsEE9xQ4X2xgFI3uSoppCPH3dS4cnB7f6orYafC1bJFZnGfAgMBAAECggEAUxrT+nhZSxYwYKk45PjoEzoR/hu2wAh88/w5uprwbfeVaeiGgRX/Dm1tRm0IZeQskXMUhIj/LXH0RdIRIIR3szkUXcjxUHVPJufY5rVZoCMzbYEhpVyryIidIcgvak1yv91OKQdL4RDTtWVkBpB/qOhWb+GmTwF4aPd+XF0qW1s5Bbwnya3BaMVBb6FfG90rZgjhpToedGFXptSsi5b8LV6K19bA1cRbnZIUbX/XmSi/ao1KcSZeKDoWn4VeBR+3467f7jdJnyCbRcQdDkNLo/xd78oPtmaO5lrTk3GK/3yY8zwPi/i57VwXN8Tx0xZLsrMvDP4tVydFUM7BM8EZgQKBgQDphAbto/tQvOPr8bvgAU0TDeKBLZj0FAQ+8finKKmGVQiBc3fXNAy4GCtS3uGJEdwpQx9eP5gkl3ZG7lWj3D2j3xrf9cX55bJLRN8NbvbokRiGaQL88Dqu7D/OGiacQSaJlPXIYOi0VxifxseY3nUXFzM1kIkxteXMr0jv2SemLQKBgQDi2y26BDQWBIRbh8Nly+F0v+Js5gM8hblsm+D6gTxm4Ye9PIbteboXttb4a+p9qJSaoOrWTuozi8+FTjagFxTQZGEIjXWt3xy9ycZ+gcKcXNTmbvcZ/y6cpdLmPjSacU6BbDFmfcxQyOnzPRUYmzUV77EhFSnhm7jk9SnrU8pCewKBgBSEMw+h/qyjS+so+/a8ZocsCyCKXwvizlSxccVN/0b8JZkqCfdwbUWnafcH+gzO8ub1LMZdFALzA8NLoV6aNRg2u+e9cj14fue6D1bgzvSz+tGkXEzY1m8T9q8nAQltd/NSkR2f1hIyLMms71BxCxySPz+PiV5CuwP2ypO4IpGpAoGAC82/uVgSpbqicGjF//v1xS9exq/JJ1MF7OLHeAFVdTzy5SPLGXwl9Kns0Yw77UHiSZFp5rDAeuIhcBw7t2S8DoPKkLlbkCp1wmJHU7nIuH4UDdFkRhQDBt+R86z067xNi2pnv594DBQIKEHbYSZiQrvwCXqibTy/CBrBjZ8UfRUCgYAbY1tHU+Hxh9TE4U75ENSflcaEayP+uWSup7E5QOXv66sf8nijHhai01RVdFUOu4jxZqv0OGS6vU4ZRZbPdH3ajKPFwC1k05dGrWhf4wWwjGnf9YtkMggOmYGEVYJkSIWf24CsccmooX73+96HjCj8O8PZ5J4m2e8D42tULB0fhw==";


    public final static String ZHIFUBAO_GOGNYAO = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4H5lWvUW+WOlHhrR74NvUhaEMWFtLEB91dhTm/dUfMt19oPinz5eoQTt7Ms+Tcc8Yi7nQ8oplvFf5KtzNFz4SUmpSBHq6ZBSPDshsAj0HxmERtC6P/LV9PSYI2RUDLLNrKaN2zmy5WW3LUA7/UOqy5JsfbEVNcY6QLriTF33lzwE05U2bI1mnMRNBsIuLom1zy9h+TV2T/3iM5dzWIM2bEreafQK+h+8bpZUdfhzpTqqSDaUzQmrXHO7XryIL6dw1hUj+u41/Mkfe9gdTV0QUIm0L6tL9NmkI/NeNH00SCvnDxxFF0A/dg+0u8NKJQYDfJx0qUlp5v8bEMk0Nk9VHQIDAQAB";

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
                MainHandlerUtil.getMainHandler().post(new Runnable() {
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
