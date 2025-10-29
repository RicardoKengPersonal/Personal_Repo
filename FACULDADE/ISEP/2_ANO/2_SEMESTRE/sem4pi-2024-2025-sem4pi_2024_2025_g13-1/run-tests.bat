@echo off
echo [TEST] Running Maven tests...

mvn test

if %ERRORLEVEL% EQU 0 (
    echo [TEST] All tests passed successfully.
) else (
    echo [TEST] Some tests failed. Check the logs above.
)

pause
