#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 long call_func(long (*f)()); 
 
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



void run_test(int x,  int y, long expected_sum )
{

    long result; 
    op1=x; 
    op2=y; 
    result=call_func(another_sum); 

    
    TEST_ASSERT_EQUAL_INT64(expected_sum,result); 
    TEST_ASSERT_EQUAL_INT(x,op1); 
    TEST_ASSERT_EQUAL_INT(y,op2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall3); 
    
}

void test_Zero()
{ 
    run_test( 0, 0, 60); 

}

void test_OneA()
{ 
    run_test( 0, 1, 59); 

}

void test_OneB()
{ 
    run_test( 1, 0, 59); 

}

void test_Minus()
{ 
    run_test( 1, -2, 61); 

}

void test_Big()
{ 
    run_test( 5000, 100, -5040); 

}

void test_Big2()
{ 
    run_test( 500000, 100000, -599940); 
}

void test_Big3()
{ 
    run_test( 500000000L, 100000, -500099940); 
}

void test_Big4()
{ 
    run_test( 500000000L, 100000000L, -599999940 ); 
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
    RUN_TEST(test_Big3);
    RUN_TEST(test_Big4);
    return UNITY_END();  

  } 






