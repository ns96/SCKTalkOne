package com.instras.sck;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class BluetoothNativeImpl {
    /* For some reason all variables need to be static for this to work */
    
    // Used for debuging purposes
    private static final String TAG = "SPPTalk";
    
    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    
    // The bluetooth adapter
    private static BluetoothAdapter bluetoothAdapter;
    
    // The bluetooth socket
    private static BluetoothSocket bluetoothSocket;
    
    // The thread used to connect and read data from the spp device
    private static ConnectedThread connectedThread;
    
    // boolean to specify that data should be read
    private static boolean readData = true;
    
    // The Handeler
    private static Handler handler;
    
    // The Status for the Handeler
    final int RECIEVE_MESSAGE = 1;        // Status  for Handler
    
    private static StringBuilder sb = new StringBuilder();
    
    private static String currentData = "****";
    
    // used for debugging
    //private static int numberOfCalls = 0;
    
    public boolean connect(String address) {
        // start the lopper thread to create the handeler so we don't get an exceptions
        LooperThread looperThread = new LooperThread();
        looperThread.start();
        
        // now start the thread for reading and writing data to motortalk
        if(connectToDevice(address)) {
            Log.d(TAG, "Connected to SPP @ " + address);
            return true;
        } else {
            Log.d(TAG, "Error -- Fail to Connect to SPP @ " + address);
            return false;
        }
    }
    
    /**
     * Method to connect to the spp device
     * 
     * @param address
     * @return 
     */
    private boolean connectToDevice(String address) {
        try {
            // Set up a pointer to the remote node using it's address.
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
            
            // Two things are needed to make a connection:
            // A MAC address, which we got above.
            // A Service ID or UUID.  In this case we are using the
            // UUID for SPP.
            bluetoothSocket = createBluetoothSocket(device);
        } catch (Exception ex) {
            currentData = "Failed to Created Bluetooth Socket: " + bluetoothAdapter;
            Log.d(TAG, currentData);
            return false;
        }
        
        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        //bluetoothAdapter.cancelDiscovery();
    
        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting...");
        
        try {
            bluetoothSocket.connect();
            Log.d(TAG, "...Connection ok...");
        } catch (IOException e) {
            try {
                bluetoothSocket.close();
            } catch (IOException e2) {
                Log.d(TAG, "Error closing BT socket", e2);
            }
            
            currentData = "Error Open Socket: " + e;
            
            return false;
        }
      
        // Create the connection thread so we send/read data from the SCK unit
        connectedThread = new ConnectedThread(bluetoothSocket);
        
        if(connectedThread.isConnected()) {
            connectedThread.start();
            return true;
        } else {
            return false;
        }       
    }
    
    /**
     * Method to create a Bluetooth socket
     * @param device
     * @return
     * @throws IOException 
     */
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws Exception {
      if(Build.VERSION.SDK_INT >= 10){
          try {
              final Method  m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
              return (BluetoothSocket) m.invoke(device, MY_UUID);
          } catch (Exception e) {
              Log.e(TAG, "Could not create Insecure RFComm Connection",e);
          }
      }
      
      return device.createRfcommSocketToServiceRecord(MY_UUID);
    }
    
    /**
     * Method to disconnect the motor talk device
     */
    public void disconnect() {
        // set read data to false so we exit out of the thread run method
        readData = false;
        
        try {
            bluetoothSocket.close();
            Log.d(TAG, "Disconnected SPP");
        } catch (IOException ex) {
            Log.d(TAG, "Error Disconnecting SPP", ex);
        }
    }
    
    /**
     * Read the current data from the motor talk device
     * 
     * @return 
     */
    public String readData() {
        return currentData;
    }
    
    /**
     * Method to get the bluetooth devices it called only once
     * 
     * @return 
     */
    public String getDevices() {
        String devices = "Select SPP Device\n";
        
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            // If there are paired devices
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {
                    // Add the name and address to an array adapter to show in a ListView
                    devices += device.getName() + " || " + device.getAddress() + "\n";
                }
            }
        } else {
            devices = "Bluetooth Not Enabled";
        }
            
        return devices;
    }

    public void sendData(String data) {
        connectedThread.write(data);
    }

    public boolean isSupported() {
        return true;
    }
    
    /**
     * This thread is needed otherwise an exception is thrown
     */
    private class LooperThread extends Thread {
        public void run() {
            Looper.prepare();

            handler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    switch (msg.what) {
                        case RECIEVE_MESSAGE:                                                   // if receive massage
                            byte[] readBuf = (byte[]) msg.obj;
                            String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
                            sb.append(strIncom);                                                // append string
                            int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
                            if (endOfLineIndex > 0) {                                            // if end-of-line,
                                currentData = sb.substring(0, endOfLineIndex);               // extract string
                                sb.delete(0, sb.length());                                      // and clear
                            }
                            //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
                            break;
                    }
                }
            ;
            };
            
            Looper.loop();
        }
    }
    
    /**
     * Thread for read and writing data to the input and output sockets
     */
    private class ConnectedThread extends Thread {
        private final InputStream inputStream;
        private final OutputStream outputStream;
        private boolean connected = true;
        
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
      
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { 
                connected = false;
            }
      
            inputStream = tmpIn;
            outputStream = tmpOut;
        }
        
        /**
         * Returns the connection status. It should be true unless an exception occurs
         * 
         * @return 
         */
        public boolean isConnected() {
            return connected;
        }
      
        @Override
        public void run() {
            byte[] buffer = new byte[256];  // buffer store for the stream
            int bytes; // bytes returned from read()
 
            // Keep listening to the InputStream until an exception occurs
            while (readData) {
                try {
                    // Read from the InputStream
                    if(inputStream != null && handler != null) {
                        bytes = inputStream.read(buffer); // Get number of bytes and message in "buffer"
                        
                        // Send to message queue Handler
                        handler.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();
                    } else {
                        currentData = "InputStream or Handler null ...";
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }
      
        /**
         * Method to send data to the device
         * 
         * @param message 
         */
        public void write(String message) {
            Log.d(TAG, "...Data to send: " + message + "...");
            
            try {
                byte[] msgBuffer = message.getBytes("UTF-8");
                outputStream.write(msgBuffer);
            } catch (IOException e) {
                currentData = "...Error sending data: " + e.getMessage() + "...";
            }
            
            Log.d(TAG, currentData);  
        }
    }

}
