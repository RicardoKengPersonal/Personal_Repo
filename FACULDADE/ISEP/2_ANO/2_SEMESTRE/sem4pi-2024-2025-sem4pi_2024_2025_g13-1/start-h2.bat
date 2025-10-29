@echo off
echo A iniciar o H2 com base de dados na home do utilizador...

java -cp h2-2.3.232.jar org.h2.tools.Server -tcp -web -ifNotExists

pause
