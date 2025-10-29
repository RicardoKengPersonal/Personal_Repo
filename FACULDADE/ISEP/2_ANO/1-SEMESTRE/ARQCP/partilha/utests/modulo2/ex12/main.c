#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 char call_func(char (*f)()); 
 
 extern long A; 
 extern long B;  
 extern long wall1; 
 extern long wall2; 
 extern long wall3; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x123456789abcdef0;  
      wall2=0x123456789abcdef0;  
      wall3=0x123456789abcdef0;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, long y, char expected)
{

    char result; 
    A=x; 
    B=y; 
    result=call_func(isMultiple); 

    
    TEST_ASSERT_EQUAL_INT8(expected,result); 
    TEST_ASSERT_EQUAL_INT64(x,A); 
    TEST_ASSERT_EQUAL_INT64(y,B); 
    TEST_ASSERT_EQUAL_INT64(0x123456789abcdef0,wall1); 
    TEST_ASSERT_EQUAL_INT64(0x123456789abcdef0,wall2); 
    TEST_ASSERT_EQUAL_INT64(0x123456789abcdef0,wall3); 
    
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
    run_test(100, 5, 1); 
}

void test_OneC()
{ 
    run_test( 0x8000000, 0x8000, 1); 
}

void test_MinusA()
{ 
    run_test( 2, 78, 0); 
}

void test_MinusB()
{ 
    run_test( 1000, 10000, 0); 
}

void test_Big()
{ 
   run_test(5000, 5000, 1); 

}

void test_Big2()
{ 
   run_test(-5000000, -50000, 1); 

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






