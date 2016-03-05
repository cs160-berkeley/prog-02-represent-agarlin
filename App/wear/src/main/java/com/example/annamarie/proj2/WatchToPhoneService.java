package com.example.annamarie.proj2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnnaMarie on 3/3/2016.
 */
public class WatchToPhoneService extends Service implements GoogleApiClient.ConnectionCallbacks {

    private static GoogleApiClient mWatchApiClient;
    private static List<Node> nodes = new ArrayList<>();

    @Override
    public void onCreate() {
        System.out.println("oncreate");
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mWatchApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(this)
                .build();
        //and actually connect it
        mWatchApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWatchApiClient.disconnect();
    }

    public void onViewClicked(View target) {
        if (mWatchApiClient == null)

            return;
        System.out.println("onview");
        final PendingResult<NodeApi.GetConnectedNodesResult> nodes = Wearable.NodeApi.getConnectedNodes(mWatchApiClient);
        nodes.setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult result) {
                final List<Node> nodes = result.getNodes();
                if (nodes != null) {
                    for (int i = 0; i < nodes.size(); i++) {
                        final Node node = nodes.get(i);

                        // You can just send a message
                        Wearable.MessageApi.sendMessage(mWatchApiClient, node.getId(), "/Senator Dianne Feinstein", null);

                        // or you may want to also check check for a result:
                        // final PendingResult<SendMessageResult> pendingSendMessageResult = Wearable.MessageApi.sendMessage(mGoogleApiClient, node.getId(), "/MESSAGE", null);
                        // pendingSendMessageResult.setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
                        //      public void onResult(SendMessageResult sendMessageResult) {
                        //          if (sendMessageResult.getStatus().getStatusCode()==WearableStatusCodes.SUCCESS) {
                        //              // do something is successed
                        //          }
                        //      }
                        // });
                    }
                }
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override //alternate method to connecting: no longer create this in a new thread, but as a callback
    public void onConnected(Bundle bundle) {
        Log.d("T", "in onconnected");
        Wearable.NodeApi.getConnectedNodes(mWatchApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        nodes = getConnectedNodesResult.getNodes();
                        System.out.println(nodes);
                        Log.d("T", "found nodes");
                        //when we find a connected node, we populate the list declared above
                        //finally, we can send a message
//                        System.out.println(Fragment_Pager.x);
                        if (Fragment_Pager.x == true) {

                            sendMessage("/SHAKE", Fragment_Pager.z);
                        } else {
                            sendMessage("/Senator Dianne Feinstein", "Senator Dianne Feinstein");
                        }
                        Log.d("T", "sent");
                    }
                });
    }

    @Override //we need this to implement GoogleApiClient.ConnectionsCallback
    public void onConnectionSuspended(int i) {}

    public static void sendMessage(final String path, final String text ) {
        System.out.println("in send message");
        System.out.println(nodes);
        for (Node node : nodes) {
            Wearable.MessageApi.sendMessage(
                    mWatchApiClient, node.getId(), path, text.getBytes());
        }
    }

}




