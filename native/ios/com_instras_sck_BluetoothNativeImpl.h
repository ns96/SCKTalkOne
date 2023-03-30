#import <Foundation/Foundation.h>

@interface com_instras_sck_BluetoothNativeImpl : NSObject {
}

-(NSString*)getDevices;
-(void)sendData:(NSString*)param;
-(NSString*)readData;
-(void)disconnect;
-(BOOL)connect:(NSString*)param;
-(BOOL)isSupported;
@end
