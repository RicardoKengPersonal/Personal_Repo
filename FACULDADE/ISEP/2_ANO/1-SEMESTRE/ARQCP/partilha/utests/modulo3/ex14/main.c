#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int   call_func ( int  (*f)(int* ptr, int num), int* ptr, int num );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int * vec, int in_num, int expected )
{
    int vec1[100];

    int  res; 

    // setup 
        memset(vec1, 0x55, sizeof vec1);
     
	memcpy(vec1+1,vec,in_num*sizeof(int));  //   
	res = call_func(vec_diff,vec1+1,in_num);
    
    TEST_ASSERT_EQUAL_INT(0x55555555, vec1[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT(0x55555555, vec1[0]);    // check sentinel  
    TEST_ASSERT_EQUAL_INT(expected,res);  
    if ( in_num !=0 ) 
	    TEST_ASSERT_EQUAL_INT_ARRAY(vec,vec1+1,in_num); // check if vector has changed 
    
}


void test_NullVector()
{ 
    run_test((int[]){0},0,0); 
}
void test_One()
{ 
    run_test((int[]){1},1,1); 
}
void test_Zero()
{ 
    run_test((int[]){1,0,1},3,1); 
}
void test_Minus3()
{ 
    run_test((int[]){-1,-2,-1,-3,-4},5,3); 
}
void test_Five()
{ 
    run_test((int[]){1,2,3,4,5},5,5); 
}
void test_NotFound()
{ 
    run_test((int[]){-1,-1,-1,-1,-1},5,0); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_NullVector);
    RUN_TEST(test_One);
    RUN_TEST(test_Zero);
    RUN_TEST(test_Minus3);
    RUN_TEST(test_Five);
    RUN_TEST(test_NotFound);
    return UNITY_END();  

  } 






