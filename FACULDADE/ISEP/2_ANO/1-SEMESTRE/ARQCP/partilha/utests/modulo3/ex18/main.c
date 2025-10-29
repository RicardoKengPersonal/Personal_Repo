#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(short* ptrsrc, short *ptrdest, int num),short* ptrsrc, short* ptrdest, int num);  
void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short * vec, int in_num, int expected, short * exp_vec)
{
    short vec1[100];
    short vec2[100];
    int res; 

    // setup 
        memset(vec1, 0x55, sizeof vec1);
        memset(vec2, 0x55, sizeof vec2);
     

	memcpy(vec1+1,vec,in_num*sizeof(short));  //   
	res=call_func(sort_without_reps,vec1+1,vec2+1,in_num);
    
    TEST_ASSERT_EQUAL_INT16(0x55555555, vec1[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT16(0x55555555, vec1[0]);    // check sentinel  
    TEST_ASSERT_EQUAL_INT(expected,res);   // check res  
    TEST_ASSERT_EQUAL_INT16(0x55555555, vec2[res+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT16(0x55555555, vec2[0]);    // check sentinel  

    if ( in_num != 0 ) 
    { 	    
	    TEST_ASSERT_EQUAL_INT16_ARRAY(exp_vec, vec2+1, res);    // check vec 
    }  
}


void test_NullVector()
{ 
    run_test((short[]){0},0,0,(short[]){0}); 
}
void test_One()
{ 
    run_test((short[]){1000},1,1,(short[]){1000}); 
}
void test_Zero()
{ 
    run_test((short[]){10,0,1},3,3,(short[]){0,1,10}); 
}
void test_Three()
{ 
    run_test((short[]){-1,-3,-3},3,2,(short[]){-3,-1}); 
}
void test_Five()
{ 
    run_test((short[]){1,1,1,1,2},5,2,(short[]){1,2}); 
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






