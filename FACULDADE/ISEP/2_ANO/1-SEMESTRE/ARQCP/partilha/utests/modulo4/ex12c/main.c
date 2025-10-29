#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int *p, int num), int *p, int num);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int  *p, int num, int expected)
{
    int  res; 

	res = call_func(vec_count_bits_zero,p,num);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    
}


void test_Zero()
{ 
    run_test((int[]){},0,0); 
}
void test_One()
{ 
    run_test((int[]){1},1,31); 
}
void test_OneA()
{ 
    run_test((int[]){0},1,32); 
}
void test_MinusOne()
{ 
    run_test((int[]){-1},1,0); 
}
void test_Three()
{ 
    run_test((int[]){-1,-2,-4},3,3); 
}
void test_Five()
{ 
    run_test((int[]){-1,-1,0,-1,-1},5,32); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_OneA);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_Three);
    RUN_TEST(test_Five);
    return UNITY_END();  

  } 






