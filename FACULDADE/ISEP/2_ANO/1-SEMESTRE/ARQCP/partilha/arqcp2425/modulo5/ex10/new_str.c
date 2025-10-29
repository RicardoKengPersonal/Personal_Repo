#include <stdlib.h>

char *new_str(char str[80]){
    char *ptr_str=str;
    int size = 0;

    //get the size of the str array
    while(*str != '\0'){
        str++; 
        size++;
    }
 
    //allocate memory for the new string
    char* str_copied = (char*)malloc((size+1)*sizeof(char));

    //reset the pointer to the beginning of the array
    str = ptr_str;

    //string copy
    for(int i=0;*(str+i)!='\0';i++){
        *(str_copied+i) = *(str+i);
    }

    //add byte 0 to the new string
    *(str_copied+size)='\0';
    return str_copied;
}