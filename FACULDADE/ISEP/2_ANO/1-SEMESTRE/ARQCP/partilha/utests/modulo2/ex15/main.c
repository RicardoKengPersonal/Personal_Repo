#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 int call_func(int (*f)()); 
 
 extern int A; 
 extern int B; 
 extern int C; 
 extern int D; 
 extern int wall1; 
 extern int wall2; 
 extern int wall3; 
 extern int wall4; 
 extern int wall5; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x12345678;  
      wall2=0x12345678;  
      wall3=0x12345678;  
      wall4=0x12345678;  
      wall5=0x12345678;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int x, int y, int w, int z,int expected)
{

    int result; 
    A=x; 
    B=y; 
    C=w; 
    D=z; 
    result=call_func(compute); 

    
    TEST_ASSERT_EQUAL_INT(expected,result); 
    TEST_ASSERT_EQUAL_INT(x,A); 
    TEST_ASSERT_EQUAL_INT(y,B); 
    TEST_ASSERT_EQUAL_INT(w,C); 
    TEST_ASSERT_EQUAL_INT(z,D); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall3); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall4); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall5); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0, 0, 0); 
}

void test_ZeroA()
{ 
    run_test(0, 0, 0, 2, 0); 
}

void test_Two()
{ 
    run_test(2, 2, 0, 2, 2); 
}

void test_Case1()
{ 
    run_test(8000, -8000, 40, -10, 6432000); 
}

void test_Case2()
{ 
    run_test(3000, -5000, 8000, 70, -557142); 
}

void test_Case3()
{ 
    run_test(1000, 1000, 1000, 10, 0); 
}

void test_Case4()
{ 
    run_test(50, 5, 50005, -9, 277777); 
}

void test_Case5()
{ 
    run_test(50000, -50, 6, 7, -400000); 
}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_ZeroA);
    RUN_TEST(test_Two);
    RUN_TEST(test_Case1);
    RUN_TEST(test_Case2);
    RUN_TEST(test_Case3);
    RUN_TEST(test_Case4);
    RUN_TEST(test_Case5);
    return UNITY_END();  

  } 






