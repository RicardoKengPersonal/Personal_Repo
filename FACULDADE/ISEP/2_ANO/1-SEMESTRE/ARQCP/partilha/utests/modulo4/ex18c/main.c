#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


void call_func ( void (*f)(int *p, int num ), int *p, int num );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int *p, int num, int * expected)
{
    int  test_vec[100]; 
    memset(test_vec, 0x55, sizeof test_vec);
    memcpy(test_vec+1,p,num*sizeof(int)); 

    call_func(changes_vec,test_vec+1,num);
    if (num!=0)
	    TEST_ASSERT_EQUAL_INT_ARRAY(expected,test_vec+1,num); 
    TEST_ASSERT_EQUAL_INT(0x55555555,test_vec[0]); 
    TEST_ASSERT_EQUAL_INT(0x55555555,test_vec[num+1]); 
        
}


void test_Zero()
{ 
    run_test((int[]){0},0,(int[]){0}); 
}
void test_One()
{ 
    run_test((int[]){1},1,(int[]){1}); 
}
void test_Three()
{ 
    run_test((int[]){2,1,0},3,(int[]){2,1,0}); 
}
void test_Four()
{ 
    run_test((int[]){0,0xffffffff,0xff,0xff0fffff,0xff8fffff,0xff7f0fff,0x700000,0x800000},8,
	     (int[]){0,0xff00ffff,0xff,0xfff0ffff,0xff70ffff,0xff800fff,0x700000,0x800000}); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Three);
    RUN_TEST(test_Four);
    return UNITY_END();  

  } 






