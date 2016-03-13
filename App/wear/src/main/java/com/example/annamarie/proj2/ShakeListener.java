package com.example.annamarie.proj2;

import android.os.Bundle;
import android.app.Activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.wearable.view.CardFrame;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.content.Context;
import java.lang.UnsupportedOperationException;
/**
 * Created by AnnaMarie on 3/3/2016.
 * Code courtesy of:  http://android.hlidskialf.com/blog/code/android-shake-detection-listener */

public class ShakeListener extends Activity implements SensorEventListener {
    private static final int FORCE_THRESHOLD = 350;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 500;
    private static final int SHAKE_DURATION = 1000;
    private static final int SHAKE_COUNT = 3;
    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    private float lastX = 0;
    private float lastY = 0;
    private float lastZ = 0;
    private float vibrateThreshold = 0;

    private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;
    private SensorManager mSensorMgr;
    private Sensor accelometer;
    private float mLastX = -1.0f, mLastY = -1.0f, mLastZ = -1.0f;
    private long mLastTime;
    private OnShakeListener mShakeListener;
    private Context mContext;
    private int mShakeCount = 0;
    private long mLastShake;
    private long mLastForce;

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public interface OnShakeListener {
        public void onShake();
    }

    public ShakeListener(Context context) {
        mContext = context;
        resume();
    }

    public void setOnShakeListener(OnShakeListener listener) {
        mShakeListener = listener;
    }

    public void resume() {
        mSensorMgr = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        accelometer = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mSensorMgr == null) {
            throw new UnsupportedOperationException("Sensors not supported");
        }
        boolean supported = mSensorMgr.registerListener(this, accelometer, SensorManager.SENSOR_DELAY_NORMAL);
        if (!supported) {
            mSensorMgr.unregisterListener(this, accelometer);
            throw new UnsupportedOperationException("Accelerometer not supported");
        }
    }

    public void pause() {
        if (mSensorMgr != null) {
            mSensorMgr.unregisterListener(this, accelometer);
            mSensorMgr = null;
        }
    }

    public void onAccuracyChanged(int sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
//        System.out.println(lastX);
////        System.out.println("");
//        System.out.println(lastY);
//        System.out.println(lastZ);
//        System.out.println(event.values[0]);
//        System.out.println(event.values[1]);
//        System.out.println(event.values[2]);
         deltaX = Math.abs(lastX - event.values[0]);
         deltaY = Math.abs(lastY - event.values[1]);
         deltaZ = Math.abs(lastZ - event.values[2]);
         boolean y = true;
        // if the change is below 2, it is just plain noise
        if (deltaX < 2)
            y = false;
        if (deltaY < 2)
            y = false;
        if (deltaZ < 2)
            y = false;
        if (event.values[0] == 0 || event.values[1] == 0 || event.values[2] == 0) {
            y =false;
        }
        if (event.values[0] == lastX || event.values[1] == lastY || event.values[2] == lastZ) {
            y =false;
        }
        System.out.println(y);
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
        if (mShakeListener != null && y) {
            mShakeListener.onShake();
        }

    }

}








