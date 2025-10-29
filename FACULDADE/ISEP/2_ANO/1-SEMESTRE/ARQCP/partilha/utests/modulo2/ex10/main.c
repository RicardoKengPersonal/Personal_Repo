#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

long long  call_func(long long  (*f)()); 
 
 extern int op1; 
 extern int op2;  
 extern int op3;  
 extern int wall1; 
 extern int wall2; 
 extern int wall3; 
 extern int wall4; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x12345678;  
      wall2=0x12345678;  
      wall3=0x12345678;  
      wall4=0x12345678;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int x, int y, int z, long expected)
{

    long result; 
    op1=x; 
    op2=y; 
    op3=z; 
    result=call_func(sum3ints); 

    
    TEST_ASSERT_EQUAL_INT64(expected,result); 
    TEST_ASSERT_EQUAL_INT(x,op1); 
    TEST_ASSERT_EQUAL_INT(y,op2); 
    TEST_ASSERT_EQUAL_INT(z,op3); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall3); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall4); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0, 0); 
}

void test_OneA()
{ 
    run_test(0, 1, 0, 1); 
}

void test_OneB()
{ 
    run_test(1, 0, 0, 1); 

}

void test_Minus()
{ 
    run_test( 0, -1, 0, -1); 
}

void test_MinusA()
{ 
    run_test( 0, 0, -1, -1); 
}

void test_MinusB()
{ 
    run_test( -1, -1, -1, -3); 
}

void test_Big()
{ 
   run_test(5000, 5000, 5000, 15000); 

}

void test_Big2()
{ 
   run_test(2000000, 2000000, 2000000, 6000000); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Minus);
    RUN_TEST(test_MinusA);
    RUN_TEST(test_MinusB);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






