@echo off
cd..
javac .\src\pixelEscape\*.java -d ./bin
java -cp ./bin pixelEscape.Laucher
pause