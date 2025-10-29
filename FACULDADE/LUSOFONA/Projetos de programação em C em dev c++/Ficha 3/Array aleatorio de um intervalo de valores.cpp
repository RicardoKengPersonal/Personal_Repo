#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void selection_sort_asc(int v[], int size);
int iBinarySearch(int v[], int size, int x);
int rBinarySearch(int v[], int ini, int fim, int x);
int linearSearch(int v[], int size, int x);
int sumArray(int v[], int size);
int main()
{
 int i,n,y,soma,lim1=1,lim2=0;
 float avg;
 // dimensao do array
 printf("Dimensao do array: ");
 scanf("%d",&n);
 int vrandom[n];

 // intervalo de valores para a geracao aleatoria (random)
 while (lim1 > lim2)
 {
 printf("\nIntervalo de valores: (limite inferior): ");
 scanf("%d",&lim1);
 printf("\nIntervalo de valores (limite superior): ");
 scanf("%d",&lim2);
 if (lim1 > lim2)
 printf("\nInserir novamente os valores do intervalo");
}

// array de valores aleatórios
 for (i = 0; i < n; i++)
 vrandom[i] = lim1 + ((float)rand()/RAND_MAX)*(float)(lim2-lim1+1);
// imprimir o array de valores aleatorios (random)
 for (i = 0; i < n; i++)
 printf("\nv[%d] = %d | ", i, vrandom[i]);



 // simple linear search
 printf("\nInserir valor a pesquisar: ");
 scanf("%d",&y);

 if (linearSearch(vrandom,n,y) != -1)
 printf("\nValor %d existe na posicao %d",y,linearSearch(vrandom,n,y));
 else
 printf("\nValor %d nao existe no array",y);

 // selection sort and binary search
 selection_sort_asc(vrandom,n);
 return 0;
}
// Binary Search (iterative algorithm)
int iBinarySearch(int v[], int n, int x)
{
int meio, ini, fim;
ini = 0;
fim = n;
// binary search logic
while (ini < fim)
{
meio = (ini + fim) / 2;
if (x < v[meio])
fim = meio - 1;
else if (x > v[meio])
ini = meio + 1;
else
return (meio);
}
return (-1);
}
int rBinarySearch(int v[], int ini, int fim, int x)
{
int meio = (ini + fim)/2;
if (v[meio] == x)
 return meio; // encontrou elemento na posicao meio
if (ini >= fim)
 return -1; // elemento não encontrado
else
 if (v[meio] < x)
 return rBinarySearch(v, meio+1, fim, x);
 else
 return rBinarySearch(v, ini, meio-1, x);
}
// selection sort
void selection_sort_asc(int v[], int n)
{
 int i, j, pos, val=0;
 float temp;
 for (i = 0; i < n-1; i++)
 {
 pos = i;
 for (j = i + 1; j < n; j++)
 {
 if (v[pos] > v[j])
 pos = j;
 }
 if (pos != i)
 {
 temp = v[i];
 v[i] = v[pos];
 v[pos] = temp;
 }
 }

 printf("\nArray v ordenado (crescente):\n");
 for (i = 0; i < n; i++)
 printf("%d\t", v[i]);

 printf("\nElemento a persquisar: ");
 scanf("%d",&val);
 // i = iBinarySearch(v,n,val);
i = rBinarySearch(v,0,n,val);
if (i == -1)
printf("\nO elemento %d nao existe no array", val);
else
printf("\nO elemento %d existe na posicao %d do array (ordenado)", val, i);
}
int linearSearch(int v[], int size, int x)
{
int i;
for (i=0; i<size; i++)
if (v[i] == x) return i;
return -1;
}
int sumArray(int v[], int size)
{
int i,soma;
for
(
i
=
0
;
i
<size
;
i++)
soma
= soma
+
v
[
i];
return soma
;
}
