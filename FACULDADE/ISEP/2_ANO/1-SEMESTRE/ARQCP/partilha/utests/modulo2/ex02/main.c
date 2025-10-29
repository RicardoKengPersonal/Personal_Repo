#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 int call_func(int (*f)()); 
 
 extern int op1; 
 extern int op2; 
 extern int wall1; 
 extern int wall2; 
 extern int wall3; 
 

void setUp(void) {
    // set stuff up here
     
      wall1=0x12345678;  
      wall2=0x12345678;  
      wall3=0x12345678; 
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int x,  int y, int expected_sum )
{

    int result; 
    op1=x; 
    op2=y; 
    result=call_func(sum); 

    
    TEST_ASSERT_EQUAL_INT(expected_sum,result); 
    TEST_ASSERT_EQUAL_INT(x,op1); 
    TEST_ASSERT_EQUAL_INT(y,op2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall3); 
    
}

void test_Zero()
{ 
    run_test( 0, 0, 0); 

}

void test_OneA()
{ 
    run_test( 1, 0, 1); 

}

void test_OneB()
{ 
    run_test( 0, 1, 1); 

}

void test_Minus()
{ 
    run_test( 0, -1, -1); 

}

void test_Big()
{ 
    run_test( 5000, 5000, 10000); 

}

void test_Big2()
{ 
    run_test( 50000, 50000, 100000); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Minus);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






