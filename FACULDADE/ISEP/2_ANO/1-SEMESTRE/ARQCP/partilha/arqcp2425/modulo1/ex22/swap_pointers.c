void swap_pointers(int **b, int **s, int n)
{
	
	int sum1 = 0;
	int sum2 = 0;
	int temp;
	
	for(int i = 0; i < n; i++)
	{
		sum1 = sum1 + **b;
	}
	
	for(int i = 0; i < n; i++)
	{
		sum2 = sum2 + **s;
	}
	
	if(sum1>sum2)
	{
		temp = *b;
		*b = *s;
		*s = temp;
	}
	else
	{
		temp = *s;
		*s = *b;
		*b = temp;
	}
}
