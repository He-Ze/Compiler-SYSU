echo Running Testcase 004: a correct input from DBv2.
echo ==============================================
echo The input is:
cat testcases/tc-004.infix
echo ----------------------------------------------
cd bin

# Run the testcase with input direction
java Postfix < ../testcases/tc-004.infix

# Compare the expected output
echo ----------------------------------------------
echo The output should be: 
cat ../testcases/tc-004.postfix

cd ..
echo ==============================================
