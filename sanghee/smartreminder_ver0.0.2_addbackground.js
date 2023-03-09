import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import BleManager from 'react-native-ble-manager';
import BackgroundTask from 'react-native-background-task';

const ReminderScreen = () => {
  const [isConnected, setIsConnected] = useState(false);

  useEffect(() => {
    BleManager.start({ showAlert: false });

    BleManager.checkState();

    const subscription = BleManager.onStateChange((state) => {
      if (state === 'PoweredOn') {
        BleManager.scan([], 5, true)
          .then(() => console.log('Scanning...'))
          .catch((err) => console.error(err));
      } else if (state === 'PoweredOff') {
        console.log('Bluetooth is powered off');
      }
    }, true);

    return () => {
      subscription.remove();
    };
  }, []);

  useEffect(() => {
    const subscription = BleManager.onPeripheralConnect((peripheral) => {
      setIsConnected(true);
      console.log(`Connected to ${peripheral.name}`);
      BackgroundTask.schedule();
    });

    return () => {
      subscription.remove();
    };
  }, []);

  BackgroundTask.define(() => {
    // TODO: Trigger alarm here
    BackgroundTask.finish();
  });

  return (
    <View>
      <Text>Reminder Screen</Text>
      <Text>{isConnected ? 'Bluetooth Connected' : 'Bluetooth Disconnected'}</Text>
    </View>
  );
};

export default ReminderScreen;