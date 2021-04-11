echo Running Testcase 001: a correct input from DBv2.
echo ==============================================
echo The input is:
cat testcases/tc-001.infix
echo ----------------------------------------------
cd bin

# Run the testcase with input direction
java Postfix < ../testcases/tc-001.infix

# Compare the expected output
echo ----------------------------------------------
echo The output should be: 
cat ../testcases/tc-001.postfix

cd ..
echo ==============================================
