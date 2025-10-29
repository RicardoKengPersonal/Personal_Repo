void swap(short *vec1, short *vec2, int size)
{
	short temp = 0; 
	
	for(int i = 0; i < size; i++)
	{
		temp = *(vec1+i);
		*(vec1+i) = *(vec2+i);
		*(vec2+i) = temp;
	}
}
