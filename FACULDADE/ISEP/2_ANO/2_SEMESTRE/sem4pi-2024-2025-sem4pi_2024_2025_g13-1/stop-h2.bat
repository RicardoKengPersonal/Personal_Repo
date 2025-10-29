@echo off
echo A terminar o servidor H2...
powershell -Command "Get-WmiObject Win32_Process | Where-Object { $_.CommandLine -like '*h2-2.3.232.jar*' } | ForEach-Object { Stop-Process -Id $_.ProcessId -Force }"
pause
