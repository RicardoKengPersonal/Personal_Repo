#include <string.h> 
#include <stdlib.h>
#include "struct.h" 
structA ** zxc_new_matrix (int lines, int columns) 
// avoid name "collision" with other functions

{ 
   structA **m=malloc(lines*sizeof(structA*)); 
   if (m == NULL )
             exit(-1);  
   int i; 
   for (i=0;i<lines;i++) 
   { 
	m[i]=malloc(columns*sizeof(structA)); 
        if (m[i] == NULL )
             exit(-1);  
   } 		
   return m; 

} 

