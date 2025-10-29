void get_array_statistics(int* vec, int n, int* min, int* max, float* avg)
{
	if (n <= 0)
	{
		return;
	}
	
	*min = *vec;
	*max = *vec; 
	int sum = 0;
	
	for(int i = 0; i < n; i++)
	{
		if(*vec < *min)
		{
			*min = *vec;
		}
		if(*vec > *max)
		{
			*max = *vec;
		}
		sum = sum + *vec;
		vec++;
	}
	
	*avg = sum/(float)n;
}
