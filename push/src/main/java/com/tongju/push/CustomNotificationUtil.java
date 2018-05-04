package com.tongju.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.alibaba.sdk.android.push.notification.CPushMessage;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by zhangyinlei on 2018/4/18 19:17
 */
public class CustomNotificationUtil {

    /**
     * 接受到对应消息后，消息的弹出处理
     */
    public static void buildNotification(Context context, CPushMessage message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message.getTitle())
                .setContentText(message.getContent())
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build();
        notification.contentIntent = buildClickContent(context, message);
        notification.deleteIntent = buildDeleteContent(context, message);
        notificationManager.notify(message.hashCode(), notification);
    }

    public static PendingIntent buildClickContent(Context context, CPushMessage message) {
        Intent clickIntent = new Intent();
        clickIntent.setAction("com.push.click.action");
        //添加其他数据
        clickIntent.putExtra("message key", message);//将message放入intent中，方便通知自建通知的点击事件
        return PendingIntent.getService(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent buildDeleteContent(Context context, CPushMessage message) {
        Intent deleteIntent = new Intent();
        deleteIntent.setAction("com.push.click.delete.action");
        //添加其他数据
        deleteIntent.putExtra("message key", message);//将message放入intent中，方便通知自建通知的点击事件
        return PendingIntent.getService(context, 0, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
