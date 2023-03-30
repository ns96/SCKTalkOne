package com.instras.sck;

public class BluetoothNativeImpl implements com.instras.sck.BluetoothNative {
    private String dummyData = "***JavaSE Data***";
    
    @Override
    public String getDevices() {
        String devices = "Select JavaSE Device\n" + 
                         "BD1 || 00:15:TT:F2:19:5F\n" + 
                         "BD2 || 00:15:TT:F2:19:5F\n" + 
                         "BD3 || 00:15:TT:F2:19:5F";
        
        return devices;
    }

    @Override
    public void sendData(String param) {
        System.out.println("JavaSE Send Data: " + param);
        
        if(param.contains("GetVersion")) {
            dummyData = "MIM Version 0.0";
        } else if(param.contains("SetRPM")) {
            String[] sa = param.split(",");
            dummyData = "OK,-" + sa[1].trim() + ":AA";
        }
    }

    @Override
    public String readData() {
        return dummyData;
    }

    @Override
    public void disconnect() {
        System.out.println("JavaSE Disconnected ...");
    }

    @Override
    public boolean connect(String param) {
        System.out.println("JavaSE Connected: " + param);
        return true;
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
