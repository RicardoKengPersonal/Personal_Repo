#include <string.h>  
#include "../../unity.h"
#include "asm.h" 




int call_func(int (*f)(Student *, int,  int * ),  Student*  s, int minimum, int * new_grades ); 


void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( int minimum, int  new_grades[], int expected , int exp_grades[] )
{
   
    Student s; 	 
    int result;  
     int best_grades[5]; 
    // setup 

      s.age=0x55; 
      s.number=0x5555; 	
      memset(s.grades,0,5*sizeof(int)); 	
      memset(best_grades,0xff,5*sizeof(int)); 	
      memset(s.name,0xaa,60); 	
      memset(s.address,0xaa,100); 
      memcpy(s.grades,new_grades,5*sizeof(int));
      result=call_func(locate_greater,&s,minimum, best_grades);
        TEST_ASSERT_EQUAL_INT8(0x55,s.age);
	TEST_ASSERT_EQUAL_INT16(0x5555,s.number); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[59]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.address[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.address[99]); 
	TEST_ASSERT_EQUAL_INT(expected,result); 
	if (result>0) 
		TEST_ASSERT_EQUAL_INT_ARRAY(exp_grades,best_grades,result); 
	if (result<5) 
		TEST_ASSERT_EQUAL_INT(-1,best_grades[result]); 
}


void test_Zero()
{ 
    run_test(200,(int[5]){6,7,8,9,10},0,(int[]){0}); 
}
void test_One()
{ 
    run_test(9,(int[5]){6,7,8,9,10},1,(int[]){10}); 
}
void test_Two()
{ 
    run_test(8,(int[5]){6,7,8,9,10},2,(int[]){9,10}); 
}
void test_Five()
{ 
    run_test(0,(int[5]){6,7,8,9,-1},4,(int[]){6,7,8,9}); 
}
void test_FiveB()
{ 
    run_test(0,(int[5]){-1,6,7,8,9},4,(int[]){6,7,8,9}); 
}
void test_Ten()
{ 
    run_test(-3,(int[5]){1,2,3,4,5},5,(int[]){1,2,3,4,5}); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Five);
    RUN_TEST(test_FiveB);
    RUN_TEST(test_Ten);
    return UNITY_END();  

  } 






