int average(int n1, int n2)
{
	return (n1+n2)/2;
}

int average_array(int v[],int n)
{
	int sum_array=0;
	int result;
	
	for(int i = 0; i < n ; i++)
	{
		sum_array = sum_array + v[i];
	}
	
	result = sum_array/n;
	
	return result;
}
