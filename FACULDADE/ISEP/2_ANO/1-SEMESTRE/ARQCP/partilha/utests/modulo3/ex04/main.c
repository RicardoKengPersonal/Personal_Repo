#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


void call_func ( void (*f)(short* ptr, int num ), short* ptr, int num);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short * vec, int in_num, short * expected )
{
    short  vec1[100];

    

    // setup 
        memset(vec1, 0x55, sizeof vec1);
     
        	
	    memcpy(vec1+1,vec,in_num*sizeof(short));  //   
	    call_func(vec_add_three,vec1+1,in_num);
    
    TEST_ASSERT_EQUAL_INT16(0x5555, vec1[in_num+1]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT16(0x5555, vec1[0]);    // check sentinel  
    if (in_num !=0) 
	    TEST_ASSERT_EQUAL_INT16_ARRAY(expected, vec1+1, in_num);  
    
}


void test_NullVector()
{ 
    run_test((short[]){0},0,(short[]){0}); 
}
void test_One()
{ 
    run_test((short[]){1},1,(short[]){4}); 
}
void test_Zero()
{ 
    run_test((short[]){1,0,-1},3,(short[]){4,3,2}); 
}
void test_Minus()
{ 
    run_test((short[]){-2,-2,-2},3,(short[]){1,1,1}); 
}
void test_Five()
{ 
    run_test((short[]){1,2,3,4,255},5,(short[]){4,5,6,7,258}); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_NullVector);
    RUN_TEST(test_One);
    RUN_TEST(test_Zero);
    RUN_TEST(test_Minus);
    RUN_TEST(test_Five);
    return UNITY_END();  

  } 






