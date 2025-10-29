#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 long  call_func(long (*f)()); 
 

 extern short C; 
 extern short D;  
 extern int A; 
 extern char B ; 
 extern int wall1; 
 extern int wall2; 
 extern short wall3; 
 extern char wall4; 
 extern char wall5; 

void setUp(void) {
    // set stuff up here
      wall1=0x12345678;  
      wall2=0x12345678;  
      wall3=0x1234;  
      wall4=0x34;  
      wall5=0x12;  
            
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, int y, short w, char z, long  expected)
{

    long result; 
        C=x; 
        A=y; 
 	    D=w; 
        B=z; 
        result=call_func(sum_and_subtract); 

    
    TEST_ASSERT_EQUAL_INT64(expected,result); 
    TEST_ASSERT_EQUAL_INT16(x,C); 
    TEST_ASSERT_EQUAL_INT(y,A); 
    TEST_ASSERT_EQUAL_INT16(w,D); 
    TEST_ASSERT_EQUAL_INT8(z,B); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall3); 
    TEST_ASSERT_EQUAL_INT8(0x34,wall4); 
    TEST_ASSERT_EQUAL_INT8(0x12,wall5); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0, 0, 0); 

}

void test_OneA()
{ 
    run_test(1, 0, 0, 0, 1); 

}

void test_OneB()
{ 
    run_test(0, 0, 0, 1, -1); 

}

void test_Minus()
{ 
    run_test(0, 1, 0, 0, -1); 

}

void test_MinusB()
{ 
    run_test(0, -1, 0, -1, 2); 

}

void test_Big()
{ 
    run_test(50, -500000, -1, 100, 499949); 

}

void test_Big2()
{ 
    run_test(50, 500000, -100, 100, -500150); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Minus);
    RUN_TEST(test_MinusB);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






