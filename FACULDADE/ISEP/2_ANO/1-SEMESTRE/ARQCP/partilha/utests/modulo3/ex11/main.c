#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int* ptr, int num), int* ptr, int num );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int * vec, int in_num, int  expected )
{
    int vec1[100];

    int res;

    // setup 
        memset(vec1, 0x55, sizeof vec1);
     
	memcpy(vec1+1,vec,in_num*sizeof(int));  //   
	res=call_func(vec_greater12,vec1+1,in_num);
    
    TEST_ASSERT_EQUAL_INT(0x55555555, vec1[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT(0x55555555, vec1[0]);    // check sentinel  
    TEST_ASSERT_EQUAL_INT(expected,res);  
    if ( in_num != 0 ) 
    TEST_ASSERT_EQUAL_INT_ARRAY(vec, vec1+1, in_num);    // check vec 
    
}


void test_NullVector()
{ 
    run_test((int[]){0},0,0); 
}
void test_One()
{ 
    run_test((int[]){11},1,0); 
}
void test_Zero()
{ 
    run_test((int[]){1,10,-1},3,0); 
}
void test_Three()
{ 
    run_test((int[]){1001,10000,0x10000001},3,3); 
}
void test_Five()
{ 
    run_test((int[]){10,10,10,10,13},5,1); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_NullVector);
    RUN_TEST(test_One);
    RUN_TEST(test_Zero);
    RUN_TEST(test_Three);
    RUN_TEST(test_Five);
    return UNITY_END();  

  } 






