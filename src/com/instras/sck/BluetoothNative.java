package com.instras.sck;

import com.codename1.system.NativeInterface;

/**
 * A class to connect to Bluetooth native API on Android to make SPP connections
 * @author Nathan Stevens
 */
public interface BluetoothNative extends NativeInterface {
    public String getDevices();
    
    public boolean connect(String address);
    
    public void disconnect();
    
    public void sendData(String data);
    
    public String readData();
}
