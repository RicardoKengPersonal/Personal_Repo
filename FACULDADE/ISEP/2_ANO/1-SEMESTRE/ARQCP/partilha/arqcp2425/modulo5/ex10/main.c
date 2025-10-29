#include <stdio.h>
#include <stdlib.h>
#include "new_str.h"


int main (void){
    char str[80] = "ARQCP é a melhor UC do curso de Engenharia Informática do Isep";
    char *str_copied = new_str(str);
    printf("%s ", str_copied);

    free(str_copied);
    return 0;
}

//Explicação:
//É possível retornar o endereço da nova string uma vez que, com a utilização do malloc é possível alocar dinamicamente memória para a string.
//Isto significa que a memória irá permanecer alocada até que seja explicitamente libertada com a função free. 
//Logo, o endereço da string pode ser retornado sem o risco de a memória ser desalocada ou substituída por outras chamadas de funções.