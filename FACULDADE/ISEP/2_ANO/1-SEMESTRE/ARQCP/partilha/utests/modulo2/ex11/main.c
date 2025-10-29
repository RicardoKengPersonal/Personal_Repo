#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 char call_func(char (*f)()); 
 
 extern short op1; 
 extern short op2;  
 extern short wall1; 
 extern short wall2; 
 extern short wall3; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x1234;  
      wall2=0x1234;  
      wall3=0x1234;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, short y, char expected)
{

    char result; 
    op1=x; 
    op2=y; 
    result=call_func(verify_flags); 

    
    TEST_ASSERT_EQUAL_INT8(expected,result); 
    TEST_ASSERT_EQUAL_INT16(x,op1); 
    TEST_ASSERT_EQUAL_INT16(y,op2); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall1); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall2); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall3); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0); 
}

void test_OneA()
{ 
    run_test(1, -1, 1); 
}

void test_OneB()
{ 
    run_test(1, 0x7fff, 1); 
}

void test_OneC()
{ 
    run_test( 0x8000, 0x8000, 1); 
}

void test_MinusA()
{ 
    run_test( 2, 78, 0); 
}

void test_MinusB()
{ 
    run_test( -1, -1, 1); 
}

void test_Big()
{ 
   run_test(5000, 5000, 0); 

}

void test_Big2()
{ 
   run_test(50000, 5000, 0); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_OneC);
    RUN_TEST(test_MinusA);
    RUN_TEST(test_MinusB);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






