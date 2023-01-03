@echo off

rem Download the WinSW.exe binary from https://github.com/winsw/winsw/releases/latest
cd /D "%~dp0"
copy ..\target\test-stub-spring-boot.jar
move WinSW.exe WinSW.test-stub-spring-boot.exe
WinSW.test-stub-spring-boot.exe install
WinSW.test-stub-spring-boot.exe restart
timeout 1
start services.msc
pause
