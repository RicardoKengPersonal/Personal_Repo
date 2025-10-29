@echo off
REM Caminho para o JAR da aplicação e dependências
SET BASE_CP=base.app.backoffice.console\target\base.app.backoffice.console-4.0.0.jar;base.app.backoffice.console\target\dependency\*

REM Executa a aplicação (classe principal Main)
java -cp "%BASE_CP%" eapli.shodrone.app.backoffice.console.BackofficeMain

pause
