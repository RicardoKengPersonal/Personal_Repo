#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


void  call_func ( void  (*f)(char* ptr1, char* ptr2, int num),char* ptr1, char* ptr2, int num );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( char * str1, char * str2, int in_num )
{
    char vec1[100];
    char vec2[100];


    // setup 
        memset(vec1, 0x55, sizeof vec1);
        memset(vec2, 0x55, sizeof vec2);
	memcpy(vec1+1,str1,in_num); 
	memcpy(vec2+1,str2,in_num); 
	call_func(swap,vec1+1,vec2+1,in_num);
    
    TEST_ASSERT_EQUAL_INT8(0x55, vec1[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT8(0x55, vec1[0]);    // check sentinel  
    TEST_ASSERT_EQUAL_INT8(0x55, vec2[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT8(0x55, vec2[0]);    // check sentinel  
    if ( in_num != 0) 
    { 
	    TEST_ASSERT_EQUAL_INT8_ARRAY(str1,vec2+1,in_num);  
	    TEST_ASSERT_EQUAL_INT8_ARRAY(str2,vec1+1,in_num);  
    } 
}


void test_NullVector()
{ 
    run_test((char[]){0},(char[]){0},0); 
}
void test_One()
{ 
    run_test((char[]){1},(char[]){2},1); 
}
void test_Zero()
{ 
    run_test((char[]){1,0,2},(char[]){0,3,4},3); 
}
void test_Zero2()
{ 
    run_test((char[]){0,0,0},(char[]){-1,-1,-1},3); 
}
void test_Negative()
{ 
    run_test((char[]){-1,2,3,4},(char[]){-10,-10,-10,-20},4); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_NullVector);
    RUN_TEST(test_One);
    RUN_TEST(test_Zero);
    RUN_TEST(test_Zero2);
    RUN_TEST(test_Negative);
    return UNITY_END();  

  } 






