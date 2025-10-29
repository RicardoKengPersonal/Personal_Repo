#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int* ptr, short num),int* ptr, short num);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int * vec, short in_num, int  sum_expected , int avg_expected)
{
    int vec1[100];

    int res_sum;
    int res_avg; 

    // setup 
        memset(vec1, 0x55, sizeof vec1);
     
	memcpy(vec1+1,vec,in_num*sizeof(int));  //   
	res_sum=call_func(vec_sum,vec1+1,in_num);
	res_avg=call_func(vec_avg,vec1+1,in_num);
    
    TEST_ASSERT_EQUAL_INT(0x55555555, vec1[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT(0x55555555, vec1[0]);    // check sentinel  
    TEST_ASSERT_EQUAL_INT(sum_expected,res_sum);  
    TEST_ASSERT_EQUAL_INT(avg_expected,res_avg);  
    
}


void test_NullVector()
{ 
    run_test((int[]){0},0,0,0); 
}
void test_One()
{ 
    run_test((int[]){1},1,1,1); 
}
void test_Zero()
{ 
    run_test((int[]){1,0,-1},3,0,0); 
}
void test_Minus3()
{ 
    run_test((int[]){-1,-1,-1},3,-3,-1); 
}
void test_Five()
{ 
    run_test((int[]){10,10,10,10,10},5,50,10); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_NullVector);
    RUN_TEST(test_One);
    RUN_TEST(test_Zero);
    RUN_TEST(test_Minus3);
    RUN_TEST(test_Five);
    return UNITY_END();  

  } 






