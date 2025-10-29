#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(char *p, int num), char *p, int num);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(char  *p, int num, int expected)
{
    int  res; 

	res = call_func(sum_multiples_x,p,num);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    
}


void test_Zero()
{ 
    run_test((char[]){0},0,0); 
}
void test_One()
{ 
    run_test((char[]){1,0},0x10000,1); 
}
void test_OneA()
{ 
    run_test((char[]){1,1,0},0x10000,2); 
}
void test_OneB()
{ 
    run_test((char[]){3,5,11,12,7,4,0},0xf030100,15); 
}
void test_Three()
{ 
    run_test((char[]){1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,0},0xf050000,30); 
}
void test_ThreeB()
{ 
    run_test((char[]){1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,0},0x40000,40); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Three);
    RUN_TEST(test_ThreeB);
    return UNITY_END();  

  } 






