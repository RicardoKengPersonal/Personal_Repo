@echo off
echo A terminar qualquer processo H2 ativo...

powershell -Command "Get-WmiObject Win32_Process | Where-Object { $_.CommandLine -like '*h2-2.3.232.jar*' } | ForEach-Object { Stop-Process -Id $_.ProcessId -Force }"

echo A iniciar novamente o servidor H2...
java -cp h2-2.3.232.jar org.h2.tools.Server -tcp -web -baseDir "C:/data" -ifNotExists

pause
