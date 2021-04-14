cd bin
java test.ExprEvalTest ../testcases/standard.xml  > ../testcases/report.txt
cd ..
cat testcases/report.txt
sleep
rm testcases/report.txt