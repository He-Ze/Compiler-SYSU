cd bin
java test.ExprEvalTest ../testcases/mytest.xml  > ../testcases/report.txt
cd ..
cat testcases/report.txt
rm testcases/report.txt