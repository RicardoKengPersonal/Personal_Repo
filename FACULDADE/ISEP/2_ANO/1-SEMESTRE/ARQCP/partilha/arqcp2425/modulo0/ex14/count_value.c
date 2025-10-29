int cmp(int a, int b)
{
	int resultado = 0;
	
	if(a > b)
	{
		resultado = 1;
	}
	if(a == b)
	{
		resultado = 0;
	}
	if(a < b)
	{
		resultado = -1;
	}
	
	return resultado;
}

int count_value(int vec[],int n, int value)
{
	int i = 0;
	int iteration_result = 0;
	int count = 0;
	
	while(i < n)
	{
		iteration_result = cmp(vec[i],value);
		if(iteration_result == 0)
		{
			count++;
		}
		else
		{
			count = count;
		}
		i++;
	}
	
	return count;
}
