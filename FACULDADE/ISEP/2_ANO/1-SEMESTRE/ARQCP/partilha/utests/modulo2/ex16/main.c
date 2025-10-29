#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 int call_func(int (*f)()); 
 
 extern long num; 
 extern int wall1; 
 extern int wall2; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x12345678;  
      wall2=0x12345678;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, int expected)
{

    int result; 
    num=x; 
    result=call_func(steps); 

    
    TEST_ASSERT_EQUAL_INT(expected,result); 
    TEST_ASSERT_EQUAL_INT64(x,num); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    
}

void test_Zero()
{ 
    run_test(0, 26); 
}

void test_One()
{ 
    run_test(1, 25); 
}

void test_Two()
{ 
    run_test(2, 24); 
}

void test_Eight()
{ 
    run_test( 800, 24); 
}

void test_MinusA()
{ 
    run_test( -1, 27); 
}

void test_MinusB()
{ 
    run_test( -1000,  27); 
}

void test_Big()
{ 
   run_test(50,  24); 
}

void test_Big2()
{ 
   run_test(50000,  24); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Eight);
    RUN_TEST(test_MinusA);
    RUN_TEST(test_MinusB);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






