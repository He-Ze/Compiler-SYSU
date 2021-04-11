echo Running Testcase 003: a correct input from DBv2.
echo ==============================================
echo The input is:
cat testcases/tc-003.infix
echo ----------------------------------------------
cd bin

# Run the testcase with input direction
java Postfix < ../testcases/tc-003.infix

# Compare the expected output
echo ----------------------------------------------
echo The output should be: 
cat ../testcases/tc-003.postfix

cd ..
echo ==============================================
