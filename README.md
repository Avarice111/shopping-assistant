# shopping-assistant
Shopping assistant Kotlin app to help you with shopping!

##Running Monkey tool
Move to directory
```bash
cd [android-sdk-path]**/platform-tools
```
Run test, this is for 100 gestures, with a delay of 500ms
```bash
adb shell monkey -p forthelulz.shoppingassistant --throttle 500 -v 100
```
