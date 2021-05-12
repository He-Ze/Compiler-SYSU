cd bin
java test.ExprEvalTest ../testcases/standard.xml  > ../testcases/report.txt
cd ..
cat testcases/report.txt
rm testcases/report.txt