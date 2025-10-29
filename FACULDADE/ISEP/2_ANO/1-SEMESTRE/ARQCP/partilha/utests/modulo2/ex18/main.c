#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 int call_func(int (*f)()); 
 
 extern int n; 
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



void run_test(int x, int expected)
{

    int result; 
    n=x; 
    result=call_func(sigma); 

    
    TEST_ASSERT_EQUAL_INT(expected,result); 
    TEST_ASSERT_EQUAL_INT(x,n); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    
}

void test_Zero()
{ 
    run_test(0, 0); 

}

void test_One()
{ 
    run_test( 1,6); 

}

void test_Two()
{ 
    run_test(2, 30 ); 

}

void test_Minus()
{ 
    run_test( -10, 0); 

}

void test_Big()
{ 
   run_test(22,22770 ); 

}

void test_Big2()
{ 
   run_test( 33,75174); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Minus);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






