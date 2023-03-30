package com.instras.sck;

import com.codename1.system.NativeLookup;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to connect to SCKTalk Device
 * @author Nathan
 */
public class SCKTalkDevice {
    // The Native bluetooth device
    private static BluetoothNative bluetoothNative;
    
    // The MAC address of bluetooth device
    private static String address;
    
    // specifies whether the motor talk device is connected
    private static boolean connected;
    
    // specify whether we are connecting to a SCK-300 or SCK-300P
    public static final int SCK_300 = 1;
    public static final int SCK_300P = 2;
    
    // The dummy text to return
    private String dummyData = "***DUMMY DATA***";

    // the currently set rpm
    private int setSpeed = 0;
    
    /**
     * The default constructor
     */
    public SCKTalkDevice() {}
    
    /**
     * Constructor that takes the address of the sck bluetooth adapter
     * @param address 
     */
    public SCKTalkDevice(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Method to connect to the sck device using Bluetooth SPP
     * 
     */
    public void connect() {
        bluetoothNative = (BluetoothNative)NativeLookup.create(BluetoothNative.class);
        
        if(bluetoothNative != null && bluetoothNative.isSupported()) {
            connected = bluetoothNative.connect(address);
            
            // send the get version command so we can check to see if we
            // are actually connect to an SCK
            if(connected) {
                sendCommand("GetVersion");
            }
        } else {
            connected = false;
        }
    }

    public void disConnect() {
        if(connected) {
            bluetoothNative.disconnect();
            connected = false;
        }
    }
    
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * This method will return an Array paired Bluetooth devices
     * 
     * @return 
     */
    public List<String> getDevices() {
        List<String> list;
        bluetoothNative = (BluetoothNative)NativeLookup.create(BluetoothNative.class);
        
        if(bluetoothNative != null && bluetoothNative.isSupported()) {
            list = StringUtil.tokenize(bluetoothNative.getDevices(), "\n");
        } else {
            list = new ArrayList<>();
            list.add("Select SPP Device");
            list.add("BD1 || 00:15:TT:F2:19:5F");
            list.add("BD2 || 00:15:TT:F2:19:5F");
            list.add("BD3 || 00:15:TT:F2:19:5F");
        }
        
        return list;
    } 
    
    /**
     * Method to read data from the sck talk device
     * @return 
     */
    public synchronized String readData() {
        if(bluetoothNative != null && bluetoothNative.isSupported()) {
            return bluetoothNative.readData();
        } else {
            return dummyData;
        }
    }
    
    /**
     * Method to send data to the motor talk board
     * @param data 
     */
    public synchronized void sendCommand(String data) {
        System.out.println("Sending Data: " + data);
        
        if(connected) {
            data += "\r\n";
            bluetoothNative.sendData(data);
        }
    }
    
    /**
     * Method to send commands to initialize the SCK
     * 
     * @param sckType 
     */
    public void initializeSCK(int sckType) {
        if(sckType == SCK_300) {
            sendCommand("SetStartPWM,5");
            sendCommand("SetSlope,740");
            sendCommand("SetIntercept,200");
        } else {
            sendCommand("SetStartPWM,0");
            sendCommand("SetSlope,960");
            sendCommand("SetIntercept,500");
        }
    }
    
    /**
     * Set the motor speed
     * @param rpm 
     */
    public void setSpeed(String rpm) {
        sendCommand("SetRPM," + rpm);
        setSpeed = Integer.parseInt(rpm);
    }
    
    /**
     * Return the current speed aka rpm
     * @return 
     */
    public String getSpeed() {
        try {
            sendCommand("GetRPM");
            Thread.sleep(100);
            String data = readData();
            if(data.contains("OK")) {
                int value = getDataValue(data);
                // round up to the nearest 50 and return
                // value = ((value+25)/50)*50;
                
                // if we are within +/- 20 rpms pf setSpeed just return the set speed
                int diff = Math.abs(setSpeed - value);
                if(diff <=20) {
                    return "" + setSpeed;
                } else {
                    return "" + value;
                }
            } else {
                return data;
            }
        } catch(InterruptedException ex) {
            return "-1";
        }
    }
    
    /**
     * Extract the data value from the data return from the SCK unit
     * 
     * @param data
     * @return 
     */
    private int getDataValue(String data) {
        int start = data.indexOf(",") + 1;
        int end = data.indexOf(":");
        
        try {
            return Integer.parseInt(data.substring(start, end));
        } catch(NumberFormatException nfe) {
            return -1;
        }
    }
    
    
    
    /**
     * start the motor
     */
    public void startMotor() {
        sendCommand("BLDCon");
    }
    
    /**
     * Stop the SCK running by turning motor off
     */
    public void stopMotor() {
        sendCommand("BLDCoff");
        setSpeed = 0;
    }
}
