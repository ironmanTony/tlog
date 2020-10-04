可以通过app本身看log的库。

# 使用方法：

init
```
TLog.config.openLog = BuildConfig.DEBUG
TLog.initTLog(this)
```
打log：
```
TLog.log("test", "this is a test")
```

打开log查看页面：
```
TLogActivity.start(requireContext())
```
如果自定义可以直接使用`TLogFragment`

<img src="./img/tlog_main.png" width="375"/>

<img src="./img/tlog_filter.png" width="375"/>

