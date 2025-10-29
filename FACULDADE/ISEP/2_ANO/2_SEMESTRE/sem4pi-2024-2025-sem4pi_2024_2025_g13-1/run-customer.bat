@echo off
REM Define o classpath com o .jar da aplicação e todas as dependências
SET BASE_CP=base.app.customer.console\target\base.app.customer.console-4.0.0.jar;base.app.customer.console\target\dependency\*;

REM Executa a aplicação (classe principal Main)
java -cp %BASE_CP% eapli.shodrone.app.customer.console.CustomerAppMain
pause
