#include <string.h>  
#include "../../unity.h"
#include "asm.h" 



void call_func(void (*f)(Student *,  int * ), Student*  s, int  * new_grades );

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( int  new_grades[])
{
   
    Student s; 	
    // setup 
      s.age=0x55;
      s.number=0x5555;
      s.filler=0x55;
      s.grades[0]=0x55555555;
      memset(s.name,0xaa,60);
      memset(s.address,0xaa,100);

      call_func(update_grades,&s,new_grades); 
        TEST_ASSERT_EQUAL_INT8(0x55,s.age);
	TEST_ASSERT_EQUAL_INT16(0x5555,s.number); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[59]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.address[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.address[99]); 
 	TEST_ASSERT_EQUAL_INT8(0x55,s.filler); 
	TEST_ASSERT_EQUAL_INT_ARRAY(new_grades,s.grades,5); 
}


void test_Zero()
{ 
    run_test((int[5]){1,2,3,4,5}); 
}
void test_One()
{ 
    run_test((int[5]){10000,20000,30000,40000,50000}); 
}
void test_Two()
{ 
    run_test((int[5]){100000,90000,80000,70000,60000}); 
}
void test_Three()
{ 
    run_test((int[5]){-1,-1,-1,-1,-1}); 
}



int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    return UNITY_END();  

  } 






