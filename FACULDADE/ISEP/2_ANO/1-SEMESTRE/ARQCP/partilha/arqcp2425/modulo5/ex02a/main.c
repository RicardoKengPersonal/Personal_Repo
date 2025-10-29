#include <stdio.h>
#include <string.h>

int main( void ){
	
	union union_u1{
	char vec[32];
	float a;
	int b;
	} u;
	union union_u1 * ptr = &u;
	
	strcpy(ptr->vec, "arquitectura de computadores" );
	printf( "[1]=%s\n", ptr->vec );
	
	/*for(int i = 0; i < 32; i++){
		printf("%0x ", u.vec[i]);
	}*/
	
	ptr->a = 123.5;
	
	for(int i = 0; i < 32; i++){
		printf("%0x ", u.vec[i]);
	}
	
	printf( "[2]=%f\n", ptr->a );
	ptr->b = 2;
	printf( "[3]=%d\n", ptr->b );
	
	/*for(int i = 0; i < 32; i++){
		printf("%0x ", u.vec[i]);
	}*/
	
	printf( "[1]=%s\n", ptr->vec );
	printf( "[2]=%f\n", ptr->a );
	printf( "[3]=%d\n", ptr->b );
	return 0;
}	

/*Uma vez que se trata de uma Union e esta utiliza o espaço do tipo
 * de variável de maior tamanho para "armazenar" todas as variáveis, 
 * no fundo quando atribuímos o valor ao vetor de chars e depois atribuímos
 * o valor ao int, este é na verdade escrito "por cima" do que foi definido
 * para o vetor de chars. É por isso que quando damos printf do valor de ptr->b este mantém-se (é 2), tendo em conta
 * que a última atribuição foi a dele; quando voltamos a dar 
 * printf do vec, nada é escrito, isto porque a representação inteira de 2
 * em ascci é 2 0 0 0 e como o vetor é interpretado em little endian, o 2 
 * é interpretado como [START OF TEXT] e o 0 como final de String. Por exemplo
 * se b fosse 1431392833 cuja representação em hexadecimal 55 51 52 41 que
 * equivale ao U Q R e A, respetivamente, o output seria ARQUitectura de computadores.*/
