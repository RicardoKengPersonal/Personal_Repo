int sum_odd(int *p)
{
    int size = *p;
    int sum = 0;
    
    p++;
    
    for(int i = 0; i < size; i++)
    {
		if(*p % 2 != 0)
		{
			sum += *p;
		}
		p++;
	}
	
	if(size == 0)
	{
		sum = 0;
	}

	
    return sum;  // Return the sum of odd elements
}
