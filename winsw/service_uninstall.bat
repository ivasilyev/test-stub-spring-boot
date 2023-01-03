@echo off

cd /D "%~dp0"
WinSW.test-stub-spring-boot.exe stop
WinSW.test-stub-spring-boot.exe uninstall
timeout 1
start services.msc

pause
