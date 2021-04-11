echo Running Testcase 002: a correct input from DBv2.
echo ==============================================
echo The input is:
cat testcases/tc-002.infix
echo ----------------------------------------------
cd bin

# Run the testcase with input direction
java Postfix < ../testcases/tc-002.infix

# Compare the expected output
echo ----------------------------------------------
echo The output should be: 
cat ../testcases/tc-002.postfix

cd ..
echo ==============================================
