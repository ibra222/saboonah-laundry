package com.ibrahimaboismail.sapoonaapp;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.ibrahimaboismail.sapoonaapp.Chat.ChatActivity;
import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Message.Message;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;

import java.util.List;

/**
 * Created by d_200 on 1/7/2018.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
              super.onMessageReceived(remoteMessage);

             try {
                 String messageContent = remoteMessage.getData().get("chat");
                 String room_id = remoteMessage.getData().get("room_id");
                 String user_id = remoteMessage.getData().get("user_id");
                 String user_name = remoteMessage.getData().get("user_name");
                 String messageType = remoteMessage.getData().get("messageType");
                 String time = remoteMessage.getData().get("time");

                 Message message = new Message();
                 message.setContent(messageContent);
                 message.setRoomId(Integer.valueOf(room_id));
                 message.setUserId(Integer.valueOf(user_id));
                 message.setUserName(user_name);
                 message.setType(Integer.valueOf(messageType));
                 message.setCreatedAt(time);

                 // check if the sender of message is current user or not
                 if (!(Integer.valueOf(user_id) == Session.newInstance().getUser().getId())) {

                     // check if app in background or not

                     if (isAppIsInBackground(this)) {
                         // app is in background show notification to user
                         sendmessage(message);
                     } else {
                         // app is forground and user see it now send broadcast to update chat
                         // you can send broadcast to do anything if you want !
                         Intent intent = new Intent("UpdateChateActivity");
                         intent.putExtra("msg", message);
                         sendBroadcast(intent);

                     }
                 }
             }catch (NumberFormatException e){
                 Log.d("dffgsdf", e.getMessage());
             }


          if (remoteMessage.getData().get("message") == null){

          }else {
              bigTextStyleNotification(remoteMessage.getData().get("message"));
          }

         //showNotification(remoteMessage.getData().get("message"));
         // notificationActions(remoteMessage.getData().get("message"));
            // headsUpNotification(remoteMessage.getData().get("message"));
         //  bigTextStyleNotification(remoteMessage.getData().get("message"));
           //   bigPictureStyleNotification(remoteMessage.getData().get("ddd"));
         //  inboxStyleNotification(remoteMessage.getData().get("message"));
         //  messageStyleNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {

        Intent i = new Intent(this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("message",message);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FCM Test")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_contact)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());
    }

    private void notificationActions(String message) {

        int NOTIFICATION_ID = 1;

        Intent i = new Intent(this,HomeActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
        builder.setContentTitle("Notification Actions");
        builder.setContentText(message);
        builder.setAutoCancel(true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);


        Intent buttonIntent = new Intent(getBaseContext(), HomeActivity.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);

        builder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void headsUpNotification(String message) {

        int NOTIFICATION_ID = 1;
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Heads Up Notification")
                        .setContentText("message")
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.journaldev.com/15126/swift-function"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent buttonIntent = new Intent(getBaseContext(), HomeActivity.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);

        builder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void bigTextStyleNotification(String message) {
        int NOTIFICATION_ID = 1;
        Intent i = new Intent(this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("message",message);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent buttonIntent = new Intent(getBaseContext(), HomeActivity.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
        builder.setContentTitle("Big Text Style");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        builder.setAutoCancel(true);
        builder.setSound(defaultSoundUri);
        builder.setContentIntent(pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);
        builder.addAction(android.R.drawable.ic_menu_send, "OPEN APP", pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void bigPictureStyleNotification(String message) {
        int NOTIFICATION_ID = 1;
        Intent i = new Intent(this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("ddd",message);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        Intent buttonIntent = new Intent(getBaseContext(), HomeActivity.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
        builder.setContentTitle("Big Picture Style");
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(pic));
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void inboxStyleNotification(String message) {
        int NOTIFICATION_ID = 1;
        Intent i = new Intent(this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
        builder.setStyle(new NotificationCompat.InboxStyle().addLine("Hello").addLine("Are you there?").addLine("How's your day?").setBigContentTitle("3 New Messages for you").setSummaryText("Inbox"));
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void messageStyleNotification(String message) {
        int NOTIFICATION_ID = 1;
        Intent i = new Intent(this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
        builder.setContentTitle("Messages");
        builder.setStyle(new NotificationCompat.MessagingStyle("Teacher").setConversationTitle("Q&A Group")
                .addMessage("This type of notification was introduced in Android N. Right?",0,"Student 1")
                .addMessage("Yes",0,null)
                .addMessage("The constructor is passed with the name of the current user. Right?",0,"Student 2")
                .addMessage("True",0,null));
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private boolean isAppIsInBackground(Context context){
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH){
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses){
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                    for (String activityProcess : processInfo.pkgList){
                        if (activityProcess.equals(context.getPackageName())){
                            isInBackground = false;
                        }
                    }
                }
            }
        }else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())){
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    private void sendmessage(Message message){
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("msg", message);
        intent.putExtra("room_id",message.getRoomId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.lol)
                .setContentTitle("SaboonahApp")
                .setContentText(message.getContent())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
