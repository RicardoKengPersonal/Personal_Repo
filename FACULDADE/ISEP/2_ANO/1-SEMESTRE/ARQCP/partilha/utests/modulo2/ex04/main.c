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



void run_test(int x,  int y, long z, long w, long expected_sum )
{

    long result; 
    op1=x; 
    op2=y; 
    op3=z; 
    op4=w; 
    result=call_func(yet_another_sum); 

    
    TEST_ASSERT_EQUAL_INT64(expected_sum,result); 
    TEST_ASSERT_EQUAL_INT(x,op1); 
    TEST_ASSERT_EQUAL_INT(y,op2); 
    TEST_ASSERT_EQUAL_INT64(z,op3); 
    TEST_ASSERT_EQUAL_INT64(w,op4); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall3); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0, 0, 0); 

}

void test_OneA()
{ 
    run_test( 0, 1, 1, 1, -1); 

}

void test_OneB()
{ 
    run_test( 0, 1, 0, 0, -2); 

}

void test_Minus()
{ 
    run_test( 1, -1, -6, 1, -3); 

}

void test_Big()
{ 
    run_test( 5000, 10000, 0x100000000, 4000, 4294952296); 

}

void test_Big2()
{ 
    run_test( 50000, 100000, 50000, 0x500000000,-100000);  

}

void test_Big3()
{ 
    run_test( 50000, 100000, -5000000000, 500, -5000150000); 

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
    return UNITY_END();  

  } 






